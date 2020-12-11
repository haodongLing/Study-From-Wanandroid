package com.haodong.study.wanandroid.ui.navigation

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.haodong.study.ktx.ext.dp2px
import com.haodong.study.mvvmcore.base.BaseVMFragment
import com.haodong.study.wanandroid.BR
import com.haodong.study.wanandroid.R
import com.haodong.study.wanandroid.adapter.NavigationAdapter
import com.haodong.study.wanandroid.adapter.VerticalTabAdapter
import com.haodong.study.wanandroid.databinding.FragmentNavigationBinding
import com.haodong.study.wanandroid.model.bean.Navigation
import kotlinx.android.synthetic.main.fragment_navigation.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import q.rorbin.verticaltablayout.VerticalTabLayout
import q.rorbin.verticaltablayout.widget.TabView

/**
 * created by linghaoDo on 2020/12/10
 * description:
 *
 * version:
 */
class NavigationFragment : BaseVMFragment<NavigationViewModel>() {
    private val navigationList = mutableListOf<Navigation>()
    private val tabAdapter by lazy {
        VerticalTabAdapter(navigationList.map { it.name })
    }
    private val navigationAdapter by lazy { NavigationAdapter() }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_navigation
    }

    override fun initVM(): NavigationViewModel {
        return getViewModel()
    }

    override fun initView() {
        mBinding.setVariable(BR.adapter, navigationAdapter)
    }

    override fun initData() {
       mViewModel.getNavigation()
    }

    override fun startObserve() {
       mViewModel.run { uiState.observe(viewLifecycleOwner, Observer { it?.let { getNavigation(it) } }) }
    }
    private fun initTabLayout(){
        tabLayout.addOnTabSelectedListener(object : VerticalTabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabView?, position: Int) {
            }

            override fun onTabSelected(tab: TabView?, position: Int) {
                scrollToPosition(position)
            }
        })
    }
    private fun scrollToPosition(position: Int){
        val mLayoutManager=(mBinding as
                FragmentNavigationBinding).navigationRecycleView.layoutManager as LinearLayoutManager
        val firstPosition=mLayoutManager.findLastVisibleItemPosition()
        val lastPosition=mLayoutManager.findLastVisibleItemPosition()
        when{
            position <= firstPosition || position >= lastPosition -> navigationRecycleView.smoothScrollToPosition(position)
            else -> navigationRecycleView.run {
                smoothScrollBy(0, this.getChildAt(position - firstPosition).top - this.dp2px(8))
            }
        }
    }

    private fun getNavigation(navigationList: List<Navigation>) {
        this.navigationList.clear()
        this.navigationList.addAll(navigationList)
        tabLayout.setTabAdapter(tabAdapter)

        navigationAdapter.setNewData(navigationList)
    }

}