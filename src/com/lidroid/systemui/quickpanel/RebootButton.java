package com.lidroid.systemui.quickpanel;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.view.Window;

public class RebootButton extends PowerButton
{
  private String action = "now";

  public RebootButton()
  {
    this.mType = "toggleReboot";
    this.mState = 2;
  }

  private void reboot()
  {
    this.action = "now";
    Context localContext = this.mView.getContext();
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContext);
    localBuilder.setIcon(17301543);
    localBuilder.setTitle(51052601);
    localBuilder.setSingleChoiceItems(50659332, 0, new RebootButton.1(this, localContext));
    localBuilder.setPositiveButton(17039370, new RebootButton.2(this, localContext));
    localBuilder.setNegativeButton(17039360, new RebootButton.3(this));
    AlertDialog localAlertDialog = localBuilder.create();
    localAlertDialog.getWindow().setType(2008);
    localAlertDialog.show();
  }

  protected int getText()
  {
    return 51052621;
  }

  protected boolean handleLongClick()
  {
    return false;
  }

  protected void toggleState()
  {
    reboot();
  }

  protected void updateState()
  {
    this.mState = 2;
    this.mIcon = 50462848;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.RebootButton
 * JD-Core Version:    0.6.0
 */