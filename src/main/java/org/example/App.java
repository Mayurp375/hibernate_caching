package org.example;

import org.example.connection.Student;
import org.example.connection.StudentDao;
import org.example.connection.UserDao;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserDao dao = new StudentDao();
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.println("==================MENU=================");
                System.out.println("1. Create a new user");
                System.out.println("2. See a user");
                System.out.println("3. See all the users");
                System.out.println("4. Update a user information");
                System.out.println("5. Delete a user");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.valueOf(input.readLine());

                switch(choice) {
                    case 1:
                    {
                        System.out.print("Enter the firstname: ");
                        String firstname = input.readLine().trim();
                        System.out.print("Enter the lastname: ");
                        int cousename = Integer.parseInt(input.readLine().trim());
                        System.out.println();
                        System.out.println("Dob format will be yyyy-mm-dd");
                        System.out.print("Enter the rollNumber: ");
                        String rollNumber = input.readLine().trim();
                        Student user = new Student(firstname, cousename, rollNumber);
                        System.out.println("\nAdding the user.........");
                        dao.saveUser(user);
                        System.out.println("User added successfully!");
                        break;
                    }
                    case 2:
                    {
                        System.out.print("Enter the userId: ");
                        long id = Long.valueOf(input.readLine());
                        System.out.println(dao.getUserById(id));
                        break;
                    }
                    case 3:
                    {
                        dao.getAllUsers().forEach(u -> System.out.println(u));
                        break;
                    }
                    case 4:
                    {
                        System.out.println("Enter the userId: ");
                        long id = Long.valueOf(input.readLine());
                        Student user = dao.getUserById(id);
                        if(user == null) {
                            System.out.println("Sorry! The user does not exit.");
                            break;
                        }
                        System.out.println("Leave blank if don't want to change.");
                        System.out.print("Enter the firstname: ");
                        String cousename = input.readLine().trim();
                        if(cousename != "")
                            user.setCourse(cousename);
                        System.out.print("Enter the lastname: ");
                        String studentName = input.readLine().trim();
                        if(studentName != "")
                            user.setStudentName(studentName);
                        System.out.println();
                        System.out.println("Dob format will be yyyy-mm-dd");
                        System.out.print("Enter the dob: ");
                        int rollnumber = Integer.parseInt(input.readLine().trim());
                        if(rollnumber != 0)
                            user.setRollNumber(rollnumber);
                        System.out.println("\nUpdating the user.........");
                        dao.updateUser(user);
                        System.out.println("User updated successfully!");
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Enter the userId: ");
                        long id = Long.valueOf(input.readLine());
                        System.out.println("Deleting the user.......");
                        dao.deleteUserById(id);
                        System.out.println("User deleted successfully!");
                        break;
                    }
                    case 6:
                        System.exit(0);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
