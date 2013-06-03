package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public final class WifiSettingButton extends QuickSettingButton
{
  private BroadcastReceiver mIntentReceiver = new WifiSettingButton.1(this);
  private WifiManager mWifiManager = null;

  public WifiSettingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void handleStateChanged(int paramInt)
  {
    updateStatus(paramInt);
  }

  private void updateIconsAndTextColor()
  {
    int i = 0;
    int j = -1;
    ImageView localImageView = (ImageView)getRootView().findViewById(2131296263);
    switch (getActivateStatus())
    {
    case 2:
    default:
    case 1:
    case 0:
    case 3:
    case 4:
    }
    while (true)
    {
      localImageView.setImageResource(i);
      setTextColor(j);
      return;
      i = 2130837529;
      setText(2131165200);
      continue;
      i = 2130837528;
      setText(2131165200);
      j = -3355444;
      continue;
      i = 2130837528;
      j = -3355444;
      setText(2131165209);
      continue;
      i = 2130837529;
      setText(2131165210);
    }
  }

  private void updateStatus(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 3:
    case 1:
    case 4:
    case 2:
    case 0:
    }
    while (true)
    {
      updateIconsAndTextColor();
      return;
      setActivateStatus(1);
      setSoundEffectsEnabled(true);
      continue;
      setActivateStatus(0);
      setSoundEffectsEnabled(true);
      continue;
      setActivateStatus(3);
      setSoundEffectsEnabled(false);
      continue;
      setActivateStatus(4);
      setSoundEffectsEnabled(false);
    }
  }

  public void activate()
  {
    Log.e("WifiSettingButton", "activate()");
    if (this.mWifiManager != null)
    {
      if (!this.mWifiManager.isWifiEnabled())
        break label34;
      Log.d("WifiSettingButton", "Wifi is already enabled.");
    }
    while (true)
    {
      return;
      label34: Log.d("WifiSettingButton", "Set wifi enable.");
      this.mWifiManager.setWifiEnabledDialog(true);
    }
  }

  public void deactivate()
  {
    Log.e("WifiSettingButton", "deactivate()");
    if (this.mWifiManager != null)
      this.mWifiManager.setWifiEnabledDialog(false);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("WifiSettingButton", "onAttachedToWindow()");
    this.mContext.registerReceiver(this.mIntentReceiver, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"), null, null);
    this.mWifiManager = ((WifiManager)this.mContext.getSystemService("wifi"));
    if (this.mWifiManager != null)
      updateStatus(this.mWifiManager.getWifiState());
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("WifiSettingButton", "onDetachedFromWindow()");
    this.mContext.unregisterReceiver(this.mIntentReceiver);
  }

  public void updateResources()
  {
    setText(2131165200);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.WifiSettingButton
 * JD-Core Version:    0.6.0
 */