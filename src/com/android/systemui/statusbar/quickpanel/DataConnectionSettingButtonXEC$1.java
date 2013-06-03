package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class DataConnectionSettingButtonXEC$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.v("DataConnectionSettingButtonXEC", "onReceive()" + paramIntent.getAction() + " boo " + paramIntent.getBooleanExtra("state", false));
    int j;
    boolean bool1;
    if (paramIntent.getAction().equals("android.intent.action.NETWORK_MODE"))
    {
      boolean bool2 = paramIntent.getBooleanExtra("state", false);
      if (bool2 == true)
      {
        j = 1;
        Log.e("DataConnectionSettingButtonXEC", "OnReceive - Network mode changed, state - " + j + " dcState - " + bool2);
      }
    }
    else if (paramIntent.getAction().equals("android.intent.action.NETWORK_MODE_INITIATE_CHANGE"))
    {
      bool1 = paramIntent.getBooleanExtra("state", false);
      if (bool1 != true)
        break label182;
    }
    label182: for (int i = 1; ; i = 0)
    {
      Log.e("DataConnectionSettingButtonXEC", "OnReceive - Network init mode changed, state - " + i + " dcState - " + bool1);
      return;
      j = 0;
      break;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.DataConnectionSettingButtonXEC.1
 * JD-Core Version:    0.6.0
 */