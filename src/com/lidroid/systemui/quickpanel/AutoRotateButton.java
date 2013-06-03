package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.System;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class AutoRotateButton extends PowerButton
{
  private static final List<Uri> OBSERVED_URIS = new ArrayList();

  static
  {
    OBSERVED_URIS.add(Settings.System.getUriFor("accelerometer_rotation"));
  }

  public AutoRotateButton()
  {
    this.mType = "toggleAutoRotate";
  }

  private static int getOrientationState(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "accelerometer_rotation", 0);
  }

  protected List<Uri> getObservedUris()
  {
    return OBSERVED_URIS;
  }

  protected int getText()
  {
    return 51052612;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.DISPLAY_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    if (getOrientationState(localContext) == 0)
      Settings.System.putInt(localContext.getContentResolver(), "accelerometer_rotation", 1);
    while (true)
    {
      return;
      Settings.System.putInt(localContext.getContentResolver(), "accelerometer_rotation", 0);
    }
  }

  protected void updateState()
  {
    if (getOrientationState(this.mView.getContext()) == 1)
      this.mIcon = 50462846;
    for (this.mState = 1; ; this.mState = 2)
    {
      return;
      this.mIcon = 50462845;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.AutoRotateButton
 * JD-Core Version:    0.6.0
 */