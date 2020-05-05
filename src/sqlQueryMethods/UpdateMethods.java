/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlQueryMethods;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author korov
 */
public class UpdateMethods {
    
    private static final String MYSQL_JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/private_school_structure?serverTimezone=UTC";
    private static final String USERNAME = "java_user";
    private static final String PASSWORD = "password";
    
    public static int insertStudent(String firstName, String lastName, Date dateOfBirth, int tuitionFees) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO STUDENTS VALUES(null, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setDate(3, dateOfBirth);
            preparedStatement.setInt(4, tuitionFees);
            result = preparedStatement.executeUpdate();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
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
    
    public static int insertTrainer(String firstName, String lastName, String subject) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO TRAINERS VALUES(null, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, subject);
            result = preparedStatement.executeUpdate();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
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
    
    public static int insertAssignment(String assignmentTitle, String description, int maxOralMark, int maxTotalMark) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO ASSIGNMENTS VALUES(null, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, assignmentTitle);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, maxOralMark);
            preparedStatement.setInt(4, maxTotalMark);
            result = preparedStatement.executeUpdate();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
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
    
    public static int insertCourse(String courseTitle, String stream, String type, Date startDate, Date endDate) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO COURSES VALUES(null, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, courseTitle);
            preparedStatement.setString(2, stream);
            preparedStatement.setString(3, type);
            preparedStatement.setDate(4, startDate);
            preparedStatement.setDate(5, endDate);
            result = preparedStatement.executeUpdate();
        } catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");
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
    
    public static int insertStudentCourse(int studentId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO STUDENT_COURSE VALUES(null, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, courseId);
            result = preparedStatement.executeUpdate();
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
    
    public static int insertAssignmentCourse(int assignmentId, int courseId, Timestamp subDateTime) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = 0;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO COURSE_ASSIGNMENT VALUES(null, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, assignmentId);
            preparedStatement.setInt(2, courseId);
            preparedStatement.setTimestamp(3, subDateTime);
            result = preparedStatement.executeUpdate();
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
    
    public static int insertTrainerCourse(int trainerId, int courseId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO COURSE_TRAINER VALUES(null, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, courseId);
            preparedStatement.setInt(2, trainerId);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
        return result;
    }
    
    public static int insertAssignmentStudent(int assignmentId, int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO STUDENT_ASSIGNMENT VALUES(null, ?, ?, null, null)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.setInt(2, assignmentId);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
        return result;
    }
    
    public static int updateAssignmentStudentMarks(int oralMark, int totalMark, int assignmentId, int studentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result = -1;
        
        try {
            Class.forName(MYSQL_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            String query = "UPDATE STUDENT_ASSIGNMENT "
                    + "SET ORAL_MARK = ?, TOTAL_MARK = ? "
                    + "WHERE STUDENT_ID = ? "
                    + "AND ASSIGNMENT_ID = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, oralMark);
            preparedStatement.setInt(2, totalMark);
            preparedStatement.setInt(3, studentId);
            preparedStatement.setInt(4, assignmentId);
            result = preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
        return result;
    }
}
