package example.yuekaolianxi8_24;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by 段昱 on 2017/8/24.
 */

public class MyAdapter extends BaseAdapter {
    private List<Data.DataBean.ComicsBean>list;
    private Context context;

    public MyAdapter(List<Data.DataBean.ComicsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=null;
        if (convertView==null){
            holder=new ViewHolder();
            convertView=View.inflate(context,R.layout.itme,null);
            x.view().inject(holder,convertView);
             convertView.setTag(holder);
        }else {
            holder=(ViewHolder)convertView.getTag();
        }
        holder.textView.setText(list.get(position).getTitle());
        x.image().bind(holder.imageView,list.get(position).getCover_image_url());
        return convertView;
    }
    @ContentView(R.layout.itme)
    class  ViewHolder{
        @ViewInject(R.id.imge)
        ImageView imageView;
        @ViewInject(R.id.tet)
        TextView textView;
    }
}
