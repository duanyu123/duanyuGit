package android.test.com.recyclerview1;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

/**
 * 实现RecyclerView的三种样式
 * ListView的横纵箱滑动
 * GridView的横纵向滑动
 * 瀑布流的横纵向滑动
 * LinearLayoutManager 现行管理器，支持横向、纵向。
   GridLayoutManager 网格布局管理器
   StaggeredGridLayoutManager 瀑布就式布局管理器
 */
public class MainActivity extends Activity {
    //
    private RecyclerView mRecyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//      初始化RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
//      RecyclerView设置展示的的样式（listView样子，gridView样子，瀑布流样子）
//        listView纵向滑动样子
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

//        listView横向滑动样子
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(linearLayoutManager);

//        GridView纵向滑动有3列
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(gridLayoutManager);

//        GridView横向滑动有3行
//        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,3);
//        gridLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(gridLayoutManager);

//       瀑布流效果纵向滑动有3列
//        StaggeredGridLayoutManager staggeredGridLayoutManager= new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
//        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

//      瀑布流效果横向滑动有3行
//        StaggeredGridLayoutManager staggeredGridLayoutManager= new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.HORIZONTAL);
//        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);

//      获取数据，向适配器传数据，绑定适配器
        ArrayList<String> datas = initData();
        HomeAdapter honmeAdapter = new HomeAdapter(MainActivity.this,datas);
        mRecyclerView.setAdapter(honmeAdapter);

    }

    /**
     * 编写一套假数据
     */
    protected ArrayList<String> initData() {
        ArrayList<String> mDatas = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            mDatas.add("我是条目" + i);
        }
        return  mDatas;
    }

}
