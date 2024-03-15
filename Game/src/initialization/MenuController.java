package initialization;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.*;

import static main.Main.exit;
import static main.Main.startGame;

public class MenuController {
    @FXML
    public Button newGameButton;
    @FXML
    public Button loadGameButton;
    @FXML
    public Button exitButton;

    public void initialize() {
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> startGame("new"));
        loadGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> startGame("load"));
        exitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> exit());
    }

}
