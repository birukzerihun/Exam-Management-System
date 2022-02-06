package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class frontController {

    @FXML
    private Button btnexit;

    @FXML
    private Button btnstud;

    @FXML
    private Button btntea;



    public void studenthandle(ActionEvent event) {
    }

    public void teacherhandle(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("question.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void exithandle(ActionEvent event) {
    }
}
