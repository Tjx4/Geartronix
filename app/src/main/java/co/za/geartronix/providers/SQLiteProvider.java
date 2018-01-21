package co.za.geartronix.providers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

import co.za.geartronix.models.MemberModel;
import co.za.geartronix.models.NamesModel;
import co.za.geartronix.models.UserModel;

public class SQLiteProvider extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "geartronix_db";
    private static final String TABLE_CONTACTS = "users";
    private static final String ID = "id";
    private static final String REMOTEID = "remote_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String GENDER = "gender";
    private static final String MAINUMBER = "main_number";
    private static final String MAINEMAIL = "main_email";
    private static final String IDNO = "id_number";
    private static final String PASSWORD = "password";
    private static final String CITY = "city";
    private static final String MEMBERTYPE = "member_type";

    public SQLiteProvider(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + ID + " INTEGER PRIMARY KEY,"
                + REMOTEID + " INTEGER,"
                + NAME + " TEXT,"
                + SURNAME + " TEXT,"
                + GENDER + " TEXT,"
                + MAINUMBER + " TEXT,"
                + MAINEMAIL + " TEXT,"
                + IDNO + " TEXT,"
                + PASSWORD + " TEXT,"
                + CITY + " TEXT,"
                + MEMBERTYPE + " INTEGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    public void addUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, user.getNames().getFirstName());
        values.put(SURNAME, user.getNames().getSurName());
        values.put(GENDER, user.getGender()+"");
        values.put(MAINUMBER, user.getContactDetailsProvider().getContactNumbers()[0]);
        values.put(MAINEMAIL, user.getContactDetailsProvider().getEmails()[0]);
        values.put(IDNO, user.getIdNo());
        values.put(PASSWORD, user.getPassword());
        values.put(CITY, user.getCity());
        values.put(MEMBERTYPE, user.getMember().getMemberType());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public UserModel getUser(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { ID, NAME, GENDER, MAINUMBER, MAINEMAIL, IDNO, CITY, MEMBERTYPE}, ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        UserModel user = new UserModel();
        user.setId(Integer.parseInt( cursor.getString(0)) );
        user.setNames(new NamesModel());
        user.getNames().setFirstName(cursor.getString(1));

        user.setGender(cursor.getString(2).charAt(0));

        ContactDetailsProvider contacts = new ContactDetailsProvider();
        contacts.setContactNumbers(new int[]{ Integer.parseInt(cursor.getString(3)) });
        contacts.setEmails(new String[]{cursor.getString(4)});
        user.setContactDetailsProvider(contacts);

        user.setIdNo(cursor.getString(5));
        user.setCity(cursor.getString(7));
        MemberModel member = new MemberModel();
        //member.setMemberType(Integer.parseInt(cursor.getString(7)));
        //user.setMemberType(member);

        return user;
    }

    public List<UserModel> getAllUsers() {
        List<UserModel> userList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setId(Integer.parseInt( cursor.getString(0)) );
                user.setNames(new NamesModel());
                user.getNames().setFirstName(cursor.getString(2));

                user.setGender(cursor.getString(4).charAt(0));

                ContactDetailsProvider contacts = new ContactDetailsProvider();
                contacts.setContactNumbers(new int[]{ Integer.parseInt(cursor.getString(5)) });
                contacts.setEmails(new String[]{cursor.getString(6)});
                user.setContactDetailsProvider(contacts);

                user.setIdNo(cursor.getString(7));
                user.setCity(cursor.getString(9));
                MemberModel member = new MemberModel();
                member.setMemberType(Integer.parseInt(cursor.getString(10)));
                //user.setMemberType(member);

                userList.add(user);
            } while (cursor.moveToNext());
        }

        return userList;
    }

    public UserModel getLastUser() {
        List<UserModel> userList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                UserModel user = new UserModel();
                user.setId(Integer.parseInt( cursor.getString(0)) );
                user.setNames(new NamesModel());
                user.getNames().setFirstName(cursor.getString(2));

                user.setGender(cursor.getString(4).charAt(0));

                ContactDetailsProvider contacts = new ContactDetailsProvider();
                contacts.setContactNumbers(new int[]{ Integer.parseInt(cursor.getString(5)) });
                contacts.setEmails(new String[]{cursor.getString(6)});
                user.setContactDetailsProvider(contacts);

                user.setIdNo(cursor.getString(7));
                user.setCity(cursor.getString(9));
                MemberModel member = new MemberModel();
                member.setMemberType(Integer.parseInt(cursor.getString(10)));
                //user.setMemberType(member);

                userList.add(user);
            } while (cursor.moveToNext());
        }

        int userId = userList.size() - 1;
        return userList.get(userId);
    }

    public int updateUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME, user.getNames().getFirstName());
        values.put(MAINUMBER, user.getContactDetailsProvider().getContactNumbers()[0]);

        return db.update(TABLE_CONTACTS, values, ID + " = ?", new String[] { String.valueOf(user.getId()) });
    }

    public void deleteUser(UserModel user) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, ID + " = ?", new String[] { String.valueOf(user.getId()) });
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);
    }
}