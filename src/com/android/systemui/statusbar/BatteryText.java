package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings.System;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.util.AttributeSet;
import android.widget.TextView;

public class BatteryText extends TextView
{
  private static final int STYLE_DISABLE = 2;
  private static final int STYLE_SHOW = 1;
  private static final int STYLE_SMALL_PERCENT = 3;
  private String appendText = "%";
  private int batteryLevel;
  private int batteryStatus;
  private boolean mAttached;
  private final BroadcastReceiver mIntentReceiver = new BatteryText.1(this);
  private String prependText = "";
  private int style;

  public BatteryText(Context paramContext)
  {
    super(paramContext);
  }

  public BatteryText(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public BatteryText(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
      localIntentFilter.addAction("android.intent.action.TIME_SET");
      localIntentFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
      localIntentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, getHandler());
    }
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

  final void updateBatteryColor()
  {
    int i;
    int k;
    int m;
    int n;
    int i1;
    if (Settings.System.getInt(getContext().getContentResolver(), "battery_text_color", 1) == 1)
    {
      i = 1;
      int j = Settings.System.getInt(getContext().getContentResolver(), "battery_color_auto_charging", -7088896);
      k = Settings.System.getInt(getContext().getContentResolver(), "battery_color_auto_regular", -1);
      m = Settings.System.getInt(getContext().getContentResolver(), "battery_color_auto_medium", -2776320);
      n = Settings.System.getInt(getContext().getContentResolver(), "battery_color_auto_low", -2798848);
      i1 = Settings.System.getInt(getContext().getContentResolver(), "battery_color", -1);
      if (i == 0)
        break label172;
      if ((this.batteryStatus != 2) && (this.batteryStatus != 5))
        break label128;
      setTextColor(j);
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label128: if (this.batteryLevel < 15)
      {
        setTextColor(n);
        continue;
      }
      if (this.batteryLevel < 40)
      {
        setTextColor(m);
        continue;
      }
      setTextColor(k);
      continue;
      label172: setTextColor(i1);
    }
  }

  final void updateBatteryText(Intent paramIntent)
  {
    if (this.style == 1)
    {
      setVisibility(0);
      setText(this.prependText + Integer.toString(this.batteryLevel) + this.appendText);
    }
    while (true)
    {
      return;
      if (this.style == 3)
      {
        setVisibility(0);
        String str = this.prependText + Integer.toString(this.batteryLevel) + "% ";
        SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(str);
        int i = str.indexOf("%");
        localSpannableStringBuilder.setSpan(new RelativeSizeSpan(0.7F), i, i + 1, 34);
        setText(localSpannableStringBuilder);
        continue;
      }
      setVisibility(8);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.BatteryText
 * JD-Core Version:    0.6.0
 */