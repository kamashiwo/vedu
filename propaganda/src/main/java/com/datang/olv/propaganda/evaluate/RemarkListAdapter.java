package com.datang.olv.propaganda.evaluate;

import java.util.List;

import com.datang.olv.propaganda.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class RemarkListAdapter extends BaseAdapter{
	private Context context;
	private List<Remark> list;
	private Resources r;
	private Teacher teacher;
	public RemarkListAdapter(Context context, List<Remark> list,Teacher teacher) {
		super();
		this.context = context;
		this.teacher=teacher;
		this.r=context.getResources();
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return list.get(position).getId();
	}
	private Holder holder;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.remark_list_item, null);
			holder=new Holder();
			holder.listener=new MyListener();
			holder.content=(TextView) convertView.findViewById(R.id.remark_item_content);
			holder.rank1=(Button)convertView.findViewById(R.id.remark_item_rank1);
			holder.rank2=(Button)convertView.findViewById(R.id.remark_item_rank2);
			holder.rank3=(Button)convertView.findViewById(R.id.remark_item_rank3);
			holder.rank4=(Button)convertView.findViewById(R.id.remark_item_rank4);
			holder.rank1.setOnClickListener(holder.listener);
			holder.rank2.setOnClickListener(holder.listener);
			holder.rank3.setOnClickListener(holder.listener);
			holder.rank4.setOnClickListener(holder.listener);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		holder.content.setText(list.get(position).getContent());
		holder.listener.setHolder(holder);
		holder.id=position;
		holder.rank1.setBackgroundColor(r.getColor(R.color.color_green));
		holder.rank1.setText(r.getText(R.string.rank1_unselected));
		holder.rank2.setBackgroundColor(r.getColor(R.color.color_green));
		holder.rank2.setText(r.getText(R.string.rank2_unselected));
		holder.rank3.setBackgroundColor(r.getColor(R.color.color_green));
		holder.rank3.setText(r.getText(R.string.rank3_unselected));
		holder.rank4.setBackgroundColor(r.getColor(R.color.color_green));
		holder.rank4.setText(r.getText(R.string.rank4_unselected));
		switch(list.get(position).getRank()){
		case Remark.RANKNONE:
			break;
		case Remark.RANK1:
			holder.rank1.setBackgroundColor(r.getColor(R.color.color_selected));
			holder.rank1.setText(r.getText(R.string.rank1_selected));
			break;
		case Remark.RANK2:
			holder.rank2.setBackgroundColor(r.getColor(R.color.color_selected));
			holder.rank2.setText(r.getText(R.string.rank2_selected));
			break;
		case Remark.RANK3:
			holder.rank3.setBackgroundColor(r.getColor(R.color.color_selected));
			holder.rank3.setText(r.getText(R.string.rank3_selected));
			break;
		case Remark.RANK4:
			holder.rank4.setBackgroundColor(r.getColor(R.color.color_selected));
			holder.rank4.setText(r.getText(R.string.rank4_selected));
			break;
		}
		return convertView;
	}
	private class Holder{
		private TextView content;
		private Button rank1,rank2,rank3,rank4;
		private MyListener listener;
		private int id;
	}
	private class MyListener implements OnClickListener{
		private Holder holder;
		public void setHolder(Holder holder) {
			this.holder = holder;
		}

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			holder.rank1.setBackgroundColor(r.getColor(R.color.color_green));
			holder.rank1.setText(r.getText(R.string.rank1_unselected));
			holder.rank2.setBackgroundColor(r.getColor(R.color.color_green));
			holder.rank2.setText(r.getText(R.string.rank2_unselected));
			holder.rank3.setBackgroundColor(r.getColor(R.color.color_green));
			holder.rank3.setText(r.getText(R.string.rank3_unselected));
			holder.rank4.setBackgroundColor(r.getColor(R.color.color_green));
			holder.rank4.setText(r.getText(R.string.rank4_unselected));
			switch(v.getId()){
			case R.id.remark_item_rank1:
				((Button)v).setText(r.getText(R.string.rank1_selected));
				list.get(holder.id).setRank(Remark.RANK1);
				break;
			case R.id.remark_item_rank2:
				((Button)v).setText(r.getText(R.string.rank2_selected));
				list.get(holder.id).setRank(Remark.RANK2);
				break;
			case R.id.remark_item_rank3:
				((Button)v).setText(r.getText(R.string.rank3_selected));
				list.get(holder.id).setRank(Remark.RANK3);
				break;
			case R.id.remark_item_rank4:
				((Button)v).setText(r.getText(R.string.rank4_selected));
				list.get(holder.id).setRank(Remark.RANK4);
				break;
			}
			v.setBackgroundColor(r.getColor(R.color.color_selected));
			for(int i=0;i<list.size();i++){
				if(list.get(i).getRank()==Remark.RANKNONE){
					return;
				}
			}
            ConfirmSubmitDialog();
		}
	}
	private void FinishEvaluateDialog(){
		DisplayMetrics metrics=new DisplayMetrics();
		((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		final Dialog dialog=new AlertDialog.Builder(context).create();
		dialog.show();
		dialog.setCancelable(false);
		Window window=dialog.getWindow();
		LayoutParams p=window.getAttributes();
		p.width=(int) (metrics.widthPixels*0.9);
		window.setAttributes(p);
		window.setContentView(R.layout.remark_finish_layout);
		Button btnOK=(Button) window.findViewById(R.id.dialog_ok);
		Button btnClose=(Button)window.findViewById(R.id.dialog_close);
		TextView dollar=(TextView)window.findViewById(R.id.usersdollars);
		dollar.setText("100");
		dialog.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				Activity a=((Activity)context);
				Intent it=a.getIntent();
				it.putExtra("tid", teacher.getId());
				a.setResult(Activity.RESULT_OK, a.getIntent());
				((Activity)context).finish();
			}
		});
		btnOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
		btnClose.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.cancel();
			}
		});
	}

    //确认是否提交评价
    private void ConfirmSubmitDialog(){
        DisplayMetrics metrics=new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        final Dialog dialog=new AlertDialog.Builder(context).create();
        dialog.show();
        dialog.setCancelable(false);
        Window window=dialog.getWindow();
        LayoutParams p=window.getAttributes();
        p.width=(int) (metrics.widthPixels*0.9);
        window.setAttributes(p);
        window.setContentView(R.layout.remark_confirm_layout);
        Button btnOK=(Button) window.findViewById(R.id.dialog_ok);
        Button btnCancel=(Button) window.findViewById(R.id.dialog_cancel);
        Button btnClose=(Button)window.findViewById(R.id.dialog_close);
        dialog.setOnCancelListener(new OnCancelListener() {
            @Override
//            public void onCancel(DialogInterface dialog) {
//                Activity a=((Activity)context);
//                Intent it=a.getIntent();
//                it.putExtra("tid", teacher.getId());
//                a.setResult(Activity.RESULT_OK, a.getIntent());
//                ((Activity)context).finish();
//            }
            public void onCancel(DialogInterface dialog) {
                FinishEvaluateDialog();//结束、感谢、送金币对话框
            }
        });
        btnOK.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
