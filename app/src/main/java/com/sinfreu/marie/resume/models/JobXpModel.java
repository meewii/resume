package com.sinfreu.marie.resume.models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.sinfreu.marie.resume.objects.JobXp;
import com.sinfreu.marie.resume.utils.FileService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class JobXpModel {

	Context context;
	String mLang;
	private ArrayList<JobXp> mJobXpList = new ArrayList<>();
	protected static JobXpModel singleton;

	public JobXpModel(Context context) {
		this.context = context;
	}

	public static JobXpModel getInstance(Context context, String lang) {
		// refresh instance if the language settings changed
		if(singleton == null || !lang.contentEquals(singleton.mLang)) {
			singleton = new JobXpModel(context);
			singleton.mLang = lang;
			singleton.setJobXpList();
		}
		return singleton;
	}

	public ArrayList<JobXp> getJobXpList() {
		return mJobXpList;
	}

	public void setJobXpList() {
		new JobXpAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
	}

	public class JobXpAsyncTask extends AsyncTask<Void, Void, ArrayList<JobXp>> {

		@Override
		protected ArrayList<JobXp> doInBackground(Void... arg0) {
			try {

				String json = FileService.loadJSONFromAsset(context, "jobxp.json");

				if (json != null) {
					JSONArray jsonArray = new JSONArray(json);
					JobXp jobXp;
					String companyName = "", jobPosition = "";
					JSONObject jobPositionLangs = null;
					long startingDate = 0L, endingDate = 0L;

					for (int i = 0; i < jsonArray.length(); ++i) {
						JSONObject jsonObj = (JSONObject) jsonArray.get(i);

						if (!jsonObj.isNull("companyName")) companyName = jsonObj.getString("companyName");
						if (!jsonObj.isNull("jobPosition")) jobPositionLangs = jsonObj.getJSONObject("jobPosition");

						if(jobPositionLangs != null && mLang != null && !mLang.isEmpty()) {
							switch (mLang) {
								case "de":
									if(jobPositionLangs.has("de")) jobPosition = jobPositionLangs.getString("de");
									break;
								case "en":
									if(jobPositionLangs.has("en")) jobPosition = jobPositionLangs.getString("en");
									break;
								case "fr":
									if(jobPositionLangs.has("fr")) jobPosition = jobPositionLangs.getString("fr");
									break;
								default:
									if(jobPositionLangs.has("en")) jobPosition = jobPositionLangs.getString("en");
							}
						}
						if (!jsonObj.isNull("startingDate")) startingDate = jsonObj.getLong("startingDate");
						if (!jsonObj.isNull("endingDate")) endingDate = jsonObj.getLong("endingDate");

						jobXp = new JobXp(companyName, jobPosition, startingDate, endingDate);

						mJobXpList.add(jobXp);
					}
					return mJobXpList;
				}

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

	}

}