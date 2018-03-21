package universe.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import universe.Universe;

public class DNA {

	public ArrayList<String> genome = new ArrayList<String>();
	
	public DNA (String trait){
		
		for (char s : trait.toCharArray()){
			
			genome.add(String.valueOf(s));
		}
		
	}
	
	public void mutate (){
		
		Random rnm = new Random();
		int mutationTarget = rnm.nextInt(genome.size() - 1);
		genome.set(mutationTarget, String.valueOf(Universe.ALPHABET[rnm.nextInt(Universe.ALPHABET.length - 1)]));
		
	}
	

	
}
