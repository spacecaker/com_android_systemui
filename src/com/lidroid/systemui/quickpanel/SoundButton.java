package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Vibrator;
import android.provider.Settings.System;
import android.view.View;

public class SoundButton extends PowerButton
{
  public static AudioManager AUDIO_MANAGER = null;
  public static final int CM_MODE_SOUNDVIB_SOUND_VIB_SILENT = 5;
  public static final int CM_MODE_SOUNDVIB_VIB = 0;
  public static final int CM_MODE_SOUNDVIB_VIB_SILENT = 3;
  public static final int CM_MODE_SOUND_SILENT = 2;
  public static final int CM_MODE_SOUND_VIB = 1;
  public static final int CM_MODE_SOUND_VIB_SILENT = 4;
  public static final int CM_MODE_VIB_SILENT = 6;
  public static final int RINGER_MODE_SILENT = 1;
  public static final int RINGER_MODE_SOUND_AND_VIBRATE = 4;
  public static final int RINGER_MODE_SOUND_ONLY = 3;
  public static final int RINGER_MODE_UNKNOWN = 0;
  public static final int RINGER_MODE_VIBRATE_ONLY = 2;
  public static final int VIBRATE_DURATION = 500;
  public static Vibrator VIBRATOR = null;

  public SoundButton()
  {
    this.mType = "toggleSound";
  }

  private static int getCurrentCMMode(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "expanded_ring_mode", 5);
  }

  private static int getSoundState(Context paramContext)
  {
    initServices(paramContext);
    int i = AUDIO_MANAGER.getRingerMode();
    int j = AUDIO_MANAGER.getVibrateSetting(0);
    int k;
    if ((i == 2) && (j == 1))
      k = 4;
    while (true)
    {
      return k;
      if (i == 2)
      {
        k = 3;
        continue;
      }
      if (i == 1)
      {
        k = 2;
        continue;
      }
      if (i == 0)
      {
        k = 1;
        continue;
      }
      k = 0;
    }
  }

  private static boolean hapticFeedbackEnabled(Context paramContext)
  {
    int i = Settings.System.getInt(paramContext.getContentResolver(), "expanded_haptic_feedback", 2);
    if (i == 2)
    {
      if (Settings.System.getInt(paramContext.getContentResolver(), "haptic_feedback_enabled", 1) == 1);
      for (j = 1; ; j = 0)
        return j;
    }
    if (i == 1);
    for (int j = 1; ; j = 0)
      break;
  }

  private static void initServices(Context paramContext)
  {
    if (AUDIO_MANAGER == null)
      AUDIO_MANAGER = (AudioManager)paramContext.getSystemService("audio");
    if (VIBRATOR == null)
      VIBRATOR = (Vibrator)paramContext.getSystemService("vibrator");
  }

  private boolean supports(int paramInt)
  {
    int i = getCurrentCMMode(this.mView.getContext());
    int j;
    switch (paramInt)
    {
    default:
      j = 0;
    case 1:
    case 2:
    case 3:
    case 4:
    }
    while (true)
    {
      return j;
      if ((i != 2) && (i != 3) && (i != 4) && (i != 5) && (i != 6))
        break;
      j = 1;
      continue;
      if ((i != 1) && (i != 0) && (i != 3) && (i != 4) && (i != 5) && (i != 6))
        break;
      j = 1;
      continue;
      if ((i != 1) && (i != 2) && (i != 4) && (i != 5))
        break;
      j = 1;
      continue;
      if ((i != 0) && (i != 3) && (i != 5))
        break;
      j = 1;
    }
  }

  private static void updateSettings(Context paramContext, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Settings.System.putInt(paramContext.getContentResolver(), "vibrate_in_silent", paramInt1);
    AUDIO_MANAGER.setVibrateSetting(0, paramInt2);
    AUDIO_MANAGER.setRingerMode(paramInt3);
    if ((paramBoolean) && (hapticFeedbackEnabled(paramContext)))
      VIBRATOR.vibrate(500L);
  }

  protected IntentFilter getBroadcastIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.media.RINGER_MODE_CHANGED");
    localIntentFilter.addAction("android.media.VIBRATE_SETTING_CHANGED");
    return localIntentFilter;
  }

  protected int getText()
  {
    int i;
    switch (getSoundState(this.mView.getContext()))
    {
    default:
      i = 51052608;
    case 4:
    case 3:
    case 2:
    case 1:
    }
    while (true)
    {
      return i;
      i = 51052609;
      continue;
      i = 51052608;
      continue;
      i = 51052611;
      continue;
      i = 51052610;
    }
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.SOUND_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    int i = getSoundState(localContext);
    initServices(localContext);
    switch (i)
    {
    default:
      updateSettings(localContext, 1, 1, 2, true);
    case 4:
    case 3:
    case 2:
    case 1:
    }
    while (true)
    {
      return;
      if (supports(3))
      {
        updateSettings(localContext, 1, 2, 2, false);
        continue;
      }
      if (supports(2))
      {
        updateSettings(localContext, 1, 1, 1, true);
        continue;
      }
      if (supports(1))
      {
        updateSettings(localContext, 0, 0, 0, false);
        continue;
      }
      updateSettings(localContext, 1, 1, 1, true);
      continue;
      if (supports(2))
      {
        updateSettings(localContext, 1, 2, 1, true);
        continue;
      }
      if (supports(1))
      {
        updateSettings(localContext, 0, 0, 0, false);
        continue;
      }
      if (supports(4))
      {
        updateSettings(localContext, 1, 1, 2, true);
        continue;
      }
      updateSettings(localContext, 1, 1, 1, true);
      continue;
      if (supports(1))
      {
        updateSettings(localContext, 0, 0, 0, false);
        continue;
      }
      if (supports(4))
      {
        updateSettings(localContext, 1, 1, 2, true);
        continue;
      }
      if (supports(3))
      {
        updateSettings(localContext, 1, 2, 2, false);
        continue;
      }
      updateSettings(localContext, 1, 1, 2, true);
      continue;
      if (supports(4))
      {
        updateSettings(localContext, 1, 1, 2, true);
        continue;
      }
      if (supports(3))
      {
        updateSettings(localContext, 1, 2, 2, false);
        continue;
      }
      if (supports(2))
      {
        updateSettings(localContext, 1, 2, 1, true);
        continue;
      }
      updateSettings(localContext, 1, 1, 2, true);
    }
  }

  protected void updateState()
  {
    switch (getSoundState(this.mView.getContext()))
    {
    default:
    case 4:
    case 3:
    case 2:
    case 1:
    }
    while (true)
    {
      return;
      this.mIcon = 50462850;
      this.mState = 1;
      continue;
      this.mIcon = 50462849;
      this.mState = 1;
      continue;
      this.mIcon = 50462861;
      this.mState = 2;
      continue;
      this.mIcon = 50462858;
      this.mState = 2;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.SoundButton
 * JD-Core Version:    0.6.0
 */