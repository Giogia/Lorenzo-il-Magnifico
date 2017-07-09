package it.polimi.ingsw.GC_15;

//exception used when a user takes too long to answer
public class TimeExpiredException extends Exception {
	
	public TimeExpiredException() {
		super("Time is expired!");
	}

}
