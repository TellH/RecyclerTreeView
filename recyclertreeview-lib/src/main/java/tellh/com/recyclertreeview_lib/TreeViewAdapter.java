package tellh.com.recyclertreeview_lib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tlh on 2016/10/1 :)
 */
public class TreeViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<? extends TreeViewBinder> viewBinders;
    private List<TreeNode> displayNodes;
    private int padding = 30;
    private OnTreeNodeClickListener onTreeNodeClickListener;
    private boolean toCollapseChild;

    public TreeViewAdapter(List<? extends TreeViewBinder> viewBinders) {
        this(null, viewBinders);
    }

    public TreeViewAdapter(List<TreeNode> nodes, List<? extends TreeViewBinder> viewBinders) {
        displayNodes = new ArrayList<>();
        if (nodes != null)
            findDisplayNodes(nodes);
        this.viewBinders = viewBinders;
    }

    private void findDisplayNodes(List<TreeNode> nodes) {
        for (TreeNode node : nodes) {
            displayNodes.add(node);
            if (!node.isLeaf() && node.isExpand())
                findDisplayNodes(node.getChildList());
        }
    }

    @Override
    public int getItemViewType(int position) {
        return displayNodes.get(position).getContent().getLayoutId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(viewType, parent, false);
        if (viewBinders.size() == 1)
            return viewBinders.get(0).provideViewHolder(v);
        for (TreeViewBinder viewBinder : viewBinders) {
            if (viewBinder.getLayoutId() == viewType)
                return viewBinder.provideViewHolder(v);
        }
        return viewBinders.get(0).provideViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setPadding(displayNodes.get(position).getHeight() * padding, 3, 3, 3);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeNode selectedNode = displayNodes.get(holder.getLayoutPosition());
                if (onTreeNodeClickListener != null && onTreeNodeClickListener.onClick(selectedNode, holder))
                    return;
                if (selectedNode.isLeaf())
                    return;
                boolean isExpand = selectedNode.toggle();
                if (isExpand) {
                    notifyItemRangeInserted(holder.getLayoutPosition() + 1, addChildNodes(selectedNode, holder.getLayoutPosition() + 1));
                } else {
                    notifyItemRangeRemoved(holder.getLayoutPosition() + 1, removeChildNodes(selectedNode));
                }
            }
        });
        for (TreeViewBinder viewBinder : viewBinders) {
            if (viewBinder.getLayoutId() == displayNodes.get(position).getContent().getLayoutId())
                viewBinder.bindView(holder, position, displayNodes.get(position));
        }
    }

    private int addChildNodes(TreeNode pNode, int startIndex) {
        List<TreeNode> childList = pNode.getChildList();
        int addChildCount = 0;
        for (TreeNode treeNode : childList) {
            displayNodes.add(startIndex + addChildCount++, treeNode);
            if (treeNode.isExpand()) {
                addChildCount += addChildNodes(treeNode, startIndex + addChildCount);
            }
        }
        return addChildCount;
    }

    private int removeChildNodes(TreeNode pNode) {
        if (pNode.isLeaf())
            return 0;
        List<TreeNode> childList = pNode.getChildList();
        int removeChildCount = childList.size();
        displayNodes.removeAll(childList);
        for (TreeNode treeNode : childList) {
            if (treeNode.isExpand()) {
                if (toCollapseChild)
                    treeNode.toggle();
                removeChildCount += removeChildNodes(treeNode);
            }
        }
        return removeChildCount;
    }

    @Override
    public int getItemCount() {
        return displayNodes == null ? 0 : displayNodes.size();
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void ifCollapseChildWhileCollapseParent(boolean toCollapseChild) {
        this.toCollapseChild = toCollapseChild;
    }

    public void setOnTreeNodeClickListener(OnTreeNodeClickListener onTreeNodeClickListener) {
        this.onTreeNodeClickListener = onTreeNodeClickListener;
    }

    public interface OnTreeNodeClickListener {
        /**
         * @return weather consume the click event.
         */
        boolean onClick(TreeNode node, RecyclerView.ViewHolder holder);
    }

    public void refresh(List<TreeNode> treeNodes) {
        displayNodes.clear();
        findDisplayNodes(treeNodes);
        notifyDataSetChanged();
    }
}