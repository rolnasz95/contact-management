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

        if (search(id, contacts))
        {
            for (Contact contact : contacts.values())
            {
                if (contact.getID() == id)
                {
                    System.out.println("Removing " + contact.getName());
                    contacts.remove(id, contact);
                }
            }
        }
        else
        {
            System.out.println("Could not find contact with that ID. Try again.");
        }
    }

    public Contact create()
    {
        Scanner keyboard = new Scanner(System.in);
        Contact contact = new Contact();

        System.out.print("Enter last name: ");
        String input = keyboard.nextLine();
        contact.setLastName(input);

        System.out.print("Enter first name: ");
        input = keyboard.nextLine();
        contact.setFirstName(input);

        System.out.print("Enter phone number: ");
        input = keyboard.nextLine();
        contact.setPhoneNumber(input);

        System.out.print("Enter address: ");
        input = keyboard.nextLine();
        contact.setAddress(input);

        System.out.print("Enter email: ");
        input = keyboard.nextLine();
        contact.setEmail(input);

        return contact;
    }

    private boolean search(int id, HashMap<Integer, Contact> contacts)
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
}
