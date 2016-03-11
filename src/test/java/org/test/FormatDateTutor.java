package org.test;

import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

import static org.junit.Assert.*;

import org.junit.Test;

public class FormatDateTutor extends Tutor {
    /**
     * Возвращает дату в формате dd.mm.yy
     * Использует Formatter
     */
    public String getDateByFormatter(Date date) {
        Formatter formatter = new Formatter(new StringBuilder());
        String tmpRetusl = formatter.format("%td.%tm.%ty", date, date, date).toString();
        return String.format("%td.%tm.%ty", date, date, date);
    }


    /**
     * Возвращает дату в формате "10 of April, 2013"
     * Использует Formatter
     */
    public String getDateString(Date date) {
        return String.format(Locale.US,"%td of %tb, %tY", date, date, date);
    }

    /**
     * Возвращает дату в формате "10.04.13"
     * Используйте SimpleDateFormat
     */
    public String getDateBySimpleDateFormat(Date date) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        return simpleDateFormat.format(date);
    }

    /**
     * Возвращает дату типа Date, полученную из строки в формате dd.mm.yy
     * Использует SimpleDateFormat метод parse()
     */
    public Date parseDDMMYY(String s) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yy");
        Date tmpDate=null;
        try {
            tmpDate= simpleDateFormat.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tmpDate;
    }

    @Test
    public void testFormatDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2013, Calendar.MAY, 1); // 1st of May, 2013
        Date date = cal.getTime();

        String dateByFormatter = getDateByFormatter(date);
        log("dateByFormatter:" + dateByFormatter);
        assertEquals(dateByFormatter, "01.05.13");

        String dateBySimpleDateFormat = getDateBySimpleDateFormat(date);
        log("dateBySimpleDateFormat:" + dateBySimpleDateFormat);
        assertEquals(dateBySimpleDateFormat, "01.05.13");

        System.out.println(getDateString(new Date()));
        //formatNumber();
    }

    @Test
    public void testParseDate() {
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 4, 1, 0, 0, 0); // 1st of May, 2013
        cal.set(Calendar.MILLISECOND, 0);
        Date date = cal.getTime();
        Date d = parseDDMMYY("01.05.13");
        assertEquals(date, d);
    }
}
