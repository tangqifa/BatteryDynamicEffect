package com.qifa.batteryeffect;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener
{
    private MyProcessBar mProcessBar;
    private Button mButton;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mProcessBar = (MyProcessBar)this.findViewById(R.id.myprocessbar);
        
        mButton = (Button)this.findViewById(R.id.btn);
        mButton.setOnClickListener(this);
        
    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        if (v == mButton) {
            mProcessBar.setProgress(50);
        }
        
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mProcessBar.destoryThread();
    }
    
}
