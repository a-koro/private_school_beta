/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package printMethods;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;
import scannerMethods.ScannerMethods;

/**
 *
 * @author korov
 */
public class PrintMethods {
    public static void printStudents(ArrayList<Student> students) {
        System.out.println(String.format("%-14s |%-18s |%-18s |%-13s  |%s" , "Student ID", "First name", "Last name", "Birthday", "Tuition fees"));
        System.out.println("---------------+-------------------+-------------------+---------------+-------------");
        students.forEach((student) -> {
            System.out.println(String.format("%-14s |%-18s |%-18s |%-13s  |%s" , student.getStudentId(), student.getStudentFirstName(), student.getStudentLastName(), ScannerMethods.LocalDateFormatter(student.getDateOfBirth()), student.getTuitionFees()));
        });
    }
    
    public static void printCourses(ArrayList<Course> courses) {
        System.out.println(String.format("%-14s |%-20s |%-18s |%-18s |%-12s  |%s", "Course ID", "Title", "Stream", "Type", "Start date", "End date"));
        System.out.println("---------------+---------------------+-------------------+-------------------+--------------+--------------");
        courses.forEach((course) -> {
            System.out.println(String.format("%-14s |%-20s |%-18s |%-18s |%-12s  |%s", course.getCourseId(), course.getTitle(), course.getStream(), course.getType(), ScannerMethods.LocalDateFormatter(course.getStartDate()), ScannerMethods.LocalDateFormatter(course.getEndDate())));
        });
    }
    
    public static void printAssignments(ArrayList<Assignment> assignments) {
        System.out.println(String.format("%-14s |%-26s |%-18s |%-18s |%s" , "Assignment ID", "Title", "Max oral mark", "Max total mark", "Description"));
        System.out.println("---------------+---------------------------+-------------------+-------------------+----------------------------------------");
        assignments.forEach((assignment) -> {
            System.out.println(String.format("%-14s |%-26s |%-18s |%-18s |%s" , assignment.getAssignmentId(), assignment.getAssignmentTitle(), assignment.getMaxOralMark(), assignment.getMaxTotalMark(), assignment.getDescription()));
        });
    }
    
    public static void printTrainers(ArrayList<Trainer> trainers) {
        System.out.println(String.format("%-14s |%-18s |%-18s |%s" , "Trainer ID", "First name", "Last name", "Subject"));
        System.out.println("---------------+-------------------+-------------------+----------------------------");
        trainers.forEach((trainer) -> {
            System.out.println(String.format("%-14s |%-18s |%-18s |%s" , trainer.getTrainerId(), trainer.getTrainerFirstName(), trainer.getTrainerLastName(), trainer.getSubject()));
        });
    }
    
    public static void printAssignmentsPerStudent(ArrayList<Assignment> assignments) {
        System.out.println(String.format("%-14s |%-26s |%-13s |%-13s |%s" , "Assignment ID", "Title", "Oral mark", "Total mark", "Description"));
        System.out.println("---------------+---------------------------+--------------+--------------+----------------------------------------");
        assignments.forEach((assignment) -> {
            System.out.println(String.format("%-14s |%-26s |%-13s |%-13s |%s" , assignment.getAssignmentId(), assignment.getAssignmentTitle(), assignment.getMaxOralMark(), assignment.getMaxTotalMark(), assignment.getDescription()));
        });
    }
    
    public static void printAssignmentsAndSubDate(Map<Assignment, LocalDateTime> assignmentsMap) {
        System.out.println(String.format("%-14s |%-26s |%-13s |%-13s |%-25s |%s" , "Assignment ID", "Title", "Oral mark", "Total mark", "Submition Date and Time", "Description"));
        System.out.println("---------------+---------------------------+--------------+--------------+--------------------------+----------------------------------------");
        assignmentsMap.forEach((assignment, datetime) -> {
            System.out.println(String.format("%-14s |%-26s |%-13s |%-13s |%-25s |%38.38s..." , assignment.getAssignmentId(), assignment.getAssignmentTitle(), assignment.getMaxOralMark(), assignment.getMaxTotalMark(), ScannerMethods.LocalDateTimeFormatter(datetime), assignment.getDescription()));
        });
    }
    
    public static void printAssignmentsAndSubDateWithMaxMarks(Map<Assignment, LocalDateTime> assignmentsMap) {
        System.out.println(String.format("%-14s |%-26s |%-17s |%-17s |%-25s |%s" , "Assignment ID", "Title", "Max Oral mark", "Max Total mark", "Submition Date and Time", "Description"));
        System.out.println("---------------+---------------------------+------------------+------------------+--------------------------+----------------------------------------");
        assignmentsMap.forEach((assignment, datetime) -> {
            System.out.println(String.format("%-14s |%-26s |%-17s |%-17s |%-25s |%38.38s..." , assignment.getAssignmentId(), assignment.getAssignmentTitle(), assignment.getMaxOralMark(), assignment.getMaxTotalMark(), ScannerMethods.LocalDateTimeFormatter(datetime), assignment.getDescription()));
        });
    }
}
