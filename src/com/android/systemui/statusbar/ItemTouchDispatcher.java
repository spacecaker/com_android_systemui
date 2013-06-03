package com.android.systemui.statusbar;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class ItemTouchDispatcher
{
  static final boolean DBG = false;
  private static final String TAG = "NotificationTouchDispatcher";
  private final GestureDetector mGestureDetector;
  private LatestItemContainer mItem;
  private int[] mItemLocation = new int[2];

  public ItemTouchDispatcher(Context paramContext)
  {
    this.mGestureDetector = new GestureDetector(paramContext, new ItemTouchDispatcher.1(this, paramContext));
  }

  public boolean handleTouchEvent(MotionEvent paramMotionEvent)
  {
    MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
    localMotionEvent.setLocation(paramMotionEvent.getRawX(), paramMotionEvent.getRawY());
    boolean bool = this.mGestureDetector.onTouchEvent(localMotionEvent);
    if (this.mItem != null)
    {
      this.mItem.getLocationOnScreen(this.mItemLocation);
      localMotionEvent.offsetLocation(this.mItemLocation[0], this.mItemLocation[1]);
      this.mItem.dispatchTouchEvent(localMotionEvent);
      switch (0xFF & localMotionEvent.getAction())
      {
      case 2:
      default:
      case 1:
      case 3:
      }
    }
    while (true)
    {
      localMotionEvent.recycle();
      return bool;
      this.mItem.stopSwipe();
      this.mItem = null;
      bool = true;
    }
  }

  public boolean needsInterceptTouch(MotionEvent paramMotionEvent)
  {
    if (((0xFF & paramMotionEvent.getAction()) == 0) && (this.mItem != null))
    {
      Log.w("NotificationTouchDispatcher", "Clearing stale item " + this.mItem);
      this.mItem.stopSwipe();
      this.mItem = null;
    }
    if (this.mItem != null)
      this.mItem.setEventsControlledByDispatcher();
    for (int i = 1; ; i = 0)
      return i;
  }

  public void releaseItem(LatestItemContainer paramLatestItemContainer)
  {
    if (paramLatestItemContainer == this.mItem)
      this.mItem = null;
  }

  public void setItem(LatestItemContainer paramLatestItemContainer)
  {
    this.mItem = paramLatestItemContainer;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.ItemTouchDispatcher
 * JD-Core Version:    0.6.0
 */