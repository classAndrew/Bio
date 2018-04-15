package universe.utils;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;

import universe.entities.Organism;

public class Visuals {

	// Output
	public static void formatOutput (Organism o, long timedelta, int iterations) {
		System.out.println("--==Winner Winner Chicken Dinner==--");
		System.out.println("Genome: " + Organisms.ArrayListToString(o.dna.genome).replaceAll("_", " ") + "\nFitness: " + o.fitness.fitnessScore);
		System.out.println("Generations: " + iterations);
		System.out.println("Time Elapsed: " + (float) timedelta / 1000 + " seconds");
	}
	public static String getGenome (Organism o) {
		return Organisms.ArrayListToString(o.dna.genome).replaceAll("_", " ");
	}
	public static synchronized void outGUI (JTextPane lbl, String text) {
		lbl.setText(text);
	}
	
	
}
