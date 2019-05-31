package com.example.app2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String[] list = intent.getStringArrayExtra("data");
        String server_url = "hutelohu8942138534890peotnusheotunheotnuhsno.lu";
        if(list.length>0){
            for(int i=0;i<list.length;i++){
                System.out.println(list[i]);
                //here one configurate the connection between the user and the remote host
            }
            Toast.makeText(context,"Contacts sended to remote host!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(context,"No contacts retrieved!",Toast.LENGTH_LONG).show();
        }
    }
}