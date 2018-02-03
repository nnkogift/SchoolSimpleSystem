package Admin;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public  class Departments{
    private SimpleIntegerProperty deptId;
    private SimpleStringProperty deptName;
    private  SimpleIntegerProperty subjNo;
    private  SimpleStringProperty nameoHod;

    public Departments(int id, String nameDept, int noSub, String hodName){
        this.deptId = new SimpleIntegerProperty(id);
        this.deptName = new SimpleStringProperty(nameDept);
        this.subjNo = new SimpleIntegerProperty(noSub);
        this.nameoHod = new SimpleStringProperty(hodName);
    }

    public int getDeptId() {
        return deptId.get();
    }

    public SimpleIntegerProperty deptIdProperty() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId.set(deptId);
    }

    public String getDeptName() {
        return deptName.get();
    }

    public SimpleStringProperty deptNameProperty() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName.set(deptName);
    }

    public int getSubjNo() {
        return subjNo.get();
    }

    public SimpleIntegerProperty subjNoProperty() {
        return subjNo;
    }

    public void setSubjNo(int subjNo) {
        this.subjNo.set(subjNo);
    }

    public String getNameoHod() {
        return nameoHod.get();
    }

    public SimpleStringProperty nameoHodProperty() {
        return nameoHod;
    }

    public void setNameoHod(String nameoHod) {
        this.nameoHod.set(nameoHod);
    }
}
