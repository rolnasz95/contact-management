package main;

import java.util.Scanner;

public class Menu
{
    private final InputValidation validation = new InputValidation();   // Used for input validation
    private final ContactManager manager = new ContactManager();        // Used to perform operations based on menu choice

    public void mainMenuOptions()   // Display main menu options for the user to navigate in the program
    {
        boolean running = true;     // Control variable for the main menu

        while (running)
        {
            displayMainMenu();

            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            int choice = validation.validateInteger(keyboard);

            switch (choice)
            {
                case 1:
                    Contact contact = manager.createContact();
                    manager.addContact(contact);
                    break;
                case 2:
                    manager.removeContact();
                    break;
                case 3:
                    System.out.println(manager.searchContact());
                    break;
                case 4:
                    manager.displayContacts();
                    break;
                case 5:
                    manager.importContacts();
                    break;
                case 6:
                    manager.exportContacts();
                    break;
                case 7:
                    editContactSubmenu();
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    private void displayMainMenu()
    {
        System.out.println("1. Create new contact\t\t\t2. Delete existing contact");
        System.out.println("3. Search for contact by ID\t\t4. Display all contacts");
        System.out.println("5. Load contacts from file\t\t6. Write contacts to file");
        System.out.println("7. Edit contact\t\t\t\t\t8. Exit");
    }


    private void editContactSubmenu()   // Display submenu options for editing existing Contact information
    {
        int choice = 0;

        while (choice != 4)
        {
            displaySubmenu();

            Scanner keyboard = new Scanner(System.in);
            System.out.print("Enter your choice: ");
            choice = validation.validateInteger(keyboard);

            switch (choice)
            {
                case 1:
                    System.out.print("Enter ID: ");
                    choice = validation.validateInteger(keyboard);

                    keyboard.nextLine();

                    manager.changePhone(choice);
                    break;
                case 2:
                    System.out.print("Enter ID: ");
                    choice = validation.validateInteger(keyboard);

                    keyboard.nextLine();

                    manager.changeAddress(choice);
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    choice = validation.validateInteger(keyboard);

                    keyboard.nextLine();

                    manager.changeEmail(choice);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        }
    }

    private void displaySubmenu()
    {
        System.out.println("1. Edit phone number\t\t2. Edit address");
        System.out.println("3. Edit email\t\t\t\t4. Go back to main menu");
    }
}
