package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class ExpandedView extends LinearLayout
{
  int mPrevHeight = -1;
  StatusBarService mService;
  ItemTouchDispatcher mTouchDispatcher;

  public ExpandedView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public int getSuggestedMinimumHeight()
  {
    return 0;
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mTouchDispatcher.needsInterceptTouch(paramMotionEvent));
    for (boolean bool = true; ; bool = super.onInterceptTouchEvent(paramMotionEvent))
      return bool;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = paramInt4 - paramInt2;
    if (i != this.mPrevHeight)
    {
      this.mPrevHeight = i;
      this.mService.updateExpandedViewPos(-10000);
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = this.mTouchDispatcher.handleTouchEvent(paramMotionEvent);
    if (super.onTouchEvent(paramMotionEvent))
      bool = true;
    return bool;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.ExpandedView
 * JD-Core Version:    0.6.0
 */