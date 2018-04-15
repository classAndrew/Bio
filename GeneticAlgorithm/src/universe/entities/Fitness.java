package universe.entities;

import universe.Environment;
import universe.Factors;

public class Fitness {

	public int fitnessScore;

	public Fitness (Organism o){
		
		fitnessScore = calculateFitness(o);
		
	}
	
	public int calculateFitness (Organism o){
		return Factors.fitnessFunction.calculateFitness(o);
	}
	
	
	@SuppressWarnings("unused")
	/* For Future uses in case of favors.
	 * Modifications to fitnessScore can also be done just by doing organism.fitness.fitnessScore +=... 
	 */
	private void modifyFitness (int modification){
		fitnessScore += modification;
	}
	
}
