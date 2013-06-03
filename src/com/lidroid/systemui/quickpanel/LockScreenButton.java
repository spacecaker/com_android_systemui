package com.lidroid.systemui.quickpanel;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

public class LockScreenButton extends PowerButton
{
  private static Boolean LOCK_SCREEN_STATE = null;
  private KeyguardManager.KeyguardLock mLock = null;

  public LockScreenButton()
  {
    this.mType = "toggleLockScreen";
  }

  private KeyguardManager.KeyguardLock getLock(Context paramContext)
  {
    if (this.mLock == null)
      this.mLock = ((KeyguardManager)paramContext.getSystemService("keyguard")).newKeyguardLock("keyguard");
    return this.mLock;
  }

  private static boolean getState()
  {
    if (LOCK_SCREEN_STATE == null)
      LOCK_SCREEN_STATE = Boolean.valueOf(true);
    return LOCK_SCREEN_STATE.booleanValue();
  }

  protected int getText()
  {
    return 51052618;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.SECURITY_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    getState();
    if (LOCK_SCREEN_STATE == null)
    {
      Toast localToast = Toast.makeText(localContext, "Not yet initialized", 1);
      localToast.setGravity(17, localToast.getXOffset() / 2, localToast.getYOffset() / 2);
      localToast.show();
    }
    while (true)
    {
      update();
      return;
      getLock(localContext);
      if (this.mLock == null)
        continue;
      if (LOCK_SCREEN_STATE.booleanValue())
      {
        this.mLock.disableKeyguard();
        LOCK_SCREEN_STATE = Boolean.valueOf(false);
        continue;
      }
      this.mLock.reenableKeyguard();
      LOCK_SCREEN_STATE = Boolean.valueOf(true);
    }
  }

  protected void updateState()
  {
    getState();
    if (LOCK_SCREEN_STATE == null)
    {
      this.mIcon = 50462843;
      this.mState = 5;
    }
    while (true)
    {
      return;
      if (LOCK_SCREEN_STATE.booleanValue())
      {
        this.mIcon = 50462844;
        this.mState = 1;
        continue;
      }
      this.mIcon = 50462843;
      this.mState = 2;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.LockScreenButton
 * JD-Core Version:    0.6.0
 */