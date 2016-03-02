package com.example.siddiquetomal.locator;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.NumberPicker;
import android.widget.Toast;

import java.text.Format;

/**
 * Created by Siddique Tomal on 3/2/2016.
 */
public class SMSReader extends BroadcastReceiver {

    private CustomLocation EKUSHEY_LOCATION;
    private CustomLocation MOSQUE_LOCATION;

    final static private String TAG = SMSReader.class.getSimpleName();

    private SmsManager smsManager;

    public SMSReader() {}

    @Override
    public void onReceive(Context context, Intent intent){

        CustomLocation currentLocation = new CustomLocation(LocatorMainActivity._CURRENT_LOCATION);

        String sendCurrentDistance = "";

        EKUSHEY_LOCATION = new CustomLocation(22.898663, 89.501136);
        MOSQUE_LOCATION = new CustomLocation(22.899320, 89.500305);

        smsManager = SmsManager.getDefault();
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        String format = intent.getStringExtra("format");

        SmsMessage sms = messages[0];

        Log.v(TAG, "handleSmsReceived" + (sms.isReplace() ? "(replace)" : "") +  "messageUri: " + ", address: " + sms.getOriginatingAddress() + ", body: " + sms.getMessageBody());

        String msg = sms.getMessageBody();


        if (msg.contains("####")){
            if (msg.contains("mosque") && !msg.contains("ekushey_hall")){
                double distance = GPSCalculator.GET_DISTANCE_KM(currentLocation, MOSQUE_LOCATION);
                sendCurrentDistance = "Distance: " + String.valueOf(distance) + " km from Central Mosque\n Current Location: " + LocatorMainActivity.CURRENT_LOCATION;
                smsManager.sendTextMessage(sms.getOriginatingAddress(), null, sendCurrentDistance, null, null);
            }

            else if (!msg.contains("mosque") && msg.contains("ekushey_hall")){
                double distance = GPSCalculator.GET_DISTANCE_KM(currentLocation, EKUSHEY_LOCATION);

                sendCurrentDistance = "Distance: " + String.valueOf(distance) + " km from Ekushey Hall\n Current Location: " + LocatorMainActivity.CURRENT_LOCATION;

                smsManager.sendTextMessage(sms.getOriginatingAddress(), null, sendCurrentDistance, null, null);
            }
        }

        Toast.makeText(context ,msg, Toast.LENGTH_LONG).show();

    }


}
