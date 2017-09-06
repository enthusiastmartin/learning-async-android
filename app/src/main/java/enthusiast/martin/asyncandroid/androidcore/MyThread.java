package enthusiast.martin.asyncandroid.androidcore;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by martin on 8/31/17.
 */

public class MyThread extends Thread {

  private MyThreadHandler handler;

  private Handler mainHandler;

  public MyThread(Handler mainHandler){
    this.mainHandler = mainHandler;
  }

  public Handler getHandler(){
    return handler;
  }

  @Override
  public void run() {
    Looper.prepare();
    this.handler = new MyThreadHandler(this);
    Looper.loop();
  }

  protected void sendMessageBack(String msg){
    Message message = Message.obtain();
    message.obj = msg;
    //this.mainHandler.sendMessage(message);

    Message message1 = Message.obtain(mainHandler);
    message1.obj = msg;
    message1.sendToTarget();

  }

  public static class MyThreadHandler extends Handler{
    private WeakReference<MyThread> myThreadWeakReference;

    public MyThreadHandler(MyThread myThread){
      myThreadWeakReference = new WeakReference<MyThread>(myThread);
    }

    @Override
    public void handleMessage(Message msg) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if ( myThreadWeakReference.get() != null ){
        myThreadWeakReference.get().sendMessageBack((String)msg.obj);
      }
    }
  }
}
