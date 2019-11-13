package tellh.com.recyclertreeview.util;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class UIUtils {
    private static Context context;
    public static void init(Context context)
    {
        UIUtils.context = context;
    }
    public static int dip2px(float dip) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }

    public static float px2dip(int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return px / density;
    }

    // 获取图片
    public static Drawable getDrawableWithSize(int id) {
        Drawable dra = context.getResources().getDrawable(id);
        dra.setBounds(0, 0, dra.getIntrinsicWidth(), dra.getIntrinsicHeight());
        return dra;
    }

}
