package com.android.systemui.statusbar;

import android.app.StatusBarManager;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import com.android.internal.policy.impl.RecentApplicationsDialog;

public final class RecentAppButton extends TextView
  implements View.OnClickListener, View.OnLongClickListener
{
  private RecentApplicationsDialog mRecentAppsDialog;

  public RecentAppButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void showRecentAppsDialog()
  {
    if (this.mRecentAppsDialog == null)
      this.mRecentAppsDialog = new RecentApplicationsDialog(this.mContext);
    this.mRecentAppsDialog.show();
  }

  protected void onAttachedToWindow()
  {
    super.onAttachedToWindow();
    Log.e("RecentAppButton", "onAttachedToWindow()");
    setOnClickListener(this);
    setOnLongClickListener(this);
  }

  public void onClick(View paramView)
  {
    Log.d("JKay", "RecentAppButton onClick activated!");
    Context localContext = this.mContext;
    showRecentAppsDialog();
    ((StatusBarManager)localContext.getSystemService("statusbar")).collapse();
  }

  protected void onDetachedFromWindow()
  {
    super.onDetachedFromWindow();
    Log.e("RecentAppButton", "onDetachedFromWindow()");
  }

  public boolean onLongClick(View paramView)
  {
    Log.d("JKay", "RecentAppButton Sending intent!");
    Context localContext = this.mContext;
    showRecentAppsDialog();
    ((StatusBarManager)localContext.getSystemService("statusbar")).collapse();
    return true;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.RecentAppButton
 * JD-Core Version:    0.6.0
 */