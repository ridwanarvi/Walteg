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

/**
 * Created by ajou on 7/17/2016.
 */
public class EditPurchaseActivity extends AppCompatActivity {
    TextView dateTV;
    TextView dateExpiredET;

    EditText InventoryName;

    EditText TotalofItem;
    EditText TotalPrice;

    String datePurchased;
    String dateExpired;

    WaltegDatabaseHelper wdh;

    int idInventory;

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

        InventoryName = (EditText)findViewById(R.id.nameET);
        TotalofItem = (EditText)findViewById(R.id.totalNumber);
        TotalPrice = (EditText)findViewById(R.id.totalPrice);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("DatePurchaseExtra");
            String k =(String) b.get("NameInventoryExtra");
            String l =(String) b.get("DateExpireExtra");
            String m =""+ b.getInt("TotalNumberExtra");
            String n ="" + b.getInt("TotalPriceExtra");

            dateTV.setText("Date Purchased: " + j);
            InventoryName.setText(k);
            dateExpiredET.setText(l);
            TotalofItem.setText(m);
            TotalPrice.setText(n);
            idInventory = b.getInt("IdInventory");
        }

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
        Inventory i = new Inventory(idInventory, datePurchased, nameinventory, dateExpire, totalNumber, totalPrice);
        wdh.updateInventory(i);

        Toast.makeText(getApplicationContext(), "The item was added", Toast.LENGTH_SHORT).show();

        finish();

    }
}
