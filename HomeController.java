package ca.sheriadancollege.faridmu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class HomeController {
	
	@Autowired
	protected DatabaseAccess da;
	
	Dice dice = new Dice();
	
	
	@GetMapping("/")
	public String getIndex(Model model) {
		
		return "index";
		
	}
	
	@PostMapping("/rollDice")
	public String rollDice(Model model, @RequestParam int num,@RequestParam int type,RestTemplate restTemplate) {
		     
		dice.setNumberOfDices(num);
		dice.setNumberOfSides(type);
		int results[] = new int[num]; 
		for(int i=0; i<num; i++){
			results[i] = (int) (Math.random()*type+1);
		}
		dice.setResults(results);
		ResponseEntity<Integer> subTotal = restTemplate.postForEntity(
		   		"http://localhost:8080/getTotal", dice, Integer.class);
		dice.setSum(subTotal.getBody());
		da.insertDice(dice);   
    		
		return "redirect:/rollResult";
		
	}
	
	@GetMapping ("/rollResult")
	public String rollResult(Model model,RestTemplate restTemplate) {
		
		Dice d = da.diceList().get(0);
		model.addAttribute("results", dice.getResults());
		model.addAttribute("sum", dice.getSum());
		return "RollResult";  //html Page
	}
	
	@GetMapping ("/newRoll")
	public String newRoll(Model model) {
		
		dice = new Dice();
		
		da.diceDelete();
		return "redirect:/";  //home Page
	}

}
