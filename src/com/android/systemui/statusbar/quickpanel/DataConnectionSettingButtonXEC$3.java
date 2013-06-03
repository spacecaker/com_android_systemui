package com.android.systemui.statusbar.quickpanel;

import android.os.Handler;
import android.telephony.TelephonyManager;
import android.util.Log;

class DataConnectionSettingButtonXEC$3
  implements Runnable
{
  public void run()
  {
    switch (this.this$0.getActivateStatus())
    {
    default:
      Log.e("DataConnectionSettingButtonXEC", "mHandleDataConnStateChangTimeOut -> mDataConnState is " + this.this$0.getActivateStatus());
    case 0:
    case 1:
    }
    while (true)
    {
      return;
      if (2 == DataConnectionSettingButtonXEC.access$000(this.this$0).getDataState())
      {
        this.this$0.setActivateStatus(1);
        DataConnectionSettingButtonXEC.access$200(this.this$0).removeCallbacks(DataConnectionSettingButtonXEC.access$100(this.this$0));
        this.this$0.updateIcons();
        continue;
      }
      this.this$0.setActivateStatus(0);
      DataConnectionSettingButtonXEC.access$200(this.this$0).removeCallbacks(DataConnectionSettingButtonXEC.access$100(this.this$0));
      this.this$0.updateIcons();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.DataConnectionSettingButtonXEC.3
 * JD-Core Version:    0.6.0
 */