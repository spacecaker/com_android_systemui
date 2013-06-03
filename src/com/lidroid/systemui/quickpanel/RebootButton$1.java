package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;

class RebootButton$1
  implements DialogInterface.OnClickListener
{
  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    String[] arrayOfString = this.val$context.getResources().getStringArray(50659333);
    RebootButton.access$002(this.this$0, arrayOfString[paramInt]);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.RebootButton.1
 * JD-Core Version:    0.6.0
 */