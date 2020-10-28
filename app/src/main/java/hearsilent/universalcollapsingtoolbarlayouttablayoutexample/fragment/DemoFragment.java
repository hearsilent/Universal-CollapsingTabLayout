package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.R;

public class DemoFragment extends Fragment {

	View mRootView;
	RecyclerView mRecyclerView;

	Context mContext;

	DemoAdapter mAdapter;

	List<String> mList = new ArrayList<>();

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
	                         @Nullable Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.fragment_demo, container, false);

		initValues();
		findViews();
		setUpViews();

		return mRootView;
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		mContext = context;
	}

	private void initValues() {
		for (int i = 0; i < 100; i++) {
			String item = "HearSilent " + i;
			mList.add(item);
		}
	}

	private void findViews() {
		mRecyclerView = mRootView.findViewById(R.id.recyclerView);
	}

	private void setUpViews() {
		mAdapter = new DemoAdapter();
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mRecyclerView.setAdapter(mAdapter);
	}

	public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.DemoViewHolder> {

		public class DemoViewHolder extends RecyclerView.ViewHolder {

			public TextView textView;

			public DemoViewHolder(View view) {
				super(view);

				textView = view.findViewById(R.id.textView);
			}
		}

		@NonNull
		@Override
		public DemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext())
					.inflate(R.layout.list_demo, parent, false);
			return new DemoViewHolder(view);
		}

		@Override
		public void onBindViewHolder(DemoViewHolder holder, int position) {
			holder.textView.setText(mList.get(position));
		}

		@Override
		public int getItemCount() {
			return mList.size();
		}
	}

}
