package com.lab3224.changgg.bluetooth20;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by Changgg on 2015/5/14.
 */
public class BtConnect extends Thread{
    private static final String TAG = "BtConnect";
    private static final UUID SerialPortServiceClass_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothSocket _BCSocket;
    private BluetoothDevice _BCDevice;

    public BtConnect(BluetoothDevice device){
        Log.i(TAG, "BtConnect Start");
        // Use a temporary object that is later assigned to _BCSocket, because _BCSocket is final
        BluetoothSocket tmp = null;
        this._BCDevice = device;

        // Get a BluetoothSocket to connect with the given BluetoothDevice
        try {
            Log.i(TAG, "CreateRfcommSocket");
            // MY_UUID is the app's UUID string, also used by the server code
            tmp = device.createInsecureRfcommSocketToServiceRecord(SerialPortServiceClass_UUID);
        } catch (Exception e) {	}
        this._BCSocket = tmp;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();

        try {
            _BCSocket.connect();
            Log.i(TAG, "Bluetooth Connect Success & Starting BtSocketStream");
            BtSocketStream SS = new BtSocketStream(_BCSocket);
            SS.start();
        } catch (IOException connectException) {
            // TODO: handle exception
            try {
                _BCSocket.close();
                Log.i(TAG, "Bluetooth Connect Fail");
            } catch (IOException closeException) {
                // TODO Auto-generated catch block
                closeException.printStackTrace();
            }
        }

        if (!_BCSocket.isConnected()) run();
        else return;

    }

}
