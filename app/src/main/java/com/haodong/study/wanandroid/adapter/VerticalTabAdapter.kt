package com.haodong.study.wanandroid.adapter

import q.rorbin.verticaltablayout.adapter.TabAdapter
import q.rorbin.verticaltablayout.widget.ITabView

/**
 * created by linghaoDo on 2020/12/10
 * description:
 *
 * version:
 */
class VerticalTabAdapter(private val titles: List<String>) : TabAdapter {
    override fun getCount(): Int {
        return titles.size
    }

    override fun getBadge(position: Int) = null

    override fun getIcon(position: Int) = null

    override fun getTitle(position: Int): ITabView.TabTitle {
        return ITabView.TabTitle.Builder()
            .setContent(titles[position])
            .setTextColor(-0xc94365, -0x8a8a8b)
            .build()
    }

    override fun getBackground(position: Int): Int {
        TODO("Not yet implemented")
    }

}