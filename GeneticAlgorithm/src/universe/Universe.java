package universe;

import javax.swing.JLabel;
import javax.swing.JTextPane;

import universe.entities.Organism;
import universe.entities.exceptions.NotRandomException;
import universe.fitness.functions.DefaultFitness;
import universe.interfaces.FitnessDeterminator;

public class Universe {
	
	public static final float mutationRate = 0.50f;
	
	public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz_.".toCharArray();
	
	public static final Organism PerfectOrganism = new Organism("testing", true);
	public static int GENOME_LENGTH = PerfectOrganism.dna.genome.size();
	public static FitnessDeterminator FitnessFunction = new DefaultFitness();
	public static JTextPane jlbl;
	
	
	public static void main (String[] args) throws NotRandomException, InterruptedException{
		new Environment(mutationRate, "abcdefghijklmnopqrstuvwxyz_.", PerfectOrganism, FitnessFunction).start();
	}
	
	
	@Deprecated
	public static char[] getAlphabet (){
		// Crazy Unnecessary way to represent alphabet.
		// Please don't use this
		char[] ALPHABET = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		return ALPHABET;
	}
}
