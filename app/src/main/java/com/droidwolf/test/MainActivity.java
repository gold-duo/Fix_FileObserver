package com.droidwolf.test;
import android.app.Activity;
import android.os.*;
import android.util.Log;
import android.view.View;

import com.droidwolf.fix.FileObserver;

public class MainActivity extends Activity implements View.OnClickListener{
    private FileObserver mFileObserver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.start).setOnClickListener(this);
        findViewById(R.id.stop).setOnClickListener(this);
        mFileObserver= new MyFileObserver("/proc/"+ android.os.Process.myPid(),FileObserver.ALL_EVENTS);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.start){
            mFileObserver.startWatching();
        }else {
            mFileObserver.stopWatching();
        }
    }

    private class MyFileObserver extends FileObserver{
        public MyFileObserver(String path,int mask) {
            super(path,mask);
        }

        @Override
        public void onEvent(int event, String path) {
            Log.d(MainActivity.class.getSimpleName(),String.format("onEvent--event=%d, path=%s", event, path));
        }
    }
}
