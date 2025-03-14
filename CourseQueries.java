
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
public class CourseQueries {
    private static Connection connection;
    private static PreparedStatement addCourse;
    private static PreparedStatement getAllCourseCode;
    private static ResultSet resultSet;
    
    public static void addCourse(CourseEntry course){
        connection = DBConnection.getConnection();
        try{
            addCourse = connection.prepareStatement("insert into app.course" +"(coursecode,Description)"
            +"values (?,?)");
            addCourse.setString(1, course.getCourseCode());
            addCourse.setString(2,course.getDescription());
            addCourse.executeUpdate();
            
        
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<String> getAllCourseCodes(){
        connection = DBConnection.getConnection();
        ArrayList<String> coursecode = new ArrayList<String>();
        try
        {
            getAllCourseCode = connection.prepareStatement("select coursecode from app.course");
            resultSet = getAllCourseCode.executeQuery();
            
            while(resultSet.next())
            {
                coursecode.add(resultSet.getString(1));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return coursecode
                ;
    
    
    
    }




}
