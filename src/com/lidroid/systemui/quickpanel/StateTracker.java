package com.lidroid.systemui.quickpanel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public abstract class StateTracker
{
  private Boolean mActualState = null;
  private boolean mDeferredStateChangeRequestNeeded = false;
  private boolean mInTransition = false;
  private Boolean mIntendedState = null;

  public abstract int getActualState(Context paramContext);

  public final int getTriState(Context paramContext)
  {
    int i;
    switch (getActualState(paramContext))
    {
    default:
      i = 5;
    case 2:
    case 1:
    }
    while (true)
    {
      return i;
      i = 2;
      continue;
      i = 1;
    }
  }

  public final boolean isTurningOn()
  {
    if ((this.mIntendedState != null) && (this.mIntendedState.booleanValue()));
    for (int i = 1; ; i = 0)
      return i;
  }

  public abstract void onActualStateChange(Context paramContext, Intent paramIntent);

  protected abstract void requestStateChange(Context paramContext, boolean paramBoolean);

  protected final void setCurrentState(Context paramContext, int paramInt)
  {
    boolean bool = this.mInTransition;
    switch (paramInt)
    {
    default:
      if ((!bool) || (this.mInTransition) || (!this.mDeferredStateChangeRequestNeeded))
        break;
      Log.v("StateTracker", "processing deferred state change");
      if ((this.mActualState != null) && (this.mIntendedState != null) && (this.mIntendedState.equals(this.mActualState)))
        Log.v("StateTracker", "... but intended state matches, so no changes.");
    case 2:
    case 1:
    case 3:
    case 4:
    }
    while (true)
    {
      this.mDeferredStateChangeRequestNeeded = false;
      return;
      this.mInTransition = false;
      this.mActualState = Boolean.valueOf(false);
      break;
      this.mInTransition = false;
      this.mActualState = Boolean.valueOf(true);
      break;
      this.mInTransition = true;
      this.mActualState = Boolean.valueOf(false);
      break;
      this.mInTransition = true;
      this.mActualState = Boolean.valueOf(true);
      break;
      if (this.mIntendedState == null)
        continue;
      this.mInTransition = true;
      requestStateChange(paramContext, this.mIntendedState.booleanValue());
    }
  }

  public final void toggleState(Context paramContext)
  {
    int i = getTriState(paramContext);
    boolean bool = false;
    switch (i)
    {
    case 3:
    case 4:
    default:
      this.mIntendedState = Boolean.valueOf(bool);
      if (!this.mInTransition)
        break;
      this.mDeferredStateChangeRequestNeeded = true;
    case 1:
    case 2:
    case 5:
    }
    while (true)
    {
      return;
      bool = false;
      break;
      bool = true;
      break;
      if (this.mIntendedState == null)
        break;
      if (!this.mIntendedState.booleanValue());
      for (bool = true; ; bool = false)
        break;
      this.mInTransition = true;
      requestStateChange(paramContext, bool);
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.StateTracker
 * JD-Core Version:    0.6.0
 */