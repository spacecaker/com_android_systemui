package com.android.systemui.statusbar.quickpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public final class AutoRotationSettingButton extends QuickSettingButton
{
  private AutoRotationObserver mAutoRotationObserver = new AutoRotationObserver();

  public AutoRotationSettingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setRotationEnabled(int paramInt)
  {
    Settings.System.putInt(this.mContext.getContentResolver(), "accelerometer_rotation", paramInt);
  }

  private void updateIcons()
  {
    int i = 0;
    int j = -1;
    ImageView localImageView = (ImageView)getRootView().findViewById(2131296271);
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
      i = 2130837523;
      continue;
      i = 2130837522;
      j = -3355444;
    }
  }

  private void updateStatus()
  {
    if (1 == Settings.System.getInt(this.mContext.getContentResolver(), "accelerometer_rotation", 0))
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
    Log.e("AutoRotationSettingButton", "activate()");
    setRotationEnabled(1);
  }

  public void deactivate()
  {
    Log.e("AutoRotationSettingButton", "deactivate()");
    setRotationEnabled(0);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("AutoRotationSettingButton", "onAttachedToWindow()");
    this.mContext.getContentResolver().registerContentObserver(Settings.System.getUriFor("accelerometer_rotation"), false, this.mAutoRotationObserver);
    updateStatus();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("AutoRotationSettingButton", "onDetachedFromWindow()");
    this.mContext.getContentResolver().unregisterContentObserver(this.mAutoRotationObserver);
  }

  public void updateResources()
  {
    setText(2131165207);
  }

  private class AutoRotationObserver extends ContentObserver
  {
    public AutoRotationObserver()
    {
      super();
    }

    public void onChange(boolean paramBoolean)
    {
      AutoRotationSettingButton.this.updateStatus();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.AutoRotationSettingButton
 * JD-Core Version:    0.6.0
 */