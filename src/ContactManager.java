import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class ContactManager
{
    public void add(Contact contact, HashMap<Integer, Contact> contacts)
    {
        contacts.put(contact.getID(), contact);
    }

    public void remove(HashMap<Integer, Contact> contacts)
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Specify an ID: ");
        int id = keyboard.nextInt();

        if (searchContact(id, contacts))
        {
            for (Contact contact : contacts.values())
            {
                if (contact.getID() == id)
                {
                    System.out.println("Removing: " + contact.getName());
                    contacts.remove(id, contact);
                }
            }
        }
        else
        {
            System.out.println("Could not find contact with that ID. Try again.");
        }
    }

    public Contact createContact()
    {
        InputValidation validation = new InputValidation();

        Scanner keyboard = new Scanner(System.in);
        Contact contact = new Contact();

        System.out.print("Enter first name: ");
        String input = keyboard.nextLine();

        while (!validation.validateName(input))
        {
            System.out.print("Your name must only contain letters: ");
            input = keyboard.nextLine();
        }

        contact.setFirstName(input);

        System.out.print("Enter last name: ");
        input = keyboard.nextLine();
        contact.setLastName(input);

        while (!validation.validateName(input))
        {
            System.out.print("Your name must only contain letters: ");
            input = keyboard.nextLine();
        }

        System.out.print("Enter phone number: ");
        input = keyboard.nextLine();

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

        while (!validation.validateEmail(input))
        {
            System.out.print("Must be a valid email address: ");
            input = keyboard.nextLine();
        }

        contact.setEmail(input);

        return contact;
    }

    public void exportContacts(HashMap<Integer, Contact> contacts)
    {
        if (contacts.isEmpty())
        {
            System.out.println("No contacts to write. Add some maybe?");
            return;
        }

        File file = new File("resources/contacts.csv");

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

    public void importContacts(HashMap<Integer, Contact> contacts)
    {
        if (!contacts.isEmpty())
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.print("List is not empty. Do you want to import and overwrite existing list? (Y/N): ");
            String choice = keyboard.nextLine().toLowerCase();

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
        else
        {
            readFile(contacts);

            System.out.println("Successfully loaded the contacts into the program.");
        }
    }

    public void displayContacts(HashMap<Integer, Contact> contacts)
    {
        if (contacts.isEmpty())
        {
            System.out.println("Contact list is empty.");
            return;
        }

        System.out.println("ID\t\tName\t\tAddress\t\t\t\tPhone\t\t\tEmail");

        for (Contact contact : contacts.values())
        {
            System.out.println(contact.getID() + " | " + contact.getName() + " | " + contact.getAddress() + " | " + contact.getPhoneNumber() + " | " + contact.getEmail());
        }
    }

    public String searchContact(HashMap<Integer, Contact> contacts)
    {
        Scanner keyboard = new Scanner(System.in);

        System.out.print("Enter a contact ID: ");
        int id = keyboard.nextInt();

        for (Contact contact : contacts.values())
        {
            if (contact.getID() == id)
            {
                return contact.toString();
            }
        }

        return "Could not find contact with ID #" + id;
    }

    private Contact createContact(String[] contactList)
    {
        return new Contact(contactList[0], contactList[1], contactList[2], contactList[3], contactList[4]);
    }

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

    private void readFile(HashMap<Integer, Contact> contacts)
    {
        ContactManager manager = new ContactManager();

        Scanner keyboard = new Scanner(System.in);
        System.out.print("Specify a path: ");
        String path = keyboard.nextLine();

        String line;
        String delimiter = ",";

        File file = new File(path);

        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            while ((line = reader.readLine()) != null)
            {
                String[] contactList = line.split(delimiter);
                Contact contact = manager.createContact(contactList);
                manager.add(contact, contacts);
            }
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}