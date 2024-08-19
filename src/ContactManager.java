import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class contains the main logic of the program, including most of the operations on Contact objects
 */
public class ContactManager
{
    // Used for accessing input validation methods the user enters
    private final InputValidation validation = new InputValidation();

    /**
     * The addContact method adds a new Contact object to the contacts HashMap
     * @param contact the contact to add to the HashMap
     * @param contacts the HashMap to add the contact to using its built-in put() method
     */
    public void addContact(Contact contact, HashMap<Integer, Contact> contacts)
    {
        contacts.put(contact.getID(), contact);
    }

    /**
     * The removeContact method removes a Contact object from the HashMap
     * @param contacts the HashMap to remove the contact from
     */
    public void removeContact(HashMap<Integer, Contact> contacts)
    {
        // Create Scanner object that will be used for input
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Specify an ID: ");

        // After validating the input is valid store the value in the id variable
        int id = validation.validateInteger(keyboard);

        // Contact object to store the contact that is to be removed
        Contact contactToRemove = new Contact();

        // Verify if contact exists
        if (searchContact(id, contacts))
        {
            // Search the HashMap for the given key (which is the ID)
            for (Contact contact : contacts.values())
            {
                // If it is a match, assign the Contact object to contactToRemove
                if (contact.getID() == id)
                {
                    contactToRemove = contact;
                }
            }

            // Ask user for confirmation and remove key-value pair from HashMap using its built-in remove method
            if (validation.confirmAction())
            {
                System.out.println("Removing " + contactToRemove.getName());
                contacts.remove(contactToRemove.getID(), contactToRemove);
            }
            else
            {
                System.out.println("Returning to main menu.");
            }
        }
    }

    /**
     * The createContact method creates a new Contact object based on user input
     * @return a new Contact object
     */
    public Contact createContact()
    {
        // Create Scanner object that will be used for input
        Scanner keyboard = new Scanner(System.in);
        // Create Contact object that will be returned from the method
        Contact contact = new Contact();

        System.out.print("Enter first name: ");
        String input = keyboard.nextLine();

        // Validate name
        while (!validation.validateName(input))
        {
            System.out.print("Your name must only contain letters: ");
            input = keyboard.nextLine();
        }

        contact.setFirstName(input);

        System.out.print("Enter last name: ");
        input = keyboard.nextLine();
        contact.setLastName(input);

        // Validate name
        while (!validation.validateName(input))
        {
            System.out.print("Your name must only contain letters: ");
            input = keyboard.nextLine();
        }

        System.out.print("Enter phone number: ");
        input = keyboard.nextLine();

        // Validate phone number
        while (!validation.validatePhone(input))
        {
            System.out.print("Phone number must be 12 characters long and must use the format of 'XXX-XXX-XXXX': ");
            input = keyboard.nextLine();
        }

        contact.setPhoneNumber(input);

        System.out.print("Enter address: ");
        input = keyboard.nextLine();
        contact.setAddress(input);

        System.out.print("Enter email: ");
        input = keyboard.nextLine();

        // Validate email address
        while (!validation.validateEmail(input))
        {
            System.out.print("Must be a valid email address: ");
            input = keyboard.nextLine();
        }

        contact.setEmail(input);

        return contact;
    }

    /**
     * The exportContacts method will write all Contact object info into a file
     * @param contacts the HashMap to read Contact objects from
     */
    public void exportContacts(HashMap<Integer, Contact> contacts)
    {
        // If HashMap is empty return to main menu
        if (contacts.isEmpty())
        {
            System.out.println("No contacts to write. Add some maybe?");
            return;
        }

        File file = new File("resources/contacts.csv");

        // Write each Contact object into the specified file in CSV format
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            for (Contact contact : contacts.values())
            {
                writer.write(contact.getFirstName() + ",");
                writer.write(contact.getLastName() + ",");
                writer.write(contact.getPhoneNumber() + ",");
                writer.write(contact.getAddress() + ",");
                writer.write(contact.getEmail());
                writer.newLine();
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        System.out.println("Contacts written successfully.");
    }

    /**
     * The importContacts method will create new Contact objects from the specified file and store them in a HashMap
     * @param contacts the HashMap to store the Contact objects in
     */
    public void importContacts(HashMap<Integer, Contact> contacts)
    {
        // Checks to see if HashMap is empty and asks for confirmation to overwrite existing data
        if (!contacts.isEmpty())
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("List is not empty. Do you want to import and overwrite existing list? (Y/N): ");
            String choice = keyboard.nextLine().toLowerCase();

            // If confirmed call the HashMap's built-in clear method to remove existing data
            // Call readFile method to read data from a file and write it to the HashMap
            if (choice.charAt(0) == 'y')
            {
                contacts.clear();

                readFile(contacts);
            }
            else
            {
                System.out.println("Returning to main menu.");
            }
        }
        // If HashMap is empty call the readFile method to read data from a file and write it to the HashMap
        else
        {
            readFile(contacts);

            System.out.println("Successfully loaded the contacts into the program.");
        }
    }

    /**
     * The displayContacts method will print all contact info to the console
     * @param contacts the HashMap to read the contacts from
     */
    public void displayContacts(HashMap<Integer, Contact> contacts)
    {
        // If HashMap is empty return to main menu
        if (contacts.isEmpty())
        {
            System.out.println("Contact list is empty.");
            return;
        }

        System.out.println("ID\t\tName\t\tAddress\t\t\t\tPhone\t\t\tEmail");

        // Iterate over each object stored in the HashMap and print them to the console
        for (Contact contact : contacts.values())
        {
            System.out.println(contact.getID() + " | " + contact.getName() + " | " + contact.getAddress() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail());
        }
    }

    /**
     * The searchContact method will retrieve and display the specified Contact object to the console
     * @param contacts the HashMap to get the Contact object from
     * @return the toString method of the retrieved object or indicate if the search failed
     */
    public String searchContact(HashMap<Integer, Contact> contacts)
    {
        // Create Scanner object that will be used for input
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter a contact ID: ");
        // Validate user input
        int id = validation.validateInteger(keyboard);

        // Iterate over each object stored in the HashMap
        for (Contact contact : contacts.values())
        {
            // If it is a match return the toString method of the retrieved Contact object
            if (contact.getID() == id)
            {
                return contact.toString();
            }
        }

        // Indicate the search was not successful
        return "Could not find contact with ID #" + id;
    }

    /**
     * The createContact private method is used to create new Contact objects from a String array
     * @param contactList the String array to create Contact objects from
     * @return a new Contact object
     */
    private Contact createContact(String[] contactList)
    {
        return new Contact(contactList[0], contactList[1], contactList[2], contactList[3], contactList[4]);
    }

    /**
     * The searchContact private method is used to look up a specified key-value pair from the HashMap
     * @param id the key that is used to search the HashMap
     * @param contacts the HashMap to search
     * @return true if found
     */
    private boolean searchContact(int id, HashMap<Integer, Contact> contacts)
    {
        for (Contact contact : contacts.values())
        {
            if (id == contact.getID())
            {
                return true;
            }
        }

        return false;
    }

    /**
     * The readFile private method is used to read a user specified file
     * @param contacts the HashMap to store the data in
     */
    private void readFile(HashMap<Integer, Contact> contacts)
    {
        // Create Scanner object that will be used for input
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Specify a path: ");
        String path = keyboard.nextLine();

        // Validate the file has a .csv extension
        while (!validation.validateFileFormat(path))
        {
            System.out.print("Only .csv files are accepted: ");
            path = keyboard.nextLine();
        }

        String line;                // The line variable is used to store each line read from the file
        String delimiter = ",";     // The delimiter to mark where to split the line

        File file = new File(path); // Open file

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            // Assign each line read from the file to the line variable until there's no more to read
            while ((line = reader.readLine()) != null)
            {
                String[] contactList = line.split(delimiter);   // This will store each value split by the delimiter
                Contact contact = createContact(contactList);   // Create new Contact object based on the data retrieved
                addContact(contact, contacts);                  // Add the Contact object to the HashMap
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}