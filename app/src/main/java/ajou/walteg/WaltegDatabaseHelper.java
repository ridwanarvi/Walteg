package ajou.walteg;

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
        db.execSQL("CREATE TABLE inventory (idinventory INTEGER PRIMARY KEY AUTOINCREMENT, datepurchase TEXT, nameinventory TEXT, dateexpire TEXT, totalnumber INTEGER, totalprice INTEGER);");
        db.execSQL("CREATE TABLE menu (idmenu INTEGER PRIMARY KEY AUTOINCREMENT,namemenu TEXT , price INTEGER);");
        db.execSQL("CREATE TABLE cooking (idcooking INTEGER PRIMARY KEY AUTOINCREMENT,date TEXT);");
        db.execSQL("CREATE TABLE usage (idusage INTEGER PRIMARY KEY AUTOINCREMENT, idcooking INTEGER,idinventory INTEGER,totalusage INTEGER);");
        db.execSQL("CREATE TABLE sold (idsold INTEGER PRIMARY KEY AUTOINCREMENT, idcooking INTEGER,idmenu INTEGER,totalsell INTEGER, excessmenu INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("DROP TABLE IF EXISTS inventory");
        db.execSQL("DROP TABLE IF EXISTS menu");
        db.execSQL("DROP TABLE IF EXISTS cooking");
        db.execSQL("DROP TABLE IF EXISTS usage");
        db.execSQL("DROP TABLE IF EXISTS sold");
        onCreate(db);
    }

    public void updateInventory(Inventory p){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cInventory = p.getContentValues();
        db.update("inventory", cInventory, "idinventory="+p.idinventory, null);
    }

    public void deleteInventory(Inventory p) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("inventory", "idinventory = "+p.idinventory, null);
    }

    public void addInventory(Inventory i) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cInventory = i.getContentValues();
        db.insert("inventory", null, cInventory);
    }

    public void addMenu(Menu m){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cMenu = m.getContentValues();
        db.insert("menu", null, cMenu);
    }

    public void addCooking(Cooking c){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cCooking = c.getContentValues();
        db.insert("cooking", null, cCooking);
    }

    public void addUsage(Usage u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cUsage = u.getContentValues();
        db.insert("usage", null, cUsage);
    }

    public void addSold(Sold s){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cSold = s.getContentValues();
        db.insert("sold", null, cSold);
    }
}

class Inventory {
    int idinventory;
    String datepurchase;
    String nameinventory;
    String dateExpire;
    int totalNumber;
    int totalPrice;
    public Inventory(int idinventory, String datepurchase, String nameinventory,String dateExpire, int totalNumber, int totalPrice){
        this.idinventory =idinventory;
        this.datepurchase=datepurchase;
        this.nameinventory=nameinventory;
        this.totalNumber=totalNumber;
        this.dateExpire=dateExpire;
        this.totalPrice=totalPrice;
    }
    public ContentValues getContentValues(){
        ContentValues cInventory = new ContentValues();
        cInventory.put("datepurchase", datepurchase);
        cInventory.put("nameinventory", nameinventory);
        cInventory.put("nameinventory", nameinventory);
        cInventory.put("dateExpire", dateExpire);
        cInventory.put("totalNumber", totalNumber);
        cInventory.put("totalPrice", totalPrice);
        return cInventory;
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
    public ContentValues getContentValues(){
        ContentValues cMenu = new ContentValues();
        cMenu.put("namemenu", namemenu);
        cMenu.put("price", price);
        return cMenu;
    }
}

class Cooking{
    int idcooking;
    String date;
    public Cooking(int idcooking, String date){
        this.idcooking=idcooking;
        this.date=date;
    }
    public ContentValues getContentValues(){
        ContentValues cCooking = new ContentValues();
        cCooking.put("date", date);
        return cCooking;
    }
}

class Usage{
    int idusage;
    int idcooking;
    int idinventory;
    int totalusage;
    public Usage(int idusage, int idcooking, int idinventory, int totalusage){
        this.idusage=idusage;
        this.idcooking=idcooking;
        this.idinventory=idinventory;
        this.totalusage=totalusage;
    }
    public ContentValues getContentValues(){
        ContentValues cUsage = new ContentValues();
        cUsage.put("idcooking", idcooking);
        cUsage.put("idinventory", idinventory);
        cUsage.put("totalusage", totalusage);
        return cUsage;
    }
}

class Sold{
    int idsold;
    int idcooking;
    int idmenu;
    int totalsell;
    int excessmenu;
    public Sold(int idsold, int idcooking, int idmenu, int totalsell, int excessmenu){
        this.idsold=idsold;
        this.idcooking=idcooking;
        this.idmenu=idmenu;
        this.totalsell=totalsell;
        this.excessmenu=excessmenu;
    }
    public ContentValues getContentValues(){
        ContentValues cUsage = new ContentValues();
        cUsage.put("idcooking", idcooking);
        cUsage.put("idmenu", idmenu);
        cUsage.put("totalsell", totalsell);
        cUsage.put("excessmenu", excessmenu);
        return cUsage;
    }
}

