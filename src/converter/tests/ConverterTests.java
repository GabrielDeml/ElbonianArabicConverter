package converter.tests;

import converter.ElbonianArabicConverter;
import converter.exceptions.MalformedNumberException;
import converter.exceptions.ValueOutOfBoundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test cases for the ElbonianArabicConverter class.
 */
public class ConverterTests {

    @Test
    public void ElbonianToArabicSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("1");
        assertEquals("I", converter.toElbonian());
    }

    @Test
    public void ArabicToElbonianSampleTest() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("I");
        assertEquals(converter.toArabic(), 1);
    }

    @Test(expected = MalformedNumberException.class)
    public void malformedNumberTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new MalformedNumberException("TEST");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest() throws MalformedNumberException, ValueOutOfBoundsException {
        throw new ValueOutOfBoundsException("0");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest1() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("3000");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest2() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("0");
    }

    @Test(expected = ValueOutOfBoundsException.class)
    public void valueOutOfBoundsTest3() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMM");
    }

    @Test(expected = MalformedNumberException.class)
    public void valueOutOfBoundsTest4() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("");
    }

    @Test(expected = MalformedNumberException.class)
    public void valueOutOfBoundsTest5() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("XM");
    }

    @Test(expected = MalformedNumberException.class)
    public void valueOutOfBoundsTest6() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("EDC");
    }

    @Test
    public void ElbonianToArabicTest1() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("360");
        assertEquals("DZ", converter.toElbonian());
    }

    @Test
    public void ElbonianToArabicTest2() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MX");
        assertEquals("MX", converter.toElbonian());
    }
    @Test
    public void ElbonianToArabicTest3() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("2999");
        assertEquals("MMEDZYKJ", converter.toElbonian());
    }
    @Test
    public void ElbonianToArabicTest4() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMEDZYKJ");
        assertEquals(2999, converter.toArabic());
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest5() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMEDZYKEJ");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest6() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("DEC");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest7() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("YZX");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest8() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("JKI");
    }
    @Test
    public void ElbonianToArabicTest9() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("2032");
        assertEquals(2032, converter.toArabic());
    }
    @Test
    public void ElbonianToArabicTest10() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MY");
        assertEquals("MY", converter.toElbonian());
    }
    @Test
    public void ElbonianToArabicTest11() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("2031");
        assertEquals("MMYI", converter.toElbonian());
    }
    @Test
    public void ElbonianToArabicTest12() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMYI");
        assertEquals(2031, converter.toArabic());
    }

    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest13() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MMyI");
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest15() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MM  y   I");
    }
    @Test
    public void ElbonianToArabicTest16() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("MM  Y   I");
        assertEquals(2031, converter.toArabic());
    }
    @Test
    public void ElbonianToArabicTest17() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("9 9");
        assertEquals(99, converter.toArabic());
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest18() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("9M9");
        assertEquals(99, converter.toArabic());
    }
    @Test(expected = MalformedNumberException.class)
    public void ElbonianToArabicTest19() throws MalformedNumberException, ValueOutOfBoundsException {
        ElbonianArabicConverter converter = new ElbonianArabicConverter("99*)&&%*)&*)&*_");
        assertEquals(99, converter.toArabic());
    }




    // TODO Add more test cases
}
