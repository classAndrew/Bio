package universe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import universe.entities.Organism;
import universe.entities.exceptions.NotRandomException;
import universe.utils.Organisms;
import universe.utils.Visuals;

public class Universe {
	
	public static final float mutationRate = 0.50f;
	
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz_.".toCharArray();
	
	public static final Organism PerfectOrganism = new Organism("longest sentence", true);
	public static int GENOME_LENGTH = PerfectOrganism.dna.genome.size();

	
	
	public static void main (String[] args) throws NotRandomException{
		System.out.println("------------------------------");
		System.out.println(PerfectOrganism.dna.genome);
		//100 as modeling population seems fastest.
		// Others would work
		Organism[] pop = initPopulation(true, 100);
		boolean ticked = false;
		long initTime = System.currentTimeMillis();
		int iterations = 0;
		while (!ticked){
			for (Organism o : pop) {
				if (o.fitness.fitnessScore > GENOME_LENGTH-1){
					long timedelta = System.currentTimeMillis() - initTime;
					Visuals.formatOutput(o, timedelta, iterations);
					ticked = true;
				}
				//System.out.println("Genome: " + o.dna.genome + " - " + o.fitness.fitnessScore);
			}
			
			pop = Organisms.nextGeneration(pop);
			iterations++;
		}
	}
	
	public static Organism[] initPopulation (boolean isRandom, int size) throws NotRandomException {
		if (isRandom){
			Organism[] organisms = new Organism[size];
			for (int i = 0; i < size; i++){
				organisms[i] = new Organism(Organisms.buildRandomGenome());
			}
			return organisms;
		} else{
			// Still being worked on...
			
			throw new NotRandomException("No set of context could be found, use randomness.");
		}
	}
	


	
	
	
	@Deprecated
	public static char[] getAlphabet (){
		// Crazy Unnecessary way to represent alphabet.
		// Please don't use this
		char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return ALPHABET;
	}
}
