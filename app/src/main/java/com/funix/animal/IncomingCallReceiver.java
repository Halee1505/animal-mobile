package com.funix.animal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.funix.animal.database.AnimalDao;
import com.funix.animal.model.Animal;
import com.funix.animal.util.SharedPreferencesController;
import com.funix.animal.util.ToastUtil;

public class IncomingCallReceiver extends BroadcastReceiver {
    private static String lastIncomingNumber = null;
    private AnimalDao animalDao;
    private SharedPreferencesController sharedPreferencesController;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (animalDao == null) {
            animalDao = new AnimalDao(context); // Initialize AnimalDao here
        }
        sharedPreferencesController = new SharedPreferencesController(context);
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            if (incomingNumber != null) {
                lastIncomingNumber = incomingNumber;
                showToast(context, incomingNumber);

            } else if (lastIncomingNumber != null) {
                showToast(context, lastIncomingNumber);

            }
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
            lastIncomingNumber = null;
        }
    }
    public void showToast (Context context, String phone){
        String key = sharedPreferencesController.getKeyFromStringValue(phone);
        if(key == null) return;
        String imagePath = key.split("_phone")[0];
        ToastUtil.showCustomToast(context, imagePath);

    }
}
