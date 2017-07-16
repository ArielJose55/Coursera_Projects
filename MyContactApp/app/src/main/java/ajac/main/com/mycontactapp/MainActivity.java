package ajac.main.com.mycontactapp;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private TextView tvName;
    private Button bDate;
    private TextView tvPhone;
    private  TextView tvEmail;
    private TextView tvDescription;

    private Integer year, month, day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName          = (TextView) findViewById(R.id.editTextName);
        bDate           = (Button) findViewById(R.id.button_date);
        tvPhone         = (TextView) findViewById(R.id.editTextPhone);
        tvEmail         = (TextView) findViewById(R.id.editTextEmail);
        tvDescription   = (TextView) findViewById(R.id.editTextDescription);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            tvName.setText(bundle.getString("NAME_FORM"));
            tvPhone.setText(bundle.getString("PHONE_FORM"));
            tvEmail.setText(bundle.getString("EMAIL_FORM"));
            tvDescription.setText(bundle.getString("DESCRITION_FORM"));
            String attribute_date[] = bundle.getString("DATE_FORM").split("/");
            day = Integer.parseInt(attribute_date[0]);
            month = Integer.parseInt(attribute_date[1]);
            year = Integer.parseInt(attribute_date[2]);
        }

        bDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });
    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.year = year;
        this.month = monthOfYear;
        this.day = dayOfMonth;
        view.dismiss();
    }

    public void actionNext(View v){
        if(isAtributesFormEmply()){
            Snackbar.make(v,getResources().getString(R.string.warning_forum),Snackbar.LENGTH_SHORT).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, ConfirmFormActivity.class);
        intent.putExtra("NAME_FORM",tvName.getText().toString());
        intent.putExtra("PHONE_FORM",tvPhone.getText().toString());
        intent.putExtra("EMAIL_FORM",tvEmail.getText().toString());
        intent.putExtra("DESCRITION_FORM",tvDescription.getText().toString());
        intent.putExtra("DATE_FORM",day+"/"+month+"/"+year);
        startActivity(intent);
        finish();
    }

    private boolean isAtributesFormEmply(){
        if(tvName.getText().toString().compareTo("") == 0 || tvPhone.getText().toString().compareTo("") == 0 ||
                tvEmail.getText().toString().compareTo("") == 0 || tvDescription.getText().toString().compareTo("") == 0)
            return true;
        if(year == null || month == null || day == null)
            return true;
        return false;
    }
}
