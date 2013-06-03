package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;

public class StatusBarView extends FrameLayout
{
  FixedSizeDrawable mBackground;
  View mDate;
  ViewGroup mNotificationIcons;
  StatusBarService mService;
  ViewGroup mStatusIcons;

  public StatusBarView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private int getDateSize(ViewGroup paramViewGroup, int paramInt1, int paramInt2)
  {
    int i = paramViewGroup.getChildCount();
    int j = 0;
    int n;
    if (j < i)
    {
      View localView = paramViewGroup.getChildAt(j);
      int m = paramInt2 + localView.getLeft();
      n = paramInt2 + localView.getRight();
      if ((paramInt1 < m) || (paramInt1 > n));
    }
    for (int k = n; ; k = -1)
    {
      return k;
      j++;
      break;
    }
  }

  private int getViewOffset(View paramView)
  {
    int i = 0;
    while (paramView != this)
    {
      i += paramView.getLeft();
      ViewParent localViewParent = paramView.getParent();
      if ((paramView instanceof View))
      {
        paramView = (View)localViewParent;
        continue;
      }
      throw new RuntimeException(paramView + " is not a child of " + this);
    }
    return i;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mService.onBarViewAttached();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.mNotificationIcons = ((ViewGroup)findViewById(2131296276));
    this.mStatusIcons = ((ViewGroup)findViewById(2131296277));
    this.mDate = findViewById(2131296281);
    this.mBackground = new FixedSizeDrawable(this.mDate.getBackground());
    this.mBackground.setFixedBounds(0, 0, 0, 0);
    this.mDate.setBackgroundDrawable(this.mBackground);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.mService.interceptTouchEvent(paramMotionEvent));
    for (boolean bool = true; ; bool = super.onInterceptTouchEvent(paramMotionEvent))
      return bool;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = this.mDate.getRight();
    int j = getDateSize(this.mNotificationIcons, i, getViewOffset(this.mNotificationIcons));
    int m;
    if (j < 0)
    {
      m = getViewOffset(this.mStatusIcons);
      if (i >= m)
        break label148;
      j = i;
    }
    while (true)
    {
      int k = paramInt3 - getPaddingRight();
      if (j > k)
        j = k;
      this.mDate.layout(this.mDate.getLeft(), this.mDate.getTop(), j, this.mDate.getBottom());
      this.mBackground.setFixedBounds(-this.mDate.getLeft(), -this.mDate.getTop(), paramInt3 - paramInt1, paramInt4 - paramInt2);
      return;
      label148: j = getDateSize(this.mStatusIcons, i, m);
      if (j >= 0)
        continue;
      j = paramInt3;
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mService.updateExpandedViewPos(-10000);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() != 0)
      this.mService.interceptTouchEvent(paramMotionEvent);
    return true;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.StatusBarView
 * JD-Core Version:    0.6.0
 */