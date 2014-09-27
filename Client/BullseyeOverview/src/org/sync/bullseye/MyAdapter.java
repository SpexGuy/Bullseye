package org.sync.bullseye;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
	public MyAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public int getCount() {
		return 3; //hard coded
	}

	@Override
	public Fragment getItem(int position) {
		return ArrayListFragment.newInstance(position);
	}
}