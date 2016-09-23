package com.fanxl.dropmenu.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.fanxl.dropmenu.R;
import com.fanxl.dropmenu.entity.DropBo;

import java.util.List;

/**
 * Created by fanxl2 on 2016/9/22.
 */

public class DropTextAdapter extends BaseAdapter {

	private List<DropBo> dropBos;
	private LayoutInflater inflater;
	private int selectedPos = -1;

	public DropTextAdapter(Context mContext, List<DropBo> dropBos){
		this.inflater = LayoutInflater.from(mContext);
		this.dropBos=dropBos;
	}


	@Override
	public int getCount() {
		return dropBos.size();
	}

	@Override
	public DropBo getItem(int i) {
		return dropBos.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int i, View view, ViewGroup viewGroup) {

		ViewHolder vh;
		if (view==null){
			view = inflater.inflate(R.layout.item_drop_single, null);
			vh = new ViewHolder(view);
			view.setTag(vh);
		}else {
			vh = (ViewHolder)view.getTag();
		}

		vh.item_drop_tv.setText(dropBos.get(i).getText());
		vh.item_drop_tv.setChecked(selectedPos==i);

		return view;
	}

	public void select(int position) {
		selectedPos = position;
		notifyDataSetChanged();
	}

	public void setData(List<DropBo> datas, int selectedPos){
		this.selectedPos=selectedPos;
		dropBos=datas;
	}


	public class ViewHolder{

		AppCompatCheckedTextView item_drop_tv;

		public ViewHolder(View view){
			item_drop_tv = (AppCompatCheckedTextView)view.findViewById(R.id.item_drop_tv);
		}
	}
}
