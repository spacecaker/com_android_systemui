package com.lidroid.systemui.quickpanel;

import android.view.View;
import android.view.View.OnLongClickListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

class PowerButton$3
  implements View.OnLongClickListener
{
  public boolean onLongClick(View paramView)
  {
    boolean bool = false;
    String str = (String)paramView.getTag();
    Iterator localIterator = PowerButton.access$100().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (!((String)localEntry.getKey()).endsWith(str))
        continue;
      bool = ((PowerButton)localEntry.getValue()).handleLongClick();
    }
    if ((bool) && (PowerButton.access$300() != null))
      PowerButton.access$300().onLongClick(paramView);
    return bool;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.PowerButton.3
 * JD-Core Version:    0.6.0
 */