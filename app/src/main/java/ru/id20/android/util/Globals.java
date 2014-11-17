package ru.id20.android.util;

import java.util.ArrayList;

/**
 * Created by hetfieldan24 on 02.11.2014.
 */
public class Globals
{
    private static Globals instance;

    private ArrayList<String> statusArray = new ArrayList<String>();
    private ArrayList<String> driver_nameArray = new ArrayList<String>();
    private ArrayList<String> numberArray = new ArrayList<String>();
    private ArrayList<String> car_nameArray = new ArrayList<String>();
    private ArrayList<String> adress_fromArray = new ArrayList<String>();
    private ArrayList<String> adress_toArray = new ArrayList<String>();
    private ArrayList<String> createdArray = new ArrayList<String>();
    private ArrayList<String> companyArray = new ArrayList<String>();
    private ArrayList<String> passenger_fioArray = new ArrayList<String>();
    private ArrayList<String> date_timeArray = new ArrayList<String>();

    private Globals(){}

    public ArrayList<String> getStatusArray()
    {
        return this.statusArray;
    }

    public void setStatusArray( ArrayList<String> statusArray)
    {
        this.statusArray = statusArray;
    }

    public ArrayList<String> getDriver_nameArray()
    {
        return this.driver_nameArray;
    }

    public void setDriver_nameArray( ArrayList<String> driver_nameArray)
    {
        this.driver_nameArray = driver_nameArray;
    }

    public ArrayList<String> getNumberArray()
    {
        return this.numberArray;
    }

    public void setNumberArray( ArrayList<String> numberArray)
    {
        this.numberArray = numberArray;
    }
    public ArrayList<String> getCar_nameArray()
    {
        return this.car_nameArray;
    }

    public void setCar_nameArray( ArrayList<String> car_nameArray)
    {
        this.car_nameArray = car_nameArray;
    }
    public ArrayList<String> getAdress_fromArray()
    {
        return this.adress_fromArray;
    }

    public void setAdress_fromArray( ArrayList<String> adress_fromArray)
    {
        this.adress_fromArray = adress_fromArray;
    }
    public ArrayList<String> getAdress_toArray()
    {
        return this.adress_toArray;
    }

    public void setAdress_toArray( ArrayList<String> adress_toArray)
    {
        this.adress_toArray = adress_toArray;
    }
    public ArrayList<String> getCreatedArray()
    {
        return this.createdArray;
    }

    public void setCreatedArray( ArrayList<String> createdArray)
    {
        this.createdArray = createdArray;
    }
    public ArrayList<String> getCompanyArray()
    {
        return this.companyArray;
    }

    public void setCompanyArray( ArrayList<String> companyArray)
    {
        this.companyArray = companyArray;
    }
    public ArrayList<String> getPassenger_fioArray()
    {
        return this.passenger_fioArray;
    }

    public void setPassenger_fioArray( ArrayList<String> passenger_fioArray)
    {
        this.passenger_fioArray = passenger_fioArray;
    }
    public ArrayList<String> getDate_timeArray()
    {
        return this.date_timeArray;
    }

    public void setDate_timeArray( ArrayList<String> date_timeArray)
    {
        this.date_timeArray = date_timeArray;
    }

    public static synchronized Globals getInstance()
    {
        if(instance == null)
        {
            instance = new Globals();
        }
        return instance;
    }
}
