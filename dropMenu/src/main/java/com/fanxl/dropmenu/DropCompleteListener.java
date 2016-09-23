package com.fanxl.dropmenu;


import com.fanxl.dropmenu.entity.DropBo;

/**
 * 下拉菜单完成
 * Created by fanxl2 on 16/1/27.
 */
public interface DropCompleteListener {

    void complete(int position, int type, DropBo... dropBos);

}
