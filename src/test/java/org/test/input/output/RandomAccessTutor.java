package org.test.input.output;

import org.test.Tutor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RandomAccessTutor extends Tutor {
    private static final String FILES_TEST_PATH = "files/test.txt";

    /**
     * Метод должен открывать файл RandomAccessFile по пути FILES_TEST_PATH
     * и записывать туда число 10 (тип Integer), а затем строку "test line"
     */
    public void randomAccessWrite() {
        File file = new File(FILES_TEST_PATH);
        file = new File(file.getParent());
        file.mkdir();
        file = new File(FILES_TEST_PATH);
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(FILES_TEST_PATH, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {

            f.writeInt(10);
            f.writeChars("test line");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод должен открывать файл RandomAccessFile по пути FILES_TEST_PATH,
     * пропускать число 10 и слово "test " (не считывая их),
     * прыгать на 5-ую букву, считывать и возвращать строку "line"
     */
    public String randomAccessRead() {
        String result = null;
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(FILES_TEST_PATH, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            f.seek(4 + 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result = f.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Before
    public void createFile() {
        File f1 = new File(FILES_TEST_PATH);
        try {
            f1.delete();
            f1.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Test
    public void testRandomAccess() throws UnsupportedEncodingException {
        randomAccessWrite();

        String s = null;
        int i = -1;
        try {
            RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r");
            i = f.readInt();
            s = f.readLine();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(i, 10);
        s = new String(s.getBytes("UTF-8"), "Unicode");
        assertEquals("test line", s);
        String read = randomAccessRead();
        read = new String(read.getBytes("UTF-8"), "Unicode");
        assertEquals("line", read);
    }

}
