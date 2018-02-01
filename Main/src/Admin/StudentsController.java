package Admin;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentsController {

    @FXML
    private JFXButton NextBtn;
    @FXML
    private JFXTextField AdminNoTxt;
    @FXML
    private TableView studentTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn firstNameColumn;
    @FXML
    private TableColumn surnameColumn;
    @FXML
    private TableColumn otherNameColumn;
    @FXML
    private TableColumn sexColumn;
    @FXML
    private TableColumn addressColumn;
    @FXML
    private JFXComboBox<String> classSelection;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private JFXButton addNewStudentBtn;
    @FXML
    private JFXButton viewStudents;

    public static Stage addStage;
    public static int classID = 1;

   private Admin school= new Admin();

    public void showStudentInfo(){

    }
    @FXML
    public void addStudent(Event e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StudentAdd.fxml"));
            Scene addScene = new Scene(root, 600,400);
            addStage = new Stage();
            addStage.setScene(addScene);
            addStage.show();

            //closing it when done
            if(StudentAdd.success != 0){
                viewStudents();
            }

        }
        catch (Exception exp){
            System.out.println("Houston we have a problem!  " + exp.getLocalizedMessage());

        }

    }
    public static class Person {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty otherName;
        private final SimpleStringProperty addressProperty;
        private final SimpleStringProperty id;
        private final SimpleStringProperty sex;

        private Person(String id, String fName, String lName, String otherName, String sex, String addressProperty) {
            this.firstName = new SimpleStringProperty(fName);
            this.lastName = new SimpleStringProperty(lName);
            this.addressProperty = new SimpleStringProperty(addressProperty);
            this.id = new SimpleStringProperty(id);
            this.sex = new SimpleStringProperty(sex);
            this.otherName = new SimpleStringProperty(otherName);
        }

        public String getFirstName() {
            return firstName.get();
        }

        public void setFirstName(String fName) {
            firstName.set(fName);
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setLastName(String lName) {
            lastName.set(lName);
        }

        public String getAddressProperty() {
            return addressProperty.get();
        }

        public void setAddress(String fName) {
            addressProperty.set(fName);
        }

        public String getSex() {
            return sex.get();
        }

        public void setSex(String fName) {
            sex.set(fName);
        }

        public String getOtherName() {
            return otherName.get();
        }

        public void setOtherName(String fName) {
            otherName.set(fName);
        }

        public String getId() {
            return id.get();
        }

        public void setId(String fName) {
            id.set(fName);
        }
        
    }
    public StudentsController()throws SQLException{
       // viewStudents();
    }
    @FXML
    public  void viewStudents() throws SQLException{

        //setting the PropertyValue

        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        surnameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        sexColumn.setCellValueFactory(
                new PropertyValueFactory<>("sex"));
        otherNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("otherName"));
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("addressProperty"));
        idColumn.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        //query the data from the database
        studentTable.setItems(databaseRetrieve("SELECT * FROM STUDENTS WHERE CLASSID LIKE '" + classID + "';"));
        //adding the list to the table
       // studentTable.getItems().add(data);
        System.out.println("SELECT * FROM STUDENTS WHERE CLASSID LIKE '" + classID + "');");
    }
    @FXML
    private void showSchoolInfo(Event e)throws IOException{
        school.showSchoolInfo(e);
    }
    @FXML
    private void logout(Event e){
        school.logout(e);
    }
    private ObservableList databaseRetrieve(String query){
        ObservableList<Person> data = FXCollections.observableArrayList();
        try {
            ResultSet results = DatabaseConnect.connectAndAccessDB(query);
            //creating a string array to store the db data

            while (results.next()) {
                //Entering the raw data into the ObservableList
            data.addAll(new Person(results.getString("STUDENTID"),
                    results.getString("FIRSTNAME"),results.getString("SURNAME"),
                    results.getString("OTHERNAME"),results.getString("SEX"),
                    results.getString("ADDRESS")));
            }

            DatabaseConnect.closeConnection(results);
        }
        catch (Exception exception){
            System.out.println("database retrieve has a problem");
        }
        return data;
    }

    @FXML
    private void showSelectedClass(){
        String classSelected = classSelection.getSelectionModel().getSelectedItem();
        try {
            switch (classSelected) {
                case "Form 1": {
                    classID = 1;
                    viewStudents();
                    break;
                }
                case "Form 2":
                    classID = 2;
                    viewStudents();
                    break;
                case "Form 3":
                    classID = 3;
                    viewStudents();
                    break;
                case "Form 4":
                    classID = 4;
                    viewStudents();
                    break;
            }
        }
        catch (Exception exp){
            System.out.println("Houston we have a late night situation Exp: " + exp.getLocalizedMessage());
        }


    }

}
