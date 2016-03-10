package org.test.input.output;

import org.test.Tutor;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by stalker on 10.03.16.
 */
public class FileStreamTutor extends Tutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Записывает в файл FILES_TEST_PATH строку TEST_LINE,
     * преобразуя строку в массив байтов.
     * Для записи используйте класс FileOutputStream.
     */
    public void writeToFile() {
        File file = new File(FILES_TEST_PATH);
        file = new File(file.getParent());
        file.mkdir();
        file = new File(FILES_TEST_PATH);
        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.write(TEST_LINE.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает строку из файла и возвращает ее, используя FileInputStream.
     *
     * @return
     */
    public String readFromFile() {
        byte array[] = new byte[TEST_LINE.length()];
        FileInputStream stream = null;


        try {
            stream = new FileInputStream(FILES_TEST_PATH);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            stream.read(array, 0, array.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(array);
    }

    @Test
    public void testFileStream() {
        writeToFile();
        String s = readFromFile();
        assertEquals(TEST_LINE, s);
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
}
