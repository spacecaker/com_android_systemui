package com.android.systemui.statusbar;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class StatusBarServiceHider extends LinearLayout
  implements View.OnTouchListener
{
  private boolean LAYOUT = false;
  private String UPDATE = "com.androidminang.UPDATE";
  private Context mContext;
  private boolean mUpdating = false;

  public StatusBarServiceHider(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    setOnTouchListener(this);
  }

  public int getOrientation()
  {
    if (getResources().getDisplayMetrics().widthPixels > getResources().getDisplayMetrics().heightPixels);
    for (int i = 1; ; i = 2)
      return i;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int i = getWidth() / 2;
    float f = paramMotionEvent.getX();
    paramMotionEvent.getY();
    if (f >= i)
      sendBroadCast(true);
    while (true)
    {
      return true;
      sendBroadCast(false);
    }
  }

  void sendBroadCast(boolean paramBoolean)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.androidminang.UPDATE");
    localIntent.putExtra("LEOT_YANG_MANA_NIH", paramBoolean);
    this.mContext.sendBroadcast(localIntent);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.StatusBarServiceHider
 * JD-Core Version:    0.6.0
 */