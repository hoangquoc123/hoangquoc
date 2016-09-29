package ArrayAdater;

import java.util.ArrayList;
import android.app.Activity;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hoangquoc.listview.EditActivity;
import com.example.hoangquoc.listview.MainActivity;
import com.example.hoangquoc.listview.R;

import model.Job;

/**
 * Created by Hoang Quyen on 9/27/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<Job> {
    Activity context=null;
    ArrayList<Job> myArray=null;
    int layout;

    public MyArrayAdapter(Activity  context, int layout, ArrayList<Job> myArray) {
        super(context, 0, myArray);
        this.context=context;
        this.layout=layout;
        this.myArray=myArray;
    }

    public View getView(final int position, View convertView,
                        ViewGroup parent) {

        LayoutInflater inflater= context.getLayoutInflater();
        convertView=inflater.inflate(layout, parent, false);

        if(myArray.size()>0 && position>=0)
        {

            final TextView txtdisplay=(TextView) convertView.findViewById(R.id.txtitem);

            final Job job =myArray.get(position);

            txtdisplay.setText(job.toString());

            convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Job job = getItem(position);
                Intent intentEditJob = new Intent(getContext(),EditActivity.class);
                // gửi đối tượng
                intentEditJob.putExtra("Position",  position);
                intentEditJob.putExtra("Job",  job);
                getContext().startActivity(intentEditJob);
            }
        });
        }

        return convertView;
    }
}

