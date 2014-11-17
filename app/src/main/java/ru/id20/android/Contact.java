package ru.id20.android;

/**
 * Created by hetfieldan24 on 22.08.2014.
 */
public class Contact {

    private int _id;
    private String mName;
    private String mPassword;
    private String mEmail;
    private String mPhoneNumber;


    // Пустой констуктор
    public Contact() {

    }

    // Конструктор с параметрами
    public Contact(int id, String name, String password, String email, String phonenumber)
    {
        this._id = id;
        this.mName = name;
        this.mPassword = password;
        this.mEmail = email;
        this.mPhoneNumber = phonenumber;
    }

    // Конструктор с параметрами
    public Contact(String name, String password, String phonenumber, String email)
    {
        this.mName = name;
        this.mPassword = password;
        this.mEmail = email;
        this.mPhoneNumber = phonenumber;
    }

    // Создание геттеров-сеттеров

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public void setEmail(String email) {
        this.mEmail = email;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

}
