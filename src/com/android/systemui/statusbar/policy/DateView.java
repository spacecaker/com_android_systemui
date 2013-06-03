package com.android.systemui.statusbar.policy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import java.util.Date;

public final class DateView extends TextView
{
  private boolean mAttachedToWindow;
  private BroadcastReceiver mIntentReceiver = new DateView.1(this);
  private boolean mUpdating;
  private boolean mWindowVisible;

  public DateView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private boolean isVisible()
  {
    Object localObject = this;
    if (((View)localObject).getVisibility() != 0);
    for (int i = 0; ; i = 1)
    {
      return i;
      ViewParent localViewParent = ((View)localObject).getParent();
      if (!(localViewParent instanceof View))
        continue;
      localObject = (View)localViewParent;
      break;
    }
  }

  private void setUpdates()
  {
    boolean bool;
    if ((this.mAttachedToWindow) && (this.mWindowVisible) && (isVisible()))
    {
      bool = true;
      if (bool != this.mUpdating)
      {
        this.mUpdating = bool;
        if (!bool)
          break label91;
        IntentFilter localIntentFilter = new IntentFilter();
        localIntentFilter.addAction("android.intent.action.TIME_TICK");
        localIntentFilter.addAction("android.intent.action.TIME_SET");
        localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
        this.mContext.registerReceiver(this.mIntentReceiver, localIntentFilter, null, null);
        updateClock();
      }
    }
    while (true)
    {
      return;
      bool = false;
      break;
      label91: this.mContext.unregisterReceiver(this.mIntentReceiver);
    }
  }

  private final void updateClock()
  {
    Context localContext = getContext();
    Date localDate = new Date();
    CharSequence localCharSequence = android.text.format.DateFormat.format("EEEE", localDate);
    String str = android.text.format.DateFormat.getLongDateFormat(localContext).format(localDate);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = localCharSequence;
    arrayOfObject[1] = str;
    setText(localContext.getString(2131165226, arrayOfObject));
  }

  protected int getSuggestedMinimumWidth()
  {
    return 0;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mAttachedToWindow = true;
    setUpdates();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    this.mAttachedToWindow = false;
    setUpdates();
  }

  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    setUpdates();
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (paramInt == 0);
    for (boolean bool = true; ; bool = false)
    {
      this.mWindowVisible = bool;
      setUpdates();
      return;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.DateView
 * JD-Core Version:    0.6.0
 */