package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public abstract class QuickSettingButton extends TextView
  implements View.OnClickListener
{
  private int mActivateStatus;
  private BroadcastReceiver mIntentReceiver = new QuickSettingButton.1(this);
  private View mRootView = null;

  public QuickSettingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  abstract void activate();

  abstract void deactivate();

  protected int getActivateStatus()
  {
    return this.mActivateStatus;
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("QuickSettingButton", "onAttachedToWindow()");
    this.mRootView = getRootView();
    setOnClickListener(this);
    this.mContext.registerReceiver(this.mIntentReceiver, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"), null, null);
  }

  public void onClick(View paramView)
  {
    setEnabled(false);
    if (1 == this.mActivateStatus)
      deactivate();
    while (true)
    {
      setEnabled(true);
      return;
      if (this.mActivateStatus != 0)
        continue;
      activate();
    }
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("QuickSettingButton", "onDetachedFromWindow()");
  }

  protected void setActivateStatus(int paramInt)
  {
    this.mActivateStatus = paramInt;
  }

  abstract void updateResources();
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.QuickSettingButton
 * JD-Core Version:    0.6.0
 */