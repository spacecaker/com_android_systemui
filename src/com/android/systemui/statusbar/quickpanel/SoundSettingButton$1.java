package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

class SoundSettingButton$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("android.media.RINGER_MODE_CHANGED".equals(str))
    {
      SoundSettingButton.access$002(paramIntent.getIntExtra("android.media.EXTRA_RINGER_MODE", 2));
      Log.e("SoundSettingButton", "onReceive()-S:" + SoundSettingButton.access$000());
    }
    while (true)
    {
      SoundSettingButton.access$200(this.this$0);
      return;
      if ((!"android.media.VIBRATE_SETTING_CHANGED".equals(str)) || (paramIntent.getIntExtra("android.media.EXTRA_VIBRATE_TYPE", 0) != 0))
        continue;
      SoundSettingButton.access$102(paramIntent.getIntExtra("android.media.EXTRA_VIBRATE_SETTING", 2));
      Log.e("SoundSettingButton", "onReceive()-V:" + SoundSettingButton.access$100());
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.SoundSettingButton.1
 * JD-Core Version:    0.6.0
 */