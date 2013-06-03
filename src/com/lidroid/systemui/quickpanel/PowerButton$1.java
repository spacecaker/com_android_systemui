package com.lidroid.systemui.quickpanel;

import android.os.Handler;
import android.os.Message;

class PowerButton$1 extends Handler
{
  public void handleMessage(Message paramMessage)
  {
    if (this.this$0.mView != null)
    {
      PowerButton.access$000(this.this$0, 50724884, this.this$0.mIcon);
      this.this$0.updateText();
      switch (this.this$0.mState)
      {
      default:
        PowerButton.access$000(this.this$0, 50724886, 50462827);
      case 1:
      case 2:
      }
    }
    while (true)
    {
      return;
      PowerButton.access$000(this.this$0, 50724886, 50462829);
      continue;
      PowerButton.access$000(this.this$0, 50724886, 50462828);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.PowerButton.1
 * JD-Core Version:    0.6.0
 */