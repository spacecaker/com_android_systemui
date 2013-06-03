package com.lidroid.systemui;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import com.lidroid.systemui.quickpanel.PowerWidget;

public class MainActivity extends Activity
{
  private PowerWidget mWidget;

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.mWidget.setupWidget();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130837504);
    this.mWidget = ((PowerWidget)findViewById(2130903040));
    this.mWidget.setupWidget();
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.MainActivity
 * JD-Core Version:    0.6.0
 */