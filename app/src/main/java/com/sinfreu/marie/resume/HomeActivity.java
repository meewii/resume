package com.sinfreu.marie.resume;

import android.support.v7.app.ActionBar;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.sinfreu.marie.resume.fragments.FormationFragment;
import com.sinfreu.marie.resume.fragments.HomeFragment;
import com.sinfreu.marie.resume.fragments.InterestsFragment;
import com.sinfreu.marie.resume.fragments.SkillsFragment;
import com.sinfreu.marie.resume.fragments.WorkXpFragment;
import com.sinfreu.marie.resume.singletons.App;

public class HomeActivity extends ActionBarActivity {

	private String LOG_TAG = "HomeActivity";

	private String[] mDrawerItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private CharSequence mTitle;
	private ActionBarDrawerToggle mDrawerToggle;
	private String mDrawerTitle;
	private Toolbar mToolbar;
	private ActionBar mActionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		App.init(this);

		mToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
		setSupportActionBar(mToolbar);
		mActionBar = getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(true);
		mActionBar.setHomeButtonEnabled(true);


		mDrawerItems = getResources().getStringArray(R.array.drawer_menu);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerTitle = LOG_TAG;
		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.navigation_drawer_open,
				R.string.navigation_drawer_close
		) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
				mToolbar.setTitle(mTitle);
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
				mToolbar.setTitle(mDrawerTitle);
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		mDrawerList = (ListView) findViewById(R.id.lvDrawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new DrawerArrayAdapter(this,
				R.layout.list_item_navigation, mDrawerItems));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		selectItem(App.getLastNavChoice());

	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			selectItem(position);
		}
	}

	/** Swaps fragments in the main content view */
	private void selectItem(int position) {
//		// update the main content by replacing fragments
		FragmentManager manager = getFragmentManager();
		FragmentTransaction trans = manager.beginTransaction();
		Fragment fragment;
		switch(position) {
			case 0:
				fragment = new HomeFragment();
				break;
			case 1:
				fragment = new WorkXpFragment();
				break;
			case 2:
				fragment = new FormationFragment();
				break;
			case 3:
				fragment = new SkillsFragment();
				break;
			case 4:
				fragment = new InterestsFragment();
				break;
			default:
				fragment = new HomeFragment();
		}

		App.setLastNavChoice(position);
		trans.replace(R.id.container, fragment);
		trans.commit();

		// Highlight the selected item, update the title, and close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(mDrawerItems[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	public void setTitle(CharSequence title) {
		mTitle = title;
		mActionBar = getSupportActionBar();
		if(mActionBar != null) mActionBar.setTitle(mTitle);
	}

	public class DrawerArrayAdapter extends ArrayAdapter<String> {

		// private final Context context;
		private final String[] items;
		private final int viewId;

		public DrawerArrayAdapter(Context context, int viewId, String[] items) {
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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.global, menu);

		Log.d(LOG_TAG, "ACTIONBAR - onCreateOptionsMenu...");

		mActionBar = getSupportActionBar();
		if(mActionBar != null) {
			mActionBar.setTitle(mTitle);
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
			startActivity(intent);
		}

		return mDrawerToggle.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

}
