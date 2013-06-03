package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import java.lang.ref.WeakReference;

final class k extends BroadcastReceiver
{
  private WeakReference a;
  private Context b;

  public k(DigitalClock paramDigitalClock)
  {
    this.a = new WeakReference(paramDigitalClock);
    this.b = paramDigitalClock.getContext();
  }

  public final void onReceive(Context paramContext, Intent paramIntent)
  {
    boolean bool = paramIntent.getAction().equals("android.intent.action.TIMEZONE_CHANGED");
    DigitalClock localDigitalClock = (DigitalClock)this.a.get();
    if (localDigitalClock != null)
      DigitalClock.a(localDigitalClock).post(new l(this, bool, localDigitalClock));
    while (true)
    {
      return;
      try
      {
        this.b.unregisterReceiver(this);
      }
      catch (RuntimeException localRuntimeException)
      {
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.k
 * JD-Core Version:    0.6.0
 */