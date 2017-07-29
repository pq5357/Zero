package sg.com.base;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethod;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Created by sg on 2017/6/13.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseInterface {

    protected Context context = this;

    private boolean isStop;

    private Dialog dialog;

    private BaseProgressDialog progressDialog;

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
    public void showInput(EditText editText) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }
    //----------------------------------------------------------------------------------

    public Dialog getProgressDialog() {
        return progressDialog;
    }

    public Dialog getDialog() {
        return dialog;
    }

    //-----------------------------------------------------------------------------------
    public void showProgressDialog() {
        showProgressDialog(new ProgressBar(context));
    }

    public void showProgrssDialog(@LayoutRes int id) {
        showProgressDialog(LayoutInflater.from(context).inflate(id, null));
    }

    public void showProgressDialog(View v) {
        dismissProgressDialog();
        progressDialog = BaseProgressDialog.newInstance(context);
        progressDialog.setContentView(v);
        progressDialog.show();
    }

    public void showDialog(Context context, View contentView) {
        dismissDialog();
        dialog = new Dialog(context);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(false);
        getDialogWindow(dialog);
        dialog.show();
    }

    private void getDialogWindow(Dialog dialog) {
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = (int) (getWidthPixel() * 0.9f);
        lp.gravity = Gravity.CENTER;
        window.setAttributes(lp);
    }


    public void showDialogFragment(DialogFragment dialogFragment) {
        String tag = dialogFragment.getTag() != null ? dialogFragment.getTag() : dialogFragment
                .getClass().getSimpleName();
        showDialogFragment(dialogFragment, tag);
    }

    private void showDialogFragment(DialogFragment dialogFragment, String tag) {
        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    public void dismissProgressDialog() {
        dismissDialog(progressDialog);
    }

    public void dismissDialog() {
        dismissDialog(dialog);
    }

    public void dismissDialog(Dialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public void dismissDialogFragment(DialogFragment dialogFragment) {
        if (dialogFragment != null) {
            dialogFragment.dismiss();
        }
    }

    //-----------------------------------------------------------------
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void registerEvent(Object obj) {
        EventBus.getDefault().register(obj);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)

    public void unregisterEvent(Object obj) {
        EventBus.getDefault().unregister(obj);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sendEvent(Object obj){
        EventBus.getDefault().post(obj);
    }

    //----------------------------------------------------------------

    protected DisplayMetrics getDisplayMetrics() {
        return getResources().getDisplayMetrics();
    }

    protected int getWidthPixel() {
        return getDisplayMetrics().widthPixels;
    }

    protected int getHeightPixel() {
        return getDisplayMetrics().heightPixels;
    }
}
