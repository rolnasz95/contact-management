import java.util.HashMap;
import java.util.Scanner;

public class Menu
{
    public Menu()
    {
    }

    public void menuOptions(HashMap<Integer, Contact> contacts)
    {
        ContactManager manager = new ContactManager();

        boolean running = true;

        while (running)
        {
            displayOptions();

            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int choice = keyboard.nextInt();

            switch (choice)
            {
                case 1:
                    Contact contact = manager.createContact();
                    manager.add(contact, contacts);
                    break;
                case 2:
                    manager.remove(contacts);
                    break;
                case 3:
                    System.out.println(manager.searchContact(contacts));
                    break;
                case 4:
                    manager.displayContacts(contacts);
                    break;
                case 5:
                    manager.importContacts(contacts);
                    break;
                case 6:
                    manager.exportContacts(contacts);
                    break;
                case 7:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void displayOptions()
    {
        System.out.println("1. Create new contact\t\t\t2. Delete existing contact");
        System.out.println("3. Search for contact by ID\t\t4. Display all contacts");
        System.out.println("5. Load contacts from file\t\t6. Write contacts to file");
        System.out.println("7. Exit");
    }
}
