package com.ygg.uni_androiddev_4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DisciplineSpecificationsActivity extends AppCompatActivity {

    // button function
    private void clickDone(String discName) {
        String day = ((Spinner) findViewById(R.id.specs_day)).getSelectedItem().toString();
        String time =((EditText) findViewById(R.id.specs_time)).getText().toString();
        String teacher =((EditText) findViewById(R.id.specs_teacher)).getText().toString().trim();
        String comment =((EditText) findViewById(R.id.specs_comment)).getText().toString().trim();

        if (day.isEmpty() || time.isEmpty() || teacher.isEmpty()) {
            Toast badDataToast = Toast.makeText(getApplicationContext(), getString(R.string.bad_disc_data), Toast.LENGTH_SHORT);
            badDataToast.show();
            return;
        }

        if (!time.matches("[0-9]{2}:[0-9]{2}")) {
            Toast badTimeToast = Toast.makeText(getApplicationContext(), getString(R.string.bad_time), Toast.LENGTH_SHORT);
            badTimeToast.show();
            return;
        }

        Discipline discipline = new Discipline(discName, day, time, teacher);
        if (!comment.isEmpty()) { discipline.setComment(comment); }

        Intent resultIntent = new Intent();
        resultIntent.putExtra("discipline", discipline);
        setResult(RESULT_OK, resultIntent);

        Toast successToast = Toast.makeText(getApplicationContext(), getString(R.string.data_passed), Toast.LENGTH_SHORT);
        successToast.show();

        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discipline_specifications);

        Bundle data = getIntent().getExtras();
        String discName = data.getString("disc name");

        final TextView currentDiscipline = findViewById(R.id.specs_discname);
        currentDiscipline.setText(getString(R.string.specs_regfor, discName));

        final Button doneButton = findViewById(R.id.specs_button);
        doneButton.setOnClickListener(v -> clickDone(discName));
    }
}