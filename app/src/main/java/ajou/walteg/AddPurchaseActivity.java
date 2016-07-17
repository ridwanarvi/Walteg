package ajou.walteg;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Ridwan on 7/14/16.
 */
public class AddPurchaseActivity extends AppCompatActivity {
    TextView dateTV;
    TextView dateExpiredET;

    String datePurchased;
    String dateExpired;

    WaltegDatabaseHelper wdh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_purchase);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        dateTV = (TextView) findViewById(R.id.dateText);
        dateExpiredET = (TextView) findViewById(R.id.dateExpiredET);
        datePurchased = getIntent().getStringExtra("date");
        dateTV.setText("Date Purchased: " + datePurchased);
        wdh = new WaltegDatabaseHelper(this);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeDate(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDate(String date) {
        dateExpiredET.setText(date);
        dateExpired = date;


    }

    public void submit(View v) {
        int totalNumber = 0;
        int totalPrice = 0;
        try {
            totalNumber = Integer.parseInt(((EditText) findViewById(R.id.totalNumber)).getText().toString());
            totalPrice = Integer.parseInt(((EditText) findViewById(R.id.totalPrice)).getText().toString());

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Please put number on Total Number and Total Price", Toast.LENGTH_SHORT).show();
            ;
            return;
        }
        String dateExpire = ((TextView) findViewById(R.id.dateExpiredET)).getText().toString();
        String nameinventory = ((EditText) findViewById(R.id.nameET)).getText().toString();
        Inventory i = new Inventory(0, datePurchased, nameinventory, dateExpire, totalNumber, totalPrice);
        wdh.addInventory(i);
        Toast.makeText(getApplicationContext(), "The item was added", Toast.LENGTH_SHORT).show();
        ;
        finish();

    }
}
