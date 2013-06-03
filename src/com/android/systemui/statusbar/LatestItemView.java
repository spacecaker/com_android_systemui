package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

public class LatestItemView extends FrameLayout
{
  public LatestItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return onTouchEvent(paramMotionEvent);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      for (boolean bool = super.onTouchEvent(paramMotionEvent); ; bool = true)
      {
        return bool;
        setSelected(true);
        break;
        if (!isSelected())
          break;
        setSelected(false);
        performClick();
      }
      if ((isPressed()) || (!isSelected()))
        continue;
      setSelected(false);
      continue;
      if (!isSelected())
        continue;
      setSelected(false);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.LatestItemView
 * JD-Core Version:    0.6.0
 */