package org.test.collection;

import org.test.Tutor;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
public class CollectionUtilitiesTutor extends Tutor{
    String [] animals =
            {"Корова", "Гусь", "Кошка", "Собака", "Слон",
                    "Заяц", "Змея", "Курица", "Лошадь", "Человек",
                    "Корова", "Змея"};

    public void print(Collection<?> c) {
        StringBuilder builder = new StringBuilder();
        Iterator<?> iterator = c.iterator();
        while(iterator.hasNext()) {
            builder.append(iterator.next());
            builder.append(" ");
        }
        log(builder.toString());
    }

    @Test
    public void testUtilities() {
        List<String> list = Arrays.asList(animals);
        log("== print the resulting list");
        print(list);

        log("== print the shuffled list");
        Collections.shuffle(list);
        print(list);

        log("== print the sorted list (used natural ordering)");
        Collections.sort(list);
        print(list);

        log("== binary Search of Слон after sorting: "+Collections.binarySearch(list, "Слон"));

        log("== print the reversed list");
        Collections.reverse(list);
        print(list);

        log("== binary Search of Слон without sorting: "+Collections.binarySearch(list, "Слон"));

        log("== print the sorted by length list of word");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length()-o2.length();
            }
        });
        print(list);

        log("== max (used natural ordering): "+Collections.max(list));
        log("== min (used natural ordering): "+Collections.min(list));

        log("== frequency of Корова: "+Collections.frequency(list, "Корова"));
        log("== frequency of Человек: "+Collections.frequency(list, "Человек"));

        log("== replace Корова to Буренка: ");
        Collections.replaceAll(list, "Корова", "Буренка");
        print(list);

        log("== swap: меняем местами значения первое и последнее: ");
        Collections.swap(list, 0, list.size()-1);
        print(list);

        log("== rotate: сдвигаем значения массива на 2: ");
        Collections.rotate(list, 2);
        print(list);

        log("== indexOfSubList: ищем подсписок в текущем списке: подсписок: ");
        List<String> subList = Arrays.asList(new String[]{list.get(5),list.get(6)});
        print(subList);
        log("место подсписка: "+Collections.indexOfSubList(list, subList));

        log("== fill: заполняем список одинаковыми значениями: ");
        Collections.fill(list, ".");
        print(list);
    }
}
