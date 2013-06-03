package com.android.systemui.statusbar;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.Slog;
import android.view.ViewDebug.ExportedProperty;
import com.android.internal.statusbar.StatusBarIcon;

public class StatusBarIconView extends AnimatedImageView
{
  private StatusBarIcon mIcon;
  private Drawable mNumberBackground;
  private Paint mNumberPain;
  private String mNumberText;
  private int mNumberX;
  private int mNumberY;

  @ViewDebug.ExportedProperty
  private String mSlot;

  public StatusBarIconView(Context paramContext, String paramString)
  {
    super(paramContext);
    Resources localResources = paramContext.getResources();
    this.mSlot = paramString;
    this.mNumberPain = new Paint();
    this.mNumberPain.setTextAlign(Paint.Align.CENTER);
    this.mNumberPain.setColor(localResources.getColor(2130837657));
    this.mNumberPain.setAntiAlias(true);
    this.mNumberPain.setTextSize(10.0F);
  }

  public static Drawable getIcon(Context paramContext, StatusBarIcon paramStatusBarIcon)
  {
    if (paramStatusBarIcon.iconPackage != null);
    Object localObject2;
    label80: StringBuilder localStringBuilder;
    while (true)
    {
      try
      {
        Resources localResources2 = paramContext.getPackageManager().getResourcesForApplication(paramStatusBarIcon.iconPackage);
        localResources1 = localResources2;
        if (paramStatusBarIcon.iconId != 0)
          break label80;
        localObject2 = null;
        return localObject2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Slog.e("StatusBarService", "Icon package not found: " + paramStatusBarIcon.iconPackage);
        localObject2 = null;
        continue;
      }
      Resources localResources1 = paramContext.getResources();
      continue;
      try
      {
        Drawable localDrawable = localResources1.getDrawable(paramStatusBarIcon.iconId);
        localObject2 = localDrawable;
      }
      catch (RuntimeException localRuntimeException)
      {
        localStringBuilder = new StringBuilder().append("Icon not found in ");
        if (paramStatusBarIcon.iconPackage == null);
      }
    }
    for (Object localObject1 = Integer.valueOf(paramStatusBarIcon.iconId); ; localObject1 = "<system>")
    {
      Slog.w("StatusBarService", localObject1 + ": " + Integer.toHexString(paramStatusBarIcon.iconId));
      localObject2 = null;
      break;
    }
  }

  private Drawable getIcon(StatusBarIcon paramStatusBarIcon)
  {
    return getIcon(getContext(), paramStatusBarIcon);
  }

  private static boolean streq(String paramString1, String paramString2)
  {
    boolean bool = false;
    if (paramString1 == paramString2);
    for (bool = true; ; bool = paramString1.equals(paramString2))
      do
        return bool;
      while (((paramString1 == null) && (paramString2 != null)) || ((paramString1 != null) && (paramString2 == null)));
  }

  protected void debug(int paramInt)
  {
    super.debug(paramInt);
    Log.d("View", debugIndent(paramInt) + "slot=" + this.mSlot);
    Log.d("View", debugIndent(paramInt) + "icon=" + this.mIcon);
  }

  public StatusBarIcon getStatusBarIcon()
  {
    return this.mIcon;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.mNumberBackground != null)
    {
      this.mNumberBackground.draw(paramCanvas);
      paramCanvas.drawText(this.mNumberText, this.mNumberX, this.mNumberY, this.mNumberPain);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.mNumberBackground != null)
      placeNumber();
  }

  void placeNumber()
  {
    String str = Integer.toString(this.mIcon.number);
    this.mNumberText = str;
    int i = getWidth();
    int j = getHeight();
    Rect localRect = new Rect();
    if (this.mIcon.number > 99)
      this.mNumberPain.setTextSize(9.5F);
    this.mNumberPain.getTextBounds(str, 0, str.length(), localRect);
    int k = localRect.right - localRect.left;
    int m = localRect.bottom - localRect.top;
    this.mNumberBackground.getPadding(localRect);
    int n = k + localRect.left + localRect.right;
    if (n < this.mNumberBackground.getMinimumWidth())
      n = this.mNumberBackground.getMinimumWidth();
    this.mNumberX = (i - localRect.right - (n - localRect.right - localRect.left) / 2);
    int i1 = m + localRect.top + localRect.bottom;
    if (i1 < this.mNumberBackground.getMinimumWidth())
      i1 = this.mNumberBackground.getMinimumWidth();
    this.mNumberY = (j - localRect.bottom - (i1 - localRect.top - m - localRect.bottom) / 2);
    this.mNumberBackground.setBounds(i - n, j - i1, i, j);
  }

  public boolean set(StatusBarIcon paramStatusBarIcon)
  {
    int i;
    int j;
    label60: int k;
    if ((this.mIcon != null) && (streq(this.mIcon.iconPackage, paramStatusBarIcon.iconPackage)) && (this.mIcon.iconId == paramStatusBarIcon.iconId))
    {
      i = 1;
      if ((i == 0) || (this.mIcon.iconLevel != paramStatusBarIcon.iconLevel))
        break label172;
      j = 1;
      if ((this.mIcon == null) || (this.mIcon.visible != paramStatusBarIcon.visible))
        break label177;
      k = 1;
      label84: if ((this.mIcon == null) || (this.mIcon.number != paramStatusBarIcon.number))
        break label183;
    }
    Drawable localDrawable;
    int n;
    label172: label177: label183: for (int m = 1; ; m = 0)
    {
      this.mIcon = paramStatusBarIcon.clone();
      if (i != 0)
        break label195;
      localDrawable = getIcon(paramStatusBarIcon);
      if (localDrawable != null)
        break label189;
      Slog.w("StatusBarService", "No icon for slot " + this.mSlot);
      n = 0;
      return n;
      i = 0;
      break;
      j = 0;
      break label60;
      k = 0;
      break label84;
    }
    label189: setImageDrawable(localDrawable);
    label195: if (j == 0)
      setImageLevel(paramStatusBarIcon.iconLevel);
    if (m == 0)
    {
      if (paramStatusBarIcon.number > 0)
      {
        if (this.mNumberBackground == null)
          this.mNumberBackground = getContext().getResources().getDrawable(2130837514);
        placeNumber();
        label247: invalidate();
      }
    }
    else if (k == 0)
      if (!paramStatusBarIcon.visible)
        break label291;
    label291: for (int i1 = 0; ; i1 = 8)
    {
      setVisibility(i1);
      n = 1;
      break;
      this.mNumberBackground = null;
      this.mNumberText = null;
      break label247;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.StatusBarIconView
 * JD-Core Version:    0.6.0
 */