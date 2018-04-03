package universe.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import universe.Universe;
import universe.utils.Organisms;

public class DNA {

	public ArrayList<String> genome = new ArrayList<String>();
	public static String cancerousPolyTail = "****bad****";
	public boolean isNormal = false; //Currently useless. This is useful for checking dna and flagging it
	
	public DNA (String trait){
		spaceFixer(trait);
		if (isNormal){ // bad base cap with polytail
			genome.add(cancerousPolyTail);
		}
	}
	
	public void mutate (){
		
		Random rnm = new Random();
		int mutationTarget = rnm.nextInt(genome.size() - 1);
		genome.set(mutationTarget, String.valueOf(Universe.ALPHABET[rnm.nextInt(Universe.ALPHABET.length - 1)]));
		
	}
	
	public void spaceFixer (String trait){
		
		String traitString = trait.replace(" ", "_");
		for (char s : traitString.toCharArray()){
			genome.add(String.valueOf(s));
		}
	}
	

	
}
