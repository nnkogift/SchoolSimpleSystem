package Admin;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Admin {
    //the admin has the ability to
    // view all students,teachers
    // edit all students, teachers
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private JFXTextField alltxtfieldhere;
    @FXML
    private Label schoolName;
    @FXML
    private Label regNo;
    @FXML
    private Label region;
    @FXML
    private Label district;
    @FXML
    private Label ward;
    @FXML
    private JFXDatePicker dateOfBirth, dateofBirthP;


    @FXML
    private void logout(){
        logoutBtn.setOnAction(e -> {
            try {
                Parent teacherParent = FXMLLoader.load(getClass().getResource("/Login/LoginF.fxml"));
                Scene mainScene = new Scene(teacherParent);
                Stage mainStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                mainStage.hide();
                mainStage.setScene(mainScene);
                mainStage.setTitle("Login");
                mainStage.show();
            }
            catch (Throwable f){
                System.out.println("This is really annoying");
            }
        });
    }

    public Admin(){
       try {
           showSchoolInfo();
       }
       catch (Throwable t){
           System.out.println(t.getLocalizedMessage());
       }
    }
    @FXML
    public void showStudentInfo(Event e){
        //this opens a new stage where the student information is shown
    }
    @FXML
    public void showTeacherInfo(Event e){
        // this opens a new stage that shows teacher's information
    }
    @FXML
    public void showStudents(Event e){

    }
    @FXML
    public void showTeachers(Event e){
        //this shows the list of all registered teachers
    }
    @FXML
    public void addTeacher(Event e){

    }
    @FXML
    public void addStudent(Event e){
        //allows the addition of new student
    }
    @FXML
    private void showSchoolInfo()throws SQLException{
        // this shows the school information using the assigned labels
        String query = "select * from SCHOOLINFO where SCHOOLNAME like \'Kibaha\';";
        ResultSet rset = connectAndAcessDB(query);

        if(rset.next()){
            schoolName.setText(rset.getString("schoolname") + " Secondary School");
            regNo.setText(rset.getString("schoolregno"));
            region.setText(rset.getString("region"));
            district.setText(rset.getString("district"));
            ward.setText(rset.getString("ward"));
        }

    }
    private ResultSet connectAndAcessDB( String query) throws SQLException{
        DatabaseConnect connect = new DatabaseConnect();

        Connection connection = connect.connectDb();

        connection.createStatement().executeUpdate("SET DATABASE SQL SYNTAX MYS TRUE");
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.clearParameters();
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }





}
