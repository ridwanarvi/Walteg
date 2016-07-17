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
public class EditFoodMenuActivity extends AppCompatActivity {
    TextView nameMenuField;
    TextView priceField;

    EditText nameMenuFieldEdit;
    EditText priceFieldEdit;

    String namemenu;
    int price;

    WaltegDatabaseHelper wdh;

    int idmenu;

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

        nameMenuFieldEdit = (EditText)findViewById(R.id.nameMenuField);
        priceFieldEdit = (EditText)findViewById(R.id.priceField);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            String nameFM =(String) b.get("namemenu");
            String priceFM ="" + b.getInt("price");

            nameMenuFieldEdit.setText(nameFM);
            priceFieldEdit.setText(priceFM);
            idmenu = b.getInt("idmenu");
        }

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
        Menu m = new Menu(idmenu, namemenu, price);
        wdh.updateMenu(m);
        Toast.makeText(getApplicationContext(), "The item was added", Toast.LENGTH_SHORT).show();
        ;
        finish();
    }
}
