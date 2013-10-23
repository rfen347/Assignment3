package rfen347.softeng206;
 
import java.util.ArrayList;
import java.util.List;
 
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
 
public class DatabaseHandler extends SQLiteOpenHelper {
 
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "contactsManager";
 
    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";
 
    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_FNAME = "first_name";
    private static final String KEY_LNAME = "last_name";
    private static final String KEY_MNUM = "mobile_number";	
    private static final String KEY_HNUM = "home_number";
    private static final String KEY_WNUM = "work_number";
    private static final String KEY_EMA = "email";
    private static final String KEY_ADD = "address";	
    private static final String KEY_DOB = "date_of_birth";	
 
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
        		+ KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT,"
                + KEY_MNUM + " TEXT," + KEY_HNUM + " TEXT," 
                + KEY_WNUM + " TEXT," + KEY_EMA + " TEXT," + KEY_ADD + " TEXT," + KEY_DOB + " TEXT" +")";
       
        db.execSQL(CREATE_CONTACTS_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
 
        // Create tables again
        onCreate(db);
    }
 
    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */
 
    // Adding new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, contact.getFirstName()); // Contact Name
        values.put(KEY_LNAME, contact.getLastName()); // Contact Phone
        values.put(KEY_MNUM, contact.getMobile());
        values.put(KEY_HNUM, contact.getHome());
        values.put(KEY_WNUM, contact.getWork());
        values.put(KEY_EMA, contact.getEmail());
        values.put(KEY_ADD, contact.getAddress());
        values.put(KEY_DOB, contact.getDob());
        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }
 
    // Getting single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                KEY_FNAME, KEY_LNAME, KEY_MNUM, KEY_HNUM, KEY_WNUM, KEY_EMA, KEY_ADD, KEY_DOB }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8));
        // return contact
        return contact;
    }
     
    // Getting All Contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.set_id(Integer.parseInt(cursor.getString(0)));
                contact.set_firstName(cursor.getString(1));
                contact.set_lastName(cursor.getString(2));
                contact.set_mobile(cursor.getString(3));
                contact.set_home(cursor.getString(4));
                contact.set_work(cursor.getString(5));
                contact.set_email(cursor.getString(6));
                contact.set_address(cursor.getString(7));
                contact.set_dob(cursor.getString(8));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return contactList;
    }
 
    // Updating single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
 
        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, contact.getFirstName());
        values.put(KEY_LNAME, contact.getLastName());
        values.put(KEY_MNUM, contact.getMobile());
        values.put(KEY_HNUM, contact.getHome());
        values.put(KEY_WNUM, contact.getWork());
        values.put(KEY_EMA, contact.getEmail());
        values.put(KEY_ADD, contact.getAddress());
        values.put(KEY_DOB, contact.getDob());
 
        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
    }
 
    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.get_id()) });
        db.close();
    }
 
 
    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
 
        // return count
        return cursor.getCount();
    }
 
}