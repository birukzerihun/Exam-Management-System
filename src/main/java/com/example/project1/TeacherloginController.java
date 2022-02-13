package com.example.project1;

import com.mysql.cj.xdevapi.PreparableStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.sql.*;
import java.util.logging.Logger;

import static java.lang.Class.forName;


public class TeacherloginController  {

    @FXML
    private Button btnlogin;

    @FXML
    private PasswordField tfpassword;

    @FXML
    private TextField tfusername;

    Connection con;
   PreparedStatement st;
   ResultSet rs;



    @FXML
    void loginhandle(ActionEvent event) throws SQLException, IOException {

        String uname= tfusername.getText();
        String pass= tfpassword.getText();

        if(uname.equals("") || pass.equals("")){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Login Failed");
            alert.setHeaderText("Please enter your your username and password");
            alert.show();
        }
        else{
            FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("question.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 450);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.show();

            Connection con = getConnection();

            st=con.prepareStatement("select * from teachers where username=? and password=?");
            st.setString(1,uname);
            st.setString(2,pass);

            rs= st.executeQuery();
            if(rs.next()){
              // JOptionPane.showMessageDialog("successfully login");

        }
            else{
              // JOptionPane.showMessageDialog("login failed");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Login Faild");
                alert.setHeaderText("Incorect username or password");
                alert.show();
                tfusername.setText("");
                tfpassword.setText("");
                tfusername.requestFocus();
            }

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
}