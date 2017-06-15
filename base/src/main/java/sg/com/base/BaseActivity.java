package sg.com.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.greenrobot.eventbus.EventBus;

import static android.R.attr.start;
import static android.transition.Fade.IN;


/**
 * Created by sg on 2017/6/13.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseInterface {

    protected Context context = this;

    private boolean isStop;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerEvent(this);
        initUI();
        initData();
        addListeners();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterEvent(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isStop = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        isStop = true;
    }

    //-----------------------------------------------------------------------------------------
    public static View getRootView(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView();
        FrameLayout content = (FrameLayout) viewGroup.findViewById(android.R.id.content);
        return content.getChildAt(0);
    }

    protected View inflate(@LayoutRes int id) {
        return inflate(id, null);
    }

    protected View inflate(@LayoutRes int id, @Nullable ViewGroup root) {
        return LayoutInflater.from(context).inflate(id, root);
    }

    protected <T extends View> T findView(@IdRes int id) {
        return (T) findViewById(id);
    }

    protected void setOnclickListener(@IdRes int id, View.OnClickListener onClickListener) {
        findViewById(id).setOnClickListener(onClickListener);
    }

    //------------------------------------------------------------------------------------------
    public void replaceFragment(@IdRes int resId, Fragment fragment) {

    }

    public void replaceFragment(@IdRes int resId, Fragment fragment, boolean isBackStack) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(resId, fragment);
        if (isBackStack) {
            fragmentTransaction.addToBackStack(null);
        }
        fragmentTransaction.commit();

    }

    //----------------------------------------------------------------------------------
    public boolean checkInput(TextView tv) {
        return checkInput(tv, NONE);
    }

    public boolean checkInput(TextView tv, @StringRes int id) {
        return checkInput(tv, id, false);
    }

    private boolean checkInput(TextView tv, @StringRes int id, boolean isShake) {

        if (TextUtils.isEmpty(tv.getText())) {
            if (isShake) {
                startShake(tv, id);
            } else {

            }
            return false;
        }
        return true;
    }

    private void startShake(TextView tv, int id) {
        startShake(tv);
    }

    private void startShake(TextView tv) {
        YoYo.with(Techniques.Shake).duration(700)
                .repeat(3).playOn(tv);
    }
    //-----------------------------------------------------------------------------------
    /**
     * 隐藏软键盘
     */
    public void hideInput(EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), InputMethodManager
                .HIDE_IMPLICIT_ONLY);
    }
    /**
     * 显示软键盘
     */
    public void showInput(EditText editText){
        InputMethodManager imm  = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
    //----------------------------------------------------------------------------------


    public static void registerEvent(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public static void unregisterEvent(Object obj) {
        EventBus.getDefault().unregister(obj);
    }
}
