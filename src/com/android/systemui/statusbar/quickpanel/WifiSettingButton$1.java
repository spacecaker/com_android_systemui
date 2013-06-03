package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class WifiSettingButton$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("wifi_state", 4);
    Log.e("WifiSettingButton", "onReceive()-S:" + i);
    WifiSettingButton.access$000(this.this$0, i);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.WifiSettingButton.1
 * JD-Core Version:    0.6.0
 */