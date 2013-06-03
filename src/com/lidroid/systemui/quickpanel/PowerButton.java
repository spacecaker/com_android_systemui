package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class PowerButton
{
  private static final HashMap<String, Class<? extends PowerButton>> BUTTONS;
  private static final HashMap<String, PowerButton> BUTTONS_LOADED;
  public static final String BUTTON_AIRPLANE = "toggleAirplane";
  public static final String BUTTON_AUTOROTATE = "toggleAutoRotate";
  public static final String BUTTON_BLUETOOTH = "toggleBluetooth";
  public static final String BUTTON_BRIGHTNESS = "toggleBrightness";
  public static final String BUTTON_FLASHLIGHT = "toggleFlashlight";
  public static final String BUTTON_GPS = "toggleGPS";
  public static final String BUTTON_LOCKSCREEN = "toggleLockScreen";
  public static final String BUTTON_MOBILEDATA = "toggleMobileData";
  public static final String BUTTON_REBOOT = "toggleReboot";
  public static final String BUTTON_SCREENTIMEOUT = "toggleScreenTimeout";
  public static final String BUTTON_SHUTDOWN = "toggleShutdown";
  public static final String BUTTON_SOUND = "toggleSound";
  public static final String BUTTON_SYNC = "toggleSync";
  public static final String BUTTON_UNKNOWN = "unknown";
  public static final String BUTTON_WIFI = "toggleWifi";
  private static View.OnClickListener GLOBAL_ON_CLICK_LISTENER;
  private static View.OnLongClickListener GLOBAL_ON_LONG_CLICK_LISTENER;
  private static final PorterDuff.Mode MASK_MODE = PorterDuff.Mode.SCREEN;
  public static final int STATE_DISABLED = 2;
  public static final int STATE_ENABLED = 1;
  public static final int STATE_INTERMEDIATE = 5;
  public static final int STATE_TURNING_OFF = 4;
  public static final int STATE_TURNING_ON = 3;
  public static final int STATE_UNKNOWN = 6;
  public static final String TAG = "PowerButton";
  private View.OnClickListener mClickListener = new PowerButton.2(this);
  protected int mIcon;
  private View.OnLongClickListener mLongClickListener = new PowerButton.3(this);
  protected int mState;
  protected int mStatusIcon;
  protected String mType = "unknown";
  protected View mView;
  private Handler mViewUpdateHandler = new PowerButton.1(this);

  static
  {
    BUTTONS = new HashMap();
    BUTTONS.put("toggleWifi", WifiButton.class);
    BUTTONS.put("toggleMobileData", MobileDataButton.class);
    BUTTONS.put("toggleGPS", GPSButton.class);
    BUTTONS.put("toggleBluetooth", BluetoothButton.class);
    BUTTONS.put("toggleSound", SoundButton.class);
    BUTTONS.put("toggleAutoRotate", AutoRotateButton.class);
    BUTTONS.put("toggleSync", SyncButton.class);
    BUTTONS.put("toggleBrightness", BrightnessButton.class);
    BUTTONS.put("toggleScreenTimeout", ScreenTimeoutButton.class);
    BUTTONS.put("toggleLockScreen", LockScreenButton.class);
    BUTTONS.put("toggleAirplane", AirplaneButton.class);
    BUTTONS.put("toggleFlashlight", FlashlightButton.class);
    BUTTONS.put("toggleReboot", RebootButton.class);
    BUTTONS.put("toggleShutdown", ShutdownButton.class);
    BUTTONS_LOADED = new HashMap();
    GLOBAL_ON_CLICK_LISTENER = null;
    GLOBAL_ON_LONG_CLICK_LISTENER = null;
  }

  public static IntentFilter getAllBroadcastIntentFilters()
  {
    IntentFilter localIntentFilter1 = new IntentFilter();
    while (true)
    {
      int j;
      synchronized (BUTTONS_LOADED)
      {
        Iterator localIterator = BUTTONS_LOADED.values().iterator();
        if (!localIterator.hasNext())
          continue;
        IntentFilter localIntentFilter2 = ((PowerButton)localIterator.next()).getBroadcastIntentFilter();
        int i = localIntentFilter2.countActions();
        j = 0;
        if (j >= i)
          continue;
        String str = localIntentFilter2.getAction(j);
        if (!localIntentFilter1.hasAction(str))
        {
          localIntentFilter1.addAction(str);
          break label102;
          return localIntentFilter1;
        }
      }
      label102: j++;
    }
  }

  // ERROR //
  public static List<Uri> getAllObservedUris()
  {
    // Byte code:
    //   0: new 221	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 222	java/util/ArrayList:<init>	()V
    //   7: astore_0
    //   8: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   11: astore_1
    //   12: aload_1
    //   13: monitorenter
    //   14: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   17: invokevirtual 182	java/util/HashMap:values	()Ljava/util/Collection;
    //   20: invokeinterface 188 1 0
    //   25: astore_3
    //   26: aload_3
    //   27: invokeinterface 194 1 0
    //   32: ifeq +72 -> 104
    //   35: aload_3
    //   36: invokeinterface 198 1 0
    //   41: checkcast 2	com/lidroid/systemui/quickpanel/PowerButton
    //   44: invokevirtual 225	com/lidroid/systemui/quickpanel/PowerButton:getObservedUris	()Ljava/util/List;
    //   47: invokeinterface 228 1 0
    //   52: astore 4
    //   54: aload 4
    //   56: invokeinterface 194 1 0
    //   61: ifeq -35 -> 26
    //   64: aload 4
    //   66: invokeinterface 198 1 0
    //   71: checkcast 230	android/net/Uri
    //   74: astore 5
    //   76: aload_0
    //   77: aload 5
    //   79: invokeinterface 234 2 0
    //   84: ifne -30 -> 54
    //   87: aload_0
    //   88: aload 5
    //   90: invokeinterface 237 2 0
    //   95: pop
    //   96: goto -42 -> 54
    //   99: astore_2
    //   100: aload_1
    //   101: monitorexit
    //   102: aload_2
    //   103: athrow
    //   104: aload_1
    //   105: monitorexit
    //   106: aload_0
    //   107: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   14	102	99	finally
    //   104	106	99	finally
  }

  protected static PowerButton getLoadedButton(String paramString)
  {
    PowerButton localPowerButton1;
    synchronized (BUTTONS_LOADED)
    {
      if (BUTTONS_LOADED.containsKey(paramString))
      {
        PowerButton localPowerButton2 = (PowerButton)BUTTONS_LOADED.get(paramString);
        localPowerButton1 = localPowerButton2;
      }
      else
      {
        localPowerButton1 = null;
      }
    }
    return localPowerButton1;
  }

  // ERROR //
  public static void handleOnChangeUri(Uri paramUri)
  {
    // Byte code:
    //   0: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   3: astore_1
    //   4: aload_1
    //   5: monitorenter
    //   6: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   9: invokevirtual 182	java/util/HashMap:values	()Ljava/util/Collection;
    //   12: invokeinterface 188 1 0
    //   17: astore_3
    //   18: aload_3
    //   19: invokeinterface 194 1 0
    //   24: ifeq +42 -> 66
    //   27: aload_3
    //   28: invokeinterface 198 1 0
    //   33: checkcast 2	com/lidroid/systemui/quickpanel/PowerButton
    //   36: astore 4
    //   38: aload 4
    //   40: invokevirtual 225	com/lidroid/systemui/quickpanel/PowerButton:getObservedUris	()Ljava/util/List;
    //   43: aload_0
    //   44: invokeinterface 234 2 0
    //   49: ifeq -31 -> 18
    //   52: aload 4
    //   54: aload_0
    //   55: invokevirtual 251	com/lidroid/systemui/quickpanel/PowerButton:onChangeUri	(Landroid/net/Uri;)V
    //   58: goto -40 -> 18
    //   61: astore_2
    //   62: aload_1
    //   63: monitorexit
    //   64: aload_2
    //   65: athrow
    //   66: aload_1
    //   67: monitorexit
    //   68: return
    //
    // Exception table:
    //   from	to	target	type
    //   6	64	61	finally
    //   66	68	61	finally
  }

  // ERROR //
  public static void handleOnReceive(Context paramContext, Intent paramIntent)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 258	android/content/Intent:getAction	()Ljava/lang/String;
    //   4: astore_2
    //   5: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   8: astore_3
    //   9: aload_3
    //   10: monitorenter
    //   11: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   14: invokevirtual 182	java/util/HashMap:values	()Ljava/util/Collection;
    //   17: invokeinterface 188 1 0
    //   22: astore 5
    //   24: aload 5
    //   26: invokeinterface 194 1 0
    //   31: ifeq +44 -> 75
    //   34: aload 5
    //   36: invokeinterface 198 1 0
    //   41: checkcast 2	com/lidroid/systemui/quickpanel/PowerButton
    //   44: astore 6
    //   46: aload 6
    //   48: invokevirtual 201	com/lidroid/systemui/quickpanel/PowerButton:getBroadcastIntentFilter	()Landroid/content/IntentFilter;
    //   51: aload_2
    //   52: invokevirtual 213	android/content/IntentFilter:hasAction	(Ljava/lang/String;)Z
    //   55: ifeq -31 -> 24
    //   58: aload 6
    //   60: aload_0
    //   61: aload_1
    //   62: invokevirtual 261	com/lidroid/systemui/quickpanel/PowerButton:onReceive	(Landroid/content/Context;Landroid/content/Intent;)V
    //   65: goto -41 -> 24
    //   68: astore 4
    //   70: aload_3
    //   71: monitorexit
    //   72: aload 4
    //   74: athrow
    //   75: aload_3
    //   76: monitorexit
    //   77: return
    //
    // Exception table:
    //   from	to	target	type
    //   11	72	68	finally
    //   75	77	68	finally
  }

  public static boolean loadButton(String paramString, View paramView)
  {
    if ((BUTTONS.containsKey(paramString)) && (paramView != null));
    while (true)
    {
      synchronized (BUTTONS_LOADED)
      {
        if (!BUTTONS_LOADED.containsKey(paramString))
          continue;
        ((PowerButton)BUTTONS_LOADED.get(paramString)).setupButton(paramView);
        i = 1;
        return i;
        try
        {
          PowerButton localPowerButton = (PowerButton)((Class)BUTTONS.get(paramString)).newInstance();
          localPowerButton.setupButton(paramView);
          BUTTONS_LOADED.put(paramString, localPowerButton);
        }
        catch (Exception localException)
        {
          Log.e("PowerButton", "Error loading button: " + paramString, localException);
        }
      }
      int i = 0;
    }
  }

  public static void setGlobalOnClickListener(View.OnClickListener paramOnClickListener)
  {
    GLOBAL_ON_CLICK_LISTENER = paramOnClickListener;
  }

  public static void setGlobalOnLongClickListener(View.OnLongClickListener paramOnLongClickListener)
  {
    GLOBAL_ON_LONG_CLICK_LISTENER = paramOnLongClickListener;
  }

  // ERROR //
  public static void unloadAllButtons()
  {
    // Byte code:
    //   0: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   3: astore_0
    //   4: aload_0
    //   5: monitorenter
    //   6: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   9: invokevirtual 182	java/util/HashMap:values	()Ljava/util/Collection;
    //   12: invokeinterface 188 1 0
    //   17: astore_2
    //   18: aload_2
    //   19: invokeinterface 194 1 0
    //   24: ifeq +24 -> 48
    //   27: aload_2
    //   28: invokeinterface 198 1 0
    //   33: checkcast 2	com/lidroid/systemui/quickpanel/PowerButton
    //   36: aconst_null
    //   37: invokevirtual 269	com/lidroid/systemui/quickpanel/PowerButton:setupButton	(Landroid/view/View;)V
    //   40: goto -22 -> 18
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    //   48: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   51: invokevirtual 300	java/util/HashMap:clear	()V
    //   54: aload_0
    //   55: monitorexit
    //   56: return
    //
    // Exception table:
    //   from	to	target	type
    //   6	46	43	finally
    //   48	56	43	finally
  }

  public static void unloadButton(String paramString)
  {
    synchronized (BUTTONS_LOADED)
    {
      if (BUTTONS_LOADED.containsKey(paramString))
      {
        ((PowerButton)BUTTONS_LOADED.get(paramString)).setupButton(null);
        BUTTONS_LOADED.remove(paramString);
      }
      return;
    }
  }

  // ERROR //
  public static void updateAllButtons()
  {
    // Byte code:
    //   0: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   3: astore_0
    //   4: aload_0
    //   5: monitorenter
    //   6: getstatic 137	com/lidroid/systemui/quickpanel/PowerButton:BUTTONS_LOADED	Ljava/util/HashMap;
    //   9: invokevirtual 182	java/util/HashMap:values	()Ljava/util/Collection;
    //   12: invokeinterface 188 1 0
    //   17: astore_2
    //   18: aload_2
    //   19: invokeinterface 194 1 0
    //   24: ifeq +23 -> 47
    //   27: aload_2
    //   28: invokeinterface 198 1 0
    //   33: checkcast 2	com/lidroid/systemui/quickpanel/PowerButton
    //   36: invokevirtual 308	com/lidroid/systemui/quickpanel/PowerButton:update	()V
    //   39: goto -21 -> 18
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    //   47: aload_0
    //   48: monitorexit
    //   49: return
    //
    // Exception table:
    //   from	to	target	type
    //   6	45	42	finally
    //   47	49	42	finally
  }

  private void updateImageView(int paramInt1, int paramInt2)
  {
    ((ImageView)this.mView.findViewById(paramInt1)).setImageResource(paramInt2);
  }

  private void updateImageView(int paramInt, Drawable paramDrawable)
  {
    ((ImageView)this.mView.findViewById(paramInt)).setImageDrawable(paramDrawable);
  }

  protected IntentFilter getBroadcastIntentFilter()
  {
    return new IntentFilter();
  }

  protected List<Uri> getObservedUris()
  {
    return new ArrayList();
  }

  protected abstract int getText();

  protected abstract boolean handleLongClick();

  protected void onChange()
  {
  }

  protected void onChangeUri(Uri paramUri)
  {
  }

  protected void onReceive(Context paramContext, Intent paramIntent)
  {
  }

  protected void setupButton(View paramView)
  {
    this.mView = paramView;
    if (this.mView != null)
    {
      this.mView.setTag(this.mType);
      this.mView.setOnClickListener(this.mClickListener);
      this.mView.setOnLongClickListener(this.mLongClickListener);
    }
  }

  protected abstract void toggleState();

  protected void update()
  {
    updateState();
    updateView();
  }

  protected abstract void updateState();

  protected void updateText()
  {
    if (this.mView != null)
      ((TextView)this.mView.findViewById(50724885)).setText(getText());
  }

  protected void updateView()
  {
    this.mViewUpdateHandler.sendEmptyMessage(0);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.PowerButton
 * JD-Core Version:    0.6.0
 */