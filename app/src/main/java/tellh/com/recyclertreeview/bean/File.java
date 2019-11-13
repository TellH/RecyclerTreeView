package tellh.com.recyclertreeview.bean;

import tellh.com.recyclertreeview.R;
import tellh.com.recyclertreeview_lib.LayoutItemType;

public class File implements LayoutItemType {
    private boolean isSelect = false;
    private String name;//

    public File() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public boolean isSelect() {
        return isSelect;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_list;
    }
}
