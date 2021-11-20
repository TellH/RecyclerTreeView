package tellh.com.recyclertreeview_lib;

/**
 * Created by tlh on 2016/10/1 :)
 */

public interface LayoutItemType {
    int NO_TOGGLE_ATTACHED = -99;
    int ATTACH_FULL_VIEW_AS_TOGGLE=-88;

    int getLayoutId();

    int getToggleViewId();
}
