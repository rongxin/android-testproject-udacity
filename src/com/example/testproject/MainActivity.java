package com.example.testproject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			String[] forecastArray = { "Today - Sunny - 88/63",
					"Tomorrow - foggy - 72/63", "Weds - cloudy - 70/40",
					"Thurs - Asterios - 72/65", "Fri - Heavy Rain - 65/56",
					"Sat - HELP TRAPPED IN WHEATERSTATION - 60/51",
					"Sun - Sunny - 80/68" };

			List<String> weekForecast = new ArrayList(
					Arrays.asList(forecastArray));
			ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
					R.layout.list_item_layout, R.id.list_item_forecast_textview,
					weekForecast);
			
			
			ListView textView = (ListView)rootView.findViewById(R.id.listview_forecast);
			textView.setAdapter(arrayAdapter);
			
			return rootView;
		}
	}
}
