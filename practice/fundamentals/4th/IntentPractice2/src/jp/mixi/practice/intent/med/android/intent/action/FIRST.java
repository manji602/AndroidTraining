package jp.mixi.practice.intent.med.android.intent.action;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class FIRST extends BroadcastReceiver{
    public void onReceive(Context context, Intent intent){
        Log.v("FIRST", "This is first from broadcast receiver.");
    }
}
