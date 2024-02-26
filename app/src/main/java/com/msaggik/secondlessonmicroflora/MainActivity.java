package com.msaggik.secondlessonmicroflora;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // поля
    private TextView output;
    private Button button;
    private int count = 0; // счётчик подсчёта количества микрофлоры
    private int preCount = 0; // вспомогательный счётчик количества предыдущего поколения количества микрофлоры

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Старт активности", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Активность доступна для взаимодействия", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Активность не доступна для взаимодействия", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Активность остановлена и пропала с экрана", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Активность уничтожена", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "Активность создана", Toast.LENGTH_SHORT).show();

        // привязка полей к разметке
        output = findViewById(R.id.output);
        button = findViewById(R.id.button);

        // обработка нажатия кнопки
        button.setOnClickListener(listener);
    }

    // создание слушателя
    private final View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            countFibonacci(); // исполнение одного цикла роста популяции
            output.setText(String.valueOf(count)); // вывод результатов на экран
        }
    };

    //  метод вычисления следующего поколения микрофлоры
    private int countFibonacci() {
        if (count == 0 && preCount == 0) { // условие начала отсчёта
            count = 1;
            preCount = 0; // появление первой бактерии
        }
        if (count == 1 && preCount == 0) { // условие первого поколения
            count = 1;
            preCount = 1; // деление бактерии на две
        } else { // в следующих поколениях происходит деление в соответствии с принципом последовательности ряда Фибоначчи
            int pre = count; // буферное сохранение счётчика популяции предыдущего поколения
            count = count + preCount; // подсчёт счётчика популяции текущего поколения
            preCount = pre; // переинициализации счётчика популяции предыдущего поколения
        }
        return count;
    }
}