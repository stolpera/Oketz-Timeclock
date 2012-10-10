package okets.com.timeclock;

import okets.com.timeclock.Not;
import okets.com.timeclock.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TimeClockView extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.timeclockview);
		TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		Not info = new Not(this);
		info.open();
		String data = info.getdata();
		info.close();
		tv.setText(data);
	}
}


