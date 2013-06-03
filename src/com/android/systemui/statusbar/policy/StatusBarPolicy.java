package com.android.systemui.statusbar.policy;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.StatusBarManager;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IPowerManager;
import android.os.IPowerManager.Stub;
import android.os.Message;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.os.storage.StorageManager;
import android.provider.Settings.System;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Slog;
import android.view.View;
import android.view.Window;
import android.view.WindowManagerImpl;
import android.widget.TextView;
import com.android.internal.app.IBatteryStats;
import com.android.internal.telephony.IccCard.State;
import com.android.server.am.BatteryStatsService;
import com.android.systemui.usb.StorageNotification;
import java.util.Set;

public class StatusBarPolicy
{
  private static final int[][] sDataNetType_1x;
  private static final int[][] sDataNetType_3g;
  private static final int[][] sDataNetType_3g_plus;
  private static final int[][] sDataNetType_e;
  private static final int[][] sDataNetType_g;
  private static final int[][] sDataNetType_h;
  private static final int[][] sDataNetType_lte;
  private static final int[] sRoamingIndicatorImages_cdma;
  private static final int[][] sSignalImages;
  private static final int[][] sSignalImages_r;
  private static final int[][] sWifiSignalImages;
  private static final int[][] sWimaxSignalImages;
  private boolean mAlwaysUseCdmaRssi;
  private PendingIntent mBattIntent;
  private boolean mBatteryFirst = true;
  private int mBatteryLevel;
  private TextView mBatteryLevelTextView;
  private boolean mBatteryPlugged;
  private boolean mBatteryShowLowOnEndCall = false;
  private final IBatteryStats mBatteryStats;
  private View mBatteryView;
  private int mBatteryViewSequence;
  private boolean mBluetoothA2dpConnected;
  private boolean mBluetoothEnabled;
  private int mBluetoothHeadsetState;
  private int mBluetoothPbapState;
  private final Context mContext;
  int mDataActivity = 0;
  private int[] mDataIconList = sDataNetType_g[0];
  private boolean mDataIconVisible;
  int mDataState = 0;
  private AlertDialog mDisChargeDialog;
  private DialogInterface.OnDismissListener mDisChargeListener = new DialogInterface.OnDismissListener()
  {
    public void onDismiss(DialogInterface paramDialogInterface)
    {
      Slog.d("StatusBarPolicy", "mDisChargeListener");
      StatusBarPolicy.access$1802(StatusBarPolicy.this, null);
      StatusBarPolicy.access$1902(StatusBarPolicy.this, null);
      StatusBarPolicy.this.mWL.release();
      StatusBarPolicy.access$2002(StatusBarPolicy.this, null);
      if (StatusBarPolicy.this.mIsDisCharged)
      {
        long l = 60000L + SystemClock.elapsedRealtime();
        StatusBarPolicy.access$2202(StatusBarPolicy.this, PendingIntent.getBroadcast(StatusBarPolicy.this.mContext, 0, StatusBarPolicy.this.mIntent, 268435456));
        ((AlarmManager)StatusBarPolicy.this.mContext.getSystemService("alarm")).set(2, l, StatusBarPolicy.this.mBattIntent);
      }
    }
  };
  private TextView mDisChargeTextView;
  private final Handler mHandler = new StatusBarHandler(null);
  private boolean mHspaDataDistinguishable;
  private int mInetCondition = 0;
  private Intent mIntent;
  private BroadcastReceiver mIntentReceiver = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      StatusBarPolicy.access$102(StatusBarPolicy.this, paramContext);
      if (str.equals("android.intent.action.BATTERY_CHANGED"))
        StatusBarPolicy.this.updateBattery(paramIntent);
      while (true)
      {
        return;
        if (str.equals("android.intent.action.ALARM_CHANGED"))
        {
          StatusBarPolicy.this.updateAlarm(paramIntent);
          continue;
        }
        if (str.equals("android.intent.action.SYNC_STATE_CHANGED"))
        {
          StatusBarPolicy.this.updateSyncState(paramIntent);
          continue;
        }
        if (str.equals("android.intent.action.BATTERY_LOW"))
        {
          StatusBarPolicy.this.onBatteryLow(paramIntent);
          continue;
        }
        if ((str.equals("android.intent.action.BATTERY_OKAY")) || (str.equals("android.intent.action.ACTION_POWER_CONNECTED")))
        {
          StatusBarPolicy.this.onBatteryOkay(paramIntent);
          continue;
        }
        if ((str.equals("android.bluetooth.adapter.action.STATE_CHANGED")) || (str.equals("android.bluetooth.headset.action.STATE_CHANGED")) || (str.equals("android.bluetooth.a2dp.action.SINK_STATE_CHANGED")) || (str.equals("android.bluetooth.pbap.intent.action.PBAP_STATE_CHANGED")))
        {
          StatusBarPolicy.this.updateBluetooth(paramIntent);
          continue;
        }
        if ((str.equals("android.net.wifi.STATE_CHANGE")) || (str.equals("android.net.wifi.WIFI_STATE_CHANGED")) || (str.equals("android.net.wifi.RSSI_CHANGED")))
        {
          StatusBarPolicy.this.updateWifi(paramIntent);
          continue;
        }
        if ((str.equals("android.location.GPS_ENABLED_CHANGE")) || (str.equals("android.location.GPS_FIX_CHANGE")))
        {
          StatusBarPolicy.this.updateGps(paramIntent);
          continue;
        }
        if ((str.equals("android.media.RINGER_MODE_CHANGED")) || (str.equals("android.media.VIBRATE_SETTING_CHANGED")))
        {
          StatusBarPolicy.this.updateVolume();
          continue;
        }
        if (str.equals("android.intent.action.SIM_STATE_CHANGED"))
        {
          StatusBarPolicy.this.updateSimState(paramIntent);
          continue;
        }
        if (str.equals("com.android.internal.telephony.cdma.intent.action.TTY_ENABLED_CHANGE"))
        {
          StatusBarPolicy.this.updateTTY(paramIntent);
          continue;
        }
        if ((str.equals("android.net.conn.CONNECTIVITY_CHANGE")) || (str.equals("android.net.conn.INET_CONDITION_ACTION")))
        {
          StatusBarPolicy.this.updateConnectivity(paramIntent);
          continue;
        }
        if (!str.equals("com.sec.android.service.statusbar.COLLAPSE"))
          continue;
        StatusBarPolicy.this.mService.collapse();
      }
    }
  };
  private boolean mIsDisCharged;
  private boolean mIsFullCharged;
  private boolean mIsWifiConnected = false;
  private boolean mIsWimaxEnabled = false;
  private int mLastWifiSignalLevel = -1;
  private AlertDialog mLowBatteryDialog;
  private DialogInterface.OnDismissListener mLowBatteryListener = new DialogInterface.OnDismissListener()
  {
    public void onDismiss(DialogInterface paramDialogInterface)
    {
      StatusBarPolicy.access$1602(StatusBarPolicy.this, null);
      StatusBarPolicy.access$1702(StatusBarPolicy.this, null);
    }
  };
  private Context mMyContext;
  private TelephonyManager mPhone;
  private int mPhoneSignalIconId;
  int mPhoneState = 0;
  private PhoneStateListener mPhoneStateListener = new PhoneStateListener()
  {
    public void onCallStateChanged(int paramInt, String paramString)
    {
      StatusBarPolicy.this.updateCallState(paramInt);
      StatusBarPolicy.this.updateSignalStrength();
    }

    public void onDataActivity(int paramInt)
    {
      StatusBarPolicy.this.mDataActivity = paramInt;
      StatusBarPolicy.this.updateDataIcon();
    }

    public void onDataConnectionStateChanged(int paramInt1, int paramInt2)
    {
      StatusBarPolicy.this.mDataState = paramInt1;
      StatusBarPolicy.this.updateDataNetType(paramInt2);
      StatusBarPolicy.this.updateDataIcon();
    }

    public void onServiceStateChanged(ServiceState paramServiceState)
    {
      StatusBarPolicy.this.mServiceState = paramServiceState;
      StatusBarPolicy.this.updateSignalStrength();
      StatusBarPolicy.this.updateDataIcon();
    }

    public void onSignalStrengthsChanged(SignalStrength paramSignalStrength)
    {
      StatusBarPolicy.this.mSignalStrength = paramSignalStrength;
      StatusBarPolicy.this.updateSignalStrength();
    }
  };
  private final StatusBarManager mService;
  ServiceState mServiceState;
  SignalStrength mSignalStrength;
  IccCard.State mSimState = IccCard.State.READY;
  private StorageManager mStorageManager;
  private boolean mVolumeVisible;
  private PowerManager.WakeLock mWL;
  private int mWimaxExtraState = 0;
  private int mWimaxSignal = 0;
  private int mWimaxState = 0;

  static
  {
    int[][] arrayOfInt1 = new int[2][];
    int[] arrayOfInt2 = new int[5];
    arrayOfInt2[0] = 2130837627;
    arrayOfInt2[1] = 2130837629;
    arrayOfInt2[2] = 2130837631;
    arrayOfInt2[3] = 2130837633;
    arrayOfInt2[4] = 2130837635;
    arrayOfInt1[0] = arrayOfInt2;
    int[] arrayOfInt3 = new int[5];
    arrayOfInt3[0] = 2130837628;
    arrayOfInt3[1] = 2130837630;
    arrayOfInt3[2] = 2130837632;
    arrayOfInt3[3] = 2130837634;
    arrayOfInt3[4] = 2130837636;
    arrayOfInt1[1] = arrayOfInt3;
    sSignalImages = arrayOfInt1;
    int[][] arrayOfInt4 = new int[2][];
    int[] arrayOfInt5 = new int[5];
    arrayOfInt5[0] = 2130837611;
    arrayOfInt5[1] = 2130837613;
    arrayOfInt5[2] = 2130837615;
    arrayOfInt5[3] = 2130837617;
    arrayOfInt5[4] = 2130837619;
    arrayOfInt4[0] = arrayOfInt5;
    int[] arrayOfInt6 = new int[5];
    arrayOfInt6[0] = 2130837612;
    arrayOfInt6[1] = 2130837614;
    arrayOfInt6[2] = 2130837616;
    arrayOfInt6[3] = 2130837618;
    arrayOfInt6[4] = 2130837620;
    arrayOfInt4[1] = arrayOfInt6;
    sSignalImages_r = arrayOfInt4;
    int[] arrayOfInt7 = new int[84];
    arrayOfInt7[0] = 2130837623;
    arrayOfInt7[1] = 2130837623;
    arrayOfInt7[2] = 2130837623;
    arrayOfInt7[3] = 2130837623;
    arrayOfInt7[4] = 2130837623;
    arrayOfInt7[5] = 2130837623;
    arrayOfInt7[6] = 2130837623;
    arrayOfInt7[7] = 2130837623;
    arrayOfInt7[8] = 2130837623;
    arrayOfInt7[9] = 2130837623;
    arrayOfInt7[10] = 2130837623;
    arrayOfInt7[11] = 2130837623;
    arrayOfInt7[12] = 2130837623;
    arrayOfInt7[13] = 2130837623;
    arrayOfInt7[14] = 2130837623;
    arrayOfInt7[15] = 2130837623;
    arrayOfInt7[16] = 2130837623;
    arrayOfInt7[17] = 2130837623;
    arrayOfInt7[18] = 2130837623;
    arrayOfInt7[19] = 2130837623;
    arrayOfInt7[20] = 2130837623;
    arrayOfInt7[21] = 2130837623;
    arrayOfInt7[22] = 2130837623;
    arrayOfInt7[23] = 2130837623;
    arrayOfInt7[24] = 2130837623;
    arrayOfInt7[25] = 2130837623;
    arrayOfInt7[26] = 2130837623;
    arrayOfInt7[27] = 2130837623;
    arrayOfInt7[28] = 2130837623;
    arrayOfInt7[29] = 2130837623;
    arrayOfInt7[30] = 2130837623;
    arrayOfInt7[31] = 2130837623;
    arrayOfInt7[32] = 2130837623;
    arrayOfInt7[33] = 2130837623;
    arrayOfInt7[34] = 2130837623;
    arrayOfInt7[35] = 2130837623;
    arrayOfInt7[36] = 2130837623;
    arrayOfInt7[37] = 2130837623;
    arrayOfInt7[38] = 2130837623;
    arrayOfInt7[39] = 2130837623;
    arrayOfInt7[40] = 2130837623;
    arrayOfInt7[41] = 2130837623;
    arrayOfInt7[42] = 2130837623;
    arrayOfInt7[43] = 2130837623;
    arrayOfInt7[44] = 2130837623;
    arrayOfInt7[45] = 2130837623;
    arrayOfInt7[46] = 2130837623;
    arrayOfInt7[47] = 2130837623;
    arrayOfInt7[48] = 2130837623;
    arrayOfInt7[49] = 2130837623;
    arrayOfInt7[50] = 2130837623;
    arrayOfInt7[51] = 2130837623;
    arrayOfInt7[52] = 2130837623;
    arrayOfInt7[53] = 2130837623;
    arrayOfInt7[54] = 2130837623;
    arrayOfInt7[55] = 2130837623;
    arrayOfInt7[56] = 2130837623;
    arrayOfInt7[57] = 2130837623;
    arrayOfInt7[58] = 2130837623;
    arrayOfInt7[59] = 2130837623;
    arrayOfInt7[60] = 2130837623;
    arrayOfInt7[61] = 2130837623;
    arrayOfInt7[62] = 2130837623;
    arrayOfInt7[63] = 2130837623;
    arrayOfInt7[64] = 2130837623;
    arrayOfInt7[65] = 2130837623;
    arrayOfInt7[66] = 2130837623;
    arrayOfInt7[67] = 2130837623;
    arrayOfInt7[68] = 2130837623;
    arrayOfInt7[69] = 2130837623;
    arrayOfInt7[70] = 2130837623;
    arrayOfInt7[71] = 2130837623;
    arrayOfInt7[72] = 2130837623;
    arrayOfInt7[73] = 2130837623;
    arrayOfInt7[74] = 2130837623;
    arrayOfInt7[75] = 2130837623;
    arrayOfInt7[76] = 2130837623;
    arrayOfInt7[77] = 2130837623;
    arrayOfInt7[78] = 2130837623;
    arrayOfInt7[79] = 2130837623;
    arrayOfInt7[80] = 2130837623;
    arrayOfInt7[81] = 2130837623;
    arrayOfInt7[82] = 2130837623;
    arrayOfInt7[83] = 2130837623;
    sRoamingIndicatorImages_cdma = arrayOfInt7;
    int[][] arrayOfInt8 = new int[2][];
    int[] arrayOfInt9 = new int[4];
    arrayOfInt9[0] = 2130837546;
    arrayOfInt9[1] = 2130837581;
    arrayOfInt9[2] = 2130837596;
    arrayOfInt9[3] = 2130837589;
    arrayOfInt8[0] = arrayOfInt9;
    int[] arrayOfInt10 = new int[4];
    arrayOfInt10[0] = 2130837553;
    arrayOfInt10[1] = 2130837560;
    arrayOfInt10[2] = 2130837574;
    arrayOfInt10[3] = 2130837567;
    arrayOfInt8[1] = arrayOfInt10;
    sDataNetType_g = arrayOfInt8;
    int[][] arrayOfInt11 = new int[2][];
    int[] arrayOfInt12 = new int[4];
    arrayOfInt12[0] = 2130837542;
    arrayOfInt12[1] = 2130837577;
    arrayOfInt12[2] = 2130837592;
    arrayOfInt12[3] = 2130837585;
    arrayOfInt11[0] = arrayOfInt12;
    int[] arrayOfInt13 = new int[4];
    arrayOfInt13[0] = 2130837549;
    arrayOfInt13[1] = 2130837556;
    arrayOfInt13[2] = 2130837570;
    arrayOfInt13[3] = 2130837563;
    arrayOfInt11[1] = arrayOfInt13;
    sDataNetType_3g = arrayOfInt11;
    int[][] arrayOfInt14 = new int[2][];
    int[] arrayOfInt15 = new int[4];
    arrayOfInt15[0] = 2130837545;
    arrayOfInt15[1] = 2130837580;
    arrayOfInt15[2] = 2130837595;
    arrayOfInt15[3] = 2130837588;
    arrayOfInt14[0] = arrayOfInt15;
    int[] arrayOfInt16 = new int[4];
    arrayOfInt16[0] = 2130837552;
    arrayOfInt16[1] = 2130837559;
    arrayOfInt16[2] = 2130837573;
    arrayOfInt16[3] = 2130837566;
    arrayOfInt14[1] = arrayOfInt16;
    sDataNetType_e = arrayOfInt14;
    int[][] arrayOfInt17 = new int[2][];
    int[] arrayOfInt18 = new int[4];
    arrayOfInt18[0] = 2130837547;
    arrayOfInt18[1] = 2130837582;
    arrayOfInt18[2] = 2130837597;
    arrayOfInt18[3] = 2130837590;
    arrayOfInt17[0] = arrayOfInt18;
    int[] arrayOfInt19 = new int[4];
    arrayOfInt19[0] = 2130837554;
    arrayOfInt19[1] = 2130837561;
    arrayOfInt19[2] = 2130837575;
    arrayOfInt19[3] = 2130837568;
    arrayOfInt17[1] = arrayOfInt19;
    sDataNetType_h = arrayOfInt17;
    int[][] arrayOfInt20 = new int[2][];
    int[] arrayOfInt21 = new int[4];
    arrayOfInt21[0] = 2130837543;
    arrayOfInt21[1] = 2130837578;
    arrayOfInt21[2] = 2130837593;
    arrayOfInt21[3] = 2130837586;
    arrayOfInt20[0] = arrayOfInt21;
    int[] arrayOfInt22 = new int[4];
    arrayOfInt22[0] = 2130837550;
    arrayOfInt22[1] = 2130837557;
    arrayOfInt22[2] = 2130837571;
    arrayOfInt22[3] = 2130837564;
    arrayOfInt20[1] = arrayOfInt22;
    sDataNetType_3g_plus = arrayOfInt20;
    int[][] arrayOfInt23 = new int[2][];
    int[] arrayOfInt24 = new int[4];
    arrayOfInt24[0] = 2130837541;
    arrayOfInt24[1] = 2130837576;
    arrayOfInt24[2] = 2130837591;
    arrayOfInt24[3] = 2130837584;
    arrayOfInt23[0] = arrayOfInt24;
    int[] arrayOfInt25 = new int[4];
    arrayOfInt25[0] = 2130837548;
    arrayOfInt25[1] = 2130837555;
    arrayOfInt25[2] = 2130837569;
    arrayOfInt25[3] = 2130837562;
    arrayOfInt23[1] = arrayOfInt25;
    sDataNetType_1x = arrayOfInt23;
    int[][] arrayOfInt26 = new int[2][];
    int[] arrayOfInt27 = new int[4];
    arrayOfInt27[0] = 2130837544;
    arrayOfInt27[1] = 2130837579;
    arrayOfInt27[2] = 2130837594;
    arrayOfInt27[3] = 2130837587;
    arrayOfInt26[0] = arrayOfInt27;
    int[] arrayOfInt28 = new int[4];
    arrayOfInt28[0] = 2130837551;
    arrayOfInt28[1] = 2130837558;
    arrayOfInt28[2] = 2130837572;
    arrayOfInt28[3] = 2130837565;
    arrayOfInt26[1] = arrayOfInt28;
    sDataNetType_lte = arrayOfInt26;
    int[][] arrayOfInt29 = new int[2][];
    int[] arrayOfInt30 = new int[4];
    arrayOfInt30[0] = 2130837641;
    arrayOfInt30[1] = 2130837643;
    arrayOfInt30[2] = 2130837645;
    arrayOfInt30[3] = 2130837647;
    arrayOfInt29[0] = arrayOfInt30;
    int[] arrayOfInt31 = new int[4];
    arrayOfInt31[0] = 2130837642;
    arrayOfInt31[1] = 2130837644;
    arrayOfInt31[2] = 2130837646;
    arrayOfInt31[3] = 2130837648;
    arrayOfInt29[1] = arrayOfInt31;
    sWifiSignalImages = arrayOfInt29;
    int[][] arrayOfInt32 = new int[2][];
    int[] arrayOfInt33 = new int[4];
    arrayOfInt33[0] = 2130837598;
    arrayOfInt33[1] = 2130837600;
    arrayOfInt33[2] = 2130837602;
    arrayOfInt33[3] = 2130837604;
    arrayOfInt32[0] = arrayOfInt33;
    int[] arrayOfInt34 = new int[4];
    arrayOfInt34[0] = 2130837599;
    arrayOfInt34[1] = 2130837601;
    arrayOfInt34[2] = 2130837603;
    arrayOfInt34[3] = 2130837605;
    arrayOfInt32[1] = arrayOfInt34;
    sWimaxSignalImages = arrayOfInt32;
  }

  public StatusBarPolicy(Context paramContext)
  {
    this.mContext = paramContext;
    this.mService = ((StatusBarManager)paramContext.getSystemService("statusbar"));
    this.mSignalStrength = new SignalStrength();
    this.mBatteryStats = BatteryStatsService.getService();
    this.mStorageManager = ((StorageManager)paramContext.getSystemService("storage"));
    this.mStorageManager.registerListener(new StorageNotification(paramContext));
    this.mService.setIcon("battery", 17302209, 0);
    this.mPhone = ((TelephonyManager)paramContext.getSystemService("phone"));
    this.mPhoneSignalIconId = 2130837638;
    this.mService.setIcon("phone_signal", this.mPhoneSignalIconId, 0);
    this.mAlwaysUseCdmaRssi = this.mContext.getResources().getBoolean(17629212);
    ((TelephonyManager)this.mContext.getSystemService("phone")).listen(this.mPhoneStateListener, 481);
    this.mService.setIcon("data_connection", 2130837546, 0);
    this.mService.setIconVisibility("data_connection", false);
    this.mService.setIcon("wifi", sWifiSignalImages[0][0], 0);
    this.mService.setIconVisibility("wifi", false);
    this.mService.setIcon("tty", 2130837639, 0);
    this.mService.setIconVisibility("tty", false);
    this.mService.setIcon("cdma_eri", 2130837623, 0);
    this.mService.setIconVisibility("cdma_eri", false);
    this.mService.setIcon("bluetooth", 2130837539, 0);
    BluetoothAdapter localBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    if (localBluetoothAdapter != null)
      this.mBluetoothEnabled = localBluetoothAdapter.isEnabled();
    while (true)
    {
      this.mBluetoothA2dpConnected = false;
      this.mBluetoothHeadsetState = 0;
      this.mBluetoothPbapState = 0;
      this.mService.setIconVisibility("bluetooth", this.mBluetoothEnabled);
      this.mService.setIcon("gps", 2130837609, 0);
      this.mService.setIconVisibility("gps", false);
      this.mService.setIcon("alarm_clock", 2130837536, 0);
      this.mService.setIconVisibility("alarm_clock", false);
      this.mService.setIcon("sync_active", 17302176, 0);
      this.mService.setIcon("sync_failing", 17302177, 0);
      this.mService.setIconVisibility("sync_active", false);
      this.mService.setIconVisibility("sync_failing", false);
      this.mService.setIcon("volume", 2130837621, 0);
      this.mService.setIconVisibility("volume", false);
      updateVolume();
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("android.intent.action.BATTERY_CHANGED");
      localIntentFilter.addAction("android.intent.action.BATTERY_LOW");
      localIntentFilter.addAction("android.intent.action.BATTERY_OKAY");
      localIntentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
      localIntentFilter.addAction("android.intent.action.ALARM_CHANGED");
      localIntentFilter.addAction("android.intent.action.SYNC_STATE_CHANGED");
      localIntentFilter.addAction("android.media.RINGER_MODE_CHANGED");
      localIntentFilter.addAction("android.media.VIBRATE_SETTING_CHANGED");
      localIntentFilter.addAction("android.bluetooth.adapter.action.STATE_CHANGED");
      localIntentFilter.addAction("android.bluetooth.headset.action.STATE_CHANGED");
      localIntentFilter.addAction("android.bluetooth.a2dp.action.SINK_STATE_CHANGED");
      localIntentFilter.addAction("android.bluetooth.pbap.intent.action.PBAP_STATE_CHANGED");
      localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
      localIntentFilter.addAction("android.net.wifi.supplicant.CONNECTION_CHANGE");
      localIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
      localIntentFilter.addAction("android.net.wifi.RSSI_CHANGED");
      localIntentFilter.addAction("android.location.GPS_ENABLED_CHANGE");
      localIntentFilter.addAction("android.location.GPS_FIX_CHANGE");
      localIntentFilter.addAction("android.intent.action.SIM_STATE_CHANGED");
      localIntentFilter.addAction("com.android.internal.telephony.cdma.intent.action.TTY_ENABLED_CHANGE");
      localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
      localIntentFilter.addAction("android.net.conn.INET_CONDITION_ACTION");
      localIntentFilter.addAction("com.sec.android.service.statusbar.COLLAPSE");
      this.mContext.registerReceiver(this.mIntentReceiver, localIntentFilter, null, this.mHandler);
      try
      {
        this.mHspaDataDistinguishable = this.mContext.getResources().getBoolean(2131034112);
        return;
        this.mBluetoothEnabled = false;
      }
      catch (Exception localException)
      {
        while (true)
          this.mHspaDataDistinguishable = false;
      }
    }
  }

  private void addFullChargeNotification()
  {
    String str1 = this.mContext.getString(2131165215);
    String str2 = this.mContext.getString(2131165216);
    Notification localNotification = new Notification(2130837538, str2, 0L);
    localNotification.flags = (0x2 | localNotification.flags);
    localNotification.setLatestEventInfo(this.mContext, str1, str2, PendingIntent.getActivity(this.mContext, 0, new Intent(), 0));
    ((NotificationManager)this.mContext.getSystemService("notification")).notify(117506048, localNotification);
    playTone();
    turnOnScreenWithForce();
  }

  private void closeLastBatteryView()
  {
    if (this.mBatteryView != null)
    {
      WindowManagerImpl.getDefault().removeView(this.mBatteryView);
      this.mBatteryView = null;
    }
  }

  private int dataRadio()
  {
    int i;
    if (this.mServiceState == null)
    {
      Slog.e("StatusBarPolicy", "Service state not updated");
      i = 5;
    }
    while (true)
    {
      return i;
      switch (this.mServiceState.getRadioTechnology())
      {
      default:
        i = 5;
        break;
      case 14:
        i = 1;
        break;
      case 7:
      case 8:
      case 12:
      case 13:
        i = 4;
        break;
      case 4:
      case 5:
      case 6:
        i = 3;
        break;
      case 1:
      case 2:
      case 3:
      case 9:
      case 10:
      case 11:
        i = 2;
      }
    }
  }

  private int getCdmaLevel()
  {
    int i = this.mSignalStrength.getCdmaDbm();
    int j = this.mSignalStrength.getCdmaEcio();
    int k;
    int m;
    if (i >= -75)
    {
      k = 4;
      if (j < -90)
        break label83;
      m = 4;
      label33: if (k >= m)
        break label127;
    }
    label83: label127: for (int n = k; ; n = m)
    {
      return n;
      if (i >= -85)
      {
        k = 3;
        break;
      }
      if (i >= -95)
      {
        k = 2;
        break;
      }
      if (i >= -100)
      {
        k = 1;
        break;
      }
      k = 0;
      break;
      if (j >= -110)
      {
        m = 3;
        break label33;
      }
      if (j >= -130)
      {
        m = 2;
        break label33;
      }
      if (j >= -150)
      {
        m = 1;
        break label33;
      }
      m = 0;
      break label33;
    }
  }

  private int getEvdoLevel()
  {
    int i = this.mSignalStrength.getEvdoDbm();
    int j = this.mSignalStrength.getEvdoSnr();
    int k;
    int m;
    if (i >= -65)
    {
      k = 4;
      if (j < 7)
        break label83;
      m = 4;
      label33: if (k >= m)
        break label122;
    }
    label83: label122: for (int n = k; ; n = m)
    {
      return n;
      if (i >= -75)
      {
        k = 3;
        break;
      }
      if (i >= -90)
      {
        k = 2;
        break;
      }
      if (i >= -105)
      {
        k = 1;
        break;
      }
      k = 0;
      break;
      if (j >= 5)
      {
        m = 3;
        break label33;
      }
      if (j >= 3)
      {
        m = 2;
        break label33;
      }
      if (j >= 1)
      {
        m = 1;
        break label33;
      }
      m = 0;
      break label33;
    }
  }

  private boolean hasService()
  {
    int i;
    if (this.mServiceState != null)
      switch (this.mServiceState.getState())
      {
      case 2:
      default:
        i = 1;
      case 1:
      case 3:
      }
    while (true)
    {
      return i;
      i = 0;
      continue;
      i = 0;
    }
  }

  private boolean isCdma()
  {
    if ((this.mSignalStrength != null) && (!this.mSignalStrength.isGsm()));
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isEvdo()
  {
    if ((this.mServiceState != null) && ((this.mServiceState.getRadioTechnology() == 7) || (this.mServiceState.getRadioTechnology() == 8) || (this.mServiceState.getRadioTechnology() == 12)));
    for (int i = 1; ; i = 0)
      return i;
  }

  private boolean isThereKeyguard()
  {
    return ((KeyguardManager)this.mContext.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
  }

  private void onBatteryLow(Intent paramIntent)
  {
    if (!isThereKeyguard())
      showLowBatteryWarning();
  }

  private void onBatteryOkay(Intent paramIntent)
  {
    if (this.mLowBatteryDialog != null)
    {
      this.mLowBatteryDialog.dismiss();
      this.mBatteryShowLowOnEndCall = false;
    }
  }

  private void playTone()
  {
    Slog.d("StatusBarPolicy", "playTone");
    int i = ((AudioManager)this.mContext.getSystemService("audio")).getRingerMode();
    NotificationManager localNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    Notification localNotification = new Notification();
    if (i == 2)
      localNotification.defaults = (0x1 | localNotification.defaults);
    while (true)
    {
      localNotificationManager.notify(117506049, localNotification);
      return;
      if (i != 1)
        continue;
      localNotification.defaults = (0x2 | localNotification.defaults);
    }
  }

  private void removeFullChargeNotification()
  {
    ((NotificationManager)this.mContext.getSystemService("notification")).cancel(117506048);
  }

  private void showDisChargePopup(boolean paramBoolean)
  {
    Slog.d("StatusBarPolicy", "showDisChargePopup() " + paramBoolean);
    this.mWL = ((PowerManager)this.mContext.getSystemService("power")).newWakeLock(6, "StatusBarPolicy");
    this.mWL.acquire();
    String str = this.mContext.getString(2131165214);
    if (this.mDisChargeTextView != null)
      this.mDisChargeTextView.setText(str);
    while (true)
    {
      if (!paramBoolean)
        playTone();
      return;
      View localView = View.inflate(this.mContext, 2130903040, null);
      this.mDisChargeTextView = ((TextView)localView.findViewById(2131296256));
      this.mDisChargeTextView.setText(str);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
      localBuilder.setCancelable(false);
      localBuilder.setTitle(2131165213);
      localBuilder.setView(localView);
      localBuilder.setIcon(17301543);
      localBuilder.setPositiveButton(17039370, null);
      AlertDialog localAlertDialog = localBuilder.create();
      localAlertDialog.setOnDismissListener(this.mDisChargeListener);
      localAlertDialog.getWindow().setType(2003);
      localAlertDialog.show();
      this.mDisChargeDialog = localAlertDialog;
    }
  }

  private void showLowBatteryWarning()
  {
    if (Settings.System.getInt(this.mContext.getContentResolver(), "SHOULD_SHUT_DOWN", 0) == 1)
      return;
    closeLastBatteryView();
    String str;
    if (this.mBatteryLevel > 5)
    {
      str = this.mContext.getString(2131165211);
      label42: if (this.mBatteryLevelTextView == null)
        break label78;
      this.mBatteryLevelTextView.setText(str);
    }
    while (true)
    {
      playTone();
      break;
      str = this.mContext.getString(2131165212);
      break label42;
      label78: View localView = View.inflate(this.mContext, 2130903041, null);
      this.mBatteryLevelTextView = ((TextView)localView.findViewById(2131296260));
      this.mBatteryLevelTextView.setText(str);
      AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.mContext);
      localBuilder.setCancelable(true);
      localBuilder.setTitle(2131165189);
      localBuilder.setView(localView);
      localBuilder.setIcon(17301543);
      localBuilder.setPositiveButton(17039370, null);
      Intent localIntent = new Intent("android.intent.action.POWER_USAGE_SUMMARY");
      localIntent.setFlags(1484783616);
      if (localIntent.resolveActivity(this.mContext.getPackageManager()) != null)
        localBuilder.setNegativeButton(2131165192, new DialogInterface.OnClickListener(localIntent)
        {
          public void onClick(DialogInterface paramDialogInterface, int paramInt)
          {
            StatusBarPolicy.this.mContext.startActivity(this.val$intent);
            if (StatusBarPolicy.this.mLowBatteryDialog != null)
              StatusBarPolicy.this.mLowBatteryDialog.dismiss();
          }
        });
      AlertDialog localAlertDialog = localBuilder.create();
      localAlertDialog.setOnDismissListener(this.mLowBatteryListener);
      localAlertDialog.getWindow().setType(2003);
      localAlertDialog.show();
      this.mLowBatteryDialog = localAlertDialog;
    }
  }

  private void turnOnScreenWithForce()
  {
    IPowerManager localIPowerManager = IPowerManager.Stub.asInterface(ServiceManager.getService("power"));
    try
    {
      localIPowerManager.userActivityWithForce(SystemClock.uptimeMillis(), false, true);
      label21: return;
    }
    catch (RemoteException localRemoteException)
    {
      break label21;
    }
  }

  private final void updateAlarm(Intent paramIntent)
  {
    boolean bool = paramIntent.getBooleanExtra("alarmSet", false);
    this.mService.setIconVisibility("alarm_clock", bool);
  }

  private final void updateBattery(Intent paramIntent)
  {
    int i = paramIntent.getIntExtra("icon-small", 0);
    int j = paramIntent.getIntExtra("level", 0);
    StatusBarManager localStatusBarManager = this.mService;
    localStatusBarManager.setIcon("battery", i, j);
    if (Settings.System.getInt(this.mContext.getContentResolver(), "show_battery_icon", 1) == 0)
    {
      localStatusBarManager.setIconVisibility("battery", false);
      return;
    }
    this.mIntent = paramIntent;
    int k = paramIntent.getIntExtra("status", 1);
    int m = paramIntent.getIntExtra("health", 1);
    Slog.i("StatusBarPolicy", "BAT. S:" + k + " H:" + m);
    if (k == 3)
    {
      if (this.mIsDisCharged)
      {
        this.mIsDisCharged = false;
        if (this.mDisChargeDialog != null)
          this.mDisChargeDialog.dismiss();
        if (this.mBattIntent != null)
          ((AlarmManager)this.mContext.getSystemService("alarm")).cancel(this.mBattIntent);
      }
      if (this.mIsFullCharged)
      {
        this.mIsFullCharged = false;
        removeFullChargeNotification();
      }
    }
    while (true)
    {
      return;
      if (k == 4)
      {
        if ((m != 3) && (m != 7))
          continue;
        if ((!isThereKeyguard()) && (this.mDisChargeDialog == null))
          showDisChargePopup(this.mIsDisCharged);
        this.mIsDisCharged = true;
        continue;
      }
      if (k == 5)
      {
        if (this.mIsFullCharged)
          continue;
        this.mIsFullCharged = true;
        addFullChargeNotification();
        continue;
      }
      if ((!this.mIsDisCharged) || (m != 2))
        continue;
      this.mIsDisCharged = false;
      if (this.mDisChargeDialog != null)
        this.mDisChargeDialog.dismiss();
      if (this.mBattIntent == null)
        continue;
      ((AlarmManager)this.mContext.getSystemService("alarm")).cancel(this.mBattIntent);
    }
  }

  private final void updateBluetooth(Intent paramIntent)
  {
    int i = 2130837539;
    String str = paramIntent.getAction();
    boolean bool;
    if (str.equals("android.bluetooth.adapter.action.STATE_CHANGED"))
      if (paramIntent.getIntExtra("android.bluetooth.adapter.extra.STATE", -2147483648) == 12)
      {
        bool = true;
        this.mBluetoothEnabled = bool;
      }
    while (true)
    {
      label43: if ((this.mBluetoothHeadsetState == 2) || (this.mBluetoothA2dpConnected) || (this.mBluetoothPbapState == 2))
        i = 2130837540;
      this.mService.setIcon("bluetooth", i, 0);
      this.mService.setIconVisibility("bluetooth", this.mBluetoothEnabled);
      do
      {
        return;
        bool = false;
        break;
        if (str.equals("android.bluetooth.headset.action.STATE_CHANGED"))
        {
          this.mBluetoothHeadsetState = paramIntent.getIntExtra("android.bluetooth.headset.extra.STATE", -1);
          break label43;
        }
        if (!str.equals("android.bluetooth.a2dp.action.SINK_STATE_CHANGED"))
          continue;
        if (new BluetoothA2dp(this.mContext).getConnectedSinks().size() != 0)
        {
          this.mBluetoothA2dpConnected = true;
          break label43;
        }
        this.mBluetoothA2dpConnected = false;
        break label43;
      }
      while (!str.equals("android.bluetooth.pbap.intent.action.PBAP_STATE_CHANGED"));
      this.mBluetoothPbapState = paramIntent.getIntExtra("android.bluetooth.pbap.intent.PBAP_STATE", 0);
    }
  }

  private final void updateCallState(int paramInt)
  {
    this.mPhoneState = paramInt;
    if (this.mPhoneState == 0)
      if (this.mBatteryShowLowOnEndCall)
      {
        if ((!this.mBatteryPlugged) && (!isThereKeyguard()))
          showLowBatteryWarning();
        this.mBatteryShowLowOnEndCall = false;
      }
    while (true)
    {
      return;
      if (this.mLowBatteryDialog != null)
      {
        this.mLowBatteryDialog.dismiss();
        this.mBatteryShowLowOnEndCall = true;
      }
      if (this.mDisChargeDialog == null)
        continue;
      this.mDisChargeDialog.dismiss();
    }
  }

  private final void updateCdmaRoamingIcon()
  {
    if ((!hasService()) || (this.mSignalStrength == null) || (!this.mServiceState.getRoaming()) || (this.mSignalStrength.isGsm()))
      this.mService.setIconVisibility("cdma_eri", false);
    int[] arrayOfInt;
    int i;
    while (true)
    {
      return;
      arrayOfInt = sRoamingIndicatorImages_cdma;
      i = this.mServiceState.getCdmaEriIconIndex();
      int j = this.mServiceState.getCdmaEriIconMode();
      if ((i == -1) || (j == -1))
        break;
      if (i == 1)
      {
        this.mService.setIconVisibility("cdma_eri", false);
        continue;
      }
      switch (j)
      {
      default:
      case 0:
      case 1:
      }
    }
    while (true)
    {
      this.mService.setIconVisibility("cdma_eri", true);
      this.mService.setIcon("phone_signal", this.mPhoneSignalIconId, 0);
      break;
      this.mService.setIcon("cdma_eri", arrayOfInt[i], 0);
      continue;
      this.mService.setIcon("cdma_eri", 2130837624, 0);
      continue;
      this.mService.setIcon("cdma_eri", 2130837623, 0);
    }
  }

  private void updateConnectivity(Intent paramIntent)
  {
    NetworkInfo localNetworkInfo = (NetworkInfo)(NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    int i;
    if (paramIntent.getIntExtra("inetCondition", 0) > 50)
      i = 1;
    switch (localNetworkInfo.getType())
    {
    default:
    case 0:
      while (true)
      {
        return;
        i = 0;
        break;
        this.mInetCondition = i;
        updateDataNetType(localNetworkInfo.getSubtype());
        updateDataIcon();
        updateSignalStrength();
      }
    case 1:
    }
    this.mInetCondition = i;
    int k;
    if (localNetworkInfo.isConnected())
    {
      this.mIsWifiConnected = true;
      if (this.mLastWifiSignalLevel == -1)
      {
        k = sWifiSignalImages[this.mInetCondition][0];
        label124: this.mService.setIcon("wifi", k, 0);
        this.mService.setIconVisibility("wifi", true);
      }
    }
    while (true)
    {
      updateSignalStrength();
      break;
      k = sWifiSignalImages[this.mInetCondition][this.mLastWifiSignalLevel];
      break label124;
      this.mLastWifiSignalLevel = -1;
      this.mIsWifiConnected = false;
      int j = sWifiSignalImages[0][0];
      this.mService.setIcon("wifi", j, 0);
      this.mService.setIconVisibility("wifi", false);
    }
  }

  // ERROR //
  private final void updateDataIcon()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 297	com/android/systemui/statusbar/policy/StatusBarPolicy:mSignalStrength	Landroid/telephony/SignalStrength;
    //   4: ifnull +99 -> 103
    //   7: aload_0
    //   8: getfield 297	com/android/systemui/statusbar/policy/StatusBarPolicy:mSignalStrength	Landroid/telephony/SignalStrength;
    //   11: invokevirtual 680	android/telephony/SignalStrength:isGsm	()Z
    //   14: ifeq +89 -> 103
    //   17: aload_0
    //   18: getfield 244	com/android/systemui/statusbar/policy/StatusBarPolicy:mSimState	Lcom/android/internal/telephony/IccCard$State;
    //   21: getstatic 242	com/android/internal/telephony/IccCard$State:READY	Lcom/android/internal/telephony/IccCard$State;
    //   24: if_acmpeq +79 -> 103
    //   27: aload_0
    //   28: getfield 244	com/android/systemui/statusbar/policy/StatusBarPolicy:mSimState	Lcom/android/internal/telephony/IccCard$State;
    //   31: getstatic 991	com/android/internal/telephony/IccCard$State:UNKNOWN	Lcom/android/internal/telephony/IccCard$State;
    //   34: if_acmpeq +69 -> 103
    //   37: aload_0
    //   38: getfield 292	com/android/systemui/statusbar/policy/StatusBarPolicy:mService	Landroid/app/StatusBarManager;
    //   41: ldc_w 356
    //   44: ldc_w 992
    //   47: iconst_0
    //   48: invokevirtual 326	android/app/StatusBarManager:setIcon	(Ljava/lang/String;II)V
    //   51: iconst_1
    //   52: istore_1
    //   53: invokestatic 997	android/os/Binder:clearCallingIdentity	()J
    //   56: lstore_2
    //   57: aload_0
    //   58: getfield 305	com/android/systemui/statusbar/policy/StatusBarPolicy:mBatteryStats	Lcom/android/internal/app/IBatteryStats;
    //   61: aload_0
    //   62: getfield 332	com/android/systemui/statusbar/policy/StatusBarPolicy:mPhone	Landroid/telephony/TelephonyManager;
    //   65: invokevirtual 1000	android/telephony/TelephonyManager:getNetworkType	()I
    //   68: iload_1
    //   69: invokeinterface 1006 3 0
    //   74: lload_2
    //   75: invokestatic 1010	android/os/Binder:restoreCallingIdentity	(J)V
    //   78: aload_0
    //   79: getfield 1012	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconVisible	Z
    //   82: iload_1
    //   83: if_icmpeq +19 -> 102
    //   86: aload_0
    //   87: getfield 292	com/android/systemui/statusbar/policy/StatusBarPolicy:mService	Landroid/app/StatusBarManager;
    //   90: ldc_w 356
    //   93: iload_1
    //   94: invokevirtual 360	android/app/StatusBarManager:setIconVisibility	(Ljava/lang/String;Z)V
    //   97: aload_0
    //   98: iload_1
    //   99: putfield 1012	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconVisible	Z
    //   102: return
    //   103: aload_0
    //   104: invokespecial 1014	com/android/systemui/statusbar/policy/StatusBarPolicy:dataRadio	()I
    //   107: iconst_2
    //   108: if_icmpeq +11 -> 119
    //   111: aload_0
    //   112: invokespecial 1014	com/android/systemui/statusbar/policy/StatusBarPolicy:dataRadio	()I
    //   115: iconst_1
    //   116: if_icmpne +151 -> 267
    //   119: aload_0
    //   120: getfield 244	com/android/systemui/statusbar/policy/StatusBarPolicy:mSimState	Lcom/android/internal/telephony/IccCard$State;
    //   123: getstatic 242	com/android/internal/telephony/IccCard$State:READY	Lcom/android/internal/telephony/IccCard$State;
    //   126: if_acmpeq +13 -> 139
    //   129: aload_0
    //   130: getfield 244	com/android/systemui/statusbar/policy/StatusBarPolicy:mSimState	Lcom/android/internal/telephony/IccCard$State;
    //   133: getstatic 991	com/android/internal/telephony/IccCard$State:UNKNOWN	Lcom/android/internal/telephony/IccCard$State;
    //   136: if_acmpne +112 -> 248
    //   139: aload_0
    //   140: invokespecial 959	com/android/systemui/statusbar/policy/StatusBarPolicy:hasService	()Z
    //   143: ifeq +100 -> 243
    //   146: aload_0
    //   147: getfield 248	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataState	I
    //   150: iconst_2
    //   151: if_icmpne +92 -> 243
    //   154: aload_0
    //   155: getfield 250	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataActivity	I
    //   158: tableswitch	default:+26 -> 184, 1:+52->210, 2:+63->221, 3:+74->232
    //   185: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   188: iconst_0
    //   189: iaload
    //   190: istore 6
    //   192: aload_0
    //   193: getfield 292	com/android/systemui/statusbar/policy/StatusBarPolicy:mService	Landroid/app/StatusBarManager;
    //   196: ldc_w 356
    //   199: iload 6
    //   201: iconst_0
    //   202: invokevirtual 326	android/app/StatusBarManager:setIcon	(Ljava/lang/String;II)V
    //   205: iconst_1
    //   206: istore_1
    //   207: goto -154 -> 53
    //   210: aload_0
    //   211: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   214: iconst_1
    //   215: iaload
    //   216: istore 6
    //   218: goto -26 -> 192
    //   221: aload_0
    //   222: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   225: iconst_2
    //   226: iaload
    //   227: istore 6
    //   229: goto -37 -> 192
    //   232: aload_0
    //   233: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   236: iconst_3
    //   237: iaload
    //   238: istore 6
    //   240: goto -48 -> 192
    //   243: iconst_0
    //   244: istore_1
    //   245: goto -192 -> 53
    //   248: aload_0
    //   249: getfield 292	com/android/systemui/statusbar/policy/StatusBarPolicy:mService	Landroid/app/StatusBarManager;
    //   252: ldc_w 356
    //   255: ldc_w 992
    //   258: iconst_0
    //   259: invokevirtual 326	android/app/StatusBarManager:setIcon	(Ljava/lang/String;II)V
    //   262: iconst_1
    //   263: istore_1
    //   264: goto -211 -> 53
    //   267: aload_0
    //   268: invokespecial 1014	com/android/systemui/statusbar/policy/StatusBarPolicy:dataRadio	()I
    //   271: iconst_3
    //   272: if_icmpeq +11 -> 283
    //   275: aload_0
    //   276: invokespecial 1014	com/android/systemui/statusbar/policy/StatusBarPolicy:dataRadio	()I
    //   279: iconst_4
    //   280: if_icmpne +112 -> 392
    //   283: aload_0
    //   284: invokespecial 959	com/android/systemui/statusbar/policy/StatusBarPolicy:hasService	()Z
    //   287: ifeq +100 -> 387
    //   290: aload_0
    //   291: getfield 248	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataState	I
    //   294: iconst_2
    //   295: if_icmpne +92 -> 387
    //   298: aload_0
    //   299: getfield 250	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataActivity	I
    //   302: tableswitch	default:+26 -> 328, 1:+52->354, 2:+63->365, 3:+74->376
    //   329: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   332: iconst_0
    //   333: iaload
    //   334: istore 7
    //   336: aload_0
    //   337: getfield 292	com/android/systemui/statusbar/policy/StatusBarPolicy:mService	Landroid/app/StatusBarManager;
    //   340: ldc_w 356
    //   343: iload 7
    //   345: iconst_0
    //   346: invokevirtual 326	android/app/StatusBarManager:setIcon	(Ljava/lang/String;II)V
    //   349: iconst_1
    //   350: istore_1
    //   351: goto -298 -> 53
    //   354: aload_0
    //   355: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   358: iconst_1
    //   359: iaload
    //   360: istore 7
    //   362: goto -26 -> 336
    //   365: aload_0
    //   366: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   369: iconst_2
    //   370: iaload
    //   371: istore 7
    //   373: goto -37 -> 336
    //   376: aload_0
    //   377: getfield 237	com/android/systemui/statusbar/policy/StatusBarPolicy:mDataIconList	[I
    //   380: iconst_3
    //   381: iaload
    //   382: istore 7
    //   384: goto -48 -> 336
    //   387: iconst_0
    //   388: istore_1
    //   389: goto -336 -> 53
    //   392: iconst_0
    //   393: istore_1
    //   394: goto -341 -> 53
    //   397: astore 5
    //   399: lload_2
    //   400: invokestatic 1010	android/os/Binder:restoreCallingIdentity	(J)V
    //   403: goto -325 -> 78
    //   406: astore 4
    //   408: lload_2
    //   409: invokestatic 1010	android/os/Binder:restoreCallingIdentity	(J)V
    //   412: aload 4
    //   414: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   57	74	397	android/os/RemoteException
    //   57	74	406	finally
  }

  private final void updateDataNetType(int paramInt)
  {
    switch (paramInt)
    {
    case 11:
    default:
      this.mDataIconList = sDataNetType_g[this.mInetCondition];
    case 2:
    case 3:
    case 8:
    case 9:
    case 10:
    case 4:
    case 7:
    case 5:
    case 6:
    case 12:
    case 14:
    case 13:
    }
    while (true)
    {
      return;
      this.mDataIconList = sDataNetType_e[this.mInetCondition];
      continue;
      this.mDataIconList = sDataNetType_3g[this.mInetCondition];
      continue;
      if (this.mHspaDataDistinguishable)
      {
        String str = SystemProperties.get("ro.csc.sales_code");
        if ((str != null) && (("FTM".equals(str)) || ("ORA".equals(str)) || ("AMN".equals(str)) || ("ORO".equals(str)) || ("ORG".equals(str)) || ("IDE".equals(str)) || ("ONE".equals(str))))
        {
          this.mDataIconList = sDataNetType_3g_plus[this.mInetCondition];
          continue;
        }
        this.mDataIconList = sDataNetType_h[this.mInetCondition];
        continue;
      }
      this.mDataIconList = sDataNetType_3g[this.mInetCondition];
      continue;
      this.mDataIconList = sDataNetType_1x[this.mInetCondition];
      continue;
      this.mDataIconList = sDataNetType_1x[this.mInetCondition];
      continue;
      this.mDataIconList = sDataNetType_3g[this.mInetCondition];
      continue;
      this.mDataIconList = sDataNetType_lte[this.mInetCondition];
    }
  }

  private final void updateGps(Intent paramIntent)
  {
    String str = paramIntent.getAction();
    boolean bool = paramIntent.getBooleanExtra("enabled", false);
    if ((str.equals("android.location.GPS_FIX_CHANGE")) && (bool))
    {
      this.mService.setIcon("gps", 17302221, 0);
      this.mService.setIconVisibility("gps", true);
    }
    while (true)
    {
      return;
      if ((str.equals("android.location.GPS_ENABLED_CHANGE")) && (!bool))
      {
        this.mService.setIconVisibility("gps", false);
        continue;
      }
      this.mService.setIcon("gps", 2130837609, 0);
      this.mService.setIconVisibility("gps", true);
    }
  }

  private final void updateSignalStrength()
  {
    updateCdmaRoamingIcon();
    if ((this.mSignalStrength == null) || (this.mServiceState == null) || ((!hasService()) && (!this.mServiceState.isEmergencyOnly())))
    {
      if (Settings.System.getInt(this.mContext.getContentResolver(), "airplane_mode_on", 0) == 1);
      for (this.mPhoneSignalIconId = 2130837637; ; this.mPhoneSignalIconId = 2130837638)
      {
        this.mService.setIcon("phone_signal", this.mPhoneSignalIconId, 0);
        return;
      }
    }
    int[] arrayOfInt2;
    label128: int i;
    int[] arrayOfInt1;
    if (!isCdma())
    {
      int j = this.mSignalStrength.getGsmSignalBar();
      if (j > 4)
        j = 4;
      if (this.mPhone.isNetworkRoaming())
      {
        arrayOfInt2 = sSignalImages_r[this.mInetCondition];
        String str1 = SystemProperties.get("gsm.sim.operator.numeric", null);
        String str2 = SystemProperties.get("gsm.operator.alpha", null);
        if ((str1 != null) && (str2 != null) && (str1.equals("52023")) && (str2.equals("TH GSM")))
          arrayOfInt2 = sSignalImages_r[this.mInetCondition];
        int[] arrayOfInt3 = arrayOfInt2;
        i = j;
        arrayOfInt1 = arrayOfInt3;
      }
    }
    while (true)
    {
      this.mPhoneSignalIconId = arrayOfInt1[i];
      this.mService.setIcon("phone_signal", this.mPhoneSignalIconId, 0);
      break;
      arrayOfInt2 = sSignalImages[this.mInetCondition];
      break label128;
      arrayOfInt1 = sSignalImages[this.mInetCondition];
      if ((this.mPhoneState == 0) && (isEvdo()) && (!this.mAlwaysUseCdmaRssi))
      {
        i = getEvdoLevel();
        continue;
      }
      i = getCdmaLevel();
    }
  }

  private final void updateSimState(Intent paramIntent)
  {
    String str1 = paramIntent.getStringExtra("ss");
    if ("ABSENT".equals(str1))
      this.mSimState = IccCard.State.ABSENT;
    while (true)
    {
      updateDataIcon();
      return;
      if ("CARD_IO_ERROR".equals(str1))
      {
        this.mSimState = IccCard.State.CARD_IO_ERROR;
        continue;
      }
      if ("READY".equals(str1))
      {
        this.mSimState = IccCard.State.READY;
        continue;
      }
      if ("LOCKED".equals(str1))
      {
        String str2 = paramIntent.getStringExtra("reason");
        if ("PIN".equals(str2))
        {
          this.mSimState = IccCard.State.PIN_REQUIRED;
          continue;
        }
        if ("PUK".equals(str2))
        {
          this.mSimState = IccCard.State.PUK_REQUIRED;
          continue;
        }
        if ("SIM NETWORK".equals(str2))
        {
          this.mSimState = IccCard.State.NETWORK_LOCKED;
          continue;
        }
        if ("SIM NETWORK SUBSET".equals(str2))
        {
          this.mSimState = IccCard.State.SIM_NETWORK_SUBSET_LOCKED;
          continue;
        }
        if ("SIM CORPORATE".equals(str2))
        {
          this.mSimState = IccCard.State.SIM_CORPORATE_LOCKED;
          continue;
        }
        if ("SIM SERVICE PROVIDER".equals(str2))
        {
          this.mSimState = IccCard.State.SIM_SERVICE_PROVIDER_LOCKED;
          continue;
        }
        if ("SIM SIM".equals(str2))
        {
          this.mSimState = IccCard.State.SIM_SIM_LOCKED;
          continue;
        }
        if ("RUIM NETWORK1".equals(str2))
        {
          this.mSimState = IccCard.State.RUIM_NETWORK1_LOCKED;
          continue;
        }
        if ("RUIM NETWORK2".equals(str2))
        {
          this.mSimState = IccCard.State.RUIM_NETWORK2_LOCKED;
          continue;
        }
        if ("RUIM HRPD".equals(str2))
        {
          this.mSimState = IccCard.State.RUIM_HRPD_LOCKED;
          continue;
        }
        if ("RUIM CORPORATE".equals(str2))
        {
          this.mSimState = IccCard.State.RUIM_CORPORATE_LOCKED;
          continue;
        }
        if ("RUIM SERVICE PROVIDER".equals(str2))
        {
          this.mSimState = IccCard.State.RUIM_SERVICE_PROVIDER_LOCKED;
          continue;
        }
        if ("RUIM RUIM".equals(str2))
        {
          this.mSimState = IccCard.State.RUIM_RUIM_LOCKED;
          continue;
        }
        this.mSimState = IccCard.State.NETWORK_LOCKED;
        continue;
      }
      this.mSimState = IccCard.State.UNKNOWN;
    }
  }

  private final void updateSyncState(Intent paramIntent)
  {
    boolean bool = paramIntent.getBooleanExtra("active", false);
    paramIntent.getBooleanExtra("failing", false);
    this.mService.setIconVisibility("sync_active", bool);
  }

  private final void updateTTY(Intent paramIntent)
  {
    paramIntent.getAction();
    if (paramIntent.getBooleanExtra("ttyEnabled", false))
    {
      this.mService.setIcon("tty", 2130837639, 0);
      this.mService.setIconVisibility("tty", true);
    }
    while (true)
    {
      return;
      this.mService.setIconVisibility("tty", false);
    }
  }

  private final void updateVolume()
  {
    AudioManager localAudioManager = (AudioManager)this.mContext.getSystemService("audio");
    int i = localAudioManager.getRingerMode();
    boolean bool;
    if ((i == 0) || (i == 1))
    {
      bool = true;
      if (!localAudioManager.shouldVibrate(0))
        break label90;
    }
    label90: for (int j = 2130837622; ; j = 2130837621)
    {
      if (bool)
        this.mService.setIcon("volume", j, 0);
      if (bool != this.mVolumeVisible)
      {
        this.mService.setIconVisibility("volume", bool);
        this.mVolumeVisible = bool;
      }
      return;
      bool = false;
      break;
    }
  }

  private final void updateWifi(Intent paramIntent)
  {
    String str = paramIntent.getAction();
    int k;
    if (str.equals("android.net.wifi.WIFI_STATE_CHANGED"))
      if (paramIntent.getIntExtra("wifi_state", 4) == 3)
      {
        k = 1;
        if (k == 0)
          this.mService.setIconVisibility("wifi", false);
      }
    while (true)
    {
      return;
      k = 0;
      break;
      if (str.equals("android.net.wifi.RSSI_CHANGED"))
      {
        int i = WifiManager.calculateSignalLevel(paramIntent.getIntExtra("newRssi", -200), sWifiSignalImages[0].length);
        if (i == this.mLastWifiSignalLevel)
          continue;
        this.mLastWifiSignalLevel = i;
        if (this.mIsWifiConnected);
        for (int j = sWifiSignalImages[this.mInetCondition][i]; ; j = 2130837640)
        {
          this.mService.setIcon("wifi", j, 0);
          break;
        }
      }
      if (!str.equals("android.net.wifi.STATE_CHANGE"))
        continue;
      NetworkInfo localNetworkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
      if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()))
      {
        this.mIsWifiConnected = true;
        if (this.mLastWifiSignalLevel == -1);
        while (true)
        {
          this.mService.setIconVisibility("wifi", true);
          break;
          sWifiSignalImages[this.mInetCondition][this.mLastWifiSignalLevel];
        }
      }
      this.mLastWifiSignalLevel = -1;
      this.mIsWifiConnected = false;
      this.mService.setIconVisibility("wifi", false);
    }
  }

  private class StatusBarHandler extends Handler
  {
    private StatusBarHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
      case 4:
      }
      while (true)
      {
        return;
        if (paramMessage.arg1 == StatusBarPolicy.this.mBatteryViewSequence)
        {
          StatusBarPolicy.this.closeLastBatteryView();
          continue;
        }
      }
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.StatusBarPolicy
 * JD-Core Version:    0.6.0
 */