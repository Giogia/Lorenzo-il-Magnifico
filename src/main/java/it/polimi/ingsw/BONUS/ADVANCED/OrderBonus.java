package it.polimi.ingsw.BONUS.ADVANCED;

public class OrderBonus extends PermanentBonus{
	private boolean[] skipAction = new boolean[4];
	
	public OrderBonus(boolean[] skipAction) {
		super("OrderBonus");
		this.skipAction = skipAction;
	}
	
	public boolean[] getSkipAction() {
		return skipAction;
	}

}
