package com.lidroid.systemui.quickpanel;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings.Secure;
import android.view.View;
import com.lidroid.util.SLog;
import java.util.ArrayList;
import java.util.List;

public class GPSButton extends PowerButton
{
  private static final List<Uri> OBSERVED_URIS = new ArrayList();

  static
  {
    OBSERVED_URIS.add(Settings.Secure.getUriFor("location_providers_allowed"));
  }

  public GPSButton()
  {
    this.mType = "toggleGPS";
  }

  private static boolean getGpsState(Context paramContext)
  {
    return Settings.Secure.isLocationProviderEnabled(paramContext.getContentResolver(), "gps");
  }

  protected List<Uri> getObservedUris()
  {
    return OBSERVED_URIS;
  }

  protected int getText()
  {
    return 51052614;
  }

  protected boolean handleLongClick()
  {
    Intent localIntent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
    localIntent.addCategory("android.intent.category.DEFAULT");
    localIntent.addFlags(268435456);
    this.mView.getContext().startActivity(localIntent);
    return true;
  }

  protected void toggleState()
  {
    Context localContext = this.mView.getContext();
    ContentResolver localContentResolver = localContext.getContentResolver();
    if (!getGpsState(localContext));
    for (boolean bool = true; ; bool = false)
    {
      Settings.Secure.setLocationProviderEnabled(localContentResolver, "gps", bool);
      return;
    }
  }

  protected void updateState()
  {
    if (getGpsState(this.mView.getContext()))
    {
      this.mIcon = 50462841;
      this.mState = 1;
      SLog.d("PowerButton", "GPS: on");
    }
    while (true)
    {
      return;
      this.mIcon = 50462840;
      this.mState = 2;
      SLog.d("PowerButton", "GPS: off");
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.GPSButton
 * JD-Core Version:    0.6.0
 */