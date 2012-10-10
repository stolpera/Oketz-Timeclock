package okets.com.timeclock;

import java.text.DateFormat;
import java.util.Date;

import okets.com.timeclock.Not;
import okets.com.timeclock.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TimeClockInsertNew extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	Button sqlNew;
	EditText sqlNumber;

	// String returnedNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeclockinsertnew);

		sqlNew = (Button) findViewById(R.id.bSQLNew);
		sqlNumber = (EditText) findViewById(R.id.etSQLnumber);
		sqlNew.setOnClickListener(this);

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bSQLNew:
			String s = sqlNumber.getText().toString();
			long l = Long.parseLong(s);
			if (s != null) {

					boolean didItWork = true;
					try {
						String number = sqlNumber.getText().toString();
						//String date_time = getDateTime();
						Not entry = new Not(this);
						entry.open();
						entry.createEntry(number, "new", "new");
						entry.close();
					} catch (Exception e) {
						didItWork = false;
						String error = e.toString();
						Dialog d = new Dialog(this);
						d.setTitle("no go");
						TextView tv = new TextView(this);
						tv.setText(error);
						d.setContentView(tv);
						d.show();
					} finally {
						if (didItWork) {
							Dialog d = new Dialog(this);
							d.setTitle("new worker added");
							TextView tv = new TextView(this);
							tv.setText("Success");
							d.setContentView(tv);
							d.show();
							sqlNumber.setText("");
						}
					}
				} else {
					Toast t = Toast.makeText(TimeClockInsertNew.this,
							"not valid worker ID", Toast.LENGTH_LONG);
					t.show();
				}
			break;
/*		case R.id.bSQLUpdateEnd:
			boolean didItWork1 = true;
			try {

				String number = sqlNumber.getText().toString();
				String date_time = getDateTime();

				Not entry = new Not(TimeClockInsertNew.this);
				entry.open();
				entry.createEntry(number, date_time);
				entry.close();

			} catch (Exception e) {
				didItWork1 = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("no go");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork1) {
					Dialog d = new Dialog(this);
					d.setTitle("you bet");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}

			break;
			*/
		case R.id.bSQLopenView:
			Intent i = new Intent("okets.com.timeclock.TimeClockView");
			startActivity(i);
			break;
		}
	}

	private String getDateTime() {
		/*
		 * SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd_HH:mm:ss");
		 * String currentDateandTime = sdf.format (new Date(0)); return
		 * currentDateandTime;
		 * 
		 * Time ctime = new Time(); Int ct = ctime.setToNow(); Date cdate = new
		 * Date(System.currentTimeMillis()); String dt = (cdate, ctime);
		 * 
		 * return (dt);
		 */
		String currentDateTimeString = DateFormat.getDateTimeInstance().format(
				new Date());
		return (currentDateTimeString);
	}

}