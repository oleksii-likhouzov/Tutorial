package org.test;

import java.sql.*;
import java.util.*;

public class JdbcTutorBatch {
    Connection conn;

    public static void main(String[] a) {
        JdbcTutorBatch t = null;
        try {
            t = new JdbcTutorBatch();

            /**
             * Create table and Insert can be executed only once!
             */
            t.dropTableNames();  // drop table if it already exist
            t.createTableNames();
            t.addName(1, "Andrew");
            t.addName(2, "George");
            t.addName(3, "Maria");
            t.addName(4, "Vladimir");

            /**
             * Add names in the batch
             */
            Name[] names = {new Name(5, "Petr"), new Name(6, "Oxana"),
                    new Name(7, "Ruslan")};
            t.addNames(Arrays.asList(names));

            /**
             * Read data from table - you can run it every time
             */
            t.printNames();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            t.closeConnection();
        }
    }

    public JdbcTutorBatch() {
        openConnection();
    }

    public Connection openConnection() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.
                    getConnection("jdbc:h2:~/DEMO",
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

    public void dropTableNames() throws SQLException {
        String sql = "DROP TABLE IF EXISTS NAMES";
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void createTableNames() throws SQLException {
        String sql = "CREATE TABLE NAMES " +
                "(id INTEGER not NULL, " +
                " name VARCHAR(255)," +
                " PRIMARY KEY ( id ))";

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void addName(int id, String val) throws SQLException {
        String sql = "INSERT INTO NAMES(id,name) " +
                "VALUES(" + id + ",'" + val + "') ";

        Statement stmt = conn.createStatement();
        stmt.executeUpdate(sql);
    }

    public void printNames() throws SQLException {
        try {
            // 1) Create statement
            Statement stmt = conn.createStatement();
            String sql = "SELECT id, name FROM NAMES ";
            // 2) Execute query and get the ResultSet
            ResultSet rs = stmt.executeQuery(sql);

            // Iterate over results and print it
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                String name = rs.getString("name");

                // Display data
                System.out.print("ID: " + id + ", ");
                System.out.print("Name: " + name + "\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class Name {
        int id;
        String name;

        public Name(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private void addNames(List<Name> names) throws SQLException {
        final String sql = "INSERT INTO NAMES(id,name) VALUES(?, ?)";

        final PreparedStatement stmt = conn.prepareStatement(sql);
        try {
            for (Name n : names) {
                stmt.setInt(1, n.id);
                stmt.setString(2, n.name);
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

}
