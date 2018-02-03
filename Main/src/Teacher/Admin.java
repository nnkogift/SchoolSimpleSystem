package Teacher;

import Login.LoginMain;
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

import java.io.IOException;

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
    private JFXButton students;
    @FXML
    private JFXButton teachers;
    @FXML
    private JFXButton department;
    @FXML
    private JFXButton schoolInfo;

    public static Stage mainAdminStage;
    public String school = "Kibaha";


    @FXML
    public void logout(Event e){
//        logoutBtn.setOnAction(e -> {
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
     //   });
    }

    public Admin(){

    }
    @FXML
    public void showStudentInfo(Event e) throws IOException{
        //this opens a new stage where the student information is shown

    }
    @FXML
    public void showTeacherInfo(Event e){
        // this opens a new stage that shows teacher's information
    }
    @FXML
    public void showStudents(Event e){
        try{
            changeScene("Students Information", "Students_Profile.fxml", e);

        }catch (Exception exp){
            System.out.println("The methods change scene or viewStudent have a problem error: " + exp.getLocalizedMessage());
        }

    }
    @FXML
    public void showTeachers(Event e){
        //this shows the list of all registered teachers
        try {
            changeScene("Teachers Information", "Teachers_profile.fxml", e);
        }
        catch (Exception exp){
            System.out.println(exp.getLocalizedMessage());
        }

    }
    @FXML
    public void showDepts(Event e){
        try {
            changeScene("Departments", "DeptProfile.fxml", e);
        }
        catch (Exception exp){
            System.out.println(exp.getLocalizedMessage());
        }
    }
    @FXML
    public  void showSchoolInfo(Event e) {
        try {
            changeScene("Administrator", "Admin.fxml", e);
        }
        catch (IOException exp){
            System.out.println("Problem show school info" + exp.getLocalizedMessage());
        }
    }
    private  void changeScene(String nameOfScene, String fxmllocation, Event e) throws IOException{
        Parent studentinfoStage = FXMLLoader.load(getClass().getResource(fxmllocation));
        LoginMain.adminScene = new Scene(studentinfoStage);
        mainAdminStage  = (Stage) ((Node) e.getSource()).getScene().getWindow();
        //mainAdminStage.hide();
        mainAdminStage.setScene(LoginMain.adminScene);
        mainAdminStage.setTitle(nameOfScene);
        mainAdminStage.show();
    }

}
