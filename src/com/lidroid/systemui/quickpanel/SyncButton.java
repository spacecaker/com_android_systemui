package com.lidroid.systemui.quickpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.net.ConnectivityManager;
import android.util.Log;
import android.view.View;

public class SyncButton extends PowerButton
{
  private static final String TAG = "SyncButton";
  private SyncStatusObserver mSyncObserver = new SyncButton.1(this);
  private Object mSyncObserverHandle = null;

  public SyncButton()
  {
    this.mType = "toggleSync";
  }

  private static boolean getBackgroundDataState(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getBackgroundDataSetting();
  }

  private static boolean getSyncState(Context paramContext)
  {
    boolean bool1 = getBackgroundDataState(paramContext);
    boolean bool2 = ContentResolver.getMasterSyncAutomatically();
    if ((bool1) && (bool2));
    for (int i = 1; ; i = 0)
      return i;
  }

  protected int getText()
  {
    return 51052615;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.SYNC_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void setupButton(View paramView)
  {
    super.setupButton(paramView);
    if ((this.mView == null) && (this.mSyncObserverHandle != null))
    {
      Log.i("SyncButton", "Unregistering sync state listener");
      ContentResolver.removeStatusChangeListener(this.mSyncObserverHandle);
      this.mSyncObserverHandle = null;
    }
    while (true)
    {
      return;
      if ((this.mView != null) && (this.mSyncObserverHandle == null))
      {
        Log.i("SyncButton", "Registering sync state listener");
        this.mSyncObserverHandle = ContentResolver.addStatusChangeListener(1, this.mSyncObserver);
        continue;
      }
    }
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    ConnectivityManager localConnectivityManager = (ConnectivityManager)localContext.getSystemService("connectivity");
    boolean bool1 = getBackgroundDataState(localContext);
    boolean bool2 = ContentResolver.getMasterSyncAutomatically();
    if ((!bool1) && (!bool2))
    {
      localConnectivityManager.setBackgroundDataSetting(true);
      ContentResolver.setMasterSyncAutomatically(true);
    }
    if ((!bool1) && (bool2))
      localConnectivityManager.setBackgroundDataSetting(true);
    if ((bool1) && (!bool2))
      ContentResolver.setMasterSyncAutomatically(true);
    if ((bool1) && (bool2))
      ContentResolver.setMasterSyncAutomatically(false);
  }

  protected void updateState()
  {
    if (getSyncState(this.mView.getContext()))
      this.mIcon = 50462860;
    for (this.mState = 1; ; this.mState = 2)
    {
      return;
      this.mIcon = 50462859;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.SyncButton
 * JD-Core Version:    0.6.0
 */