package com.ygg.uni_androiddev_4;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisciplineRegisterActivity extends AppCompatActivity {

    private ArrayList<Discipline> registeredDisciplines = new ArrayList<>();

    private void refreshDisciplines() {
        // create a nicely formatted list of our disciplines
        StringBuilder discListBuilder = new StringBuilder();
        int num = 1;
        for (Discipline dscp : registeredDisciplines) {
            discListBuilder.append(num).append(". ")
                    .append(dscp.getName()).append(" (")
                    .append("on ").append(dscp.getLessonDay())
                    .append(", at ").append(dscp.getTime())
                    .append(", taught by ").append(dscp.getTeacher())
                    .append(")\n");
            num++;
        }
        String finalList = discListBuilder.toString();

        // update the text view
        final TextView discListView = findViewById(R.id.reg_list);
        discListView.setText(finalList);
    }

    // button's function
    public void addDisciplineData() {
        registeredDisciplines.add(new Discipline("QQ", "Monday", "69.69pm", "Ygg"));
        refreshDisciplines();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_register);

        final Button dataButton = findViewById(R.id.reg_button);
        dataButton.setOnClickListener(v -> addDisciplineData());

        final TextView discListView = findViewById(R.id.reg_list);
        discListView.setMovementMethod(new ScrollingMovementMethod());
    }
}