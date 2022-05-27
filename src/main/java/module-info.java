module com.example.pt2022_30423_tat_teodora_assigment3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.pt2022_30423_tat_teodora_assigment3 to javafx.fxml;
    exports com.example.pt2022_30423_tat_teodora_assigment3;

    opens start to javafx.fxml;
    exports start;

    opens Presentation to javafx.fxml;
    exports Presentation;

    opens Model to javafx.fxml;
    exports Model;


}