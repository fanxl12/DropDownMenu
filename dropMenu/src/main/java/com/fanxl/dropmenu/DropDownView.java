package com.fanxl.dropmenu;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fanxl.dropmenu.entity.DropBo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 下拉view
 * Created by guilin on 16/1/12.
 */
public class DropDownView extends LinearLayout implements View.OnClickListener {

    private List<LinearLayout> menuList;

    private List<AppCompatCheckedTextView> tabsTitles;

    private List<String> defaultTabs;//默认标签文本

    private ArrayList<AbsDrop> absDropList = new ArrayList<>();

    private DropCompleteListener dropCompleteListener;


    private DropListener dropListener = new DropListener() {
        @Override
        public void dropComplete(int position, int type, DropBo... dropBos) {
            if (dropCompleteListener != null) {
                dropCompleteListener.complete(position, type, dropBos);
            }
        }

        @Override
        public void dropDismiss() {
            tabsTitles.get(checkedPosition).setChecked(false);
        }
    };

    private Context mContext;

    public DropDownView(Context context) {
        this(context, null);
    }

    public DropDownView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropDownView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;

        menuList = new ArrayList<>();

        tabsTitles = new ArrayList<>();

        tabIds = new ArrayList<>();
    }

    private List<Integer> tabIds;

    /**
     * 添加下拉菜单
     * @param absDrops AbsDrop列表
     */
    public void addDrops(AbsDrop... absDrops) {

        defaultTabs = new ArrayList<>();

        int position = 0;
        for (AbsDrop absDrop : absDrops){

            absDrop.setPosition(position);

            LinearLayout item_tab = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_drop_tab, null);

            int tabId = generateViewId();
            item_tab.setId(tabId);

            item_tab.setOnClickListener(this);

            tabIds.add(tabId);

            AppCompatCheckedTextView item_text = (AppCompatCheckedTextView) item_tab.findViewById(R.id.item_text);
            item_text.setText(absDrop.getTitle());

            defaultTabs.add(absDrop.getTitle());

            menuList.add(item_tab);
            tabsTitles.add(item_text);

            absDrop.setDropListener(dropListener);

            absDropList.add(absDrop);

            LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;

            addView(item_tab, params);

            position++;
        }
    }

    /**
     * 设置选中状态
     *
     * @param position 0-3
     * @param selected 是否选中
     */
    private void select(int position, boolean selected) {
        tabsTitles.get(position).setChecked(selected);
    }

    private int checkedPosition = -1;

    @Override
    public void onClick(View v) {

        for (int i=0; i<tabIds.size(); i++){
            Integer id = tabIds.get(i);
            if (id==v.getId()){
                checkedPosition = i;
                absDropList.get(i).show();
                break;
            }
        }

        for (int i=0; i<tabsTitles.size(); i++){
            if (i==checkedPosition){
                if(tabsTitles.get(i).isChecked()){
                    tabsTitles.get(i).setChecked(false);
                }else {
                    tabsTitles.get(i).setChecked(true);
                }
            }else {
                tabsTitles.get(i).setChecked(false);
            }
        }
    }

    /**
     * 重置标签到默认
     *
     * @param position 标签位置 0-3
     */
    public void resetTab(int position) {
        setTab(position, defaultTabs.get(position));
        select(position, false);
    }

    /**
     * 设置标签
     *
     * @param position 位置0-3
     * @param tab 文本
     */
    public void setTab(int position, String tab) {
        tabsTitles.get(position).setText(tab);
        select(position, true);
    }

    public void setDropCompleteListener(DropCompleteListener dropCompleteListener) {
        this.dropCompleteListener = dropCompleteListener;
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);


    /**
     * 动态生成View ID
     * API LEVEL 17 以上View.generateViewId()生成
     * API LEVEL 17 以下需要手动生成
     */
    public static int generateViewId() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1) {
            for (; ; ) {
                final int result = sNextGeneratedId.get();
                // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
                int newValue = result + 1;
                if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
                if (sNextGeneratedId.compareAndSet(result, newValue)) {
                    return result;
                }
            }
        } else {
            return View.generateViewId();
        }
    }


}
