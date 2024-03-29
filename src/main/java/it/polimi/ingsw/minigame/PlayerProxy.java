package it.polimi.ingsw.minigame;

import java.io.Serializable;
import java.util.ArrayList;

import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.FamilyMember;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;

public class PlayerProxy implements Serializable{

	private String name;
	private Color color;
	private PersonalBoardProxy personalBoardProxy;
	private ArrayList<FamilyMemberProxy> familyMemberProxies = new ArrayList<>();
	private ArrayList<LeaderCardProxy> leaderCardInHandProxies = new ArrayList<>();
	
	public PlayerProxy(Player player) {
		name = player.getName();
		color = player.getColor();
		personalBoardProxy = new PersonalBoardProxy(player.getPersonalBoard());
		for(FamilyMember familyMember : player.getFamilyMembers()){
			familyMemberProxies.add(new FamilyMemberProxy(familyMember));
		}
		for(LeaderCard leaderCard : player.getLeaderCardInHand()){
			System.out.println(leaderCard);
			leaderCardInHandProxies.add(new LeaderCardProxy(leaderCard));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public Color getColor() {
		return color;
	}
	
	public PersonalBoardProxy getPersonalBoardProxy() {
		return personalBoardProxy;
	}
	
	public ArrayList<FamilyMemberProxy> getFamilyMemberProxies() {
		return familyMemberProxies;
	}
	
	public FamilyMemberProxy getFamilyMemberProxy(DiceColour diceColour) {
		for (FamilyMemberProxy familyMemberProxy : familyMemberProxies) {
			if(familyMemberProxy.getDiceColour().equals(diceColour))
				return familyMemberProxy;
		}
		return null;
	}
	
	public ArrayList<LeaderCardProxy> getLeaderCardInHandProxies() {
		return leaderCardInHandProxies;
	}
}
