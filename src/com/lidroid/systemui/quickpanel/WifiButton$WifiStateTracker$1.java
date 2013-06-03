package com.lidroid.systemui.quickpanel;

import android.net.wifi.WifiManager;
import android.os.AsyncTask;

class WifiButton$WifiStateTracker$1 extends AsyncTask<Void, Void, Void>
{
  protected Void doInBackground(Void[] paramArrayOfVoid)
  {
    this.val$wifiManager.setWifiEnabledDialog(this.val$desiredState);
    return null;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.WifiButton.WifiStateTracker.1
 * JD-Core Version:    0.6.0
 */