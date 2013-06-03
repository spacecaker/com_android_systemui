package com.lidroid.systemui.quickpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.IPowerManager;
import android.os.IPowerManager.Stub;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings.System;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class BrightnessButton extends PowerButton
{
  private static final int AUTO_BACKLIGHT = -1;
  private static final int CM_MODE_AUTO_LOW_MAX = 2;
  private static final int CM_MODE_AUTO_MIN_DEF_MAX = 0;
  private static final int CM_MODE_AUTO_MIN_LOW_MID_HIGH_MAX = 1;
  private static final int CM_MODE_MIN_MAX = 3;
  private static final int DEFAULT_BACKLIGHT = 102;
  private static final int HIGH_BACKLIGHT = 191;
  private static final int LOW_BACKLIGHT = 63;
  private static final int MAX_BACKLIGHT = 255;
  private static final int MID_BACKLIGHT = 127;
  private static final int MIN_BACKLIGHT = 30;
  private static final List<Uri> OBSERVED_URIS;
  private static Boolean SUPPORTS_AUTO_BACKLIGHT = null;

  static
  {
    OBSERVED_URIS = new ArrayList();
    OBSERVED_URIS.add(Settings.System.getUriFor("screen_brightness"));
    OBSERVED_URIS.add(Settings.System.getUriFor("screen_brightness_mode"));
  }

  public BrightnessButton()
  {
    this.mType = "toggleBrightness";
  }

  private static int getBrightnessState(Context paramContext)
  {
    int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness", 0);
    int j = getCurrentCMMode(paramContext);
    int k;
    if (i < 63)
      k = 2;
    while (true)
    {
      return k;
      if (i < 102)
      {
        k = 2;
        continue;
      }
      if (i < 127)
      {
        if (j == 1)
        {
          k = 2;
          continue;
        }
        k = 4;
        continue;
      }
      if (i < 191)
      {
        if (j == 1)
        {
          k = 4;
          continue;
        }
        k = 3;
        continue;
      }
      if (i < 255)
      {
        k = 3;
        continue;
      }
      k = 1;
    }
  }

  private static int getCurrentCMMode(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "expanded_brightness_mode", 1);
  }

  private static int getMinBacklight(Context paramContext)
  {
    if (Settings.System.getInt(paramContext.getContentResolver(), "light_sensor_custom", 0) != 0);
    for (int i = Settings.System.getInt(paramContext.getContentResolver(), "light_screen_dim", 30); ; i = 30)
      return i;
  }

  private static int getNextBrightnessValue(Context paramContext)
  {
    int i = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness", 0);
    int j = getCurrentCMMode(paramContext);
    int k;
    if ((isAutomaticModeSupported(paramContext)) && (isBrightnessSetToAutomatic(paramContext)))
      if (j == 2)
        k = 63;
    while (true)
    {
      return k;
      k = getMinBacklight(paramContext);
      continue;
      if (i < 63)
      {
        if (j == 2)
        {
          k = 63;
          continue;
        }
        if (j == 3)
        {
          k = 255;
          continue;
        }
        k = 102;
        continue;
      }
      if (i < 102)
      {
        if (j == 0)
        {
          k = 102;
          continue;
        }
        if ((j == 2) || (j == 3))
        {
          k = 255;
          continue;
        }
        k = 127;
        continue;
      }
      if (i < 127)
      {
        if (j == 1)
        {
          k = 127;
          continue;
        }
        k = 255;
        continue;
      }
      if (i < 191)
      {
        if (j == 1)
        {
          k = 191;
          continue;
        }
        k = 255;
        continue;
      }
      if (i < 255)
      {
        k = 255;
        continue;
      }
      if ((isAutomaticModeSupported(paramContext)) && (j != 3))
      {
        k = -1;
        continue;
      }
      if (j == 2)
      {
        k = 63;
        continue;
      }
      k = getMinBacklight(paramContext);
    }
  }

  private static boolean isAutomaticModeSupported(Context paramContext)
  {
    if (SUPPORTS_AUTO_BACKLIGHT == null)
    {
      if (!paramContext.getResources().getBoolean(17629189))
        break label32;
      SUPPORTS_AUTO_BACKLIGHT = Boolean.valueOf(true);
    }
    while (true)
    {
      return SUPPORTS_AUTO_BACKLIGHT.booleanValue();
      label32: SUPPORTS_AUTO_BACKLIGHT = Boolean.valueOf(false);
    }
  }

  private static boolean isBrightnessSetToAutomatic(Context paramContext)
  {
    try
    {
      if (IPowerManager.Stub.asInterface(ServiceManager.getService("power")) != null)
      {
        int j = Settings.System.getInt(paramContext.getContentResolver(), "screen_brightness_mode");
        if (j == 1);
        for (i = 1; ; i = 0)
          return i;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        Log.d("PowerWidget", "getBrightnessMode: " + localException);
        int i = 0;
      }
    }
  }

  protected List<Uri> getObservedUris()
  {
    return OBSERVED_URIS;
  }

  protected int getText()
  {
    return 51052616;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.DISPLAY_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    try
    {
      IPowerManager localIPowerManager = IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
      if (localIPowerManager != null)
      {
        int i = getNextBrightnessValue(localContext);
        ContentResolver localContentResolver = localContext.getContentResolver();
        if (i == -1)
        {
          Settings.System.putInt(localContentResolver, "screen_brightness_mode", 1);
        }
        else
        {
          if (isAutomaticModeSupported(localContext))
            Settings.System.putInt(localContentResolver, "screen_brightness_mode", 0);
          localIPowerManager.setBacklightBrightness(i);
          Settings.System.putInt(localContentResolver, "screen_brightness", i);
        }
      }
    }
    catch (RemoteException localRemoteException)
    {
      Log.d("PowerWidget", "toggleBrightness: " + localRemoteException);
    }
  }

  protected void updateState()
  {
    Context localContext = this.mView.getContext();
    if (isBrightnessSetToAutomatic(localContext))
    {
      this.mIcon = 50462832;
      this.mState = 1;
    }
    while (true)
    {
      return;
      switch (getBrightnessState(localContext))
      {
      case 2:
      default:
        this.mIcon = 50462834;
        this.mState = 2;
        break;
      case 1:
        this.mIcon = 50462835;
        this.mState = 1;
        break;
      case 3:
        this.mIcon = 50462835;
        this.mState = 5;
        break;
      case 4:
        this.mIcon = 50462833;
        this.mState = 5;
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.BrightnessButton
 * JD-Core Version:    0.6.0
 */