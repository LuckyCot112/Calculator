package com.example.calculator;
import java.util.*;

public class Calculate {
    public static Double calc(String primer) {

        List<String> strList = new ArrayList<>();
        for (String listElement : primer.trim().split(" ")) {
            strList.add(listElement);
        } //Стринговый список с элементами
        while (strList.size() != 1) {

            Double result = 0d; //Результат одного действия

            if (strList.indexOf("^") != -1) {
                int index = strList.indexOf("^");
                Double n1 = Double.valueOf(strList.get(index - 1));
                Double n2 = Double.valueOf(strList.get(index + 1));
                result = Math.pow(n1, n2);
                strList.add(index - 1, String.valueOf(result));
                strList.remove(index + 2);
                strList.remove(index + 1);
                strList.remove(index);  //Находит символ действия, берет 2 соседних числа, выполняте это действие, добавляет в список элемент с результатом
                                        //удаляет уже решенное, выполняет следующее действие, пока список не сожмется до 1 символа (ответа)

            } if (strList.indexOf("/") != -1) {
                int index = strList.indexOf("/");
                if (Double.valueOf(strList.get(index + 1)) == 0) {
                    return Double.NEGATIVE_INFINITY;
                }

                result = Double.valueOf(strList.get(index - 1)) / Double.valueOf(strList.get(index + 1));
                strList.add(index - 1, String.valueOf(result));
                strList.remove(index + 2);
                strList.remove(index + 1);
                strList.remove(index);
            } else if (strList.indexOf("*") != -1) {
                int index = strList.indexOf("*");
                result = Double.valueOf(strList.get(index - 1)) * Double.valueOf(strList.get(index + 1));
                strList.add(index - 1, String.valueOf(result));
                strList.remove(index + 2);
                strList.remove(index + 1);
                strList.remove(index);
            } else if (strList.indexOf("-") != -1) {
                int index = strList.indexOf("-");
                int lastIndex = strList.lastIndexOf("-");
                if (index == 0) {
                    result = 0.0 - Double.valueOf(strList.get(index + 1));
                    strList.add(0, String.valueOf(result));
                    strList.remove(2);
                    strList.remove(1);
                } else if ((lastIndex - 2 > 0) && (strList.get(lastIndex - 2).equals("-"))) {
                    result = Double.valueOf(strList.get(lastIndex + 1)) + Double.valueOf(strList.get(lastIndex - 1));
                    strList.add(lastIndex - 1, String.valueOf(result));
                    strList.remove(lastIndex + 2);
                    strList.remove(lastIndex + 1);
                    strList.remove(lastIndex);
                } else {
                    result = Double.valueOf(strList.get(index - 1)) - Double.valueOf(strList.get(index + 1)); 
                    strList.add(index - 1, String.valueOf(result));
                    strList.remove(index + 2);
                    strList.remove(index + 1);
                    strList.remove(index);  //Для минуса есть 3 случая.
                                            // 1) если минус находится в самом начале (получает -x)
                                            // 2) если уравнение подобно ".. - 5 - 2 .." Складывает 2 числа, получая ".. - 7 .."
                                            // 3) остальные случаи. Просто вычитает b из a
                                            // Прим. минус нельзя ввести вручную в начале строки, но можно выполнить операцию "0-x"
                }
            } else if (strList.indexOf("+") != -1) {
                int index = strList.indexOf("+");
                result = Double.valueOf(strList.get(index - 1)) + Double.valueOf(strList.get(index + 1));
                strList.add(index - 1, String.valueOf(result));
                strList.remove(index + 2);
                strList.remove(index + 1);
                strList.remove(index);
            }
            //if ((strList.indexOf("*") == -1) && (strList.indexOf("/") == -1) && (strList.indexOf("+") == -1) && (strList.indexOf("-") == -1)) {
                //return result;
            //}
        }
        return Double.valueOf(strList.get(0)); //Возвращает первый (и единственный) элемент таблицы т.е. ответ
    }
}
