package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.provider.Settings.System;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public final class SoundSettingButton extends QuickSettingButton
{
  private static int mSoundProfile;
  private static int mSoundText;
  private static int mVibProfile;
  private AudioManager mAudioManager = null;
  private BroadcastReceiver mIntentReceiver = new SoundSettingButton.1(this);

  public SoundSettingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void updateIconsAndText()
  {
    int i = 0;
    int j = 0;
    int k = -1;
    ImageView localImageView = (ImageView)getRootView().findViewById(2131296269);
    switch (getActivateStatus())
    {
    default:
    case 1:
      while (true)
      {
        localImageView.setImageResource(i);
        setText(j);
        mSoundText = j;
        setTextColor(k);
        return;
        if (1 == mVibProfile)
        {
          i = 2130837526;
          j = 2131165204;
          continue;
        }
        i = 2130837525;
        j = 2131165203;
      }
    case 0:
    }
    if (mSoundProfile == 0)
    {
      i = 2130837524;
      j = 2131165205;
    }
    while (true)
    {
      k = -3355444;
      break;
      if (1 != mSoundProfile)
        continue;
      i = 2130837527;
      j = 2131165206;
    }
  }

  private void updateStatus()
  {
    if (2 == mSoundProfile)
      setActivateStatus(1);
    while (true)
    {
      updateIconsAndText();
      return;
      setActivateStatus(0);
    }
  }

  public void activate()
  {
    Log.e("SoundSettingButton", "activate()");
    this.mAudioManager.setRingerMode(2);
  }

  public void deactivate()
  {
    int i = 0;
    switch (mVibProfile)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      Log.e("SoundSettingButton", "deactivate()-S:" + i + " V:" + mVibProfile);
      this.mAudioManager.setRingerMode(i);
      return;
      i = 0;
      continue;
      if (Settings.System.getInt(this.mContext.getContentResolver(), "vibrate_in_silent", 0) == 1)
      {
        i = 1;
        continue;
      }
      i = 0;
      continue;
      i = 1;
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("SoundSettingButton", "onAttachedToWindow()");
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.media.RINGER_MODE_CHANGED");
    localIntentFilter.addAction("android.media.VIBRATE_SETTING_CHANGED");
    this.mContext.registerReceiver(this.mIntentReceiver, localIntentFilter, null, null);
    this.mAudioManager = ((AudioManager)this.mContext.getSystemService("audio"));
    if (this.mAudioManager != null)
    {
      mSoundProfile = this.mAudioManager.getRingerMode();
      mVibProfile = this.mAudioManager.getVibrateSetting(0);
      updateStatus();
    }
    while (true)
    {
      return;
      Log.e("SoundSettingButton", "mAudioManager is null");
    }
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("SoundSettingButton", "onDetachedFromWindow()");
    this.mContext.unregisterReceiver(this.mIntentReceiver);
  }

  public void updateResources()
  {
    setText(mSoundText);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.SoundSettingButton
 * JD-Core Version:    0.6.0
 */