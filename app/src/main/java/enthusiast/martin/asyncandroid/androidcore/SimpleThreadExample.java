package enthusiast.martin.asyncandroid.androidcore;

import android.os.Handler;
import android.os.Message;

/**
 * Created by martin on 8/30/17.
 */

public class SimpleThreadExample {

  private MyThread myThread;

  public SimpleThreadExample(Handler handler){
    myThread = new MyThread(handler);
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
