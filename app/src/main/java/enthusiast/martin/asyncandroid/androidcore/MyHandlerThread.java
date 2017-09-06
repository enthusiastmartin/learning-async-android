package enthusiast.martin.asyncandroid.androidcore;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by martin on 8/31/17.
 */

public class MyHandlerThread extends HandlerThread {

  private MyThreadHandler myThreadHandler;

  private Handler mainThreadHandler;

  public MyHandlerThread(Handler handler) {
    super("myhandlerthread");
    this.mainThreadHandler = handler;
  }

  public Handler getHandler(){
    return this.myThreadHandler;
  }

  @Override
  protected void onLooperPrepared() {
    super.onLooperPrepared();
    this.myThreadHandler = new MyThreadHandler(this);
  }

  protected void sendMessageBack(String msg){
    Message message = Message.obtain();
    message.obj = msg;
    //this.mainHandler.sendMessage(message);

    Message message1 = Message.obtain(mainThreadHandler);
    message1.obj = msg;
    message1.sendToTarget();

  }


  public static class MyThreadHandler extends Handler {
    private WeakReference<MyHandlerThread> myHandlerThreadWeakReference;

    public MyThreadHandler(MyHandlerThread myThread){
      this.myHandlerThreadWeakReference = new WeakReference<>(myThread);
    }

    @Override
    public void handleMessage(Message msg) {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if ( this.myHandlerThreadWeakReference.get() != null ){
        myHandlerThreadWeakReference.get().sendMessageBack((String)msg.obj);
      }
    }
  }
}
