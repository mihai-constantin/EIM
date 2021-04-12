package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import ro.pub.cs.systems.eim.lab05.startedservice.general.Constants;

public class ProcessingThread  extends Thread {

    private Context context;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        if (messageType == Constants.MESSAGE_STRING) {
            intent.setAction(Constants.ACTION_STRING);
            intent.putExtra(Constants.DATA, Constants.STRING_DATA);
        } else if (messageType == Constants.MESSAGE_INTEGER) {
            intent.setAction(Constants.ACTION_INTEGER);
            intent.putExtra(Constants.DATA, Constants.INTEGER_DATA);
        } else if (messageType == Constants.MESSAGE_ARRAY_LIST) {
            intent.setAction(Constants.ACTION_ARRAY_LIST);
            intent.putExtra(Constants.DATA, Constants.ARRAY_LIST_DATA);
        }
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(Constants.SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    @Override
    public void run() {
        Log.d(Constants.TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while(true) {
            sendMessage(Constants.MESSAGE_STRING);
            sleep();
            sendMessage(Constants.MESSAGE_INTEGER);
            sleep();
            sendMessage(Constants.MESSAGE_ARRAY_LIST);
            sleep();
        }
    }
}