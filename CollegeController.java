package McaProject;

import java.sql.*;
public class CollegeController {
    public static Connection connectDB(String dbUrl,String dbUser,String dbpass) {
        Connection con=null;
        try {
            con = DriverManager.getConnection(dbUrl,dbUser,dbpass);
        } catch (Exception e) {
            System.out.println(e);
        }
        return con;
    }
    public void createStudent(Connection con,String tableName) throws SQLException
    {
        String query="create table college (enrollmentNumber int(10) primary key auto_increment ," +
                "studentName varchar(45) not null ," +
                "division varchar(10) not null ,gender varchar(10) not null,marks double precision not null )";
        PreparedStatement psmt=con.prepareStatement(query);
        psmt.executeUpdate();
        PreparedStatement psmt1=con.prepareStatement("alter table college auto_increment=2023100");
        psmt1.executeUpdate();
        System.out.println("Table created Successfully...");
        System.out.println("--------------------------------------------------------");
    }
public void insertData(Connection con,String name,String division,String gender,double marks) throws SQLException {
    PreparedStatement psmt1=con.prepareStatement("alter table college auto_increment=2023100");
    psmt1.executeUpdate();
    //String query=String.format("insert into college(studentName,division,gender,marks) values(%s,%s,%c,%.2f)",name,division,gender,marks);
    String query=String.format("insert into college(studentName,division,gender,marks) values(?,?,?,?)");
    //insert into college(studentName,division,gender,marks) values('Nil','div 1','m',55.55);
    PreparedStatement psmt=con.prepareStatement(query);

    psmt.setString(1,name);
    psmt.setString(2,division);
    psmt.setString(3,gender);
    psmt.setDouble(4,marks);

    psmt.executeUpdate();
    System.out.println("Data inserted successfully...");
    System.out.println("--------------------------------------------------------");
}
public void printSpecificStudent(Connection con,int enroll) throws SQLException {
    String query = "select * from college where enrollmentNumber=" + enroll;
    PreparedStatement psmt = con.prepareStatement(query);

    ResultSet rs = psmt.executeQuery();
    while (rs.next()) {
        College c = new College(rs.getInt("enrollmentNumber"), rs.getString("studentName"),
                rs.getString("division"),rs.getString("gender"),
                rs.getDouble("marks"));

        c.setEnrollment_Number(rs.getInt("enrollmentNumber"));
        c.setName(rs.getString("studentName"));
        c.setDivision(rs.getString("division"));
        c.setGender(rs.getString("gender"));
        c.setMarks(rs.getDouble("marks"));


        System.out.printf(
                        "+-------------------------------------------------------------------%n" +
                        "| %-18s | %-15s | %-10s | %-8s | %-6s |%n" +
                        "+-------------------------------------------------------------------%n" +
                        "| %-18d | %-15s | %-10s | %-8s | %-6.2f |%n",
                "enrollmentNumber", "studentName", "division", "gender", "marks",
                rs.getInt("enrollmentNumber"), rs.getString("studentName"),
                rs.getString("division"), rs.getString("gender"), rs.getDouble("marks"));
        System.out.println("+-------------------------------------------------------------------+");

    }
}





public void grade(Connection con,String stuName) throws SQLException {
    //String query="select * from college where studentName="+ stuName;
    String query="select * from college where studentName=?";
    PreparedStatement psmt=con.prepareStatement(query);
psmt.setString(1,stuName);
    ResultSet rs=psmt.executeQuery();
    double result=0;
    while (rs.next())
    {
        result=rs.getDouble("marks");
    }
    System.out.println("result : "+result);
    if(result>=75 && result<=100)
    {
        System.out.println("+--------------+--------+");
        System.out.println("| Name         | Grade  |");
        System.out.println("+--------------+--------+");
        System.out.printf("| %-12s | A     |\n", stuName);
        System.out.println("+--------------+--------+");
        System.out.println();
    }
    else if(result>=60 && result<=74)
    {
        System.out.println("+--------------+--------+");
        System.out.println("| Name         | Grade  |");
        System.out.println("+--------------+--------+");
        System.out.printf("| %-12s | B     |\n", stuName);
        System.out.println("+--------------+--------+");                             System.out.println();
    }
    else if(result>=40 && result<=59)
    {
        System.out.println("+--------------+--------+");
        System.out.println("| Name         | Grade  |");
        System.out.println("+--------------+--------+");
        System.out.printf("| %-12s | C     |\n", stuName);
        System.out.println("+--------------+--------+");
        System.out.println();
    }
    else{
        System.out.println("+--------------+--------+");
        System.out.println("| Name         | Grade  |");
        System.out.println("+--------------+--------+");
        System.out.printf("| %-12s | D     |\n", stuName);
        System.out.println("+--------------+--------+");
        System.out.println();
    }
}
    public void findByGender(Connection con,String gender) throws SQLException {
        String query = "select * from college where gender=?";
        PreparedStatement psmt = con.prepareStatement(query);
        psmt.setString(1, gender);
        ResultSet rs = psmt.executeQuery();

        System.out.println("+-------------------------------------------------------------------+");
        System.out.printf("| %-18s | %-15s | %-10s | %-8s | %-6s |%n", "enrollmentNumber", "studentName", "division", "gender", "marks");
        System.out.println("+-------------------------------------------------------------------+");

        while (rs.next()) {
            College c = new College(rs.getInt("enrollmentNumber"), rs.getString("studentName"),
                    rs.getString("division"), rs.getString("gender"),
                    rs.getDouble("marks"));

            c.setEnrollment_Number(rs.getInt("enrollmentNumber"));
            c.setName(rs.getString("studentName"));
            c.setDivision(rs.getString("division"));
            c.setGender(rs.getString("gender"));
            c.setMarks(rs.getDouble("marks"));

            // Print data inside the loop
            System.out.printf("| %-18d | %-15s | %-10s | %-8s | %-6.2f |%n",
                    c.getEnrollment_Number(), c.getName(), c.getDivision(), c.getGender(), c.getMarks());
            System.out.println("+-------------------------------------------------------------------+");
        }
    }
    public void bonusMarks(Connection con,int enroll,double bonus) throws SQLException {
        String query="select marks from college where enrollmentNumber=?";

        PreparedStatement pstm=con.prepareStatement(query);
         pstm.setInt(1,enroll);
        ResultSet rs=pstm.executeQuery();

        double res=0;
        while(rs.next())
        {
         res=rs.getDouble("marks");
        }
        res=res+bonus;

        String query1="update college set marks=? where enrollmentNumber=?";
        PreparedStatement pstm2=con.prepareStatement(query1);
        pstm2.setDouble(1,res);
        pstm2.setInt(2,enroll);
        pstm2.executeUpdate();

        printSpecificStudent(con,enroll);
    }
    public void changeDivision(Connection con,String name,String  div) throws SQLException {
        String query="update college set division=? where studentName=?";
        PreparedStatement pstm=con.prepareStatement(query);
        pstm.setString(1,div);
        pstm.setString(2,name);
        pstm.executeUpdate();
        System.out.println("Division updated successfully...");
        System.out.println("+-------------------------------------------------------------------+");

    }
    public void range(Connection con,double min,double max) throws SQLException {
        String query="select * from college where marks>=? and marks<=?";
        PreparedStatement pstm=con.prepareStatement(query);
        pstm.setDouble(1,min);
        pstm.setDouble(2,max);

        ResultSet rs=pstm.executeQuery();
        System.out.println("+-------------------------------------------------------------------+");
        System.out.printf("| %-18s | %-15s | %-10s | %-8s | %-6s |%n", "enrollmentNumber", "studentName", "division", "gender", "marks");
        System.out.println("+-------------------------------------------------------------------+");

        while (rs.next()) {
            College c = new College(rs.getInt("enrollmentNumber"), rs.getString("studentName"),
                    rs.getString("division"), rs.getString("gender"),
                    rs.getDouble("marks"));

            c.setEnrollment_Number(rs.getInt("enrollmentNumber"));
            c.setName(rs.getString("studentName"));
            c.setDivision(rs.getString("division"));
            c.setGender(rs.getString("gender"));
            c.setMarks(rs.getDouble("marks"));

            // Print data inside the loop
            System.out.printf("| %-18d | %-15s | %-10s | %-8s | %-6.2f |%n",
                    c.getEnrollment_Number(), c.getName(), c.getDivision(), c.getGender(), c.getMarks());
            System.out.println("+-------------------------------------------------------------------+");
        }
    }
    public void printTable(Connection con) throws SQLException {
        String query="select * from college";
        PreparedStatement psmt = con.prepareStatement(query);

        ResultSet rs = psmt.executeQuery();

        System.out.println("+-------------------------------------------------------------------+");
        System.out.printf("| %-18s | %-15s | %-10s | %-8s | %-6s |%n", "enrollmentNumber", "studentName", "division", "gender", "marks");
        System.out.println("+--------------------------------------------------------------------+");

        while (rs.next()) {
            College c = new College(rs.getInt("enrollmentNumber"), rs.getString("studentName"),
                    rs.getString("division"), rs.getString("gender"),
                    rs.getDouble("marks"));

            c.setEnrollment_Number(rs.getInt("enrollmentNumber"));
            c.setName(rs.getString("studentName"));
            c.setDivision(rs.getString("division"));
            c.setGender(rs.getString("gender"));
            c.setMarks(rs.getDouble("marks"));

            // Print data inside the loop
            System.out.printf("| %-18d | %-15s | %-10s | %-8s | %-6.2f |%n",
                    c.getEnrollment_Number(), c.getName(), c.getDivision(), c.getGender(), c.getMarks());
            System.out.println("+-------------------------------------------------------------------+");
        }

    }
}

