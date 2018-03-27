package universe;

import java.util.HashMap;
import java.util.Random;

import universe.entities.Organism;
import universe.entities.exceptions.NotRandomException;

public class Universe {
	
	public static final float mutationRate = 0.90f;
	
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public static final Organism PerfectOrganism = new Organism("helloworld", true);
	public static int GENOME_LENGTH = PerfectOrganism.dna.genome.size();
	public static final int GENERATION_LIMIT = 4;
	
	
	public static void main (String[] args) throws NotRandomException{
		
	}
	
	public static Organism[] InitializePopulation (boolean isRandom, int size) throws NotRandomException {
		if (isRandom){
			Organism[] organisms = new Organism[size+1];
			for (int i = 0; i < size+1; i++){
				organisms[i] = new Organism(buildRandomGenome());
			}
			return organisms;
		} else{
			// Still being worked on...
			throw new NotRandomException("No set of context could be found, use randomness.");
		}
	}
	
	public static String buildRandomGenome (){
		int limit = GENOME_LENGTH;
		Random rnm = new Random();
		String genomeLet = "";
		for (int i = 0; i < limit; i++){
			genomeLet += ALPHABET[rnm.nextInt(ALPHABET.length-1)];
		}
		return genomeLet;
	}
	public static Organism[] nextGeneration (Organism[] prevGen){
		int len = prevGen.length;
		HashMap<Organism, Float> oPool = new HashMap<>();
		Organism[] newGen = new Organism[len];
		int[] fitnesses = new int[len];
		for (int i = 0; i < len-1; i++){
			// For tommorow
		}
		return null;
	}
	
	@Deprecated
	public static char[] getAlphabet (){
		// Crazy Unnecessary way to represent alphabet.
		char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return ALPHABET;
	}
}
