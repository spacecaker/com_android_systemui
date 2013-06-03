package com.android.systemui.usb;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.hardware.usb.IUsbManager;
import android.hardware.usb.IUsbManager.Stub;
import android.hardware.usb.UsbAccessory;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;
import android.widget.CheckBox;
import com.android.internal.app.ResolverActivity;
import java.util.ArrayList;

public class UsbResolverActivity extends ResolverActivity
{
  private UsbAccessory mAccessory;
  private UsbDisconnectedReceiver mDisconnectedReceiver;

  protected void onCreate(Bundle paramBundle)
  {
    Intent localIntent1 = getIntent();
    Parcelable localParcelable = localIntent1.getParcelableExtra("android.intent.extra.INTENT");
    if (!(localParcelable instanceof Intent))
    {
      Log.w("UsbResolverActivity", "Target is not an intent: " + localParcelable);
      finish();
    }
    while (true)
    {
      return;
      Intent localIntent2 = (Intent)localParcelable;
      ArrayList localArrayList = localIntent1.getParcelableArrayListExtra("rlist");
      super.onCreate(paramBundle, localIntent2, getResources().getText(17040278), null, localArrayList, true);
      CheckBox localCheckBox = (CheckBox)findViewById(16908700);
      if (localCheckBox != null)
        localCheckBox.setText(2131165199);
      this.mAccessory = ((UsbAccessory)localIntent2.getParcelableExtra("accessory"));
      if (this.mAccessory == null)
      {
        Log.e("UsbResolverActivity", "accessory is null");
        finish();
        continue;
      }
      this.mDisconnectedReceiver = new UsbDisconnectedReceiver(this, this.mAccessory);
    }
  }

  protected void onDestroy()
  {
    if (this.mDisconnectedReceiver != null)
      unregisterReceiver(this.mDisconnectedReceiver);
    super.onDestroy();
  }

  protected void onIntentSelected(ResolveInfo paramResolveInfo, Intent paramIntent, boolean paramBoolean)
  {
    try
    {
      localIUsbManager = IUsbManager.Stub.asInterface(ServiceManager.getService("usb"));
      int i = paramResolveInfo.activityInfo.applicationInfo.uid;
      localIUsbManager.grantAccessoryPermission(this.mAccessory, i);
      if (paramBoolean)
        localIUsbManager.setAccessoryPackage(this.mAccessory, paramResolveInfo.activityInfo.packageName);
    }
    catch (RemoteException localRemoteException)
    {
      try
      {
        IUsbManager localIUsbManager;
        startActivity(paramIntent);
        while (true)
        {
          return;
          localIUsbManager.setAccessoryPackage(this.mAccessory, null);
          break;
          localRemoteException = localRemoteException;
          Log.e("UsbResolverActivity", "onIntentSelected failed", localRemoteException);
        }
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        while (true)
          Log.e("UsbResolverActivity", "startActivity failed", localActivityNotFoundException);
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbResolverActivity
 * JD-Core Version:    0.6.0
 */