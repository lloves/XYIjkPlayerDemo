package com.fang.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.AdvertiseCallback;
import android.bluetooth.le.AdvertiseData;
import android.bluetooth.le.AdvertiseSettings;
import android.bluetooth.le.BluetoothLeAdvertiser;
import android.net.wifi.WifiManager;
import android.util.Log;

import java.nio.ByteBuffer;

public class BleAdvertiser {

    private static String TAG = "BleAdvertiser";
    private WifiManager mWifiManager;
    private BluetoothLeAdvertiser advertiser = null;

    public BleAdvertiser(WifiManager wm) {
        mWifiManager = wm;
    }

    public void start() {
        int ip = mWifiManager.getConnectionInfo().getIpAddress();
        byte[] bytes = ByteBuffer.allocate(4).putInt(ip).array();
        advertiser = BluetoothAdapter.getDefaultAdapter().getBluetoothLeAdvertiser();
        AdvertiseSettings.Builder builder = new AdvertiseSettings.Builder();
        builder.setAdvertiseMode(AdvertiseSettings.ADVERTISE_MODE_LOW_LATENCY);
        builder.setConnectable(true);
        builder.setTimeout(0);
        builder.setTxPowerLevel(AdvertiseSettings.ADVERTISE_TX_POWER_HIGH);
        AdvertiseSettings advertiseSettings = builder.build();

        AdvertiseData.Builder builder2 = new AdvertiseData.Builder();

        byte[] bytes1 = new byte[8];
        bytes1[0] = 0x09;
        bytes1[1] = 0x06;
        bytes1[2] = 0x03;
        bytes1[3] = 0x05;
        bytes1[4] = bytes[3];
        bytes1[5] = bytes[2];
        bytes1[6] = bytes[1];
        bytes1[7] = bytes[0];

        builder2.addManufacturerData(0x004c, bytes1);
        AdvertiseData advertiseData2 = builder2.build();

        if(advertiser != null) {
            advertiser.startAdvertising(advertiseSettings, advertiseData2, new AdvertiseCallback() {
                @Override
                public void onStartSuccess(AdvertiseSettings settingsInEffect) {
                    super.onStartSuccess(settingsInEffect);
                    Log.i(TAG, "Began advertising");
                }

                @Override
                public void onStartFailure(int errorCode) {
                    super.onStartFailure(errorCode);
                    Log.i(TAG, "Error advertising: " + errorCode);
                }
            });
        }


    }

}


