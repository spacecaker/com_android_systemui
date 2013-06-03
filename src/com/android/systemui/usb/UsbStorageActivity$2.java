package com.android.systemui.usb;

import android.os.storage.StorageEventListener;
import android.util.Log;

class UsbStorageActivity$2 extends StorageEventListener
{
  public void onStorageStateChanged(String paramString1, String paramString2, String paramString3)
  {
    boolean bool = paramString3.equals("shared");
    Log.e("UsbStorageActivity", "### onStorageStateChanged :: newState = " + bool);
    if ((paramString3.equals("removed")) || (paramString3.equals("bad_removal")) || (paramString3.equals("nofs")))
    {
      this.this$0.removeDialog(1);
      this.this$0.removeDialog(2);
      this.this$0.finish();
    }
    while (true)
    {
      return;
      UsbStorageActivity.access$100(this.this$0, bool);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbStorageActivity.2
 * JD-Core Version:    0.6.0
 */