package com.example.bipanacsd214test2b;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LogInController {

    public TextField mail;
    public PasswordField Secret;
    public Label Show;

    public void LoginData(ActionEvent actionEvent) {
        Show.setText("");

        String Email = mail.getText();
        String Pro_pass= Secret.getText();

        if(Email.equals("")||Pro_pass.equals("")){

            Show.setText("Please Enter Email or Password");

        }else {
            // Establish a database connection
            String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test2bbipana";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
// Execute a SQL query to retrieve data from the database
                String query = "SELECT * FROM `user_login` WHERE Email='"+Email+"'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
// Populate the table with data from the database

                if(resultSet.next()){
                    int id = resultSet.getInt("id");
                    String Mail = resultSet.getString("Email");
                    String Password = resultSet.getString("Password");

                    if(Password.equals(Pro_pass)){

                        Show.setText("Login Success!!");

                        try {
                            // Load the FXML file for the second scene
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
                            Parent secondScene = loader.load();

                            // Access the controller of the second scene
                            HelloController userController = loader.getController();

                            // Set the data in the controller of the second scene
//                            userController.set_username("Welcome"+First-name);

                            // Create a new stage for the second scene
                            Stage secondStage = new Stage();
                            secondStage.setTitle("Reservation_Details Scene");
                            secondStage.setScene(new Scene(secondScene));

                            Stage firstSceneStage = (Stage) Secret.getScene().getWindow();
                            firstSceneStage.close();

                            // Show the second stage
                            secondStage.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else{
                        Show.setText("Invalid Email or Password.");
                    }

                }else{
                    Show.setText("Invalid Email or Password.");
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
    }
}


