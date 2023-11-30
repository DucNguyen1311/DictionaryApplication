module com.example.dictionaryapplication {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.dictionaryapplication to javafx.fxml;
    exports com.example.dictionaryapplication;
}