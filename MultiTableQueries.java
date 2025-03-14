import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author jackielian
 */
public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescriptions;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static ResultSet resultSet;
    
    
    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester){
         connection= DBConnection.getConnection();
         ArrayList<ClassDescription> class_description = new ArrayList<ClassDescription>();
         
         try{
             getAllClassDescriptions = connection.prepareStatement("select app.class.courseCode, description, seats from app.class, app.course where semester = ? and app.class.courseCode = app.course.courseCode order by app.class.courseCode");
             getAllClassDescriptions.setString(1,semester);
             resultSet = getAllClassDescriptions.executeQuery();
         
         
             while(resultSet.next())
            {
                class_description.add(new ClassDescription(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
         
         
         }
    
         catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return class_description;
    
 
    }
    
    
    public static ArrayList<StudentEntry>getScheduledStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> scheduled_student_byclass = new ArrayList<StudentEntry>();
        
        try{
            getScheduledStudentsByClass = connection.prepareStatement("select app.student.studentid,firstname,lastname from app.schedule, app.student where status = 's' and semester=? and coursecode = ? and app.student.studentid = app.schedule.studentid  ");
            getScheduledStudentsByClass.setString(1, semester);
            getScheduledStudentsByClass.setString(2, courseCode);
            resultSet = getScheduledStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                scheduled_student_byclass.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        
        
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduled_student_byclass;
    
    
    
    
    
    }
    
    
    
    public static ArrayList<StudentEntry>getWaitlistedStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> waitlist_student_byClass = new ArrayList<StudentEntry>();
        
        
        try{
            getWaitlistedStudentsByClass = connection.prepareStatement("select app.student.studentid,firstname,lastname from app.schedule, app.student where status = 'w' and semester=? and coursecode = ? and app.student.studentid = app.schedule.studentid ");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2, courseCode);
            resultSet = getWaitlistedStudentsByClass.executeQuery();
        
            while(resultSet.next())
            {
                waitlist_student_byClass.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        
        
        }
    
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist_student_byClass;
    
    
    
    
    
    }
    
    
    
}
