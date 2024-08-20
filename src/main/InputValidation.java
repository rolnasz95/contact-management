package main;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation
{
    /**
     * The validateName method checks if the input contains only letters
     * @param name the String to check
     * @return true if there are only letters
     */
    public boolean validateName(String name)
    {
        return name.matches("^[a-zA-Z]+$");
    }

    /**
     * The validateEmail method checks the format for an email input
     * @param email the String to check
     * @return true if format is right
     */
    public boolean validateEmail(String email)
    {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    /**
     * The validatePhone method checks the length and format for a phone number input (should be xxx-xxx-xxxx)
     * @param phone the String to check
     * @return true if length and format is right
     */
    public boolean validatePhone(String phone)
    {
        return phone.matches("^\\d{3}+-\\d{3}+-\\d{4}$");
    }

    /**
     * The validateFileFormat method checks if the input ends with .csv
     * @param path the String to check
     * @return true if it ends with .csv
     */
    public boolean validateFileFormat(String path)
    {
        return path.matches(".*\\.csv$");
    }

    public boolean confirmAction()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Do you want to proceed? (Y/N): ");
        String input = keyboard.nextLine();

        return input.toLowerCase().charAt(0) == 'y';
    }

    public int validateInteger(Scanner keyboard)
    {
        while (true)
        {
            try
            {
                return keyboard.nextInt();
            }
            catch (InputMismatchException e)
            {
                System.out.print("Invalid input. Try again: ");
                keyboard.next();
            }
        }
    }
}
