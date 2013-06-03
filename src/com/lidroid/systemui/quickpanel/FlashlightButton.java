package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.lidroid.util.LedController;

public class FlashlightButton extends PowerButton
{
  private boolean mGotInfo = false;
  private boolean mIsFlashSupported;
  private LedController mLedController = LedController.getInstance();

  public FlashlightButton()
  {
    this.mType = "toggleFlashlight";
    this.mState = 2;
  }

  private void startFlashlightActivity()
  {
    Context localContext = this.mView.getContext();
    Intent localIntent = new Intent(localContext, FlashlightActivity.class);
    localIntent.addFlags(268435456);
    localContext.startActivity(localIntent);
  }

  protected int getText()
  {
    return 51052620;
  }

  protected boolean handleLongClick()
  {
    return false;
  }

  protected void toggleState()
  {
    if (!this.mGotInfo)
    {
      this.mIsFlashSupported = this.mLedController.isFlashSupported();
      this.mGotInfo = true;
    }
    if (this.mIsFlashSupported)
      if (this.mState == 1)
      {
        this.mState = 2;
        this.mLedController.off();
        update();
      }
    while (true)
    {
      return;
      this.mState = 1;
      this.mLedController.on();
      break;
      this.mState = 2;
      startFlashlightActivity();
    }
  }

  protected void updateState()
  {
    switch (this.mState)
    {
    default:
    case 1:
    }
    for (this.mIcon = 50462838; ; this.mIcon = 50462839)
      return;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.FlashlightButton
 * JD-Core Version:    0.6.0
 */