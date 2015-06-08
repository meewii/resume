package com.sinfreu.marie.resume.fragments;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.sinfreu.marie.resume.R;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class WorkXpFragment extends Fragment {

	public WorkXpFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_work_xp, container, false);

		ListView lvCompanies = (ListView) rootView.findViewById(R.id.lv_companies);
//		lvCompanies.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//
//				FragmentManager manager = getActivity().getSupportFragmentManager();
//				FragmentTransaction trans = manager.beginTransaction();
//				trans.replace(R.id.container, new WorkXpDetailsFragment());
//				trans.commit();
//
//			}
//		});

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

}
