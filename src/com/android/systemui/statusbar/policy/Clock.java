package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class Clock extends TextView
{
  private boolean mAttached;
  private Calendar mCalendar;
  private SimpleDateFormat mClockFormat;
  private String mClockFormatString;
  private boolean mConfigChanged;
  private final BroadcastReceiver mIntentReceiver = new Clock.1(this);

  public Clock(Context paramContext)
  {
    this(paramContext, null);
  }

  public Clock(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public Clock(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  private final CharSequence getSmallTime()
  {
    Context localContext = getContext();
    boolean bool = DateFormat.is24HourFormat(localContext);
    int i;
    SimpleDateFormat localSimpleDateFormat;
    if (bool)
    {
      i = 17039478;
      String str = localContext.getString(i);
      if ((str.equals(this.mClockFormatString)) && ((bool) || (!this.mConfigChanged)))
        break label89;
      localSimpleDateFormat = new SimpleDateFormat(str);
      this.mClockFormat = localSimpleDateFormat;
      this.mClockFormatString = str;
    }
    while (true)
    {
      return localSimpleDateFormat.format(this.mCalendar.getTime());
      i = 17039477;
      break;
      label89: localSimpleDateFormat = this.mClockFormat;
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.TIME_TICK");
      localIntentFilter.addAction("android.intent.action.TIME_SET");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      localIntentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, getHandler());
    }
    this.mCalendar = Calendar.getInstance(TimeZone.getDefault());
    updateClock();
    setTextColor(-1);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAttached)
    {
      getContext().unregisterReceiver(this.mIntentReceiver);
      this.mAttached = false;
    }
  }

  final void updateClock()
  {
    this.mCalendar.setTimeInMillis(System.currentTimeMillis());
    setText(getSmallTime());
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.Clock
 * JD-Core Version:    0.6.0
 */