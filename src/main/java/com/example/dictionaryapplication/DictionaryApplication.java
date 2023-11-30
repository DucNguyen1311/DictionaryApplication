package com.example.dictionaryapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class DictionaryApplication {
    public void startGame(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Game.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        try {
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/dictionaryapplication/dataGame/GameIcon.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Không cho phép kéo dãn cửa sổ
        stage.setResizable(false);
        stage.setTitle("Learning English game");
        stage.setScene(new Scene(root));
        stage.show();
    }
}