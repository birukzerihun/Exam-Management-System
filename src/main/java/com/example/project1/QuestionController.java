package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class QuestionController {
    @FXML
    private Button btnback;

    @FXML
    private Button btninsert;

    @FXML
    private Button btndelete;

    @FXML
    private TextField op1;

    @FXML
    private TextField op2;

    @FXML
    private TextField op3;

    @FXML
    private TextField op4;

    @FXML
    private TextField tfqua;

    @FXML
    private TextField tfans;

    @FXML
    private TextField tfidQ;

    @FXML
    void actionhandlebutton(ActionEvent event) {
   if (event.getSource()==btninsert){
       insertRecord();
   }
   if (event.getSource()==btndelete){
       deleteButton();
   }
    }
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/project1", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    private void insertRecord(){
        String query = "INSERT INTO questions VALUES ('"+ tfidQ.getText()  +"','"+ tfqua.getText() +"','" + op1.getText() + "','" + op2.getText() + "','" + op3.getText() + "','"
                + op4.getText() + "','" + tfans.getText() + "')";
        executeQuery(query);
        // showBooks();
    }
    private void deleteButton(){
        String query = "DELETE FROM questions WHERE Qid =" + tfidQ.getText() + "";
        executeQuery(query);
        // showBooks();
    }
    private void executeQuery(String query) {
        Connection conn = getConnection();
        Statement st;
        try{
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}