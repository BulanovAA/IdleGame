package initialization;

import characters.MainCharacter;
import draw.Drawer;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.Main;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class CheatsController {
    @FXML
    public Label currentLevelLabel;
    @FXML
    public Label currentSPointsLabel;
    @FXML
    public Label currentGoldLabel;
    @FXML
    public Button levelAddButton;
    @FXML
    public Button sPointsAddButton;
    @FXML
    public Button goldAddButton;
    @FXML
    public Button count1Button;
    @FXML
    public Button count1kButton;
    @FXML
    public Button count1mButton;
    @FXML
    public TextField countUserField;
    @FXML
    public Button applyButton;
    @FXML
    public Button cancelButton;

    private static MainCharacter mainCharacter = null;
    private static Stage stage = new Stage();

    public static void startCheats(MainController mainController) {
        try {
            URL url = new File("src/fxml/Cheats.fxml").toURI().toURL();
            FXMLLoader loader = new FXMLLoader(url);
            mainCharacter = new MainCharacter(mainController.getMainCharacter());
            CheatsController cheatsController = new CheatsController();
            loader.setController(cheatsController);
            Scene scene = new Scene(loader.load());
            stage.setTitle("Cheats");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void initialize() {
        redrawCurrent();
        countUserField.setText("1");
        countUserField.textProperty().addListener(e -> {
            if (!countUserField.getText().matches("\\d*")) {
                countUserField.setText(countUserField.getText().replaceAll("[^\\d]", ""));
            }
        });
        applyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> apply());
        cancelButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> cancel());
        count1Button.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> count1());
        count1kButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> count1k());
        count1mButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> count1m());
        levelAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> addLevel());
        sPointsAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> addSPoints());
        goldAddButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> addGold());
    }

    private void redrawCurrent() {
        currentGoldLabel.setText(Drawer.convert(mainCharacter.getGold()));
        currentLevelLabel.setText(Drawer.convert(mainCharacter.getLevel()));
        currentSPointsLabel.setText(Drawer.convert(mainCharacter.getSPoints()));
    }

    private void apply() {
        Main.mainController.setMainCharacter(mainCharacter);
        stage.close();
    }

    private void cancel() {
        stage.close();
    }

    private void count1() {
        countUserField.setText("1");
    }

    private void count1k() {
        countUserField.setText("1000");
    }

    private void count1m() {
        countUserField.setText("1000000");
    }

    private void addLevel() {
        for (int i = 0; i < Integer.parseInt(countUserField.getText()); i++) {
            mainCharacter.addExperience(mainCharacter.getLevelCost());
        }
        redrawCurrent();
    }

    private void addSPoints() {
        mainCharacter.addSPoints(Integer.parseInt(countUserField.getText()));
        redrawCurrent();
    }

    private void addGold() {
        mainCharacter.addGold(Integer.parseInt(countUserField.getText()));
        redrawCurrent();
    }
}
