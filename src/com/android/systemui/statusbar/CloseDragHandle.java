package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class CloseDragHandle extends LinearLayout
{
  StatusBarService mService;

  public CloseDragHandle(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mService.interceptTouchEvent(paramMotionEvent));
    for (boolean bool = true; ; bool = super.onInterceptTouchEvent(paramMotionEvent))
      return bool;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() != 0)
      this.mService.interceptTouchEvent(paramMotionEvent);
    return true;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.CloseDragHandle
 * JD-Core Version:    0.6.0
 */