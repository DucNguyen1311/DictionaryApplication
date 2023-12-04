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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TranslatorApi {


    /**
     * Translate English text text into Vietnamese.
     *
     * @param text the text to be translated
     * @return the Vietnamese translation, or "500" if got errors
     */
    public static String translateEnToVi (String text) {
        return translate ("en", "vi", text);
    }

    /**
     * Translate Vietnamese text text into English.
     *
     * @param text the text to be translated
     * @return the English translation, or "500" if got errors
     */
    public static String translateViToEn (String text) {
        return translate ("vi", "en", text);
    }

    /**
     * Translate text from langFrom to langTo.
     *
     * <p><a
     * href="https://stackoverflow.com/questions/8147284/how-to-use-google-translate-api-in-my-java-application">Reference</a>
     *
     * @param langFrom the input language (2 letters (ex: 'en'))
     * @param langTo   the output language (2 letters (ex: 'vi'))
     * @param text     the text to be translated
     * @return the translation text in langTo
     */


    public static String translate (String langFrom, String langTo, String text) {
        try {
            String urlStr = "https://script.google.com/macros/s/AKfycbzmOz2akZRcxkjp8aOA9AdAxiVPvHJYFsXPke8dPVoI_G4gIT7bMwjq4z-eQnnLx_UuQA/exec"
                    + "?q="
                    + URLEncoder.encode (text, StandardCharsets.UTF_8)
                    + "&target="
                    + langTo
                    + "&source="
                    + langFrom;

            URL url = new URL (urlStr);
            StringBuilder response = new StringBuilder ();
            HttpURLConnection con = (HttpURLConnection) url.openConnection ();
            con.setRequestProperty ("User-Agent", "Mozilla/5.0");

            try (BufferedReader in = new BufferedReader (
                    new InputStreamReader (con.getInputStream (), StandardCharsets.UTF_8))) {

                String inputLine;
                while ((inputLine = in.readLine ()) != null) {
                    response.append (inputLine);
                }
            }

            return response.toString ();

        } catch (IOException e) {
            // Xử lý ngoại lệ IOException ở đây
            e.printStackTrace (); // Hoặc xử lý theo cách khác tùy vào yêu cầu của ứng dụng
            return "Translation failed due to an error.";
        }
    }


    public static String langFx(String lang) {
        try {
            Path path = Path.of("D:\\JAVA\\Lesson1\\untitled\\src\\lang.txt");
            List<String> inputLanguages = Files.readAllLines(path);
            for (String language : inputLanguages) {
                String[] split = language.split(":");
                if (split.length == 2 && split[1].trim().equalsIgnoreCase(lang.trim())) {
                    return split[0].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error reading from file: " + e.getMessage());
        }
        return "en-us";
    }
    private static List<String> spokenWords = new ArrayList<> ();
    public static void speak (String word, String lang) {

        String mediaUrl = "https://api.voicerss.org/?key=03aaf975468e41e789310826be12c786&hl=" + langFx (lang) + "&src=" + word;
        Media sound = new Media (mediaUrl);
        MediaPlayer mediaPlayer = new MediaPlayer (sound);
        mediaPlayer.play ();
        spokenWords.add("Audio for " + word);
    }
    public static void combineAudio() {
        // Thực hiện kết hợp các tệp âm thanh trong spokenWords
        // (Thay thế bằng cách kết hợp âm thanh theo cách phù hợp với API của bạn)
        System.out.println("Combining audio...");
        for (String audio : spokenWords) {
            System.out.println(audio);
        }
    }

    public static void speakSentence(String sentence, String lang) {
        List<String> words = Arrays.asList(sentence.split("\\s+"));

        for (String word : words) {
            speak (word, lang);
        }
    }
}



