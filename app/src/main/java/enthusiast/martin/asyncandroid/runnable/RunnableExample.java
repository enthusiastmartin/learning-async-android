package enthusiast.martin.asyncandroid.runnable;

import android.util.Log;

/**
 * Created by martin on 8/28/17.
 */

public class RunnableExample implements Runnable {

  @Override
  public void run() {
    Log.d(RunnableExample.class.getName(), "Runnable " + Thread.currentThread().getName());
  }
}
