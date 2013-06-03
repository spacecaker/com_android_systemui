package com.lidroid.systemui.quickpanel;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.util.Log;

class WifiApButton$WifiApStateTracker$1 extends AsyncTask<Void, Void, Void>
{
  protected Void doInBackground(Void[] paramArrayOfVoid)
  {
    int i = this.val$wifiManager.getWifiState();
    if ((this.val$desiredState) && ((i == 2) || (i == 3)))
      this.val$wifiManager.setWifiEnabled(false);
    WifiConfiguration localWifiConfiguration = this.val$wifiManager.getWifiApConfiguration();
    this.val$wifiManager.setWifiApEnabled(localWifiConfiguration, this.val$desiredState);
    Log.i("WifiAp", "Async Setting: " + this.val$desiredState);
    return null;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.WifiApButton.WifiApStateTracker.1
 * JD-Core Version:    0.6.0
 */