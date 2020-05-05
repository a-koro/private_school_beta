/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scannerMethods;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author korov
 */
public class ScannerMethods {
    
        
    //This scanner method is an improved version of scannerIntMethod()
    //it does not accept empty input and prints a message for it.
    //And a limit parameter
    //Doesnt have use in this app
    public static int scannerInt(int limit) {
        Scanner sc = new Scanner(System.in);
        int output;
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("")) {
                System.out.println("Invalid input, try again.");
            } else {
                try {
                    output = Integer.parseInt(userInput);
                    if (output > limit) {
                        System.out.println("Invalid input, try again.");
                    }
                    else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, try again.");
                }
            }
        }
        return output;
    }
    
    public static int scannerIntMethod() {
        Scanner sc = new Scanner(System.in);
        //The statement inside the while loop runs an scanner user input
        //at the time it checks the conditional
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("Wrong input, try again.");
        }
        return sc.nextInt();
    }
    
    //A method to give the user time before continuing
    public static void enterToContinue() {
        System.out.println("\nPress \"ENTER\" to continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
    
    //LocalDate Scanner Method if input is empty method creates synthetic data and returns it
    public static LocalDate scannerLocalDate() {
        LocalDate date;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            try {
                date = LocalDate.parse(userInput, dateFormatter.withResolverStyle(ResolverStyle.STRICT));
                break;
            }
            catch(Exception e) {
                System.out.println("Invalid input, try again.");
            }
        }
        return date;
    }
    
    //Scanner method for strings does note accept empty input
    public static String scannerString() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String output;
        while(true){
            userInput = sc.nextLine();
            if (userInput.equals("")) {
                System.out.println("Invalid input, try again.");
            }
            else{
                if (userInput.matches("^[a-zA-Z\\s]*$")) {
                    output = userInput;
                    break;
                }
                else{
                    System.out.println("Invalid input, try again.");
                }
            }
        }
        return output;
    }
    
    //Scanner method for strings does note accept empty input
    //Input can be a-z A-Z and 0-9 also #,+,-.
    public static String scannerStringWithNumbers() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        String output;
        while(true){
            userInput = sc.nextLine();
            if (userInput.equals("")) {
                System.out.println("Invalid input, try again.");
            }
            else{
                if (userInput.matches("^[a-zA-Z0-9][a-zA-Z0-9\\#\\-\\s\\+]*$")) {
                    output = userInput;
                    break;
                }
                else{
                    System.out.println("Invalid input, try again.");
                }
            }
        }
        return output;
    }
    
    //This scanner method is an improved version of scannerIntMethod()
    //it does not accept empty input and prints a message for it.
    public static int scannerInt() {
        Scanner sc = new Scanner(System.in);
        int output;
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("")) {
                System.out.println("Invalid input, try again.");
            } else {
                try {
                    output = Integer.parseInt(userInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input, try again.");
                }
            }
        }
        return output;
    }
    
    //A method for local date objects for validating user input of end date of course
    public static LocalDate scannerLocalDate4ValidatingEndDate(LocalDate startDate) {
        LocalDate date;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (!userInput.equals("")) {
                try {
                    date = LocalDate.parse(userInput, dateFormatter.withResolverStyle(ResolverStyle.STRICT));
                    if (date.isAfter(startDate)) {
                        break;
                    }
                    else{
                        System.out.print("Enter a date after course starting date:");
                    }
                }
                catch(Exception e) {
                    System.out.println("Invalid input, try again.");
                }
            } else {
                System.out.println("Invalid input, try again.");
            }
        }
        return date;
    }
    
    //LocalDateTime scanner method
    public static LocalDateTime scannerLocalDateTimeForAssignment(){
        LocalDateTime date = null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (!userInput.equals("")) {
                try {
                    if (!LocalDate.parse(userInput, dateFormatter.withResolverStyle(ResolverStyle.STRICT)).getDayOfWeek().toString().equals("SATURDAY") && !LocalDate.parse(userInput, dateFormatter.withResolverStyle(ResolverStyle.STRICT)).getDayOfWeek().toString().equals("SUNDAY")) {
                        date = LocalDateTime.parse(userInput, dateFormatter.withResolverStyle(ResolverStyle.STRICT));
                        break;
                    }
                    else {
                        System.out.println("Assignment due dates are from Monday to Friday.\nEnter date and time for submition (ex. 31/12/2020 20:00): ");
                    }
                }
                catch(Exception e) {
                    System.out.println("Invalid input, try again.");
                }
            } else {
                System.out.println("Invalid input, try again.");
//                break;
            }
        }
        return date;
    }
    
    //LocalDateTime formatter method only used by Assignment Class
    public static String LocalDateTimeFormatter(LocalDateTime date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm");
        return date.format(dateFormatter);
    }
    
    //Formats LocalDate Objects and returns them as a string
    public static String LocalDateFormatter(LocalDate date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");
        return date.format(dateFormatter);
    }
}
