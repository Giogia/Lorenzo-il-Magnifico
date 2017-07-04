package it.polimi.ingsw.gui;

import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Dice;
import it.polimi.ingsw.GC_15.DiceColour;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.PersonalBoard;
import it.polimi.ingsw.GC_15.Player;
import it.polimi.ingsw.GC_15.Player.Color;
import it.polimi.ingsw.minigame.BoardProxy;
import it.polimi.ingsw.minigame.DevelopmentCardProxy;
import it.polimi.ingsw.minigame.DiceProxy;
import it.polimi.ingsw.minigame.ExcommunicationTileProxy;
import it.polimi.ingsw.minigame.FamilyMemberProxy;
import it.polimi.ingsw.minigame.GameProxy;
import it.polimi.ingsw.minigame.OrderPawn;
import it.polimi.ingsw.minigame.PlayerProxy;
import it.polimi.ingsw.minigame.PositionProxy;
import it.polimi.ingsw.minigame.RoundOrderProxy;
import it.polimi.ingsw.minigame.TowerFloorProxy;
import it.polimi.ingsw.minigame.TowerProxy;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class GuiController implements Initializable {
	private GuiSocketView guiSocketView;
	private FXMLLoader loader;
	private GameProxy game;
	private String stringToSend;
	
	public GuiController(GameProxy game) {
		this.game = game;
	}
	
    @FXML
    private TextField answerChat;
	
	@FXML
    private Tab tabPlayer1;

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
    private Tab tabPlayer2;
	
	@FXML
    private Tab tabPlayer3;
	
	@FXML
    private Tab tabPlayer4;
	
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
    private ImageView councilPalace;

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
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
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
    //		stringToSend="";//if one player click two times on development card, string must be cleaned
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
    	GuiRmiView.getCallback().answer("5");
    	action1.setDisable(true);
    	action2.setDisable(true);
    }
    
    @FXML
    void familyMemberCkd(MouseEvent event) throws RemoteException {
    	String id = event.getPickResult().getIntersectedNode().getId();
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
		}
    }

    @FXML
    void placeFamilyMemberCkd(MouseEvent event) throws RemoteException {//event fired when Place Family Member button
    	if (stringToSend == null){
    		setChatLabel("You haven't choose a position!!!");
    	}
    	else{
    		GuiRmiView.getCallback().answer(stringToSend);
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
		GuiRmiView.getCallback().answer(text);
		disableButtons(false);
	}

	public void setMain(GuiSocketView guiSocketView) {
		this.guiSocketView = guiSocketView;
	}
	
	public void updateTowerFloor(TowerFloorProxy newTowerFloorProxy){
		DevelopmentCardType towerType = newTowerFloorProxy.getTowerType();
		int numberOfTowerFloor = newTowerFloorProxy.getNumberOfPosition();
		TowerFloorProxy towerFloorProxy = game.getBoardProxy().getTowerProxy(towerType).getTowerFloorProxy(numberOfTowerFloor);
		
		//setting to null the image of card on towerFloor where player setted.
		towerFloorProxy.getDevelopmentCardProxy().setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		
		//setting the image of family member on tower floor
		//towerFloorProxy.getFamilyMemberProxy().setImageProperty(newTowerFloorProxy.getFamilyMemberProxy().getImagePath());
		
		for (FamilyMemberProxy familyMemberProxy : game.getPlayerProxies().get(0).getFamilyMemberProxies()) {
			familyMemberProxy.setImageProperty("it/polimi/ingsw/gui/resources/blank.png");
		}
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
				towerFloorProxy.getDevelopmentCardProxy().setImageProperty();
			}
		}
		/*for(ExcommunicationTileProxy excommunicationTileProxy : board.getExcommunicationTileProxies()){
			excommunicationTileProxy.setImageProperty();
		}*/
		/*for(PlayerProxy playerProxy : game.getPlayerProxies()){
			PersonalBoardProxy personalBoardProxy = playerProxy.getPersonalBoardProxy();
			for(CardContainerProxy cardContainerProxy : personalBoardProxy.getCardContainers()){
				for(DevelopmentCardProxy developmentCardProxy : cardContainerProxy.getDevelopmentCardProxies()){
					developmentCardProxy.setImageProperty();
				}
			}
			for(LeaderCardProxy leaderCardProxy : personalBoardProxy.getLeaderCardProxies()){
				leaderCardProxy.setImageProperty();
			}
			for(LeaderCardProxy leaderCardProxy : playerProxy.getLeaderCardInHandProxies()){
				leaderCardProxy.setImageProperty();
			}
		}*/
		
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
		}else tabPlayer3.setDisable(true);
		
		if(players.size() >3){
			tabPlayer4.setText(players.get(3).getName());
		}else tabPlayer4.setDisable(true);
		
		//setting cards binding
		TowerProxy towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.territory);
		String imagePath = towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImagePath();
		try{
			towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().setImageProperty(imagePath);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		setImages();
		
		territory1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		territory2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		territory3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		territory4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.building);
		building1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		building2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		building3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		building4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.character);
		character1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		character2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		character3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		character4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());
		
		towerProxy = game.getBoardProxy().getTowerProxy(DevelopmentCardType.venture);
		venture1.imageProperty().bind(towerProxy.getTowerFloorProxy(0).getDevelopmentCardProxy().getImageProperty());
		venture2.imageProperty().bind(towerProxy.getTowerFloorProxy(1).getDevelopmentCardProxy().getImageProperty());
		venture3.imageProperty().bind(towerProxy.getTowerFloorProxy(2).getDevelopmentCardProxy().getImageProperty());
		venture4.imageProperty().bind(towerProxy.getTowerFloorProxy(3).getDevelopmentCardProxy().getImageProperty());

	}

	public void roundBegins(GameProxy game) {
		
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

	public void updatePosition(PositionProxy positionProxy) {
		
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
}