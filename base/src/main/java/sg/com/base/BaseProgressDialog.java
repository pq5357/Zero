package sg.com.base;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.widget.ProgressBar;

/**
 * Created by sg on 2017/6/19.
 */

public class BaseProgressDialog extends Dialog {

    public static BaseProgressDialog newInstance(Context context){
        return new BaseProgressDialog(context);
    }


    public BaseProgressDialog(@NonNull Context context) {
        super(context);
        initUI();
    }

    public BaseProgressDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        initUI();
    }

    protected BaseProgressDialog(@NonNull Context context, boolean cancelable, @Nullable
            OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        initUI();
    }

    private void initUI() {
        setContentView(new ProgressBar(getContext()));

        getWindow().getAttributes().gravity = Gravity.CENTER;

        setCanceledOnTouchOutside(false);

    }


}
