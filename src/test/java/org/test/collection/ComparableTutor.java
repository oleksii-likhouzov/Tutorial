package org.test.collection;


import org.test.Tutor;
import org.junit.Test;

import java.util.*;

/**
 *	1) Имплементируйте метод Set<Animal> getAnimalsOrderedByNameSet()
 * 		и метод Set<Animal> getAnimalsOrderedByNameSetDesc()
 */
public class ComparableTutor extends Tutor{
    String [] animals =
            {"Корова", "Гусь", "Кошка", "Собака", "Слон",
                    "Заяц", "Змея", "Курица", "Лошадь", "Человек",
                    "Корова", "Змея"};

    class compAnimals implements Comparator {
        @Override
        public int compare(Object o1, Object o2) {
            return  - ((Animal)o1).name.compareTo(((Animal)o2).name);
        }
    }
    class Animal implements  Comparable {
        String name;
        public Animal(String name) {
            this.name = name;
        }
        @Override
        public String toString() {
            return name;
        }
        @Override
        public int compareTo(Object o) {
            return name.compareTo(((Animal)o).name);
        }
    }

    /**
     * Метод должен возвращать Set экземпляров класса Animal,
     * упорядоченный по имени.
     * Для этого используйте TreeSet и имплементируйте в классе Animal
     * интерфейс Comparable.
     */
    public Set<Animal> getAnimalsOrderedByNameSet() {
        Set <Animal> animalSet = new TreeSet<Animal>();
        for(String animal: animals) {
            animalSet.add(new Animal(animal));
        }
        return animalSet;
    }

    /**
     * Метод должен возвращать Set экземпляров класса Animal,
     * упорядоченный по имени в обратном порядке.
     * Для этого используйте TreeSet и передайте ему в качестве параметра
     * имплементацию класса Comparator.
     *
     */
    public Set<Animal> getAnimalsOrderedByNameSetDesc() {
        // Set <Animal> animalSet = new TreeSet<Animal>(new compAnimals());
        Set <Animal> animalSet = new TreeSet<Animal>(new Comparator()
                                                         {
                                                             @Override
                                                             public int compare(Object o1, Object o2) {
                                                                 return  - ((Animal)o1).name.compareTo(((Animal)o2).name);
                                                             }
                                                         }
                                                    );
        for(String animal: animals) {
            animalSet.add(new Animal(animal));
        }
        return animalSet;
    }

    public String joinByCycle(Collection<?> c) {
        if (c==null) return "";
        StringBuilder builder = new StringBuilder();
        for (Object s: c) {
            builder.append(s);
            if (builder.length()>0) builder.append(", ");
        }
        return builder.toString();
    }

    @Test
    public void testCollections() {
        log("getAnimalsList: "+joinByCycle(Arrays.asList(animals)));

        log("getAnimalsOrderedByNameSet: "+joinByCycle(getAnimalsOrderedByNameSet()));
        log("getAnimalsOrderedByNameSetDesc: "+joinByCycle(getAnimalsOrderedByNameSetDesc()));
    }
}
