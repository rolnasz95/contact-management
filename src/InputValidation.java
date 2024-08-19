import java.util.InputMismatchException;
import java.util.Scanner;

public class InputValidation
{
    public boolean validateName(String name)
    {
        return name.matches("^[a-zA-Z]+$");
    }

    public boolean validateEmail(String email)
    {
        return email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public boolean validatePhone(String phone)
    {
        return phone.matches("^\\d{3}+-\\d{3}+-\\d{4}$");
    }

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
