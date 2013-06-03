package com.android.systemui.statusbar;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;

class BatteryText$SettingsObserver extends ContentObserver
{
  BatteryText$SettingsObserver(BatteryText paramBatteryText, Handler paramHandler)
  {
    super(paramHandler);
  }

  void observe()
  {
    this.this$0.getContext().getContentResolver().registerContentObserver(BatteryText.access$0(), false, this);
  }

  public void onChange(boolean paramBoolean)
  {
    BatteryText.access$1(this.this$0);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.BatteryText.SettingsObserver
 * JD-Core Version:    0.6.0
 */