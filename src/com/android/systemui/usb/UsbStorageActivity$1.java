package com.android.systemui.usb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class UsbStorageActivity$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.hardware.usb.action.USB_STATE"))
      UsbStorageActivity.access$000(this.this$0, paramIntent);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbStorageActivity.1
 * JD-Core Version:    0.6.0
 */