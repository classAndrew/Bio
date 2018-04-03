package universe.utils;

import universe.entities.Organism;

public class Visuals {

	// Output
	public static void formatOutput (Organism o, long timedelta, int iterations) {
		System.out.println("--==Winner Winner Chicken Dinner==--");
		System.out.println("Genome: " + Organisms.ArrayListToString(o.dna.genome).replaceAll("_", " ") + "\nFitness: " + o.fitness.fitnessScore);
		System.out.println("Generations: " + iterations);
		System.out.println("Time Elapsed: " + (float) timedelta / 1000 + " seconds");
	}
	
}
