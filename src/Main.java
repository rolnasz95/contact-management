import java.util.HashMap;

public class Main
{
    public static void main(String[] args)
    {
        // Store Contact objects and map them to their ID numbers
        HashMap<Integer, Contact> contacts = new HashMap<>();

        Menu menu = new Menu();

        menu.menuOptions(contacts);
    }
}
