package universe;

import java.util.Arrays;

import universe.entities.DNA;
import universe.entities.Organism;

public class Universe {
	
	public static final float mutationRate = 0.90f;
	public static final char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static Organism PerfectOrganism = new Organism("helloworld");
	public static int GENOME_LENGTH = PerfectOrganism.dna.genome.size();
	public static final int GENERATION_LIMIT = 4;
	
	
	public static void main (String[] args){
		Organism organism1 = new Organism("helloworld");
		System.out.println(organism1.dna.genome);
		Organism organism2 = new Organism("bellosword");
		System.out.println(organism1.reproduce(organism2).dna.genome.toString());
	}
	
}
