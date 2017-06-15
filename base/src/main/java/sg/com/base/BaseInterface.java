package sg.com.base;

import android.app.Activity;

import sg.com.base.model.EventMessage;

/**
 * Created by sg on 2017/6/13.
 */

public interface BaseInterface {


    int NONE = -1;

    int REQUEST = 0x01;

    int RESULT_OK = Activity.RESULT_OK;

    int RESULT_CANCELD = Activity.RESULT_CANCELED;

    String KEY_ID = "key_id";

    String KEY_TITLE = "key_title";

    String KEY_URL = "key_url";

    String KEY_SERIALIZABLE = "key_serializable";

    String KEY_OBJECT = "key_object";

    String KEY_FRAGMENT = "key_fragment";

    void initUI();

    void initData();

    void addListeners();

    void onEventMessage(EventMessage em);


}
