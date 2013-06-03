package com.android.systemui.statusbar;

import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.android.internal.statusbar.IStatusBar.Stub;
import com.android.internal.statusbar.StatusBarIcon;
import com.android.internal.statusbar.StatusBarIconList;
import com.android.internal.statusbar.StatusBarNotification;

class CommandQueue extends IStatusBar.Stub
{
  private Callbacks mCallbacks;
  private Handler mHandler = new H(null);
  private StatusBarIconList mList;

  public CommandQueue(Callbacks paramCallbacks, StatusBarIconList paramStatusBarIconList)
  {
    this.mCallbacks = paramCallbacks;
    this.mList = paramStatusBarIconList;
  }

  public void addNotification(IBinder paramIBinder, StatusBarNotification paramStatusBarNotification)
  {
    synchronized (this.mList)
    {
      NotificationQueueEntry localNotificationQueueEntry = new NotificationQueueEntry(null);
      localNotificationQueueEntry.key = paramIBinder;
      localNotificationQueueEntry.notification = paramStatusBarNotification;
      this.mHandler.obtainMessage(131072, 0, 0, localNotificationQueueEntry).sendToTarget();
      return;
    }
  }

  public void animateCollapse()
  {
    synchronized (this.mList)
    {
      this.mHandler.removeMessages(393216);
      this.mHandler.obtainMessage(393216, 2, 0, null).sendToTarget();
      return;
    }
  }

  public void animateExpand()
  {
    synchronized (this.mList)
    {
      this.mHandler.removeMessages(393216);
      this.mHandler.obtainMessage(393216, 1, 0, null).sendToTarget();
      return;
    }
  }

  public void disable(int paramInt)
  {
    synchronized (this.mList)
    {
      this.mHandler.removeMessages(327680);
      this.mHandler.obtainMessage(327680, paramInt, 0, null).sendToTarget();
      return;
    }
  }

  public void hideCallOnGoingView()
  {
    synchronized (this.mList)
    {
      this.mHandler.obtainMessage(536870912).sendToTarget();
      return;
    }
  }

  public void removeIcon(int paramInt)
  {
    StatusBarIconList localStatusBarIconList = this.mList;
    monitorenter;
    int i = 0x10000 | paramInt;
    try
    {
      this.mHandler.removeMessages(i);
      this.mHandler.obtainMessage(i, 2, 0, null).sendToTarget();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void removeNotification(IBinder paramIBinder)
  {
    synchronized (this.mList)
    {
      this.mHandler.obtainMessage(262144, 0, 0, paramIBinder).sendToTarget();
      return;
    }
  }

  public void setIcon(int paramInt, StatusBarIcon paramStatusBarIcon)
  {
    StatusBarIconList localStatusBarIconList = this.mList;
    monitorenter;
    int i = 0x10000 | paramInt;
    try
    {
      this.mHandler.removeMessages(i);
      this.mHandler.obtainMessage(i, 1, 0, paramStatusBarIcon.clone()).sendToTarget();
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public void showCallOnGoingView()
  {
    synchronized (this.mList)
    {
      this.mHandler.obtainMessage(268435456).sendToTarget();
      return;
    }
  }

  public void updateNotification(IBinder paramIBinder, StatusBarNotification paramStatusBarNotification)
  {
    synchronized (this.mList)
    {
      NotificationQueueEntry localNotificationQueueEntry = new NotificationQueueEntry(null);
      localNotificationQueueEntry.key = paramIBinder;
      localNotificationQueueEntry.notification = paramStatusBarNotification;
      this.mHandler.obtainMessage(196608, 0, 0, localNotificationQueueEntry).sendToTarget();
      return;
    }
  }

  public static abstract interface Callbacks
  {
    public abstract void addIcon(String paramString, int paramInt1, int paramInt2, StatusBarIcon paramStatusBarIcon);

    public abstract void addNotification(IBinder paramIBinder, StatusBarNotification paramStatusBarNotification);

    public abstract void animateCollapse();

    public abstract void animateExpand();

    public abstract void disable(int paramInt);

    public abstract void hideCallOnGoingView();

    public abstract void removeIcon(String paramString, int paramInt1, int paramInt2);

    public abstract void removeNotification(IBinder paramIBinder);

    public abstract void showCallOnGoingView();

    public abstract void updateIcon(String paramString, int paramInt1, int paramInt2, StatusBarIcon paramStatusBarIcon1, StatusBarIcon paramStatusBarIcon2);

    public abstract void updateNotification(IBinder paramIBinder, StatusBarNotification paramStatusBarNotification);
  }

  private final class H extends Handler
  {
    private H()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (0xFFFF0000 & paramMessage.what)
      {
      default:
      case 65536:
      case 131072:
      case 196608:
      case 262144:
      case 327680:
      case 393216:
      case 268435456:
      case 536870912:
      }
      while (true)
      {
        return;
        int i = 0xFFFF & paramMessage.what;
        int j = CommandQueue.this.mList.getViewIndex(i);
        switch (paramMessage.arg1)
        {
        default:
          break;
        case 1:
          StatusBarIcon localStatusBarIcon1 = (StatusBarIcon)paramMessage.obj;
          StatusBarIcon localStatusBarIcon2 = CommandQueue.this.mList.getIcon(i);
          if (localStatusBarIcon2 == null)
          {
            CommandQueue.this.mList.setIcon(i, localStatusBarIcon1);
            CommandQueue.this.mCallbacks.addIcon(CommandQueue.this.mList.getSlot(i), i, j, localStatusBarIcon1);
            continue;
          }
          CommandQueue.this.mList.setIcon(i, localStatusBarIcon1);
          CommandQueue.this.mCallbacks.updateIcon(CommandQueue.this.mList.getSlot(i), i, j, localStatusBarIcon2, localStatusBarIcon1);
          break;
        case 2:
          if (CommandQueue.this.mList.getIcon(i) == null)
            continue;
          CommandQueue.this.mList.removeIcon(i);
          CommandQueue.this.mCallbacks.removeIcon(CommandQueue.this.mList.getSlot(i), i, j);
          continue;
          CommandQueue.NotificationQueueEntry localNotificationQueueEntry2 = (CommandQueue.NotificationQueueEntry)paramMessage.obj;
          CommandQueue.this.mCallbacks.addNotification(localNotificationQueueEntry2.key, localNotificationQueueEntry2.notification);
          continue;
          CommandQueue.NotificationQueueEntry localNotificationQueueEntry1 = (CommandQueue.NotificationQueueEntry)paramMessage.obj;
          CommandQueue.this.mCallbacks.updateNotification(localNotificationQueueEntry1.key, localNotificationQueueEntry1.notification);
          continue;
          CommandQueue.this.mCallbacks.removeNotification((IBinder)paramMessage.obj);
          continue;
          CommandQueue.this.mCallbacks.disable(paramMessage.arg1);
          continue;
          if (paramMessage.arg1 == 1)
          {
            CommandQueue.this.mCallbacks.animateExpand();
            continue;
          }
          CommandQueue.this.mCallbacks.animateCollapse();
          continue;
          CommandQueue.this.mCallbacks.showCallOnGoingView();
          continue;
          CommandQueue.this.mCallbacks.hideCallOnGoingView();
        }
      }
    }
  }

  private class NotificationQueueEntry
  {
    IBinder key;
    StatusBarNotification notification;

    private NotificationQueueEntry()
    {
    }
  }
}

/* Location:           C:\apk\tools\classes-dex2jar.jar
 * Qualified Name:     com.android.systemui.statusbar.CommandQueue
 * JD-Core Version:    0.6.0
 */