package com.lidroid.systemui.quickpanel;

import android.bluetooth.BluetoothAdapter;
import android.os.AsyncTask;

class BluetoothButton$BluetoothStateTracker$1 extends AsyncTask<Void, Void, Void>
{
  protected Void doInBackground(Void[] paramArrayOfVoid)
  {
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter.isEnabled())
      localBluetoothAdapter.disable();
    while (true)
    {
      return null;
      localBluetoothAdapter.enable();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.BluetoothButton.BluetoothStateTracker.1
 * JD-Core Version:    0.6.0
 */