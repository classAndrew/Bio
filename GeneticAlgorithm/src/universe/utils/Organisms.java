package universe.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import universe.Environment;
import universe.Factors;
import universe.entities.Organism;
import universe.entities.exceptions.CancerousException;

public class Organisms {

	public static String buildRandomGenome (){
		int limit = Factors.genomeLength;
		Random rnm = new Random();
		String genomeLet = "";
		for (int i = 0; i < limit; i++){
			genomeLet += Factors.bases[rnm.nextInt(Factors.bases.length-1)];
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
		//Just a quick sort, to lazy to do one myself
		Arrays.sort(sortedOrganism);

		
		Organism[] parents = {hm.get(sortedOrganism[len-2]), hm.get(sortedOrganism[len-1])};
		//System.out.println(parents[0].dna.genome + "    " + parents[1].dna.genome);
		for (int i = 0; i < len; i++) {
			try {
				newGen[i] = parents[0].reproduce(parents[1]);
			} catch (CancerousException e) {
				
				e.printStackTrace();
			}
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
	public static String ArrayListToString (ArrayList<String> a){
		StringBuilder s = new StringBuilder();
		for (String i : a) {
			s.append(i);
		}
		return s.toString();
	}
	public static boolean anyNotInArrayList (ArrayList<String> left){
		boolean b = false; //Everything is normal
		for (String s : left) {
			if (!Arrays.asList(Factors.bases).contains(s.toCharArray()[0])){
				b = true;
			}
		}
		return b;
	}
}
