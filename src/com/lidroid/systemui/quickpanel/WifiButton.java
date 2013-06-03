package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.Log;
import android.view.View;

public class WifiButton extends PowerButton
{
  private static final StateTracker sWifiState = new WifiStateTracker(null);

  public WifiButton()
  {
    this.mType = "toggleWifi";
  }

  protected IntentFilter getBroadcastIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
    return localIntentFilter;
  }

  protected int getText()
  {
    return 51052605;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.WIFI_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    sWifiState.onActualStateChange(paramContext, paramIntent);
  }

  protected void toggleState()
  {
    sWifiState.toggleState(this.mView.getContext());
  }

  protected void updateState()
  {
    this.mState = sWifiState.getTriState(this.mView.getContext());
    switch (this.mState)
    {
    case 3:
    case 4:
    default:
    case 2:
    case 1:
    case 5:
    }
    while (true)
    {
      return;
      this.mIcon = 50462863;
      continue;
      this.mIcon = 50462864;
      continue;
      if (sWifiState.isTurningOn())
      {
        this.mIcon = 50462864;
        continue;
      }
      this.mIcon = 50462863;
    }
  }

  private static final class WifiStateTracker extends StateTracker
  {
    private static int wifiStateToFiveState(int paramInt)
    {
      int i;
      switch (paramInt)
      {
      default:
        i = 6;
      case 1:
      case 3:
      case 0:
      case 2:
      }
      while (true)
      {
        return i;
        i = 2;
        continue;
        i = 1;
        continue;
        i = 4;
        continue;
        i = 3;
      }
    }

    public int getActualState(Context paramContext)
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (localWifiManager != null);
      for (int i = wifiStateToFiveState(localWifiManager.getWifiState()); ; i = 6)
        return i;
    }

    public void onActualStateChange(Context paramContext, Intent paramIntent)
    {
      if (!"android.net.wifi.WIFI_STATE_CHANGED".equals(paramIntent.getAction()));
      while (true)
      {
        return;
        setCurrentState(paramContext, wifiStateToFiveState(paramIntent.getIntExtra("wifi_state", -1)));
      }
    }

    protected void requestStateChange(Context paramContext, boolean paramBoolean)
    {
      WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
      if (localWifiManager == null)
        Log.d("WifiButton", "No wifiManager.");
      while (true)
      {
        return;
        new WifiButton.WifiStateTracker.1(this, localWifiManager, paramBoolean).execute(new Void[0]);
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.WifiButton
 * JD-Core Version:    0.6.0
 */