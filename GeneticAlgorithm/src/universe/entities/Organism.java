package universe.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import universe.Universe;

public class Organism {

	public String trait;
	public DNA dna;
	
	public Organism(String trait){
		
		this.trait = trait;
		this.dna = new DNA(this.trait);	
		
	}
	
	public Organism(DNA genome){
		
		this.trait = genome.genome.toString();
		this.dna = genome;
		
	}
	
	public Organism reproduce (Organism partner){
		
		//Random rnm = new Random();
		ArrayList<String> partnergenome = partner.dna.genome;
		ArrayList<String> thisGenome = dna.genome;
		ArrayList<String> genePool = new ArrayList<>();
		
		String[] thisGenomeS = asString(thisGenome).split("");
		String[] partnerGenomeS = asString(partnergenome).split("");
		String[] thisOffSpring1 = asString(thisGenome).split(thisGenomeS[(Universe.GENOME_LENGTH / 2)], 2);
		String[] thisOffSpring2 = asString(partnergenome).split(partnerGenomeS[(Universe.GENOME_LENGTH / 2)], 2);
		addToGenePool(genePool, thisOffSpring1);
		addToGenePool(genePool, thisOffSpring2);
		//System.out.println(Arrays.deepToString(thisOffSpring1));
		shuffleArray(genePool);
		String beforeRepair = genePool.get(new Random().nextInt(4)) + genePool.get(new Random().nextInt(4));
		if (new Random().nextFloat() <= Universe.mutationRate){
			char[] genomeCharArr = beforeRepair.toCharArray();
			genomeCharArr[new Random().nextInt(genomeCharArr.length - 1)] = Universe.ALPHABET[new Random().nextInt(Universe.ALPHABET.length - 1)];
			beforeRepair = String.valueOf(genomeCharArr);
		}
		
		return new Organism(fillGenome(beforeRepair));
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
		//I mean c'mon we don't even know what half of the stuff in our genome does
		
		int fixedLength = Universe.GENOME_LENGTH;
		int genomeLength = genome.length();
		
		for (int i = 0; i < fixedLength - genomeLength; i++){
			genome += " ";
		}
		return genome;
	}
}