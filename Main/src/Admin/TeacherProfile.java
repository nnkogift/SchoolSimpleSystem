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
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TeacherProfile {

    @FXML
    private JFXButton NextBtn;
    @FXML
    private JFXTextField AdminNoTxt;
    @FXML
    private TableView<Person> teacherTable;
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
    private JFXButton addNewteacherBtn;
    @FXML
    private JFXButton viewTeachers;

    public static Stage addStage;
    public static int deptID = 1;

    private Admin school= new Admin();

    public void showteacherInfo(){

    }
    @FXML
    public void addteacher(Event e){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("teacherAdd.fxml"));
            Scene addScene = new Scene(root, 600,400);
            addStage = new Stage();
            addStage.setScene(addScene);
            addStage.setAlwaysOnTop(true);
            addStage.showAndWait();

            //closing it when done
            if(TeacherAdd.success != 0){
               viewTeachers();
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
    public TeacherProfile()throws SQLException{
        // viewTeachers();
    }
    @FXML
    private void viewTeachers(String query) throws SQLException{

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
        teacherTable.setItems(databaseRetrieve(query));
        //adding the list to the table
        // teacherTable.getItems().add(data);
      //  System.out.println("SELECT * FROM Teachers WHERE DEPTID LIKE '" + deptID + "';");
    }
    @FXML
    private void showSchoolInfo(Event e)throws IOException{
        school.showSchoolInfo(e);
    }
    @FXML
    private void logout(Event e){
        school.logout(e);
    }
    @FXML
    private void showStudentsInfo(Event e){
        school.showStudents(e);
    }
    @FXML
    private void showDeptsInfo(Event e){
        school.showDepts(e);
    }
    private ObservableList databaseRetrieve(String query){
        ObservableList<Person> data = FXCollections.observableArrayList();
        try {
            ResultSet results = DatabaseConnect.connectAndAccessDB(query);
            //creating a string array to store the db data

            while (results.next()) {
                //Entering the raw data into the ObservableList
                data.addAll(new Person(results.getString("teacherID"),
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
    private void showSelectedDept(){
        
        try {
            switch (classSelection.getSelectionModel().getSelectedItem()) {
                case "Mathematics": {
                    deptID = 1;
                    viewTeachers("SELECT * FROM TEACHERS WHERE DEPTID LIKE '" + deptID + "';");
                    break;
                }
                case "Science":
                    deptID = 2;
                    viewTeachers("SELECT * FROM TEACHERS WHERE DEPTID LIKE '" + deptID + "';");
                    break;
                case "Social Studies":
                    deptID = 3;
                    viewTeachers("SELECT * FROM TEACHERS WHERE DEPTID LIKE '" + deptID + "';");
                    break;
                case "Languages":
                    deptID = 4;
                    viewTeachers("SELECT * FROM TEACHERS WHERE DEPTID LIKE '" + deptID + "';");
                    break;
            }
        }
        catch (Exception exp){
            System.out.println("Houston we have a late night situation Exp: " + exp.getLocalizedMessage());
        }

    }
    @FXML
    private void initialize(){
        try {
            viewTeachers("SELECT * FROM TEACHERS;");
        }
        catch (Exception exp){
            System.out.println("initialize failed Exp: " + exp.getLocalizedMessage());
        }

    }
    @FXML
    private void viewTeachers(){
        try {
            viewTeachers("SELECT * FROM TEACHERS;");
        }
        catch (Exception exp){
            System.out.println("initialize failed Exp: " + exp.getLocalizedMessage());
        }
    }
    @FXML
    public void setDeleteTeacher()throws SQLException {
        int success;
        Person selectedPerson = teacherTable.getSelectionModel().getSelectedItem();
        String selectedId = selectedPerson.getId();
        success = DatabaseConnect.deleteRow("DELETE FROM TEACHERS WHERE TEACHERID LIKE '" + selectedId + "';");

        if (success != 0) {

            initialize();
        } else {
            System.out.println("Problem deleting");
        }
    }

}
