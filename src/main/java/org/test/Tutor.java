package org.test;

/**
 * Created by alykhouzov on 29.02.2016.
 */
public class Tutor {
    public static void log(String message) {
        System.out.println("[log]" + message);
    }

    public static void log(double message) {
        System.out.println("[log]" + message);
    }

    public static void log(long message) {
        System.out.println("[log]" + message);
    }

    public static void log(Exception message) {
        System.out.println("[log]:" + message.getMessage() + "\n" + "Stack:"+ message.getStackTrace().toString());
    }
}
