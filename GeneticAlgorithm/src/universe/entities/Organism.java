package universe.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import universe.Universe;
import universe.entities.exceptions.CancerousException;

public class Organism {

	public String trait;
	public DNA dna;
	public Fitness fitness;
	public boolean isFit;
	public boolean isDead;
	
	public Organism(String trait, boolean isPerfect){
		// Use only for perfect organism or goal trying to be reached.
		this.trait = trait;
		this.dna = new DNA(this.trait);
		if (!isPerfect){
			this.fitness = getFitness();	
		}
		if (dna.genome.contains(DNA.cancerousPolyTail)) {
			// Kill this organism immediately if found tail 
			this.Apoptosize();
		}
		this.isFit = getFitted();
		isDead = false;
		makeEven();
		
	}
	
	public Organism (String trait){
		this.trait = trait;
		this.dna = new DNA(this.trait);
		if (dna.genome.contains(DNA.cancerousPolyTail)) {
			// Kill this organism immediately if found tail 
			this.Apoptosize();
		}
		this.isFit = getFitted();
		this.fitness = getFitness();
		isDead = false;
	}
	
	@Deprecated
	public Organism(DNA genome){
		// Inconvenient
		this.trait = genome.genome.toString();
		this.dna = genome;
		this.fitness = getFitness();
		isDead = false;
	}
	
	public Organism mixGenes (Organism partner) throws CancerousException{
		/*
		 * This place is a heck hole, needs cleaning.
		 */
		//Random rnm = new Random();
		if (!isDead) {
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
		} else {
			throw new CancerousException("Organism is dead / obsolete", this);
		}

	}
	
	public Organism reproduce (Organism partner) throws CancerousException{
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
	
	public void Apoptosize (){
		isDead = true;
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
	/*
	 * This method will make the genome's length of this organism even
	 * This method should only be used for perfect organisms.
	 */
	private void makeEven() {
		if (dna.genome.size() % 2 != 0){
			dna.genome.add(".");
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
