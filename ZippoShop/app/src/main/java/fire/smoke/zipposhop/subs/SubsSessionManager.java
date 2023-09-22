package fire.smoke.zipposhop.subs;

import android.content.Context;
import android.content.SharedPreferences;

public class SubsSessionManager {
    private static final String SHARED_PREFERENCES_NAME = "";
    private static final String KEY_SAVE_BUY_SUBS = "key_save_buy_subs";
    private static SubsSessionManager sInstance;
    private SharedPreferences sharedPref;

    private SubsSessionManager() {
        // no instance
    }

    public synchronized static SubsSessionManager getInstance() {
        if (sInstance == null) {
            sInstance = new SubsSessionManager();
        }
        return sInstance;
    }

    public String getKeySaveBuyInApp() {
        return sharedPref.getString(KEY_SAVE_BUY_SUBS, "");
    }

    public void setKeySaveBuyInApp(String token) {
        sharedPref.edit().putString(KEY_SAVE_BUY_SUBS, token).apply();
    }

    public void init(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
}