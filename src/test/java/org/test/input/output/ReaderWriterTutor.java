package org.test.input.output;

import org.test.Tutor;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class ReaderWriterTutor extends Tutor {
    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Записывает в файл FILES_TEST_PATH строку TEST_LINE, используя
     * метод write класса BufferedWriter.
     * Затем закрывает поток.
     */
    public void writeToFile() {
        File file = new File(FILES_TEST_PATH);
        file = new File(file.getParent());
        file.mkdir();
        file = new File(FILES_TEST_PATH);
        BufferedWriter bwriter = null;
        try {
            bwriter = new BufferedWriter(new FileWriter(file));

        bwriter.write(TEST_LINE);
        bwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает строку из файла с помощью метода readLine()
     * класса BufferedReader и возвращает ее.
     *
     * @return
     */
    public String readFromFile() {
        BufferedReader  breader = null;
        String result = null;
        try {
            breader = new BufferedReader(new FileReader(FILES_TEST_PATH));

            result = breader.readLine();
            breader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Test
    public void testStream() {
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
