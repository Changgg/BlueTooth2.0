package com.lab3224.changgg.bluetooth20;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.util.Log;

/**
 * Created by Changgg on 2015/5/14.
 */
public class Bluetooth extends Thread{
    private static final String TAG = "BlueTooth";
    public static String strAddress_HC05 = "20:14:11:28:32:35";
    public static String strAddress_HC06 = "30:14:11:27:17:21";
    public static String strAddress_BT_UART = "98:D3:31:B0:FD:03";
    private static BluetoothAdapter _BluetoothAdapter = null;
    private static BluetoothDevice _BluetoothDevice;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        super.run();
        Log.i(TAG, "BlueTooth Start");
        _BluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (_BluetoothAdapter != null){
            if(!_BluetoothAdapter.isEnabled())
                _BluetoothAdapter.enable();
            else{
                _BluetoothDevice = _BluetoothAdapter.getRemoteDevice(strAddress_BT_UART);
                if (_BluetoothDevice != null){
                    Log.i(TAG, "Starting BtConnect");
                    BtConnect BC = new BtConnect(_BluetoothDevice);
                    BC.start();
                }
            }
        }
    }
}
