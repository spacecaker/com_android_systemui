package com.spacecaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;

public class SlideBrightness extends LinearLayout
{
  private float BackLightValue;
  private Context context;
  private boolean mAttached;
  private final BroadcastReceiver mIntentReceiver = new SlideBrightness.1(this);
  private SeekBar sb;

  public SlideBrightness(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    this.sb = new SeekBar(paramContext);
  }

  public SlideBrightness(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.context = paramContext;
    this.sb = new SeekBar(paramContext);
  }

  private void updateRotationState()
  {
    removeAllViews();
    setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    setPadding(5, 5, 5, 5);
    this.sb.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    this.sb.setPadding(10, 0, 10, 0);
    addView(this.sb);
    this.sb.setMax(255);
    this.BackLightValue = 0.1F;
    int i = 2;
    try
    {
      i = Settings.System.getInt(this.context.getContentResolver(), "screen_brightness");
      Log.e("ONLOAD", Integer.toString(i));
      this.sb.setProgress(i);
      this.sb.setOnSeekBarChangeListener(new SlideBrightness.2(this));
      return;
    }
    catch (Settings.SettingNotFoundException localSettingNotFoundException)
    {
      while (true)
        localSettingNotFoundException.printStackTrace();
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, getHandler());
    }
    updateRotationState();
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
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.spacecaker.SlideBrightness
 * JD-Core Version:    0.6.0
 */