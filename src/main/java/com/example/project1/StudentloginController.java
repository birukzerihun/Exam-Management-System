package com.example.project1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class StudentloginController {
    @FXML
    private Button btnlogin;

    @FXML
    private Button btnregister;

    @FXML
    private PasswordField pspassword;

    @FXML
    private TextField tfusername;

    Connection con;
    PreparedStatement st;
    ResultSet rs;


    @FXML
    void loginhandler(ActionEvent event) throws SQLException, IOException {
        String uname= tfusername.getText();
        String pass= pspassword.getText();
        if(uname.equals("")||pass.equals("")){
            //System.out.println("incorect username or password");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Empty  Format");
            alert.setHeaderText("Please enter your Username and Password");
             alert.show();
        }
       else{
           Connection con= getConnection();

            st=con.prepareStatement("select * from students where username=? and password=?");
            st.setString(1,uname);
            st.setString(2,pass);


            rs= st.executeQuery();
            if(rs.next()){
                FXMLLoader fxmlLoader=new FXMLLoader(Main.class.getResource("studentquestionTry.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 800, 600);
                Stage stage=new Stage();
                stage.setScene(scene);
                stage.show();
            }
            else{
                // JOptionPane.showMessageDialog("login failed");
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Unauthorised User");
                alert.setHeaderText("Incorect  Username or Password");
                alert.show();
                tfusername.setText("");
                pspassword.setText("");
                tfusername.requestFocus();
            }
        }

    }

    @FXML
    void registerhandler(ActionEvent event) {
        String query = "INSERT INTO students VALUES ('"+ tfusername.getText()  +"','"+ pspassword.getText() +"')";
        executeQuery(query);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successfull");
        alert.setHeaderText("You are successfully Registered");
        alert.show();
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
