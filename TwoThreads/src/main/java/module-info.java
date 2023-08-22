module com.example.twothreads {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.twothreads to javafx.fxml;
    exports com.example.twothreads;
}