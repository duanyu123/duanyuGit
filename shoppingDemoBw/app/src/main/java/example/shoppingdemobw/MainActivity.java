package example.shoppingdemobw;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_add;
    private Button btn_delete;
    private Button btn_list;
    private Button btn_grid;
    private Button btn_flow;
    private RecyclerView recyclerview;

    private ArrayList<String> datas;
    private MyRecyclevewadapter myRecyclevewadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        datas = new ArrayList<>();
        //准备数据集合
        for (int i = 0; i < 100; i++) {
            datas.add("Content_" + i);
        }
        myRecyclevewadapter = new MyRecyclevewadapter(this, datas);
        recyclerview.setAdapter(myRecyclevewadapter);
        //点击事件
        recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
 myRecyclevewadapter.setmMyItemclickListener(new MyRecyclevewadapter.MyItemclickListener() {
     @Override
     public void itemclick(View view, int position) {
         Toast.makeText(MainActivity.this,datas.get(position),Toast.LENGTH_SHORT).show();
     }
 });
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        //布局管理器所需参数,其参数上下文this.

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //2.设置布局管理器,参数  LinearLayoutManager

        recyclerview.setLayoutManager(linearLayoutManager);

    }

    private void initView() {
        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_list = (Button) findViewById(R.id.btn_list);
        btn_grid = (Button) findViewById(R.id.btn_grid);
        btn_flow = (Button) findViewById(R.id.btn_flow);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);

        //设置点击事件
        btn_add.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_list.setOnClickListener(this);
        btn_grid.setOnClickListener(this);
        btn_flow.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add://D.添加数据
               myRecyclevewadapter.addData(0);
                myRecyclevewadapter.notifyItemChanged(1);
                break;

            case R.id.btn_delete://D.删除数据
                datas.remove(2);
                myRecyclevewadapter.notifyItemRemoved(2);
                break;

            case R.id.btn_list://设置List类型效果
                recyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
                break;

            case R.id.btn_grid://设置Grid类型效果
                recyclerview.setLayoutManager(new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false));
                break;

            case R.id.btn_flow://设置瀑布流类型效果
                recyclerview.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
                break;
        }

    }
}
