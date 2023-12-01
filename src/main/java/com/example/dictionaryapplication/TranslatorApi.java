package com.example.dictionaryapplication;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TranslatorApi {

    /**
     * Translate English text text into Vietnamese.
     *
     * @param text the text to be translated
     * @return the Vietnamese translation, or "500" if got errors
     */
    public static String translateEnToVi(String text) {
        try {
            return translate("en", "vi", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }

    /**
     * Translate Vietnamese text text into English.
     *
     * @param text the text to be translated
     * @return the English translation, or "500" if got errors
     */
    public static String translateViToEn(String text) {
        try {
            return translate("vi", "en", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "500";
    }

    /**
     * Translate text from langFrom to langTo.
     *
     * <p><a
     * href="https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application">Reference</a>
     *
     * @param langFrom the input language (2 letters (ex: 'en'))
     * @param langTo the output language (2 letters (ex: 'vi'))
     * @param text the text to be translated
     * @return the translation text in langTo
     */
    private static String translate(String langFrom, String langTo, String text)
            throws IOException {
        String urlStr =
                "https://script.google.com/macros/s/AKfycbzmOz2akZRcxkjp8aOA9AdAxiVPvHJYFsXPke8dPVoI_G4gIT7bMwjq4z-eQnnLx_UuQA/exec"
                        + "?q="
                        + URLEncoder.encode(text, StandardCharsets.UTF_8)
                        + "&target="
                        + langTo
                        + "&source="
                        + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in =
                new BufferedReader(
                        new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
    public void speak(String word) {
        String mediaUrl = "https://api.voicerss.org/?key=61a7d5f8f67646279b4c24dd81bb6576&hl=en-us&src=" + word;
        Media sound = new Media(mediaUrl);
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
}