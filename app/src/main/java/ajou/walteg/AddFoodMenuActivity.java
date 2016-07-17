package ajou.walteg;

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
 * Created by ajou on 7/14/17.
 */
public class AddFoodMenuActivity extends AppCompatActivity {
    TextView nameMenuField;
    TextView priceField;

    String namemenu;
    int price;

    WaltegDatabaseHelper wdh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_food_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        nameMenuField = (TextView) findViewById(R.id.nameMenuField);
        priceField = (TextView) findViewById(R.id.priceField);
        wdh = new WaltegDatabaseHelper(this);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    public void submit(View v) {
        namemenu = ((EditText) findViewById(R.id.nameMenuField)).getText().toString();
        price = Integer.parseInt(((TextView) findViewById(R.id.priceField)).getText().toString());
        Menu m = new Menu(0, namemenu, price);
        wdh.addMenu(m);
        Toast.makeText(getApplicationContext(), "The item was added", Toast.LENGTH_SHORT).show();
        ;
        finish();

    }
}
