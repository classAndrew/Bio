package universe.fitness.functions;

import universe.Environment;
import universe.Factors;
import universe.entities.DNA;
import universe.entities.Organism;
import universe.interfaces.FitnessDeterminator;

public class DefaultFitness implements FitnessDeterminator{

	@Override
	public int calculateFitness(Organism o) {
		
		int fitSwitch = 0;
		DNA dna = o.dna;
		if (o.isFit) {
			for (int i = 0; i < dna.genome.size(); i++){
				if (dna.genome.get(i).equals(Factors.perfectOrganism.dna.genome.get(i))){
					fitSwitch++;
				}
			}
		}
		return fitSwitch;
		
	}

}
