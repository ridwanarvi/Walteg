package ajou.walteg;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

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
        db.execSQL("UPDATE inventory SET nameinventory='"+p.nameinventory+"',dateexpire='"+p.dateExpire+"',totalnumber="+p.totalNumber+",totalprice="+p.totalPrice+" WHERE idinventory="+p.idinventory);
    }

    public void deleteInventory(Inventory p) {
        SQLiteDatabase db = this.getWritableDatabase();
       // db.delete("inventory", "idinventory = "+p.idinventory, null);
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

    public ArrayList<Inventory> getInventory(String date){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Inventory> arr = new ArrayList<Inventory>();
        Cursor c = db.rawQuery("SELECT * FROM inventory where datepurchase ='"+ date+"'", null);
        if(c.moveToFirst()){
            do{
                arr.add(new Inventory(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4),c.getInt(5)));
                }while(c.moveToNext());
        }
        c.close();
        return arr;
    }


    public ArrayList<String> getInventory(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> arr = new ArrayList<String>();
        Cursor c = db.rawQuery("SELECT i.nameinventory, i.totalnumber-COALESCE(SUM(u.totalusage),0) " +
                "FROM inventory i LEFT JOIN usage u ON i.idinventory=u.idinventory " +
                "GROUP BY i.idinventory", null);
        if(c.moveToFirst()){
            do{
                arr.add("Name : "+c.getString(0)+"\nTotal: "+c.getString(1));
            }while(c.moveToNext());
        }
        c.close();
        return arr;
    }

    public ArrayList<Menu> getMenu(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Menu> arr = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM menu", null);
        if(c.moveToFirst()){
            do{
                arr.add(new Menu(c.getInt(0),c.getString(1),c.getInt(2)));
            }while(c.moveToNext());
        }
        c.close();
        return arr;
    }

    public void deleteMenu(Menu m) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("menu", "idmenu = "+m.idmenu, null);
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

