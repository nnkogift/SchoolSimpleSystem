package Admin;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;


import java.util.Date;

public class TeacherAdd {
    @FXML
    private JFXButton addTeacher;
    @FXML
    private JFXTextField firstName;
    @FXML
    private JFXTextField surname;
    @FXML
    private JFXTextField otherName;
    @FXML
    private JFXTextField address;
    @FXML
    private JFXComboBox<String> classPicker;
    @FXML
    private JFXDatePicker datePicker;
    @FXML
    private JFXComboBox<String> sexPicker;
    @FXML
    private JFXTextField stdID;
    @FXML
    private JFXComboBox<String> subject;

    public static int success = 0;

    @FXML
    private void setAddTeacher(Event e) {
        String fName, sName, oName, addrs, teacherClass, sexS;
        Date dOB;
        int teacherID;
        int subjId;

        if(firstName.getText() != null && surname.getText() != null && otherName.getText() != null && address.getText() != null && classPicker.getSelectionModel().getSelectedItem() != null){
            fName = firstName.getText().trim();
            sName = surname.getText().trim();
            oName = otherName.getText().trim();
            addrs = address.getText().trim();
            subjId = subject.getSelectionModel().getSelectedIndex();

            teacherClass = classPicker.getSelectionModel().getSelectedItem();
            //convert to int
            int classID =0;

            switch (teacherClass){
                case "Mathematics": classID = 1;
                    break;
                case "Science": classID = 2;
                    break;
                case "Social Studies": classID = 3;
                    break;
                case "Languages": classID = 4;
                    break;
            }

            sexS = sexPicker.getSelectionModel().getSelectedItem();
            teacherID = Integer.parseInt(stdID.getText());

            //writing to the database
            String query = "INSERT INTO TEACHERS (TEACHERID,FIRSTNAME,SURNAME,OTHERNAME,SEX,ADDRESS,DEPTID,SUBJECTID) VALUES ('" +   + teacherID + "','"  + fName +"','" + sName + "','" + oName + "','" + sexS + "','" + addrs + "','" + classID + "','" + (subjId +1) + "');";
            //System.out.println(query);
            try{
                success =  DatabaseConnect.writeDB(query);
            }
            catch (Exception exp){
                System.out.println("Houston we have a problem! Exp: "+ exp.getLocalizedMessage());
            }
            if(success != 0){
                TeacherProfile.addStage.close();
            }

        }
        else {
            System.out.println("Something is wrong with the data entered");
        }


    }
}
