package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;

public class SpaceCakerJBheader extends LinearLayout
{
  private final Context context;
  private TextView mClockView;
  private TextView mDateView;
  private BroadcastReceiver mIntentReceiver = new SpaceCakerJBheader.1(this);
  private boolean mUpdating = false;

  public SpaceCakerJBheader(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    initViews();
  }

  public SpaceCakerJBheader(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    initViews();
  }

  private String[] getTimeText()
  {
    Calendar localCalendar = Calendar.getInstance();
    Date localDate = localCalendar.getTime();
    int i = localCalendar.get(12);
    int j = localCalendar.get(11);
    if ((j > 12) && (android.text.format.DateFormat.is24HourFormat(this.context) != true))
      j -= 12;
    String[] arrayOfString = new String[2];
    StringBuilder localStringBuilder = new StringBuilder().append(j).append(":");
    if (i > 9);
    for (Object localObject = Integer.valueOf(i); ; localObject = "0" + i)
    {
      arrayOfString[0] = localObject;
      arrayOfString[1] = (android.text.format.DateFormat.format("EEEE", localDate) + "\n" + android.text.format.DateFormat.getLongDateFormat(this.context).format(localDate)).toUpperCase();
      return arrayOfString;
    }
  }

  private void initViews()
  {
    String[] arrayOfString = getTimeText();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.leftMargin = 8;
    this.mClockView = new TextView(this.context);
    this.mClockView.setTextSize(32.0F);
    this.mClockView.setTextColor(-1);
    this.mClockView.setText(arrayOfString[0]);
    this.mClockView.setLayoutParams(localLayoutParams);
    this.mDateView = new TextView(this.context);
    this.mDateView.setTextColor(Color.parseColor("#cccccc"));
    this.mDateView.setTextSize(12.0F);
    this.mDateView.setText(arrayOfString[1]);
    this.mDateView.setLayoutParams(localLayoutParams);
    addView(this.mClockView);
    addView(this.mDateView);
    setGravity(16);
  }

  private final void updateViews()
  {
    String[] arrayOfString = getTimeText();
    this.mClockView.setText(arrayOfString[0]);
    this.mDateView.setText(arrayOfString[1]);
    invalidate();
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    setUpdates(true);
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
      this.context.registerReceiver(this.mIntentReceiver, localIntentFilter, null, null);
      updateViews();
    }
    while (true)
    {
      return;
      label57: this.context.unregisterReceiver(this.mIntentReceiver);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.SpaceCakerJBheader
 * JD-Core Version:    0.6.0
 */