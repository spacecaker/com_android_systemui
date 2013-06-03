package com.android.systemui.statusbar;

import android.app.Notification;
import android.os.IBinder;
import android.view.View;
import com.android.internal.statusbar.StatusBarNotification;
import java.util.ArrayList;

public class NotificationData
{
  private final ArrayList<Entry> mEntries = new ArrayList();

  private int chooseIndex(long paramLong)
  {
    int i = this.mEntries.size();
    int j = 0;
    if (j < i)
      if (((Entry)this.mEntries.get(j)).notification.notification.when <= paramLong);
    for (int k = j; ; k = i)
    {
      return k;
      j++;
      break;
    }
  }

  public int add(IBinder paramIBinder, StatusBarNotification paramStatusBarNotification, View paramView1, View paramView2, View paramView3, StatusBarIconView paramStatusBarIconView)
  {
    Entry localEntry = new Entry();
    localEntry.key = paramIBinder;
    localEntry.notification = paramStatusBarNotification;
    localEntry.row = paramView1;
    localEntry.content = paramView2;
    localEntry.expanded = paramView3;
    localEntry.icon = paramStatusBarIconView;
    int i = chooseIndex(paramStatusBarNotification.notification.when);
    this.mEntries.add(i, localEntry);
    return i;
  }

  public int findEntry(IBinder paramIBinder)
  {
    int i = this.mEntries.size();
    int j = 0;
    if (j < i)
      if (((Entry)this.mEntries.get(j)).key != paramIBinder);
    for (int k = j; ; k = -1)
    {
      return k;
      j++;
      break;
    }
  }

  public Entry getEntryAt(int paramInt)
  {
    return (Entry)this.mEntries.get(paramInt);
  }

  public boolean hasClearableItems()
  {
    int i = this.mEntries.size();
    int j = 0;
    if (j < i)
    {
      Entry localEntry = (Entry)this.mEntries.get(j);
      if ((localEntry.expanded == null) || ((0x20 & localEntry.notification.notification.flags) != 0));
    }
    for (int k = 1; ; k = 0)
    {
      return k;
      j++;
      break;
    }
  }

  public boolean hasVisibleItems()
  {
    int i = this.mEntries.size();
    int j = 0;
    if (j < i)
      if (((Entry)this.mEntries.get(j)).expanded == null);
    for (int k = 1; ; k = 0)
    {
      return k;
      j++;
      break;
    }
  }

  public Entry remove(IBinder paramIBinder)
  {
    int i = this.mEntries.size();
    int j = 0;
    Entry localEntry2;
    if (j < i)
    {
      localEntry2 = (Entry)this.mEntries.get(j);
      if (localEntry2.key == paramIBinder)
        this.mEntries.remove(j);
    }
    for (Entry localEntry1 = localEntry2; ; localEntry1 = null)
    {
      return localEntry1;
      j++;
      break;
    }
  }

  public int size()
  {
    return this.mEntries.size();
  }

  public static final class Entry
  {
    public View content;
    public View expanded;
    public StatusBarIconView icon;
    public IBinder key;
    public StatusBarNotification notification;
    public View row;
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.NotificationData
 * JD-Core Version:    0.6.0
 */