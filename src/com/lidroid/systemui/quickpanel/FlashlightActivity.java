package com.lidroid.systemui.quickpanel;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.System;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class FlashlightActivity extends Activity
{
  private static final int SCREEN_TIMEOUT_MAX = 600000;
  private int mOrigScreenTimeout;
  private PowerManager.WakeLock mWakeLock;

  public void onPause()
  {
    super.onPause();
    this.mWakeLock.release();
    Settings.System.putInt(getContentResolver(), "screen_off_timeout", this.mOrigScreenTimeout);
    finish();
  }

  public void onResume()
  {
    super.onResume();
    WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
    localLayoutParams.screenBrightness = 1.0F;
    getWindow().setAttributes(localLayoutParams);
    getWindow().setBackgroundDrawable(new ColorDrawable(-1));
    this.mWakeLock = ((PowerManager)getSystemService("power")).newWakeLock(6, "Flashlight");
    this.mWakeLock.acquire();
    this.mOrigScreenTimeout = Settings.System.getInt(getContentResolver(), "screen_off_timeout", 0);
    Settings.System.putInt(getContentResolver(), "screen_off_timeout", 600000);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.FlashlightActivity
 * JD-Core Version:    0.6.0
 */