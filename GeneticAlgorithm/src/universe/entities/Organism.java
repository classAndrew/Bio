package universe.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import universe.Universe;

public class Organism {

	public String trait;
	public DNA dna;
	public Fitness fitness;
	public boolean isFit;
	
	public Organism(String trait, boolean isPerfect){
		// Use only for perfect organism or goal trying to be reached.
		this.trait = trait;
		this.dna = new DNA(this.trait);
		if (!isPerfect){
			this.fitness = getFitness();	
		}
		this.isFit = getFitted();
		
	}
	
	public Organism (String trait){
		this.trait = trait;
		this.dna = new DNA(this.trait);
		this.isFit = getFitted();
		this.fitness = getFitness();
	}
	
	@Deprecated
	public Organism(DNA genome){
		// Inconvenient
		this.trait = genome.genome.toString();
		this.dna = genome;
		this.fitness = getFitness();
	}
	
	public Organism mixGenes (Organism partner){
		/*
		 * This place is a heck hole, needs cleaning.
		 */
		//Random rnm = new Random();
		ArrayList<String> partnergenome = partner.dna.genome;
		ArrayList<String> thisGenome = dna.genome;
		ArrayList<String> genePool = new ArrayList<>();
		
		String thisGenomeS = new StringBuilder(asString(thisGenome)).insert((Universe.GENOME_LENGTH/2), ',').toString();
		String partnerGenomeS = new StringBuilder(asString(partnergenome)).insert((Universe.GENOME_LENGTH/2), ',').toString();
		String[] thisOffSpring1 = thisGenomeS.split(",");
		
		String[] thisOffSpring2 = partnerGenomeS.split(",");
		addToGenePool(genePool, thisOffSpring1);
		addToGenePool(genePool, thisOffSpring2);
		//System.out.println(Arrays.deepToString(thisOffSpring1));
		shuffleArray(genePool);
		String beforeRepair = genePool.get(new Random().nextInt(4)) + genePool.get(new Random().nextInt(4));
		beforeRepair = fillGenome(beforeRepair);
		if (new Random().nextFloat() <= Universe.mutationRate){
			char[] genomeCharArr = beforeRepair.toCharArray();
			
			int val1 = new Random().nextInt(Universe.GENOME_LENGTH);
			int val2 = new Random().nextInt(Universe.ALPHABET.length);
			
			genomeCharArr[val1] = Universe.ALPHABET[val2];
			beforeRepair = String.valueOf(genomeCharArr);
		}
		
		return new Organism(beforeRepair);
	}
	
	public Organism reproduce (Organism partner){
		/*
		 * Denatured genomes get fixed over here
		 */
		Organism obuf = mixGenes(partner);
		while (true) {
			obuf = mixGenes(partner);
			if (obuf.dna.genome.size() == Universe.GENOME_LENGTH){
				break;
			}
		}
		return obuf;
	}
	
	private Fitness getFitness (){
		return new Fitness(this);
	}
	private boolean getFitted() {
		if (this.dna.genome.size() > Universe.GENOME_LENGTH) {
			return false;
		}
		else {
			return true;
		}
	}
	
	private static String asString (ArrayList<String> sVec){
		String ret = "";
		for (String s : sVec){
			ret += s;
		}
		return ret;
	}
	
	private static void addToGenePool (ArrayList<String> genePool, String[] stringArr){
		for (String s : stringArr){
			genePool.add(s);
		}
	}
	
	private static void shuffleArray (ArrayList<String> vec){

		Random rnm = new Random();
		for (int i = vec.size() - 1; i > 0; i--){
			// ----> <----
			int indexFuture = rnm.nextInt(i + 1);
			String buffer = vec.get(indexFuture);
			vec.set(indexFuture, vec.get(i));
			vec.set(i, buffer);
			
		}
		
	}
	
	private static String fillGenome (String genome){
		/**
		 *  Fills in genomes that do not have enough to meet the requirement genome length.
		 *  @param The original genome
		 *  @return Returns the fixed Genome
		 */
		
		int fixedLength = Universe.GENOME_LENGTH;
		int genomeLength = genome.length();
		
		for (int i = 0; i < fixedLength - genomeLength; i++){
			genome += " ";
		}
		return genome;
	}
}
