package com.android.systemui.statusbar;

import android.app.Notification;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.TextSwitcher;
import android.widget.TextView;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.statusbar.StatusBarNotification;
import com.android.internal.util.CharSequences;
import java.util.ArrayList;

public abstract class Ticker
{
  private Runnable mAdvanceTicker = new Runnable()
  {
    public void run()
    {
      while (Ticker.this.mSegments.size() > 0)
      {
        Ticker.Segment localSegment = (Ticker.Segment)Ticker.this.mSegments.get(0);
        if (localSegment.first)
          Ticker.this.mIconSwitcher.setImageDrawable(localSegment.icon);
        CharSequence localCharSequence = localSegment.advance();
        if (localCharSequence == null)
        {
          Ticker.this.mSegments.remove(0);
          continue;
        }
        Ticker.this.mTextSwitcher.setText(localCharSequence);
        Ticker.this.scheduleAdvance();
      }
      if (Ticker.this.mSegments.size() == 0)
        Ticker.this.tickerDone();
    }
  };
  private Context mContext;
  private Handler mHandler = new Handler();
  private ImageSwitcher mIconSwitcher;
  private TextPaint mPaint;
  private ArrayList<Segment> mSegments = new ArrayList();
  private TextSwitcher mTextSwitcher;
  private View mTickerView;

  Ticker(Context paramContext, StatusBarView paramStatusBarView)
  {
    this.mContext = paramContext;
    this.mTickerView = paramStatusBarView.findViewById(2131296278);
    this.mIconSwitcher = ((ImageSwitcher)paramStatusBarView.findViewById(2131296279));
    this.mIconSwitcher.setInAnimation(AnimationUtils.loadAnimation(paramContext, 17432614));
    this.mIconSwitcher.setOutAnimation(AnimationUtils.loadAnimation(paramContext, 17432615));
    this.mTextSwitcher = ((TextSwitcher)paramStatusBarView.findViewById(2131296280));
    this.mTextSwitcher.setInAnimation(AnimationUtils.loadAnimation(paramContext, 17432614));
    this.mTextSwitcher.setOutAnimation(AnimationUtils.loadAnimation(paramContext, 17432615));
    this.mPaint = ((TextView)this.mTextSwitcher.getChildAt(0)).getPaint();
  }

  private void scheduleAdvance()
  {
    this.mHandler.postDelayed(this.mAdvanceTicker, 3000L);
  }

  void addEntry(StatusBarNotification paramStatusBarNotification)
  {
    int i = this.mSegments.size();
    if (i > 0)
    {
      Segment localSegment4 = (Segment)this.mSegments.get(0);
      if ((!paramStatusBarNotification.pkg.equals(localSegment4.notification.pkg)) || (paramStatusBarNotification.notification.icon != localSegment4.notification.notification.icon) || (paramStatusBarNotification.notification.iconLevel != localSegment4.notification.notification.iconLevel) || (!CharSequences.equals(localSegment4.notification.notification.tickerText, paramStatusBarNotification.notification.tickerText)));
    }
    while (true)
    {
      return;
      Segment localSegment1 = new Segment(paramStatusBarNotification, StatusBarIconView.getIcon(this.mContext, new StatusBarIcon(paramStatusBarNotification.pkg, paramStatusBarNotification.notification.icon, paramStatusBarNotification.notification.iconLevel, 0)), paramStatusBarNotification.notification.tickerText);
      for (int j = 0; j < this.mSegments.size(); j++)
      {
        Segment localSegment3 = (Segment)this.mSegments.get(j);
        if ((paramStatusBarNotification.id != localSegment3.notification.id) || (!paramStatusBarNotification.pkg.equals(localSegment3.notification.pkg)))
          continue;
        ArrayList localArrayList = this.mSegments;
        int k = j - 1;
        localArrayList.remove(j);
        j = k;
      }
      this.mSegments.add(localSegment1);
      if ((i != 0) || (this.mSegments.size() <= 0))
        continue;
      Segment localSegment2 = (Segment)this.mSegments.get(0);
      localSegment2.first = false;
      this.mIconSwitcher.setAnimateFirstView(false);
      this.mIconSwitcher.reset();
      this.mIconSwitcher.setImageDrawable(localSegment2.icon);
      this.mTextSwitcher.setAnimateFirstView(false);
      this.mTextSwitcher.reset();
      this.mTextSwitcher.setText(localSegment2.getText());
      tickerStarting();
      scheduleAdvance();
    }
  }

  void halt()
  {
    this.mHandler.removeCallbacks(this.mAdvanceTicker);
    this.mSegments.clear();
    tickerHalting();
  }

  void reflowText()
  {
    if (this.mSegments.size() > 0)
    {
      CharSequence localCharSequence = ((Segment)this.mSegments.get(0)).getText();
      this.mTextSwitcher.setCurrentText(localCharSequence);
    }
  }

  abstract void tickerDone();

  abstract void tickerHalting();

  abstract void tickerStarting();

  private final class Segment
  {
    int current;
    boolean first;
    Drawable icon;
    int next;
    StatusBarNotification notification;
    CharSequence text;

    Segment(StatusBarNotification paramDrawable, Drawable paramCharSequence, CharSequence arg4)
    {
      this.notification = paramDrawable;
      this.icon = paramCharSequence;
      Object localObject;
      this.text = localObject;
      int i = 0;
      int j = localObject.length();
      while ((i < j) && (!TextUtils.isGraphic(localObject.charAt(i))))
        i++;
      this.current = i;
      this.next = i;
      this.first = true;
    }

    CharSequence advance()
    {
      this.first = false;
      int i = this.next;
      int j = this.text.length();
      while ((i < j) && (!TextUtils.isGraphic(this.text.charAt(i))))
        i++;
      Object localObject;
      if (i >= j)
        localObject = null;
      while (true)
      {
        return localObject;
        CharSequence localCharSequence1 = this.text.subSequence(i, this.text.length());
        StaticLayout localStaticLayout = getLayout(localCharSequence1);
        int k = localStaticLayout.getLineCount();
        label183: for (int m = 0; ; m++)
        {
          if (m >= k)
            break label189;
          int n = localStaticLayout.getLineStart(m);
          int i1 = localStaticLayout.getLineEnd(m);
          if (m == k - 1);
          for (this.next = j; ; this.next = (i + localStaticLayout.getLineStart(m + 1)))
          {
            CharSequence localCharSequence2 = rtrim(localCharSequence1, n, i1);
            if (localCharSequence2 == null)
              break label183;
            this.current = (i + n);
            localObject = localCharSequence2;
            break;
          }
        }
        label189: this.current = j;
        localObject = null;
      }
    }

    StaticLayout getLayout(CharSequence paramCharSequence)
    {
      int i = Ticker.this.mTextSwitcher.getWidth() - Ticker.this.mTextSwitcher.getPaddingLeft() - Ticker.this.mTextSwitcher.getPaddingRight();
      return new StaticLayout(paramCharSequence, Ticker.this.mPaint, i, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, true);
    }

    CharSequence getText()
    {
      if (this.current > this.text.length());
      CharSequence localCharSequence1;
      int i;
      int j;
      int k;
      for (CharSequence localCharSequence2 = null; ; localCharSequence2 = rtrim(localCharSequence1, j, k))
      {
        return localCharSequence2;
        localCharSequence1 = this.text.subSequence(this.current, this.text.length());
        StaticLayout localStaticLayout = getLayout(localCharSequence1);
        i = localStaticLayout.getLineCount();
        if (i <= 0)
          break;
        j = localStaticLayout.getLineStart(0);
        k = localStaticLayout.getLineEnd(0);
        this.next = (k + this.current);
      }
      throw new RuntimeException("lineCount=" + i + " current=" + this.current + " text=" + this.text);
    }

    CharSequence rtrim(CharSequence paramCharSequence, int paramInt1, int paramInt2)
    {
      while ((paramInt2 > paramInt1) && (!TextUtils.isGraphic(paramCharSequence.charAt(paramInt2 - 1))))
        paramInt2--;
      if (paramInt2 > paramInt1);
      for (CharSequence localCharSequence = paramCharSequence.subSequence(paramInt1, paramInt2); ; localCharSequence = null)
        return localCharSequence;
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.Ticker
 * JD-Core Version:    0.6.0
 */