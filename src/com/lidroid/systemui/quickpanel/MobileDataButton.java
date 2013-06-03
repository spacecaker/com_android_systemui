package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.provider.Settings.Secure;
import android.view.View;
import com.lidroid.util.SLog;

public class MobileDataButton extends PowerButton
{
  public static final String MOBILE_DATA_CHANGED = "com.android.internal.telephony.MOBILE_DATA_CHANGED";
  public static boolean STATE_CHANGE_REQUEST = false;
  private static final String TAG = "MobileDataButton";

  public MobileDataButton()
  {
    this.mType = "toggleMobileData";
  }

  private static boolean getDataRomingEnabled(Context paramContext)
  {
    if (Settings.Secure.getInt(paramContext.getContentResolver(), "data_roaming", 0) > 0);
    for (int i = 1; ; i = 0)
      return i;
  }

  private static boolean getDataState(Context paramContext)
  {
    return ((ConnectivityManager)paramContext.getSystemService("connectivity")).getMobileDataEnabled();
  }

  protected IntentFilter getBroadcastIntentFilter()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.ANY_DATA_STATE");
    return localIntentFilter;
  }

  protected int getText()
  {
    return 51052606;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.intent.action.MAIN");
    localIntent.setClassName("com.android.phone", "com.android.phone.Settings");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  public void networkModeChanged(Context paramContext, int paramInt)
  {
    if (STATE_CHANGE_REQUEST)
    {
      ((ConnectivityManager)paramContext.getSystemService("connectivity")).setMobileDataEnabled(true);
      STATE_CHANGE_REQUEST = false;
    }
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    boolean bool = getDataState(localContext);
    ConnectivityManager localConnectivityManager = (ConnectivityManager)localContext.getSystemService("connectivity");
    if (bool)
      localConnectivityManager.setMobileDataEnabled(false);
    while (true)
    {
      return;
      localConnectivityManager.setMobileDataEnabled(true);
    }
  }

  protected void updateState()
  {
    if (STATE_CHANGE_REQUEST)
    {
      this.mIcon = 50462837;
      this.mState = 5;
    }
    while (true)
    {
      return;
      if (getDataState(this.mView.getContext()))
      {
        this.mIcon = 50462837;
        this.mState = 1;
        SLog.d("MobileDataButton", "Data Connection: on");
        continue;
      }
      this.mIcon = 50462836;
      this.mState = 2;
      SLog.d("MobileDataButton", "Data Connection: off");
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.MobileDataButton
 * JD-Core Version:    0.6.0
 */