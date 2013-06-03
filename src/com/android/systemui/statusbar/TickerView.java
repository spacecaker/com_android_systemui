package com.android.systemui.statusbar;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextSwitcher;

public class TickerView extends TextSwitcher
{
  Ticker mTicker;

  public TickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mTicker.reflowText();
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.TickerView
 * JD-Core Version:    0.6.0
 */