package sg.com.base.utils;

import android.view.View;

/**
 * 没有多次点击的监听器,建议所有点击时间监听器使用该类
 * Created by sg on 2017/6/19.
 */

public abstract class NoDoubleClickListener implements View.OnClickListener {

    private static final long DOUBLE_CLICK_GAP = 2000l;

    private long lastClickTime = 0l;

    private int mId = -1;


    @Override
    public final void onClick(View v) {

        long currentClickTime = System.currentTimeMillis();

        int id = v.getId();

        if(mId != id){
            mId = id;
            lastClickTime = currentClickTime;
            onNoDoubleClick();
            return;
        }

        if((currentClickTime - lastClickTime) > DOUBLE_CLICK_GAP){
            lastClickTime = currentClickTime;
            onNoDoubleClick();
        }
    }

    public abstract void onNoDoubleClick();
}
