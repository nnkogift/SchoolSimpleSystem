package HeadOfDept;

import Admin.Admin;
import Admin.TeacherProfile;
import Database.DatabaseConnect;
import Login.LoginMain;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

import static Login.LoginMain.headDUsername;


public class HeadDeptMain {
    @FXML
    private JFXTextField deptName;
    @FXML
    private JFXTextField subName1;
    @FXML
    private JFXTextField subName2;
    @FXML
    private JFXTextField subName3;
    @FXML
    private TableView<Teacher> teacherTable;
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn nameColumn;
    @FXML
    private TableColumn subject;
    private int deptId;
    @FXML
    private void initialize(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("teacherId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));
        subject.setCellValueFactory(new PropertyValueFactory<>("subject"));

        //set the dept Name
        deptName.setText(getDeptName());
        deptName.setEditable(false);

        //get the subjects
       subName1.setText(getSubjects(getDeptName())[0]);
       subName2.setText(getSubjects(getDeptName())[1]);
       subName3.setText(getSubjects(getDeptName())[2]);

       //create array of subjects
        String [] subjectList= {"Mathematics","Physics","Chemistry", "Biology", "Civics", "History", "Geography","English", "Swahili","Commerce", "Book Keeping"};

       //get the teachers
        // getting the name
        ObservableList<Teacher> observableList = FXCollections.observableArrayList();
      try{
          ResultSet resultSet =  DatabaseConnect.connectAndAccessDB("SELECT * FROM TEACHERS WHERE DEPTID LIKE '"
          + deptId + "';");
          while (resultSet.next()){
              observableList.addAll(new Teacher(resultSet.getInt("TEACHERID"), resultSet.getString("FIRSTNAME"),subjectList[resultSet.getInt("SUBJECTID") -1]));
              System.out.printf(subjectList[resultSet.getInt("SUBJECTID") -1]);
          }
          teacherTable.setItems(observableList);
      }
      catch (Exception exp){
          System.out.println("Problem with displaying teachers");
      }


    }
    @FXML
    private void addTeacher(Event e){
        try{
            TeacherProfile addT = new TeacherProfile();
            addT.addteacher(e);
        }
        catch (Exception exp){
            System.out.println("Error Adding Teacher");
        }

    }
    @FXML
    private void delTeacher(){
        try{
            TeacherProfile addT = new TeacherProfile();
            addT.setDeleteTeacher();
        }
        catch (Exception exp){
            System.out.println("Error Adding Teacher");
        }
    }
    @FXML
    private void showDepartment(String deptName){
        //
    }
    private String [] getSubjects(String deptName){
        String [] subjects = {"","",""};
        int deptId =0, i=0;
        try{
           ResultSet resultSet =  DatabaseConnect.connectAndAccessDB("SELECT * FROM DEPARTMENTS WHERE DEPTNAME LIKE '" + deptName + "';");

           if(resultSet.next()){
             deptId =  resultSet.getInt("DEPTID");
           }
           resultSet.close();
           if(deptId ==0)
               System.out.println("problem obtaining the deptid");
           resultSet = DatabaseConnect.connectAndAccessDB("SELECT * FROM SUBJECTS WHERE DEPTID LIKE '" + deptId + "';" );

           while (resultSet.next()){
               subjects[i] = resultSet.getString("SUBJECTNAME");
               i++;
           }
           resultSet.close();
        }
        catch (Exception anyExp){
            System.out.println(anyExp.getLocalizedMessage());
        }
        return subjects;
    }
    private String getDeptName(){
        //get the username Entered
        String deptName= null;
        try {
            String username = LoginMain.headDUsername;
            ResultSet resultSet = DatabaseConnect.connectAndAccessDB("SELECT * FROM DEPARTMENTS WHERE HEADNAME LIKE '"
                    + username + "';");
            if(resultSet.next()){
               deptName =  resultSet.getString("DEPTNAME");
               deptId = resultSet.getInt("DEPTID");
            }
        }
        catch (SQLException sql){
            System.out.println("problem with the database access");
        }
    return deptName;
    }
    @FXML
    public  void logout(Event e) {
//        logoutBtn.setOnAction(e -> {
        try {
            Parent teacherParent = FXMLLoader.load(getClass().getResource("/Login/LoginF.fxml"));
            Scene mainScene = new Scene(teacherParent);
            Stage mainStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            mainStage.hide();
            mainStage.setScene(mainScene);
            mainStage.setTitle("Login");
            mainStage.show();
        } catch (Throwable f) {
            System.out.println("This is really annoying");
        }
    }
}
