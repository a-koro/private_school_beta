/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;
import printMethods.PrintMethods;
import scannerMethods.ScannerMethods;
import sqlQueryMethods.SelectMethods;

/**
 *
 * @author korov
 */
public class PrintSubMenu {

    public static void printSubMenu() {
        System.out.println("____________________________________PRIVATE SCHOOL MENU____________________________________");
        System.out.println("1.  Print courses list");
        System.out.println("2.  Print trainers list");
        System.out.println("3.  Print students list");
        System.out.println("4.  Print assignments list");
        System.out.println("5.  Print students per course (With submenu)");
        System.out.println("6.  Print students per course (Only executes query as the assignment require)");
        System.out.println("7.  Print trainers per course (With submenu)");
        System.out.println("8.  Print trainers per course (Only executes query as the assignment require)");
        System.out.println("9.  Print assignments per course (With submenu)");
        System.out.println("10. Print assignments per course (Only executes query as the assignment require)");
        System.out.println("11. Print assignments per course per student (With submenu)");
        System.out.println("12. Print assignments per course per student (Only executes query as the assignment require)");
        System.out.println("13. Print students that belong to more than one course");
        System.out.println("14. Return to main menu");
        System.out.println("___________________________________________________________________________________________");
        
        int userInput = ScannerMethods.scannerIntMethod();
        
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        ArrayList<Assignment> assignments = new ArrayList<>();
        ArrayList<Trainer> trainers = new ArrayList<>();
        Map<Assignment, LocalDateTime> assignmentsMap = new HashMap<>();
        int courseId;
        boolean courseExists;
        
        switch (userInput) {
            case 1:
                //PRINT COURSES LIST
                courses = SelectMethods.selectAllCoursesToAList();
                PrintMethods.printCourses(courses);
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 2:
                //PRINT TRAINERS LIST
                trainers = SelectMethods.selectAllTrainersToAList();
                PrintMethods.printTrainers(trainers);
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 3:
                //PRINT STUDENTS LIST
                students = SelectMethods.selectAllStudentsToALists();
                PrintMethods.printStudents(students);
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 4:
                //PRINT ASSIGNMENTS LIST
                assignments = SelectMethods.selectAllAssignmentsToAList();
                PrintMethods.printAssignments(assignments);
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 5:
                //Print students per course
                courses = SelectMethods.selectAllCoursesToAList();
                PrintMethods.printCourses(courses);
                courseExists = false;
                do {
                    System.out.print("Enter course ID to print its students: ");
                    courseId = ScannerMethods.scannerInt();
                    for (Course course : courses) {
                        if (course.getCourseId() == courseId) {
                            courseExists = true;
                            break;
                        }
                    }
                    if (!courseExists) {
                        System.out.println("This course does not exist.");
                    }
                } while (!courseExists);
                students = SelectMethods.selectStudentsPerCourse(courseId);
                if (students.isEmpty()) {
                    System.out.println("This course doesn't have any students");
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                else {
                    PrintMethods.printStudents(students);
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                break;
            case 6:
                //Print students per course
                SelectMethods.selectAllStudentsPerCourse();
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 7:
                //PRINT TRAINERS PER COURSE
                courses = SelectMethods.selectAllCoursesToAList();
                PrintMethods.printCourses(courses);
                courseExists = false;
                do {
                    System.out.print("Enter course ID to print its trainers: ");
                    courseId = ScannerMethods.scannerInt();
                    for (Course course : courses) {
                        if (course.getCourseId() == courseId) {
                            courseExists = true;
                            break;
                        }
                    }
                    if (!courseExists) {
                        System.out.println("This course does not exist.");
                    }
                } while (!courseExists);
                trainers = SelectMethods.selectTrainersPerCourse(courseId);
                if (trainers.isEmpty()) {
                    System.out.println("This course doesn't have any trainers");
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                else {
                    PrintMethods.printTrainers(trainers);
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                break;
            case 8:
                //PRINT TRAINERS PER COURSE
                SelectMethods.selectAllTrainersPerCourse();
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 9:
                //PRINT ASSIGNMENTS PER COURSE
                courses = SelectMethods.selectAllCoursesToAList();
                courseExists = false;
                PrintMethods.printCourses(courses);
                do {
                    System.out.print("Enter course ID you want to print its assignments: ");
                    courseId = ScannerMethods.scannerInt();
                    for (Course course : courses) {
                        if (course.getCourseId() == courseId) {
                            courseExists = true;
                            break;
                        }
                    }
                    if (!courseExists) {
                        System.out.println("This course does not exist.");
                    }
                } while (!courseExists);
                assignmentsMap = SelectMethods.selectAssignmentsWhereCourseId(courseId);
                if (assignmentsMap.isEmpty()) {
                    System.out.println("This course doesn't have any assignments.");
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                else {
                    PrintMethods.printAssignmentsAndSubDateWithMaxMarks(assignmentsMap);
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                break;
            case 10:
                //PRINT ASSIGNMENTS PER COURSE
                SelectMethods.selectAllAssignmentsPerCourse();
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 11:
                //PRINT ASSIGNMENTS PER COURSE PER STUDENT
                //First you choose the student then you choose a course and lastly you get a list of all the assignments
                students = new ArrayList<>(SelectMethods.selectAllStudentsToALists());
                PrintMethods.printStudents(students);
                boolean studentExists = false;
                courseExists = false;
                int studentId;
                do {
                    System.out.print("Select a student by ID: ");
                    studentId = ScannerMethods.scannerInt();
                    for (Student student : students) {
                        if (student.getStudentId() == studentId) {
                            studentExists = true;
                            break;
                        }
                    }
                    if (!studentExists) {
                        System.out.println("This student does not exist.");
                    }
                } while (!studentExists);
                courses = new ArrayList<>(SelectMethods.selectCoursesWhereStudentId(studentId));
                if (courses.isEmpty()) {
                    System.out.println("Student doesn't attend any courses");
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                else {
                    PrintMethods.printCourses(courses);
                }
                do {
                    System.out.print("Enter course ID (Only the courses the student attends to are shown): ");
                    courseId = ScannerMethods.scannerInt();
                    for (Course course : courses) {
                        if (course.getCourseId() == courseId) {
                            courseExists = true;
                            break;
                        }
                    }
                    if (!courseExists) {
                        System.out.println("This course does not exist.");
                    }
                } while (!courseExists);
                assignmentsMap = SelectMethods.selectAssignmentsWhereStudentIdAndCourseId(studentId, courseId);
                if (assignmentsMap.isEmpty()) {
                    System.out.println("Student hasn't any assignments from this course.");
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                else {
                    PrintMethods.printAssignmentsAndSubDate(assignmentsMap);
                    ScannerMethods.enterToContinue();
                    printSubMenu();
                }
                break;
            case 12:
                //PRINT ASSIGNMENTS PER COURSE PER STUDENT
                SelectMethods.selectAllAssignmentsPerStudentPerCourse();
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 13:
                //PRINT STUDENTS THAT ATTEND MORE THAN ONE COURSE
                Map<Student, Integer> studentsMap = new HashMap<>(SelectMethods.selectStudentsWithMoreThanOneCourse());
                System.out.println(String.format("%-4s |%-18s |%-18s |%-12s |%-14s |%s" , "ID", "First name", "Last name", "Birthday", "Tuition fees", "Courses attending"));
                System.out.println("-----+-------------------+-------------------+-------------+---------------+------------------");
                studentsMap.forEach((student, count) -> {
                    System.out.println(String.format("%-4s |%-18s |%-18s |%-12s |%-14s |%s", student.getStudentId(), student.getStudentFirstName(), student.getStudentLastName(), student.getDateOfBirth(), student.getTuitionFees(), count));
                });
                ScannerMethods.enterToContinue();
                printSubMenu();
                break;
            case 14:
                MainMenu.mainMenu();
                break;
            default:
                printSubMenu();
                break;
        }
    }
}
