package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.PowerManager;

class RebootButton$2
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    ((PowerManager)this.val$context.getSystemService("power")).reboot(RebootButton.access$000(this.this$0));
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.RebootButton.2
 * JD-Core Version:    0.6.0
 */