package it.polimi.ingsw.BONUS.ADVANCED;

import java.util.ArrayList;

import it.polimi.ingsw.GC_15.Player;

//this bonus makes a player skip a certain action and let it to be done at the end of the round
public class OrderBonus extends PermanentBonus{
	private boolean[] skipAction = new boolean[4];
	
	
	//If skipAction[i] is true, skip the i-th action of the turn
	public OrderBonus(boolean[] skipAction) {
		super("OrderBonus");
		this.skipAction = skipAction;
	}
	
	public boolean[] getSkipAction() {
		return skipAction;
	}
	
	public void setSkipAction(boolean[] skipAction) {
		this.skipAction = skipAction;
	}
	
	@Override
	public void getPermanentBonus(Player player) {
		ArrayList<PermanentBonus> playerBonus = player.getPersonalBoard().getPermanentBonus();
		if (playerBonus != null){
			for (PermanentBonus permanentBonus : playerBonus) {
				if (permanentBonus instanceof OrderBonus){
					boolean[] permanentBonusArray = ((OrderBonus) permanentBonus).getSkipAction();
					for (int i = 0; i < 4; i++) {
						permanentBonusArray[i] = permanentBonusArray[i] || this.skipAction[i];
					}
					((OrderBonus) permanentBonus).setSkipAction(permanentBonusArray);
					return;
				}
			}
		}
		super.getPermanentBonus(player);
	}
	
	public String getDescription(){
		StringBuilder description = new StringBuilder();
		description.append("You skip :\n");
		for(int i = 0; i < 4; i++){
			if(skipAction[i]==true){
				description.append("action number"+ i + "\n");
			}
		}
		description.append("and you do it at the end of the turn \n");
		return description.toString();
	}

}
