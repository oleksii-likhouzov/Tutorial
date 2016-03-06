package org.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * Created by alykhouzov on 29.02.2016.
 */
public class ObjectToStringTutor extends Tutor {
    class Employee {
        public String name;
        public String surname;
        public int age;

        public Employee(String name, String surname, int age) {
            this.name = name;
            this.surname = surname;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", surname='" + surname + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<Employee>();
        employees.add(new Employee( "Иван", "Иванов", 35));
        employees.add(new Employee("Иван", "Иванов", 30));
        employees.add(new Employee("Иван", "Петров", 25));
        employees.add(new Employee("Иван", "Сидоров", 25));
        return employees;
    }

    @Test
    public void testEmployees() {
        List<Employee> employees = getEmployees();
        int index=0;
        for (Employee employee: employees) {
            log("emp"+ index++ +"="+employee);
        }
    }

}
