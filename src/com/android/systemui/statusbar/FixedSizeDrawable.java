package com.android.systemui.statusbar;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class FixedSizeDrawable extends Drawable
{
  int mBottom;
  Drawable mDrawable;
  int mLeft;
  int mRight;
  int mTop;

  FixedSizeDrawable(Drawable paramDrawable)
  {
    this.mDrawable = paramDrawable;
  }

  public void draw(Canvas paramCanvas)
  {
    this.mDrawable.draw(paramCanvas);
  }

  public int getOpacity()
  {
    return this.mDrawable.getOpacity();
  }

  public void setAlpha(int paramInt)
  {
    this.mDrawable.setAlpha(paramInt);
  }

  public void setBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mDrawable.setBounds(this.mLeft, this.mTop, this.mRight, this.mBottom);
  }

  public void setBounds(Rect paramRect)
  {
    this.mDrawable.setBounds(this.mLeft, this.mTop, this.mRight, this.mBottom);
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.mDrawable.setColorFilter(paramColorFilter);
  }

  public void setFixedBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLeft = paramInt1;
    this.mTop = paramInt2;
    this.mRight = paramInt3;
    this.mBottom = paramInt4;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.FixedSizeDrawable
 * JD-Core Version:    0.6.0
 */