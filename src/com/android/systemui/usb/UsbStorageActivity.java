package com.android.systemui.usb;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemProperties;
import android.os.storage.IMountService;
import android.os.storage.IMountService.Stub;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.io.File;
import java.util.List;

public class UsbStorageActivity extends Activity
  implements DialogInterface.OnCancelListener, View.OnClickListener
{
  private Handler mAsyncStorageHandler;
  private TextView mBanner;
  private boolean mDestroyed;
  private ImageView mIcon;
  private TextView mMessage;
  private Button mMountButton;
  private ProgressBar mProgressBar;
  private StorageEventListener mStorageListener = new UsbStorageActivity.2(this);
  private StorageManager mStorageManager = null;
  private Handler mUIHandler;
  private Button mUnmountButton;
  private BroadcastReceiver mUsbStateReceiver = new UsbStorageActivity.1(this);

  private void checkStorageUsers()
  {
    this.mAsyncStorageHandler.post(new UsbStorageActivity.9(this));
  }

  private void checkStorageUsersAsync()
  {
    IMountService localIMountService = getMountService();
    if (localIMountService == null)
      scheduleShowDialog(2);
    while (true)
    {
      return;
      String str = Environment.getExternalStorageDirectory().toString();
      int i = 0;
      try
      {
        int[] arrayOfInt = localIMountService.getStorageUsers(str);
        if ((arrayOfInt != null) && (arrayOfInt.length > 0))
          Log.e("UsbStorageActivity", "checkStorageUsersAsync :: stUsers = " + arrayOfInt + ", stUsers.length = " + arrayOfInt.length);
        for (i = 1; ; i = 1)
        {
          List localList;
          do
          {
            if (i == 0)
              break label170;
            scheduleShowDialog(1);
            break;
            localList = ((ActivityManager)getSystemService("activity")).getRunningExternalApplications();
          }
          while ((localList == null) || (localList.size() <= 0));
          Log.e("UsbStorageActivity", "checkStorageUsersAsync :: infoList.size() = " + localList.size());
        }
      }
      catch (RemoteException localRemoteException)
      {
        while (true)
          scheduleShowDialog(2);
        label170: Log.i("UsbStorageActivity", "onClick !!! (Enabling UMS)");
        if (this.mStorageManager.isUsbMassStorageConnected())
        {
          switchUsbMassStorage(true);
          continue;
        }
        Log.i("UsbStorageActivity", "onClick :: Failed not connected ums mode or usb is ejected");
      }
    }
  }

  private IMountService getMountService()
  {
    IBinder localIBinder = ServiceManager.getService("mount");
    if (localIBinder != null);
    for (IMountService localIMountService = IMountService.Stub.asInterface(localIBinder); ; localIMountService = null)
      return localIMountService;
  }

  private void handleUsbStateChanged(Intent paramIntent)
  {
    if (!paramIntent.getExtras().getBoolean("connected"))
    {
      removeDialog(1);
      removeDialog(2);
      finish();
    }
  }

  private void scheduleShowDialog(int paramInt)
  {
    Log.w("UsbStorageActivity", "showDialogInner :: id = " + paramInt);
    this.mUIHandler.post(new UsbStorageActivity.6(this, paramInt));
  }

  private void switchDisplay(boolean paramBoolean)
  {
    this.mUIHandler.post(new UsbStorageActivity.3(this, paramBoolean));
  }

  private void switchDisplayAsync(boolean paramBoolean)
  {
    Log.d("UsbStorageActivity", "switchDisplayAsync :: usbStorageInUse = " + paramBoolean);
    if (paramBoolean)
    {
      this.mProgressBar.setVisibility(8);
      this.mUnmountButton.setVisibility(0);
      this.mMountButton.setVisibility(8);
      this.mIcon.setImageResource(17302472);
      this.mBanner.setText(17040342);
      this.mMessage.setText(17040343);
    }
    while (true)
    {
      return;
      this.mProgressBar.setVisibility(8);
      this.mUnmountButton.setVisibility(8);
      this.mMountButton.setVisibility(0);
      this.mIcon.setImageResource(17302471);
      this.mBanner.setText(17040334);
      this.mMessage.setText(17040335);
    }
  }

  private void switchUsbMassStorage(boolean paramBoolean)
  {
    if (SystemProperties.getBoolean("ro.monkey", false))
      Log.d("UsbStorageActivity", "Monkey Running: Switching to UsbMassStorage disabled");
    while (true)
    {
      return;
      this.mUIHandler.post(new UsbStorageActivity.7(this));
      this.mAsyncStorageHandler.post(new UsbStorageActivity.8(this, paramBoolean));
    }
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    Log.i("UsbStorageActivity", "onCancel !!!");
    finish();
  }

  public void onClick(View paramView)
  {
    if (paramView == this.mMountButton)
      checkStorageUsers();
    while (true)
    {
      return;
      if (paramView == this.mUnmountButton)
      {
        Log.i("UsbStorageActivity", "onClick !!! (Disabling UMS)");
        switchUsbMassStorage(false);
        continue;
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    Log.d("UsbStorageActivity", "onCreate !!!");
    super.onCreate(paramBundle);
    if (this.mStorageManager == null)
    {
      this.mStorageManager = ((StorageManager)getSystemService("storage"));
      if (this.mStorageManager == null)
        Log.w("UsbStorageActivity", "Failed to get StorageManager");
    }
    this.mUIHandler = new Handler();
    HandlerThread localHandlerThread = new HandlerThread("SystemUI UsbStorageActivity");
    localHandlerThread.start();
    this.mAsyncStorageHandler = new Handler(localHandlerThread.getLooper());
    requestWindowFeature(5);
    setProgressBarIndeterminateVisibility(true);
    getWindow().addFlags(4194304);
    if (Environment.isExternalStorageRemovable())
      getWindow().addFlags(524288);
    setTitle(getString(17040333));
    setContentView(17367151);
    this.mIcon = ((ImageView)findViewById(16908294));
    this.mBanner = ((TextView)findViewById(16908722));
    this.mMessage = ((TextView)findViewById(16908299));
    this.mMountButton = ((Button)findViewById(16908879));
    this.mMountButton.setOnClickListener(this);
    this.mUnmountButton = ((Button)findViewById(16908880));
    this.mUnmountButton.setOnClickListener(this);
    this.mProgressBar = ((ProgressBar)findViewById(16908301));
  }

  public Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    AlertDialog localAlertDialog;
    switch (paramInt)
    {
    default:
      localAlertDialog = null;
    case 1:
    case 2:
    }
    while (true)
    {
      return localAlertDialog;
      localAlertDialog = new AlertDialog.Builder(this).setTitle(17040346).setPositiveButton(17040349, new UsbStorageActivity.5(this)).setNegativeButton(17039360, null).setMessage(17040347).setOnCancelListener(this).create();
      continue;
      localAlertDialog = new AlertDialog.Builder(this).setTitle(17040348).setNeutralButton(17040349, null).setMessage(17040337).setOnCancelListener(this).create();
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mDestroyed = true;
  }

  protected void onPause()
  {
    super.onPause();
    Log.d("UsbStorageActivity", "onPause !!!");
    removeDialog(1);
    removeDialog(2);
    unregisterReceiver(this.mUsbStateReceiver);
    if ((this.mStorageManager == null) && (this.mStorageListener != null))
      this.mStorageManager.unregisterListener(this.mStorageListener);
  }

  protected void onResume()
  {
    Log.d("UsbStorageActivity", "onResume !!!");
    super.onResume();
    this.mStorageManager.registerListener(this.mStorageListener);
    registerReceiver(this.mUsbStateReceiver, new IntentFilter("android.hardware.usb.action.USB_STATE"));
    try
    {
      this.mAsyncStorageHandler.post(new UsbStorageActivity.4(this));
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("UsbStorageActivity", "Failed to read UMS enable state", localException);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbStorageActivity
 * JD-Core Version:    0.6.0
 */