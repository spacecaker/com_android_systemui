package com.lidroid.systemui.quickpanel;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

public class BluetoothButton extends PowerButton
{
  private static final StateTracker sBluetoothState = new BluetoothStateTracker(null);

  public BluetoothButton()
  {
    this.mType = "toggleBluetooth";
  }

  protected IntentFilter getBroadcastIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
    return localIntentFilter;
  }

  protected int getText()
  {
    return 51052613;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.BLUETOOTH_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    sBluetoothState.onActualStateChange(paramContext, paramIntent);
  }

  protected void toggleState()
  {
    sBluetoothState.toggleState(this.mView.getContext());
  }

  protected void updateState()
  {
    this.mState = sBluetoothState.getTriState(this.mView.getContext());
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
      this.mIcon = 50462830;
      continue;
      this.mIcon = 50462831;
      continue;
      if (sBluetoothState.isTurningOn())
      {
        this.mIcon = 50462831;
        continue;
      }
      this.mIcon = 50462830;
    }
  }

  private static final class BluetoothStateTracker extends StateTracker
  {
    private static int bluetoothStateToFiveState(int paramInt)
    {
      int i;
      switch (paramInt)
      {
      default:
        i = 6;
      case 10:
      case 12:
      case 11:
      case 13:
      }
      while (true)
      {
        return i;
        i = 2;
        continue;
        i = 1;
        continue;
        i = 3;
        continue;
        i = 4;
      }
    }

    public int getActualState(Context paramContext)
    {
      BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if (localBluetoothAdapter == null);
      for (int i = 6; ; i = bluetoothStateToFiveState(localBluetoothAdapter.getState()))
        return i;
    }

    public void onActualStateChange(Context paramContext, Intent paramIntent)
    {
      if (!"android.bluetooth.adapter.action.STATE_CHANGED".equals(paramIntent.getAction()));
      while (true)
      {
        return;
        setCurrentState(paramContext, bluetoothStateToFiveState(paramIntent.getIntExtra("android.bluetooth.adapter.extra.STATE", -1)));
      }
    }

    protected void requestStateChange(Context paramContext, boolean paramBoolean)
    {
      new BluetoothButton.BluetoothStateTracker.1(this).execute(new Void[0]);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.BluetoothButton
 * JD-Core Version:    0.6.0
 */