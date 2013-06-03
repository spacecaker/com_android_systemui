package com.spacecaker;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.System;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

class SlideBrightness$2
  implements SeekBar.OnSeekBarChangeListener
{
  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    SlideBrightness.access$1(this.this$0, paramInt / 255.0F);
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    int i = (int)(255.0F * SlideBrightness.access$2(this.this$0));
    Settings.System.putInt(SlideBrightness.access$3(this.this$0).getContentResolver(), "screen_brightness", i);
    try
    {
      Intent localIntent = new Intent("android.intent.action.MAIN");
      localIntent.addFlags(268435456);
      localIntent.setClassName("in.jmkl.dcsms.statusbargreper", "in.jmkl.dcsms.statusbargreper.Dummy");
      SlideBrightness.access$3(this.this$0).startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        Log.e("SLIDERBRIGThEWkekSkSkS", "error launch dummy");
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.spacecaker.SlideBrightness.2
 * JD-Core Version:    0.6.0
 */