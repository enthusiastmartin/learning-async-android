package enthusiast.martin.asyncandroid.androidcore;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by martin on 8/31/17.
 */

public class MyThread extends Thread {

  public Handler handler;

  private Handler mainHandler;

  public MyThread(Handler mainHandler){
    this.mainHandler = mainHandler;
  }

  @Override
  public void run() {
    Looper.prepare();


    handler = new Handler(){
      @Override
      public void handleMessage(Message msg) {
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        sendMessageBack((String)msg.obj);
      }
    };

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

}
