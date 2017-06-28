package it.polimi.ingsw.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import it.polimi.ingsw.BOARD.TowerFloor;
import it.polimi.ingsw.CARD.DevelopmentCard;
import it.polimi.ingsw.CARD.DevelopmentCardType;
import it.polimi.ingsw.GC_15.Game;
import it.polimi.ingsw.GC_15.Player;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class GuiController implements Initializable {
	
	private GuiSocketView guiSocketView;
	private FXMLLoader loader;
	ObjectProperty<Image> excommunicationTile1Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> excommunicationTile2Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> excommunicationTile3Property = new SimpleObjectProperty<>();
	
	ObjectProperty<Image> territory1Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> territory2Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> territory3Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> territory4Property = new SimpleObjectProperty<>();
	
	ObjectProperty<Image> building1Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> building2Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> building3Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> building4Property = new SimpleObjectProperty<>();
	
	ObjectProperty<Image> character1Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> character2Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> character3Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> character4Property = new SimpleObjectProperty<>();
	
	ObjectProperty<Image> venture1Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> venture2Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> venture3Property = new SimpleObjectProperty<>();
	ObjectProperty<Image> venture4Property = new SimpleObjectProperty<>();
	
	@FXML
    private Tab tabPlayer1;

	@FXML
    private Tab tabPlayer2;
	
	@FXML
    private Tab tabPlayer3;
	
	@FXML
    private Tab tabPlayer4;
	
	@FXML
	private Label getDescription;
	
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
    private Ellipse councilPalace;

    @FXML
    private Circle production1;

    @FXML
    private Circle harvest1;

    @FXML
    private Circle market1;

    @FXML
    private Circle market2;

    @FXML
    private Circle market3;

    @FXML
    private Circle market4;

    @FXML
    private Rectangle production2;

    @FXML
    private Rectangle harvest2;

    @FXML
    private Rectangle dice1;

    @FXML
    private Rectangle dice2;

    @FXML
    private Rectangle dice3;

    @FXML
    private ImageView excommunicationTile1;

    @FXML
    private ImageView excommunicationTile2;

    @FXML
    private ImageView excommunicationTile3;
    

    @FXML
    private Button action2;

    @FXML
    private Button action4;

    @FXML
    private Button action1;
    
    @FXML
    private Button action5;

    @FXML
    private Label labelAction;

    @FXML
    private Button action3;

    public void setLoader(FXMLLoader loader){
    	this.loader = loader;
    }
    
    @FXML
    void towerFloorCkd(MouseEvent event) {
    	System.out.println(event.getPickResult().getIntersectedNode().getId());
    	
    	ImageView positionClicked = (ImageView) event.getPickResult().getIntersectedNode();
    	if (positionClicked.getImage() != null){//in this position there is a card
    		imageZoomed.setImage(positionClicked.getImage());
    		
    		getDescription.setText("TODO");
    	}else{//there isn't a card
			Image image = new Image("it/polimi/ingsw/gui/resources/towerFloor.jpeg");
    		imageZoomed.setImage(image);
    		
    		getDescription.setText("In this position there isn't a card.");
    	}
    }
    
    @FXML
    void actionCkd(MouseEvent event) {
    	
    }

	public void setMain(GuiSocketView guiSocketView) {
		this.guiSocketView = guiSocketView;
	}
	
	public void setCards(ArrayList<DevelopmentCard> cards){
		territory1Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(0).getName() + ".png"));
		territory2Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(1).getName() + ".png"));
		territory3Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(2).getName() + ".png"));
		territory4Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(3).getName() + ".png"));
		
		building1Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(4).getName() + ".png"));
		building2Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(5).getName() + ".png"));
		building3Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(6).getName() + ".png"));
		building4Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(7).getName() + ".png"));
		
		character1Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(8).getName() + ".png"));
		character2Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(9).getName() + ".png"));
		character3Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(10).getName() + ".png"));
		character4Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(11).getName() + ".png"));
		
		venture1Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(12).getName() + ".png"));
		venture2Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(13).getName() + ".png"));
		venture3Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(14).getName() + ".png"));
		venture4Property.set(new Image("it/polimi/ingsw/gui/resources/developmentCards/" + cards.get(15).getName() + ".png"));
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Game game = GuiRmiView.getGame();
		
		excommunicationTile1.imageProperty().bind(excommunicationTile1Property);
		excommunicationTile2.imageProperty().bind(excommunicationTile2Property);
		excommunicationTile3.imageProperty().bind(excommunicationTile3Property);
		
		//setting name players
		Player[] players = game.getPlayers();
		tabPlayer1.setText(players[0].getName());
		tabPlayer2.setText(players[1].getName());
		if(players.length >2){
			tabPlayer3.setText(players[2].getName());
		}else tabPlayer3.setDisable(true);;
		
		if(players.length >3){
			tabPlayer4.setText(players[3].getName());
		}else tabPlayer4.setDisable(true);
		
		//setting cards binding
		territory1.imageProperty().bind(territory1Property);
		territory2.imageProperty().bind(territory2Property);
		territory3.imageProperty().bind(territory3Property);
		territory4.imageProperty().bind(territory4Property);
		
		building1.imageProperty().bind(building1Property);
		building2.imageProperty().bind(building2Property);
		building3.imageProperty().bind(building3Property);
		building4.imageProperty().bind(building4Property);
		
		character1.imageProperty().bind(character1Property);
		character2.imageProperty().bind(character2Property);
		character3.imageProperty().bind(character3Property);
		character4.imageProperty().bind(character4Property);
		
		venture1.imageProperty().bind(venture1Property);
		venture2.imageProperty().bind(venture2Property);
		venture3.imageProperty().bind(venture3Property);
		venture4.imageProperty().bind(venture4Property);
	}
}