package com.example.bugssolveinc.bangou.helper;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bugssolveinc.bangou.model.PointTable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {

	// Logcat tag
	private static final String LOG = "DatabaseHelper";

	// Database Version
	private static final int DATABASE_VERSION = 2;

	// Database Name
	private static final String DATABASE_NAME = "/mnt/sdcard/point.db";

	// Tab	le Names
	private static final String TABLE_PLAYER_ONE = "player_one";
	private static final String TABLE_PLAYER_POINT_DISPLAY = "player_total";
	private static final String TABLE_PLAYER_POINT_DISPLAY_HARD = "player_total_hard";

	// Common column names
	private static final String KEY_ID = "id";
	private static final String KEY_CREATED_AT = "created_at";

	// Player one Table - column nmaes
	private static final String KEY_POINTS_ONE = "points_one";

	// Player total point Table - column names
	private static final String KEY_TOTAL_POINT_ALL = "points_total";

	// Player total point Hard Table - column names
	private static final String KEY_TOTAL_POINT_ALL_HARD = "points_total_hard";

	// Player one Create Statements
	// Todo table create statement
	private static final String CREATE_TABLE_PLAYER_ONE = "CREATE TABLE "
			+ TABLE_PLAYER_ONE + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_POINTS_ONE + " INTEGER," + KEY_CREATED_AT
			+ " DATETIME" + ")";


	// Total point table create statement
	private static final String CREATE_TABLE_TOTAL_POINT = "CREATE TABLE "
			+ TABLE_PLAYER_POINT_DISPLAY + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TOTAL_POINT_ALL + " INTEGER," + KEY_CREATED_AT
			+ " DATETIME" + ")";

	// Total point hard table create statement
	private static final String CREATE_TABLE_TOTAL_POINT_HARD = "CREATE TABLE "
			+ TABLE_PLAYER_POINT_DISPLAY_HARD + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TOTAL_POINT_ALL_HARD + " INTEGER," + KEY_CREATED_AT
			+ " DATETIME" + ")";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		// creating required tables
		db.execSQL(CREATE_TABLE_PLAYER_ONE);
		db.execSQL(CREATE_TABLE_TOTAL_POINT);
		db.execSQL(CREATE_TABLE_TOTAL_POINT_HARD);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_ONE);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_POINT_DISPLAY);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER_POINT_DISPLAY_HARD);

		// create new tables
		onCreate(db);
	}


	// ------------------------ "Point one" table methods ----------------//

	/*
	 * Creating point one table
	 */
	public long createTableOne(PointTable points) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_POINTS_ONE, points.getPoints());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long tag_id = db.insert(TABLE_PLAYER_ONE, null, values);

		return tag_id;
	}

	/**
	 * getting all point from table one
	 * */
	public List<PointTable> getAllPointsFromTableOne() {

		List<PointTable> pointsList = new ArrayList<PointTable>();

		String selectQuery = "SELECT  * FROM " + TABLE_PLAYER_ONE;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				PointTable t = new PointTable();
				t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				t.setPoints(c.getInt(c.getColumnIndex(KEY_POINTS_ONE)));

				// adding to point list
				pointsList.add(t);
			} while (c.moveToNext());
		}
		return pointsList;
	}

	/*
	 * Updating a point table one
	 */
	public int updatePointTable(PointTable points) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_POINTS_ONE, points.getPoints());

		// updating row
		return db.update(TABLE_PLAYER_ONE, values, KEY_ID + " = ?",
				new String[] { String.valueOf(points.getId()) });
	}




	// ------------------------ "Total Point" table methods ----------------//

	public long createTableTotalPoint(PointTable points) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TOTAL_POINT_ALL, points.getPoints());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long tag_id = db.insert(TABLE_PLAYER_POINT_DISPLAY, null, values);

		return tag_id;
	}

	/**
	 * getting all point from total point table
	 * */
	public List<PointTable> getPointsFromTotalTable() {

		List<PointTable> pointsList = new ArrayList<PointTable>();

		String selectQuery = "SELECT  * FROM " + TABLE_PLAYER_POINT_DISPLAY;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToLast()) {
			do {
				PointTable t = new PointTable();
				t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				t.setPoints(c.getInt(c.getColumnIndex(KEY_TOTAL_POINT_ALL)));

				// adding to point list
				pointsList.add(t);
			} while (c.moveToNext());
		}
		return pointsList;
	}


	// ------------------------ "Total Point Hard" table methods ----------------//

	/*
	 * Creating point one table
	 */
	public long createTableHardPoint(PointTable points) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_TOTAL_POINT_ALL_HARD, points.getPoints());
		values.put(KEY_CREATED_AT, getDateTime());

		// insert row
		long tag_id = db.insert(TABLE_PLAYER_POINT_DISPLAY_HARD, null, values);

		return tag_id;
	}

	/**
	 * getting all point from table one
	 * */
	public List<PointTable> getAllPointsFromTableHardPoint() {

		List<PointTable> pointsList = new ArrayList<PointTable>();

		String selectQuery = "SELECT  * FROM " + TABLE_PLAYER_POINT_DISPLAY_HARD;

		Log.e(LOG, selectQuery);

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (c.moveToFirst()) {
			do {
				PointTable t = new PointTable();
				t.setId(c.getInt((c.getColumnIndex(KEY_ID))));
				t.setPoints(c.getInt(c.getColumnIndex(KEY_TOTAL_POINT_ALL_HARD)));

				// adding to point list
				pointsList.add(t);
			} while (c.moveToNext());
		}
		return pointsList;
	}


	public void deleteAll(){

		SQLiteDatabase db = this.getReadableDatabase();

		db.delete(TABLE_PLAYER_POINT_DISPLAY_HARD, null, null);
        db.close();
	}

	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	/**
	 * get datetime
	 * */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
}
