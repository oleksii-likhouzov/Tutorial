package org.test.input.output;

import java.io.File;
import java.io.IOException;

import org.junit.Test;
import org.test.Tutor;


public class FilePathTest extends Tutor {


    @Test
    public void testPath() {
        File f = new File("test/.././file.txt");
        System.out.println("Name: " + f.getName());
        System.out.println("Path: " + f.getPath());
        System.out.println("Absolute Path: " + f.getAbsolutePath());
        try {
            System.out.println("Canonical Path: " + f.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        f = new File("file.txt");
        System.out.println("Name: " + f.getName());
        System.out.println("Path: " + f.getPath());
        System.out.println("Absolute Path: " + f.getAbsolutePath());
        try {
            System.out.println("Canonical Path: " + f.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

        f = new File("H:\\demo","test/.././file.txtt");
        System.out.println("Name: " + f.getName());
        System.out.println("Path: " + f.getPath());
        System.out.println("Absolute Path: " + f.getAbsolutePath());
        try {
            System.out.println("Canonical Path: " + f.getCanonicalPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
