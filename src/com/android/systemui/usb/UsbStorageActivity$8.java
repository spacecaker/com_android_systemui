package com.android.systemui.usb;

import android.os.storage.StorageManager;

class UsbStorageActivity$8
  implements Runnable
{
  public void run()
  {
    if (this.val$on)
      UsbStorageActivity.access$300(this.this$0).enableUsbMassStorage();
    while (true)
    {
      return;
      UsbStorageActivity.access$300(this.this$0).disableUsbMassStorage();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbStorageActivity.8
 * JD-Core Version:    0.6.0
 */