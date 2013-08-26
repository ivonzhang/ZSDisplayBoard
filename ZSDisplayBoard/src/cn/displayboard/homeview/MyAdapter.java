package cn.displayboard.homeview;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import cn.displayboard.net.constant.NetConstant;
import cn.ivonzhang.views.R;

public class MyAdapter extends BaseAdapter {

	private Context context;
	private List<ItemInfo> list;
	private LayoutInflater inflater;
	private ItemInfo itemInfo;

	public final class ViewHolder {
		public MyImageView imgLeft, imgRight;
	}

	public MyAdapter(Context context, List<ItemInfo> list) {
		this.context = context;
		this.list = list;
		this.inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return getItem(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		itemInfo = list.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.list, null);
			holder = new ViewHolder();
			holder.imgLeft = (MyImageView) convertView
					.findViewById(R.id.myimgleft);
			holder.imgRight = (MyImageView) convertView
					.findViewById(R.id.myimgright);
			/*
			 * holder.imgLeft.setBackgroundResource(itemInfo.getimgLeft());
			 * holder.imgRight.setBackgroundResource(itemInfo.getimgRight());
			 * holder.imgLeft.setOnClickListener(new ImgListener(holder));
			 * holder.imgRight.setOnClickListener(new ImgListener(holder));
			 */
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// holder.imgLeft = (ImageView)convertView.findViewById(R.id.myimgleft);
		// holder.imgRight =
		// (ImageView)convertView.findViewById(R.id.myimgright);
		holder.imgLeft.setText((String) list.get(position).getimgLeftMap()
				.get("hotdisplayboardDiscription"));// 可在这里添加从网路获取图片的请求
		holder.imgRight.setText((String) list.get(position).getimgRightMap()
				.get("hotdisplayboardDiscription"));

		holder.imgLeft.setImageUrl(
				NetConstant.IP
						+ "/HotDisplayBoardUploadImages/"
						+ (String) list.get(position).getimgLeftMap()
								.get("hotdisplayboardUrl"), holder.imgLeft);
		holder.imgRight.setImageUrl(
				NetConstant.IP
						+ "/HotDisplayBoardUploadImages/"
						+ (String) list.get(position).getimgRightMap()
								.get("hotdisplayboardUrl"), holder.imgRight);

		// 图片单击事件
		// holder.imgLeft.setOnClickListener(new ImgListener(holder));
		// holder.imgRight.setOnClickListener(new ImgListener(holder));
		// }
		return convertView;
	}

	// 添加图片的单击事件 跳转到另一个activity显示
	public class ImgListener implements OnClickListener {

		private ViewHolder holder;

		public ImgListener(ViewHolder holder) {
			this.holder = holder;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			intent.setClass(context, DetailsActivity.class);/*
															 * if(v.getId()==R.id
															 * .myimgleft){
															 * intent
															 * .putExtra("imgnumber"
															 * ,
															 * itemInfo.getimgLeft
															 * ()); }else{
															 * intent
															 * .putExtra("imgnumber"
															 * ,
															 * itemInfo.getimgRight
															 * ()); }
															 */

			context.startActivity(intent);

		}
	}

}
