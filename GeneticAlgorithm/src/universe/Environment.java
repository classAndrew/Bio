package universe;

import universe.entities.Organism;
import universe.entities.exceptions.NotRandomException;
import universe.interfaces.FitnessDeterminator;
import universe.utils.Organisms;
import universe.utils.Visuals;

public class Environment {

	public float mutationRate;
	public char[] bases;
	public Organism perfectOrganism;
	public FitnessDeterminator fitnessFunction;
	public int genomeLength;
	
	public Environment (float mutationRate, String bases, Organism perfect, FitnessDeterminator fd){
		
		this.mutationRate = mutationRate;
		this.bases = bases.toCharArray();
		this.perfectOrganism = perfect;
		this.fitnessFunction = fd;
		this.genomeLength = perfectOrganism.dna.genome.size();
		
	}
	public void start () throws NotRandomException, InterruptedException{
		System.out.println("------------------------------");
		System.out.println(perfectOrganism.dna.genome);
		setValues();
		//100 as modeling population seems fastest.
		// Others would work
		Organism[] pop = initPopulation(true, 100);
		boolean ticked = false;
		long initTime = System.currentTimeMillis();
		int iterations = 0;
		while (!ticked){
			for (Organism o : pop) {
				if (o.fitness.fitnessScore > genomeLength-1){
					long timedelta = System.currentTimeMillis() - initTime;
					//Visuals.outGUI(Universe.jlbl, Visuals.getGenome(o));
					Visuals.formatOutput(o, timedelta, iterations);
					ticked = true;
					// Threading
					break;
				}
				//System.out.println("Genome: " + o.dna.genome + " - " + o.fitness.fitnessScore);
				//Visuals.formatOutput(o, System.currentTimeMillis()-initTime, iterations);
				//Visuals.outGUI(Universe.jlbl, Visuals.getGenome(o));
				
			}
			
			pop = Organisms.nextGeneration(pop);
			iterations++;
		}
	}
	
	public void setValues (){
		Factors.mutationRate = this.mutationRate;
		Factors.bases = this.bases;
		Factors.genomeLength = this.genomeLength;
		Factors.perfectOrganism = this.perfectOrganism;
		Factors.fitnessFunction = this.fitnessFunction;
	}
	
	public Organism[] initPopulation (boolean isRandom, int size) throws NotRandomException {
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
	
}

