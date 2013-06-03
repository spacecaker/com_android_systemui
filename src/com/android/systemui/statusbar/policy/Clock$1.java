package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

class Clock$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str1 = paramIntent.getAction();
    if (str1.equals("android.intent.action.TIMEZONE_CHANGED"))
    {
      String str2 = paramIntent.getStringExtra("time-zone");
      Clock.access$002(this.this$0, Calendar.getInstance(TimeZone.getTimeZone(str2)));
      if (Clock.access$100(this.this$0) != null)
        Clock.access$100(this.this$0).setTimeZone(Clock.access$000(this.this$0).getTimeZone());
    }
    while (true)
    {
      this.this$0.updateClock();
      Clock.access$202(this.this$0, false);
      return;
      if (!str1.equals("android.intent.action.CONFIGURATION_CHANGED"))
        continue;
      Clock.access$202(this.this$0, true);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.Clock.1
 * JD-Core Version:    0.6.0
 */