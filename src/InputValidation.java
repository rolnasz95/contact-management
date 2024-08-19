public class InputValidation
{
    public boolean validateName(String name)
    {
        return name.matches("^[a-zA-Z]+$");
    }

    public boolean validateEmail(String email)
    {
        char[] array = email.toLowerCase().toCharArray();

        int atCount = 0;

        for (char ch : array)
        {
            if (ch == '@')
            {
                atCount++;
            }
        }

        return email.contains("@") && email.contains(".") && atCount == 1;
    }

    public boolean validatePhone(String phone)
    {
        return phone.length() == 12 && phone.charAt(3) == '-' && phone.charAt(7) == '-';
    }
}
