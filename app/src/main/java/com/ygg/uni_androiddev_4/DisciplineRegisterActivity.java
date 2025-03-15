package com.ygg.uni_androiddev_4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
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
                    .append("; extra: ").append(dscp.getComment())
                    .append(")\n");
            num++;
        }
        String finalList = discListBuilder.toString();

        // update the text view
        final TextView discListView = findViewById(R.id.reg_list);
        discListView.setText(finalList);
    }

    // register a callback to launch SpecsActivity for a result
    ActivityResultLauncher<Intent> specsResCallback = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult res) {
                    if (res.getResultCode() != Activity.RESULT_OK) { return; }

                    Bundle data = res.getData().getExtras();
                    Discipline newDiscipline = (Discipline) data.get("discipline");
                    registeredDisciplines.add(newDiscipline);

                    refreshDisciplines();
                    ((EditText) findViewById(R.id.reg_edit)).getText().clear();
                }
            }
    );

    // button's function
    public void addDisciplineData() {
        // get text from edittext
        final EditText addDiscipline = findViewById(R.id.reg_edit);
        String discText = addDiscipline.getText().toString().trim();

        // make sure its not completely empy
        if (discText.isEmpty()) {
            Toast badDisciplineToast = Toast.makeText(getApplicationContext(), R.string.bad_disc, Toast.LENGTH_SHORT);
            badDisciplineToast.show();
            return;
        }

        // launch
        Intent specsIntent = new Intent(getApplicationContext(), DisciplineSpecificationsActivity.class);
        specsIntent.putExtra("disc name", discText);
        specsResCallback.launch(specsIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_register);

        Bundle data = getIntent().getExtras();

        final Button dataButton = findViewById(R.id.reg_button);
        dataButton.setOnClickListener(v -> addDisciplineData());

        final TextView discListView = findViewById(R.id.reg_list);
        discListView.setMovementMethod(new ScrollingMovementMethod());

        final TextView nameView = findViewById(R.id.reg_welcome_first);
        nameView.setText(data.getString("first name"));
        final TextView surnameView = findViewById(R.id.reg_welcome_last);
        surnameView.setText(data.getString("last name"));
    }
}