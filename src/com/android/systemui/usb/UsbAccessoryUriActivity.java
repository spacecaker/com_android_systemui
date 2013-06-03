package com.android.systemui.usb;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.android.internal.app.AlertActivity;
import com.android.internal.app.AlertController.AlertParams;

public class UsbAccessoryUriActivity extends AlertActivity
  implements DialogInterface.OnClickListener
{
  private UsbAccessory mAccessory;
  private Uri mUri;

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    Intent localIntent;
    if (paramInt == -1)
    {
      localIntent = new Intent("android.intent.action.VIEW", this.mUri);
      localIntent.addCategory("android.intent.category.BROWSABLE");
      localIntent.addFlags(268435456);
    }
    try
    {
      startActivity(localIntent);
      finish();
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      while (true)
        Log.e("UsbAccessoryUriActivity", "startActivity failed for " + this.mUri);
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    Intent localIntent = getIntent();
    this.mAccessory = ((UsbAccessory)localIntent.getParcelableExtra("accessory"));
    String str1 = localIntent.getStringExtra("uri");
    Uri localUri;
    if (str1 == null)
    {
      localUri = null;
      this.mUri = localUri;
      if (this.mUri != null)
        break label89;
      Log.e("UsbAccessoryUriActivity", "could not parse Uri " + str1);
      finish();
    }
    while (true)
    {
      return;
      localUri = Uri.parse(str1);
      break;
      label89: String str2 = this.mUri.getScheme();
      if ((!"http".equals(str2)) && (!"https".equals(str2)))
      {
        Log.e("UsbAccessoryUriActivity", "Uri not http or https: " + this.mUri);
        finish();
        continue;
      }
      AlertController.AlertParams localAlertParams = this.mAlertParams;
      localAlertParams.mTitle = this.mAccessory.getDescription();
      if ((localAlertParams.mTitle == null) || (localAlertParams.mTitle.length() == 0))
        localAlertParams.mTitle = getString(2131165197);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.mUri;
      localAlertParams.mMessage = getString(2131165196, arrayOfObject);
      localAlertParams.mPositiveButtonText = getString(2131165198);
      localAlertParams.mNegativeButtonText = getString(17039360);
      localAlertParams.mPositiveButtonListener = this;
      localAlertParams.mNegativeButtonListener = this;
      setupAlert();
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.usb.UsbAccessoryUriActivity
 * JD-Core Version:    0.6.0
 */