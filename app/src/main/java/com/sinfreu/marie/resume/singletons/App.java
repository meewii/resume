package com.sinfreu.marie.resume.singletons;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class App extends Application {

	private static final String LOG_TAG = "App";
	private static Context mContext;
	private static Activity mActivity;
	private static int mLastNavChoice = 0;
	private static SharedPreferences mPreferences;

	public static Context getContext() {
		return mContext;
	}

	public static void init(Activity activity) {
		mContext = activity.getApplicationContext();
		mActivity = activity;

		if(getStringPreference("lang") == null) {
			setPreference("lang", "en");
		}
	}

	public static SharedPreferences getPreferences() {
		if(mPreferences == null) {
			mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
		}
		return mPreferences;
	}
	public static void setPreference(String key, String lang) {
		SharedPreferences.Editor editor = getPreferences().edit();
		editor.putString(key, lang);
		editor.apply();
	}
	public static String getStringPreference(String key) {
		Log.d("WorkXpFragment", "LANGUE - getStringPreference lang: "+getPreferences().getString(key, ""));
		return getPreferences().getString(key, "");
	}

	public static void setLastNavChoice(int nav) {
		mLastNavChoice = nav;
	}
	public static int getLastNavChoice() {
		return mLastNavChoice;
	}
}
