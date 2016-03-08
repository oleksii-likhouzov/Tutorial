package org.test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcTutor extends Tutor {
    Connection conn;

    public static void main(String[] a) {
        JdbcTutor t = null;
        try {
            t = new JdbcTutor();

            /**
             * Create table and Insert can be executed only once!
             */
            t.createTableMarks();
            t.createTableStudents();
            t.addStudent(1, "Andrew", "andrew@mail.ua");
            t.addStudent(2, "John", "john@mail.ua");
            t.addMark(1, 5); // add mark 5 to Andrew
            t.addMark(1, 4); // and mark 4
            t.addMark(2, 5); // add mark 5 to John


            t.addStudents(new ArrayList<Student>(){{
                add(new Student(10,"Gottor","gottor@fg.ua"));
                add(new Student(20,"Jecson","jecson@fg.ua"));
            }}
            );
            /**
             * Print all students and average marks
             */
            t.printStudents();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            t.closeConnection();
        }

    }

    public JdbcTutor() {
        openConnection();
    }

    public Connection openConnection() {
        try {
            Class.forName("org.h2.Driver"); // this is driver for H2
            conn = DriverManager.getConnection("jdbc:h2:~/DEMO",
                    "sa", // login
                    "" // password
            );
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Should create table Students
     */
    public void createTableStudents() throws SQLException {
        String sql = "DROP TABLE IF EXISTS STUDENTS";
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            log(e);
        }
        sql = "CREATE TABLE STUDENTS( \n" +
                "    ID INT PRIMARY KEY, \n" +
                "    NAME VARCHAR(255),\n" +
                "    EMAIL VARCHAR(255),\n" +
                "    GROUP_ID INT )";
        stmt.executeUpdate(sql);
    }

    /**
     * Should create table Marks
     */
    public void createTableMarks() throws SQLException {
        String sql = "DROP TABLE IF EXISTS MARKS";
        Statement stmt = conn.createStatement();
        try {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            log(e);
        }
        sql = "CREATE TABLE MARKS( \n" +
                "    ID IDENTITY PRIMARY KEY, \n" +
                "    STUDENT_ID INT,\n" +
                "    COURSE_ID INT,\n" +
                "    MARK INT,\n" +
                "    COMMENT  VARCHAR(255),\n" +
                "    FOREIGN KEY(COURSE_ID ) \n" +
                "\tREFERENCES COURSES(ID),\n" +
                "    FOREIGN KEY(STUDENT_ID ) \n" +
                "\tREFERENCES  STUDENTS(ID)\n" +
                ");";
        stmt.executeUpdate(sql);
    }

    /**
     * Should add student to the Students table
     */
    public void addStudent(int id, String name, String email) throws SQLException {
        String sql = "INSERT INTO STUDENTS (id,name,EMAIL) " +
                "VALUES(" + id + ",'" + name + "'," + "'" + email + "') ";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    /**
     * Should add mark to the Student
     */
    public void addMark(int student, int mark) throws SQLException {
        String sql = "INSERT INTO MARKS (STUDENT_ID,MARK) " +
                "VALUES(" + student + "," + mark + ")";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    static class Student {
        int id;
        String name;
        String email;

        public Student(int id, String name,String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }
    }

    public void addStudents(List<Student> strdents) throws SQLException {
        final String sql = "INSERT INTO STUDENTS (id,name,EMAIL) VALUES(?,?,?)";

        final PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            for (Student student : strdents) {
                stmt.setInt(1, student.id);
                stmt.setString(2, student.name);
                stmt.setString(3, student.email);
                stmt.addBatch();
            }

            final int[] results = stmt.executeBatch();
            for (int i = 0; i < results.length; i++) {
                if (results[i] == Statement.EXECUTE_FAILED) {
                    System.out.println("Batch #" + i + " - Failed.");
                } else if (results[i] == Statement.SUCCESS_NO_INFO) {
                    System.out.println("Batch #" + i + " - Succeded with no results.");
                } else {
                    System.out.println("Batch #" + i + " - Affected " + results[i] + " rows.");
                }
            }

        } finally {
            stmt.close();
        }
    }

    /**
     * Should print all students and their marks
     */
    public void printStudents() throws SQLException {
        try {
            // 1) Create statement
            Statement stmt = conn.createStatement();
            String sql = "SELECT s.id, s.name, s.email, m.mark FROM STUDENTS s \n" +
                    " LEFT JOIN MARKS m ON m.STUDENT_ID=s.ID ";
            // 2) Execute query and get the ResultSet
            ResultSet rs = stmt.executeQuery(sql);

            // Iterate over results and print it
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int mark = rs.getInt("mark");
                // Display data
                System.out.print("ID: " + id + ", ");
                System.out.print("Name: " + name + ", ");
                System.out.print("Email: " + email + ", ");
                System.out.print("Mark: " + mark + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
