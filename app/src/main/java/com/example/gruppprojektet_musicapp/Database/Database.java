package com.example.gruppprojektet_musicapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class Database extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "database.db";

    //USERS
    private static final String TABLE_USER = "user";

    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_NAME = "user_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_CITY = "user_city";
    private static final String COLUMN_USER_ADRESS = "user_adress";
    private static final String COLUMN_USER_POSTALCODE = "user_postalcode";
    private static final String COLUMN_USER_DATEOFBIRTH = "user_dateofbirth";
    private static final String COLUMN_USER_GENDER = "user_gender";
    private static final String COLUMN_USER_TYPE = "user_type";
    private static final String COLUMN_USER_GENREID = "user_genreid";

    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_ADRESS + " TEXT,"
            + COLUMN_USER_POSTALCODE + " TEXT," + COLUMN_USER_DATEOFBIRTH + " TEXT," + COLUMN_USER_GENREID + " INTEGER,"
            + COLUMN_USER_GENDER + " TEXT," + COLUMN_USER_CITY + " TEXT," + COLUMN_USER_TYPE + " TEXT," + COLUMN_USER_PASSWORD + " TEXT, "
            + "FOREIGN KEY (user_genreid) REFERENCES genre(genre_id)" + ")";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;


    //Genres
    private static final String TABLE_GENRE = "genre";

    private static final String COLUMN_GENRE_ID = "genre_id";
    private static final String COLUMN_GENRE_NAME = "name";

    private String CREATE_GENRE_TABLE = "CREATE TABLE " + TABLE_GENRE + "(" + COLUMN_GENRE_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_GENRE_NAME + " TEXT" + ")";

    private String DROP_GENRE_TABLE = "DROP TABLE IF EXISTS " + TABLE_GENRE;

    //Artists
    private static final String TABLE_ARTIST = "artist";

    private static final String COLUMN_ARTIST_ID = "artist_id";
    private static final String COLUMN_ARTIST_NAME = "artist_name";
    private static final String COLUMN_ARTIST_GENRE = "artist_genre";
    private static final String COLUMN_ARTIST_DESCRIPTION = "artist_description";
    private static final String COLUMN_ARTIST_RATING = "artist_rating";
    private static final String COLUMN_ARTIST_NEWSID = "artist_newsid";
    private static final String COLUMN_ARTIST_CONCERTID = "artist_concertid";

    private String CREATE_ARTIST_TABLE = "CREATE TABLE " + TABLE_ARTIST + "(" + COLUMN_ARTIST_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_ARTIST_DESCRIPTION + " TEXT," + COLUMN_ARTIST_RATING + " INTEGER,"
            + COLUMN_ARTIST_GENRE + " TEXT,"
            + COLUMN_ARTIST_NEWSID + " TEXT,"
            + COLUMN_ARTIST_CONCERTID + " TEXT,"
            + COLUMN_ARTIST_NAME + " TEXT, " + "FOREIGN KEY (artist_concertid) REFERENCES concert(concert_id)," + "FOREIGN KEY (artist_newsid) REFERENCES news(news_id)" + ")";

    private String DROP_ARTIST_TABLE = "DROP TABLE IF EXISTS " + TABLE_ARTIST;

    //News
    private static final String TABLE_NEWS = "news";

    private static final String COLUMN_NEWS_ID = "news_id";
    private static final String COLUMN_NEWS_NAME = "news_name";
    private static final String COLUMN_NEWS_DESCRIPTION = "news_description";

    private String CREATE_NEWS_TABLE = "CREATE TABLE " + TABLE_NEWS+ "(" + COLUMN_NEWS_ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NEWS_DESCRIPTION + " TEXT," + COLUMN_NEWS_NAME + " TEXT " + ")";

    private String DROP_NEWS_TABLE = "DROP TABLE IF EXISTS " + TABLE_NEWS;

    //Concerts
    private static final String TABLE_CONCERT = "concert";

    private static final String COLUMN_CONCERT_ID = "concert_id";
    private static final String COLUMN_CONCERT_NAME = "concert_name";
    private static final String COLUMN_CONCERT_VENUE = "concert_venue";
    private static final String COLUMN_CONCERT_LOCATION = "concert_location";
    private static final String COLUMN_CONCERT_DATE = "concert_date";
    private static final String COLUMN_CONCERT_TIME = "conert_time";
    private static final String COLUMN_CONCERT_PRICE = "concert_price";
    private static final String COLUMN_CONCERT_RATING = "concert_rating";
    private static final String COLUMN_CONCERT_USERID = "concert_userid";

    private String CREATE_CONCERT_TABLE = "CREATE TABLE "
            + TABLE_CONCERT + "("
            + COLUMN_CONCERT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CONCERT_DATE + " TEXT,"
            + COLUMN_CONCERT_NAME + " TEXT,"
            + COLUMN_CONCERT_VENUE + " TEXT,"
            + COLUMN_CONCERT_LOCATION + " TEXT,"
            + COLUMN_CONCERT_PRICE + " TEXT,"
            + COLUMN_CONCERT_TIME + " TEXT,"
            + COLUMN_CONCERT_RATING + " INTEGER,"
            + COLUMN_CONCERT_USERID + " INTEGER,"
            + "FOREIGN KEY (concert_userid) REFERENCES user(user_id)"
            + ")";

    private String DROP_CONCERT_TABLE = "DROP TABLE IF EXISTS " + TABLE_CONCERT;

    //MYSHOWS
    private static final String TABLE_MYSHOWS = "myshows";

    private static final String COLUMN_MYSHOWS_ID = "myshows_id";
    private static final String COLUMN_MYSHOWS_USERID = "myshows_userid";
    private static final String COLUMN_MYSHOWS_CONCERTID = "myshows_concertid";
    private static final String COLUMN_MYSHOWS_INTERESTED = "myshows_interested";
    private static final String COLUMN_MYSHOWS_GOING = "myshows_going";
    private static final String COLUMN_MYSHOWS_USERRATING = "myshows_userrating";

    private String CREATE_MYSHOWS_TABLE = "CREATE TABLE " + TABLE_MYSHOWS + "("
            + COLUMN_MYSHOWS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_MYSHOWS_USERID + " INTEGER,"
            + COLUMN_MYSHOWS_CONCERTID + " INTEGER," + COLUMN_MYSHOWS_INTERESTED + " INTEGER,"
            + COLUMN_MYSHOWS_GOING + " INTEGER," + COLUMN_MYSHOWS_USERRATING + " INTEGER,"
            + "FOREIGN KEY (myshows_concertid) REFERENCES concert(concert_id),"
            + "FOREIGN KEY (myshows_userid) REFERENCES user(user_id)" + ")";

    private String DROP_MYSHOWS_TABLE = "DROP TABLE IF EXISTS " + TABLE_MYSHOWS;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_GENRE_TABLE);
        db.execSQL(CREATE_ARTIST_TABLE);
        db.execSQL(CREATE_CONCERT_TABLE);
        db.execSQL(CREATE_NEWS_TABLE);
        db.execSQL(CREATE_MYSHOWS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_GENRE_TABLE);
        db.execSQL(DROP_ARTIST_TABLE);
        db.execSQL(DROP_CONCERT_TABLE);
        db.execSQL(DROP_NEWS_TABLE);
        db.execSQL(DROP_MYSHOWS_TABLE);
        onCreate(db);
    }

    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_ADRESS, user.getAdress());
        values.put(COLUMN_USER_CITY, user.getCity());
        values.put(COLUMN_USER_DATEOFBIRTH, user.getDateofbirth());
        values.put(COLUMN_USER_GENDER, user.getGender());
        values.put(COLUMN_USER_POSTALCODE, user.getPostalcode());
        values.put(COLUMN_USER_TYPE, "normal");


        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addArtist(Artist artist) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ARTIST_NAME, artist.getName());
        values.put(COLUMN_ARTIST_GENRE, artist.getGenre());
        values.put(COLUMN_ARTIST_DESCRIPTION, artist.getDescription());
        values.put(COLUMN_ARTIST_RATING, artist.getRating());

        db.insert(TABLE_ARTIST, null, values);
        db.close();
    }

    public void addNews(News news)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NEWS_NAME, news.getName());
        values.put(COLUMN_NEWS_DESCRIPTION, news.getDescription());

        db.insert(TABLE_NEWS, null, values);
        db.close();
    }

    public void addConcert(Concert concert) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CONCERT_NAME, concert.getName());
        values.put(COLUMN_CONCERT_VENUE, concert.getVenue());
        values.put(COLUMN_CONCERT_LOCATION, concert.getLocation());
        values.put(COLUMN_CONCERT_DATE, concert.getDate());
        values.put(COLUMN_CONCERT_TIME, concert.getTime());
        values.put(COLUMN_CONCERT_PRICE, concert.getPrice());
        values.put(COLUMN_CONCERT_RATING, concert.getRating());

        db.insert(TABLE_CONCERT, null, values);
        db.close();
    }

    public Cursor getAllConcerts() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_CONCERT_NAME
        };

        List<Concert> concertsList = new ArrayList<Concert>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_CONCERT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        // Traversing through all rows and adding to list

        //db.close();
        // return user list
        return cursor;
    }

    public Cursor getAllNews() {

        // array of columns to fetch
        String[] columns = {
                COLUMN_NEWS_NAME
        };

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_NEWS, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        return cursor;
    }


    public void setMyShowsGoing(String email, String ConcertName) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns_concert = {
                COLUMN_CONCERT_ID
        };

        String selection_concert = COLUMN_CONCERT_NAME + " = ?";

        String[] selectionArgs_concert = {ConcertName};

        Cursor cursorConcertId = db.query(TABLE_CONCERT, //Table to query
                columns_concert,    //columns to return
                selection_concert,        //columns for the WHERE clause
                selectionArgs_concert,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String concertID;
        cursorConcertId.moveToFirst();
        concertID = cursorConcertId.getString(0);

        String[] columns_user = {
                COLUMN_USER_ID
        };

        String selection_user = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs_user = {email};

        Cursor cursorUserId = db.query(TABLE_USER, //Table to query
                columns_user,    //columns to return
                selection_user,        //columns for the WHERE clause
                selectionArgs_user,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        String userID;
        cursorUserId.moveToFirst();
        userID = cursorUserId.getString(0);

        SQLiteDatabase dbWrite = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MYSHOWS_USERID, (Integer.parseInt(userID)));
        values.put(COLUMN_MYSHOWS_GOING, (1));
        values.put(COLUMN_MYSHOWS_CONCERTID, (Integer.parseInt(concertID)));

        dbWrite.insert(TABLE_MYSHOWS, null, values);
    }

    public void setMyShowsInterested(String email, String ConcertName) {


        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns_concert = {
                COLUMN_CONCERT_ID
        };

        String selection_concert = COLUMN_CONCERT_NAME + " = ?";

        String[] selectionArgs_concert = {ConcertName};

        Cursor cursorConcertId = db.query(TABLE_CONCERT, //Table to query
                columns_concert,    //columns to return
                selection_concert,        //columns for the WHERE clause
                selectionArgs_concert,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order

        String concertID;
        cursorConcertId.moveToFirst();
        concertID = cursorConcertId.getString(0);

        String[] columns_user = {
                COLUMN_USER_ID
        };

        String selection_user = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs_user = {email};

        Cursor cursorUserId = db.query(TABLE_USER, //Table to query
                columns_user,    //columns to return
                selection_user,        //columns for the WHERE clause
                selectionArgs_user,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                null); //The sort order


        String userID;
        cursorUserId.moveToFirst();
        userID = cursorUserId.getString(0);

        SQLiteDatabase dbWrite = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_MYSHOWS_USERID, (Integer.parseInt(userID)));
        values.put(COLUMN_MYSHOWS_INTERESTED, (1));
        values.put(COLUMN_MYSHOWS_CONCERTID, (Integer.parseInt(concertID)));

        dbWrite.insert(TABLE_MYSHOWS, null, values);

    }

    public Cursor getIntrested(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        final String MY_QUERY = "SELECT " + COLUMN_CONCERT_NAME + " FROM "+ TABLE_CONCERT + " a INNER JOIN " + TABLE_MYSHOWS + " b INNER JOIN " + TABLE_USER + " c ON a.concert_id=b.myshows_concertid AND b.myshows_userid=c.user_id WHERE b.myshows_interested=? AND c.user_email=?";
        Cursor cursor = db.rawQuery(MY_QUERY, new String[]{"1",email});

        return cursor;

    }

    public Cursor getGoing(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        final String MY_QUERY = "SELECT " + COLUMN_CONCERT_NAME + " FROM "+ TABLE_CONCERT + " a INNER JOIN " + TABLE_MYSHOWS + " b INNER JOIN " + TABLE_USER + " c ON a.concert_id=b.myshows_concertid AND b.myshows_userid=c.user_id WHERE b.myshows_going=? AND c.user_email=?";
        Cursor cursor = db.rawQuery(MY_QUERY, new String[]{"1",email});

        return cursor;

    }

    public User getUser(String email) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_NAME,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_ADRESS,
                COLUMN_USER_CITY,
                COLUMN_USER_DATEOFBIRTH,
                COLUMN_USER_GENDER,
                COLUMN_USER_POSTALCODE
        };

        String selection = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        User user = new User();
        while (cursor.moveToNext())
        {
            user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
            user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
            user.setAdress(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
            user.setCity(cursor.getString(cursor.getColumnIndex(COLUMN_USER_CITY)));
            user.setDateofbirth(cursor.getString(cursor.getColumnIndex(COLUMN_USER_DATEOFBIRTH)));
            user.setGender(cursor.getString(cursor.getColumnIndex(COLUMN_USER_GENDER)));
            user.setPostalcode(cursor.getString(cursor.getColumnIndex(COLUMN_USER_POSTALCODE)));
        }
        cursor.close();
        db.close();

        return user;
    }

    public void updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        // updating row
        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                new String[]{String.valueOf(user.getId())});
        db.close();
    }

    public boolean checkUser(String email) {

        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        String selection = COLUMN_USER_EMAIL + " = ?";

        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public boolean addData()
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        ///////////////////////////CONCERT//////////////////////////////////////////////////////
        //testdata
        contentValues.put(COLUMN_CONCERT_NAME, "Inflames");
        contentValues.put(COLUMN_CONCERT_LOCATION, "Örthagsparken");
        contentValues.put(COLUMN_CONCERT_VENUE, "Hello");
        contentValues.put(COLUMN_CONCERT_TIME, "21:00");
        contentValues.put(COLUMN_CONCERT_PRICE, "500kr");
        contentValues.put(COLUMN_CONCERT_RATING, "4/5");

        contentValues.put(COLUMN_CONCERT_DATE, "Date");
        contentValues.put(COLUMN_CONCERT_USERID, "Inflames");

        long result = db.insert(TABLE_CONCERT, null, contentValues);

        //testdata
        contentValues.put(COLUMN_CONCERT_NAME, "Tjo");
        contentValues.put(COLUMN_CONCERT_LOCATION, "Stadsparken");
        contentValues.put(COLUMN_CONCERT_VENUE, "Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test Test");
        contentValues.put(COLUMN_CONCERT_TIME, "10:00");
        contentValues.put(COLUMN_CONCERT_PRICE, "200kr");
        contentValues.put(COLUMN_CONCERT_RATING, "2/5");

        contentValues.put(COLUMN_CONCERT_DATE, "Date");
        contentValues.put(COLUMN_CONCERT_USERID, "Tjo");
        db.insert(TABLE_CONCERT, null, contentValues);
        //testdata

        contentValues.put(COLUMN_CONCERT_NAME, "Red Hot Chili Peppers");
        contentValues.put(COLUMN_CONCERT_LOCATION, "Backstage Rockbar");
        contentValues.put(COLUMN_CONCERT_VENUE, "Hello");
        contentValues.put(COLUMN_CONCERT_TIME, "20:00");
        contentValues.put(COLUMN_CONCERT_PRICE, "500kr");
        contentValues.put(COLUMN_CONCERT_RATING, "5/5");

        contentValues.put(COLUMN_CONCERT_DATE, "Date");
        contentValues.put(COLUMN_CONCERT_USERID, "");
        db.insert(TABLE_CONCERT, null, contentValues);


        ////////////////////////////ARTIST/////////////////////////////////////////////////////
        ContentValues artistValues = new ContentValues();

        SQLiteDatabase data = this.getWritableDatabase();

        String entry = "Inflames";

        //String query = "SELECT * FROM concert WHERE concert_name=" + entry;
        Cursor  cursor =  db.rawQuery("select * from " +  TABLE_CONCERT  +  " where " + COLUMN_CONCERT_NAME  + "=?", new String[] {entry});

        String concertID;
        if (cursor != null) {
            cursor.moveToFirst();
            concertID = cursor.getString(0);
        }
        else {concertID = "13";}

        artistValues.put(COLUMN_ARTIST_NAME, "Inflames");
        artistValues.put(COLUMN_ARTIST_RATING, "4.5/5");
        artistValues.put(COLUMN_ARTIST_GENRE, "Metal");
        artistValues.put(COLUMN_ARTIST_DESCRIPTION, "In a music scene full of seemingly endless subgenres and transient trends, In Flames are an example of what it means to steadfastly stay true to your vision. Since forming in Gothenburg, Sweden, in 1990 the legendary melodic metal act have toured the planet countless times and influenced many of today's biggest metal acts without ever ceasing to push their own signature sound forward.");

        artistValues.put(COLUMN_ARTIST_NEWSID, "");
        artistValues.put(COLUMN_ARTIST_CONCERTID, concertID);
        db.insert(TABLE_ARTIST, null, artistValues);



        cursor = db.rawQuery("select * from " +  TABLE_CONCERT  +  " where " + COLUMN_CONCERT_NAME  + "=?", new String[] {"Tjo"});
        if (cursor != null) {
            cursor.moveToFirst();
            concertID = cursor.getString(0);
        }
        else {concertID = "13";}

        artistValues.put(COLUMN_ARTIST_NAME, "Tjo");
        artistValues.put(COLUMN_ARTIST_RATING, "1/5");
        artistValues.put(COLUMN_ARTIST_GENRE, "Funk");
        artistValues.put(COLUMN_ARTIST_DESCRIPTION, "We are three guys that play for fun.");

        artistValues.put(COLUMN_ARTIST_NEWSID, "");
        artistValues.put(COLUMN_ARTIST_CONCERTID, concertID);
        db.insert(TABLE_ARTIST, null, artistValues);


        cursor = db.rawQuery("select * from " +  TABLE_CONCERT  +  " where " + COLUMN_CONCERT_NAME  + "=?", new String[] {"Red Hot Chili Peppers"});
        if (cursor != null) {
            cursor.moveToFirst();
            concertID = cursor.getString(0);
        }
        else {concertID = "13";}

        artistValues.put(COLUMN_ARTIST_NAME, "Red Hot Chili Peppers");
        artistValues.put(COLUMN_ARTIST_RATING, "5/5");
        artistValues.put(COLUMN_ARTIST_GENRE, "Funk");
        artistValues.put(COLUMN_ARTIST_DESCRIPTION, "Red Hot Chili Peppers are an American rock band formed in Los Angeles in 1983. The group's musical style primarily consists of alternative rock with an emphasis on funk, as well as elements from other genres such as punk rock and psychedelic rock. When played live, their music incorporates elements of jam band due to the improvised nature of many of their performances. Currently, the band consists of founding members vocalist Anthony Kiedis and bassist Flea (Michael Peter Balzary), longtime drummer Chad Smith, and guitarist John Frusciante. Red Hot Chili Peppers are one of the best-selling bands of all time with over 80 million records sold worldwide, they have been nominated for sixteen Grammy Awards, of which they have won six, and are the most successful band in alternative rock radio history, currently holding the records for most number-one singles (13), most cumulative weeks at number one (85) and most top-ten songs (25) on the Billboard Alternative Songs chart.[1] In 2012, they were inducted into the Rock and Roll Hall of Fame.");

        artistValues.put(COLUMN_ARTIST_NEWSID, "");
        artistValues.put(COLUMN_ARTIST_CONCERTID, concertID);
        db.insert(TABLE_ARTIST, null, artistValues);

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }

    //För att få listor med information om artist och konserter till concertInfo
    public String getConcertId(String entry)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        //concert
        Cursor concert = db.rawQuery("select * from " +  "concert"  +  " where " + "concert_name"  + "=?", new String[] {entry});
        concert.moveToFirst();
        String concertID = concert.getString(0);  // gav 1

        return concertID;
    }

    public Cursor getArtist (String concertID )
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor artist = db.rawQuery("select * from " +  "artist"  +  " where " + "artist_concertid"  + "=?", new String[] {concertID});
        return  artist;
    }
    public Cursor getNewsByID (String newsID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor news = db.rawQuery("select * from " +  "news",null);// + " where " + "news_id"  + "=?", new String[] {newsID});
        return news;
    }

    public Cursor getConcert (String concertID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor artist = db.rawQuery("select * from " +  "concert"  +  " where " + "concert_id"  + "=?", new String[] {concertID});
        return  artist;
    }
}
