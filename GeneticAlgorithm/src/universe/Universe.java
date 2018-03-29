package universe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import universe.entities.Organism;
import universe.entities.exceptions.NotRandomException;

public class Universe {
	
	public static final float mutationRate = 0.5f;
	
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	
	public static final Organism PerfectOrganism = new Organism("helloworldiliketoeat", true);
	public static int GENOME_LENGTH = PerfectOrganism.dna.genome.size();

	
	
	public static void main (String[] args) throws NotRandomException{
		System.out.println("------------------------------");
		System.out.println(PerfectOrganism.dna.genome);
		//100 as modeling population seems fastest.
		Organism[] pop = initPopulation(true, 100);
		boolean ticked = false;
		long initTime = System.currentTimeMillis();
		int iterations = 0;
		while (!ticked){
			for (Organism o : pop) {
				if (o.fitness.fitnessScore > 19){
					long timedelta = System.currentTimeMillis() - initTime;
					System.out.println("--==Winner Winner Chicken Dinner==--");
					System.out.println("Genome: " + o.dna.genome + "\nFitness: " + o.fitness.fitnessScore);
					System.out.println("Generations: " + iterations);
					System.out.println("Time Elapsed: " + (float) timedelta / 1000 + " seconds");
					ticked = true;
				}
				//System.out.println("Genome: " + o.dna.genome + " - " + o.fitness.fitnessScore);
			}
			
			pop = nextGeneration(pop);
			iterations++;
		}
	}
	
	public static Organism[] initPopulation (boolean isRandom, int size) throws NotRandomException {
		if (isRandom){
			Organism[] organisms = new Organism[size];
			for (int i = 0; i < size; i++){
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
		Random rnm = new Random();
		Organism[] newGen = new Organism[len];
		
		HashMap<Integer, Organism> hm = new HashMap<>();
		int totalFitness = intArrayAdder(prevGen);
		for (Organism o : prevGen){
			hm.put(o.fitness.fitnessScore, o);
		}
		
		int[] sortedOrganism = oToFit(prevGen);
		Arrays.sort(sortedOrganism);

		
		Organism[] parents = {hm.get(sortedOrganism[len-2]), hm.get(sortedOrganism[len-1])};
		//System.out.println(parents[0].dna.genome + "    " + parents[1].dna.genome);
		for (int i = 0; i < len; i++) {
			newGen[i] = parents[0].reproduce(parents[1]);
		}
		return newGen;
	}
	
	public static int intArrayAdder (Organism[] ogs){
		int total = 0;
		for (Organism o : ogs) {
			total += o.fitness.fitnessScore;
		}
		return total;
	}
	public static int[] oToFit (Organism[] os){
		int[] fits = new int[os.length];
		for (int i = 0; i < os.length; i++){
			fits[i] = os[i].fitness.fitnessScore;
		}
		return fits;
	}
	
	
	@Deprecated
	public static char[] getAlphabet (){
		// Crazy Unnecessary way to represent alphabet.
		// Please don't use this
		char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return ALPHABET;
	}
}
