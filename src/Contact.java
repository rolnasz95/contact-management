public class Contact
{
    private static int nextId = 1000;
    private int id;
    private String lastName;
    private String firstName;

    public Contact()
    {
        this.id = nextId++;
        this.lastName = "";
        this.firstName = "";
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

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
}
