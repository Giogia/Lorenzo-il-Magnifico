package it.polimi.ingsw.gui;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class GameController {
    @FXML
    private Rectangle imageZoomed;

    @FXML
    private Rectangle towerFloor4;

    @FXML
    private Rectangle towerFloor3;

    @FXML
    void towerFloorCkd(MouseEvent event) {
    	System.out.println(event.getPickResult().getIntersectedNode().getId());
    	
    }
}
