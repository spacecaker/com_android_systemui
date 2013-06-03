package com.android.systemui.statusbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.widget.TextView;

public class CarrierLabel extends TextView
{
  private boolean mAttached;
  private final BroadcastReceiver mIntentReceiver = new CarrierLabel.1(this);

  public CarrierLabel(Context paramContext)
  {
    this(paramContext, null);
  }

  public CarrierLabel(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public CarrierLabel(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    updateNetworkName(false, null, false, null);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    if (!this.mAttached)
    {
      this.mAttached = true;
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.provider.Telephony.SPN_STRINGS_UPDATED");
      getContext().registerReceiver(this.mIntentReceiver, localIntentFilter, null, getHandler());
    }
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAttached)
    {
      getContext().unregisterReceiver(this.mIntentReceiver);
      this.mAttached = false;
    }
  }

  void updateNetworkName(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if ((paramBoolean2) && (paramString2 != null))
    {
      localStringBuilder.append(paramString2);
      i = 1;
    }
    if ((paramBoolean1) && (paramString1 != null))
    {
      if (i != 0)
        localStringBuilder.append('\n');
      localStringBuilder.append(paramString1);
      i = 1;
    }
    if (i != 0)
      setText(localStringBuilder.toString());
    while (true)
    {
      return;
      setText(17040141);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.CarrierLabel
 * JD-Core Version:    0.6.0
 */