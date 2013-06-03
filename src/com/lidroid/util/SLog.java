package com.lidroid.util;

import android.util.Log;

public class SLog
{
  private static final boolean DEBUG = true;

  public static void d(String paramString1, String paramString2)
  {
    Log.d(paramString1, "-----------------------------------------------------");
    Log.d(paramString1, paramString2);
    Log.d(paramString1, "-----------------------------------------------------");
  }

  public static void i(String paramString1, String paramString2)
  {
    Log.d(paramString1, "-----------------------------------------------------");
    Log.i(paramString1, paramString2);
    Log.d(paramString1, "-----------------------------------------------------");
  }

  public static void w(String paramString1, String paramString2)
  {
    Log.d(paramString1, "-----------------------------------------------------");
    Log.w(paramString1, paramString2);
    Log.d(paramString1, "-----------------------------------------------------");
  }

  public void e(String paramString1, String paramString2)
  {
    Log.d(paramString1, "-----------------------------------------------------");
    Log.e(paramString1, paramString2);
    Log.d(paramString1, "-----------------------------------------------------");
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.util.SLog
 * JD-Core Version:    0.6.0
 */