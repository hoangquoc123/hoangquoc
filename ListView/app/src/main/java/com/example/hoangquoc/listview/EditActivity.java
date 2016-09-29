package com.example.hoangquoc.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import java.io.Serializable;

import model.Job;

/**
 * Created by Hoang Quyen on 9/28/2016.
 */
public class EditActivity extends AppCompatActivity {

    Button btnSaveLayout;
    // ImageButton btnEditLayout;
    EditText editNameLayout, editNotesLayout;
    RadioGroup radStatusLayout;
    Job job;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);
        addControls();
        // addEvent();


    }


    private void addControls() {
        job = (Job) getIntent().getExtras().getSerializable("Job");
        position = getIntent().getIntExtra("Position", 0);

        btnSaveLayout = (Button) findViewById(R.id.btnSaveLayout);

        editNameLayout = (EditText) findViewById(R.id.editNameLayout);
        editNotesLayout = (EditText) findViewById(R.id.editNotesLayout);
        radStatusLayout = (RadioGroup) findViewById(R.id.radStatusLayout);

        editNotesLayout.setText(job.getNotes());
        editNameLayout.setText(job.getName());

        btnSaveLayout.setOnClickListener(OnClickSave);
    }



    View.OnClickListener OnClickSave = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
// Sửa đổi các thông tin của
            String name=editNameLayout.getText()+"";
            String notes=editNotesLayout.getText()+"";
            String stt=null;
            if(radStatusLayout.getCheckedRadioButtonId()==R.id.radDone) {
                stt = "Done";

            }else {
                stt = "ToDo";

            }
            Job job=new Job();
            job.setName(name);
            job.setNotes(notes);
            job.setStatus(stt);
            //  job.setName(editNameLayout.getText().toString());
            //job.setNotes(editNotesLayout.getText().toString());
            //job.setStatus(radStatusLayout.getText());
            // Gửi  lại MainActivity
            Intent intentBack = new Intent();
            intentBack.putExtra("Job",  job);
            intentBack.putExtra("Position",  position);

            setResult(RESULT_OK, intentBack);
            finish();
        }
    };

}
