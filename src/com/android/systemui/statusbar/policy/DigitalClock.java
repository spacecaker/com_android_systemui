package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Typeface;
import android.os.Handler;
import android.provider.Settings.System;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Calendar;

public class DigitalClock extends RelativeLayout
{
  private static Typeface j;
  private static Typeface k;
  private Calendar a;
  private String b;
  private TextView c;
  private TextView d;
  private i e;
  private ContentObserver f;
  private int g = 0;
  private final Handler h = new Handler();
  private BroadcastReceiver i;

  public DigitalClock(Context paramContext)
  {
    this(paramContext, null);
  }

  public DigitalClock(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void b()
  {
    if (DateFormat.is24HourFormat(getContext()));
    for (String str = "kk:mm"; ; str = "h:mm")
    {
      this.b = str;
      this.e.a(this.b.equals("h:mm"));
      return;
    }
  }

  public final void a()
  {
    this.a.setTimeInMillis(System.currentTimeMillis());
    CharSequence localCharSequence = DateFormat.format(this.b, this.a);
    this.c.setText(localCharSequence);
    this.d.setText(localCharSequence);
    i locali = this.e;
    if (this.a.get(9) == 0);
    for (boolean bool = true; ; bool = false)
    {
      locali.b(bool);
      return;
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.g = (1 + this.g);
    if (this.i == null)
    {
      this.i = new k(this);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.TIME_TICK");
      localIntentFilter.addAction("android.intent.action.TIME_SET");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      this.mContext.registerReceiver(this.i, localIntentFilter);
    }
    if (this.f == null)
    {
      this.f = new j(this);
      this.mContext.getContentResolver().registerContentObserver(Settings.System.CONTENT_URI, true, this.f);
    }
    a();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.g = (-1 + this.g);
    if (this.i != null)
      this.mContext.unregisterReceiver(this.i);
    if (this.f != null)
      this.mContext.getContentResolver().unregisterContentObserver(this.f);
    this.f = null;
    this.i = null;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    try
    {
      Resources localResources = this.mContext.getResources();
      if (j == null)
        j = Typeface.createFromAsset(localResources.getAssets(), "AndroidClock.ttf");
      if (k == null)
        k = Typeface.createFromAsset(localResources.getAssets(), "AndroidClock_Highlight.ttf");
      label48: this.c = ((TextView)findViewById(2131296304));
      if (j != null)
        this.c.setTypeface(j);
      this.c.setVisibility(4);
      this.d = ((TextView)findViewById(2131296303));
      if (k != null)
        this.d.setTypeface(k);
      this.e = new i();
      this.a = Calendar.getInstance();
      b();
      return;
    }
    catch (Exception localException)
    {
      break label48;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.DigitalClock
 * JD-Core Version:    0.6.0
 */