package com.sinfreu.marie.resume.singletons;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;

import com.sinfreu.marie.resume.models.JobXpModel;

import java.util.Locale;

public class App extends Application {

	private static final String LOG_TAG = "App";
	private static Context mContext;
	private static int mLastNavChoice = 0;
	private static SharedPreferences mPreferences;
	private static JobXpModel mJobXpModel;

	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();

		String lang = getStringPreference("lang");
		if(lang == null || lang.isEmpty()) {
			setLocale("en");
			mJobXpModel = JobXpModel.getInstance(mContext, "en");
		} else {
			setLocale(lang);
			mJobXpModel = JobXpModel.getInstance(mContext, lang);
		}
	}

	public static Context getContext() {
		return mContext;
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
		return getPreferences().getString(key, "");
	}

	public static void setLocale(String lang) {
		setPreference("lang", lang);
		Resources res = mContext.getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = new Locale(lang);
		res.updateConfiguration(conf, dm);
	}

	public static void setLastNavChoice(int nav) {
		mLastNavChoice = nav;
	}
	public static int getLastNavChoice() {
		return mLastNavChoice;
	}
}
