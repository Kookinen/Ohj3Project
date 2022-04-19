module fi.tuni.prog3.sisu {
    requires javafx.controls;
    requires com.google.gson;
    requires javafx.fxml;
    exports fi.tuni.prog3.sisu;
    opens fi.tuni.prog3.sisu to javafx.fxml;
}
