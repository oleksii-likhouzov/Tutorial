package org.test.input.output;

import org.test.Tutor;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by stalker on 10.03.16.
 */
public class RandomAccessUTFTutor extends Tutor {

    private static final String FILES_TEST_PATH = "files/test.txt";

    /**
     * Запишите в файл FILES_TEST_PATH 2 строки UTF:
     * "test line" и "test line 2"
     */
    public void randomAccessWriteUTF() {
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

            f.writeUTF("test line");
            f.writeUTF("test line 2");
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
     * Перепрыгните ко 2-ой строке UTF, используя skip,
     * считайте и верните ее значение
     *
     * @return
     */
    public String randomAccessReadUTF() {
        String result = null;
        RandomAccessFile f = null;
        try {
            f = new RandomAccessFile(FILES_TEST_PATH, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            f.seek(2 + "test line".length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            result = f.readUTF();
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
    public void testRandomAccessUTF() throws UnsupportedEncodingException {
        randomAccessWriteUTF();
        String s1 = null, s2 = null;
        try {
            RandomAccessFile f = new RandomAccessFile(FILES_TEST_PATH, "r");
            s1 = f.readUTF();
            s2 = f.readUTF();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals("test line", s1);
        assertEquals("test line 2", s2);
        String read = randomAccessReadUTF();
        assertEquals("test line 2", read);
    }
}
