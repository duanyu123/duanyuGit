package example.ykdemon9_20.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import example.ykdemon9_20.R;
import example.ykdemon9_20.bean.Data;

/**
 * Created by 段昱 on 2017/9/20.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private Data data;

    public MyAdapter(Context context, Data data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.itme, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(data.zqf.get(position).Moment.pictureList.get(0)).into(holder.touxiang);
        holder.name.setText(data.zqf.get(position).User.name);
    }

    @Override
    public int getItemCount() {
        return data.zqf.size();
    }

    @Override
    public void onClick(View view) {
        if (mMyItemclickListener != null) {
            mMyItemclickListener.itemclick(view, (Integer) view.getTag());

        }
    }

    public interface MyItemclickListener {
        void itemclick(View view, int position);
    }

    public MyItemclickListener mMyItemclickListener;

    public void setmMyItemclickListener(MyItemclickListener mMyItemclickListener) {
        this.mMyItemclickListener = mMyItemclickListener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView touxiang;
        ImageView image1;
        ImageView image2;
        ImageView image3;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            touxiang = itemView.findViewById(R.id.touxiang);
            image1 = itemView.findViewById(R.id.image1);
            image2 = itemView.findViewById(R.id.image2);
            image3 = itemView.findViewById(R.id.image3);
            name = itemView.findViewById(R.id.name);
        }
    }
}
