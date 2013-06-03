package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;

class BatteryText$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.intent.action.BATTERY_CHANGED"))
    {
      BatteryText.access$0(this.this$0, paramIntent.getIntExtra("status", 1));
      BatteryText.access$1(this.this$0, paramIntent.getIntExtra("level", 50));
    }
    BatteryText.access$2(this.this$0, Settings.System.getString(this.this$0.getContext().getContentResolver(), "battery_text_append"));
    BatteryText.access$3(this.this$0, Settings.System.getString(this.this$0.getContext().getContentResolver(), "battery_text_prepend"));
    BatteryText.access$4(this.this$0, Settings.System.getInt(this.this$0.getContext().getContentResolver(), "battery_text_style", 2));
    this.this$0.updateBatteryColor();
    this.this$0.updateBatteryText(paramIntent);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.BatteryText.1
 * JD-Core Version:    0.6.0
 */