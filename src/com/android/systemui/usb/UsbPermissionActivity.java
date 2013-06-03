package com.android.systemui.usb;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.hardware.usb.IUsbManager;
import android.hardware.usb.IUsbManager.Stub;
import android.hardware.usb.UsbAccessory;
import android.os.Bundle;
import android.os.RemoteException;
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

public class UsbPermissionActivity extends AlertActivity
  implements DialogInterface.OnClickListener, CompoundButton.OnCheckedChangeListener
{
  private UsbAccessory mAccessory;
  private CheckBox mAlwaysUse;
  private TextView mClearDefaultHint;
  private UsbDisconnectedReceiver mDisconnectedReceiver;
  private String mPackageName;
  private PendingIntent mPendingIntent;
  private boolean mPermissionGranted;
  private int mUid;

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
    if (paramInt == -1)
      this.mPermissionGranted = true;
    finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    this.mAccessory = ((UsbAccessory)localIntent.getParcelableExtra("accessory"));
    this.mDisconnectedReceiver = new UsbDisconnectedReceiver(this, this.mAccessory);
    this.mPendingIntent = ((PendingIntent)localIntent.getParcelableExtra("android.intent.extra.INTENT"));
    this.mUid = localIntent.getIntExtra("uid", 0);
    this.mPackageName = localIntent.getStringExtra("package");
    PackageManager localPackageManager = getPackageManager();
    try
    {
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(this.mPackageName, 0);
      String str = localApplicationInfo.loadLabel(localPackageManager).toString();
      AlertController.AlertParams localAlertParams = this.mAlertParams;
      localAlertParams.mIcon = localApplicationInfo.loadIcon(localPackageManager);
      localAlertParams.mTitle = str;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = str;
      localAlertParams.mMessage = getString(2131165193, arrayOfObject);
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
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        Log.e("UsbPermissionActivity", "unable to look up package name", localNameNotFoundException);
        finish();
      }
    }
  }

  public void onDestroy()
  {
    IUsbManager localIUsbManager = IUsbManager.Stub.asInterface(ServiceManager.getService("usb"));
    Intent localIntent = new Intent();
    try
    {
      if (this.mAccessory != null)
      {
        localIntent.putExtra("accessory", this.mAccessory);
        if (this.mPermissionGranted)
        {
          localIUsbManager.grantAccessoryPermission(this.mAccessory, this.mUid);
          if (this.mAlwaysUse.isChecked())
            localIUsbManager.setAccessoryPackage(this.mAccessory, this.mPackageName);
        }
      }
      localIntent.putExtra("permission", this.mPermissionGranted);
      this.mPendingIntent.send(this, 0, localIntent);
      if (this.mDisconnectedReceiver != null)
        unregisterReceiver(this.mDisconnectedReceiver);
      super.onDestroy();
      return;
    }
    catch (PendingIntent.CanceledException localCanceledException)
    {
      while (true)
        Log.w("UsbPermissionActivity", "PendingIntent was cancelled");
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        Log.e("UsbPermissionActivity", "IUsbService connection failed", localRemoteException);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbPermissionActivity
 * JD-Core Version:    0.6.0
 */