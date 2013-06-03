package com.android.systemui.statusbar.policy;

import java.util.Calendar;

final class l
  implements Runnable
{
  l(k paramk, boolean paramBoolean, DigitalClock paramDigitalClock)
  {
  }

  public final void run()
  {
    if (this.b)
      DigitalClock.a(this.c, Calendar.getInstance());
    this.c.a();
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.l
 * JD-Core Version:    0.6.0
 */