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

public class TimeClockActivity extends Activity implements OnClickListener {
	/** Called when the activity is first created. */

	Button sqlUpdateS, sqlUpdateE, sqlView;
	EditText sqlNumber;
	String s;
	long l;

	// String returnedNumber;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeclockactivity);

		sqlUpdateS = (Button) findViewById(R.id.bSQLUpdateStart);
		sqlUpdateE = (Button) findViewById(R.id.bSQLUpdateEnd);
		sqlNumber = (EditText) findViewById(R.id.etSQLnumber);

		sqlView = (Button) findViewById(R.id.bSQLopenView);
		sqlView.setOnClickListener(this);

		sqlUpdateS.setOnClickListener(this);
		sqlUpdateE.setOnClickListener(this);
	}

	/*
	 * case R.id.bgetInfo: String s = sqlRow.getText().toString(); long l =
	 * Long.parseLong(s); Not hon = new Not(this); hon.open(); String
	 * returnedName = hon.getname(l); String returnedNumber = hon.getNumber(l);
	 * hon.close(); sqlName.setText(returnedName);
	 * sqlNumber.setText(returnedNumber); break;
	 */
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bSQLUpdateStart:
			s = sqlNumber.getText().toString();
			l = Long.parseLong(s);
			if (l == 1948) {
				Intent i = new Intent("okets.com.timeclock.TimeClockInsertNew");
				startActivity(i);
				sqlNumber.setText("");
			} else {
				Not hon = new Not(this);
				hon.open();
				String returnedNumber = hon.checkNumber(s);
				hon.close();
				if (returnedNumber != null) {

					boolean didItWork = true;
					try {
						String number = sqlNumber.getText().toString();
						String date_time = getDateTime();
						String in_out = "in";
						Not entry = new Not(TimeClockActivity.this);
						entry.open();
						entry.createEntry(number, date_time, in_out);
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
							d.setTitle("you bet");
							TextView tv = new TextView(this);
							tv.setText("Signed IN");
							d.setContentView(tv);
							d.show();
							sqlNumber.setText("");
						}
					}
				} else {
					Toast t = Toast.makeText(TimeClockActivity.this,
							"The Worker does not exist", Toast.LENGTH_LONG);
					t.show();
				}
			}
			break;
		case R.id.bSQLUpdateEnd:
			s = sqlNumber.getText().toString();
			l = Long.parseLong(s);
			Not hon = new Not(this);
			hon.open();
			String returnedNumber = hon.checkNumber(s);
			hon.close();
			if (returnedNumber != null) {

				boolean didItWork = true;
				try {
					String number = sqlNumber.getText().toString();
					String date_time = getDateTime();
					String in_out = "out";
					Not entry = new Not(TimeClockActivity.this);
					entry.open();
					entry.createEntry(number, date_time, in_out);
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
						d.setTitle("you bet");
						TextView tv = new TextView(this);
						tv.setText("Signed OUT");
						d.setContentView(tv);
						d.show();
						sqlNumber.setText("");
					}
				}
			} else {
				Toast t = Toast.makeText(TimeClockActivity.this,
						"The Worker does not exist", Toast.LENGTH_LONG);
				t.show();
			}
			break;
		case R.id.bSQLopenView:
			Intent i = new Intent("okets.com.timeclock.TimeClockView");
			startActivity(i);
			break;
		/*
		 * case R.id.bSQLopenView: Intent i = new
		 * Intent("org.example.sqlite.SQLView"); startActivity(i); break; case
		 * R.id.bgetInfo: String s = sqlRow.getText().toString(); long l =
		 * Long.parseLong(s); Not hon = new Not(this); hon.open(); String
		 * returnedName = hon.getname(l); String returnedNumber =
		 * hon.getNumber(l); hon.close();
		 * 
		 * sqlName.setText(returnedName); sqlNumber.setText(returnedNumber);
		 * 
		 * break; case R.id.bSQLmodify: String mName =
		 * sqlName.getText().toString(); String mNumber =
		 * sqlNumber.getText().toString(); String sRow =
		 * sqlRow.getText().toString(); long lRow = Long.parseLong(sRow);
		 * 
		 * Not ex = new Not(this); ex.open(); ex.updateEntry(lRow, mName,
		 * mNumber); ex.close(); break; case R.id.bSQLdelete: String sRow1 =
		 * sqlRow.getText().toString(); long lRow1 = Long.parseLong(sRow1); Not
		 * ex1 = new Not(this); ex1.open(); ex1.deleteEntry(lRow1); ex1.close();
		 * 
		 * break;
		 */
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

	/*
	 * private String checkIfWorkerExists() { TextView sqlRow = null; String s =
	 * sqlRow.getText().toString(); long l = Long.parseLong(s); Not hon = new
	 * Not(this); hon.open(); String returnedNumber = hon.getNumber(l); //
	 * hon.close(); return returnedNumber; }
	 */
}