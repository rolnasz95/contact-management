package tests;

import main.*;
import org.junit.Test;
import static org.junit.Assert.*;

public class InputValidationTest
{
    private InputValidation validation = new InputValidation();

    @Test
    public void testValidName()
    {
        assertTrue(validation.validateName("Matthew"));
        assertTrue(validation.validateName("Mary"));
    }

    @Test
    public void testInvalidName()
    {
        assertFalse(validation.validateName("L3wi$"));
        assertFalse(validation.validateName(""));
    }

    @Test
    public void testValidEmail()
    {
        assertTrue(validation.validateEmail("john.smith@example.com"));
        assertTrue(validation.validateEmail("robert_robinson42@domain.com"));
    }

    @Test
    public void testInvalidEmail()
    {
        assertFalse(validation.validateEmail("john@."));
        assertFalse(validation.validateEmail("@robert.com"));
    }

    @Test
    public void testValidPhone()
    {
        assertTrue(validation.validatePhone("012-345-6789"));
    }

    @Test
    public void testInvalidPhone()
    {
        assertFalse(validation.validatePhone("00-1234-56789"));
        assertFalse(validation.validatePhone("012X345X6789"));
        assertFalse(validation.validatePhone("0123456789"));
    }

    @Test
    public void testValidFileFormat()
    {
        assertTrue(validation.validateFileFormat("my_file.csv"));
        assertTrue(validation.validateFileFormat("path/to/my/file.csv"));
    }

    @Test
    public void testInvalidFileFormat()
    {
        assertFalse(validation.validateFileFormat("my_file.txt"));
    }
}
