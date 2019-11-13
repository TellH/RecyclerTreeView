package com.nlinks.minielectric_helper_android.module.message

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import tellh.com.recyclertreeview.view.SelectTreeView
import tellh.com.recyclertreeview.R
import tellh.com.recyclertreeview.bean.File
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder

/**
 * 项目名称：minielectric-helper-android
 * 类描述：
 * @author hxiaohui0@linewell.com
 * 创建时间：2019/10/22 0022 下午 4:54
 */
class FileBinder(var rv: RecyclerView): TreeViewBinder<FileBinder.ViewHolder>()
{
    override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<*>) {
        var data = (node.content as? File)
        if(data?.isSelect == true)
        {
            holder.iv_select.setImageResource(R.drawable.icon_xuanze)
        }else
        {
            holder.iv_select.setImageResource(R.drawable.icon_weixuan)
        }

        holder.itemView.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                data?.isSelect = !data?.isSelect!!
                SelectTreeView.adjustParentIsCheckAll(node)
                rv.adapter?.notifyDataSetChanged()
            }

        })
        holder.tv_item.text = data?.name
    }



    override fun getLayoutId(): Int {
        return R.layout.item_list
    }

    override fun provideViewHolder(itemView: View?): ViewHolder {
        return ViewHolder(itemView!!)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
    {
        lateinit var tv_item:TextView
        lateinit var iv_select:ImageView
        init {
            tv_item = view.findViewById(R.id.tv_item)
            iv_select = view.findViewById(R.id.iv_select)
        }
    }
}