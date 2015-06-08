package com.sinfreu.marie.resume.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sinfreu.marie.resume.R;

public class HomeFragment extends Fragment {

	private String LOG_TAG = "HomeFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_home, container, false);


		return rootView;
	}



//	@Override
//	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//		super.onCreateOptionsMenu(menu, inflater);
//
//		Log.d(LOG_TAG, "ACTIONBAR - onCreateOptionsMenu...");
//
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		return super.onOptionsItemSelected(item);
//	}

}
