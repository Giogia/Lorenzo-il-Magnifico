package it.polimi.ingsw.HANDLER.GAME;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.*;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import it.polimi.ingsw.BOARD.ActionZone;
import it.polimi.ingsw.BOARD.CouncilPalace;
import it.polimi.ingsw.BOARD.HarvestArea;
import it.polimi.ingsw.BOARD.Market;
import it.polimi.ingsw.BOARD.ProductionArea;
import it.polimi.ingsw.BOARD.Tower;
import it.polimi.ingsw.BOARD.Zone;
import it.polimi.ingsw.BONUS.ActionBonus;
import it.polimi.ingsw.BONUS.AddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.AddResourceBonus;
import it.polimi.ingsw.BONUS.Bonus;
import it.polimi.ingsw.BONUS.CouncilPrivilegeBonus;
import it.polimi.ingsw.BONUS.FamilyMemberBonus;
import it.polimi.ingsw.BONUS.FamilyMemberValueBonus;
import it.polimi.ingsw.BONUS.ImmediateBonus;
import it.polimi.ingsw.BONUS.MultiplyFamilyMemberBonus;
import it.polimi.ingsw.BONUS.MultiplyResourceBonus;
import it.polimi.ingsw.BONUS.ResourceBonus;
import it.polimi.ingsw.BONUS.ResourcePerDevelopmentCardBonus;
import it.polimi.ingsw.BONUS.ResourceValueBonus;
import it.polimi.ingsw.BONUS.ADVANCED.ActivationZoneBonus;
import it.polimi.ingsw.BONUS.ADVANCED.AddCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CanFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.CopyBonus;
import it.polimi.ingsw.BONUS.ADVANCED.EndGameCardBonus;
import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.LoseVictoryPointsPerResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.MultiplyCardCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.OccupiedTowerCostBonus;
import it.polimi.ingsw.BONUS.ADVANCED.OrderBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentAddResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentMultResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentResourceBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PermanentValueFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.PositionFamilyMemberBonus;
import it.polimi.ingsw.BONUS.ADVANCED.ResourcePerMissedExcommunicationBonus;
import it.polimi.ingsw.BONUS.ADVANCED.TerritoryCardRequirementBonus;
import it.polimi.ingsw.CARD.Building;
import it.polimi.ingsw.CARD.Territory;
import it.polimi.ingsw.CARD.Venture;
import it.polimi.ingsw.GC_15.ExcommunicationTile;
import it.polimi.ingsw.GC_15.PersonalBonusTile;
import it.polimi.ingsw.RESOURCE.Coins;
import it.polimi.ingsw.RESOURCE.FaithPoints;
import it.polimi.ingsw.RESOURCE.MilitaryPoints;
import it.polimi.ingsw.RESOURCE.Resource;
import it.polimi.ingsw.RESOURCE.Servants;
import it.polimi.ingsw.RESOURCE.Stones;
import it.polimi.ingsw.RESOURCE.VictoryPoints;
import it.polimi.ingsw.RESOURCE.Wood;
import it.polimi.ingsw.CARD.Character;
import it.polimi.ingsw.CARD.LeaderCard;
import it.polimi.ingsw.CARD.OncePerRoundLeaderCard;
import it.polimi.ingsw.CARD.PermanentLeaderCard;

public class ConfigurationFileHandler {
	
	private final static Logger LOGGER = Logger.getLogger(ConfigurationFileHandler.class.getName());
	
	public static void main(String[] args) {
		try {
			DataFromFile data = ConfigurationFileHandler.getData();
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
		}
	}
	public static DataFromFile getData() throws FileNotFoundException{
		File file = new File("config.json");
		Scanner scanner= new Scanner(file);
		String inJson="";
		//In this way I can use spaces between characters
		try{
			while (scanner.hasNextLine()){
				inJson = inJson + scanner.nextLine();
			}
		}
		finally{
			scanner.close();
		}
		return toDeserialize(inJson);
	}
	
	public static String toSerialize(Object object){
		try{
			Gson gsonToSerialize = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().create();
			return gsonToSerialize.toJson(object);
		} catch(Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
			return "error"; //TODO da vedere cosa ritornare
		}
	}
	
	public static DataFromFile toDeserialize(String inJson){
		try{
			//Type requestListTypeToken = new TypeToken<List<Territory>>() {}.getType();
			
			final RuntimeTypeAdapterFactory<ResourceBonus> t1 = RuntimeTypeAdapterFactory
					.of(ResourceBonus.class, "subtype")
			        .registerSubtype(AddResourceBonus.class, "addResourceBonus")
			        .registerSubtype(MultiplyResourceBonus.class, "multiplyResourceBonus")
			        .registerSubtype(ResourcePerDevelopmentCardBonus.class, "ATTENTION");//TODO
			
			final RuntimeTypeAdapterFactory<Resource> t2 = RuntimeTypeAdapterFactory
					.of(Resource.class, "resourceType")
			        .registerSubtype(Coins.class, "coins")
			        .registerSubtype(Wood.class, "wood")
			        .registerSubtype(FaithPoints.class, "faithPoints")
			        .registerSubtype(MilitaryPoints.class, "militaryPoints")
			        .registerSubtype(Servants.class, "servants")
			        .registerSubtype(Stones.class, "stones")
			        .registerSubtype(VictoryPoints.class, "victoryPoints");
			        
			final RuntimeTypeAdapterFactory<ImmediateBonus> t3 = RuntimeTypeAdapterFactory
					.of(ImmediateBonus.class, "type")
			        .registerSubtype(ResourceBonus.class, "resourceBonus")
			        .registerSubtype(ActionBonus.class, "actionBonus")
			        .registerSubtype(CouncilPrivilegeBonus.class, "councilPrivilegeBonus")
			        .registerSubtype(FamilyMemberBonus.class, "familyMemberBonus")
					.registerSubtype(CopyBonus.class, "CopyBonus");
			
			final RuntimeTypeAdapterFactory<PermanentBonus> t7 = RuntimeTypeAdapterFactory
					.of(PermanentBonus.class, "subtype")
			        .registerSubtype(ActivationZoneBonus.class, "ActivationZoneBonus")
			        .registerSubtype(CanFamilyMemberBonus.class, "CanFamilyMemberBonus")
			        .registerSubtype(CardCostBonus.class, "CardCostBonus")
			        .registerSubtype(EndGameCardBonus.class, "EndGameCardBonus")
					.registerSubtype(LoseVictoryPointsPerCostBonus.class, "LoseVictoryPointsPerCostBonus")
					.registerSubtype(LoseVictoryPointsPerResourceBonus.class, "LoseVictoryPointsPerResourceBonus")
			        .registerSubtype(OccupiedTowerCostBonus.class, "OccupiedTowerCostBonus")
			        .registerSubtype(OrderBonus.class, "OrderBonus")
			        .registerSubtype(PermanentFamilyMemberBonus.class, "PermanentFamilyMemberBonus")
					.registerSubtype(PermanentResourceBonus.class, "PermanentResourceBonus")
					.registerSubtype(PositionFamilyMemberBonus.class, "PositionFamilyMemberBonus")
					.registerSubtype(TerritoryCardRequirementBonus.class, "TerritoryCardRequirementBonus");
			
			final RuntimeTypeAdapterFactory<CardCostBonus> t8 = RuntimeTypeAdapterFactory
					.of(CardCostBonus.class, "subsubtype")
			        .registerSubtype(AddCardCostBonus.class, "AddCardCostBonus")
			        .registerSubtype(MultiplyCardCostBonus.class, "MultiplyCardCostBonus");
			
			final RuntimeTypeAdapterFactory<PermanentFamilyMemberBonus> t9 = RuntimeTypeAdapterFactory
					.of(PermanentFamilyMemberBonus.class, "subsubtype")
			        .registerSubtype(PermanentAddFamilyMemberBonus.class, "PermanentAddFamilyMemberBonus")
			        .registerSubtype(PermanentMultFamilyMemberBonus.class, "PermanentMultFamilyMemberBonus")
			        .registerSubtype(PermanentValueFamilyMemberBonus.class, "PermanentValueFamilyMemberBonus");
			
			final RuntimeTypeAdapterFactory<PermanentResourceBonus> t10 = RuntimeTypeAdapterFactory
					.of(PermanentResourceBonus.class, "subsubtype")
			        .registerSubtype(PermanentAddResourceBonus.class, "PermanentAddResourceBonus")
			        .registerSubtype(PermanentMultResourceBonus.class, "PermanentMultResourceBonus")
			        .registerSubtype(ResourceValueBonus.class, "ResourceValueBonus")
			        .registerSubtype(ResourcePerMissedExcommunicationBonus.class, "ResourcePerMissedExcommunicationBonus");
			
			
			
			final RuntimeTypeAdapterFactory<FamilyMemberBonus> t4 = RuntimeTypeAdapterFactory
					.of(FamilyMemberBonus.class, "subtype")
			        .registerSubtype(AddFamilyMemberBonus.class, "addFamilyMemberBonus")
			        .registerSubtype(MultiplyFamilyMemberBonus.class, "multiplyFamilyMemberBonus")
			        .registerSubtype(FamilyMemberValueBonus.class, "familyMemberValueBonus");
			
			final RuntimeTypeAdapterFactory<Bonus> t5 = RuntimeTypeAdapterFactory
					.of(Bonus.class, "type1")
			        .registerSubtype(ImmediateBonus.class, "immediateBonus")
					.registerSubtype(PermanentBonus.class, "PermanentBonus");
			
			final RuntimeTypeAdapterFactory<ActionZone> t6 = RuntimeTypeAdapterFactory
					.of(ActionZone.class, "subType")
			        .registerSubtype(HarvestArea.class, "harvestArea")
			        .registerSubtype(ProductionArea.class, "productionArea")
			        .registerSubtype(Tower.class, "Tower");
			
			final RuntimeTypeAdapterFactory<Zone> t11 = RuntimeTypeAdapterFactory
					.of(Zone.class, "type")
			        .registerSubtype(ActionZone.class, "actionZone")
			        .registerSubtype(Market.class, "market")
			        .registerSubtype(CouncilPalace.class, "councilPalace");
			
			final RuntimeTypeAdapterFactory<LeaderCard> t12 = RuntimeTypeAdapterFactory
					.of(LeaderCard.class, "type")
			        .registerSubtype(PermanentLeaderCard.class, "PermanentLeaderCard")
			        .registerSubtype(OncePerRoundLeaderCard.class, "OncePerRoundLeaderCard");
			
			Gson gsonToDeserialize = new GsonBuilder()
					.registerTypeAdapterFactory(t6)
					.registerTypeAdapterFactory(t2)
					.registerTypeAdapterFactory(t10)
					.registerTypeAdapterFactory(t9)
					.registerTypeAdapterFactory(t8)
					.registerTypeAdapterFactory(t4)
					.registerTypeAdapterFactory(t1)
					.registerTypeAdapterFactory(t7)
					.registerTypeAdapterFactory(t3)
					.registerTypeAdapterFactory(t5)
					.registerTypeAdapterFactory(t11)
					.registerTypeAdapterFactory(t12)
					.create();
			
			return gsonToDeserialize.fromJson(inJson, DataFromFile.class);
			//return gsonToDeserialize.fromJson(inJson, Character.class);
			
		} catch (Exception e){
			LOGGER.log(Level.SEVERE, e.getMessage(),e);
			return new DataFromFile(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
			//return new Character("", null, 1, null, null);
			//return new Territory("", 2, 1, null, null);
		}
	}
}
