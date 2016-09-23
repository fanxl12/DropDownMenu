package com.fanxl.dropmenu;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fanxl.dropmenu.entity.DropBo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by fanxl12 on 16/1/13.
 */
public abstract class AbsDrop<T> implements PopupWindow.OnDismissListener {

    protected final View anchor;

    protected PopupWindow popupWindow;

    protected ArrayList<T> arrayList = new ArrayList<>();

    protected DropListener localDropListener;

    private String title;

    private int position;

    private DropListener dropListener = new DropListener() {

        @Override
        public void dropComplete(int p, int type, DropBo... dropBos) {
            if (localDropListener != null) {
                localDropListener.dropComplete(p, type, dropBos);
            }
        }

        @Override
        public void dropDismiss() {
            if (localDropListener != null) {
                localDropListener.dropDismiss();
            }
        }
    };

    public AbsDrop(View anchor, String title) {
        this.anchor = anchor;
        this.title = title;
    }

    protected void initPopWindow(View view, Drawable bg) {
        if (bg==null){
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT, true);
            popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }else {
            popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setBackgroundDrawable(bg);
        }
        popupWindow.setOnDismissListener(this);
    }

    /**
     * 初始化
     */
    public abstract void init(List<T> arrayList);

    public void setDropListener(DropListener localDropListener) {
        this.localDropListener = localDropListener;
    }

    /**
     * @param type     下拉菜单类型
     * @param dropBos  数据
     */
    protected void dropComplete(int position, int type, DropBo... dropBos) {
        dropListener.dropComplete(position, type, dropBos);
        dismiss();
    }

    public void setPosition(int position) {
        this.position = position;
    }

    protected int getPosition(){
        return position;
    }

    /**
     * 显示
     */
    public void show() {
        if (popupWindow != null &&
                !popupWindow.isShowing())
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT){
                popupWindow.showAsDropDown(anchor, 0, 0, Gravity.CENTER_HORIZONTAL);
            }else {
                popupWindow.showAsDropDown(anchor);
            }
    }

    protected void dismiss() {
        if (popupWindow != null)
            popupWindow.dismiss();
    }

    @Override
    public void onDismiss() {
        localDropListener.dropDismiss();
    }

    public String getTitle() {
        return title;
    }
}
