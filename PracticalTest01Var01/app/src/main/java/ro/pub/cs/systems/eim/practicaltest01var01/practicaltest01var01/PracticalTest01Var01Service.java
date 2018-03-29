package ro.pub.cs.systems.eim.practicaltest01var01.practicaltest01var01;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class PracticalTest01Var01Service extends Service {
    private ProcessingThread processingThread = null;
    public PracticalTest01Var01Service() {}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String text = intent.getStringExtra("prevText");
        processingThread = new ProcessingThread(this, text);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
