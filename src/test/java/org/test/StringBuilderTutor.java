package org.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by alykhouzov on 01.03.2016.
 */
public class StringBuilderTutor extends Tutor {
    String[] animals =
            {"Корова", "Гусь", "Кошка", "Собака", "Слон",
                    "Заяц", "Змея", "Курица", "Лошадь", "Человек"};

    /**
     * Метод должен вернуть строку:
     * "Список животных: Корова, Гусь, ..., Человек."
     * Для реализации используйте StringBuilder
     */
    public String getAnimalsString() {
        StringBuilder builder = new StringBuilder("Список животных: ");
        for (int i = 0; i < animals.length; i++) {
            builder.append(animals[i]);
            if (i != animals.length - 1) {
                builder.append(", ");
            }
        }
        builder.append(".");

        return builder.toString();
    }

    @Test
    public void testGetAnimalsString() {
        String animalsString = getAnimalsString();
        assertEquals("Список животных: Корова, Гусь, Кошка, Собака, Слон, " +
                "Заяц, Змея, Курица, Лошадь, Человек.", animalsString);
    }
}
