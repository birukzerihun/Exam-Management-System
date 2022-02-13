package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class StudentController  implements Initializable {
    Dbc db = new Dbc();
    Connection con = db.getConnection();
    public int i = 2;
    public int j = 0;
    public String choice;
    @FXML
    private Button nxt;

    @FXML
    private Label score;
    @FXML
    private Label qlabel;

    @FXML
    private RadioButton rbop1;

    @FXML
    private RadioButton rbop2;

    @FXML
    private RadioButton rbop3;

    @FXML
    private RadioButton rbop4;

    @FXML
    private ToggleGroup answer;

    @FXML
    void nxtmtfd(ActionEvent event) {


       /// ene yechemerkut
        if (!rbop1.isSelected() && !rbop2.isSelected() && !rbop3.isSelected() && !rbop4.isSelected()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Incorect Format");
            alert.setHeaderText("Please enter your answer");
            alert.show();
        }
        else{


        }
            ResultSet rs;
            try {

                String query = "select * from questions where Qid = '" + i + "" + "'";
                rs = con.createStatement().executeQuery(query);
                while (rs.next()) {
                    qlabel.setText(rs.getString(2));
                    rbop1.setText(rs.getString(3));
                    rbop2.setText(rs.getString(4));
                    rbop3.setText(rs.getString(5));
                    rbop4.setText(rs.getString(6));
                }
                setScore(i);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }



        }


        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){

          //  timer();
            ResultSet rs;
            try {
                String query = "select * from questions where Qid = '" + 1 + "" + "'";
                rs = con.createStatement().executeQuery(query);
                while (rs.next()) {
                    qlabel.setText(rs.getString(2));
                    rbop1.setText(rs.getString(3));
                    rbop2.setText(rs.getString(4));
                    rbop3.setText(rs.getString(5));
                    rbop4.setText(rs.getString(6));
                }
                setScore(1);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        public void setScore ( int k){

            if (rbop1.isSelected()) {
                choice = "A";
            } else if (rbop2.isSelected()) {
                choice = "B";
            } else if (rbop3.isSelected()) {
                choice = "C";
            } else if (rbop4.isSelected()) {
                choice = "D";
            } else {
                choice = null;
            }
            ResultSet rs;
            try {
                String query = "select * from questions where Qid = '" + k + "" + "'";
                rs = con.createStatement().executeQuery(query);
                while (rs.next()) {
                    if (choice.equals(rs.getString(7))) {
                        j++;
                        score.setText(j + "");
                    }
                }

            } catch (Exception e) {

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
//      //  timer.schedule(task,3000);
//        timer.scheduleAtFixedRate(task,0,1000);
//
//    }
    }
