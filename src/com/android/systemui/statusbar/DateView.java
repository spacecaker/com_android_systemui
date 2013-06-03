package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.TextView;
import java.util.Date;

public final class DateView extends TextView
{
  private BroadcastReceiver mIntentReceiver = new DateView.1(this);
  private boolean mUpdating = false;

  public DateView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private final void updateClock()
  {
    Date localDate = new Date();
    setText(android.text.format.DateFormat.getDateFormat(this.mContext).format(localDate));
  }

  protected int getSuggestedMinimumWidth()
  {
    return 0;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    setUpdates(false);
  }

  void setUpdates(boolean paramBoolean)
  {
    if (paramBoolean != this.mUpdating)
    {
      this.mUpdating = paramBoolean;
      if (!paramBoolean)
        break label57;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.TIME_TICK");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      this.mContext.registerReceiver(this.mIntentReceiver, localIntentFilter, null, null);
      updateClock();
    }
    while (true)
    {
      return;
      label57: this.mContext.unregisterReceiver(this.mIntentReceiver);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.DateView
 * JD-Core Version:    0.6.0
 */