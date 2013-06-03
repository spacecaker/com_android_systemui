package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings.System;
import android.view.View;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ScreenTimeoutButton extends PowerButton
{
  private static final List<Uri> OBSERVED_URIS;
  private static final int SCREEN_TIMEOUT_HIGH = 120000;
  private static final int SCREEN_TIMEOUT_LOW = 30000;
  private static final int SCREEN_TIMEOUT_MAX = 600000;
  private static final int SCREEN_TIMEOUT_MIN = 15000;
  private static final int SCREEN_TIMEOUT_NORMAL = 60000;
  private static Toast TOAST = null;

  static
  {
    OBSERVED_URIS = new ArrayList();
    OBSERVED_URIS.add(Settings.System.getUriFor("screen_off_timeout"));
  }

  public ScreenTimeoutButton()
  {
    this.mType = "toggleScreenTimeout";
  }

  private static int getScreenTimeout(Context paramContext)
  {
    return Settings.System.getInt(paramContext.getContentResolver(), "screen_off_timeout", 0);
  }

  private static String timeoutToString(Context paramContext, int paramInt)
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = 51052625;
    arrayOfInt[1] = 51052626;
    arrayOfInt[2] = 51052627;
    Resources localResources = paramContext.getResources();
    int i = paramInt / 1000;
    int j = arrayOfInt[0];
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(i);
    String str = localResources.getString(j, arrayOfObject1);
    for (int k = 1; (k < arrayOfInt.length) && (i >= 60); k++)
    {
      i /= k * 60;
      int m = arrayOfInt[1];
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(i);
      str = localResources.getString(m, arrayOfObject2);
    }
    return str;
  }

  protected List<Uri> getObservedUris()
  {
    return OBSERVED_URIS;
  }

  protected int getText()
  {
    return 51052617;
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
    int i = getScreenTimeout(localContext);
    int j;
    if (i < 15000)
      j = 15000;
    while (true)
    {
      Settings.System.putInt(localContext.getContentResolver(), "screen_off_timeout", j);
      if (TOAST == null)
        TOAST = Toast.makeText(localContext, "", 1);
      TOAST.cancel();
      TOAST.setText(timeoutToString(localContext, j));
      TOAST.setGravity(17, TOAST.getXOffset() / 2, TOAST.getYOffset() / 2);
      TOAST.show();
      return;
      if (i < 30000)
      {
        j = 30000;
        continue;
      }
      if (i < 60000)
      {
        j = 60000;
        continue;
      }
      if (i < 120000)
      {
        j = 120000;
        continue;
      }
      if (i < 600000)
      {
        j = 600000;
        continue;
      }
      j = 15000;
    }
  }

  protected void updateState()
  {
    switch (getScreenTimeout(this.mView.getContext()))
    {
    default:
      this.mIcon = 50462856;
      this.mState = 2;
    case 15000:
    case 30000:
    case 60000:
    case 120000:
    case 600000:
    }
    while (true)
    {
      return;
      this.mIcon = 50462852;
      this.mState = 2;
      continue;
      this.mIcon = 50462855;
      this.mState = 5;
      continue;
      this.mIcon = 50462853;
      this.mState = 5;
      continue;
      this.mIcon = 50462854;
      this.mState = 5;
      continue;
      this.mIcon = 50462851;
      this.mState = 1;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.ScreenTimeoutButton
 * JD-Core Version:    0.6.0
 */