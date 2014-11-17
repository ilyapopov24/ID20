package ru.id20.android.util;

/**
 * Created by hetfieldan24 on 28.08.2014.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ru.id20.android.ApplicationsContact;
import ru.id20.android.Contact;

public class ApplicationsDatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "applicationsdatabase.db";
    private static final String DATABASE_TABLE = "users";

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_ACCESSTOKEN = "access_token";
    public static final String COLUMN_PHONE = "number";

    public static final String COLUMN_DATETIME = "date_time";
    public static final String COLUMN_ADRESSFROM = "address_from";
    public static final String COLUMN_ADRESSTO = "address_to";
    public static final String COLUMN_CONTACTNAME = "contact_name";

    public static final String COLUMN_CONTACT_PHONE = "contact_phone";
    public static final String COLUMN_PURPOSES = "purposes";
    public static final String COLUMN_WISHES = "wishes";
    public static final String COLUMN_ISPASSENGER = "is_passenger";

    public static final String COLUMN_ISPOINT = "is_point";
    public static final String COLUMN_CREATED = "created";
    public static final String COLUMN_PROCURATION = "procuration";
    public static final String COLUMN_PERMIT = "permit";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + COLUMN_LOGIN
            + " text not null, " + COLUMN_PASSWORD + " text not null, "
            + COLUMN_ACCESSTOKEN + " text not null, "
            + COLUMN_PHONE + " integer, " + COLUMN_DATETIME
            + " text not null, " + COLUMN_ADRESSFROM + " text not null, "
            + COLUMN_ADRESSTO + " text not null, " + COLUMN_CONTACTNAME
            + " text not null, " + COLUMN_CONTACT_PHONE + " integer, "
            + COLUMN_PURPOSES + " text not null, " + COLUMN_WISHES
            + " text not null, " + COLUMN_ISPASSENGER + " text not null, "
            + COLUMN_ISPOINT + " text not null, " + COLUMN_CREATED
            + " text not null, " + COLUMN_PROCURATION + " text not null, "
            + COLUMN_PERMIT + " text not null);";

    public ApplicationsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public ApplicationsDatabaseHelper(Context context, String name, CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        // TODO Auto-generated constructor stub
    }

    public ApplicationsDatabaseHelper(Context context, String name, CursorFactory factory,
                          int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(DATABASE_CREATE_SCRIPT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // Запишем в журнал
        Log.w("SQLite", "Обновляемся с версии " + oldVersion + " на версию "
                + newVersion);

        // Удаляем старую таблицу и создаём новую
        db.execSQL("DROP TABLE IF IT EXIST " + DATABASE_TABLE);
        // Создаём новую таблицу
        onCreate(db);
    }

    // Добавляем новый контакт
    public void addContact(ApplicationsContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, contact.getLogin());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_ACCESSTOKEN, contact.getAccessToken());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());

        values.put(COLUMN_DATETIME, contact.getDateTime());
        values.put(COLUMN_ADRESSFROM, contact.getAddressFrom());
        values.put(COLUMN_ADRESSTO, contact.getAddressTo());
        values.put(COLUMN_CONTACTNAME, contact.getContactName());

        values.put(COLUMN_CONTACT_PHONE, contact.getContactPhone());
        values.put(COLUMN_PURPOSES, contact.getPurposes());
        values.put(COLUMN_WISHES, contact.getWishes());
        values.put(COLUMN_ISPASSENGER, contact.getIsPassenger());

        values.put(COLUMN_ISPOINT, contact.getIsPoint());
        values.put(COLUMN_CREATED, contact.getCreated());
        values.put(COLUMN_PROCURATION, contact.getProcuration());
        values.put(COLUMN_PERMIT, contact.getPermit());

        // Вставляем строку в таблицу
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    // Получить контакт
    public ApplicationsContact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[] { COLUMN_ID,
                        COLUMN_LOGIN, COLUMN_PASSWORD, COLUMN_ACCESSTOKEN,
                        COLUMN_PHONE, COLUMN_DATETIME, COLUMN_ADRESSFROM,
                        COLUMN_ADRESSTO, COLUMN_CONTACTNAME, COLUMN_CONTACT_PHONE,
                        COLUMN_PURPOSES, COLUMN_WISHES, COLUMN_ISPASSENGER,
                        COLUMN_ISPOINT, COLUMN_CREATED, COLUMN_PROCURATION,
                        COLUMN_PERMIT }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        ApplicationsContact contact = new ApplicationsContact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
                cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),
                cursor.getString(13), cursor.getString(14), cursor.getString(15), cursor.getString(16));
        return contact;
    }

    // Получить все контакты
    public List<ApplicationsContact> getAllContacts() {
        List<ApplicationsContact> contactList = new ArrayList<ApplicationsContact>();
        // Выбираем всю таблицу
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Проходим по всем строкам и добавляем в список
        if (cursor.moveToFirst()) {
            do {
                ApplicationsContact contact = new ApplicationsContact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setLogin(cursor.getString(1));
                contact.setPassword(cursor.getString(2));
                contact.setAccessToken(cursor.getString(3));
                contact.setPhoneNumber(cursor.getString(4));
                contact.setDateTime(cursor.getString(5));
                contact.setAddressFrom(cursor.getString(6));
                contact.setAddressTo(cursor.getString(7));
                contact.setContactName(cursor.getString(8));
                contact.setContactPhone(cursor.getString(9));
                contact.setPurposes(cursor.getString(10));
                contact.setWishes(cursor.getString(11));
                contact.setIsPassenger(cursor.getString(12));
                contact.setIsPoint(cursor.getString(13));
                contact.setCreated(cursor.getString(14));
                contact.setProcuration(cursor.getString(15));
                contact.setPermit(cursor.getString(16));
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        return contactList;
    }

    // Получить число контактов
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

    // Обновить контакт
    public int updateContact(ApplicationsContact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, contact.getLogin());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_ACCESSTOKEN, contact.getAccessToken());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());

        values.put(COLUMN_DATETIME, contact.getDateTime());
        values.put(COLUMN_ADRESSFROM, contact.getAddressFrom());
        values.put(COLUMN_ADRESSTO, contact.getAddressTo());
        values.put(COLUMN_CONTACTNAME, contact.getContactName());

        values.put(COLUMN_CONTACT_PHONE, contact.getContactPhone());
        values.put(COLUMN_PURPOSES, contact.getPurposes());
        values.put(COLUMN_WISHES, contact.getWishes());
        values.put(COLUMN_ISPASSENGER, contact.getIsPassenger());

        values.put(COLUMN_ISPOINT, contact.getIsPoint());
        values.put(COLUMN_CREATED, contact.getCreated());
        values.put(COLUMN_PROCURATION, contact.getProcuration());
        values.put(COLUMN_PERMIT, contact.getPermit());
        // обновляем строку
        return db.update(DATABASE_TABLE, values, COLUMN_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    // Удалить контакт
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, COLUMN_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }
}