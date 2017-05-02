package gcm.udacity.uelordi.com.mygcmfirstapplication.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by uelordi on 2/05/17.
 */

public class MyInstanceIDListenerService extends InstanceIDListenerService {
    private static final String TAG = "MyInstanceIDListenerService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        Intent intent = new Intent(this,RegistrationIntentService.class);
        startService(intent);
    }
}
