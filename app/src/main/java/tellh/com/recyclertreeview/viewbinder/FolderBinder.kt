package com.nlinks.minielectric_helper_android.module.message

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import tellh.com.recyclertreeview.view.SelectTreeView
import tellh.com.recyclertreeview.R
import tellh.com.recyclertreeview.bean.Folder
import tellh.com.recyclertreeview.util.UIUtils
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewBinder


class FolderBinder(var rv: RecyclerView): TreeViewBinder<FolderBinder.ViewHolder>()
{
    override fun bindView(holder: ViewHolder, position: Int, node: TreeNode<*>) {
        var data = node.content as? Folder
        if(data?.isSelect == true)
        {
            var dra = UIUtils.getDrawableWithSize(R.drawable.icon_xuanze)
            holder.tv_select.setCompoundDrawables(null, null, dra, null)
        }else
        {
            var dra = UIUtils.getDrawableWithSize(R.drawable.icon_weixuan)
            holder.tv_select.setCompoundDrawables(null, null, dra, null)
        }
        holder.tv_carshop_name.text = data?.name
        holder.iv_arrow.rotation = 0f
        val rotateDegree = if (node.isExpand) 90f else 0f
        holder.iv_arrow.rotation = rotateDegree

        holder.tv_select.setOnClickListener(object:View.OnClickListener{
            override fun onClick(p0: View?) {
                data?.isSelect = !data?.isSelect!!
                reverseAllChildSelect(data, data?.isSelect)
                SelectTreeView.adjustParentIsCheckAll(node)
                var iii = 0
                rv?.adapter?.notifyDataSetChanged()
            }

        })

    }

    private fun reverseAllChildSelect(data: Folder, select:Boolean) {
        data?.isSelect = select
        var child = data?.children
        var bikeList = data?.itemList
        if(child != null )
        {
            for(c in child)
            {
                reverseAllChildSelect(c, select)
                c.isSelect = select
            }
        }

        if(bikeList != null)
        {
            for(b in bikeList)
            {
                b.isSelect = select
            }
        }


    }

    override fun getLayoutId(): Int {
        return R.layout.item_folder
    }

    override fun provideViewHolder(itemView: View?): ViewHolder {
        return ViewHolder(itemView!!)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view)
    {
        lateinit var  iv_arrow:ImageView
        lateinit var tv_carshop_name:TextView
        lateinit var tv_select:TextView
        init {
            iv_arrow = view?.findViewById(R.id.iv_arrow)
            tv_carshop_name = view?.findViewById(R.id.tv_carshop_name)
            tv_select = view?.findViewById(R.id.tv_select)
        }
    }
}