package ajou.walteg;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
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
        setDate(year,month,day);


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

    public void refreshData(){
        SQLiteDatabase db = wdh.getReadableDatabase();

        ArrayList<String> arr= new ArrayList<String>();
        int totalPrice = 0;
        Cursor c = db.rawQuery("SELECT * FROM inventory where datepurchase ='"+dateTV.getText()+"'", null);
        if(c.moveToFirst()){
            do{
                //assing values
                c.getColumnCount();
                StringBuilder sb =  new StringBuilder();
                /*for(int i =0; i<c.getColumnCount();i++){
                    sb.append(c.getColumnName(i)+":"+c.getString(i)+",");
                }*/

                sb.append("Name: "+c.getString(2)+"\n");
                sb.append("Date Expire: "+c.getString(3)+"\n");
                sb.append("Total Item: "+ c.getString(4)+"\n");
                sb.append("Total Price: "+c.getString(5)+"\n");
                totalPrice += Integer.parseInt(c.getString(5));

                arr.add(sb.toString());
                Log.d("TEST", sb.toString());

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        
        arr.add("Total Purchase: "+ totalPrice);
        listView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr));
    }


    public void chooseDate(View v){
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDate(int year, int month, int day)
    {
        dateTV.setText(day+"/"+(month+1)+"/"+year);
        refreshData();

    }

    public void add(View v){
        Intent p = new Intent(this, AddPurchaseActivity.class);
        p.putExtra("date",dateTV.getText().toString());
        startActivity(p);

    }

}
