package com.example.project1;

import com.mysql.cj.xdevapi.PreparableStatement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

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
    void loginhandle(ActionEvent event) throws SQLException {

        String uname= tfusername.getText();
        String pass= tfpassword.getText();

        if(uname.equals("") && pass.equals("")){
            System.out.println("incorect username or password");
        }
        else{
            Connection con = getConnection();

            st=con.prepareStatement("select * from teachers where username=? and password=?");
            st.setString(1,uname);
            st.setString(2,pass);

            rs= st.executeQuery();
            if(rs.next()){
              // JOptionPane.showMessageDialog("successfully login");
                System.out.println("login successfull");
        }
            else{
              // JOptionPane.showMessageDialog("login failed");
                System.out.println("login failed");
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