package com.spacecaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

class SpaceLayOut$1 extends BroadcastReceiver
{
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals(SpaceLayOut.access$0(this.this$0)))
    {
      SpaceLayOut.access$1(this.this$0, paramIntent.getBooleanExtra("LEOT_YANG_MANA_NIH", true));
      SpaceLayOut.access$3(this.this$0, SpaceLayOut.access$2(this.this$0));
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.spacecaker.SpaceLayOut.1
 * JD-Core Version:    0.6.0
 */