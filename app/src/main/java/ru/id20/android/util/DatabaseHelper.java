package ru.id20.android.util;

/**
 * Created by hetfieldan24 on 22.08.2014.
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

import ru.id20.android.Contact;

public class DatabaseHelper extends SQLiteOpenHelper implements BaseColumns {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final String DATABASE_TABLE = "users";

    public static final String COLUMN_ID = BaseColumns._ID;
    public static final String COLUMN_LOGIN = "login";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONE = "phone";

    private static final String DATABASE_CREATE_SCRIPT = "create table "
            + DATABASE_TABLE + " (" + BaseColumns._ID
            + " integer primary key autoincrement, " + COLUMN_LOGIN
            + " text not null, " + COLUMN_PASSWORD + " text not null, "
            + COLUMN_EMAIL + " text not null, "
            + COLUMN_PHONE + " integer);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DatabaseHelper(Context context, String name, CursorFactory factory,
                          int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
        // TODO Auto-generated constructor stub
    }

    public DatabaseHelper(Context context, String name, CursorFactory factory,
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
    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, contact.getName());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());

        // Вставляем строку в таблицу
        db.insert(DATABASE_TABLE, null, values);
        db.close();
    }

    // Получить контакт
    public Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DATABASE_TABLE, new String[] { COLUMN_ID,
                        COLUMN_LOGIN, COLUMN_PASSWORD, COLUMN_EMAIL, COLUMN_PHONE }, COLUMN_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4));
        return contact;
    }

    // Получить все контакты
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Выбираем всю таблицу
        String selectQuery = "SELECT  * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Проходим по всем строкам и добавляем в список
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPassword(cursor.getString(2));
                contact.setEmail(cursor.getString(3));
                contact.setPhoneNumber(cursor.getString(4));
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
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_LOGIN, contact.getName());
        values.put(COLUMN_PASSWORD, contact.getPassword());
        values.put(COLUMN_EMAIL, contact.getEmail());
        values.put(COLUMN_PHONE, contact.getPhoneNumber());

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