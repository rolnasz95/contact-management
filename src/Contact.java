public class Contact
{
    private static int nextId = 1000;
    private int id;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String address;
    private String email;

    public Contact()
    {
        // Value of id becomes the next available number
        this.id = nextId++;
        this.lastName = "";
        this.firstName = "";
        this.phoneNumber = "";
        this.address = "";
        this.email = "";
    }

    public Contact(String firstName, String lastName, String phoneNumber, String address, String email)
    {
        // Value of id becomes the next available number
        this.id = nextId++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.email = email;
    }

    public int getID()
    {
        return id;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getFirstName()
    {
        return firstName;
    }

    public String getPhoneNumber()
    {
        return phoneNumber;
    }

    public String getAddress()
    {
        return address;
    }

    public String getEmail()
    {
        return email;
    }

    public String getName()
    {
        return firstName + " " + lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    @Override
    public String toString()
    {
        return "Name: " + firstName + " " + lastName + "\nID: " + id + "\nAddress: " + address + "\nPhone: " + phoneNumber
                + "\ne-mail: " + email;
    }
}
