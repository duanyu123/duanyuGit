package example.zhaokaolianxi9_10;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by 段昱 on 2017/9/10.
 */
public abstract class TypeAbstractViewHolder  extends RecyclerView.ViewHolder{
    public TypeAbstractViewHolder(View itemView) {
        super(itemView);

    }
    public  abstract void bindholder(Dtata bean);
}
