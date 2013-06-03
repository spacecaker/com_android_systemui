package com.android.systemui.statusbar.policy;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import java.lang.ref.WeakReference;

final class j extends ContentObserver
{
  private WeakReference a;
  private Context b;

  public j(DigitalClock paramDigitalClock)
  {
    super(new Handler());
    this.a = new WeakReference(paramDigitalClock);
    this.b = paramDigitalClock.getContext();
  }

  public final void onChange(boolean paramBoolean)
  {
    DigitalClock localDigitalClock = (DigitalClock)this.a.get();
    if (localDigitalClock != null)
    {
      DigitalClock.b(localDigitalClock);
      localDigitalClock.a();
    }
    while (true)
    {
      return;
      try
      {
        this.b.getContentResolver().unregisterContentObserver(this);
      }
      catch (RuntimeException localRuntimeException)
      {
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.j
 * JD-Core Version:    0.6.0
 */