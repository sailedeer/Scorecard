package sailedeer.scorecard.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sailedeer.scorecard.R;
import sailedeer.scorecard.data.Course;
import sailedeer.scorecard.data.Player;
import sailedeer.scorecard.util.Constants;

public class NewCourseActivity extends AppCompatActivity implements View.OnClickListener{

    Course toEdit;
    private Intent newCourseIntent;
    private ActionBar ab;
    private EditText edtTxtSlope;
    private EditText edtTxtName;
    private Button btnAddCourse;
    private boolean isEditing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_nc);
        setSupportActionBar(toolbar);

        ab = getSupportActionBar();

        edtTxtSlope = (EditText) findViewById(R.id.edtTxtSlope);
        edtTxtName = (EditText) findViewById(R.id.edtTxtCourseName);
        btnAddCourse = (Button) findViewById(R.id.btnAddCourse);

        btnAddCourse.setOnClickListener(this);

        if (getIntent().getAction().equals(Constants.EDIT_COURSE))
        {
            isEditing = true;
        }

        if (isEditing) {
            ab.setTitle("Edit Course");
            toEdit = (Course) getIntent().getSerializableExtra(Constants.K_COURSE);
            edtTxtName.setText(toEdit.getName());
            edtTxtSlope.setText(Integer.toString(toEdit.getSlope()));
            btnAddCourse.setText("Save Course");
        }
    }

    @Override
    public void onClick(View v)
    {
        try{
            String name = edtTxtName.getText().toString();
            try {
                int slope = Integer.parseInt(edtTxtSlope.getText().toString());
                if (edtTxtName.getText().equals("") || edtTxtSlope.getText().equals("")) {
                    Toast toast = Toast.makeText(this, "Bad data1", Toast.LENGTH_LONG);
                    toast.show();
                } else if (name.isEmpty()) {
                    Toast toast = Toast.makeText(this, "Bad data2", Toast.LENGTH_LONG);
                    toast.show();
                } else if (slope == 0) {
                    Toast toast = Toast.makeText(this, "Bad data3", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Course c = new Course(name, slope);
                    if (isEditing) c.setId(toEdit.getId());
                    newCourseIntent = new Intent();
                    newCourseIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    newCourseIntent.setAction(isEditing ? Constants.EDIT_COURSE : Constants.ADD_COURSE);
                    newCourseIntent.setClass(this, MainActivity.class);
                    //if (isEditing) newCourseIntent.putExtra(Constants.K_COURSE + "_edit", toEdit);
                    newCourseIntent.putExtra(Constants.K_COURSE, c);
                    startActivity(newCourseIntent);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (Exception sne) {
            sne.printStackTrace();
        }
    }
}
