package com.fanxl.dropmenu;


import com.fanxl.dropmenu.entity.DropBo;

/**
 * 下拉监听
 * Created by fanxl12 on 16/1/27.
 */
public interface DropListener {

    /**
     * @param type     下拉菜单类型
     * @param dropBos  数据
     */
    void dropComplete(int position, int type, DropBo... dropBos);

    void dropDismiss();
}
