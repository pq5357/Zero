package sg.com.base.model;

import java.io.Serializable;

/**
 * Created by sg on 2017/6/13.
 */

public class EventMessage implements Serializable{

    public int what;

    public int num;

    public String str;

    public Object obj;

    public EventMessage(int what) {
        this.what = what;
    }

    public EventMessage(int what, int num) {
        this.what = what;
        this.num = num;
    }

    public EventMessage(int what, String str) {
        this.what = what;
        this.str = str;
    }

    public EventMessage(int what, Object obj) {
        this.what = what;
        this.obj = obj;
    }

    public EventMessage(int what, int num, String str) {
        this.what = what;
        this.num = num;
        this.str = str;
    }

    public EventMessage(int what, String str, Object obj) {
        this.what = what;
        this.str = str;
        this.obj = obj;
    }

    public EventMessage(int what, int num, String str, Object obj) {
        this.what = what;
        this.num = num;
        this.str = str;
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "what=" + what +
                ", num=" + num +
                ", str='" + str + '\'' +
                ", obj=" + obj +
                '}';
    }
}
