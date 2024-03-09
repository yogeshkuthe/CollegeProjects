package McaProject;

public class College {
   private int enrollment_Number;
    private String Name;
    private String division;
    private String gender;

    private double marks;

    public College(int enrollment_Number, String name, String division, String gender, double marks) {
        this.enrollment_Number = enrollment_Number;
        Name = name;
        this.division = division;
        this.gender = gender;
        this.marks = marks;
    }

    public int getEnrollment_Number() {
        return enrollment_Number;
    }

    public void setEnrollment_Number(int enrollment_Number) {
        this.enrollment_Number = enrollment_Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}
