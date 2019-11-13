package tellh.com.recyclertreeview.view

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.nlinks.minielectric_helper_android.module.message.FileBinder
import com.nlinks.minielectric_helper_android.module.message.FolderBinder
import kotlinx.android.synthetic.main.layout_empty_list.view.*
import kotlinx.android.synthetic.main.layout_select_view.view.*
import tellh.com.recyclertreeview.R
import tellh.com.recyclertreeview.bean.File
import tellh.com.recyclertreeview.bean.Folder
import tellh.com.recyclertreeview.util.UIUtils
import tellh.com.recyclertreeview_lib.LayoutItemType
import tellh.com.recyclertreeview_lib.TreeNode
import tellh.com.recyclertreeview_lib.TreeViewAdapter
import java.util.*
import kotlin.collections.ArrayList

/**
 * 项目名称：minielectric-helper-android
 * 类描述：消息列表过滤视图
 * @author hxiaohui0@linewell.com
 * 创建时间：2019/10/25 0025 上午 9:14
 */
class SelectTreeView(context: Context?, attrs: AttributeSet?) : LinearLayout(context, attrs){

    private var filterData: Folder? = null

    private var filterView:View? = null

    var filterListener: OnFilterListener? = null

    init {

        UIUtils.init(context?.applicationContext)

        filterView = LayoutInflater.from(context).inflate(R.layout.layout_select_view, this, true)

        setListeners()

        var rv = rv_filter
        rv.setEmptyView(llayout_empty_view)
        rv.layoutManager = LinearLayoutManager(context)
    }

    private fun setListeners() {
    }

    /**
     * 加载过滤配置数据
     */
    fun showFolders(data:ArrayList<Folder>) {
        filterData = Folder()
        filterData!!.children = data
        var firstNode = TreeNode(filterData!!)
        changeToNodes(firstNode)
        initFilterList(ArrayList(firstNode.childList))

    }

    /**
     * 设置过滤树形控件的列表适配器
     */
    private fun initFilterList(filterNodes : ArrayList<TreeNode<out LayoutItemType>>) {
        var rv = rv_filter
        var adapter = TreeViewAdapter(filterNodes, Arrays.asList(FolderBinder(rv), FileBinder(rv)))
        adapter.setOnTreeNodeListener(object : TreeViewAdapter.OnTreeNodeListener {
            override fun onClick(node: TreeNode<*>, holder: RecyclerView.ViewHolder?): Boolean {
                if (holder is FolderBinder.ViewHolder) {
                    onToggle(!node.isExpand, holder)
                }
                return false
            }

            override fun onToggle(isExpand: Boolean, holder: RecyclerView.ViewHolder?) {
                if(holder is FolderBinder.ViewHolder)
                {
                    val ivArrow = holder.iv_arrow
                    val rotateDegree = if (isExpand) 90 else -90
                    ivArrow!!.animate().rotationBy(rotateDegree.toFloat())
                        .start()
                }
            }
        })
        adapter.setPadding(UIUtils.dip2px(20F))
        rv.adapter = adapter
    }

    /**
     * 将数据转化为树形控件需要的数据类型
     */
    private fun changeToNodes(data: TreeNode<Folder>?) {
        if(data != null)
        {

            var bikeList = data.content.itemList
            if(bikeList != null && bikeList.isNotEmpty())
            {
                for(b in bikeList)
                {
                    var nodeChild = TreeNode(b)
                    data.addChild(nodeChild)
                }
            }

            var children = data.content.children
            if(children != null && children.size > 0)
            {
                for(c in children)
                {
                    var nodeChild = TreeNode(c)
                    data.addChild(nodeChild)
                    changeToNodes(nodeChild)
                }
            }

        }
    }


    /**
     * 获取过滤的车辆id列表
     */
    public fun getSelectCars(): ArrayList<File> {
        var list = arrayListOf<File>()
        if(filterData != null)
        {
            addItemCars(filterData, list)
        }
        return list
    }

    /**
     * 重置过滤消息设置
     */
    private fun resetSelect() {
        if(filterData == null)
        {
            return
        }
        resetItemCarSelect(Folder().apply { children = arrayListOf(filterData) })

        rv_filter.adapter?.notifyDataSetChanged()
    }

    /**
     * 递归方法：添加已选择的车辆id到到list中
     */
    private fun addItemCars(d: Folder?, list:ArrayList<File>)
    {
        var bikes = d?.itemList
        if(bikes != null)
        {
            var subList = bikes.filter { it.isSelect }
            subList?.forEach { list.add(it) }
        }

        if(d?.children != null)
        {
            for(c in d.children)
            {
                addItemCars(c, list)
            }
        }

    }

    /**
     * 重置车辆选择状态为未选择
     */
    private fun resetItemCarSelect(d: Folder?)
    {
        d?.isSelect = false

        d?.children?.forEach{
            it.isSelect = false
            resetItemCarSelect(it)
        }

        d?.itemList?.forEach {
            it.isSelect = false
        }
    }

    public interface OnFilterListener
    {
        public fun onSure()
        public fun onClose()
    }

    companion object{
        /**
         * 判断父节点是否全选
         */
        fun adjustParentIsCheckAll(node: TreeNode<*>)
        {
            var parent = node.parent
            if(parent != null)
            {
                var content = parent?.content as? Folder
                var child = content?.children
                var bikes = content?.itemList
                var res = true
                if(child != null)
                {
                    for(c in child)
                    {
                        res = res && c.isSelect
                        if(!res)
                        {
                            break
                        }
                    }
                }

                if(res) {
                    if (bikes != null) {
                        for (b in bikes) {
                            res = res && b.isSelect
                            if (!res) {
                                break
                            }
                        }
                    }
                }

                content?.isSelect = res
                adjustParentIsCheckAll(parent)
            }
        }
    }

}