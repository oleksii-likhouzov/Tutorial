package org.test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import org.junit.Test;

public class SystemCurrentTimeTutor extends  Tutor {


    /**
     * getTimeInMillis() должен возвращать текущее время в миллисекундах
     */
    public long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    /**
     * профайлер должен вычислять, сколько миллисекунд заняло
     * выполнение метода Runnable.run()
     *
     */
    public long profiler(Runnable run) {
        long startTime = System.currentTimeMillis();
        run.run();
        return  System.currentTimeMillis() - startTime ;
    }

    /**
     * Метод должен возвращать дату по миллисекундам
     * done.
     */
    public Date getDate(long timeInMillis) {
        return new Date(timeInMillis);
    }

    /**
     * метод должен возвращать дату, к которой прибавлено (или отнято) plusDays дней
     */
    public Date getDatePlus(Date date, int plusDays) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, plusDays);
        return c.getTime();
    }

    @Test
    public void testGetDate() {
        Date date = getDate(1363877852603L);
        log(date.toString());
        assertEquals(date.getTime(), 1363877852603L);
        Date dateOfBeginning = getDate(0);
        log(dateOfBeginning.toString());
        assertEquals(dateOfBeginning.getTime(), 0);
    }

    @Test
    public void testGetDatePlus() {
        // create date for 1.03.2013, 12:30
        Calendar cal = Calendar.getInstance();
        cal.set(2013, 3, 1, 12, 30, 0);
        cal.clear(Calendar.MILLISECOND);
        // create date for 3.03.2013, 12:30
        Calendar cal2 = Calendar.getInstance();
        cal2.set(2013, 3, 3, 12, 30, 0);
        cal2.clear(Calendar.MILLISECOND);
        Date datePlus = getDatePlus(cal.getTime(), 2);
        log(cal.getTime().toString());
        log(datePlus.toString());
        log(cal2.getTime().toString());
        log(datePlus.getTime()+":"+cal2.getTimeInMillis());
        assertEquals("datePlus() возвращает неверную дату",
                datePlus, cal2.getTime());
    }

    @Test
    public void testGetTimeInMillis() {
        assertTrue(
                "getTimeInMillis() должен возвращать время в миллисекундах",
                getTimeInMillis()>1363877852603l);
    }

    @Test
    public void testForProfiler() {
        assertTrue(noOperationProfiler()==0);
        assertTrue(forProfiler()>0);
    }

    public long noOperationProfiler() {
        return profiler(new Runnable() {
            public void run() {}
        });
    }

    public long forProfiler() {
        return profiler(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch(Exception e) {log(e.getMessage());}


            }
        });
    }

    public static void main(String[] args) {
        SystemCurrentTimeTutor systemClass = new SystemCurrentTimeTutor();
        log(systemClass.getTimeInMillis());
    }
}
