package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.util.Date;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class DateUtilTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void formatDate() {
        final int yearFrom = 1900;
        int yearToTest = 2006 - yearFrom;

        // AM
        assertEquals("31 Jan 2006, 08:30 AM", DateUtil.formatDate(new Date(yearToTest, 0, 31, 8, 30)));

        // PM
        assertEquals("31 Jan 2006, 02:30 PM", DateUtil.formatDate(new Date(yearToTest, 0, 31, 14, 30)));

        // Noon
        assertEquals("31 Jan 2006, 12:30 PM", DateUtil.formatDate(new Date(yearToTest, 0, 31, 12, 30)));

        // MidNight
        assertEquals("31 Jan 2006, 12:30 AM", DateUtil.formatDate(new Date(yearToTest, 0, 31, 0, 30)));
    }

    @Test
    public void formatStringToDate() throws ParseException {
        final int yearFrom = 1900;
        int yearToTest = 2006 - yearFrom;

        // AM
        assertEquals(DateUtil.formatStringToDate("31 Jan 2006, 08:30 AM"), new Date(yearToTest, 0, 31, 8, 30));
        // PM
        assertEquals(DateUtil.formatStringToDate("31 Jan 2006, 02:30 PM"), new Date(yearToTest, 0, 31, 14, 30));
        // Noon
        assertEquals(DateUtil.formatStringToDate("31 Jan 2006, 12:30 PM"), new Date(yearToTest, 0, 31, 12, 30));
        // MidNight
        assertEquals(DateUtil.formatStringToDate("31 Jan 2006, 12:30 AM"), new Date(yearToTest, 0, 31, 0, 30));
    }

    @Test
    public void test_formatStringParseException() throws ParseException {
        thrown.expect(ParseException.class);

        DateUtil.formatStringToDate("31 Jan 2005 8:30AM");
    }
}
