package McaProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CollegeService {
    public static void main(String[] args) throws SQLException {

     CollegeController cc=new CollegeController();
        String url="jdbc:mysql://localhost:3306/user";

        String user="root";
        String pass="root";
        Connection conn=cc.connectDB(url,user,pass);
        Scanner sc=new Scanner(System.in);
        Scanner sc1=new Scanner(System.in);
        String repeat;
        do{
            System.out.println("Enter 1 to create table : ");
            System.out.println("Enter 2 to insert data : ");
            System.out.println("Enter 3 to print specific student data : ");
            System.out.println("Enter 4 to calculate grade of student by using name  : ");
            System.out.println("Enter 5 to find student by gender : ");
            System.out.println("Enter 6 to add bonus marks to specific student : ");
            System.out.println("Enter 7 to change division of a student : ");
            System.out.println("Enter 8 to  print student in given range : ");
            System.out.println("Enter 9 to print table ");
            System.out.println("------------------------------------------------");

            int choice=sc.nextInt();

            switch(choice)
            {
                case 1:{
                    System.out.println("Enter table name");
                    String tableName=sc.next();
                    cc.createStudent(conn,tableName);
                    break;
                }
                case 2:{
                    System.out.println("Enter Student Name : ");
                    String name=sc.next();
                    System.out.println("Enter Student Division : ");
                    String div=sc.next();
                    System.out.println("Enter Student Gender(F/M) : ");
                    String gender=sc.next();
                    String gen=String.valueOf(gender.charAt(0));
                    System.out.println("Enter Student Marks : ");
                    double marks=sc.nextDouble();
                    cc.insertData(conn,name,div,gen,marks);
                    break;
                }
                case 3:{
                    System.out.println("Enter Enrollment Number : ");
                    int enroll=sc.nextInt();
                        cc.printSpecificStudent(conn,enroll);
                    break;
                }
                case 4 :{
                    System.out.println("Enter Student Name: ");
                    String stuName=sc.next();
                    cc.grade(conn,stuName);
                    break;
                }
                case 5:{
                    System.out.println("Enter Gender F/M");
                    String gender=String.valueOf(sc.next().charAt(0));
                    cc.findByGender(conn,gender);
                    break;
                }
                case 6:{
                    System.out.println("Enter Enrollment Number : ");
                    int enroll=sc.nextInt();
                    System.out.println("Enter Bonus marks : ");
                    double bonus=sc.nextDouble();
                    cc.bonusMarks(conn,enroll,bonus);
                    break;
                }
                case 7:{
                    System.out.println("Enter Name :");
                    String stuName=sc.next();
                    System.out.println("Enter New Division : ");
                    String div=sc.next();
                    cc.changeDivision(conn,stuName,div);
                    break;
                }
                case 8 : {
                    System.out.println("Enter Min Marks : ");
                    double min=sc.nextDouble();
                    System.out.println("Enter Max Marks : ");
                    double max=sc.nextDouble();

                    cc.range(conn,min,max);
                    break;
                }
                case 9:
                {
                    cc.printTable(conn);
                    break;
                }
                default:
                    System.out.println("You enter invalid choice ");
            }
            System.out.println("Press Y to repeat again : ");
             repeat=sc.next();
        }while(repeat.equalsIgnoreCase("y"));
    }
}