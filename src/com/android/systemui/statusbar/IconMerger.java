package com.android.systemui.statusbar;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.android.internal.statusbar.StatusBarIcon;

public class IconMerger extends LinearLayout
{
  private int mIconSize;
  private StatusBarIcon mMoreIcon = new StatusBarIcon(null, 2130837537, 0);
  private StatusBarIconView mMoreView;

  public IconMerger(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mIconSize = paramContext.getResources().getDimensionPixelSize(17104901);
    this.mMoreView = new StatusBarIconView(paramContext, "more");
    this.mMoreView.set(this.mMoreIcon);
    addView(this.mMoreView, 0, new LinearLayout.LayoutParams(this.mIconSize, this.mIconSize));
  }

  public void addView(StatusBarIconView paramStatusBarIconView, int paramInt)
  {
    if (paramInt == 0)
      throw new RuntimeException("Attempt to put view before the more view: " + paramStatusBarIconView);
    addView(paramStatusBarIconView, paramInt, new LinearLayout.LayoutParams(this.mIconSize, this.mIconSize));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    int i = paramInt3 - paramInt1;
    int j = getChildCount();
    int k = -1;
    int m = j - 1;
    StatusBarIconView localStatusBarIconView1;
    int i1;
    int i2;
    label80: View localView1;
    if (m >= 0)
    {
      View localView2 = getChildAt(m);
      if (localView2.getVisibility() != 8)
        k = localView2.getRight();
    }
    else
    {
      localStatusBarIconView1 = this.mMoreView;
      n = -1;
      i1 = -1;
      i2 = 0;
      if (i2 >= j)
        break label137;
      localView1 = getChildAt(i2);
      if (localView1 != localStatusBarIconView1)
        break label120;
      i1 = i2 + 1;
    }
    label120: 
    do
    {
      i2++;
      break label80;
      m--;
      break;
    }
    while (localView1.getVisibility() == 8);
    int n = localView1.getLeft();
    label137: if ((localStatusBarIconView1 == null) || (i1 < 0));
    while (true)
    {
      return;
      int i3 = 0;
      if (k - n <= i)
      {
        i3 = n - localStatusBarIconView1.getLeft();
        n -= i3;
        k -= i3;
        localStatusBarIconView1.layout(0, localStatusBarIconView1.getTop(), 0, localStatusBarIconView1.getBottom());
      }
      int i4 = k - paramInt3;
      int i5 = -1;
      int i6 = i3 + (n + i4);
      int i7 = 0;
      int i8 = i1;
      if (i8 < j)
      {
        StatusBarIconView localStatusBarIconView2 = (StatusBarIconView)getChildAt(i8);
        int i9;
        int i10;
        int i11;
        if (localStatusBarIconView2.getVisibility() != 8)
        {
          i9 = localStatusBarIconView2.getLeft();
          i10 = localStatusBarIconView2.getRight();
          if (i9 >= i6)
            break label335;
          localStatusBarIconView2.layout(0, localStatusBarIconView2.getTop(), 0, localStatusBarIconView2.getBottom());
          i11 = localStatusBarIconView2.getStatusBarIcon().number;
          if (i11 != 0)
            break label320;
          i7++;
        }
        while (true)
        {
          i8++;
          break;
          label320: if (i11 <= 0)
            continue;
          i7 += i11;
          continue;
          label335: if (i5 < 0)
            i5 = i9 - n;
          localStatusBarIconView2.layout(i9 - i5, localStatusBarIconView2.getTop(), i10 - i5, localStatusBarIconView2.getBottom());
        }
      }
      this.mMoreIcon.number = i7;
      this.mMoreView.set(this.mMoreIcon);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.IconMerger
 * JD-Core Version:    0.6.0
 */