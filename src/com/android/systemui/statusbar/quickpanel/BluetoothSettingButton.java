package com.android.systemui.statusbar.quickpanel;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public final class BluetoothSettingButton extends QuickSettingButton
{
  private BluetoothAdapter mBluetoothAdapter = null;
  private BroadcastReceiver mIntentReceiver = new BluetoothSettingButton.1(this);

  public BluetoothSettingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private boolean couldClick()
  {
    int i;
    if (Settings.System.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 1)
      i = 0;
    while (true)
    {
      return i;
      if (this.mBluetoothAdapter == null)
      {
        Log.e("BluetoothSettingButton", "mBluetoothAdapter is null");
        i = 0;
        continue;
      }
      i = 1;
    }
  }

  private void handleStateChanged(int paramInt)
  {
    updateStatus(paramInt);
  }

  private void updateIconsAndTextColor()
  {
    int i = 0;
    int j = -1;
    ImageView localImageView = (ImageView)getRootView().findViewById(2131296265);
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
      i = 2130837516;
      setText(2131165201);
      continue;
      i = 2130837515;
      setText(2131165201);
      j = -3355444;
      continue;
      i = 2130837515;
      j = -3355444;
      setText(2131165209);
      continue;
      i = 2130837516;
      setText(2131165210);
    }
  }

  private void updateStatus(int paramInt)
  {
    switch (paramInt)
    {
    default:
    case 12:
    case 10:
    case 11:
    case 13:
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
    if (!couldClick())
      Log.e("BluetoothSettingButton", "activate() couldn't click");
    while (true)
    {
      return;
      Log.e("BluetoothSettingButton", "activate()");
      this.mBluetoothAdapter.enable();
    }
  }

  public void deactivate()
  {
    if (!couldClick())
      Log.e("BluetoothSettingButton", "deactivate() couldn't click");
    while (true)
    {
      return;
      Log.e("BluetoothSettingButton", "deactivate()");
      this.mBluetoothAdapter.disable();
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("BluetoothSettingButton", "onAttachedToWindow()");
    this.mContext.registerReceiver(this.mIntentReceiver, new IntentFilter("android.bluetooth.adapter.action.STATE_CHANGED"), null, null);
    this.mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (this.mBluetoothAdapter != null)
      updateStatus(this.mBluetoothAdapter.getState());
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("BluetoothSettingButton", "onDetachedFromWindow()");
    this.mContext.unregisterReceiver(this.mIntentReceiver);
  }

  public void updateResources()
  {
    setText(2131165201);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.BluetoothSettingButton
 * JD-Core Version:    0.6.0
 */