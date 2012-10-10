package okets.com.timeclock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Not {
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NUMBER = "persons_number";
	public static final String KEY_DATE_TIME = "persons_date_time";
	public static final String KEY_IN_OUT = "in_out";
	private static final String DATABASE_NAME = "EmployeesDB1";
	private static final String DATABASE_TABLE = "EmployeesT1";
	private static final int DATABASE_VERSION = 1;
	
	private DbHelper ourHelper;
	private final Context ourContext;
	private SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
					KEY_NUMBER + " TEXT NOT NULL, " +
					KEY_DATE_TIME + " TEXT NOT NULL,  " +
					KEY_IN_OUT + " TEXT NOT NULL);"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}

	}
	public Not(Context c){
		ourContext = c;
		
	}
	public Not open() throws SQLException {
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourHelper.close();
	}
	public long createEntry(String number, String date_time, String in_out) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		
		cv.put(KEY_NUMBER, number);
		cv.put(KEY_DATE_TIME, date_time);
		cv.put(KEY_IN_OUT, in_out);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
		
	}
	public String getdata() {
		// TODO Auto-generated method stub
		String [] columns = new String[] {KEY_ROWID, KEY_NUMBER, KEY_DATE_TIME, KEY_IN_OUT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_ROWID);
		int iNumber = c.getColumnIndex(KEY_NUMBER);
		int iDate_Time = c.getColumnIndex(KEY_DATE_TIME);
		int iInOut = c.getColumnIndex(KEY_IN_OUT);
		
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(iRow) + " " + c.getString(iNumber) + " " + c.getString(iDate_Time) + " " + c.getString(iInOut) + "\n";
			}
		
		return result;
	}
	public String checkNumber(String l) {
		// TODO Auto-generated method stub
		String [] columns = new String[] {KEY_ROWID, KEY_NUMBER, KEY_DATE_TIME, KEY_IN_OUT};
		//ourDatabase.rawQuery("select count(*) from table_name where employee_id  = 52", selectionArgs)
		
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_NUMBER + "=" + l, null, null, null, null);
//		if (c != null){
		if (c.moveToFirst()) {
	//		c.moveToFirst();
			String number = c.getString(1);
			return number;
		}
		return null;
	}
	
	public String getNumber(String l) {
		// TODO Auto-generated method stub
		String [] columns = new String[] {KEY_ROWID, KEY_NUMBER, KEY_DATE_TIME, KEY_IN_OUT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String number = c.getString(1);
			return number;
		}
		return null;
	}
	public String getdate_time(long l) {
		// TODO Auto-generated method stub
		String [] columns = new String[] {KEY_ROWID, KEY_NUMBER, KEY_DATE_TIME, KEY_IN_OUT};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null);
		if (c != null){
			c.moveToFirst();
			String date_time = c.getString(2);
			return date_time;
		}
		
		
		return null;
	}
	
	
	public void updateEntry(long lRow, String  mNumber, String mdate_time) {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues();
		cvUpdate.put( KEY_NUMBER, mNumber);
		cvUpdate.put(KEY_DATE_TIME, mdate_time);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
		
		
		
	}
	public void deleteEntry(long lRow1) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1 , null);
		
	}
};

