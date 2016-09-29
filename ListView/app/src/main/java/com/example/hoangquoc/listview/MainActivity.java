package com.example.hoangquoc.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import ArrayAdater.MyArrayAdapter;
import model.Job;

public class MainActivity extends AppCompatActivity {

    ArrayList<Job>arrJob=new ArrayList<Job>();
    //Sử dụng MyArrayAdapter thay thì ArrayAdapter
    MyArrayAdapter adapter=null;
    ListView lvJob=null;
    Button btnSave;
    ImageButton btnRemoveAll,btnEdit;
    EditText editName,editNotes;
    RadioGroup radStatus;
    Job job;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            job = (Job) data.getExtras().getSerializable("Job");
            position = data.getIntExtra("Position", 0);
            Job updatedJob = arrJob.get(position);
            updatedJob.setName(job.getName());
            updatedJob.setNotes(job.getNotes());
            updatedJob.setStatus(job.getStatus());
            adapter.notifyDataSetChanged();
        }
    }

    private void addControls() {
        btnSave=(Button) findViewById(R.id.btnSave);
        btnRemoveAll=(ImageButton) findViewById(R.id.btndelete);
        editName=(EditText) findViewById(R.id.editName);
        editNotes=(EditText) findViewById(R.id.editNotes);

        radStatus =(RadioGroup) findViewById(R.id.radStatus);

        lvJob=(ListView) findViewById(R.id.lvJob);
        arrJob=new ArrayList<Job>();
        //Khởi tạo đối tượng adapter và gán Data source
        adapter=new MyArrayAdapter(this, R.layout.my_item_layout, arrJob/*thiết lập data source*/);
        lvJob.setAdapter(adapter);//gán Adapter vào Lisview
    }
    private void addEvent() {
        btnSave.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Input();
            }
        });
        btnRemoveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Delete();
            }
        });
        lvJob.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Job job = arrJob.get(position);
                Intent intentEditJob = new Intent(MainActivity.this,EditActivity.class);
                // gửi đối tượng
                intentEditJob.putExtra("Position",  position);
                intentEditJob.putExtra("Job",  job);
                startActivityForResult(intentEditJob, 200);
            }
        });
    }

    public void Input()
    {
        String name=editName.getText()+"";
        String notes=editNotes.getText()+"";
        String stt=null;
        if(radStatus.getCheckedRadioButtonId()==R.id.radDone) {
            stt = "Done";

        }else {
            stt = "ToDo";

        }
        Job job=new Job();
        job.setName(name);
        job.setNotes(notes);
        job.setStatus(stt);
        //Đưa vào danh sách
        arrJob.add(job);
        //jobToEdit(editNotes.editName.getText());
        //gọi hàm cập nhật giao diện
        adapter.notifyDataSetChanged();

        editNotes.setText("");
        editName.setText("");

    }

    public void Delete()
    {

        for(int i=lvJob.getChildCount()-1;i>=0;i--)
        {
            //lấy ra dòng thứ i trong ListView

            View v=lvJob.getChildAt(i);
            //Ta chỉ lấy CheckBox ra kiểm tra
            CheckBox chk=(CheckBox) v.findViewById(R.id.chkitem);
            //Nếu nó Checked thì xóa ra khỏi arrEmployee
            if(chk.isChecked())
            {
                //xóa phần tử thứ i ra khỏi danh sách
                arrJob.remove(i);
            }
        }
        //Sau khi xóa xong thì gọi update giao diện
        adapter.notifyDataSetChanged();
    }
}
