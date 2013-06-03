package com.android.systemui.statusbar.policy;

import android.widget.TextView;
import java.text.DateFormatSymbols;

final class i
{
  private TextView a;
  private String b;
  private String c;

  i()
  {
    String[] arrayOfString = new DateFormatSymbols().getAmPmStrings();
    this.b = arrayOfString[0];
    this.c = arrayOfString[1];
  }

  final void a(boolean paramBoolean)
  {
    TextView localTextView;
    if (this.a != null)
    {
      localTextView = this.a;
      if (!paramBoolean)
        break label24;
    }
    label24: for (int i = 0; ; i = 8)
    {
      localTextView.setVisibility(i);
      return;
    }
  }

  final void b(boolean paramBoolean)
  {
    TextView localTextView;
    if (this.a != null)
    {
      localTextView = this.a;
      if (!paramBoolean)
        break label27;
    }
    label27: for (String str = this.b; ; str = this.c)
    {
      localTextView.setText(str);
      return;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.policy.i
 * JD-Core Version:    0.6.0
 */