package com.android.systemui.usb;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.storage.IMountService;
import android.os.storage.IMountService.Stub;
import android.os.storage.StorageEventListener;
import android.os.storage.StorageManager;
import android.provider.Settings.Secure;
import android.util.Slog;
import com.android.internal.app.ExternalMediaFormatActivity;

public class StorageNotification extends StorageEventListener
{
  private static IMountService mMountService = null;
  private Handler mAsyncEventHandler;
  private Context mContext;
  private Notification mMediaStorageNotification;
  private final BroadcastReceiver mReceiver = new StorageBroadcastReceiver(null);
  private StorageManager mStorageManager;
  private boolean mUmsAvailable;
  private Notification mUsbStorageNotification;

  public StorageNotification(Context paramContext)
  {
    Slog.d("StorageNotification", "StorageNotification :: ");
    this.mContext = paramContext;
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.ADB_ENABLED_BY_KIES_MODE");
    paramContext.registerReceiver(this.mReceiver, localIntentFilter);
    this.mStorageManager = ((StorageManager)paramContext.getSystemService("storage"));
    boolean bool = this.mStorageManager.isUsbMassStorageConnected();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(this.mUmsAvailable);
    arrayOfObject[1] = Environment.getExternalStorageState();
    Slog.d("StorageNotification", String.format("Startup with UMS connection %s (media state %s)", arrayOfObject));
    getMountService();
    HandlerThread localHandlerThread = new HandlerThread("SystemUI StorageNotification");
    localHandlerThread.start();
    this.mAsyncEventHandler = new Handler(localHandlerThread.getLooper());
    onUsbMassStorageConnectionChanged(bool);
  }

  private IMountService getMountService()
  {
    monitorenter;
    try
    {
      if (mMountService == null)
      {
        IBinder localIBinder = ServiceManager.getService("mount");
        if (localIBinder == null)
          break label33;
        mMountService = IMountService.Stub.asInterface(localIBinder);
      }
      while (true)
      {
        IMountService localIMountService = mMountService;
        return localIMountService;
        label33: Slog.e("StorageNotification", "Can't get mount service");
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void hideNotification(int paramInt)
  {
    Slog.d("StorageNotification", "hideNotification");
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    if (localNotificationManager == null)
      Slog.e("StorageNotification", "notificationManager is " + localNotificationManager);
    while (true)
    {
      return;
      Notification localNotification = new Notification();
      localNotification.flags = (0x8 | localNotification.flags);
      localNotification.icon = 0;
      localNotification.defaults = (0xFFFFFFFE & localNotification.defaults);
      try
      {
        localNotificationManager.notify(paramInt, localNotification);
      }
      catch (NullPointerException localNullPointerException)
      {
        Slog.e("StorageNotification", "Exception is " + localNullPointerException);
      }
    }
  }

  private static final boolean isActivityPlayStorageSounds()
  {
    boolean bool = false;
    try
    {
      if (mMountService != null)
        bool = mMountService.getPlayNotificationSounds();
      Slog.d("StorageNotification", "isActivityPlayStorageSounds :: bRet = " + bool);
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      while (true)
        Slog.e("StorageNotification", "isActivityPlayStorageSounds :: RemoteException " + localRemoteException);
    }
  }

  private void onStorageStateChangedAsync(String paramString1, String paramString2, String paramString3)
  {
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = paramString1;
    arrayOfObject1[1] = paramString2;
    arrayOfObject1[2] = paramString3;
    Slog.e("StorageNotification", String.format("onStorageStateChanged :: Media {%s} state changed from {%s} -> {%s}", arrayOfObject1));
    if (paramString3.equals("shared"))
    {
      Intent localIntent1 = new Intent();
      localIntent1.setClass(this.mContext, UsbStorageActivity.class);
      setUsbStorageNotification(17040340, 17040341, 17301642, false, true, PendingIntent.getActivity(this.mContext, 0, localIntent1, 0));
    }
    while (true)
    {
      return;
      if (paramString3.equals("pending"))
      {
        Slog.d("StorageNotification", "Wait for Mount...");
        continue;
      }
      if (paramString3.equals("checking"))
      {
        setMediaStorageNotification(17040360, 17040361, 17301675, true, false, null, true);
        hideNotification(17301675);
        continue;
      }
      if (paramString3.equals("mounted"))
      {
        if (!paramString2.equals("checking"))
          setMediaStorageNotification(17040360, 17040361, 17301675, true, false, null, true);
        hideNotification(17301675);
        updateUsbMassStorageNotification(this.mUmsAvailable);
        continue;
      }
      if (paramString3.equals("unmounted"))
      {
        if (!this.mStorageManager.isUsbMassStorageEnabled())
        {
          if ((paramString2.equals("shared")) || (paramString2.equals("unmountable")) || (paramString2.equals("removed")) || (paramString2.equals("pending")))
          {
            setMediaStorageNotification(0, 0, 0, false, false, null, false);
            updateUsbMassStorageNotification(this.mUmsAvailable);
            continue;
          }
          try
          {
            boolean bool2 = mMountService.getShowSafeUnmountNotification(paramString1);
            bool1 = bool2;
            if (bool1 == true)
            {
              setMediaStorageNotification(17040368, 17040369, 17301626, true, true, null, true);
              updateUsbMassStorageNotification(false);
              if (bool1 != true)
                continue;
              try
              {
                mMountService.setShowSafeUnmountNotification(paramString1, false);
              }
              catch (Exception localException2)
              {
              }
            }
          }
          catch (Exception localException1)
          {
            while (true)
            {
              boolean bool1 = false;
              continue;
              Slog.d("StorageNotification", "Maybe next state will be MEDIA_BAD_REMOVAL");
              setMediaStorageNotificationDummy(17301626, true);
            }
          }
        }
        Slog.d("StorageNotification", String.format("Be receviced the msg 'MEDIA_UNMOUNTED', but we still have connected ums", new Object[0]));
        setMediaStorageNotification(0, 0, 0, false, false, null, false);
        updateUsbMassStorageNotification(false);
        continue;
      }
      if (paramString3.equals("nofs"))
      {
        Intent localIntent2 = new Intent();
        localIntent2.setClass(this.mContext, ExternalMediaFormatActivity.class);
        setMediaStorageNotification(17040362, 17040363, 17301627, true, false, PendingIntent.getActivity(this.mContext, 0, localIntent2, 0), false);
        updateUsbMassStorageNotification(this.mUmsAvailable);
        continue;
      }
      if (paramString3.equals("unmountable"))
      {
        Intent localIntent3 = new Intent();
        localIntent3.setClass(this.mContext, ExternalMediaFormatActivity.class);
        setMediaStorageNotification(17040364, 17040365, 17301627, true, false, PendingIntent.getActivity(this.mContext, 0, localIntent3, 0), false);
        updateUsbMassStorageNotification(this.mUmsAvailable);
        continue;
      }
      if (paramString3.equals("removed"))
      {
        setMediaStorageNotification(17040370, 17040371, 17301627, true, true, null, false);
        hideNotification(17301627);
        updateUsbMassStorageNotification(false);
        continue;
      }
      if (paramString3.equals("bad_removal"))
      {
        setMediaStorageNotification(17040366, 17040367, 17301642, true, true, null, true);
        updateUsbMassStorageNotification(false);
        continue;
      }
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramString3;
      Slog.w("StorageNotification", String.format("Ignoring unknown state {%s}", arrayOfObject2));
    }
  }

  private void onUsbMassStorageConnectionChangedAsync(boolean paramBoolean)
  {
    Slog.d("StorageNotification", "onUsbMassStorageConnectionChangedAsync :: connected = " + paramBoolean);
    this.mUmsAvailable = paramBoolean;
    String str = Environment.getExternalStorageState();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = str;
    Slog.i("StorageNotification", String.format("UMS connection changed to %s (media state %s)", arrayOfObject));
    if ((paramBoolean) && ((str.equals("removed")) || (str.equals("checking")) || (str.equals("bad_removal")) || (str.equals("nofs")) || (str.equals("unmounted")) || (str.equals("pending"))))
      paramBoolean = false;
    updateUsbMassStorageNotification(paramBoolean);
  }

  private void sendUmsIntent(boolean paramBoolean)
  {
    Slog.d("StorageNotification", "sendUmsIntent :: connect = " + paramBoolean);
    if (paramBoolean == true)
      SystemClock.sleep(500L);
    Context localContext = this.mContext;
    if (paramBoolean);
    for (String str = "android.intent.action.UMS_CONNECTED"; ; str = "android.intent.action.UMS_DISCONNECTED")
    {
      localContext.sendBroadcast(new Intent(str));
      return;
    }
  }

  private void setMediaStorageNotification(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, PendingIntent paramPendingIntent, boolean paramBoolean3)
  {
    monitorenter;
    while (true)
    {
      NotificationManager localNotificationManager;
      int i;
      try
      {
        Slog.d("StorageNotification", "setMediaStorageNotification :: titleId = " + paramInt1 + ", messageId = " + paramInt2 + ", icon = " + paramInt3 + ", visible = " + paramBoolean1 + ", dismissable = " + paramBoolean2 + ", sound = " + paramBoolean3);
        if (paramBoolean1)
          continue;
        Notification localNotification2 = this.mMediaStorageNotification;
        if (localNotification2 == null)
          return;
        localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (localNotificationManager == null)
          continue;
        if ((this.mMediaStorageNotification == null) || (!paramBoolean1))
          continue;
        localNotificationManager.cancel(this.mMediaStorageNotification.icon);
        if (!paramBoolean1)
          continue;
        Resources localResources = Resources.getSystem();
        CharSequence localCharSequence1 = localResources.getText(paramInt1);
        CharSequence localCharSequence2 = localResources.getText(paramInt2);
        if (this.mMediaStorageNotification != null)
          continue;
        this.mMediaStorageNotification = new Notification();
        this.mMediaStorageNotification.when = 0L;
        if ((isActivityPlayStorageSounds()) && (0 != 0))
        {
          Slog.d("StorageNotification", "setMediaStorageNotification :: Play Inserted Sound");
          this.mMediaStorageNotification.sound = Uri.parse("system/media/audio/ui/Insert.ogg");
          if (!paramBoolean2)
            break label393;
          this.mMediaStorageNotification.flags = 16;
          this.mMediaStorageNotification.tickerText = localCharSequence1;
          if (paramPendingIntent != null)
            continue;
          Intent localIntent = new Intent();
          paramPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, localIntent, 0);
          this.mMediaStorageNotification.icon = paramInt3;
          this.mMediaStorageNotification.setLatestEventInfo(this.mContext, localCharSequence1, localCharSequence2, paramPendingIntent);
          i = this.mMediaStorageNotification.icon;
          if (!paramBoolean1)
            break label404;
          localNotificationManager.notify(i, this.mMediaStorageNotification);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      Slog.d("StorageNotification", "setMediaStorageNotification :: Don't play Inserted Sound. sound is " + false);
      Notification localNotification1 = this.mMediaStorageNotification;
      localNotification1.defaults = (0xFFFFFFFE & localNotification1.defaults);
      this.mMediaStorageNotification.sound = null;
      continue;
      label393: this.mMediaStorageNotification.flags = 2;
      continue;
      label404: localNotificationManager.cancel(i);
    }
  }

  private void setMediaStorageNotificationDummy(int paramInt, boolean paramBoolean)
  {
    monitorenter;
    try
    {
      Slog.d("StorageNotification", "setMediaStorageNotificationDummy :: icon = " + paramInt + ", sound = " + paramBoolean);
      try
      {
        NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        this.mMediaStorageNotification = null;
        if (this.mMediaStorageNotification == null)
        {
          this.mMediaStorageNotification = new Notification();
          this.mMediaStorageNotification.when = 0L;
        }
        if (localNotificationManager != null)
        {
          if ((!isActivityPlayStorageSounds()) || (0 == 0))
            break label133;
          Slog.d("StorageNotification", "setMediaStorageNotification :: Play Inserted Sound");
        }
        for (this.mMediaStorageNotification.sound = Uri.parse("system/media/audio/ui/Insert.ogg"); ; this.mMediaStorageNotification.sound = null)
        {
          localNotificationManager.notify(paramInt, this.mMediaStorageNotification);
          monitorexit;
          return;
          label133: Slog.d("StorageNotification", "setMediaStorageNotification :: Don't play Inserted Sound. sound is " + false);
          Notification localNotification = this.mMediaStorageNotification;
          localNotification.defaults = (0xFFFFFFFE & localNotification.defaults);
        }
      }
      catch (Exception localException)
      {
        while (true)
          Slog.e("StorageNotification", "Exception is " + localException);
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  private void setUsbStorageNotification(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2, PendingIntent paramPendingIntent)
  {
    monitorenter;
    while (true)
    {
      NotificationManager localNotificationManager;
      Resources localResources;
      int i;
      try
      {
        Slog.d("StorageNotification", "setUsbStorageNotification :: titleId = " + paramInt1 + ", messageId = " + paramInt2 + ", icon = " + paramInt3 + ", sound = " + paramBoolean1 + ", visible = " + paramBoolean2);
        if (paramBoolean2)
          continue;
        Notification localNotification3 = this.mUsbStorageNotification;
        if (localNotification3 == null)
          return;
        localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
        if (localNotificationManager == null)
          continue;
        if (!paramBoolean2)
          continue;
        localResources = Resources.getSystem();
        CharSequence localCharSequence1 = localResources.getText(paramInt1);
        if (paramInt2 == 0)
        {
          localCharSequence2 = null;
          if (this.mUsbStorageNotification != null)
            continue;
          this.mUsbStorageNotification = new Notification();
          this.mUsbStorageNotification.icon = paramInt3;
          this.mUsbStorageNotification.when = 0L;
          if (!paramBoolean1)
            break label344;
          Notification localNotification2 = this.mUsbStorageNotification;
          localNotification2.defaults = (0x1 | localNotification2.defaults);
          this.mUsbStorageNotification.flags = 2;
          this.mUsbStorageNotification.tickerText = localCharSequence1;
          if (paramPendingIntent != null)
            continue;
          Intent localIntent = new Intent();
          paramPendingIntent = PendingIntent.getBroadcast(this.mContext, 0, localIntent, 0);
          this.mUsbStorageNotification.setLatestEventInfo(this.mContext, localCharSequence1, localCharSequence2, paramPendingIntent);
          if (1 != Settings.Secure.getInt(this.mContext.getContentResolver(), "adb_enabled", 0))
            break label398;
          j = 1;
          if (!"GT-S5830".startsWith("GT-I5510"))
            break label377;
          if (j != 0)
            break label366;
          this.mUsbStorageNotification.fullScreenIntent = paramPendingIntent;
          i = this.mUsbStorageNotification.icon;
          if (!paramBoolean2)
            break label388;
          localNotificationManager.notify(i, this.mUsbStorageNotification);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      CharSequence localCharSequence2 = localResources.getText(paramInt2);
      continue;
      label344: Notification localNotification1 = this.mUsbStorageNotification;
      localNotification1.defaults = (0xFFFFFFFE & localNotification1.defaults);
      continue;
      label366: this.mUsbStorageNotification.fullScreenIntent = null;
      continue;
      label377: this.mUsbStorageNotification.fullScreenIntent = null;
      continue;
      label388: localNotificationManager.cancel(i);
      continue;
      label398: int j = 0;
    }
  }

  public void onStorageStateChanged(String paramString1, String paramString2, String paramString3)
  {
    this.mAsyncEventHandler.post(new Runnable(paramString1, paramString2, paramString3)
    {
      public void run()
      {
        StorageNotification.this.onStorageStateChangedAsync(this.val$path, this.val$oldState, this.val$newState);
      }
    });
  }

  public void onUsbMassStorageConnectionChanged(boolean paramBoolean)
  {
    this.mAsyncEventHandler.post(new Runnable(paramBoolean)
    {
      public void run()
      {
        StorageNotification.this.onUsbMassStorageConnectionChangedAsync(this.val$connected);
      }
    });
  }

  void updateUsbMassStorageNotification(boolean paramBoolean)
  {
    Slog.d("StorageNotification", "updateUsbMassStorageNotification :: available = " + paramBoolean);
    if (paramBoolean)
    {
      Intent localIntent = new Intent();
      localIntent.setClass(this.mContext, UsbStorageActivity.class);
      localIntent.setFlags(268435456);
      setUsbStorageNotification(17040338, 17040339, 17302212, false, true, PendingIntent.getActivity(this.mContext, 0, localIntent, 0));
    }
    while (true)
    {
      if ("GT-S5830".startsWith("GT-I5510"))
      {
        String str = Environment.getExternalStorageState().toString();
        Slog.d("StorageNotification", "updateUsbMassStorageNotification :: state = " + str);
        if (!str.equals("shared"))
          sendUmsIntent(paramBoolean);
      }
      return;
      setUsbStorageNotification(0, 0, 0, false, false, null);
    }
  }

  private class StorageBroadcastReceiver extends BroadcastReceiver
  {
    private StorageBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.intent.action.ADB_ENABLED_BY_KIES_MODE"))
      {
        String str = Environment.getExternalStorageState().toString();
        Slog.d("StorageNotification", "onReceive :: received ACTION_ADB_ENABLED_BY_KIES_MODE :: state = " + str);
        if ((!str.equals("mounted")) && (!str.equals("shared")))
          break label71;
        StorageNotification.this.updateUsbMassStorageNotification(true);
      }
      while (true)
      {
        return;
        label71: StorageNotification.this.updateUsbMassStorageNotification(false);
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.StorageNotification
 * JD-Core Version:    0.6.0
 */