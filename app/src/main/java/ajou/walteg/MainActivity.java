package ajou.walteg;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void purchase(View v)
    {
        Toast.makeText(getApplicationContext(),"Purchase is not yet implemented",Toast.LENGTH_SHORT);
    }
    public void inventory(View v)
    {
        Toast.makeText(getApplicationContext(),"Inventory is not yet implemented",Toast.LENGTH_SHORT);

    }
    public void menu(View v)
    {
        Toast.makeText(getApplicationContext(),"Menu is not yet implemented",Toast.LENGTH_SHORT);

    }
    public void cook(View v)
    {
        Toast.makeText(getApplicationContext(),"Cook is not yet implemented",Toast.LENGTH_SHORT);

    }
    public void sell(View v)
    {
        Toast.makeText(getApplicationContext(),"Food Sales is not yet implemented",Toast.LENGTH_SHORT);

    }
    public void review(View v)
    {
        Toast.makeText(getApplicationContext(),"History and Review is not yet implemented",Toast.LENGTH_SHORT);

    }

}
