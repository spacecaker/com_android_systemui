package com.android.systemui.statusbar.quickpanel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public final class DataConnectionSettingButtonXEC extends QuickSettingButton
{
  private ConnectivityManager mConnectivityManager;
  private Handler mDataConnStateChangeHandler = null;
  private Runnable mHandleDataConnStateChangTimeOut = new DataConnectionSettingButtonXEC.3(this);
  private BroadcastReceiver mIntentReceiver = new DataConnectionSettingButtonXEC.1(this);
  private Intent mNetworkModeChangedInitIntent;
  PhoneStateListener mPhoneStateListener = new DataConnectionSettingButtonXEC.2(this);
  private TelephonyManager mTelephonyManager;

  public DataConnectionSettingButtonXEC(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void activate()
  {
    Log.v("DataConnectionSettingButtonXEC", "activate()");
    this.mConnectivityManager.setMobileDataEnabled(true);
    Intent localIntent = new Intent("android.intent.action.NETWORK_MODE");
    localIntent.putExtra("state", true);
    this.mContext.sendBroadcast(localIntent);
  }

  public void deactivate()
  {
    Log.v("DataConnectionSettingButtonXEC", "deactivate()");
    this.mConnectivityManager.setMobileDataEnabled(false);
    Intent localIntent = new Intent("android.intent.action.NETWORK_MODE");
    localIntent.putExtra("state", false);
    this.mContext.sendBroadcast(localIntent);
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.v("DataConnectionSettingButtonXEC", "onAttachedToWindow() - entered");
    Log.v("DataConnectionSettingButtonXEC", "OnAttachedToWindow - simcard is present");
    IntentFilter localIntentFilter = new IntentFilter("android.intent.action.NETWORK_MODE");
    localIntentFilter.addAction("android.intent.action.NETWORK_MODE_INITIATE_CHANGE");
    this.mContext.registerReceiver(this.mIntentReceiver, localIntentFilter);
    this.mConnectivityManager = ((ConnectivityManager)this.mContext.getSystemService("connectivity"));
    this.mTelephonyManager = ((TelephonyManager)this.mContext.getSystemService("phone"));
    setActivateStatus(0);
    if (this.mConnectivityManager.getMobileDataEnabled())
      setActivateStatus(1);
    Log.v("DataConnectionSettingButtonXEC", "OnAttachedWindow, calling updateIcons and updateResources");
    updateIcons();
    updateResources();
    this.mNetworkModeChangedInitIntent = new Intent("android.intent.action.NETWORK_MODE_INITIATE_CHANGE");
    this.mNetworkModeChangedInitIntent.putExtra("state", false);
    this.mTelephonyManager.listen(this.mPhoneStateListener, 64);
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("DataConnectionSettingButtonXEC", "onDetachedFromWindow()");
  }

  public void updateIcons()
  {
    Log.e("DataConnectionSettingButtonXEC", "updateIcons( " + this.mConnectivityManager.getMobileDataEnabled() + " data state " + this.mTelephonyManager.getDataState());
    if (1 == this.mTelephonyManager.getSimState())
    {
      Log.v("DataConnectionSettingButtonXEC", "updateIcons - no simcard, returning");
      setActivateStatus(0);
      return;
    }
    Log.v("DataConnectionSettingButtonXEC", "updateIcons - simcard is present");
    int i = 0;
    ImageView localImageView = (ImageView)getRootView().findViewById(2131296273);
    switch (getActivateStatus())
    {
    default:
    case 1:
    case 0:
    }
    while (true)
    {
      localImageView.setImageResource(i);
      break;
      i = 2130837519;
      continue;
      i = 2130837518;
    }
  }

  public void updateResources()
  {
    Log.e("DataConnectionSettingButtonXEC", "updateResources");
    setText(2131165217);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.quickpanel.DataConnectionSettingButtonXEC
 * JD-Core Version:    0.6.0
 */