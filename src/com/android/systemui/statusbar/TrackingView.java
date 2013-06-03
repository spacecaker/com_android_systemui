package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Display;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.LinearLayout;

public class TrackingView extends LinearLayout
{
  final Display mDisplay;
  StatusBarService mService;

  public TrackingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    int i;
    if (paramKeyEvent.getAction() == 0)
    {
      i = 1;
      switch (paramKeyEvent.getKeyCode())
      {
      default:
      case 4:
      }
    }
    for (boolean bool = super.dispatchKeyEvent(paramKeyEvent); ; bool = true)
    {
      return bool;
      i = 0;
      break;
      if (i == 0)
        continue;
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mService.onTrackingViewAttached();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    this.mService.updateExpandedHeight();
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.TrackingView
 * JD-Core Version:    0.6.0
 */