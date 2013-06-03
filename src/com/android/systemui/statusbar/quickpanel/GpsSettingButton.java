package com.android.systemui.statusbar.quickpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings.Secure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public final class GpsSettingButton extends QuickSettingButton
{
  private GpsObserver mGpsObserver = new GpsObserver();

  public GpsSettingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setGPSEnabled(boolean paramBoolean)
  {
    Settings.Secure.setLocationProviderEnabled(this.mContext.getContentResolver(), "gps", paramBoolean);
  }

  private void updateIcons()
  {
    int i = 0;
    int j = -1;
    ImageView localImageView = (ImageView)getRootView().findViewById(2131296267);
    switch (getActivateStatus())
    {
    default:
    case 1:
    case 0:
    }
    while (true)
    {
      localImageView.setImageResource(i);
      setTextColor(j);
      return;
      i = 2130837521;
      continue;
      i = 2130837520;
      j = -3355444;
    }
  }

  private void updateStatus()
  {
    if (Settings.Secure.isLocationProviderEnabled(this.mContext.getContentResolver(), "gps"))
      setActivateStatus(1);
    while (true)
    {
      updateIcons();
      return;
      setActivateStatus(0);
    }
  }

  public void activate()
  {
    Log.e("GpsSettingButton", "activate()");
    setGPSEnabled(true);
  }

  public void deactivate()
  {
    Log.e("GpsSettingButton", "deactivate()");
    setGPSEnabled(false);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("GpsSettingButton", "onAttachedToWindow()");
    this.mContext.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("location_providers_allowed"), false, this.mGpsObserver);
    updateStatus();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("GpsSettingButton", "onDetachedFromWindow()");
    this.mContext.getContentResolver().unregisterContentObserver(this.mGpsObserver);
  }

  public void updateResources()
  {
    setText(2131165202);
  }

  private class GpsObserver extends ContentObserver
  {
    public GpsObserver()
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      GpsSettingButton.this.updateStatus();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.GpsSettingButton
 * JD-Core Version:    0.6.0
 */