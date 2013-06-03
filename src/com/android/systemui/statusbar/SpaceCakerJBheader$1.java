package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class SpaceCakerJBheader$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ((str.equals("android.intent.action.TIME_TICK")) || (str.equals("android.intent.action.TIMEZONE_CHANGED")))
      SpaceCakerJBheader.access$000(this.this$0);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.SpaceCakerJBheader.1
 * JD-Core Version:    0.6.0
 */