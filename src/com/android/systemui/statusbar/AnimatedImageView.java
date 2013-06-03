package com.android.systemui.statusbar;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews.RemoteView;

@RemoteViews.RemoteView
public class AnimatedImageView extends ImageView
{
  AnimationDrawable mAnim;
  boolean mAttached;

  public AnimatedImageView(Context paramContext)
  {
    super(paramContext);
  }

  public AnimatedImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void updateAnim()
  {
    Drawable localDrawable = getDrawable();
    if ((this.mAttached) && (this.mAnim != null))
      this.mAnim.stop();
    if ((localDrawable instanceof AnimationDrawable))
    {
      this.mAnim = ((AnimationDrawable)localDrawable);
      if (isShown())
        this.mAnim.start();
    }
    while (true)
    {
      return;
      this.mAnim = null;
    }
  }

  public void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    this.mAttached = true;
  }

  public void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    if (this.mAnim != null)
      this.mAnim.stop();
    this.mAttached = false;
  }

  protected void onVisibilityChanged(View paramView, int paramInt)
  {
    super.onVisibilityChanged(paramView, paramInt);
    if (this.mAnim != null)
    {
      if (!isShown())
        break label28;
      this.mAnim.start();
    }
    while (true)
    {
      return;
      label28: this.mAnim.stop();
    }
  }

  public void setImageDrawable(Drawable paramDrawable)
  {
    super.setImageDrawable(paramDrawable);
    updateAnim();
  }

  @RemotableViewMethod
  public void setImageResource(int paramInt)
  {
    super.setImageResource(paramInt);
    updateAnim();
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.AnimatedImageView
 * JD-Core Version:    0.6.0
 */