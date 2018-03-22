package universe;

import universe.entities.Organism;

public class Universe {
	
	public static final float mutationRate = 0.90f;
	
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public static final Organism PerfectOrganism = new Organism("helloworld", true);
	public static int GENOME_LENGTH = PerfectOrganism.dna.genome.size();
	public static final int GENERATION_LIMIT = 4;
	
	
	public static void main (String[] args){

		Organism organism1 = new Organism("helloworld");
		System.out.println(organism1.dna.genome);
		Organism organism2 = new Organism("bellosword");
		System.out.println(organism1.reproduce(organism2).dna.genome.toString() + "\n");
		System.out.println(new Organism("bellosword").fitness.fitnessScore);
	}
	
	@Deprecated
	public static char[] getAlphabet (){
		// Crazy Unnecessary way to represent alphabet.
		char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return ALPHABET;
	}
}
