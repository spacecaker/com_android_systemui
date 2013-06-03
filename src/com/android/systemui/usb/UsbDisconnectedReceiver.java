package com.android.systemui.usb;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;

class UsbDisconnectedReceiver extends BroadcastReceiver
{
  private UsbAccessory mAccessory;
  private final Activity mActivity;

  public UsbDisconnectedReceiver(Activity paramActivity, UsbAccessory paramUsbAccessory)
  {
    this.mActivity = paramActivity;
    this.mAccessory = paramUsbAccessory;
    paramActivity.registerReceiver(this, new IntentFilter("android.hardware.usb.action.USB_ACCESSORY_DETACHED"));
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.hardware.usb.action.USB_ACCESSORY_DETACHED".equals(paramIntent.getAction()))
    {
      UsbAccessory localUsbAccessory = (UsbAccessory)paramIntent.getParcelableExtra("accessory");
      if ((localUsbAccessory != null) && (localUsbAccessory.equals(this.mAccessory)))
        this.mActivity.finish();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbDisconnectedReceiver
 * JD-Core Version:    0.6.0
 */