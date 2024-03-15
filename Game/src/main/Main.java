package main;

import characters.MainCharacter;
import draw.Drawer;
import initialization.MainController;
import initialization.MenuController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static Stage stage;
    private static FXMLLoader loader;
    public static Timeline timeline;
    public static Timeline attackTimeline;
    public static Timeline spawnTimeline;
    public static MainController mainController = new MainController();
    public static MenuController menuController = new MenuController();
    public static boolean attack = true;
    private static int k = 0;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) {
        try {
            Main.stage = stage;
            URL url = new File("src/fxml/Menu.fxml").toURI().toURL();
            loader = new FXMLLoader(url);
            loader.setController(menuController);
            Scene scene = new Scene(loader.load());
            stageStart(scene);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private static void centerWindow() {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((screenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((screenBounds.getHeight() - stage.getHeight()) / 2);
    }

    public static void stageStart(Scene scene) {
        stage.setTitle("Game");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        centerWindow();
    }

    public static void startGame(String s) {
        try {
            URL url = new File("src/fxml/MainGame.fxml").toURI().toURL();
            loader = new FXMLLoader(url);
            MainCharacter mainCharacter = new MainCharacter();
            if (s == "load") {
                mainCharacter = mainController.loadMaincharacter();
            }
            mainController.setMainCharacter(mainCharacter);
            startMainTimeline(mainController);
            startAttackTimeline(mainCharacter);
            startSpawnTimeline(mainCharacter);
            loader.setController(mainController);
            Scene scene = new Scene(loader.load());
            stageStart(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void exit() {
        stage.close();
    }

    public static void startMainTimeline(MainController mainController) {
        timeline = new Timeline(new KeyFrame(Duration.seconds((double) 1 / 20 / mainController.getSpeed()), event -> {
            mainController.move();
            mainController.updateBars();
            k++;
            if (k > 20) {
                mainController.updateMain();
                k = 0;
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void startAttackTimeline(MainCharacter mainCharacter) {
        attackTimeline = new Timeline(new KeyFrame((Duration.seconds((double) 1 / ((double) mainCharacter.getAttackSpeed() / 100) / mainController.getSpeed())), event -> {
            attack = true;
        }));
        attackTimeline.setCycleCount(Timeline.INDEFINITE);
//        System.out.println((double) 1 / ((double) mainCharacter.getAttackSpeed() / 100));
        attackTimeline.play();
    }

    public static void startSpawnTimeline(MainCharacter mainCharacter) {
        spawnTimeline = new Timeline(new KeyFrame((Duration.seconds(mainCharacter.getSpawnTime() / mainController.getSpeed())), event -> {
            mainController.addEnemy();
        }));
        spawnTimeline.setCycleCount(Timeline.INDEFINITE);
        spawnTimeline.play();
    }
}