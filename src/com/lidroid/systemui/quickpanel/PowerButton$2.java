package com.lidroid.systemui.quickpanel;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

class PowerButton$2
  implements View.OnClickListener
{
  public void onClick(View paramView)
  {
    String str = (String)paramView.getTag();
    Iterator localIterator = PowerButton.access$100().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (!((String)localEntry.getKey()).equals(str))
        continue;
      ((PowerButton)localEntry.getValue()).toggleState();
    }
    if (PowerButton.access$200() != null)
      PowerButton.access$200().onClick(paramView);
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.lidroid.systemui.quickpanel.PowerButton.2
 * JD-Core Version:    0.6.0
 */