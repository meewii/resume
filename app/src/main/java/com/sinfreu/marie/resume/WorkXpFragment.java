package com.sinfreu.marie.resume;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

		TextView tv = (TextView) rootView.findViewById(R.id.section_label);
		tv.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				FragmentManager manager = getActivity().getSupportFragmentManager();
				FragmentTransaction trans = manager.beginTransaction();
				trans.replace(R.id.container, new WorkXpDetailsFragment());
				trans.commit();

			}
		});

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
