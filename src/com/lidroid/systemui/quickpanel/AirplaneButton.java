package com.lidroid.systemui.quickpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.System;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

public class AirplaneButton extends PowerButton
{
  private static final List<Uri> OBSERVED_URIS = new ArrayList();

  static
  {
    OBSERVED_URIS.add(Settings.System.getUriFor("airplane_mode_on"));
  }

  public AirplaneButton()
  {
    this.mType = "toggleAirplane";
  }

  private static boolean getState(Context paramContext)
  {
    if (Settings.System.getInt(paramContext.getContentResolver(), "airplane_mode_on", 0) == 1);
    for (int i = 1; ; i = 0)
      return i;
  }

  protected List<Uri> getObservedUris()
  {
    return OBSERVED_URIS;
  }

  protected int getText()
  {
    return 51052619;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.AIRPLANE_MODE_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    boolean bool1 = getState(localContext);
    ContentResolver localContentResolver = localContext.getContentResolver();
    int i;
    Intent localIntent;
    if (bool1)
    {
      i = 0;
      Settings.System.putInt(localContentResolver, "airplane_mode_on", i);
      localIntent = new Intent("android.intent.action.AIRPLANE_MODE");
      if (bool1)
        break label75;
    }
    label75: for (boolean bool2 = true; ; bool2 = false)
    {
      localIntent.putExtra("state", bool2);
      localContext.sendBroadcast(localIntent);
      return;
      i = 1;
      break;
    }
  }

  protected void updateState()
  {
    if (getState(this.mView.getContext()))
      this.mIcon = 50462826;
    for (this.mState = 1; ; this.mState = 2)
    {
      return;
      this.mIcon = 50462825;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.AirplaneButton
 * JD-Core Version:    0.6.0
 */