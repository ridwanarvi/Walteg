package ajou.walteg;

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
import java.util.ArrayList;

/**
 * Created by ajou on 7/15/16.
 */
public class FoodMenuActivity extends AppCompatActivity {

    WaltegDatabaseHelper wdh;
    ListView listViewFoodMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        wdh = new WaltegDatabaseHelper(this);
        listViewFoodMenu = (ListView) findViewById(R.id.listViewFoodMenu);
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

    public void refreshData() {
        SQLiteDatabase db = wdh.getReadableDatabase();

        ArrayList<String> arr= new ArrayList<String>();
        int totalMenu= 0;
        Cursor c = db.rawQuery("SELECT * FROM menu", null);
        totalMenu = c.getCount();
        arr.add("Total Menu: "+ totalMenu);
        if(c.moveToFirst()){
            do{
                //assing values
                c.getColumnCount();
                StringBuilder sb =  new StringBuilder();
                /*for(int i =0; i<c.getColumnCount();i++){
                    sb.append(c.getColumnName(i)+":"+c.getString(i)+",");
                }*/

                sb.append("Menu name: "+c.getString(1)+"\n");
                sb.append("Price: "+c.getString(2));

                arr.add(sb.toString());
            }
            while(c.moveToNext());
        }
        c.close();
        db.close();


        listViewFoodMenu.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,arr));
    }

    public void add(View v){
        Intent m = new Intent(this, AddFoodMenuActivity.class);
        startActivity(m);
    }

}
