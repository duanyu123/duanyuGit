package example.ydlianxi7_27;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by 段昱 on 2017/7/27.
 */

public class Myabdsa extends BaseAdapter {
    private List<Data.DataBean.ComicsBean> list;

    private Context context;

    public Myabdsa(Context context, List<Data.DataBean.ComicsBean> list) {
        this.context = context;
        this.list = list;
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
        viewHolder holder = null;
        if (convertView == null) {
            holder = new viewHolder();
            convertView = View.inflate(context, R.layout.itme, null);
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.textView.setText(list.get(position).getTitle());
        x.image().bind(holder.imge, list.get(position).getCover_image_url());
        return convertView;
    }

    class viewHolder {
        @ViewInject(R.id.texty)
        TextView textView;
        @ViewInject(R.id.imge)
        ImageView imge;
    }
}
