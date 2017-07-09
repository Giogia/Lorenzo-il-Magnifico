package it.polimi.ingsw.GC_15;

import java.lang.Exception;

//exception used to send alerts to the player when breaks a rule
public class MyException extends Exception {

	public MyException(String message) {
        super(message);
    }
}
