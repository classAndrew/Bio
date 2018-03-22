package universe.entities;

import universe.Universe;

public class Fitness {

	public int fitnessScore;

	public Fitness (Organism o){
		
		fitnessScore = calculateFitness(o);
		
	}
	
	private int calculateFitness (Organism o){
		int fitSwitch = 0;
		DNA dna = o.dna;
		for (int i = 0; i < dna.genome.size(); i++){
			if (dna.genome.get(i).equals(Universe.PerfectOrganism.dna.genome.get(i))){
				fitSwitch++;
			}
		}
		return fitSwitch;
	}
	
	
	@SuppressWarnings("unused")
	/* For Future uses in case of favors.
	 * Modifications to fitnessScore can also be done just by doing organism.fitness.fitnessScore +=... 
	 */
	private void modifyFitness (int modification){
		fitnessScore += modification;
	}
	
}
