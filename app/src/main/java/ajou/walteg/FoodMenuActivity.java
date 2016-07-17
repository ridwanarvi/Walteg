package ajou.walteg;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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
        onItemLongClick();
    }

    public void onItemLongClick() {
        listViewFoodMenu.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                        if(position==0) return false;
                        AlertDialog.Builder builder = new AlertDialog.Builder(FoodMenuActivity.this);
                        builder
                                .setMessage("Do you want to delete this record?")

                                .setPositiveButton(getString(R.string.yes_button), new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        wdh.deleteMenu(arr.get(position-1));
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



    ArrayList<Menu> arr = new ArrayList<Menu>();

    public void refreshData() {

        arr = wdh.getMenu();
        ArrayList<String> arrString = new ArrayList<String>();
        arrString.add("Total Menu: "+ arr.size());
        for (int i = 0; i < arr.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append("Menu name: "+arr.get(i).namemenu+"\n");
            sb.append("Price: "+arr.get(i).price);
            arrString.add(sb.toString());
        }

        listViewFoodMenu.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrString));
    }

    public void add(View v){
        Intent m = new Intent(this, AddFoodMenuActivity.class);
        startActivity(m);
    }

}
