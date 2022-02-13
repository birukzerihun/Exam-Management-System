package com.example.project1;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static sun.swing.SwingUtilities2.submit;

public class StudentTryController implements Initializable {

    Dbc db = new Dbc();
    Connection con = db.getConnection();
    int i = 2;


    @FXML
    private Button btnnext;

    @FXML
    private Label label;

    @FXML
    private RadioButton rb1;

    @FXML
    private RadioButton rb2;

    @FXML
    private RadioButton rb3;

    @FXML
    private RadioButton rb4;

    @FXML
    private Label Tlabel;

    @FXML



    void nexthandler(ActionEvent event) {


        Tlabel.setText("00,00,00");


        if (!rb1.isSelected() && !rb2.isSelected() && !rb3.isSelected() && !rb4.isSelected()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Incorect Format");
            alert.setHeaderText("Please enter your answer");
            alert.show();
        } else {
            ResultSet rs;
            try {

                String query = "select * from questions where Qid = '" + i + "" + "'";
                rs = con.createStatement().executeQuery(query);
                while (rs.next()) {
                    label.setText(rs.getString(2));
                    rb1.setText(rs.getString(3));
                    rb2.setText(rs.getString(4));
                    rb3.setText(rs.getString(5));
                    rb4.setText(rs.getString(6));
                }
                i++;

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        ResultSet rs;
        try {
            String query = "select * from questions where Qid = '" + 1 + "" + "'";
            rs = con.createStatement().executeQuery(query);
            while (rs.next()) {
                label.setText(rs.getString(2));
                rb1.setText(rs.getString(3));
                rb2.setText(rs.getString(4));
                rb3.setText(rs.getString(5));
                rb4.setText(rs.getString(6));
            }
            //setScore(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public void timer(){
//        Timer timer =new Timer();
//        TimerTask task=new TimerTask() {
//            int counter=10;
//            @Override
//            public void run() {
//                if(counter>0) {
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Time is started");
//                    alert.setHeaderText("you should start the exam");
//                    alert.show();
//                    counter--;
//                    Tlabel.setText(counter+"");
//
//                }
//                else{
//                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                    alert.setTitle("Time is Up");
//                    alert.setHeaderText("you have finished your time ");
//                    alert.show();
//                    timer.cancel();
//
//
//                }
//            }
//        };
//        //  timer.schedule(task,3000);
//        timer.scheduleAtFixedRate(task,0,1000);
//
//    }

    private long min, sec, hr, totalSec = 0; //250 4 min 10 sec
    private Timer timer;

    private void setTimer() {
    int    totalSec = 10;
        this.timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("After 1 sec...");
                      //  convertTime();
                        if (totalSec >= 0) {
                            timer.cancel();
                            Tlabel.setText("00:00:00");

                            // saveing data to database
                            submit(null);
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Unauthorised User");
                            alert.setHeaderText("Incorect  Username or Password");
                            alert.show();
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 1000);
    }

}
