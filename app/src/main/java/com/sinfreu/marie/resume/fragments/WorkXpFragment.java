package com.sinfreu.marie.resume.fragments;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sinfreu.marie.resume.R;
import com.sinfreu.marie.resume.models.JobXpModel;
import com.sinfreu.marie.resume.objects.JobXp;
import com.sinfreu.marie.resume.singletons.App;

import java.util.ArrayList;

public class WorkXpFragment extends Fragment {

	public WorkXpFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_work_xp, container, false);

		ListView lvXp = (ListView) rootView.findViewById(R.id.lvWorkXp);

		String lang = App.getStringPreference("lang");


		JobXpModel jxm = JobXpModel.getInstance(getActivity(), lang);
		ArrayList<JobXp> jobs = jxm.getJobXpList();

		lvXp.setAdapter(new JobArrayAdapter(App.getContext(),
				R.layout.list_item_xp, jobs));

		return rootView;
	}



	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}

	public class JobArrayAdapter extends ArrayAdapter<JobXp> {

		// private final Context context;
		private final ArrayList<JobXp> items;
		private final Context context;
		private final int viewId;

		public JobArrayAdapter(Context context, int viewId, ArrayList<JobXp> items) {
			super(context, viewId, items);
			this.context = context;
			this.viewId = viewId;
			this.items = items;
		}

		@Override
		public View getView(int position, View row, ViewGroup parent) {

			ViewHolder holder;
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			if (row == null) {

				holder = new ViewHolder();
				row = mInflater.inflate(viewId, parent, false);

				holder.tvCompanyName = (TextView) row.findViewById(R.id.tvCompanyName);
				holder.tvJobPosition = (TextView) row.findViewById(R.id.tvJobPosition);
				holder.tvStartingDate = (TextView) row.findViewById(R.id.tvStartingDate);
				holder.tvEndingDate = (TextView) row.findViewById(R.id.tvEndingDate);

				row.setTag(viewId, holder);

			} else {
				holder = (ViewHolder) row.getTag(viewId);
			}

			JobXp job = items.get(position);
			holder.tvCompanyName.setText(job.getCompanyName());
			holder.tvJobPosition.setText(job.getJobPosition());
			holder.tvStartingDate.setText(job.getStartingDate()+"");
			holder.tvEndingDate.setText(job.getEndingDate()+"");

			return row;
		}

		class ViewHolder {
			TextView tvCompanyName, tvJobPosition, tvStartingDate, tvEndingDate;
		}
	}
}
