package ajou.walteg;

import static android.provider.BaseColumns._ID;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WaltegDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "walteg.db";
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
<<<<<<< HEAD
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();


        db.insert("purchase", null, cv);
    }

    public void updatePurchase(Purchase p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        db.update("purchase", cv, "idpurchase="+p.id, null);
    }

    public void deletePurchase(Purchase p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("purchase", "idpurchase = "+p.id , null);
=======
        
>>>>>>> c4afcd86ab64ace5203208936e340938b148ad8f
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
    int idpurchase;
    String date;
    int itemid;
    int totalNumber;
    int totalPrice;
    public Purchase(int idpurchase, String date, int itemid, int totalNumber, int totalPrice){
        this.idpurchase=idpurchase;
        this.date=date;
        this.itemid=itemid;
        this.totalNumber=totalNumber;
        this.totalPrice=totalPrice;
    }
}

class Inventory{
    int idinventory;
    String nameinventory;
    String dateexpired;
    int total;
    public Inventory(int idinventory, String nameinventory, String dateexpired, int total){
        this.idinventory=idinventory;
        this.nameinventory=nameinventory;
        this.dateexpired=dateexpired;
        this.total=total;
    }
}

class Menu{
    int idmenu;
    String namemenu;
    int price;
    public Menu(int idmenu, String namemenu, int price) {
        this.idmenu=idmenu;
        this.namemenu=namemenu;
        this.price=price;
    }
}

class Cooking{
    int idcooking;
    String date;
    public Cooking(int idcooking, String date){
        this.idcooking=idcooking;
        this.date=date;
    }
}

class Usage{
    int idcooking;
    int idinventory;
    int totalusage;
    public Usage(int idcooking, int idinventory, int totalusage){
        this.idcooking=idcooking;
        this.idinventory=idinventory;
        this.totalusage=totalusage;
    }
}

class Sold{
    int idcooking;
    int idmenu;
    int totalsell;
    int excessmenu;
    public Sold(int idcooking, int idmenu, int totalsell, int excessmenu){
        this.idcooking=idcooking;
        this.idmenu=idmenu;
        this.totalsell=totalsell;
        this.excessmenu=excessmenu;
    }
}

