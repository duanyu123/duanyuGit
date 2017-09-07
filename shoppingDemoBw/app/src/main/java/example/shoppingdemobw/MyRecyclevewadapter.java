package example.shoppingdemobw;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by 段昱 on 2017/9/2.
 */

public class MyRecyclevewadapter extends  RecyclerView.Adapter<MyRecyclevewadapter.ListViewHolder> implements View.OnClickListener{
private List<String>list;
    private Context context;


    public MyRecyclevewadapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyRecyclevewadapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.itme,null);
        ListViewHolder listViewHolder=new ListViewHolder(view);
        view.setOnClickListener(this);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclevewadapter.ListViewHolder holder, int position) {
        String s = list.get(position);
        holder.setData(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View view) {
        if(mMyItemclickListener!=null){
//            mMyItemclickListener.itemclick(view, (Integer) view.getTag());
            mMyItemclickListener.itemclick(view, (Integer) view.getTag());
        }
    }
    //接口回调点击事件
    public interface MyItemclickListener{
        void itemclick(View view,int position);
    }
    public MyItemclickListener mMyItemclickListener;
    public void setmMyItemclickListener(MyItemclickListener mMyItemclickListener) {
        this.mMyItemclickListener = mMyItemclickListener;
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView text;

        public ListViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_icon);

            text = itemView.findViewById(R.id.tv_title);

        }

        public void setData(String s) {

            text.setText(s.toString());
        }
    }
    public  void addData(int position){
        list.add(position,"添加的数据");
        notifyItemInserted(position);
    }

}
