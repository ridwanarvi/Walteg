package ajou.walteg;

import static android.provider.BaseColumns._ID;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WaltegDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    /** Create a helper object for the Events database */
    public WaltegDatabaseHelper(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE purchase (idpurchase INTEGER PRIMARY KEY AUTO INCREMENT, date STRING, itemid INTEGER, totalnumber INTEGER, totalprice INTEGER);");
        db.execSQL("CREATE TABLE inventory (idinventory INTEGER PRIMARY KEY AUTO INCREMENT,nameinventory STRING , dateexpired STRING, total INTEGER);");
        db.execSQL("CREATE TABLE menu (idmenu INTEGER PRIMARY KEY AUTO INCREMENT,namemenu STRING , price INTEGER);");
        db.execSQL("CREATE TABLE cooking (idcooking INTEGER PRIMARY KEY AUTO INCREMENT,date STRING);");
        db.execSQL("CREATE TABLE usage (idcooking INTEGER,idinventory INTEGER,totalusage INTEGER);");
        db.execSQL("CREATE TABLE sold (idcooking INTEGER,idmenu INTEGER,totalsell INTEGER, excessmenu INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS purchase");
        db.execSQL("DROP TABLE IF EXISTS inventory");
        db.execSQL("DROP TABLE IF EXISTS menu");
        db.execSQL("DROP TABLE IF EXISTS cooking");
        db.execSQL("DROP TABLE IF EXISTS usage");
        db.execSQL("DROP TABLE IF EXISTS sold");
        onCreate(db);
    }

    public void addPurchase(Purchase p) {

    }
    public void addInventory(Inventory i) {

    }

    public void addMenu(Menu m){

    }
    public void addCooking(Cooking c){

    }
    public void addUsage(Usage u){

    }
    public void addSold(Sold s){

    }


}

class Purchase {
    int id;
    String date;
    int itemid;
    int totalItem;
    int totalNumber;
    public Purchase(int id, String date, int itemid, int totalItem, int totalNumber){
        this.id=id;
        this.date=date;
    }
}

class Inventory{

}

class Menu{

}

class Cooking{

}

class Usage{

}

class Sold{

}

