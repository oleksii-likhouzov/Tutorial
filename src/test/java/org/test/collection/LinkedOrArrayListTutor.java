package org.test.collection;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import org.junit.Test;
import org.test.Tutor;

public class LinkedOrArrayListTutor extends Tutor {
    long start;

    String [] animals =
            {"Корова", "Гусь", "Кошка", "Собака", "Слон",
                    "Заяц", "Змея", "Курица", "Лошадь", "Человек",
                    "Корова", "Змея"};

    ArrayList<String> arrayList = new ArrayList<String>();
    LinkedList<String> linkedList = new LinkedList<String>();

    public String getRandomAnimal() {
        int index = (int)(Math.random()*animals.length);
        return animals[index];
    }

    public void addAnimalsToList() {
        arrayList.add(getRandomAnimal());
    }

    public void nextTimeLog(String st) {
        log("Time of work for "+st+": "+(new Date().getTime()-start));
        start = new Date().getTime();
    }

    @Test
    public void testList() {
        start = new Date().getTime();
        for (int i=0;i<1000000;i++) {
            arrayList.add(getRandomAnimal());
        }
        nextTimeLog("arrayList add");

        for (int i=0;i<1000000;i++) {
            linkedList.add(getRandomAnimal());
        }
        nextTimeLog("linkedList add");

        for (int i=0;i<100000;i++) {
            arrayList.get(1000);
        }
        nextTimeLog("arrayList get()");

        for (int i=0;i<100000;i++) {
            linkedList.get(1000);
        }
        nextTimeLog("linkedList get()");

        for (int i=0;i<1000;i++) {
            arrayList.remove(1000);
        }
        nextTimeLog("arrayList remove()");

        for (int i=0;i<1000;i++) {
            linkedList.remove(1000);
        }
        nextTimeLog("linkedList remove()");

        for (int i=0;i<1000;i++) {
            arrayList.add(1000,"Динозавр");
        }
        nextTimeLog("arrayList insert in the middle");

        for (int i=0;i<1000;i++) {
            linkedList.add(1000,"Динозавр");
        }
        nextTimeLog("linkedList insert in the middle");

        for (int i=0;i<1000;i++) {
            linkedList.add(1000,"Динозавр");
        }

        for (int i=0;i<100;i++) {
            arrayList.contains("Динозавр1");
        }
        nextTimeLog("arrayList contains");

        for (int i=0;i<100;i++) {
            linkedList.contains("Динозавр1");
        }
        nextTimeLog("linkedList contains");
    }
}
