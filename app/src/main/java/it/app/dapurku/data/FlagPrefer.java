package it.app.dapurku.data;

import android.content.Context;
import android.content.SharedPreferences;

import it.app.dapurku.config.ResepConst;

/**
 * Created by Islam on 12/05/2016.
 */
public class FlagPrefer {

    public static final String FLAG_PREF_KEY = "flag_pref_key";
    public static final String SERVER_IP_KEY = "server_ip_key";

    public static String SERVER_IP = ResepConst.BASE_URL;

    public static String getFlag(Context context){
        SharedPreferences sharedPrefernces = context.getSharedPreferences(FLAG_PREF_KEY,Context.MODE_PRIVATE);
        return sharedPrefernces.getString(SERVER_IP_KEY,SERVER_IP);
    }

    public static void setIpServer(Context context,String ip){
        SERVER_IP = "http://"+ip;
        SharedPreferences shared = context.getSharedPreferences(FLAG_PREF_KEY,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString(SERVER_IP_KEY,SERVER_IP);
        editor.commit();
    }
}
