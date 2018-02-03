package Admin;

import Database.DatabaseConnect;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;

public  class DeptAdd{
    @FXML
    private JFXButton edit;
    @FXML
    public JFXTextField deptName;
    @FXML
    public  JFXTextField subjNo;
    @FXML
    public  JFXTextField headName;
    @FXML
    public  JFXTextField deptID;
    DeptController controller = new DeptController();
    public static int row = 0;
    @FXML
    private void setAdd(){

        try{
            row = DatabaseConnect.writeDB("INSERT INTO DEPARTMENTS (DEPTID,DEPTNAME,SUBNO,HEADNAME)VALUES" +
                    " ('" + deptID.getText() + "','" + deptName.getText() + "','" + subjNo.getText() +"','" + headName.getText() + "');");
        }
        catch (Exception exp){
            System.out.println("Error Editing the dept");
        }
        if(row != 0){
            System.out.println("Data Written Successfully");
            DeptController.editStage.close();
        }
    }
//    public void getSelectedDept(){
//        Departments selectedDept = deptTable.getSelectionModel().getSelectedItem();
//        if(selectedDept != null) {
//            deptID.setText(Integer.toString(selectedDept.getDeptId()));
//            deptName.setText(selectedDept.getDeptName());
//            subjNo.setText(Integer.toString(selectedDept.getSubjNo()));
//            headName.setText(selectedDept.getNameoHod());
//            // System.out.println(selectedDept.getDeptName());
//        }
//        else {
//            System.out.println("Please select a Department to edit");
//        }
//    }
}
