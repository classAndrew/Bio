package universe.entities.exceptions;

import universe.entities.Organism;

public class CancerousException extends Exception{

	public CancerousException (String sException, Organism o) {
		super(sException);
		// Make sure organism is dead.
		o.Apoptosize();
	}
	
}
