/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import models.Assignment;
import models.Course;
import models.Student;
import models.Trainer;
import printMethods.PrintMethods;
import scannerMethods.ScannerMethods;
import sqlQueryMethods.CheckBeforeInsertMethods;
import sqlQueryMethods.SelectMethods;
import sqlQueryMethods.UpdateMethods;

/**
 *
 * @author korov
 */
public class MainMenu {
    public static void mainMenu(){
        System.out.println("____________________________________PRIVATE SCHOOL MENU____________________________________");
        System.out.println("1.  Print lists");
        System.out.println("2.  Add course");
        System.out.println("3.  Add trainer");
        System.out.println("4.  Add student");
        System.out.println("5.  Add assignment");
        System.out.println("6.  Edit student assignment marks");
        System.out.println("7.  Connect students to courses");
        System.out.println("8.  Connect assignments to courses");
        System.out.println("9.  Connect trainers to courses");
        System.out.println("10. Connect assignments to students (Assignments from courses the student attends)");
        System.out.println("11. Exit");
        System.out.println("___________________________________________________________________________________________");
        
        int userInput = ScannerMethods.scannerIntMethod();
        
        //Declaring variables to use inside switch case
        int courseId;
        int assignmentId;
        int studentId;
        int result;
        boolean courseExists;
        boolean studentExists;
        boolean assignmentExists;
        ArrayList<Course> courses = null;
        ArrayList<Student> students = null;
        ArrayList<Assignment> assignments = null;
        
        switch (userInput) {
            case 1:
                PrintSubMenu.printSubMenu();
                break;
            case 2:
                //INSERT COURSE
                System.out.print("Title: ");
                String courseTitle = ScannerMethods.scannerStringWithNumbers();
                System.out.print("Stream (ex. Java/C#/Javascript): ");
                String stream = ScannerMethods.scannerStringWithNumbers();
                System.out.print("Type (ex. Full-time/Part-time): ");
                String type = ScannerMethods.scannerStringWithNumbers();
                System.out.print("Start date (ex. 31/12/1999): ");
                LocalDate startDate = ScannerMethods.scannerLocalDate();
                System.out.print("End date (ex. 31/12/1999): ");
                LocalDate endDate = ScannerMethods.scannerLocalDate4ValidatingEndDate(startDate);
                //Checks if course already exists
                if (CheckBeforeInsertMethods.check4DuplicateCourse(courseTitle, stream, type) == 1) {
                    System.out.println("Course already exists.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertCourse(courseTitle, stream, type, Date.valueOf(startDate), Date.valueOf(endDate));
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Course inserted successfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 3:
                //INSERT TRAINER
                System.out.print("First name: ");
                String trainerFirstName = ScannerMethods.scannerString();
                System.out.print("Last name: ");
                String trainerLastName = ScannerMethods.scannerString();
                System.out.print("Subject: ");
                String subject = ScannerMethods.scannerString();
                //Checks if trainer already exists
                if (CheckBeforeInsertMethods.check4DuplicateTrainer(trainerFirstName, trainerLastName, subject) == 1) {
                    System.out.println("Trainer already exists.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertTrainer(trainerFirstName, trainerLastName, subject);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Trainer inserted successfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 4:
                //INSERT STUDENT
                System.out.print("First name: ");
                String studentFirstName = ScannerMethods.scannerString();
                System.out.print("Last name: ");
                String studentLastName = ScannerMethods.scannerString();
                System.out.print("Birthday (ex. 31/12/1999): ");
                LocalDate dateOfBirth = ScannerMethods.scannerLocalDate();
                System.out.print("Tuition fees: ");
                int tuitionFees = ScannerMethods.scannerInt();
                //Checks if student already exists
                if (CheckBeforeInsertMethods.check4DuplicateStudent(studentFirstName, studentLastName, Date.valueOf(dateOfBirth)) == 1) {
                    System.out.println("Student already exists.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertStudent(studentFirstName, studentLastName, Date.valueOf(dateOfBirth), tuitionFees);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Student inserted successfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 5:
                //INSERT ASSIGNMENT
                System.out.print("Title: ");
                String assignmentTitle = ScannerMethods.scannerStringWithNumbers();
                System.out.print("Description: ");
                String description = ScannerMethods.scannerStringWithNumbers();
                System.out.print("Max oral mark: ");
                int maxOralMark = ScannerMethods.scannerInt();
                System.out.print("Max total mark: ");
                int maxTotalMark = ScannerMethods.scannerInt();
                //Check if assignment already exists by its title
                if (CheckBeforeInsertMethods.check4DuplicateAssignment(assignmentTitle) == 1) {
                    System.out.println("Assignment already exists.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertAssignment(assignmentTitle, description, maxOralMark, maxTotalMark);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Assignment inserted successfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 6:
                //EDIT STUDENT MARKS
                students = new ArrayList<>(SelectMethods.selectAllStudentsToALists());
                assignmentExists = false;
                studentExists = false;
                PrintMethods.printStudents(students);
                do {
                    System.out.print("Enter student ID to edit marks: ");
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
                assignments = new ArrayList<>(SelectMethods.selectAssignmentsWhereStudentId(studentId));
                //Checks if stident hasnt any assignments
                if (assignments.isEmpty()) {
                    System.out.println("This student does not have any assignments.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    PrintMethods.printAssignmentsPerStudent(assignments);
                    do {
                        System.out.print("Enter assignment ID to edit the marks: ");
                        assignmentId = ScannerMethods.scannerInt();
                        for (Assignment assignment : assignments) {
                            if (assignment.getAssignmentId() == assignmentId) {
                                assignmentExists = true;
                                break;
                            }
                        }
                        if (!assignmentExists) {
                            System.out.println("This assignment does not exist.");
                        }
                    } while (!assignmentExists);
                    
                    System.out.print("Enter oral mark: ");
                    int oralMark = ScannerMethods.scannerInt();
                    System.out.print("Enter total mark: ");
                    int totalMark = ScannerMethods.scannerInt();
                    result = UpdateMethods.updateAssignmentStudentMarks(oralMark, totalMark, assignmentId, studentId);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Marks updated succesfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 7:
                //ASSIGN STUDENT TO A COURSE
                students = new ArrayList<>(SelectMethods.selectAllStudentsToALists());
                studentExists = false;
                courseExists = false;
                PrintMethods.printStudents(students);
                do {
                    System.out.print("Enter student ID to connect to a course: ");
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
                courses = new ArrayList<>(SelectMethods.selectAllCoursesToAList());
                PrintMethods.printCourses(courses);
                do {
                    System.out.print("Enter course ID to connect student with ID " + studentId + ": ");
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
                
                if (CheckBeforeInsertMethods.check4DuplicateStudentCourse(studentId, courseId) == 1) {
                    System.out.println("Student already attended this course.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertStudentCourse(studentId, courseId);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Record inserted succesfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 8:
                //CONNECT ASSIGNMENTS TO COURSES
                assignmentExists = false;
                courseExists = false;
                
                courses = new ArrayList<>(SelectMethods.selectAllCoursesToAList());
                PrintMethods.printCourses(courses);
                do {
                    System.out.print("Enter course ID to connect assignments to: ");
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
                
                assignments = new ArrayList<>(SelectMethods.selectAllAssignmentsToAList());
                PrintMethods.printAssignments(assignments);
                do {
                    System.out.print("Enter assignment ID to connect to course with ID " + courseId + ": ");
                    assignmentId = ScannerMethods.scannerInt();
                    for (Assignment assignment : assignments) {
                        if (assignment.getAssignmentId() == assignmentId) {
                            assignmentExists = true;
                            break;
                        }
                    }
                    if (!assignmentExists) {
                        System.out.println("This assignment does not exist.");
                    }
                } while (!assignmentExists);
                
                if (CheckBeforeInsertMethods.check4DuplicateAssignmentCourse(assignmentId, courseId) == 1) {
                    System.out.println("Assignment already connected to this course.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    //Take start and end date of course to check if assignment sub datetime is within this period
                    LocalDate courseStartDate = null;
                    LocalDate courseEndDate = null;
                    for (Course course : courses) {
                        if (course.getCourseId() == courseId) {
                            courseStartDate = course.getStartDate();
                            courseEndDate = course.getEndDate();
                        }
                    }
                    while(true) {
                        System.out.println("Enter date and time for submission after starting date and before last date of course.");
                        System.out.print("Start date: " + ScannerMethods.LocalDateFormatter(courseStartDate) + " End date: " + ScannerMethods.LocalDateFormatter(courseEndDate) + " (ex. 31/12/2020 20:00): ");
                        LocalDateTime subDateTime = ScannerMethods.scannerLocalDateTimeForAssignment();
                        if (subDateTime.toLocalDate().isAfter(courseStartDate) && subDateTime.toLocalDate().isBefore(courseEndDate)) {
                            result = UpdateMethods.insertAssignmentCourse(assignmentId, courseId, Timestamp.valueOf(subDateTime));
                            System.out.println("Rows affected: " + result);
                            if (result > 0) {
                                System.out.println("Record inserted succesfully");
                            }
                            else {
                                System.out.println("Something went wrong");
                            }
                            break;
                        }
                        else {
                            System.out.println("Submission date and time must be within course dates");
                        }
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 9:
                //CONNECT TRAINERS TO COURSES
                courseExists = false;
                courses = new ArrayList<>(SelectMethods.selectAllCoursesToAList());
                PrintMethods.printCourses(courses);
                do {
                    System.out.print("Enter course ID you want to add trainers: ");
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
                
                ArrayList<Trainer> trainers = new ArrayList<>(SelectMethods.selectAllTrainersToAList());
                boolean trainerExists = false;
                int trainerId;
                PrintMethods.printTrainers(trainers);
                do {
                    System.out.print("Enter trainer ID: ");
                    trainerId = ScannerMethods.scannerInt();
                    for (Trainer trainer : trainers) {
                        if (trainer.getTrainerId() == trainerId) {
                            trainerExists = true;
                            break;
                        }
                    }
                    if (!trainerExists) {
                        System.out.println("This trainer does not exist.");
                    }
                } while (!trainerExists);

                if (CheckBeforeInsertMethods.check4DuplicateTrainerCourse(trainerId, courseId) == 1) {
                    System.out.println("Trainer already connected to this course.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertTrainerCourse(trainerId, courseId);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Record inserted succesfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 10:
                //CONNECT ASSIGNMENTS TO STUDENTS
                students = new ArrayList<>(SelectMethods.selectAllStudentsToALists());
                studentExists = false;
                assignmentExists = false;
                PrintMethods.printStudents(students);
                do {
                    System.out.print("Enter student ID you want to give assignments to: ");
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

                courseExists = false;
                
                courses = new ArrayList<>(SelectMethods.selectCoursesWhereStudentId(studentId));
                if (courses.isEmpty()) {
                    System.out.println("This student doesn't attend any courses.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                PrintMethods.printCourses(courses);
                do {
                    System.out.print("Enter course ID to show its assignments (Only courses student attends are shown): ");
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
                assignments = new ArrayList<>(SelectMethods.selectAssignmentsPerCourse(courseId));
                //Check if list is empty
                if (assignments.isEmpty()) {
                    System.out.println("This course doesn't have any assignments.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    PrintMethods.printAssignments(assignments);
                }
                do {
                    System.out.print("Enter assignment ID to connect to student with ID " + studentId + " (Student can only take assignments of courses he/she attends): ");
                    assignmentId = ScannerMethods.scannerInt();
                    for (Assignment assignment : assignments) {
                        if (assignment.getAssignmentId() == assignmentId) {
                            assignmentExists = true;
                            break;
                        }
                    }
                    if (!assignmentExists) {
                        System.out.println("This assignment does not exist.");
                    }
                } while (!assignmentExists);
                //Check for duplicate
                if (CheckBeforeInsertMethods.check4DuplicatesAssignmentStudent(assignmentId, studentId) == 1) {
                    System.out.println("Assignment already connected to student.");
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                else {
                    result = UpdateMethods.insertAssignmentStudent(assignmentId, studentId);
                    System.out.println("Rows affected: " + result);
                    if (result > 0) {
                        System.out.println("Record inserted succesfully");
                    }
                    else {
                        System.out.println("Something went wrong");
                    }
                    ScannerMethods.enterToContinue();
                    mainMenu();
                }
                break;
            case 11:
                System.out.println("Good bye.");
                System.exit(0);
                break;
            default:
                mainMenu();
                break;
        }
    }
}
