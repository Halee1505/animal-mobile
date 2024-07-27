package com.funix.animal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.funix.animal.database.AnimalDao;
import com.funix.animal.model.Animal;
import com.funix.animal.util.ToastUtil;

public class IncomingCallReceiver extends BroadcastReceiver {
    private static String lastIncomingNumber = null;
    private AnimalDao animalDao;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (animalDao == null) {
            animalDao = new AnimalDao(context); // Initialize AnimalDao here
        }

        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);

        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            if (incomingNumber != null) {
                lastIncomingNumber = incomingNumber;

                Animal existedAnimal = animalDao.getAnimalByPhone(incomingNumber);
                if (existedAnimal != null) {
                    ToastUtil.showCustomToast(context, existedAnimal.getPhoto());
                }
            } else if (lastIncomingNumber != null) {

                Animal existedAnimal = animalDao.getAnimalByPhone(lastIncomingNumber);
                if (existedAnimal != null) {
                    ToastUtil.showCustomToast(context, existedAnimal.getPhoto());
                }
            }
        } else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
            lastIncomingNumber = null;
        }
    }
}
