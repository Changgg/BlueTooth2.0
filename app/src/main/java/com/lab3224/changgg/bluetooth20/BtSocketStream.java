package com.lab3224.changgg.bluetooth20;

import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Changgg on 2015/5/14.
 */
public class BtSocketStream extends Thread{
    private static final String TAG = "BtSocketStream";
    private static BluetoothSocket _BluetoothSocket;
    private static InputStream _InputStream;

    public BtSocketStream(BluetoothSocket socket){
        Log.i(TAG, "BtSocketStream Start");
        _BluetoothSocket = socket;
        _InputStream = null;

        InputStream tmpInputStream = null;
        try {
            tmpInputStream = socket.getInputStream();
        } catch (Exception e) {
            // TODO: handle exception
            Log.i(TAG, "temp sockets not created", e);
        }
        _InputStream = tmpInputStream;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();

//		byte[] buffer = new byte[1024];
        String str="";
        while(true){
            try {
                if (_InputStream != null){
//					int ISread = _InputStream.read(buffer);
                    BufferedReader br = new BufferedReader(new InputStreamReader(_InputStream));
                    while( (str = br.readLine()) != null){
						Log.i(TAG, str+"  Length:"+str.length());

                    }
                }
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                try {	_InputStream.close();	} catch (IOException e) {e.printStackTrace();}
            }
        }

    }

}
