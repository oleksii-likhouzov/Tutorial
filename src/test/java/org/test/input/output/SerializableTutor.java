package org.test.input.output;

import org.test.Tutor;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
public class SerializableTutor extends Tutor {

    private static final String FILE_OBJECT_DATA = "files/object.data";

    /**
     * Сделайте класс статическим, имплементируйте Serializable
     * и пометьте поле age как transient
     */
    static class Person implements Serializable {
        public String name;
        public Date birthdate;
        public List<Address> addressList = new ArrayList<SerializableTutor.Address>();
        public Address firstAddress;
        transient Integer age;

        public Person(String name, Date birthdate) {
            super();
            this.name = name;
            this.birthdate = birthdate;
            age = new Date().getYear() - birthdate.getYear();
        }

        public void addAddress(Address address) {
            addressList.add(address);
        }
    }

    static class Address implements Serializable {
        public String city;
        public String street;
        public Integer appartment;

        public Address() {
        }

        public Address(String city, String street, Integer appartment) {
            super();
            this.city = city;
            this.street = street;
            this.appartment = appartment;
        }
    }

    /**
     * Записывает данные Person в файл FILE_OBJECT_DATA,
     * используя ObjectOutputStream
     * @param person
     */
    public void writeToFile(Person person) {
        try {
            ObjectOutputStream output = new ObjectOutputStream(
                    new FileOutputStream(FILE_OBJECT_DATA));
            output.writeObject(person);
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает и возвращает данные из файла FILE_OBJECT_DATA
     * @return
     */
    public Person readFromFile() {
        Person person = null;
        try {
            ObjectInputStream input = new ObjectInputStream(
                    new FileInputStream(FILE_OBJECT_DATA));
            person = (Person) input.readObject();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return person;
    }

    @Test
    public void testObjectSerialize() {
        Person person = new Person("Иван Иванов", new Date("2000/10/10"));
        writeToFile(person);
        log("Age:"+person.age);
        Person personFromFile = readFromFile();
        log("Name from file:"+personFromFile.name);
        log("Age from file:"+personFromFile.age);
        assertEquals(personFromFile.name, personFromFile.name);
        assertNotEquals("Поле age не помечено transient", person.age, personFromFile.age);
    }

    /**
     * Раскомментируйте @Test и отредактируйте класс Address,
     * чтобы он позволял сериализацию адресов
     */
    @Test
    public void testPersonAddressSerialize() {
        Person person = new Person("Иван Иванов", new Date("2000/10/10"));
        Address address = new Address("Москва", "Тверская", 10);
        person.addAddress(address);
        person.firstAddress = address;
        writeToFile(person);

        log("Age:" + person.age);
        Person personFromFile = readFromFile();
        log("Name from file:" + personFromFile.name);
        log("Age from file:" + personFromFile.age);
        assertEquals(personFromFile.name, personFromFile.name);
        assertNotEquals(person.age, personFromFile.age);

        Address addressFromFile = personFromFile.addressList.get(0);
        log("City from file:" + addressFromFile.city);
        log("Appartment from file:" + addressFromFile.appartment);
        assertEquals(addressFromFile.city, address.city);
        assertEquals(addressFromFile.appartment, address.appartment);
    }
}
