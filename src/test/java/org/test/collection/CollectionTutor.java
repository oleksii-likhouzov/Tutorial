package org.test.collection;

import java.util.*;

import org.junit.Test;
import org.test.Tutor;

/**
 * 1) Имплементрируйте метод joinByCycle(). Проверьте результат.
 * Имплементируйте методы getAnimalsList(), getAnimalsSet(), getAnimalsOrderedSet()
 * и верните их значения.
 * Как отличаются полученные результаты?
 * Имплементируйте метод joinByIterator()
 */
public class CollectionTutor extends Tutor {

    String[] animals =
            {"Корова", "Гусь", "Кошка", "Собака", "Слон",
                    "Заяц", "Змея", "Курица", "Лошадь", "Человек",
                    "Корова", "Змея"};

    /**
     * Метод должен возвращать ArrayList со всеми животными
     */
    public List<String> getAnimalsList() {
        List<String> animalList = new ArrayList<String>(animals.length);
        Collections.addAll(animalList, animals);

        return animalList;
    }

    /**
     * Метод должен возвращать Set со всеми животными
     */
    public Set<String> getAnimalsSet() {
        Set<String> animalSet = new HashSet<String>(animals.length);
        Collections.addAll(animalSet, animals);
        return animalSet;
    }


    /**
     * Метод должен брать коллекцию строк c
     * и возвращать одну строку, состоящую из
     * элементов коллекции, соединенных запятой,
     * используя цикл for
     */
    public String joinByCycle(Collection<?> c) {
        StringBuilder result = new StringBuilder();
        for (Object element : c) {
            if (result.toString().length() != 0) {
                result.append(",");
            }
            result.append(element);
        }
        return result.toString();
    }

    /**
     * Метод должен брать коллекцию строк c
     * и возвращать одну строку, состоящую из
     * элементов коллекции, соединенных запятой,
     * используя итератор
     */
    public String joinByIterator(Collection<?> c) {
        Iterator iter = c.iterator();
        StringBuilder result = new StringBuilder();

        while (iter.hasNext()) {
            String element = (String) iter.next();
            if (result.toString().length() != 0) {
                result.append(",");
            }
            result.append(element);
        }
        return result.toString();
    }

    @Test
    public void testCollections() {
        log("getAnimalsList: " + joinByCycle(Arrays.asList(animals)));

        log("getAnimalsList: " + joinByCycle(getAnimalsList()));
        log("getAnimalsSet: " + joinByCycle(getAnimalsSet()));

        log("getAnimalsList by iterator: " + joinByIterator(getAnimalsList()));
    }

}



