package ajou.walteg;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Ridwan on 7/14/16.
 */
public class PurchaseActivity extends AppCompatActivity {

    TextView dateTV;
    WaltegDatabaseHelper wdh;
    ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.purchase);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        wdh = new WaltegDatabaseHelper(this);
        listView = (ListView) findViewById(R.id.listViewPurchase);
        dateTV = (TextView) findViewById(R.id.dateTV);
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        setDate(year, month, day);
        onItemLongClick();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View
                    view, int position, long id) {
                if(position==arr.size()) return ;



                int idInventory = arr.get(position).idinventory;
                String datepurchase= arr.get(position).datepurchase;
                String nameinventory = arr.get(position).nameinventory;
                String dateExpire = arr.get(position).dateExpire;
                int totalNumber = arr.get(position).totalNumber;
                int totalPrice = arr.get(position).totalPrice;
                Intent intent = new Intent(PurchaseActivity.this, EditPurchaseActivity.class);

                intent.putExtra("IdInventory",idInventory);
                intent.putExtra("DatePurchaseExtra", datepurchase);
                intent.putExtra("NameInventoryExtra", nameinventory);
                intent.putExtra("DateExpireExtra", dateExpire);
                intent.putExtra("TotalNumberExtra", totalNumber);
                intent.putExtra("TotalPriceExtra", totalPrice);

                startActivity(intent);


            }
        });


    }

    public void onItemLongClick() {
        listView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        if(position==arr.size()) return false;
                        AlertDialog.Builder builder = new AlertDialog.Builder(PurchaseActivity.this);
                        builder
                                .setMessage("Do you want to delete this record?")

                                .setPositiveButton(getString(R.string.yes_button), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        wdh.deleteInventory(arr.get(position));
                                        refreshData();
                                    }
                                })
                                .setNegativeButton(getString(R.string.no_button), new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        refreshData();
                                    }
                                });
                        builder.create().show();

                        return true;
                    }
                });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshData();
    }

    ArrayList<Inventory> arr = new ArrayList<Inventory>();

    public void refreshData() {

        arr = wdh.getInventory(dateTV.getText().toString());
        ArrayList<String> arrString = new ArrayList<String>();
        int totalPrice = 0;
        for (int i = 0; i < arr.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Name: " + arr.get(i).nameinventory + "\n");
            sb.append("Date Expire: " + arr.get(i).dateExpire + "\n");
            sb.append("Total Item: " + arr.get(i).totalNumber + "\n");
            sb.append("Total Price: " + arr.get(i).totalPrice + "\n");
            totalPrice += arr.get(i).totalPrice;

            arrString.add(sb.toString());
        }
        arrString.add("Total Purchase: " + totalPrice);
        listView.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrString));
    }


    public void chooseDate(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDate(int year, int month, int day) {
        dateTV.setText(day + "/" + (month + 1) + "/" + year);
        refreshData();
    }

    public void add(View v) {
        Intent p = new Intent(this, AddPurchaseActivity.class);
        p.putExtra("date", dateTV.getText().toString());
        startActivity(p);

    }

}
