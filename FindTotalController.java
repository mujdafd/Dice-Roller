package ca.sheriadancollege.faridmu;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/getTotal")
public class FindTotalController {
	
	@PostMapping(consumes="application/json")
	public double calculateCost(@RequestBody Dice dice) {
		
		int[] results = dice.getResults();
		int sum = 0;
		for (int i : results)
		    sum += i;
		return sum;
	}
	

}
