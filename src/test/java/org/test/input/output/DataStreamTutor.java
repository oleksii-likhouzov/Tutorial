package org.test.input.output;

import org.test.Tutor;

import static org.junit.Assert.*;

import java.io.*;

import org.junit.Before;
import org.junit.Test;

public class DataStreamTutor extends Tutor {

    private static final String FILES_TEST_PATH = "files/test.txt";
    private static final String TEST_LINE = "test line";

    /**
     * Записывает в файл FILES_TEST_PATH строку TEST_LINE, используя
     * метод writeUTF класса DataOutputStream.
     * Также использует BufferedOutputStream для буферизации.
     * Затем закрывает поток.
     */
    public void writeToFile() throws IOException {
        File file = new File(FILES_TEST_PATH);
        file = new File(file.getParent());
        file.mkdir();
        file = new File(FILES_TEST_PATH);
        DataOutputStream dstream = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(file)));
        dstream.writeUTF(TEST_LINE);
        dstream.close();
    }

    /**
     * Считывает строку из файла с помощью метода readUTF и возвращает ее.
     *
     * @return
     */
    public String readFromFile() throws IOException {
        String result = null;
        DataInputStream dstream = new DataInputStream(new BufferedInputStream((new FileInputStream(FILES_TEST_PATH))));
        result=dstream.readUTF();
        dstream.close();
        return result;
    }

    @Test
    public void testStream() {
        try {
            writeToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String s=null;
        try {
        s = readFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
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