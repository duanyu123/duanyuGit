package example.ykdemon9_12;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 段昱 on 2017/9/12.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private  List<Data.DataBean>list;

    public RecyclerViewAdapter(Context context, List<Data.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(layoutInflater.inflate(R.layout.itme,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImg()).into(holder.imge);
        holder.name.setText(list.get(position).getTitle());
        holder.work.setText(list.get(position).getContent());
        holder.age.setText(list.get(position).getReplyTimes());
        holder.title.setText(list.get(position).getIntroduction());
    }

    @Override
    public int getItemCount() {
        return  list == null ? 0 : list.size();
    }
    class  ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imge;
        private final TextView name;
        private final TextView age;
        private final TextView work;
        private final TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            imge = itemView.findViewById(R.id.imge);
            name = itemView.findViewById(R.id.name);
            age = itemView.findViewById(R.id.age);
            work = itemView.findViewById(R.id.work);
            title = itemView.findViewById(R.id.title);

        }
    }
}
