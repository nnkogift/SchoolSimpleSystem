package Admin;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;


import java.util.Date;

public class StudentAdd {
    @FXML
    private JFXButton addStudent;
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

    public static int success = 0;

    @FXML
    private void setAddStudent(Event e) {
        String fName, sName, oName, addrs, studentClass, sexS;
        Date dOB;
        int studentID;

        if(firstName.getText() != null && surname.getText() != null && otherName.getText() != null && address.getText() != null && classPicker.getSelectionModel().getSelectedItem() != null){
            fName = firstName.getText().trim();
            sName = surname.getText().trim();
            oName = otherName.getText().trim();
            addrs = address.getText().trim();
            studentClass = classPicker.getSelectionModel().getSelectedItem();
            //convert to int
            int classID =0;
            switch (studentClass){
                case "Form 1": classID = 1;
                break;
                case "Form 2": classID = 2;
                break;
                case "Form 3": classID = 3;
                break;
                case "Form 4": classID = 4;
                break;
            }
            sexS = sexPicker.getSelectionModel().getSelectedItem();
            studentID = Integer.parseInt(stdID.getText());

            //writing to the database
            String query = "INSERT INTO STUDENTS (STUDENTID,FIRSTNAME,SURNAME,OTHERNAME,SEX,ADDRESS,CLASSID) VALUES ('" +   + studentID + "','"  + fName +"','" + sName + "','" + oName + "','" + sexS + "','" + addrs + "','" + classID + "');";
            //System.out.println(query);
            try{
               success =  DatabaseConnect.writeDB(query);
            }
            catch (Exception exp){
                System.out.println("Houston we have a problem! Exp: "+ exp.getLocalizedMessage());
            }
            if(success != 0){
                StudentsController.addStage.close();
            }

        }
        else {
            System.out.println("Something is wrong with the data entered");
        }


    }
}
