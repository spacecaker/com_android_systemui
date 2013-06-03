package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.TextView;

public class BatteryBar extends TextView
{
  private static final int STYLE_DISABLE = 2;
  private static final int STYLE_SHOW = 1;
  private int batteryLevel;
  private int batteryStatus;
  private int height;
  private boolean mAttached;
  private final BroadcastReceiver mIntentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.intent.action.BATTERY_CHANGED"))
      {
        BatteryBar.this.batteryStatus = paramIntent.getIntExtra("status", 1);
        BatteryBar.this.batteryLevel = paramIntent.getIntExtra("level", 100);
      }
      BatteryBar.this.setText("");
      BatteryBar.this.updateBatteryBar();
    }
  };
  private int style;
  private int width;

  public BatteryBar(Context paramContext)
  {
    super(paramContext);
  }

  public BatteryBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public BatteryBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    setText("");
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
      localIntentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, getHandler());
      new SettingsObserver(getHandler()).observe();
    }
    updateBatteryBar();
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

  final void updateBatteryBar()
  {
    updateBatteryColor();
    this.style = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_style", 2);
    int i = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_height", 1);
    if (this.style == 1)
    {
      setVisibility(0);
      WindowManager localWindowManager = (WindowManager)getContext().getApplicationContext().getSystemService("window");
      ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
      this.width = localWindowManager.getDefaultDisplay().getWidth();
      this.height = i;
      localLayoutParams.width = (this.batteryLevel * this.width / 100);
      localLayoutParams.height = this.height;
      setLayoutParams(localLayoutParams);
    }
    while (true)
    {
      return;
      setVisibility(8);
    }
  }

  final void updateBatteryColor()
  {
    int i = 1;
    int k;
    int m;
    int n;
    int i1;
    if (Settings.System.getInt(getContext().getContentResolver(), "battery_bar_auto_color", i) == i)
    {
      int j = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_color_auto_charging", -7088896);
      k = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_color_auto_regular", -1);
      m = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_color_auto_medium", -2776320);
      n = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_color_auto_low", -2798848);
      i1 = Settings.System.getInt(getContext().getContentResolver(), "battery_bar_color", -1);
      if (i == 0)
        break label172;
      if ((this.batteryStatus != 2) && (this.batteryStatus != 5))
        break label128;
      setBackgroundColor(j);
    }
    while (true)
    {
      return;
      i = 0;
      break;
      label128: if (this.batteryLevel < 15)
      {
        setBackgroundColor(n);
        continue;
      }
      if (this.batteryLevel < 40)
      {
        setBackgroundColor(m);
        continue;
      }
      setBackgroundColor(k);
      continue;
      label172: setBackgroundColor(i1);
    }
  }

  class SettingsObserver extends ContentObserver
  {
    public SettingsObserver(Handler arg2)
    {
      super();
      Log.d("pvymods", "signal observe");
    }

    void observe()
    {
      Log.d("pvymods", "signal observe");
      ContentResolver localContentResolver = BatteryBar.this.getContext().getContentResolver();
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_style"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_height"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_color"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_color_auto_low"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_color_auto_medium"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_color_auto_regular"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_color_auto_charging"), false, this);
      localContentResolver.registerContentObserver(Settings.System.getUriFor("battery_bar_auto_color"), false, this);
    }

    public void onChange(boolean paramBoolean)
    {
      BatteryBar.this.updateBatteryBar();
      Log.d("pvymods", "batterybar observed change done");
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.BatteryBar
 * JD-Core Version:    0.6.0
 */