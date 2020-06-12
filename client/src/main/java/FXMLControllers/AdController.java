package FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lab.LanguageController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class AdController {
    @FXML
    public Button adGOButton;
    public Button adCloseButton;

    @FXML
    public void initialize() {
        changeLangOfWindow();
    }

    private void changeLangOfWindow() {
        adGOButton.setText(LanguageController.loadLocale("adGOButton"));
        adCloseButton.setText(LanguageController.loadLocale("adCloseButton"));
    }

    public void adGO() throws IOException {
        Desktop.getDesktop().browse(URI.create("https://www.leon.ru/"));
        Stage stage = (Stage) adGOButton.getScene().getWindow();
        stage.close();
    }

    public void adClose() {
        Stage stage = (Stage) adCloseButton.getScene().getWindow();
        stage.close();
    }
}
