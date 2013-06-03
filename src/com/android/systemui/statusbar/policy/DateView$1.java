package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class DateView$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if (("android.intent.action.TIME_TICK".equals(str)) || ("android.intent.action.TIME_SET".equals(str)) || ("android.intent.action.TIMEZONE_CHANGED".equals(str)))
      DateView.access$000(this.this$0);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.DateView.1
 * JD-Core Version:    0.6.0
 */