import java.util.Scanner;
import java.util.HashMap;

public class Main
{
    public static void main(String[] args)
    {
        // Store Contact objects and map them to their ID numbers
        HashMap<Integer, Contact> contacts = new HashMap<>();

        ContactManager manager = new ContactManager();

        // Control variable for the menu
        boolean running = true;

        while (running)
        {
            displayMenu();

            // Get user input
            Scanner input = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            switch (choice)
            {
                case 1:
                    Contact contact = manager.create();
                    manager.add(contact, contacts);
                    break;
                case 2:
                    manager.remove(contacts);
                    break;
                case 3:
                    manager.export(contacts);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    public static void displayMenu()
    {
        System.out.println("1. Create new contact");
        System.out.println("2. Delete existing contact");
        System.out.println("3. Write contacts to file");
        System.out.println("4. Exit");
    }
}
