package org.test.input.output;

import org.junit.Test;
import org.test.Tutor;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by stalker on 10.03.16.
 */
public class FileTutor extends Tutor {
    /**
     * Метод должен создавать папку test и в ней - файл test/test.txt
     * Также выведите в лог полный путь к созданному файлу
     */
    public void createFile() {
        File file = new File("test");
        file.mkdir();
        file = new File("test/test.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод должен удалять папку test и файл test/test.txt
     */
    public void deleteFile() {
        File file = file = new File("test/test.txt");
        file.delete();
        file = new File("test");
        file.delete();
    }


    @Test
    public void testCreateFile() {
        createFile();
        File f = new File("test/test.txt");
        assertTrue(f.exists());
    }


    @Test
    public void testDeleteFile() {
        deleteFile();
        File f = new File("test/test.txt");
        assertFalse(f.exists());
        assertFalse(new File("test").exists());
    }

}
