package enthusiast.martin.asyncandroid.androidcore;

import android.os.Handler;
import android.os.Message;

/**
 * Created by martin on 8/30/17.
 */

public class HandlerThreadExample {

  private MyHandlerThread myThread;

  public HandlerThreadExample(Handler handler){
    //myThread = new MyThread(handler);
    myThread = new MyHandlerThread(handler);
  }

  public void runIt(){
    myThread.start();
  }

  public void stopIt(){
    myThread.interrupt();
  }

  public void submitMessage(String msg){
    Message message = Message.obtain();
    message.obj = msg;

    myThread.getHandler().sendMessage(message);
  }
}
