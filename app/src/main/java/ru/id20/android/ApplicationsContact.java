package ru.id20.android;

/**
 * Created by hetfieldan24 on 28.08.2014.
 */
public class ApplicationsContact
{
    private int _id;
    private String mLogin;
    private String mPassword;
    private String mAccessToken;
    private String mPhoneNumber;

    private String mDateTime;
    private String mAddressFrom;
    private String mAddressTo;
    private String mContactName;

    private String mContactPhone;
    private String mPurposes;
    private String mWishes;
    private String mIsPassenger;

    private String mIsPoint;
    private String mCreated;
    private String mProcuration;
    private String mPermit;

    // Пустой констуктор
    public ApplicationsContact() {

    }

    // Конструктор с параметрами
    public ApplicationsContact(int id, String login, String password, String accessToken, String phonenumber,
                   String dateTime, String addressFrom, String addressTo, String contactName,
                   String contactPhone, String purposes, String wishes, String isPassenger,
                   String isPoint, String created, String procuration, String permit)
    {
        this._id = id;
        this.mLogin = login;
        this.mPassword = password;
        this.mAccessToken = accessToken;
        this.mPhoneNumber = phonenumber;

        this.mDateTime = dateTime;
        this.mAddressFrom = addressFrom;
        this.mAddressTo = addressTo;
        this.mContactName = contactName;

        this.mContactPhone = contactPhone;
        this.mPurposes = purposes;
        this.mWishes = wishes;
        this.mIsPassenger = isPassenger;

        this.mIsPoint = isPoint;
        this.mCreated = created;
        this.mProcuration = procuration;
        this.mPermit = permit;
    }

    // Конструктор с параметрами
    public ApplicationsContact(String login, String password, String accessToken, String phonenumber,
                   String dateTime, String addressFrom, String addressTo, String contactName,
                   String contactPhone, String purposes, String wishes, String isPassenger,
                   String isPoint, String created, String procuration, String permit)
    {
        this.mLogin = login;
        this.mPassword = password;
        this.mAccessToken = accessToken;
        this.mPhoneNumber = phonenumber;

        this.mDateTime = dateTime;
        this.mAddressFrom = addressFrom;
        this.mAddressTo = addressTo;
        this.mContactName = contactName;

        this.mContactPhone = contactPhone;
        this.mPurposes = purposes;
        this.mWishes = wishes;
        this.mIsPassenger = isPassenger;

        this.mIsPoint = isPoint;
        this.mCreated = created;
        this.mProcuration = procuration;
        this.mPermit = permit;
    }

    // Создание геттеров-сеттеров

    public int getID() {
        return this._id;
    }

    public void setID(int id) {
        this._id = id;
    }

    public String getLogin() {
        return this.mLogin;
    }

    public void setLogin(String login) {
        this.mLogin = login;
    }

    public String getPassword() {
        return this.mPassword;
    }

    public void setPassword(String password) {
        this.mPassword = password;
    }

    public String getAccessToken() {
        return this.mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        this.mAccessToken = accessToken;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.mPhoneNumber = phoneNumber;
    }

    public String getDateTime() {
        return this.mDateTime;
    }

    public void setDateTime(String dateTime) {
        this.mDateTime = dateTime;
    }

    public String getAddressFrom() {
        return this.mAddressFrom;
    }

    public void setAddressFrom(String addressFrom) {
        this.mAddressFrom = addressFrom;
    }

    public String getAddressTo() {
        return this.mAddressTo;
    }

    public void setAddressTo(String addressTo) {
        this.mAddressTo = addressTo;
    }

    public String getContactName() {
        return this.mContactName;
    }

    public void setContactName(String contactName) {
        this.mContactName = contactName;
    }

    public String getContactPhone() {
        return this.mContactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.mContactPhone = contactPhone;
    }

    public String getPurposes() {
        return this.mPurposes;
    }

    public void setPurposes(String purposes) {
        this.mPurposes = purposes;
    }

    public String getWishes() {
        return this.mWishes;
    }

    public void setWishes(String wishes) {
        this.mWishes = wishes;
    }

    public String getIsPassenger() {
        return this.mIsPassenger;
    }

    public void setIsPassenger(String isPassenger) {
        this.mIsPassenger = isPassenger;
    }

    public String getIsPoint() {
        return this.mIsPoint;
    }

    public void setIsPoint(String isPoint) {
        this.mIsPoint = isPoint;
    }

    public String getCreated() {
        return this.mCreated;
    }

    public void setCreated(String created) {
        this.mCreated = created;
    }

    public String getProcuration() {
        return this.mProcuration;
    }

    public void setProcuration(String procuration) {
        this.mProcuration = procuration;
    }

    public String getPermit() {
        return this.mPermit;
    }

    public void setPermit(String permit) {
        this.mPermit = permit;
    }
}
