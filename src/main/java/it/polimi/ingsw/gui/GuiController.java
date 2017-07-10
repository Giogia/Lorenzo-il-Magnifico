package it.polimi.ingsw.gui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.RESOURCE.ResourceType;
import it.polimi.ingsw.minigame.BoardProxy;
import it.polimi.ingsw.minigame.CardContainerProxy;
import it.polimi.ingsw.minigame.CouncilPalaceProxy;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.DiceProxy;
import it.polimi.ingsw.minigame.FamilyMemberProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.HarvestProxy;
import it.polimi.ingsw.minigame.MarketProxy;
import it.polimi.ingsw.minigame.OrderPawn;
import it.polimi.ingsw.minigame.PersonalBoardProxy;
import it.polimi.ingsw.minigame.PlayerProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.ProductionProxy;
import it.polimi.ingsw.minigame.ResourceProxy;
import it.polimi.ingsw.minigame.RoundOrderProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;
import it.polimi.ingsw.minigame.TowerProxy;
import it.polimi.ingsw.minigame.ZoneProxy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

//controller for every action made by user in gui 
public class GuiController implements Initializable {
	private FXMLLoader loader;
	private GameProxy game;
	private String stringToSend;
	private boolean isRmiClient;
	
	public void setIsRmiClient(boolean b){
		isRmiClient = b;
	}
	
	public GuiController(GameProxy game) {
		this.game = game;
	}
	
    @FXML
    private Label coins_player1;

    @FXML
    private Label wood_player1;

    @FXML
    private Label stones_player1;

    @FXML
    private Label servants_player1;
	
    @FXML
    private Label faithPoints_player1;

    @FXML
    private Label militaryPoints_player1;

    @FXML
    private Label victoryPoints_player1;
    
    @FXML
    private Label servants_player2;

    @FXML
    private Label stones_player2;

    @FXML
    private Label wood_player2;

    @FXML
    private Label coins_player2;

    @FXML
    private Label faithPoints_player2;

    @FXML
    private Label militaryPoints_player2;

    @FXML
    private Label victoryPoints_player2;
    
    @FXML
    private Label servants_player3;

    @FXML
    private Label stones_player3;

    @FXML
    private Label wood_player3;

    @FXML
	private Labeled coins_player3;
    
    @FXML
    private Label faithPoints_player3;

    @FXML
    private Label militaryPoints_player3;

    @FXML
    private Label victoryPoints_player3;
    
    @FXML
    private Label servants_player4;

    @FXML
    private Label stones_player4;

    @FXML
    private Label wood_player4;

    @FXML
	private Labeled coins_player4;
    
    @FXML
    private Label faithPoints_player4;

    @FXML
    private Label militaryPoints_player4;

    @FXML
    private Label victoryPoints_player4;
    
    @FXML
    private TextField answerChat;

    @FXML
    private ImageView territoryfloor4;

    @FXML
    private ImageView territoryfloor3;

    @FXML
    private ImageView territoryfloor2;

    @FXML
    private ImageView territoryfloor1;

    @FXML
    private ImageView characterfloor4;

    @FXML
    private ImageView characterfloor3;

    @FXML
    private ImageView characterfloor2;

    @FXML
    private ImageView characterfloor1;

    @FXML
    private ImageView buildingfloor4;

    @FXML
    private ImageView buildingfloor3;

    @FXML
    private ImageView buildingfloor2;

    @FXML
    private ImageView buildingfloor1;

    @FXML
    private ImageView venturefloor4;

    @FXML
    private ImageView venturefloor3;

    @FXML
    private ImageView venturefloor2;

    @FXML
    private ImageView venturefloor1;

    @FXML
    private Tab tabPlayer1;

    @FXML
    private ImageView personalBoard1;

    @FXML
    private ImageView personalBonusTile1;

    @FXML
    private Group player1_building;

    @FXML
    private ImageView player1_building1;

    @FXML
    private ImageView player1_building2;

    @FXML
    private ImageView player1_building3;

    @FXML
    private ImageView player1_building4;

    @FXML
    private ImageView player1_building5;

    @FXML
    private ImageView player1_building6;

    @FXML
    private Group player1_territory;

    @FXML
    private ImageView player1_territory1;

    @FXML
    private ImageView player1_territory2;

    @FXML
    private ImageView player1_territory3;

    @FXML
    private ImageView player1_territory4;

    @FXML
    private ImageView player1_territory5;

    @FXML
    private ImageView player1_territory6;

    @FXML
    private Group player1_character;

    @FXML
    private ImageView player1_character1;

    @FXML
    private ImageView player1_character2;

    @FXML
    private ImageView player1_character3;

    @FXML
    private ImageView player1_character4;

    @FXML
    private ImageView player1_character5;

    @FXML
    private ImageView player1_character6;

    @FXML
    private Group player1_venture;

    @FXML
    private ImageView player1_venture1;

    @FXML
    private ImageView player1_venture2;

    @FXML
    private ImageView player1_venture3;

    @FXML
    private ImageView player1_venture4;

    @FXML
    private ImageView player1_venture5;

    @FXML
    private ImageView player1_venture6;

    @FXML
    private Tab tabPlayer2;

    @FXML
    private ImageView personalBoard2;

    @FXML
    private ImageView personalBonusTile2;

    @FXML
    private Group player2_building;

    @FXML
    private ImageView player2_building1;

    @FXML
    private ImageView player2_building2;

    @FXML
    private ImageView player2_building3;

    @FXML
    private ImageView player2_building4;

    @FXML
    private ImageView player2_building5;

    @FXML
    private ImageView player2_building6;

    @FXML
    private Group player2_territory;

    @FXML
    private ImageView player2_territory1;

    @FXML
    private ImageView player2_territory2;

    @FXML
    private ImageView player2_territory3;

    @FXML
    private ImageView player2_territory4;

    @FXML
    private ImageView player2_territory5;

    @FXML
    private ImageView player2_territory6;

    @FXML
    private Group player2_character;

    @FXML
    private ImageView player2_character1;

    @FXML
    private ImageView player2_character2;

    @FXML
    private ImageView player2_character3;

    @FXML
    private ImageView player2_character4;

    @FXML
    private ImageView player2_character5;

    @FXML
    private ImageView player2_character6;

    @FXML
    private Group player2_venture;

    @FXML
    private ImageView player2_venture1;

    @FXML
    private ImageView player2_venture2;

    @FXML
    private ImageView player2_venture3;

    @FXML
    private ImageView player2_venture4;

    @FXML
    private ImageView player2_venture5;

    @FXML
    private ImageView player2_venture6;

    @FXML
    private Tab tabPlayer3;

    @FXML
    private ImageView personalBoard3;

    @FXML
    private ImageView personalBonusTile3;

    @FXML
    private Group player3_building;

    @FXML
    private ImageView player3_building1;

    @FXML
    private ImageView player3_building2;

    @FXML
    private ImageView player3_building3;

    @FXML
    private ImageView player3_building4;

    @FXML
    private ImageView player3_building5;

    @FXML
    private ImageView player3_building6;

    @FXML
    private Group player3_territory;

    @FXML
    private ImageView player3_territory1;

    @FXML
    private ImageView player3_territory2;

    @FXML
    private ImageView player3_territory3;

    @FXML
    private ImageView player3_territory4;

    @FXML
    private ImageView player3_territory5;

    @FXML
    private ImageView player3_territory6;

    @FXML
    private Group player3_character;

    @FXML
    private ImageView player3_character1;

    @FXML
    private ImageView player3_character2;

    @FXML
    private ImageView player3_character3;

    @FXML
    private ImageView player3_character4;

    @FXML
    private ImageView player3_character5;

    @FXML
    private ImageView player3_character6;

    @FXML
    private Group player3_venture;

    @FXML
    private ImageView player3_venture1;

    @FXML
    private ImageView player3_venture2;

    @FXML
    private ImageView player3_venture3;

    @FXML
    private ImageView player3_venture4;

    @FXML
    private ImageView player3_venture5;

    @FXML
    private ImageView player3_venture6;

    @FXML
    private Tab tabPlayer4;

    @FXML
    private ImageView personalBoard4;

    @FXML
    private ImageView personalBonusTile4;

    @FXML
    private Group player4_building;

    @FXML
    private ImageView player4_building1;

    @FXML
    private ImageView player4_building2;

    @FXML
    private ImageView player4_building3;

    @FXML
    private ImageView player4_building4;

    @FXML
    private ImageView player4_building5;

    @FXML
    private ImageView player4_building6;

    @FXML
    private Group player4_territory;

    @FXML
    private ImageView player4_territory1;

    @FXML
    private ImageView player4_territory2;

    @FXML
    private ImageView player4_territory3;

    @FXML
    private ImageView player4_territory4;

    @FXML
    private ImageView player4_territory5;

    @FXML
    private ImageView player4_territory6;

    @FXML
    private Group player4_character;

    @FXML
    private ImageView player4_character1;

    @FXML
    private ImageView player4_character2;

    @FXML
    private ImageView player4_character3;

    @FXML
    private ImageView player4_character4;

    @FXML
    private ImageView player4_character5;

    @FXML
    private ImageView player4_character6;

    @FXML
    private Group player4_venture;

    @FXML
    private ImageView player4_venture1;

    @FXML
    private ImageView player4_venture2;

    @FXML
    private ImageView player4_venture3;

    @FXML
    private ImageView player4_venture4;

    @FXML
    private ImageView player4_venture5;

    @FXML
    private ImageView player4_venture6;
	
	@FXML
	private Label chatLabel;
	
	@FXML
    private ImageView imageZoomed;

    @FXML
    private ImageView territory4;

    @FXML
    private ImageView territory3;

    @FXML
    private ImageView territory2;

    @FXML
    private ImageView territory1;

    @FXML
    private ImageView character1;

    @FXML
    private ImageView character2;

    @FXML
    private ImageView character3;

    @FXML
    private ImageView character4;

    @FXML
    private ImageView building4;

    @FXML
    private ImageView building3;

    @FXML
    private ImageView building2;

    @FXML
    private ImageView building1;

    @FXML
    private ImageView venture4;

    @FXML
    private ImageView venture3;

    @FXML
    private ImageView venture2;

    @FXML
    private ImageView venture1;

    @FXML
    private ImageView councilPalace1;
    
    @FXML
    private ImageView councilPalace2;

    @FXML
    private ImageView councilPalace3;

    @FXML
    private ImageView councilPalace4;

    @FXML
    private ImageView production1;

    @FXML
    private ImageView harvest1;

    @FXML
    private ImageView market1;

    @FXML
    private ImageView market2;

    @FXML
    private ImageView market3;

    @FXML
    private ImageView market4;

    @FXML
    private ImageView production2;

    @FXML
    private ImageView harvest2;

    @FXML
    private ImageView diceBlack;

    @FXML
    private ImageView diceWhite;

    @FXML
    private ImageView diceOrange;

    @FXML
    private ImageView excommunicationTile1;

    @FXML
    private ImageView excommunicationTile2;

    @FXML
    private ImageView excommunicationTile3;
    
    @FXML
    private Button action1;

    @FXML
    private Button action2;
    
    @FXML
    private Label labelAction;
    
    @FXML
    private TextArea chatText;

    @FXML
    private ImageView neutralFamilyMember;

    @FXML
    private ImageView blackFamilyMember;

    @FXML
    private ImageView whiteFamilyMember;

    @FXML
    private ImageView orangeFamilyMember;
    
    @FXML
    private ImageView pawn1;

    @FXML
    private ImageView pawn2;

    @FXML
    private ImageView pawn3;
    
    @FXML
    private ImageView pawn4;
    
    public void setChatLabel(String textToAdd){
    	chatText.setText(chatText.getText() + "\n"+"Lorenzo: " + textToAdd);
    	chatText.positionCaret(chatText.lengthProperty().get());
    }
    
    @FXML
    void councilPalaceClk(MouseEvent event) {
    	stringToSend="1$5$1";//if one player click two times on development card, string must be cleaned
		imageZoomed.setImage(new Image("it/polimi/ingsw/gui/resources/Positions/CouncilPalace.png"));
    }
    
    @FXML
    void harvestClk(MouseEvent event) {
//6
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	stringToSend="1$6";//if one player click two times on development card, string must be cleaned
		String id = positionClicked.getId();
    	String number = id.substring(id.length()-1, id.length());
		imageZoomed.setImage(new Image("it/polimi/ingsw/gui/resources/Positions/Harvest-" + number + ".png"));
		stringToSend = stringToSend + "$" + number;
    }

    @FXML
    void marketClk(MouseEvent event) {
//8
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	stringToSend="1$8";//if one player click two times on development card, string must be cleaned
		String id = positionClicked.getId();
    	String number = id.substring(id.length()-1, id.length());
		imageZoomed.setImage(new Image("it/polimi/ingsw/gui/resources/Positions/Market-" + number + ".png"));
		stringToSend = stringToSend + "$" + number;
    }
    
    @FXML
    void productionClk(MouseEvent event) {
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	stringToSend="1$7";//if one player click two times on development card, string must be cleaned
		String id = positionClicked.getId();
    	String number = id.substring(id.length()-1, id.length());
		imageZoomed.setImage(new Image("it/polimi/ingsw/gui/resources/Positions/Production-" + number + ".png"));
		stringToSend = stringToSend + "$" + number;
    }
    
    @FXML
    void towerFloorCkd(MouseEvent event) {
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	if (positionClicked.getImage() != null){//in this position there is a card
    		imageZoomed.setImage(positionClicked.getImage());
    		String id = positionClicked.getId();
    		String firstLetter = id.substring(0, 1);
    		switch (firstLetter) {
			case "t":
				stringToSend = "1$1";
				break;
			case "c":
				stringToSend = "1$2";
				break;
			case "b":
				stringToSend = "1$3";
				break;
			case "v":
				stringToSend = "1$4";
				break;
			}
    		String number = id.substring(id.length()-1, id.length());
    		stringToSend = stringToSend + "$" + number;
    		
    	}else{//there isn't a card
			Image image = new Image("it/polimi/ingsw/gui/resources/towerFloor.jpeg");
    		imageZoomed.setImage(image);
    	}
    }
    
    @FXML
    void passTurnCkd(MouseEvent event) throws RemoteException {
    	if (isRmiClient)
    		GuiRmiView.getCallback().answer("5");
    	else
    		GuiSocketView.getCallback().setToSend("5");
    	
    	setChatLabel("You have passed the turn.");
    	action1.setDisable(true);
    	action2.setDisable(true);
    }
    
    @FXML
    void familyMemberCkd(MouseEvent event) throws RemoteException {
    	/*String id = event.getPickResult().getIntersectedNode().getId();
    	System.out.println(id);
    	switch (id) {
		case "neutralFamilyMember":
			GuiRmiView.getCallback().answer("1");
			break;
		
		case "whiteFamilyMember":
			GuiRmiView.getCallback().answer("2");
			break;
			
		case "blackFamilyMember":
			GuiRmiView.getCallback().answer("3");
			break;
			
		case "orangeFamilyMember":
			GuiRmiView.getCallback().answer("4");
			break;
			
		default:
			System.out.println("NON HO TROVATO IL FAMILY MEMBER");
		}*/
    }

    @FXML
    void placeFamilyMemberCkd(MouseEvent event) throws RemoteException {//event fired when Place Family Member button
    	if (stringToSend == null){
    		setChatLabel("You haven't choose a position!!!");
    	}
    	else{
    		if (isRmiClient)
    			GuiRmiView.getCallback().answer(stringToSend);
    		else
    			GuiSocketView.getCallback().setToSend(stringToSend);
    		
    		stringToSend = null;
    	}
    }
    
    public void disableButtons(boolean bool){
    	action1.setDisable(bool);
    	action2.setDisable(bool);
    }
  
    @FXML
    void enterPressed(KeyEvent event) throws RemoteException {
    	if (event.getCode() == KeyCode.ENTER)  {
            sendFunction();
       }
    }
    
    @FXML
    void sendCkd(MouseEvent event) throws RemoteException{
    	sendFunction();
    }
    
	private void sendFunction() throws RemoteException {
		String text = answerChat.getText();
		chatText.setText(chatText.getText() + "\n"+"Michele: " + text);//adding input of user in chat
		answerChat.setText("");//clear answer field
		if (isRmiClient)
			GuiRmiView.getCallback().answer(text);
		else
			GuiSocketView.getCallback().setToSend(text);
		disableButtons(false);
	}
	
	public void setCards(ArrayList<DevelopmentCardProxy> cards){//every turn set the cards of minimodel to right path
		BoardProxy board = game.getBoardProxy();
		for(int i = 0; i < 16; i++){
			int toowerType = i/4;
			int numberOfFloor = i%4;
			String imagePath = cards.get(i).getImagePath();
			board.getTowerProxyByInt(toowerType).getTowerFloorProxies().get(numberOfFloor).getDevelopmentCardProxy().setImageProperty(imagePath);
		}
	}
	
	public void setImages(){
		BoardProxy board = game.getBoardProxy();
		for(OrderPawn orderPawn : board.getRoundOrderProxy().getOrderPawns()){
			orderPawn.setImageProperty();
		}
		for(TowerProxy towerProxy : board.getTowerProxies()){
			for(TowerFloorProxy towerFloorProxy : towerProxy.getTowerFloorProxies()){
				towerFloorProxy.setImageProperty();
				towerFloorProxy.getDevelopmentCardProxy().setImageProperty();
			}
		}
		board.getMarketProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(0).setImageProperty();
		board.getMarketProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(0).setImageProperty();
		board.getMarketProxy().getPositionProxies().get(2).getFamilyMemberProxies().get(0).setImageProperty();
		board.getMarketProxy().getPositionProxies().get(3).getFamilyMemberProxies().get(0).setImageProperty();
		
		board.getProductionProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(0).setImageProperty();
		board.getProductionProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(0).setImageProperty();
		//board.getProductionProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(1).setImageProperty();
		//board.getProductionProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(2).setImageProperty();
		//board.getProductionProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(3).setImageProperty();
				
		board.getHarvestProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(0).setImageProperty();
		board.getHarvestProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(0).setImageProperty();
		//board.getHarvestProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(1).setImageProperty();
		//board.getHarvestProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(2).setImageProperty();
		//board.getHarvestProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(3).setImageProperty();
		
		board.getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(0).setImageProperty();
		board.getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(1).setImageProperty();
		board.getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(2).setImageProperty();
		board.getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(3).setImageProperty();
		
		for(PlayerProxy playerProxy : game.getPlayerProxies()){
			PersonalBoardProxy personalBoardProxy = playerProxy.getPersonalBoardProxy();
			for(CardContainerProxy cardContainerProxy : personalBoardProxy.getCardContainers()){
				for(DevelopmentCardProxy developmentCardProxy : cardContainerProxy.getDevelopmentCardProxies()){
					developmentCardProxy.setImageProperty();
				}
			}
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		disableButtons(true);//place family member and pass the turn are disabled.
		
		//setting name players
		ArrayList<PlayerProxy> players = game.getPlayerProxies();
		tabPlayer1.setText(players.get(0).getName());
		tabPlayer2.setText(players.get(1).getName());
		
		if(players.size() >2){
			tabPlayer3.setText(players.get(2).getName());
		}else {
			tabPlayer3.setDisable(true);
			//if there are only 2 players -> harvest 2 and production 2 are unavailable
			
			production2.setOnMouseClicked(null);
			harvest2.setOnMouseClicked(null);
		}
		
		if(players.size() >3){
			tabPlayer4.setText(players.get(3).getName());
		} else {
			tabPlayer4.setDisable(true);
			//if there are only 3 players, market 3 and market 4 are unavailable
			market3.setOnMouseClicked(null);
			market4.setOnMouseClicked(null);
		}
		
		setImages();
		excommunicationTile1.setImage(new Image(game.getBoardProxy().getExcommunicationTileProxies().get(0).getImagePath()));
		excommunicationTile2.setImage(new Image(game.getBoardProxy().getExcommunicationTileProxies().get(1).getImagePath()));
		excommunicationTile3.setImage(new Image(game.getBoardProxy().getExcommunicationTileProxies().get(2).getImagePath()));
		
		//updating resource players
		
		for (PlayerProxy playerProxy : players) {
			for (ResourceProxy resourceProxy : playerProxy.getPersonalBoardProxy().getResourceProxies()) {
				resourceProxy.setVal();
			}
		}
		
		coins_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.coins).getVal());
		wood_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.wood).getVal());
		servants_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.servants).getVal());
		stones_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.stones).getVal());
		faithPoints_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.faithPoints).getVal());
		victoryPoints_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.victoryPoints).getVal());
		militaryPoints_player1.textProperty().bind(players.get(0).getPersonalBoardProxy().getResource(ResourceType.militaryPoints).getVal());
		

		coins_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.coins).getVal());
		wood_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.wood).getVal());
		servants_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.servants).getVal());
		stones_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.stones).getVal());
		faithPoints_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.faithPoints).getVal());
		victoryPoints_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.victoryPoints).getVal());
		militaryPoints_player2.textProperty().bind(players.get(1).getPersonalBoardProxy().getResource(ResourceType.militaryPoints).getVal());
		
		if(players.size() > 2){
			coins_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.coins).getVal());
			wood_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.wood).getVal());
			servants_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.servants).getVal());
			stones_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.stones).getVal());
			faithPoints_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.faithPoints).getVal());
			victoryPoints_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.victoryPoints).getVal());
			militaryPoints_player3.textProperty().bind(players.get(2).getPersonalBoardProxy().getResource(ResourceType.militaryPoints).getVal());
		}
		
		if(players.size() > 3){
			coins_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.coins).getVal());
			wood_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.wood).getVal());
			servants_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.servants).getVal());
			stones_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.stones).getVal());
			faithPoints_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.faithPoints).getVal());
			victoryPoints_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.victoryPoints).getVal());
			militaryPoints_player4.textProperty().bind(players.get(3).getPersonalBoardProxy().getResource(ResourceType.militaryPoints).getVal());
		}
		
		
		//setting cards binding
		
		TowerProxy towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.territory);
		//binding between towerfloor position for family member and mini model
		territoryfloor1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getImageProperty());
		territoryfloor2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getImageProperty());
		territoryfloor3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getImageProperty());
		territoryfloor4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getImageProperty());
		//binding between towerfloor card and mini model
		territory1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		territory2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		territory3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		territory4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.building);
		buildingfloor1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getImageProperty());
		buildingfloor2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getImageProperty());
		buildingfloor3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getImageProperty());
		buildingfloor4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getImageProperty());
		//binding between towerfloor card and mini model
		building1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		building2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		building3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		building4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.character);
		characterfloor1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getImageProperty());
		characterfloor2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getImageProperty());
		characterfloor3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getImageProperty());
		characterfloor4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getImageProperty());
		
		character1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		character2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		character3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		character4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.venture);
		venturefloor1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getImageProperty());
		venturefloor2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getImageProperty());
		venturefloor3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getImageProperty());
		venturefloor4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getImageProperty());
		
		venture1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		venture2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		venture3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		venture4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		ArrayList<PositionProxy> marketPositionProxies = game.getBoardProxy().getMarketProxy().getPositionProxies();
		market1.imageProperty().bind(marketPositionProxies.get(0).getFamilyMemberProxies().get(0).getImageProperty());
		market2.imageProperty().bind(marketPositionProxies.get(1).getFamilyMemberProxies().get(0).getImageProperty());
		market3.imageProperty().bind(marketPositionProxies.get(2).getFamilyMemberProxies().get(0).getImageProperty());
		market4.imageProperty().bind(marketPositionProxies.get(3).getFamilyMemberProxies().get(0).getImageProperty());
		
		ArrayList<PositionProxy> productionPositionProxies = game.getBoardProxy().getProductionProxy().getPositionProxies();
		production1.imageProperty().bind(productionPositionProxies.get(0).getFamilyMemberProxies().get(0).getImageProperty());
		production2.imageProperty().bind(productionPositionProxies.get(1).getFamilyMemberProxies().get(0).getImageProperty());
		//production2.imageProperty().bind(productionPositionProxies.get(1).getFamilyMemberProxies().get(1).getImageProperty());
		//production2.imageProperty().bind(productionPositionProxies.get(1).getFamilyMemberProxies().get(2).getImageProperty());
		//production2.imageProperty().bind(productionPositionProxies.get(1).getFamilyMemberProxies().get(3).getImageProperty());
		
		ArrayList<PositionProxy> harvestPositionProxies = game.getBoardProxy().getHarvestProxy().getPositionProxies();
		harvest1.imageProperty().bind(harvestPositionProxies.get(0).getFamilyMemberProxies().get(0).getImageProperty());
		harvest2.imageProperty().bind(harvestPositionProxies.get(1).getFamilyMemberProxies().get(0).getImageProperty());
		//harvest2.imageProperty().bind(harvestPositionProxies.get(1).getFamilyMemberProxies().get(1).getImageProperty());
		//harvest2.imageProperty().bind(harvestPositionProxies.get(1).getFamilyMemberProxies().get(2).getImageProperty());
		//harvest2.imageProperty().bind(harvestPositionProxies.get(1).getFamilyMemberProxies().get(3).getImageProperty());
		
		
		//council palace has only 1 position with 4 family members
		ArrayList<FamilyMemberProxy> councilPalaceMembers = game.getBoardProxy().getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies();
		councilPalace1.imageProperty().bind(councilPalaceMembers.get(0).getImageProperty());
		councilPalace2.imageProperty().bind(councilPalaceMembers.get(1).getImageProperty());
		councilPalace3.imageProperty().bind(councilPalaceMembers.get(2).getImageProperty());
		councilPalace4.imageProperty().bind(councilPalaceMembers.get(3).getImageProperty());
		
		
		CardContainerProxy container = game.getPlayerProxies().get(0).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.territory);
		player1_territory1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player1_territory2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player1_territory3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player1_territory4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player1_territory5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player1_territory6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		container = game.getPlayerProxies().get(0).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.building);
		player1_building1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player1_building2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player1_building3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player1_building4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player1_building5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player1_building6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		container = game.getPlayerProxies().get(0).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.character);
		player1_character1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player1_character2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player1_character3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player1_character4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player1_character5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player1_character6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		container = game.getPlayerProxies().get(0).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.venture);
		player1_venture1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player1_venture2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player1_venture3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player1_venture4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player1_venture5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player1_venture6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		
		
		container = game.getPlayerProxies().get(1).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.territory);
		player2_territory1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player2_territory2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player2_territory3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player2_territory4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player2_territory5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player2_territory6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		container = game.getPlayerProxies().get(1).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.building);
		player2_building1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player2_building2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player2_building3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player2_building4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player2_building5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player2_building6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		container = game.getPlayerProxies().get(1).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.character);
		player2_character1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player2_character2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player2_character3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player2_character4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player2_character5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player2_character6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		container = game.getPlayerProxies().get(1).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.venture);
		player2_venture1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
		player2_venture2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
		player2_venture3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
		player2_venture4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
		player2_venture5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
		player2_venture6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		
		
		if(players.size() > 2){
			container = game.getPlayerProxies().get(2).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.territory);
			player3_territory1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player3_territory2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player3_territory3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player3_territory4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player3_territory5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player3_territory6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
			
			container = game.getPlayerProxies().get(2).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.building);
			player3_building1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player3_building2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player3_building3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player3_building4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player3_building5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player3_building6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
			
			container = game.getPlayerProxies().get(2).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.character);
			player3_character1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player3_character2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player3_character3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player3_character4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player3_character5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player3_character6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
			
			container = game.getPlayerProxies().get(2).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.venture);
			player3_venture1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player3_venture2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player3_venture3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player3_venture4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player3_venture5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player3_venture6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		}
		
		if(players.size() > 3){
			container = game.getPlayerProxies().get(3).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.territory);
			player4_territory1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player4_territory2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player4_territory3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player4_territory4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player4_territory5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player4_territory6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
			
			container = game.getPlayerProxies().get(3).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.building);
			player4_building1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player4_building2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player4_building3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player4_building4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player4_building5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player4_building6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
			
			container = game.getPlayerProxies().get(3).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.character);
			player4_character1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player4_character2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player4_character3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player4_character4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player4_character5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player4_character6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
			
			container = game.getPlayerProxies().get(3).getPersonalBoardProxy().getCardContainer(DevelopmentCardType.venture);
			player4_venture1.imageProperty().bind(container.getDevelopmentCardProxies().get(0).getImageProperty());
			player4_venture2.imageProperty().bind(container.getDevelopmentCardProxies().get(1).getImageProperty());
			player4_venture3.imageProperty().bind(container.getDevelopmentCardProxies().get(2).getImageProperty());
			player4_venture4.imageProperty().bind(container.getDevelopmentCardProxies().get(3).getImageProperty());
			player4_venture5.imageProperty().bind(container.getDevelopmentCardProxies().get(4).getImageProperty());
			player4_venture6.imageProperty().bind(container.getDevelopmentCardProxies().get(5).getImageProperty());
		}
	}

	public void roundBegins(GameProxy game) {
		
		setChatLabel("New round!");
		
		//clearing positions on market
		for (PositionProxy position : this.game.getBoardProxy().getMarketProxy().getPositionProxies()) {
			for (FamilyMemberProxy familyMemberProxy : position.getFamilyMemberProxies()) {
				familyMemberProxy.setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
			}
		}
		
		//clearing family members in council palace
		for (FamilyMemberProxy familyMemberProxy : this.game.getBoardProxy().getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies()) {
				familyMemberProxy.setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		}
		

		this.game.getBoardProxy().getHarvestProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(0).setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		this.game.getBoardProxy().getHarvestProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(0).setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		
		this.game.getBoardProxy().getProductionProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(0).setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		this.game.getBoardProxy().getProductionProxy().getPositionProxies().get(1).getFamilyMemberProxies().get(0).setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		
		
		
		//clearing positions on board
		for (TowerProxy tower : this.game.getBoardProxy().getTowerProxies()) {
			for (TowerFloorProxy towerFloorProxy : tower.getTowerFloorProxies()) {
				towerFloorProxy.setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
			}
		}

		
		PlayerProxy player = game.getPlayerProxies().get(0); //current player
		for(FamilyMemberProxy familyMemberProxy : player.getFamilyMemberProxies()){
			familyMemberProxy.setImageProperty();
		}

		player.getFamilyMemberProxy(DiceColour.Neutral).getImagePath();

		showDice(game.getDiceProxies());
		
		neutralFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.Neutral).getImageProperty());
		blackFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.Black).getImageProperty());
		whiteFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.White).getImageProperty());
		orangeFamilyMember.imageProperty().bind(player.getFamilyMemberProxy(DiceColour.Orange).getImageProperty());
		
		ImageView[] pawns = new ImageView[4];
		pawns[0] = pawn1;
		pawns[1] = pawn2;
		pawns[2] = pawn3;
		pawns[3] = pawn4;
		RoundOrderProxy roundOrderProxy = game.getBoardProxy().getRoundOrderProxy();
		for (int i = 0; i < roundOrderProxy.getOrderPawns().size(); i++){
			pawns[i].setImage(new Image(roundOrderProxy.getOrderPawns().get(i).getImagePath()));
		}
	}
	
	public void updateTowerFloor(TowerFloorProxy newTowerFloorProxy, DevelopmentCardProxy developmentCardTaken){
		DevelopmentCardType towerType = newTowerFloorProxy.getTowerType();
		int numberOfTowerFloor = newTowerFloorProxy.getNumberOfPosition();
		TowerFloorProxy towerFloorProxy = game.getBoardProxy().getTowerProxy(towerType).getTowerFloorProxy(numberOfTowerFloor);
		
		//setting to null the image of card on towerFloor where player setted.
		towerFloorProxy.getDevelopmentCardProxy().setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		
		//setting the image of family member on tower floor occupied
		towerFloorProxy.setImageProperty(newTowerFloorProxy.getFamilyMemberProxy().getImagePath());
		
		Color colorCurrentPlayer = newTowerFloorProxy.getFamilyMemberProxy().getColor();
		ArrayList<DevelopmentCardProxy> cards = game.getPlayerProxy(colorCurrentPlayer).getPersonalBoardProxy().getCardContainer(towerType).getDevelopmentCardProxies();
		for (DevelopmentCardProxy developmentCardProxy : cards) {
			if("it/polimi/ingsw/gui/resources/blank.png".equals(developmentCardProxy.getImagePath())){//find first position to place card in personal board
				try{
				developmentCardProxy.setImagePath(developmentCardTaken.getImagePath());
				developmentCardProxy.setImageProperty(developmentCardTaken.getImagePath());
				}catch (NullPointerException e) { } //here is thrown an exception without any logic
				break;
			}
		}
		action1.setDisable(true); //after client has placed a family member, he can't place another one
	}

	public void updatePosition(PositionProxy positionProxy) {
		int numberOfPosition = positionProxy.getNumberOfPosition();
		String imagePath = positionProxy.getFamilyMemberProxies().get(0).getImagePath();
		
		if (positionProxy.getZoneProxy() instanceof CouncilPalaceProxy){
			game.getBoardProxy().getCouncilPalaceProxy().getPositionProxies().get(0).getFamilyMemberProxies().get(numberOfPosition).setImageProperty(imagePath);
		}else if(positionProxy.getZoneProxy() instanceof MarketProxy){
			game.getBoardProxy().getMarketProxy().getPositionProxies().get(numberOfPosition).getFamilyMemberProxies().get(0).setImageProperty(imagePath);
		}else if(positionProxy.getZoneProxy() instanceof ProductionProxy){
			game.getBoardProxy().getProductionProxy().getPositionProxies().get(numberOfPosition).getFamilyMemberProxies().get(0).setImageProperty(imagePath);
		}else if(positionProxy.getZoneProxy() instanceof HarvestProxy){
			game.getBoardProxy().getHarvestProxy().getPositionProxies().get(numberOfPosition).getFamilyMemberProxies().get(0).setImageProperty(imagePath);
		}
		action1.setDisable(true); //after client has placed a family member, he can't place another one
	}

	public void setFamilyMemberProxies(ArrayList<FamilyMemberProxy> familyMemberProxies) {
		PlayerProxy player = game.getPlayerProxies().get(0); //current player
		for(int i = 0; i < player.getFamilyMemberProxies().size() ; i++){
			player.getFamilyMemberProxies().get(i).setImageProperty(familyMemberProxies.get(i).getImagePath());
		}
	}		
			
	public void showDice(ArrayList<DiceProxy> dices) {
		for (DiceProxy dice : dices) {
			String colorFirstLetter = dice.getImagePath().substring(36, 37);
			switch (colorFirstLetter) {
			case "O":
				diceOrange.setImage(new Image(dice.getImagePath()));
				break;

			case "W":
				diceWhite.setImage(new Image(dice.getImagePath()));
				break;

			case "B":
				diceBlack.setImage(new Image(dice.getImagePath()));
				break;

			default:
				break;
			}
		}
	}

	public void updatePlayerResources(Color playerColor, ArrayList<ResourceProxy> resources) {
		ArrayList<ResourceProxy> res = game.getPlayerProxy(playerColor).getPersonalBoardProxy().getResourceProxies();
		for (ResourceProxy resource : res) {
			for (ResourceProxy resourceProxy : resources) {
				if(resource.getType().equals(resourceProxy.getType())){
					resource.setVal(resourceProxy.getValue());
				}
			}
		}
	}
}