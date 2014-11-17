package ru.id20.android;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class MyDatePickerActivity extends Activity implements View.OnClickListener
{
    private static final String tag = "MyDatePickerActivity";

    SeekBar sb1, sb2;
    TextView tv1;
    int hours;
    int minutes;
    private ImageView calendarToJournalButton;
    private Button currentMonth;
    private ImageView prevMonth;
    private ImageView nextMonth;
    private GridView calendarView;
    private GridCellAdapter adapter;
    private Calendar _calendar, calendar;
    private int month, year;
    private final DateFormat dateFormatter = new DateFormat();
    private static final String dateTemplate = "MMMM yyyy";
    private List<String> list;
    TextView time, test;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        _calendar = Calendar.getInstance(Locale.getDefault());
        month = _calendar.get(Calendar.MONTH) + 1;
        year = _calendar.get(Calendar.YEAR);
        Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);

        prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
        prevMonth.setOnClickListener(this);

        currentMonth = (Button) this.findViewById(R.id.currentMonth);
        setMonth();

        nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
        nextMonth.setOnClickListener(this);

        calendarView = (GridView) this.findViewById(R.id.calendar);

        time = (TextView)findViewById(R.id.timeNow);
        test = (TextView)findViewById(R.id.hours);
        // Initialised
        adapter = new GridCellAdapter(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
        calendarView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        calendarView.setSelection(5);

        sb1 = (SeekBar)findViewById(R.id.seekBar1);
        sb2 = (SeekBar)findViewById(R.id.seekBar2);

        sb1.setOnSeekBarChangeListener(seekBarChangeListener);
        sb2.setOnSeekBarChangeListener(seekBarChangeListener);
    }

    private void setGridCellAdapterToDate(int month, int year)
    {
        adapter = new GridCellAdapter(getApplicationContext(), R.id.calendar_day_gridcell, month+1, year);
        _calendar.set(year, month, _calendar.get(Calendar.DAY_OF_MONTH));
        setMonth();
        adapter.notifyDataSetChanged();
        calendarView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v)
    {
        if (v == prevMonth)
        {
            if (month <= 1)
            {
                month = 12;
                year--;
            }
            else
            {
                month--;
            }
            Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(month-1, year);
        }
        if (v == nextMonth)
        {
            if (month > 11)
            {
                month = 1;
                year++;
            }
            else
            {
                month++;
            }
            Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
            setGridCellAdapterToDate(month-1, year);
        }

    }

    @Override
    public void onDestroy()
    {
        Log.d(tag, "Destroying View ...");
        super.onDestroy();
    }

    public class GridCellAdapter extends BaseAdapter implements View.OnClickListener
    {
        private static final String tag = "GridCellAdapter";
        private final Context _context;


        private static final int DAY_OFFSET = 1;
        private final String[] weekdays = new String[]{"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Вс"};
        private final String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        private final int month, year;
        private int daysInMonth, prevMonthDays;
        private int currentDayOfMonth;
        private int currentWeekDay;
        private Button gridcell;
        private TextView num_events_per_day;
        private final HashMap eventsPerMonthMap;
        private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MMM.yyyy");

        // Days in Current Month
        public GridCellAdapter(Context context, int textViewResourceId, int month, int year)
        {
            super();
            this._context = context;
            list = new ArrayList<String>();
            this.month = month;
            this.year = year;

            Log.d(tag, "==> Passed in Date FOR Month: " + month + " " + "Year: " + year);
            Calendar calendar = Calendar.getInstance();
            setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
            setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
            Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
            Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
            Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

            // Print Month
            printMonth(month, year);

            // Find Number of Events
            eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
        }
        private String getMonthAsString(int i)
        {
            return months[i];
        }

        private String getWeekDayAsString(int i)
        {
            return weekdays[i];
        }

        private int getNumberOfDaysOfMonth(int i)
        {
            return daysOfMonth[i];
        }

        public String getItem(int position)
        {
            return list.get(position);
        }

        @Override
        public int getCount()
        {
            return list.size();
        }

        /**
         * Prints Month
         *
         * @param mm
         * @param yy
         */
        private void printMonth(int mm, int yy)
        {
            Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);

            int trailingSpaces = 0;
            int leadSpaces = 0;
            int daysInPrevMonth = 0;
            int prevMonth = 0;
            int prevYear = 0;
            int nextMonth = 0;
            int nextYear = 0;

            int currentMonth = mm - 1;
            String currentMonthName = getMonthAsString(currentMonth);
            daysInMonth = getNumberOfDaysOfMonth(currentMonth);

            Log.d(tag, "Current Month: " + " " + currentMonthName + " having " + daysInMonth + " days.");

            // Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
            GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
            Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

            if (currentMonth == 11)
            {
                prevMonth = currentMonth - 1;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 0;
                prevYear = yy;
                nextYear = yy + 1;
                Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else if (currentMonth == 0)
            {
                prevMonth = 11;
                prevYear = yy - 1;
                nextYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                nextMonth = 1;
                Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }
            else
            {
                prevMonth = currentMonth - 1;
                nextMonth = currentMonth + 1;
                nextYear = yy;
                prevYear = yy;
                daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
                Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
            }

            // Compute how much to leave before before the first day of the
            // month.
            // getDay() returns 0 for Sunday.
            int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 2;
            trailingSpaces = currentWeekDay;

            Log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay+1));
            Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
            Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

            if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1)
            {
                ++daysInMonth;
            }

            // Trailing Month days
            for (int i = 0; i < trailingSpaces; i++)
            {
                Log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
                list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
            }

            // Current Month Days
            for (int i = 1; i <= daysInMonth; i++)
            {
                Log.d(currentMonthName, String.valueOf(i) + " " + getMonthAsString(currentMonth) + " " + yy);
                if (i == getCurrentDayOfMonth())
                {
                    list.add(String.valueOf(i) + "-BLACK" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
                else
                {
                    list.add(String.valueOf(i) + "-BLACK" + "-" + getMonthAsString(currentMonth) + "-" + yy);
                }
            }

            // Leading Month days
            for (int i = 0; i < list.size() % 7; i++)
            {
                Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
                list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
            }
        }

        /**
         * NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
         * ALL entries from a SQLite database for that month. Iterate over the
         * List of All entries, and get the dateCreated, which is converted into
         * day.
         *
         * @param year
         * @param month
         * @return
         */
        private HashMap findNumberOfEventsPerMonth(int year, int month)
        {
            HashMap map = new HashMap<String, Integer>();
            // DateFormat dateFormatter2 = new DateFormat();
            //
            // String day = dateFormatter2.format("dd", dateCreated).toString();
            //
            // if (map.containsKey(day))
            // {
            // Integer val = (Integer) map.get(day) + 1;
            // map.put(day, val);
            // }
            // else
            // {
            // map.put(day, 1);
            // }
            return map;
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View row = convertView;
            if (row == null)
            {
                LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
            }

            // Get a reference to the Day gridcell
            gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
            gridcell.setOnClickListener(this);

            // ACCOUNT FOR SPACING

            Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
            String[] day_color = list.get(position).split("-");
            String theday = day_color[0];
            String themonth = day_color[2];
            String theyear = day_color[3];
            if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null))
            {
                if (eventsPerMonthMap.containsKey(theday))
                {
                    num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);
                    Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
                    num_events_per_day.setText(numEvents.toString());
                }
            }

            // Set the Day GridCell
            gridcell.setText(theday);
            gridcell.setTag(theday + "." + themonth + "." + theyear);
            Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);

            if (day_color[1].equals("GREY"))
            {
                gridcell.setTextColor(Color.LTGRAY);
            }
            if (day_color[1].equals("BLACK"))
            {
                gridcell.setTextColor(Color.BLACK);
            }
            if (day_color[1].equals("BLUE"))
            {
                gridcell.setTextColor(getResources().getColor(R.color.static_text_color));
            }
            return row;
        }
        @Override
        public void onClick(View view)
        {
            String date_month_year = (String) view.getTag();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyDatePickerActivity.this);
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("date_month_year",date_month_year);
            editor.apply();
        }

        public int getCurrentDayOfMonth()
        {
            return currentDayOfMonth;
        }

        private void setCurrentDayOfMonth(int currentDayOfMonth)
        {
            this.currentDayOfMonth = currentDayOfMonth;
        }
        public void setCurrentWeekDay(int currentWeekDay)
        {
            this.currentWeekDay = currentWeekDay;
        }
        public int getCurrentWeekDay()
        {
            return currentWeekDay;
        }
    }
    public void setMonth()
    {
        String str = dateFormatter.format(dateTemplate, _calendar.getTime()).toString();

        calendar = Calendar.getInstance(TimeZone.getDefault(), java.util.Locale.getDefault());
        calendar.setTime(new Date());

        if(str.contains("сентября"))
            str = str.replace(str, "Сентябрь " + year);
        if(str.contains("октября"))
            str = str.replace(str, "Октябрь " + year);
        if(str.contains("ноября"))
            str = str.replace(str, "Ноябрь " + year);
        if(str.contains("декабря"))
            str = str.replace(str, "Декабрь " + year);
        if(str.contains("января"))
            str = str.replace(str, "Январь " + year);
        if(str.contains("февраля"))
            str = str.replace(str, "Февраль " + year);
        if(str.contains("марта"))
            str = str.replace(str, "Март " + year);
        if(str.contains("апреля"))
            str = str.replace(str, "Апрель " + year);
        if(str.contains("мая"))
            str = str.replace(str, "Май " + year);
        if(str.contains("июня"))
            str = str.replace(str, "Июнь " + year);
        if(str.contains("июля"))
            str = str.replace(str, "Июль " + year);
        if(str.contains("августа"))
            str = str.replace(str, "Август " + year);

        currentMonth.setText(str);
    }
    public void clickNow(View v)
    {

        String currentTimeString = (String) DateFormat.format("kk:mm",new Date());
        time.setText(currentTimeString);

        String currentDateString = (String) DateFormat.format("dd.MM.yyyy",new Date());
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyDatePickerActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("date_month_year",currentDateString);
        editor.apply();
    }

    private SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener()
    {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            hours = sb1.getProgress();
            minutes = sb2.getProgress();
            if(hours < 10 && minutes >= 10)
                time.setText("0" + hours + ":" + minutes);
            else if(hours < 10)
                time.setText("0" + hours + ":" + "0" + minutes);
            else if(minutes >= 10)
                time.setText(hours + ":" + minutes);
            else if(minutes < 10)
                time.setText(hours + ":" + "0" + minutes);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar)
        {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar)
        {

        }
    };
    public void clickCLose(View v)
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MyDatePickerActivity.this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("time",time.getText().toString());
        editor.apply();

        //startActivity(new Intent(MyDatePickerActivity.this, CreateTaskActivity.class));
        finish();
    }

    /*
    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //startActivity(new Intent(MyDatePickerActivity.this, CreateTaskActivity.class));
    }
    */
}