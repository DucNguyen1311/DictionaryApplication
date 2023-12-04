package com.example.dictionaryapplication;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
public class Main extends Application {
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DictionaryApplication.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



    /*
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Online Sound Example");

        Button playButton = new Button("Play Sound");

        // Đường dẫn URL của phương tiện trực tuyến
        String mediaUrl = "https://api.voicerss.org/?key=61a7d5f8f67646279b4c24dd81bb6576&hl=en-us&src=hello";
        Media sound = new Media(mediaUrl);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        playButton.setOnAction(e -> mediaPlayer.play());

        StackPane layout = new StackPane();
        layout.getChildren().add(playButton);

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
     */
}