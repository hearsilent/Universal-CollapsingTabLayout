package hearsilent.universalcollapsingtoolbarlayouttablayoutexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.R
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.databinding.FragmentDemoBinding
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.databinding.ListDemoBinding
import hearsilent.universalcollapsingtoolbarlayouttablayoutexample.fragment.DemoFragment.DemoAdapter.DemoViewHolder

class DemoFragment : Fragment() {

    private lateinit var mBinding: FragmentDemoBinding

    private lateinit var mAdapter: DemoAdapter
    private val mList = ArrayList<String>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDemoBinding.inflate(inflater, container, false)

        initValues()
        setUpViews()

        return mBinding.root
    }

    private fun initValues() {
        for (i in 0..99) {
            val item = "HearSilent $i"
            mList.add(item)
        }
    }

    private fun setUpViews() {
        mAdapter = DemoAdapter()
        mBinding.recyclerView.setHasFixedSize(true)
        mBinding.recyclerView.setItemAnimator(DefaultItemAnimator())
        mBinding.recyclerView.setLayoutManager(LinearLayoutManager(requireContext()))
        mBinding.recyclerView.setAdapter(mAdapter)
    }

    inner class DemoAdapter : RecyclerView.Adapter<DemoViewHolder>() {

        inner class DemoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var binding = ListDemoBinding.bind(view)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DemoViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.list_demo, parent, false)
            return DemoViewHolder(view)
        }

        override fun onBindViewHolder(holder: DemoViewHolder, position: Int) {
            holder.binding.textView.text = mList[position]
        }

        override fun getItemCount(): Int {
            return mList.size
        }
    }
}
