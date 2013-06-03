package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class ShutdownButton$1
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Intent localIntent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
    localIntent.putExtra("android.intent.extra.KEY_CONFIRM", false);
    localIntent.setFlags(268435456);
    this.val$context.startActivity(localIntent);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.ShutdownButton.1
 * JD-Core Version:    0.6.0
 */