package view.ui;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import taherzadeh.com.mvvmsample.R;


/**
 * Created by hadis.t on 4/29/2018.
 */

public class MainActivity extends LifecycleActivity  {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            FragmentList fragment = new FragmentList();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, fragment, FragmentList.TAG).commit();
        }
    }


}
