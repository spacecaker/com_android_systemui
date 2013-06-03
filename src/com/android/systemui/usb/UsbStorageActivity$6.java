package com.android.systemui.usb;

class UsbStorageActivity$6
  implements Runnable
{
  public void run()
  {
    if (!UsbStorageActivity.access$500(this.this$0))
    {
      this.this$0.removeDialog(1);
      this.this$0.removeDialog(2);
      this.this$0.showDialog(this.val$id);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbStorageActivity.6
 * JD-Core Version:    0.6.0
 */