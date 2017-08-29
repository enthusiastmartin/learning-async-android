package enthusiast.martin.asyncandroid.thread;

import android.util.Log;

/**
 * Created by martin on 8/28/17.
 */

public class JavaThread extends Thread {

  private boolean isStopping = false;

  private static final String TAG = JavaThread.class.getName();

  private MessageHandler messageHandler;

  public JavaThread() {}

  public JavaThread(MessageHandler.Callback callback){
    this.messageHandler = new MessageHandler(callback);
  }

  @Override
  public void run() {
    super.run();
    Log.i(TAG, "Java Thread + " + Thread.currentThread().getName());
    String msg;
    while (!isStopping) {
      while ((msg = MessageQueue.queue.poll()) != null) {
        if (messageHandler != null) {
          messageHandler.sendMessage(msg);
        }
      }
    }
  }

  public void stopIt(){
    synchronized (this){
      isStopping = true;
    }
  }
}
