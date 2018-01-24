package Login;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class LoginMain {
    //this class is going to access the User table and log in the users
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXButton loginBtn;
    @FXML
    private JFXComboBox<String> loginas;
    ResultSet resultSet = null;
    private int previledgeID;
    @FXML
    private void getLoginAction(Event event)throws IOException {
        String usernameS;
        String passwordS;
        String loginasS;

        usernameS = username.getText();
        passwordS = password.getText();
        loginasS = loginas.getSelectionModel().getSelectedItem();
        //setting the previlege from the comboBox  ooh okay no need...

        switch (loginasS) {
            case "Admin": {
                //first we check the integrity of the entered values
                //ask for connection
                //create a statement
                System.out.println("Starting the Database attack");
                System.out.println("Administrator");
                previledgeID = 1;
                if (validation(usernameS, passwordS)) {
                    Parent adminParent = FXMLLoader.load(getClass().getResource("/Admin/Admin.fxml"));
                    Scene adminScene = new Scene(adminParent);
                    Stage adminStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    adminStage.hide();
                    adminStage.setScene(adminScene);
                    adminStage.setTitle("Administrator");
                    adminStage.show();
                }
                break;
            }

            case "Teacher": {
                System.out.println("Teacher");
                previledgeID = 4;
                if (validation(usernameS, passwordS)) {
                    Parent teacherParent = FXMLLoader.load(getClass().getResource("/teacher/teacher.fxml"));
                    Scene teacherScene = new Scene(teacherParent);
                    Stage teacherStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    teacherStage.hide();
                    teacherStage.setScene(teacherScene);
                    teacherStage.setTitle("Teacher");
                    teacherStage.show();
                }
                break;
            }
            case "HeadMaster": {
                System.out.println("HeadMaster");
                previledgeID = 2;
                if (validation(usernameS, passwordS)) {
                    Parent headmasterParent = FXMLLoader.load(getClass().getResource("headmaster/headmaster.fxml"));
                    Scene headmasterScene = new Scene(headmasterParent);
                    Stage headmasterStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    headmasterStage.hide();
                    headmasterStage.setScene(headmasterScene);
                    headmasterStage.setTitle("HeadMaster");
                    headmasterStage.show();
                }
                break;
            }
            case "Head of Dept": {
                System.out.println("Head of dept");
                previledgeID = 3;
                if (validation(usernameS, passwordS)) {
                    Parent HeadofDeptParent = FXMLLoader.load(getClass().getResource("HeadofDept/HeadofDept.fxml"));
                    Scene HeadofDeptScene = new Scene(HeadofDeptParent);
                    Stage HeadofDeptStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    HeadofDeptStage.hide();
                    HeadofDeptStage.setScene(HeadofDeptScene);
                    HeadofDeptStage.setTitle("HeadofDept");
                    HeadofDeptStage.show();
                }
                break;
            }

        }
    }
    @FXML
        private boolean validation (String usernameS, String passwordS){
            boolean isithim = false;

            try {
                DatabaseConnect connect = new DatabaseConnect();

                Connection connection = connect.connectDb();

                connection.createStatement().executeUpdate("SET DATABASE SQL SYNTAX MYS TRUE");
                //System.out.println("SELECT USERNAME,PASSWORD,PREVILEDGEID FROM PUBLIC.USERS WHERE USERNAME=\'" + usernameS + "\';");
                 String query = "SELECT * FROM PUBLIC.PUBLIC.USERS WHERE USERNAME LIKE '%" + usernameS + "%';";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.clearParameters();
                resultSet = preparedStatement.executeQuery();
                usernameS = usernameS.trim();
                passwordS = passwordS.trim();

                if (resultSet.next()) {
                    System.out.println(resultSet.getString("USERNAME"));
                    System.out.println(resultSet.getString("PASSWORD"));
                    System.out.println(resultSet.getInt("PREVILEDGEID"));
                    System.out.println(previledgeID);
                    if (!usernameS.equalsIgnoreCase(resultSet.getString("USERNAME")) )
                        System.out.println("Username is incorrect");
                    else if(!passwordS.equalsIgnoreCase(resultSet.getString("PASSWORD")))
                        System.out.println("Password is incorrect");
                    else if(resultSet.getInt("PREVILEDGEID") != previledgeID)
                        System.out.println("Wrong privilege");

                     else {
                        isithim = true;
                    }
                }
            } catch (Throwable e) {
                System.out.println("Error ni:" + e.getLocalizedMessage());

            }
            return isithim;
        }

        public LoginMain() {
        username =  new JFXTextField();
        password = new JFXPasswordField();
        loginas = new JFXComboBox<>();
        loginBtn = new JFXButton();

    }
}
