package com.android.systemui.usb;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.usb.IUsbManager;
import android.hardware.usb.IUsbManager.Stub;
import android.hardware.usb.UsbAccessory;
import android.os.Bundle;
import android.os.ServiceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController.AlertParams;

public class UsbConfirmActivity extends AlertActivity
  implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener
{
  private UsbAccessory mAccessory;
  private CheckBox mAlwaysUse;
  private TextView mClearDefaultHint;
  private UsbDisconnectedReceiver mDisconnectedReceiver;
  private ResolveInfo mResolveInfo;

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    if (this.mClearDefaultHint == null);
    while (true)
    {
      return;
      if (paramBoolean)
      {
        this.mClearDefaultHint.setVisibility(0);
        continue;
      }
      this.mClearDefaultHint.setVisibility(8);
    }
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (paramInt == -1);
    try
    {
      IUsbManager localIUsbManager = IUsbManager.Stub.asInterface(ServiceManager.getService("usb"));
      int i = this.mResolveInfo.activityInfo.applicationInfo.uid;
      boolean bool = this.mAlwaysUse.isChecked();
      Intent localIntent = new Intent("android.hardware.usb.action.USB_ACCESSORY_ATTACHED");
      localIntent.putExtra("accessory", this.mAccessory);
      localIntent.addFlags(268435456);
      localIntent.setComponent(new ComponentName(this.mResolveInfo.activityInfo.packageName, this.mResolveInfo.activityInfo.name));
      localIUsbManager.grantAccessoryPermission(this.mAccessory, i);
      if (bool)
        localIUsbManager.setAccessoryPackage(this.mAccessory, this.mResolveInfo.activityInfo.packageName);
      while (true)
      {
        startActivity(localIntent);
        finish();
        return;
        localIUsbManager.setAccessoryPackage(this.mAccessory, null);
      }
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("UsbConfirmActivity", "Unable to start activity", localException);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    this.mAccessory = ((UsbAccessory)localIntent.getParcelableExtra("accessory"));
    this.mDisconnectedReceiver = new UsbDisconnectedReceiver(this, this.mAccessory);
    this.mResolveInfo = ((ResolveInfo)localIntent.getParcelableExtra("rinfo"));
    PackageManager localPackageManager = getPackageManager();
    String str = this.mResolveInfo.loadLabel(localPackageManager).toString();
    AlertController.AlertParams localAlertParams = this.mAlertParams;
    localAlertParams.mIcon = this.mResolveInfo.loadIcon(localPackageManager);
    localAlertParams.mTitle = str;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = str;
    localAlertParams.mMessage = getString(2131165195, arrayOfObject);
    localAlertParams.mPositiveButtonText = getString(17039370);
    localAlertParams.mNegativeButtonText = getString(17039360);
    localAlertParams.mPositiveButtonListener = this;
    localAlertParams.mNegativeButtonListener = this;
    localAlertParams.mView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(17367064, null);
    this.mAlwaysUse = ((CheckBox)localAlertParams.mView.findViewById(16908700));
    this.mAlwaysUse.setText(2131165199);
    this.mAlwaysUse.setOnCheckedChangeListener(this);
    this.mClearDefaultHint = ((TextView)localAlertParams.mView.findViewById(16908701));
    this.mClearDefaultHint.setVisibility(8);
    setupAlert();
  }

  protected void onDestroy()
  {
    if (this.mDisconnectedReceiver != null)
      unregisterReceiver(this.mDisconnectedReceiver);
    super.onDestroy();
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbConfirmActivity
 * JD-Core Version:    0.6.0
 */