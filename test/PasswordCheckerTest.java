import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordCheckerTest
{

    private PasswordChecker passwordChecker;

    @BeforeEach
    public void preparar()
    {
        passwordChecker = new PasswordChecker();
        passwordChecker.setMinLength(8);
        passwordChecker.setMaxLength(12);
    }

    // Tests del metodo PasswordChecker.check
    @Test
    public void passwordCorrecto()
    {
        String password = "Abcd1234";
        String repeatPassword = "Abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void passwordVacio()
    {
        String password = "";
        String repeatPassword = "";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_BLANK, result);
    }

    @Test
    public void passwordSoloEspacios()
    {
        String password = "        ";
        String repeatPassword = "        ";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_BLANK, result);
    }

    @Test
    public void longitudInferiorAlMinimo()
    {
        String password = "Abc1234";
        String repeatPassword = "Abc1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_LENGTH, result);
    }

    @Test
    public void longitudIgualAlMinimo()
    {
        String password = "Abc12345";
        String repeatPassword = "Abc12345";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void longitudSuperiorAlMinimo()
    {
        String password = "Abc123456";
        String repeatPassword = "Abc123456";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void longitudInferiorAlMaximo()
    {
        String password = "Abc12345678";
        String repeatPassword = "Abc12345678";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void longitudIgualAlMaximo()
    {
        String password = "Abc123456789";
        String repeatPassword = "Abc123456789";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void longitudSuperiorAlMaximo()
    {
        // Supera 12 pero pasa igual
        String password = "Abc1234567890";
        String repeatPassword = "Abc1234567890";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_LENGTH, result);
    }

    @Test
    public void longitudExactaConLimitesIguales()
    {
        passwordChecker.setMaxLength(8);
        String password = "Abcd1234";
        String repeatPassword = "Abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void superarLongitudConLimitesIguales()
    {
        //Deberia de devolver 1 porque tiene 9 pero la pasa
        passwordChecker.setMaxLength(8);
        String password = "Abcd12345";
        String repeatPassword = "Abcd12345";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_LENGTH, result);
    }

    @Test
    public void sinDigitosConComprobacionActivada()
    {
        passwordChecker.setCheckDigits(true);
        String password = "Abcdefgh";
        String repeatPassword = "Abcdefgh";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_DIGITS, result);
    }

    @Test
    public void conDigitosConComprobacionActivada()
    {
        passwordChecker.setCheckDigits(true);
        String password = "Abcd1234";
        String repeatPassword = "Abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void sinDigitosConComprobacionDesactivada()
    {
        passwordChecker.setCheckDigits(true);
        passwordChecker.setCheckDigits(false);
        String password = "Abcdefgh";
        String repeatPassword = "Abcdefgh";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void sinMayusculasConComprobacionActivada()
    {
        passwordChecker.setCheckUppers(true);
        String password = "abcd1234";
        String repeatPassword = "abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_UPPERS, result);
    }

    @Test
    public void conMayusculasConComprobacionActivada()
    {
        passwordChecker.setCheckUppers(true);
        String password = "Abcd1234";
        String repeatPassword = "Abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void sinMayusculasConComprobacionDesactivada()
    {
        passwordChecker.setCheckUppers(true);
        passwordChecker.setCheckUppers(false);
        String password = "abcd1234";
        String repeatPassword = "abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void sinMinusculasConComprobacionActivada()
    {
        passwordChecker.setCheckLowers(true);
        String password = "ABCD1234";
        String repeatPassword = "ABCD1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_LOWERS, result);
    }

    @Test
    public void conMinusculasConComprobacionActivada()
    {
        passwordChecker.setCheckLowers(true);
        String password = "Abcd1234";
        String repeatPassword = "Abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void sinMinusculasConComprobacionDesactivada()
    {
        passwordChecker.setCheckLowers(true);
        passwordChecker.setCheckLowers(false);
        String password = "ABCD1234";
        String repeatPassword = "ABCD1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    @Test
    public void passwordsNoCoinciden()
    {
        String password = "Abcd1234";
        String repeatPassword = "Abcd1235";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.ERR_NO_MATCH, result);
    }

    @Test
    public void passwordCorrectoConTodasLasComprobaciones()
    {
        passwordChecker.setCheckDigits(true);
        passwordChecker.setCheckUppers(true);
        passwordChecker.setCheckLowers(true);
        String password = "Abcd1234";
        String repeatPassword = "Abcd1234";
        int result = passwordChecker.check(password, repeatPassword);
        assertEquals(PasswordChecker.PASSWORD_OK, result);
    }

    // Tests de los metodos getCheckDigits, getCheckLowers y getCheckUppers
    @Test
    public void comprobacionesDesactivadasPorDefecto()
    {
        assertFalse(passwordChecker.getCheckDigits());
        assertFalse(passwordChecker.getCheckUppers());
        assertFalse(passwordChecker.getCheckLowers());
    }

    // Tests del metodo setCheckDigits
    @Test
    public void cambiarComprobacionDigitos()
    {
        passwordChecker.setCheckDigits(true);
        assertTrue(passwordChecker.getCheckDigits());
        passwordChecker.setCheckDigits(false);
        assertFalse(passwordChecker.getCheckDigits());
    }

    // Tests del metdo setCheckLowers
    @Test
    public void cambiarComprobacionMinusculas()
    {
        passwordChecker.setCheckLowers(true);
        assertTrue(passwordChecker.getCheckLowers());
        passwordChecker.setCheckLowers(false);
        assertFalse(passwordChecker.getCheckLowers());
    }

    // Tests del metodo setCheckUppers
    @Test
    public void cambiarComprobacionMayusculas()
    {
        passwordChecker.setCheckUppers(true);
        assertTrue(passwordChecker.getCheckUppers());
        passwordChecker.setCheckUppers(false);
        assertFalse(passwordChecker.getCheckUppers());
    }

    // Tests del metodo setMaxLength
    @Test
    public void establecerMaximoMayorQueMinimo()
    {
        passwordChecker.setMaxLength(15);
        assertEquals(15, passwordChecker.getMaxLength());
    }

    @Test
    public void establecerMaximoIgualQueMinimo()
    {
        passwordChecker.setMaxLength(8);
        assertEquals(8, passwordChecker.getMaxLength());
    }

    @Test
    public void rechazarMaximoInferiorAlMinimo()
    {
        try
        {
            passwordChecker.setMaxLength(7);
        } catch (IllegalArgumentException error)
        {
            assertEquals(PasswordChecker.ERR_INVALID_MAX_LENGTH, error.getMessage());
            assertEquals(12, passwordChecker.getMaxLength());
            return;
        }
        Assertions.fail();
    }

    @Test
    public void rechazarMaximoNegativo()
    {
        try
        {
            passwordChecker.setMaxLength(-1);
        } catch (IllegalArgumentException error)
        {
            assertEquals(PasswordChecker.ERR_INVALID_MAX_LENGTH, error.getMessage());
            return;
        }
        Assertions.fail();
    }

    // Tests del metodo setMinLength
    @Test
    public void establecerMinimoPositivo()
    {
        passwordChecker.setMinLength(1);
        assertEquals(1, passwordChecker.getMinLength());
    }

    @Test
    public void establecerMinimoCero()
    {
        // En la documentacion se dice que solo rechaza valores inferiores a cero, pero la tru es que tambien rechaza el mismo 0
        passwordChecker.setMinLength(0);
        assertEquals(0, passwordChecker.getMinLength());
    }

    @Test
    public void rechazarMinimoNegativo()
    {
        try
        {
            passwordChecker.setMinLength(-1);
        } catch (IllegalArgumentException error)
        {
            assertEquals(PasswordChecker.ERR_INVALID_MIN_LENGTH, error.getMessage());
            assertEquals(8, passwordChecker.getMinLength());
            return;
        }
        Assertions.fail();
    }
}
