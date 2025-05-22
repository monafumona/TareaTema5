module org.example.tareatema5 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;
    requires javafx.media;


    opens org.example.tareatema5 to javafx.fxml;
    exports org.example.tareatema5;
}