package org.test;
import static org.junit.Assert.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;
/**
 * Created by alykhouzov on 01.03.2016.
 */
public class RegExpTutor extends Tutor {
    class Email {
        String name;
        String domainName;
        String domainZone;
    }

    public Email getEmail(String emailString) {
        String[] tmpResult = emailString.split("[.|@]");

        Email email = new Email();
        if (tmpResult != null && tmpResult.length==3) {
            email.name = tmpResult[0];
            email.domainName = tmpResult[1];
            email.domainZone = tmpResult[2];
        }
        return email;
    }

    /**
     * Возвращает список строк по текстовому представлению строк:
     * принимает строку
     * "Список животных: Корова, Гусь, Кошка, Собака, Слон, Заяц, Змея, Курица, Лошадь, Человек."
     * и возвращает массив из отдельных животных
     */
    public String[] getAnimalsArray(String animalsString) {

        return animalsString.replace(".","").substring("Список животных:".length()+1).split(", ");
    }

    @Test
    public void testGetEmail() {
        Email email = getEmail("ivanov@mail.ru");
        assertEquals("ivanov", email.name);
        assertEquals("mail", email.domainName);
        assertEquals("ru", email.domainZone);

    }

    @Test
    public void testGetAnimalsArray() {
        String [] animals =
                {"Корова", "Гусь", "Кошка", "Собака", "Слон",
                        "Заяц", "Змея", "Курица", "Лошадь", "Человек"};
        String animalsString = "Список животных: Корова, Гусь, Кошка, Собака, Слон, "+
                "Заяц, Змея, Курица, Лошадь, Человек.";
        assertArrayEquals(animals, getAnimalsArray(animalsString));
    }
}
