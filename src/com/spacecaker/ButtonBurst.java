package com.spacecaker;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;

public class ButtonBurst extends Button
{
  private Context mContext;
  private boolean mUpdating = false;

  public ButtonBurst(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    update();
  }

  private void update()
  {
    setBackgroundColor(16777215);
    Drawable localDrawable1 = this.mContext.getResources().getDrawable(2130837704);
    Drawable localDrawable2 = this.mContext.getResources().getDrawable(2130837703);
    if (!this.mUpdating)
      setBackgroundDrawable(localDrawable1);
    while (true)
    {
      setOnClickListener(new ButtonBurst.1(this));
      return;
      setBackgroundDrawable(localDrawable2);
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    update();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }

  void sendBroadCast(boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.androidminang.UPDATE");
    localIntent.putExtra("LEOT_YANG_MANA_NIH", paramBoolean);
    this.mContext.sendBroadcast(localIntent);
    if (paramBoolean);
    for (boolean bool = false; ; bool = true)
    {
      this.mUpdating = bool;
      update();
      return;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.spacecaker.ButtonBurst
 * JD-Core Version:    0.6.0
 */