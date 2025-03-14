
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
public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static PreparedStatement getScheduledStudentCount;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester,coursecode,studentid,status,timestamp) values (?,?,?,?,?)");
            addScheduleEntry.setString(1,entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3,entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5,entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        
        }
    
    
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    
    
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try{
            getScheduleByStudent = connection.prepareStatement("select semester,coursecode,studentid,status,timestamp from app.schedule where semester = (?) and studentid =(?)");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2,studentID);
            
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                schedule.add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        
        
        
        
        
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
    
    
    }
    
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode){
        connection = DBConnection.getConnection();
        int schedule_count = 0;
        try{
            getScheduledStudentCount=connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and courseCode = ?");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
//            getScheduleByStudent.executeUpdate();
            resultSet = getScheduledStudentCount.executeQuery();
        
            while(resultSet.next()){
                schedule_count = resultSet.getInt(1);
//                schedule_count+=1;
            }
        
        
        
        }
          
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule_count;
    
    
    }
    
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> waitlist_student = new ArrayList<ScheduleEntry>();
        try{
            getWaitlistedStudentsByClass = connection.prepareStatement("select semester,coursecode,studentid,status,timestamp from app.schedule where status = 'w' and semester=? and coursecode = ?  ");
            getWaitlistedStudentsByClass.setString(1,semester);
            getWaitlistedStudentsByClass.setString(2,courseCode);
            
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next())
            {
                waitlist_student.add(new ScheduleEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getTimestamp(5)));
            }
        
        
        
        
        
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return waitlist_student;
    
    
    
}
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester =? and studentid =? and coursecode =?");
            dropStudentScheduleByCourse.setString(1,semester);
            dropStudentScheduleByCourse.setString(2,studentID);
            dropStudentScheduleByCourse.setString(3,courseCode);
            
            dropStudentScheduleByCourse.executeUpdate();
            
            
           
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        
        
        
        
        }
    
    
    
    
    
    
    }
    
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try{
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ?");
            dropScheduleByCourse.setString(1,semester);
            dropScheduleByCourse.setString(2,courseCode);
            dropScheduleByCourse.executeUpdate();
        
        
        
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        
        
        
        
        }
    
    
    
    
    
    
    
    }
    
    public static void updateScheduleEntry(ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try{
            updateScheduleEntry = connection.prepareStatement("UPDATE app.schedule SET status = 's' WHERE semester = ? AND studentid = ? AND coursecode = ?");
            updateScheduleEntry.setString(1,entry.getSemester());
            updateScheduleEntry.setString(3, entry.getCourseCode());
            updateScheduleEntry.setString(2,entry.getStudentID());
            
            updateScheduleEntry.executeUpdate();
        
        
        
        
        }
        catch(SQLException sqlException)
        {
        sqlException.printStackTrace();
        
        
        
        }
    
    
    
    
    }
    







}