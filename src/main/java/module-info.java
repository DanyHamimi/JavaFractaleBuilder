module com.example.prjt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prjt to javafx.fxml;
    exports com.example.prjt;
}