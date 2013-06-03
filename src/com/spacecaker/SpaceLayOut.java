package com.spacecaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class SpaceLayOut extends LinearLayout
{
  private boolean LAYOUT = false;
  private String UPDATE = "com.androidminang.UPDATE";
  private Context mContext;
  private BroadcastReceiver mIntentReceiver = new SpaceLayOut.1(this);
  private boolean mUpdating = false;

  public SpaceLayOut(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
  }

  private void hide(View paramView)
  {
    paramView.setVisibility(8);
  }

  private void show(View paramView)
  {
    paramView.setVisibility(0);
  }

  private void update(boolean paramBoolean)
  {
    View localView1 = getChildAt(0);
    View localView2 = getChildAt(1);
    if (paramBoolean)
    {
      show(localView1);
      hide(localView2);
    }
    while (true)
    {
      return;
      show(localView2);
      hide(localView1);
    }
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    setUpdates(true);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    setUpdates(false);
  }

  void setUpdates(boolean paramBoolean)
  {
    if (paramBoolean != this.mUpdating)
    {
      this.mUpdating = paramBoolean;
      if (!paramBoolean)
        break label57;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction(this.UPDATE);
      this.mContext.registerReceiver(this.mIntentReceiver, localIntentFilter, null, null);
      update(this.LAYOUT);
    }
    while (true)
    {
      return;
      label57: this.mContext.unregisterReceiver(this.mIntentReceiver);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.spacecaker.SpaceLayOut
 * JD-Core Version:    0.6.0
 */