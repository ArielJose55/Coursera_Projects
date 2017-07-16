package ajac.main.com.mycontactapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ConfirmFormActivity extends AppCompatActivity {

    private Bundle bundle;

    private TextView tvName;
    private TextView tvDate;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_form);

        bundle  = getIntent().getExtras();

        tvName         = (TextView) findViewById(R.id.confirm_name_text);
        tvDate         = (TextView) findViewById(R.id.confirm_date_text);
        tvPhone        = (TextView) findViewById(R.id.confirm_phone_text);
        tvEmail        = (TextView) findViewById(R.id.confirm_email_text);
        tvDescription  = (TextView) findViewById(R.id.confirm_description_text);

        tvName.setText(bundle.getString("NAME_FORM"));
        tvDate.setText("Fecha de Nacimiento: "+bundle.getString("DATE_FORM"));
        tvPhone.setText("Tel. "+bundle.getString("PHONE_FORM"));
        tvEmail.setText("Email:"+bundle.getString("EMAIL_FORM"));
        tvDescription.setText("Descripción: "+bundle.getString("DESCRITION_FORM"));

    }

    public void actionConfirmData(View v){
        Intent intent = new Intent(ConfirmFormActivity.this, MainActivity.class);
        intent.putExtra("NAME_FORM",bundle.getString("NAME_FORM"));
        intent.putExtra("DATE_FORM",bundle.getString("DATE_FORM"));
        intent.putExtra("PHONE_FORM",bundle.getString("PHONE_FORM"));
        intent.putExtra("EMAIL_FORM",bundle.getString("EMAIL_FORM"));
        intent.putExtra("DESCRITION_FORM",bundle.getString("DESCRITION_FORM"));
        startActivity(intent);
        finish();
    }
}
