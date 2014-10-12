package com.example.testproject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastFragment extends Fragment {

	public ForecastFragment() {
	}
	
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true); 
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
		inflater.inflate(R.menu.forecast_fragment, menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		int itemId = item.getItemId();
		if (itemId==R.id.action_refresh){
			FetcchWeatherTask fetchWeatherTask=new FetcchWeatherTask();
			fetchWeatherTask.execute();
			return true;
		}
		
		return super.onOptionsItemSelected(item);
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
	
	private class FetcchWeatherTask extends AsyncTask<Void, Void, Void>{
		private final String LOG_TAG=FetcchWeatherTask.class.getSimpleName();

		@Override
		protected Void doInBackground(Void... params) {
			// These two need to be declared outside the try/catch
			// so that they can be closed in the finally block.
			HttpURLConnection urlConnection = null;
			BufferedReader reader = null;
			 
			// Will contain the raw JSON response as a string.
			String forecastJsonStr = null;
			 
			Log.v(LOG_TAG, "test");
			try {
			    // Construct the URL for the OpenWeatherMap query
			    // Possible parameters are available at OWM's forecast API page, at
			    // http://openweathermap.org/API#forecast
			    URL url = new URL("http://api.openweathermap.org/data/2.5/forecast/daily?q=94043&mode=json&units=metric&cnt=7");
			 
			    // Create the request to OpenWeatherMap, and open the connection
			    urlConnection = (HttpURLConnection) url.openConnection();
			    urlConnection.setRequestMethod("GET");
			    urlConnection.connect();
			 
			    // Read the input stream into a String
			    InputStream inputStream = urlConnection.getInputStream();
			    StringBuffer buffer = new StringBuffer();
			    if (inputStream == null) {
			        // Nothing to do.
			        forecastJsonStr = null;
			    }
			    reader = new BufferedReader(new InputStreamReader(inputStream));
			 
			    String line;
			    while ((line = reader.readLine()) != null) {
			        // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
			        // But it does make debugging a *lot* easier if you print out the completed
			        // buffer for debugging.
			        buffer.append(line + "\n");
			    }
			 
			    if (buffer.length() == 0) {
			        // Stream was empty.  No point in parsing.
			        forecastJsonStr = null;
			    }
			    forecastJsonStr = buffer.toString();
			    Log.v(LOG_TAG, forecastJsonStr);
			} catch (IOException e) {
			    Log.e("PlaceholderFragment", "Error ", e);
			    // If the code didn't successfully get the weather data, there's no point in attempting
			    // to parse it.
			    forecastJsonStr = null;
			} finally{
			    if (urlConnection != null) {
			        urlConnection.disconnect();
			    }
			    if (reader != null) {
			        try {
			            reader.close();
			        } catch (final IOException e) {
			            Log.e("PlaceholderFragment", "Error closing stream", e);
			        }
			    }
			}
			
			return null;
		}

		
		
	}
	
}