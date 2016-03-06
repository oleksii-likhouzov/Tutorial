package org.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Pattern p =Pattern.compile("\\s0");
        Matcher action = p.matcher("  0120391238");
        boolean de= action.find();
        System.out.println("find:" + de);

        boolean ded= action.matches();
        System.out.println("matches:" + ded);
        System.out.println("String.matches:" + " 0120391238".matches("\\s0"));
        String d= action.replaceAll(" +44 (0) ");
        System.out.println("replaceAll:" + d);
        System.out.println( "Hello World!" );

        Pattern p1 =Pattern.compile("a*b");
        Matcher action1 = p1.matcher("aaaaaab");
        boolean de1= action1.matches();
        System.out.println("matches:" + de1);
        System.out.println("String.matches:" + " aaaaaab".matches("a*b"));
    }
}
