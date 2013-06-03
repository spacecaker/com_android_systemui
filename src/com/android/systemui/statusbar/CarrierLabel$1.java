package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class CarrierLabel$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.provider.Telephony.SPN_STRINGS_UPDATED".equals(paramIntent.getAction()))
      this.this$0.updateNetworkName(paramIntent.getBooleanExtra("showSpn", false), paramIntent.getStringExtra("spn"), paramIntent.getBooleanExtra("showPlmn", false), paramIntent.getStringExtra("plmn"));
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.CarrierLabel.1
 * JD-Core Version:    0.6.0
 */