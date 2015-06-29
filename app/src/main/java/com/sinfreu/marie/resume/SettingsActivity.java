package com.sinfreu.marie.resume;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.Locale;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.sinfreu.marie.resume.singletons.App;


public class SettingsActivity extends ActionBarActivity {

	private String LOG_TAG = "SettingsActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);

		Toolbar toolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		String[] languages = getResources().getStringArray(R.array.languages);
		ListView lvLanguages = (ListView) findViewById(R.id.lvLanguages);
		lvLanguages.setAdapter(new LanguagesArrayAdapter(this,
				R.layout.list_item_navigation, languages));
		// Set the list's click listener
		lvLanguages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				selectItem(i);
			}
		});

	}


	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
//		// update the main content by replacing fragments

		switch(position) {
			case 0:
				setLocale("de");
				break;
			case 1:
				setLocale("en");
				break;
			case 2:
				setLocale("fr");
				break;
			default:
		}
	}

	public void setLocale(String lang) {
		App.setLocale(lang);

		Intent refresh = new Intent(this, HomeActivity.class);
		startActivity(refresh);
		finish();
	}

	public class LanguagesArrayAdapter extends ArrayAdapter<String> {

		// private final Context context;
		private final String[] items;
		private final int viewId;

		public LanguagesArrayAdapter(Context context, int viewId, String[] items) {
			super(context, viewId, items);
			this.viewId = viewId;
			this.items = items;
		}

		@Override
		public View getView(int position, View row, ViewGroup parent) {

			ViewHolder holder;
			LayoutInflater mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (row == null) {

				holder = new ViewHolder();
				row = mInflater.inflate(viewId, parent, false);

				holder.textView = (TextView) row.findViewById(R.id.label);

				row.setTag(viewId, holder);

			} else {
				holder = (ViewHolder) row.getTag(viewId);
			}

			String s = items[position];
			holder.textView.setText(s);

			return row;
		}

		class ViewHolder {
			TextView textView;
		}
	}


//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.menu_settings, menu);
//
//		Log.d(LOG_TAG, "ACTIONBAR - onCreateOptionsMenu...");
//
//		return super.onCreateOptionsMenu(menu);
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		int id = item.getItemId();
//
//
//		return mDrawerToggle.onOptionsItemSelected(item);
//	}


}
