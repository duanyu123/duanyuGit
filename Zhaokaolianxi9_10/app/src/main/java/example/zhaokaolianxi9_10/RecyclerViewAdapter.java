package example.zhaokaolianxi9_10;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 段昱 on 2017/9/10.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ListViewHolder> {
private List<Dtata.美女Bean>list;
private Context context;

    public RecyclerViewAdapter(Context context, List<Dtata.美女Bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewAdapter.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.itme,null);
        ListViewHolder listViewHolder=new ListViewHolder(view);
        return listViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ListViewHolder holder, int position) {
   holder.text1.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImg()).into(holder.imge1);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView imge1;
        private TextView text1;
        public ListViewHolder(View itemView) {
            super(itemView);
            imge1=itemView.findViewById(R.id.imge1);
            text1=itemView.findViewById(R.id.text1);

        }
    }
}