
package com.qifa.batteryeffect;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ImageView.ScaleType;

public class MyProcessBar extends RelativeLayout {
    private final static String TAG = "MyProcessBar";
    private int mMax = 100;
    private int mProcess = 0;
    private ImageView mImageView = null;
    private LayoutParams mParams;
    private Handler mHandler;
    
    private Thread mThread = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(20);
                mProcess++;
                setProgress(mProcess);
                reflashPorcess(mProcess);// 界面的修改，交由线程来处理
                Log.d(TAG, "mThread mProcess==" + mProcess);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });

    public MyProcessBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.d(TAG, "context1");
        init();
    }

    public MyProcessBar(Context context) {
        super(context);
        init();
        Log.d(TAG, "context2");
    }

    private void init() {
        mHandler = new Handler(getContext().getMainLooper());
        mParams = new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        Log.d(TAG, "init");
        mThread.start();
    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getMax() {
        return mMax;
    }
    public void destoryThread() {
        mHandler.removeCallbacks(mThread);
    }
    public void setProgress(int process) {
        if (process <= mMax) {
            Log.d(TAG, "setProgress");
            mProcess = process;
            mHandler.post(mThread);
        }
    }

    public int getProgress() {
        return mProcess;
    }

    private int getCountLength() {
        // 16是一个增益参数，可控制调节高度
        int length = (getHeight() - 16) * mProcess / mMax;
        Log.d(TAG, "lenth==" + length);
        return length;
    }

    private void reflashPorcess(int process) {

        if (mImageView != null) {
            removeView(mImageView);
        }
        Log.d(TAG, "reflashPorcess==" + process);
        mImageView = null;
        mImageView = new ImageView(getContext());
        mImageView.setAdjustViewBounds(true);
        mImageView.setScaleType(ScaleType.FIT_XY);
        mImageView.setImageResource(R.drawable.widget_battery_bg1);
       mParams.height = getCountLength();
        addView(mImageView, mParams);
    }
}
