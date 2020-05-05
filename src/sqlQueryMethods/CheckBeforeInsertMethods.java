/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlQueryMethods;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author korov
 */
public class CheckBeforeInsertMethods {
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/private_school_structure?serverTimezone=UTC";
    private static final String USERNAME = "java_user";
    private static final String PASSWORD = "password";
    
    public static int check4DuplicateCourse(String courseTitle, String stream, String type) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT "
                    + "FROM COURSES "
                    + "WHERE TITLE = ? "
                    + "AND STREAM = ? "
                    + "AND COURSE_TYPE = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseTitle);
            preparedStatement.setString(2, stream);
            preparedStatement.setString(3, type);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static int check4DuplicateTrainer(String firstName, String lastName, String subject) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT "
                    + "FROM TRAINERS "
                    + "WHERE FIRST_NAME = ? "
                    + "AND LAST_NAME = ? "
                    + "AND TRAINER_SUBJECT = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, subject);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static int check4DuplicateStudent(String firstName, String lastName, Date dateOfBirth) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT "
                    + "FROM STUDENTS "
                    + "WHERE FIRST_NAME = ? "
                    + "AND LAST_NAME = ? "
                    + "AND DATE_OF_BIRTH = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, dateOfBirth);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    public static int check4DuplicateAssignment(String title) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT "
                    + "FROM ASSIGNMENTS "
                    + "WHERE TITLE = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    //This method checks for duplicate records before insert and returns
    //0 if record doesnt already exist and 1 if it already exists
    public static int check4DuplicateStudentCourse(int studentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT FROM STUDENT_COURSE SC WHERE SC.STUDENT_ID = ? AND SC.COURSE_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    //This method checks for duplicate records before insert and returns
    //0 if record doesnt already exist and 1 if it already exists
    public static int check4DuplicateAssignmentCourse(int assignmentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT FROM COURSE_ASSIGNMENT CA WHERE CA.ASSIGNMENT_ID = ? AND CA.COURSE_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, assignmentId);
            preparedStatement.setInt(2, courseId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    //This method checks for duplicate records before insert and returns
    //0 if record doesnt already exist and 1 if it already exists
    public static int check4DuplicateTrainerCourse(int trainerId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT FROM COURSE_TRAINER CT WHERE CT.TRAINER_ID = ? AND CT.COURSE_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, trainerId);
            preparedStatement.setInt(2, courseId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
    
    //This method checks for duplicate records before insert and returns
    //0 if record doesnt already exist and 1 if it already exists
    public static int check4DuplicatesAssignmentStudent(int assignmentId, int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int result = 0;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "SELECT COUNT(*) AS RESULT FROM STUDENT_ASSIGNMENT SA WHERE SA.ASSIGNMENT_ID = ? AND SA.STUDENT_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, assignmentId);
            preparedStatement.setInt(2, studentId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt("RESULT");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
