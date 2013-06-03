package com.android.systemui.statusbar.quickpanel;

import android.telephony.PhoneStateListener;
import android.util.Log;

class DataConnectionSettingButtonXEC$2 extends PhoneStateListener
{
  public void onDataConnectionStateChanged(int paramInt)
  {
    Log.i("DataConnectionSettingButtonXEC", "onDataConnectionStateChanged -> current data state : " + paramInt);
    switch (paramInt)
    {
    case 1:
    default:
    case 0:
    case 2:
    }
    while (true)
    {
      return;
      Log.i("DataConnectionSettingButtonXEC", "onDataConnectionStateChanged -> DATA DISCONNECTED !!!");
      this.this$0.setActivateStatus(0);
      this.this$0.updateIcons();
      continue;
      Log.i("DataConnectionSettingButtonXEC", "onDataConnectionStateChanged -> DATA CONNECTED !!!");
      this.this$0.setActivateStatus(1);
      this.this$0.updateIcons();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.DataConnectionSettingButtonXEC.2
 * JD-Core Version:    0.6.0
 */