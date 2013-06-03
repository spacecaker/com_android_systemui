package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class BluetoothSettingButton$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("android.bluetooth.adapter.extra.STATE", -2147483648);
    Log.e("BluetoothSettingButton", "onReceive()-S:" + i);
    BluetoothSettingButton.access$000(this.this$0, i);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.BluetoothSettingButton.1
 * JD-Core Version:    0.6.0
 */