module com.example.dictionaryapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens com.example.dictionaryapplication to javafx.fxml;
    exports com.example.dictionaryapplication;
}