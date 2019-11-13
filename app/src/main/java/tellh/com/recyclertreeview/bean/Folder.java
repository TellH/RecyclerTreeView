package tellh.com.recyclertreeview.bean;

import java.util.List;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

/**
 * 节点数
 */
public class Folder implements LayoutItemType {
    private boolean isSelect = false;

    /** 名称 */
    private String name;

    /** 数据列表 */
    List<File> itemList;//

    /** 子节点 */
    private List<Folder> children; //（结果嵌套）

    public Folder() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<File> getItemList() {
        return itemList;
    }

    public void setItemList(List<File> itemList) {
        this.itemList = itemList;
    }

    public List<Folder> getChildren() {
        return children;
    }

    public void setChildren(List<Folder> children) {
        this.children = children;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_folder;
    }
}
