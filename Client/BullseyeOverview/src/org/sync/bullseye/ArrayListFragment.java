package org.sync.bullseye;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class ArrayListFragment extends ListFragment {
	int mNum;

	/**
	 * Create a new instance of CountingFragment, providing "num"
	 * as an argument.
	 */
	static ArrayListFragment newInstance(int num) {
		ArrayListFragment f = new ArrayListFragment();

		// Supply num input as an argument.
		Bundle args = new Bundle();
		args.putInt("num", num);
		f.setArguments(args);

		return f;
	}
	/**
	 * When creating, retrieve this instance's number from its arguments.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mNum = getArguments() != null ? getArguments().getInt("num") : 1;
	}

	/**
	 * The Fragment's UI is just a simple text view showing its
	 * instance number.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		if(mNum==0){
			LeftFragmentSupport leftHelper;
			v = inflater.inflate(R.layout.left_fragment, container, false);
			leftHelper= new LeftFragmentSupport(v,this.getActivity());
			
		}
		if(mNum == 1){
			MiddleFragmentSupport middleHelper;
			v = inflater.inflate(R.layout.middle_fragment, container, false);
			View tv = v.findViewById(R.id.text);
			((TextView)tv).setText("This is the middle frag, has graphics");
			middleHelper = new MiddleFragmentSupport(v);
			
		}
		if(mNum==2){
			RightFragmentSupport rightHelper;
			v = inflater.inflate(R.layout.right_fragment, container, false);
			rightHelper = new RightFragmentSupport(v,this.getActivity());
		}
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, 1));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Log.i("FragmentList", "Item clicked: " + id);
	}
}


