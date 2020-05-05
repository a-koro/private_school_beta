/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlQueryMethods;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;
import scannerMethods.ScannerMethods;
/**
 *
 * @author korov
 */
public class SelectMethods {
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/private_school_structure?serverTimezone=UTC";
    private static final String USERNAME = "java_user";
    private static final String PASSWORD = "password";
    
    public static void selectAllStudentsPerCourse() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME, C.COURSE_ID, C.TITLE, C.STREAM, C.COURSE_TYPE FROM STUDENTS S, STUDENT_COURSE SC, COURSES C "
                    + "WHERE S.STUDENT_ID = SC.STUDENT_ID AND SC.COURSE_ID = C.COURSE_ID ORDER BY S.STUDENT_ID";
            resultSet = statement.executeQuery(query);
            
            System.out.println(String.format("%-12s |%-17s |%-17s |%-12s |%-18s |%-18s |%s" , "Student ID", "First name", "Last name", "Course ID", "Title", "Stream", "Type"));
            System.out.println("-------------+------------------+------------------+-------------+-------------------+-------------------+-----------------------");
            while(resultSet.next()) {
                int studentId = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                int courseId = resultSet.getInt("COURSE_ID");
                String title = resultSet.getString("TITLE");
                String stream = resultSet.getString("STREAM");
                String courseType = resultSet.getString("COURSE_TYPE");
                System.out.println(String.format("%-12s |%-17s |%-17s |%-12s |%-18s |%-18s |%s" , studentId, firstName, lastName, courseId, title, stream, courseType));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void selectAllTrainersPerCourse() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT T.TRAINER_ID, T.FIRST_NAME, T.LAST_NAME, C.COURSE_ID, C.TITLE, C.STREAM, C.COURSE_TYPE FROM TRAINERS T, COURSE_TRAINER CT, COURSES C WHERE T.TRAINER_ID = CT.TRAINER_ID AND CT.COURSE_ID = C.COURSE_ID ORDER BY C.COURSE_ID";
            resultSet = statement.executeQuery(query);
            
            System.out.println(String.format("%-11s |%-18s |%-18s |%-18s |%-13s |%-18s |%s" ,"Course ID" ,"Title", "Stream", "Type", "Trainer ID", "First name", "Last name"));
            System.out.println("------------+-------------------+-------------------+-------------------+--------------+-------------------+-----------------");
            while(resultSet.next()) {
                int trainerId = resultSet.getInt("TRAINER_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                int courseId = resultSet.getInt("COURSE_ID");
                String title = resultSet.getString("TITLE");
                String stream = resultSet.getString("STREAM");
                String courseType = resultSet.getString("COURSE_TYPE");
                System.out.println(String.format("%-11s |%-18s |%-18s |%-18s |%-13s |%-18s |%s" ,courseId ,title, stream, courseType, trainerId, firstName, lastName));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void selectAllAssignmentsPerCourse() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT C.COURSE_ID, C.TITLE, C.STREAM, C.COURSE_TYPE, A.ASSIGNMENT_ID, A.TITLE, CA.SUB_DATETIME "
                    + "FROM COURSES C, COURSE_ASSIGNMENT CA, ASSIGNMENTS A "
                    + "WHERE C.COURSE_ID = CA.COURSE_ID "
                    + "AND CA.ASSIGNMENT_ID = A.ASSIGNMENT_ID "
                    + "ORDER BY C.COURSE_ID, A.ASSIGNMENT_ID";
            resultSet = statement.executeQuery(query);
            
            System.out.println(String.format("%-12s |%-15s |%-15s |%-15s |%-14s |%-25s |%s" ,"Course ID" ,"Title", "Stream", "Type", "Assignment ID", "Title", "SUBMISSION DATETIME"));
            System.out.println("-------------+----------------+----------------+----------------+---------------+--------------------------+--------------------------");
            while(resultSet.next()) {
                int courseId = resultSet.getInt("COURSE_ID");
                String title = resultSet.getString("C.TITLE");
                String stream = resultSet.getString("STREAM");
                String courseType = resultSet.getString("COURSE_TYPE");
                int assignmentId = resultSet.getInt("ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("A.TITLE");
                Timestamp subDateTime = resultSet.getTimestamp("SUB_DATETIME");
                System.out.println(String.format("%-12s |%-15s |%-15s |%-15s |%-14s |%-25s |%s" ,courseId ,title, stream, courseType, assignmentId, assignmentTitle, ScannerMethods.LocalDateTimeFormatter(subDateTime.toLocalDateTime())));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public static void selectAllAssignmentsPerStudentPerCourse() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT A.ASSIGNMENT_ID, A.TITLE, C.COURSE_ID, C.TITLE, C.STREAM, C.COURSE_TYPE, S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME "
                    + "FROM ASSIGNMENTS A, STUDENT_ASSIGNMENT SA, COURSES C, STUDENT_COURSE SC, STUDENTS S "
                    + "WHERE A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID AND SA.STUDENT_ID = S.STUDENT_ID AND C.COURSE_ID = SC.COURSE_ID AND SC.STUDENT_ID = S.STUDENT_ID";
            resultSet = statement.executeQuery(query);
            
            System.out.println(String.format("%-13s |%-27s |%-9s |%-15s |%-15s |%-15s |%-11s |%-17s |%s", "Assignment ID","Assignment Title","Course ID", "Course Title", "Stream", "Type" , "Student ID", "First name", "Last name"));
            System.out.println("--------------+----------------------------+----------+----------------+----------------+----------------+------------+------------------+------------------");
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("A.ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("A.TITLE");
                int courseId = resultSet.getInt("C.COURSE_ID");
                String title = resultSet.getString("C.TITLE");
                String stream = resultSet.getString("C.STREAM");
                String courseType = resultSet.getString("C.COURSE_TYPE");
                int studentId = resultSet.getInt("S.STUDENT_ID");
                String firstName = resultSet.getString("S.FIRST_NAME");
                String lastName = resultSet.getString("S.LAST_NAME");
                
                System.out.println(String.format("%-13s |%-27s |%-9s |%-15s |%-15s |%-15s |%-11s |%-17s |%s",assignmentId ,assignmentTitle, courseId, title, stream, courseType, studentId, firstName, lastName));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    //DEPRECATED METHOD
    public static void DEPRECATEDselectAllStudentsWithMoreThanOneCourse() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            statement = connection.createStatement();
            String query = "SELECT S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME, S.DATE_OF_BIRTH, S.TUITION_FEES, COUNT(S.STUDENT_ID) AS COURSES_ATTENDING " +
                           "FROM STUDENTS S, STUDENT_COURSE SC, COURSES C " +
                           "WHERE S.STUDENT_ID = SC.STUDENT_ID " +
                           "AND SC.COURSE_ID = C.COURSE_ID " +
                           "GROUP BY STUDENT_ID " +
                           "HAVING COUNT(C.COURSE_ID) > 1";
            resultSet = statement.executeQuery(query);
            
            System.out.println(String.format("%-14s |%-18s |%-18s |%-13s  |%-13s |%s" , "Student ID", "First name", "Last name", "Birthday", "Tuition fees", "Courses attending"));
            System.out.println("---------------+-------------------+-------------------+---------------+--------------+----------------------");
            while(resultSet.next()) {
                int studentId = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String dateOfBirth = resultSet.getString("DATE_OF_BIRTH");
                int tuitionFees = resultSet.getInt("TUITION_FEES");
                int coursesAttending = resultSet.getInt("COURSES_ATTENDING");
                System.out.println(String.format("%-14s |%-18s |%-18s |%-13s  |%-13s |%s" , studentId, firstName, lastName, dateOfBirth, tuitionFees, coursesAttending));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    //Updates select method for students with more than one course it uses a Map<>
    //to return the results instead of printing the results straight from the function
    public static Map<Student, Integer> selectStudentsWithMoreThanOneCourse() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Student, Integer> students = new HashMap<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME, S.DATE_OF_BIRTH, S.TUITION_FEES, COUNT(SC.STUDENT_ID) AS COURSES_ATTENDING "
                    + "FROM STUDENTS S, STUDENT_COURSE SC "
                    + "WHERE S.STUDENT_ID = SC.STUDENT_ID "
                    + "GROUP BY SC.STUDENT_ID "
                    + "HAVING COUNT(SC.STUDENT_ID) >1 "
                    + "ORDER BY S.STUDENT_ID DESC";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int studentId = resultSet.getInt("STUDENT_ID");
                String studentFirstName = resultSet.getString("FIRST_NAME");
                String studentLastName = resultSet.getString("LAST_NAME");
                Date dateOfBirth = resultSet.getDate("DATE_OF_BIRTH");
                int tuitionFees = resultSet.getInt("TUITION_FEES");
                int coursesAttending = resultSet.getInt("COURSES_ATTENDING");
                
                Student student = new Student(studentId, studentFirstName, studentLastName, dateOfBirth.toLocalDate(), tuitionFees);
                students.put(student, coursesAttending);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }
    
    //A select method for all courses. It return the results in an arrayList
    public static ArrayList<Course> selectAllCoursesToAList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Course> courses = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM COURSES";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int courseId = resultSet.getInt("COURSE_ID");
                String courseTitle = resultSet.getString("TITLE");
                String stream = resultSet.getString("STREAM");
                String type = resultSet.getString("COURSE_TYPE");
                Date startDate = resultSet.getDate("START_DATE");
                Date endDate = resultSet.getDate("END_DATE");
                
                Course course = new Course(courseId, courseTitle, stream, type, startDate.toLocalDate(), endDate.toLocalDate());
                courses.add(course);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }
    
    //A select method for all students. It returns the results in an arrayList
    public static ArrayList<Student> selectAllStudentsToALists() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Student> students = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM STUDENTS";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int studentId = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                Date dateOfBirth = resultSet.getDate("DATE_OF_BIRTH");
                int tuitionFees = resultSet.getInt("TUITION_FEES");
                
                Student student = new Student(studentId, firstName, lastName, dateOfBirth.toLocalDate(), tuitionFees);
                students.add(student);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }
    
    //A select method for all assignments. It returns the results in an arrayList
    public static ArrayList<Assignment> selectAllAssignmentsToAList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Assignment> assignments = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM ASSIGNMENTS";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("TITLE");
                String description = resultSet.getString("ASSIGNMENT_DESCRIPTION");
                int maxOralMark = resultSet.getInt("MAX_ORAL_MARK");
                int maxTotalMark = resultSet.getInt("MAX_TOTAL_MARK");
                
                Assignment assignment = new Assignment(assignmentId, assignmentTitle, description, maxOralMark, maxTotalMark);
                assignments.add(assignment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignments;
    }
    
    //A select Method for all assignments. It returns the results in an arrayList
    public static ArrayList<Trainer> selectAllTrainersToAList() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Trainer> trainers = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM TRAINERS ORDER BY TRAINER_ID";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int trainerId = resultSet.getInt("TRAINER_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String trainerSubject = resultSet.getString("TRAINER_SUBJECT");
                
                Trainer trainer = new Trainer(trainerId, firstName, lastName, trainerSubject);
                trainers.add(trainer);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return trainers;
    }
    
    //This method is only used to connect assignments to students
    //It prints only assignments that are connected to the courses the student already attends
    public static ArrayList<Assignment> selectAllAssignmentsPerCoursePerStudent(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Assignment> assignments = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT A.ASSIGNMENT_ID, A.TITLE, A.ASSIGNMENT_DESCRIPTION, A.MAX_ORAL_MARK, A.MAX_TOTAL_MARK "
                    + "FROM STUDENTS S, STUDENT_COURSE SC, COURSES C, COURSE_ASSIGNMENT CA, ASSIGNMENTS A "
                    + "WHERE S.STUDENT_ID = SC.STUDENT_ID "
                    + "AND SC.COURSE_ID = C.COURSE_ID "
                    + "AND C.COURSE_ID = CA.COURSE_ID "
                    + "AND CA.ASSIGNMENT_ID = A.ASSIGNMENT_ID "
                    + "AND S.STUDENT_ID = ? "
                    + "ORDER BY A.ASSIGNMENT_ID";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("TITLE");
                String description = resultSet.getString("ASSIGNMENT_DESCRIPTION");
                int maxOralMark = resultSet.getInt("MAX_ORAL_MARK");
                int maxTotalMark = resultSet.getInt("MAX_TOTAL_MARK");
                
                Assignment assignment = new Assignment(assignmentId, assignmentTitle, description, maxOralMark, maxTotalMark);
                assignments.add(assignment);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignments;
    }
    
    //This method is only used for edditing student marks
    //returns the assignments the has.
    public static ArrayList<Assignment> selectAssignmentsWhereStudentId(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Assignment> assignments = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT A.ASSIGNMENT_ID, A.TITLE, A.ASSIGNMENT_DESCRIPTION, SA.ORAL_MARK, SA.TOTAL_MARK "
                    + "FROM ASSIGNMENTS A, STUDENT_ASSIGNMENT SA, STUDENTS S "
                    + "WHERE A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID "
                    + "AND SA.STUDENT_ID = S.STUDENT_ID "
                    + "AND S.STUDENT_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("TITLE");
                String description = resultSet.getString("ASSIGNMENT_DESCRIPTION");
                int oralMark = resultSet.getInt("ORAL_MARK");
                int totalMark = resultSet.getInt("TOTAL_MARK");
                
                Assignment assignment = new Assignment(assignmentId, assignmentTitle, description, oralMark, totalMark);
                assignments.add(assignment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignments;
    }
    
    //This method is only used for printing courses per student in submenu 8
    public static ArrayList<Course> selectCoursesWhereStudentId(int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Course> courses = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT C.COURSE_ID, C.TITLE, C.STREAM, C.COURSE_TYPE, C.START_DATE, C.END_DATE FROM STUDENT_COURSE SC, COURSES C "
                    + "WHERE SC.COURSE_ID = C.COURSE_ID "
                    + "AND SC.STUDENT_ID = ? "
                    + "ORDER BY C.COURSE_ID ASC";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int courseId = resultSet.getInt("COURSE_ID");
                String courseTitle = resultSet.getString("TITLE");
                String stream = resultSet.getString("STREAM");
                String type = resultSet.getString("COURSE_TYPE");
                Date startDate = resultSet.getDate("START_DATE");
                Date endDate = resultSet.getDate("END_DATE");
                
                Course course = new Course(courseId, courseTitle, stream, type, startDate.toLocalDate(), endDate.toLocalDate());
                courses.add(course);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }
    
    //This method is only used to return assignments per student per course
    //it is only used under submenu 8
    public static Map<Assignment, LocalDateTime> selectAssignmentsWhereStudentIdAndCourseId(int studentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Assignment, LocalDateTime> assignmentsMap = new HashMap<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT A.ASSIGNMENT_ID, A.TITLE, A.ASSIGNMENT_DESCRIPTION, SA.ORAL_MARK, SA.TOTAL_MARK, CA.SUB_DATETIME "
                    + "FROM COURSE_ASSIGNMENT CA, ASSIGNMENTS A, STUDENT_ASSIGNMENT SA, STUDENTS S "
                    + "WHERE A.ASSIGNMENT_ID = SA.ASSIGNMENT_ID "
                    + "AND SA.STUDENT_ID = S.STUDENT_ID "
                    + "AND CA.ASSIGNMENT_ID = A.ASSIGNMENT_ID "
                    + "AND CA.COURSE_ID = ? "
                    + "AND S.STUDENT_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, studentId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("TITLE");
                String description = resultSet.getString("ASSIGNMENT_DESCRIPTION");
                int oralMark = resultSet.getInt("ORAL_MARK");
                int totalMark = resultSet.getInt("TOTAL_MARK");
                Timestamp subDateTime = resultSet.getTimestamp("SUB_DATETIME");
                
                Assignment assignment = new Assignment(assignmentId, assignmentTitle, description, oralMark, totalMark);
                assignmentsMap.put(assignment, subDateTime.toLocalDateTime());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignmentsMap;
    }
    
    //This method is used for connecting assignments to sutdents under mainmenu.10
    public static ArrayList<Assignment> selectAssignmentsPerCourse(int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Assignment> assignments = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT A.ASSIGNMENT_ID, A.TITLE, A.ASSIGNMENT_DESCRIPTION, A.MAX_ORAL_MARK, A.MAX_TOTAL_MARK "
                    + "FROM COURSE_ASSIGNMENT CA, ASSIGNMENTS A "
                    + "WHERE CA.ASSIGNMENT_ID = A.ASSIGNMENT_ID "
                    + "AND CA.COURSE_ID = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("A.ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("A.TITLE");
                String description = resultSet.getString("A.ASSIGNMENT_DESCRIPTION");
                int maxOralMark = resultSet.getInt("A.MAX_ORAL_MARK");
                int maxTotalMark = resultSet.getInt("A.MAX_TOTAL_MARK");
                
                Assignment assignment = new Assignment(assignmentId, assignmentTitle, description, maxOralMark, maxTotalMark);
                assignments.add(assignment);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignments;
    }
    
    //This method returns a list of all the students who attend a specified course
    public static ArrayList<Student> selectStudentsPerCourse(int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Student> students = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT S.STUDENT_ID, S.FIRST_NAME, S.LAST_NAME, S.DATE_OF_BIRTH, S.TUITION_FEES "
                    + "FROM STUDENTS S, STUDENT_COURSE SC, COURSES C "
                    + "WHERE S.STUDENT_ID = SC.STUDENT_ID "
                    + "AND SC.COURSE_ID = C.COURSE_ID "
                    + "AND C.COURSE_ID = ? "
                    + "ORDER BY S.STUDENT_ID";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                int studentId = resultSet.getInt("STUDENT_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                Date dateOfBirth = resultSet.getDate("DATE_OF_BIRTH");
                int tuitionFees = resultSet.getInt("TUITION_FEES");
                
                Student student = new Student(studentId, firstName, lastName, dateOfBirth.toLocalDate(), tuitionFees);
                students.add(student);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return students;
    }
    
    //This method returns a list of all the trainers who teach at a specified course
    public static ArrayList<Trainer> selectTrainersPerCourse(int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Trainer> trainers = new ArrayList<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT T.TRAINER_ID, T.FIRST_NAME, T.LAST_NAME, T.TRAINER_SUBJECT "
                    + "FROM TRAINERS T, COURSE_TRAINER CT, COURSES C "
                    + "WHERE T.TRAINER_ID = CT.TRAINER_ID "
                    + "AND CT.COURSE_ID = C.COURSE_ID "
                    + "AND C.COURSE_ID = ? "
                    + "ORDER BY T.TRAINER_ID";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                int trainerId = resultSet.getInt("TRAINER_ID");
                String firstName = resultSet.getString("FIRST_NAME");
                String lastName = resultSet.getString("LAST_NAME");
                String subject = resultSet.getString("TRAINER_SUBJECT");
                
                Trainer trainer = new Trainer(trainerId, firstName, lastName, subject);
                trainers.add(trainer);
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return trainers;
    }
    
    //This method is use only in submenu under "assignments per course(with submenu)"
    //It prints assignments and also sub datetime
    public static Map<Assignment, LocalDateTime> selectAssignmentsWhereCourseId(int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Map<Assignment, LocalDateTime> assignmentsMap = new HashMap<>();
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT A.ASSIGNMENT_ID, A.TITLE, A.ASSIGNMENT_DESCRIPTION, A.MAX_ORAL_MARK, A.MAX_TOTAL_MARK, CA.SUB_DATETIME "
                    + "FROM COURSE_ASSIGNMENT CA, ASSIGNMENTS A "
                    + "WHERE CA.ASSIGNMENT_ID = A.ASSIGNMENT_ID "
                    + "AND CA.COURSE_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()) {
                int assignmentId = resultSet.getInt("ASSIGNMENT_ID");
                String assignmentTitle = resultSet.getString("TITLE");
                String description = resultSet.getString("ASSIGNMENT_DESCRIPTION");
                int maxOralMark = resultSet.getInt("MAX_ORAL_MARK");
                int maxTotalMark = resultSet.getInt("MAX_TOTAL_MARK");
                Timestamp subDateTime = resultSet.getTimestamp("SUB_DATETIME");
                
                Assignment assignment = new Assignment(assignmentId, assignmentTitle, description, maxOralMark, maxTotalMark);
                assignmentsMap.put(assignment, subDateTime.toLocalDateTime());
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if(resultSet != null) {
                try {
                    resultSet.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return assignmentsMap;
    }
}
