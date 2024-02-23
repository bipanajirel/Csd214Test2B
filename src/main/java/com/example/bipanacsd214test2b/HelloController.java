package com.example.bipanacsd214test2b;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;

import java.util.ResourceBundle;

import static java.lang.Integer.valueOf;

public class HelloController implements Initializable {

    public TextField iD;
    public TextField Cus;
    public TextField date;
    public TextField NoG;

    @FXML
    private TableView<Reservation_Details> reservation;
    @FXML
    private TableColumn<Reservation_Details, Integer> ReservationID;
    @FXML
    private TableColumn<Reservation_Details, String> CustomerName;
    @FXML
    private TableColumn<Reservation_Details, String> ReservationDate;
    @FXML
    private TableColumn<Reservation_Details, Integer> NumOfGuests;


    ObservableList<Reservation_Details> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ReservationID.setCellValueFactory(new
                PropertyValueFactory<Reservation_Details, Integer>("ReservationID"));
        CustomerName.setCellValueFactory(new
                PropertyValueFactory<Reservation_Details, String>("CustomerName"));
        ReservationDate.setCellValueFactory(new
                PropertyValueFactory<Reservation_Details, String>("ReservationDate"));
        NumOfGuests.setCellValueFactory(new
                PropertyValueFactory<Reservation_Details, Integer>("NumOfGuests"));
        reservation.setItems(list);
    }


    @FXML
    protected void onHelloButtonClick() {
        populateTable();
    }

    public void populateTable() {
        // Establish a database connection

        list.clear();
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test2bbipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM reservation_details";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                int ReservationID = resultSet.getInt("ReservationID");
                String CustomerName = resultSet.getString("CustomerName");
                String ReservationDate = resultSet.getString("ReservationDate");
                int NumOfGuests = resultSet.getInt("NumOfGuests");
                reservation.getItems().add(new Reservation_Details(ReservationID, CustomerName, ReservationDate, NumOfGuests));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertNew(ActionEvent actionEvent) {
        String customer = Cus.getText();
        String reserved = date.getText();
        Integer num= Integer.valueOf(NoG.getText());
        InsertTable(customer, reserved, num);
    }

    private void InsertTable(String customer, String reserved, Integer num) {

            String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test2bbipana";
            String dbUser = "root";
            String dbPassword = "";
            try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                    dbPassword)) {
// Execute a SQL query to retrieve data from the database
                String query = "INSERT INTO `reservation_details`(`CustomerName`, `ReservationDate`, `NumOfGuests`) VALUES ('" + customer + "','" + reserved + "','" + num + "')";
                Statement statement = connection.createStatement();
                statement.execute(query);

                populateTable();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        public void LoadData(ActionEvent actionEvent) {
        Integer id = valueOf(iD.getText());
        LoadTable(id);
    }

    private void LoadTable(Integer id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test2bbipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
            // Execute a SQL query to retrieve data from the database
            String query = "SELECT * FROM `reservation_details` WHERE ReservationID='" + id + "'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // Populate the table with data from the database
            while (resultSet.next()) {
                String CustomerName = resultSet.getString("CustomerName");
                String ReservationDate = resultSet.getString("ReservationDate");
                int NumOfGuests = resultSet.getInt(" NumOfGuests");
                Cus.setText(CustomerName);
                date.setText(ReservationDate);
                NoG.setText(String.valueOf(valueOf(NumOfGuests)));
                populateTable();


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }
    public void UpdateData(ActionEvent actionEvent) {
        Integer id = valueOf(iD.getText());
        String customer = Cus.getText();
        String reserved = date.getText();
        Integer num= Integer.valueOf(NoG.getText());
        UpdateTable(id,customer,reserved,num);
    }

    private void UpdateTable(Integer id, String customer, String reserved, Integer num) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test2bbipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "UPDATE `reservation_details` SET `CustomerName`='"+customer+"',`ReservationDate`='"+reserved+"',`NumOfGuests`='"+num+"' WHERE ReservationID='"+id+"'";
            Statement statement = connection.createStatement();
            statement.execute(query);

            populateTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void Deletedata(ActionEvent actionEvent) {
        Integer id = valueOf(iD.getText());
        DeletTable(id);
    }

    private void DeletTable(Integer id) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/csd214test2bbipana";
        String dbUser = "root";
        String dbPassword = "";
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser,
                dbPassword)) {
// Execute a SQL query to retrieve data from the database
            String query = "DELETE FROM `reservation_details` WHERE ReservationID='" + id + "'";
            Statement statement = connection.createStatement();
            statement.execute(query);


        } catch (SQLException e) {
            e.printStackTrace();
        }
        populateTable();
    }
}




