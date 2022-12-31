package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class MainFormController {
    public AnchorPane mainFormContext;

    public void btnCustomerManagement(ActionEvent actionEvent) throws IOException {
        setUi("CustomerForm");
    }

    public void btnItemManagement(ActionEvent actionEvent) throws IOException {
        setUi("ItemForm");
    }

    private void setUi(String location) throws IOException {
        Stage stage = (Stage) mainFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
