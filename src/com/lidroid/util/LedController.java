package com.lidroid.util;

import android.hardware.Camera;
import android.hardware.Camera.Parameters;

public class LedController
{
  private static final String TAG = "LedController";
  private static LedController sInstance;
  private Camera mCamera = null;

  public static LedController getInstance()
  {
    if (sInstance == null)
      sInstance = new LedController();
    return sInstance;
  }

  private void openCamera()
  {
    if (this.mCamera == null)
    {
      SLog.d("LedController", "openCamera()");
      this.mCamera = Camera.open();
    }
  }

  private void releaseCamera()
  {
    if (this.mCamera != null)
    {
      this.mCamera.stopPreview();
      this.mCamera.release();
      this.mCamera = null;
      SLog.d("LedController", "releaseCamera()");
    }
  }

  public boolean isFlashSupported()
  {
    openCamera();
    if (this.mCamera.getParameters().getSupportedFlashModes() != null);
    for (int i = 1; ; i = 0)
    {
      releaseCamera();
      return i;
    }
  }

  public void off()
  {
    if (this.mCamera != null)
    {
      Camera.Parameters localParameters = this.mCamera.getParameters();
      localParameters.setFlashMode("off");
      this.mCamera.setParameters(localParameters);
      releaseCamera();
    }
  }

  public void on()
  {
    openCamera();
    Camera.Parameters localParameters = this.mCamera.getParameters();
    localParameters.setFlashMode("torch");
    this.mCamera.setParameters(localParameters);
    this.mCamera.startPreview();
    this.mCamera.autoFocus(null);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.util.LedController
 * JD-Core Version:    0.6.0
 */