package example.ykdemon9_20.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import example.ykdemon9_20.R;

/**
 * Created by 段昱 on 2017/9/20.
 */

public class Fragment02 extends Fragment {


    private View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frgmt02, container, false);


        return v;
    }


}
