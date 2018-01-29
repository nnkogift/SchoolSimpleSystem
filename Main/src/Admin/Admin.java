package Admin;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public String school = "Kibaha";


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
           System.out.println(t.fillInStackTrace());
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
        String query = "SELECT * FROM SCHOOLINFO;";
        System.out.println(query);
        ResultSet rset = connectAndAccessDB(query);
        String regno = "", regions="", districts="",wards="";
        if(rset.next()){
            school = rset.getString("SCHOOLNAME");
            regno = rset.getString("SCHOOLREGNO");
            regions = rset.getString("Region ");
            districts = rset.getString("DISTRICT");
            wards = rset.getString("WARD");

        }
        System.out.println(school + regno + regions + districts + wards);
        schoolName = new Label(school);
//        regNo.setText(regno);
//        region.setText(regions);
//        district.setText(districts);
//        ward.setText(wards);


    }
    private ResultSet connectAndAccessDB(String query) throws SQLException{
        DatabaseConnect connect = new DatabaseConnect();
        Connection connection = connect.connectDb();
        if(connection == null)
        connection.createStatement().executeUpdate("SET DATABASE SQL SYNTAX MYS TRUE");
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.clearParameters();
        return  preparedStatement.executeQuery();
    }





}
