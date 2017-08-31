package enthusiast.martin.asyncandroid.runnable;

import android.util.Log;

/**
 * Created by martin on 8/28/17.
 */

public class RunnableExample implements Runnable {

  public interface RunnableHandler {
    void sendMessage(String msg);
  }

  private RunnableHandler handler;


  public RunnableExample(RunnableHandler handler) {
    this.handler = handler;
  }

  @Override
  public void run() {
    Log.d(RunnableExample.class.getName(), "Runnable " + Thread.currentThread().getName());

    String msg;
    while (!Thread.currentThread().isInterrupted()){
      while ((msg = RunnableQueue.queue.poll()) != null) {
        handler.sendMessage(msg);
      }
    }
  }
}
