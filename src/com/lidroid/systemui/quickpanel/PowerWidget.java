package com.lidroid.systemui.quickpanel;

import android.app.StatusBarManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PowerWidget extends FrameLayout
{
  private static final String BUTTONS_DEFAULT = "toggleWifi|toggleMobileData|toggleBluetooth|toggleGPS|toggleFlashlight|toggleBrightness|toggleAirplane|toggleSound|toggleAutoRotate|toggleSync|toggleScreenTimeout|toggleLockScreen|toggleReboot|toggleShutdown";
  public static final String BUTTON_DELIMITER = "|";
  private static final LinearLayout.LayoutParams BUTTON_LAYOUT_PARAMS;
  public static final String EXPANDED_BRIGHTNESS_MODE = "expanded_brightness_mode";
  public static final String EXPANDED_FLASH_MODE = "expanded_flash_mode";
  public static final String EXPANDED_HAPTIC_FEEDBACK = "expanded_haptic_feedback";
  public static final String EXPANDED_HIDE_ONCHANGE = "expanded_hide_onchange";
  public static final String EXPANDED_RING_MODE = "expanded_ring_mode";
  public static final String EXPANDED_SCREENTIMEOUT_MODE = "expanded_screentimeout_mode";
  private static final int LAYOUT_SCROLL_BUTTON_THRESHOLD_LAND = 8;
  private static final int LAYOUT_SCROLL_BUTTON_THRESHOLD_PORT = 5;
  private static final String TAG = "PowerWidget";
  public static final String WIDGET_BUTTONS = "expanded_widget_buttons";
  private static final FrameLayout.LayoutParams WIDGET_LAYOUT_PARAMS = new FrameLayout.LayoutParams(-1, -2);
  private WidgetBroadcastReceiver mBroadcastReceiver = null;
  private Context mContext;
  private Handler mHandler = new Handler();
  private LayoutInflater mInflater;
  private List<WidgetSettingsObserver> mObservers = new LinkedList();
  private HorizontalScrollView mScrollView;

  static
  {
    BUTTON_LAYOUT_PARAMS = new LinearLayout.LayoutParams(-2, -1, 1.0F);
  }

  public PowerWidget(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
    updateButtonLayoutWidth();
  }

  private void animateCollapse()
  {
    ((StatusBarManager)this.mContext.getSystemService("statusbar")).collapse();
  }

  private boolean needScrollBar(int paramInt)
  {
    int i;
    if (this.mContext.getResources().getConfiguration().orientation == 2)
      if (paramInt > 8)
        i = 1;
    while (true)
    {
      return i;
      i = 0;
      continue;
      if (paramInt > 5)
      {
        i = 1;
        continue;
      }
      i = 0;
    }
  }

  private void observeAllObserver()
  {
    Iterator localIterator = this.mObservers.iterator();
    while (localIterator.hasNext())
      ((WidgetSettingsObserver)localIterator.next()).observe();
  }

  private void setupBroadcastReceiver()
  {
    if (this.mBroadcastReceiver == null)
      this.mBroadcastReceiver = new WidgetBroadcastReceiver(null);
  }

  private void setupSettingsObserver()
  {
    if (!this.mObservers.isEmpty())
    {
      unobserveAllObserver();
      this.mObservers.clear();
    }
    this.mObservers.add(new WidgetSettingsObserver(this.mHandler, Settings.System.getUriFor("expanded_haptic_feedback")));
    this.mObservers.add(new WidgetSettingsObserver(this.mHandler, Settings.System.getUriFor("expanded_widget_buttons")));
    Iterator localIterator = PowerButton.getAllObservedUris().iterator();
    while (localIterator.hasNext())
    {
      Uri localUri = (Uri)localIterator.next();
      this.mObservers.add(new WidgetSettingsObserver(this.mHandler, localUri));
    }
  }

  private void unobserveAllObserver()
  {
    Iterator localIterator = this.mObservers.iterator();
    while (localIterator.hasNext())
      ((WidgetSettingsObserver)localIterator.next()).unobserve();
  }

  private void updateButtonLayoutWidth()
  {
    if (this.mContext.getResources().getConfiguration().orientation == 2);
    for (BUTTON_LAYOUT_PARAMS.width = (this.mContext.getResources().getDisplayMetrics().widthPixels / 8); ; BUTTON_LAYOUT_PARAMS.width = (this.mContext.getResources().getDisplayMetrics().widthPixels / 5))
      return;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    setGlobalButtonOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (Settings.System.getInt(PowerWidget.this.mContext.getContentResolver(), "expanded_hide_onchange", 0) == 1)
          PowerWidget.this.animateCollapse();
      }
    });
    setGlobalButtonOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        PowerWidget.this.animateCollapse();
        return true;
      }
    });
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mBroadcastReceiver != null)
      this.mContext.unregisterReceiver(this.mBroadcastReceiver);
    unobserveAllObserver();
    this.mObservers.clear();
  }

  public void setGlobalButtonOnClickListener(View.OnClickListener paramOnClickListener)
  {
    PowerButton.setGlobalOnClickListener(paramOnClickListener);
  }

  public void setGlobalButtonOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    PowerButton.setGlobalOnLongClickListener(paramOnLongClickListener);
  }

  public void setupWidget()
  {
    Log.i("PowerWidget", "Clearing any old widget stuffs");
    removeAllViews();
    if (this.mBroadcastReceiver != null)
      this.mContext.unregisterReceiver(this.mBroadcastReceiver);
    unobserveAllObserver();
    PowerButton.unloadAllButtons();
    Log.i("PowerWidget", "Setting up widget");
    String str1 = Settings.System.getString(this.mContext.getContentResolver(), "expanded_widget_buttons");
    if (str1 == null)
    {
      Log.i("PowerWidget", "Default buttons being loaded");
      str1 = "toggleWifi|toggleMobileData|toggleBluetooth|toggleGPS|toggleFlashlight|toggleBrightness|toggleAirplane|toggleSound|toggleAutoRotate|toggleSync|toggleScreenTimeout|toggleLockScreen|toggleReboot|toggleShutdown";
    }
    Log.i("PowerWidget", "Button list: " + str1);
    LinearLayout localLinearLayout = new LinearLayout(this.mContext);
    localLinearLayout.setOrientation(0);
    localLinearLayout.setGravity(1);
    int i = 0;
    String[] arrayOfString = str1.split("\\|");
    int j = arrayOfString.length;
    int k = 0;
    if (k < j)
    {
      String str2 = arrayOfString[k];
      Log.i("PowerWidget", "Setting up button: " + str2);
      View localView = this.mInflater.inflate(50528258, null, false);
      if (PowerButton.loadButton(str2, localView))
      {
        localLinearLayout.addView(localView, BUTTON_LAYOUT_PARAMS);
        i++;
      }
      while (true)
      {
        k++;
        break;
        Log.e("PowerWidget", "Error setting up button: " + str2);
      }
    }
    if (needScrollBar(i))
    {
      this.mScrollView = ((HorizontalScrollView)this.mInflater.inflate(50528260, null, false));
      localLinearLayout.setPadding(localLinearLayout.getPaddingLeft(), localLinearLayout.getPaddingTop(), localLinearLayout.getPaddingRight(), this.mScrollView.getVerticalScrollbarWidth());
      this.mScrollView.addView(localLinearLayout, WIDGET_LAYOUT_PARAMS);
      addView(this.mScrollView, WIDGET_LAYOUT_PARAMS);
    }
    while (true)
    {
      setupBroadcastReceiver();
      IntentFilter localIntentFilter = PowerButton.getAllBroadcastIntentFilters();
      localIntentFilter.addAction("android.settings.SETTINGS_CHANGED_ACTION");
      localIntentFilter.addAction("android.intent.action.BOOT_COMPLETED");
      localIntentFilter.addAction("android.intent.action.CONFIGURATION_CHANGED");
      this.mContext.registerReceiver(this.mBroadcastReceiver, localIntentFilter);
      setupSettingsObserver();
      observeAllObserver();
      return;
      addView(localLinearLayout, WIDGET_LAYOUT_PARAMS);
    }
  }

  public void updateWidget()
  {
    PowerButton.updateAllButtons();
  }

  private class WidgetBroadcastReceiver extends BroadcastReceiver
  {
    private WidgetBroadcastReceiver()
    {
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (paramIntent.getAction().equals("android.intent.action.BOOT_COMPLETED"))
        PowerWidget.this.setupWidget();
      while (true)
      {
        PowerWidget.this.updateWidget();
        return;
        if (paramIntent.getAction().equals("android.intent.action.CONFIGURATION_CHANGED"))
        {
          PowerWidget.this.updateButtonLayoutWidth();
          PowerWidget.this.setupWidget();
          continue;
        }
        PowerButton.handleOnReceive(paramContext, paramIntent);
      }
    }
  }

  private class WidgetSettingsObserver extends ContentObserver
  {
    private Uri mUri;

    public WidgetSettingsObserver(Handler paramUri, Uri arg3)
    {
      super();
      Object localObject;
      this.mUri = localObject;
    }

    public void observe()
    {
      PowerWidget.this.mContext.getContentResolver().registerContentObserver(this.mUri, false, this);
    }

    public void onChange(boolean paramBoolean)
    {
      if (this.mUri.equals(Settings.System.getUriFor("expanded_widget_buttons")))
        PowerWidget.this.setupWidget();
      PowerButton.handleOnChangeUri(this.mUri);
      PowerWidget.this.updateWidget();
    }

    public void unobserve()
    {
      PowerWidget.this.mContext.getContentResolver().unregisterContentObserver(this);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.PowerWidget
 * JD-Core Version:    0.6.0
 */