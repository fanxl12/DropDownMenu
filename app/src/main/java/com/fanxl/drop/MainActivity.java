package com.fanxl.drop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fanxl.dropmenu.DropCompleteListener;
import com.fanxl.dropmenu.DropDownView;
import com.fanxl.dropmenu.drop.SingleDrop;
import com.fanxl.dropmenu.entity.DropBo;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private DropDownView main_dv;

	private SingleDrop one, two, three, four;

	private DropCompleteListener dropCompleteListener = new DropCompleteListener() {

		@Override
		public void complete(int position, int type, DropBo... dropBos) {
			main_dv.setTab(position, dropBos[0].getText());
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		main_dv = (DropDownView) findViewById(R.id.main_dv);

		one = new SingleDrop(main_dv, this, null, "语言");
		two = new SingleDrop(main_dv, this, null, "价格");
		three = new SingleDrop(main_dv, this, null, "类型");
		four = new SingleDrop(main_dv, this, null, "更多");

		main_dv.addDrops(one, two, three, four);
		main_dv.setDropCompleteListener(dropCompleteListener);

		List<DropBo> oneList = new ArrayList<>();
		for (int i=0; i<10; i++){
			DropBo item = new DropBo(""+i, "选项"+i);
			oneList.add(item);
		}

		one.init(oneList);
		two.init(oneList);
		three.init(oneList);
		four.init(oneList);

	}
}
