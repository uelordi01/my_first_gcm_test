package gcm.udacity.uelordi.com.mygcmfirstapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import gcm.udacity.uelordi.com.mygcmfirstapplication.gcm.RegistrationIntentService;


public class MainActivity extends AppCompatActivity {
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private static final String TAG = "MainActivity";
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (checkGooglePlayServices()) {
            //Log.e(TAG,"Google play services not installed");
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
            boolean sentToken = sp.getBoolean(SENT_TOKEN_TO_SERVER,false);
            if (!sentToken) {
                Intent intent = new Intent(this, RegistrationIntentService.class);
                startService(intent);
            }
        }
    }
    public boolean checkGooglePlayServices() {
        GoogleApiAvailability gaa = GoogleApiAvailability.getInstance();
        int result = gaa.isGooglePlayServicesAvailable(this);
        if (result != ConnectionResult.SUCCESS) {
            if (gaa.isUserResolvableError(result)) {
                gaa.getErrorDialog(this,result,PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Log.e(TAG,"Google play services not installed");
                finish();
            }
            return false;
        }
        return true;
    }
}
