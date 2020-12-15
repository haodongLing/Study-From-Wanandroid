package com.haodong.study.wanandroid.ui.project

import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.haodong.study.mvvmcore.base.BaseVMFragment
import com.haodong.study.wanandroid.R
import com.haodong.study.wanandroid.model.bean.SystemParent
import kotlinx.android.synthetic.main.activity_project.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * created by linghaoDo on 2020/12/14
 * description:
 *
 * version:
 */
class ProjectFragment : BaseVMFragment<ProjectViewModel>(useDataBinding = false) {
    override fun getLayoutResId(): Int {
        return R.layout.activity_project
    }

    private val mProjectTypeList = mutableListOf<SystemParent>()
    open var isBlog = false

    override fun initVM(): ProjectViewModel {
        return getViewModel();
    }

    override fun initView() {
        initViewpager()
    }

    override fun initData() {
        if (isBlog) mViewModel.getBlogType()
        else mViewModel.getProjectTypeList()
    }

    override fun startObserve() {
        mViewModel.systemData.observe(viewLifecycleOwner, Observer {
            it.run {
                getProjectTypeList(it)
            }
        })
    }

    private fun getProjectTypeList(projectTpeList: List<SystemParent>) {
        mProjectTypeList.clear()
        mProjectTypeList.addAll(projectTpeList)
        projectViewPager.adapter?.notifyDataSetChanged()
    }


    private fun initViewpager() {
        projectViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return mProjectTypeList.size
            }

            override fun createFragment(position: Int): Fragment {
            }

        }
    }
    private fun chooseFragment(position:Int):Fragment{
        return if (isBlog) SystemTypeFragment.newInstance(mProjectTypeList[position].id, true)
        else ProjectTypeFragment.newInstance(mProjectTypeList[position].id, false)
    }


}