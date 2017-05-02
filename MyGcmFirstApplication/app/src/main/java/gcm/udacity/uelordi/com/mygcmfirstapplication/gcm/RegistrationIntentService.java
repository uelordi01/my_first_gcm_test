package gcm.udacity.uelordi.com.mygcmfirstapplication.gcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import gcm.udacity.uelordi.com.mygcmfirstapplication.MainActivity;
import gcm.udacity.uelordi.com.mygcmfirstapplication.R;

/**
 * Created by uelordi on 2/05/17.
 */

public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegIntentService";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     *
     */
    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        try {
            synchronized (TAG) {
                InstanceID iid = InstanceID.getInstance(this);
                String token =iid.getToken(getString(R.string.gcm_defaultSenderId),
                                        GoogleCloudMessaging.INSTANCE_ID_SCOPE,null);
                sendRegistrationToServer(token);
                sp.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER,true).apply();
            }

        }
        catch(Exception e) {
            Log.e(TAG,"Failed to complete token refresh",e);
            sp.edit().putBoolean(MainActivity.SENT_TOKEN_TO_SERVER,false).apply();
        }


    }
    private void sendRegistrationToServer(String token) {
              Log.i(TAG, "GCM Registration Token: " + token);
           }
}
