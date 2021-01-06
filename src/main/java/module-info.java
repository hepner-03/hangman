module hepner {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    opens hepner to javafx.fxml;
    exports hepner;
}