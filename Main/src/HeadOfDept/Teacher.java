package HeadOfDept;

public class Teacher {
    public int teacherId;
    public String teacherName;
    public String subject;

    public Teacher(int id, String name, String subj){
        this.teacherId = id;
        this.teacherName = name;
        this.subject = subj;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
