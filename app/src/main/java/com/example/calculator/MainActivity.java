package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addText(String sim) { //Метод для добавляения символа в строку ввода (нужен для удобства) Вызывает кнопками калькулятора
        final TextView changingText = findViewById(R.id.res);
        final TextView example = findViewById(R.id.invisibles);
        if (changingText.getText() == example.getText()) { //Эта конструкция приравнивает значение поля к 0 для корректного распознавания
            changingText.setText("0");
        }

        if (changingText.getText().toString().substring(0,1) == "0" & sim != "+" & sim != "-" & sim != "*" & sim != "/" & sim != "^" & sim != ",")
            changingText.setText(sim); //Удаляет 0 в начале, если вставляешь цифру
        else
            changingText.setText(changingText.getText() + sim); //просто добавляет символ
    }

    private void delText(Integer count) { //Метод для удаления символов
        final TextView changingText = findViewById(R.id.res);
        final TextView example = findViewById(R.id.invisibles);
        if (count == 1) { //count определяет кол-во символов для удаления. 1 выдает кнопка B (стирает 1 символ в конце. Справка: B - от Backspace)
            if (changingText.getText().length() > 0)
                changingText.setText(changingText.getText().subSequence(0, changingText.getText().length() - 1));
            if (changingText.getText().length() == 0) //Если длина текста равна 0 после стирания, то приравнивает текст к 0
                changingText.setText("0");
        }
        if (count == 0) { //Нажатие на C. Стирает весь текст.
            changingText.setText("0");

        }
    }

    public void onClick0(View v) {
        addText("0");
    } //1000 и 1 кнопкка для ввода символов
    public void onClick1(View v) {
        addText("1");
    }
    public void onClick2(View v) {
        addText("2");
    }
    public void onClick3(View v) {
        addText("3");
    }
    public void onClick4(View v) {
        addText("4");
    }
    public void onClick5(View v) {
        addText("5");
    }
    public void onClick6(View v) {
        addText("6");
    }
    public void onClick7(View v) {
        addText("7");
    }
    public void onClick8(View v) {
        addText("8");
    }
    public void onClick9(View v) {
        addText("9");
    }
    public void onClickComma(View v) {
        addText(".");
    }
    public void onClickC(View v) {
        delText(0);
    }
    public void onClickB(View v) {
        delText(1);
    }
    public void onClickPlus(View v) {
        addText("+");
    }
    public void onClickMinus(View v) {
        addText("-");
    }
    public void onClickMulti(View v) {
        addText("*");
    }
    public void onClick9Division(View v) {
        addText("/");
    }
    public void onClickPow(View v) {
        addText("^");
    }

    public void onClickEq(View v) { //Кнопка =
        final TextView changingText = (TextView) findViewById(R.id.res);
        try {
            String result = changingText.getText().toString().replaceAll("-", " - ")
                    .replaceAll("\\+", " + ")
                    .replaceAll("\\*", " * ")
                    .replaceAll("/", " / ")
                    .replaceAll("\\^", " ^ "); //Добавляет вокруг символов пробелы (нужно для будущего разделения на части))
            Double res1 = Calculate.calc(result); //Все расчеты в классе Calculate
            if (res1 == res1.intValue())
                changingText.setText(String.valueOf(res1.intValue())); //Убирает дробную часть равную 0
            else
                changingText.setText(String.valueOf(res1)); //Вывод ответа
            if (res1 == Double.NEGATIVE_INFINITY)
                changingText.setText("Делить на 0 нельзя");
        }
        catch (Exception e) {
            changingText.setText("Ошибка");
        }
    }
}

