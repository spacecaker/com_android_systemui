package com.spacecaker;

import android.view.View;
import android.view.View.OnClickListener;

class ButtonBurst$1
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    if (ButtonBurst.access$0(this.this$0))
      this.this$0.sendBroadCast(true);
    while (true)
    {
      return;
      this.this$0.sendBroadCast(false);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.spacecaker.ButtonBurst.1
 * JD-Core Version:    0.6.0
 */