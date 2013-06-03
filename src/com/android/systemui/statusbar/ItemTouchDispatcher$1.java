package com.android.systemui.statusbar;

import android.content.Context;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

class ItemTouchDispatcher$1 extends GestureDetector.SimpleOnGestureListener
{
  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    int i = ViewConfiguration.get(this.val$context).getScaledTouchSlop();
    int j = (int)Math.abs(paramMotionEvent2.getX() - paramMotionEvent1.getX());
    int k = 0;
    boolean bool;
    if ((j > i) && (Math.abs(paramFloat1) > Math.abs(paramFloat2)))
    {
      LatestItemContainer localLatestItemContainer = ItemTouchDispatcher.access$000(this.this$0);
      if (paramFloat1 > 0.0F)
      {
        bool = true;
        localLatestItemContainer.finishSwipe(bool);
        k = 1;
      }
    }
    while (true)
    {
      ItemTouchDispatcher.access$002(this.this$0, null);
      return k;
      bool = false;
      break;
      ItemTouchDispatcher.access$000(this.this$0).stopSwipe();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.ItemTouchDispatcher.1
 * JD-Core Version:    0.6.0
 */