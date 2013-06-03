package com.lidroid.systemui.quickpanel;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.Window;

public class ShutdownButton extends PowerButton
{
  public ShutdownButton()
  {
    this.mType = "toggleShutdown";
    this.mState = 2;
  }

  private void shutdown()
  {
    Context localContext = this.mView.getContext();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContext);
    localBuilder.setIcon(17301543);
    localBuilder.setTitle(51052601);
    localBuilder.setMessage(51052624);
    localBuilder.setPositiveButton(17039370, new ShutdownButton.1(this, localContext));
    localBuilder.setNegativeButton(17039360, new ShutdownButton.2(this));
    AlertDialog localAlertDialog = localBuilder.create();
    localAlertDialog.getWindow().setType(2008);
    localAlertDialog.show();
  }

  protected int getText()
  {
    return 51052622;
  }

  protected boolean handleLongClick()
  {
    return false;
  }

  protected void toggleState()
  {
    shutdown();
  }

  protected void updateState()
  {
    this.mState = 2;
    this.mIcon = 50462857;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.ShutdownButton
 * JD-Core Version:    0.6.0
 */