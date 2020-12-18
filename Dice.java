package ca.sheriadancollege.faridmu;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dice {
	private int numberOfDices;
	private int numberOfSides;
	private int [] results;
	private int sum;
	
	
}
