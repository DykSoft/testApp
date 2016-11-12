package lesson05;

import org.junit.*;
import ru.jawawebinar.webapp.WebAppException;

import static org.junit.Assert.assertEquals;

/**
 * denis
 * 12.11.2016.
 */
public class CalculatorTest {

   // static Calculator calc = new Calculator();
   static Calculator calc;

    static {

        calc = new Calculator();
    }

    @BeforeClass
    public static void setBefore() throws Exception {

    }


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test(expected = WebAppException.class)
    public void abs() throws Exception {

        assertEquals(5,calc.abs(-5));
        throw new WebAppException("");

    }

}