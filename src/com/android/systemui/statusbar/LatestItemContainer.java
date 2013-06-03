package com.android.systemui.statusbar;

import android.content.Context;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewConfiguration;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class LatestItemContainer extends LinearLayout
{
  private static final boolean DBG = false;
  private static final String TAG = "NotificationContainer";
  private ItemTouchDispatcher mDispatcher = null;
  private boolean mEventsControlledByDispatcher = false;
  private final Handler mHandler = new Handler();
  private final Point mStartPoint = new Point();
  private Runnable mSwipeCallback = null;
  private int mTouchSlop;

  public LatestItemContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mTouchSlop = ViewConfiguration.get(paramContext).getScaledTouchSlop();
  }

  private void reset()
  {
    scrollTo(0, 0);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    boolean bool = false;
    if (this.mDispatcher != null)
    {
      if (!this.mEventsControlledByDispatcher)
        bool = this.mDispatcher.handleTouchEvent(paramMotionEvent);
      switch (0xFF & paramMotionEvent.getAction())
      {
      default:
      case 0:
      case 2:
      case 1:
      case 3:
      }
    }
    while (true)
    {
      if (super.dispatchTouchEvent(paramMotionEvent))
        bool = true;
      return bool;
      this.mStartPoint.set((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      bool = true;
      continue;
      int i = (int)paramMotionEvent.getX() - this.mStartPoint.x;
      int j = (int)paramMotionEvent.getY() - this.mStartPoint.y;
      if ((Math.abs(i) > this.mTouchSlop) && (Math.abs(i) > Math.abs(j)))
        this.mDispatcher.setItem(this);
      scrollTo(-i, 0);
      bool = true;
      continue;
      if (!bool)
        reset();
      if (!this.mEventsControlledByDispatcher)
        this.mDispatcher.releaseItem(this);
      bool = true;
      continue;
      if (!this.mEventsControlledByDispatcher)
      {
        this.mDispatcher.releaseItem(this);
        reset();
      }
      bool = true;
    }
  }

  public void finishSwipe(boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 2131361793; ; i = 2131361792)
    {
      Animation localAnimation = AnimationUtils.loadAnimation(getContext(), i);
      startAnimation(localAnimation);
      this.mHandler.postDelayed(this.mSwipeCallback, localAnimation.getDuration());
      this.mEventsControlledByDispatcher = false;
      return;
    }
  }

  public void setEventsControlledByDispatcher()
  {
    this.mEventsControlledByDispatcher = true;
  }

  public void setOnSwipeCallback(ItemTouchDispatcher paramItemTouchDispatcher, Runnable paramRunnable)
  {
    this.mDispatcher = paramItemTouchDispatcher;
    this.mSwipeCallback = paramRunnable;
  }

  public void stopSwipe()
  {
    reset();
    this.mEventsControlledByDispatcher = false;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.LatestItemContainer
 * JD-Core Version:    0.6.0
 */