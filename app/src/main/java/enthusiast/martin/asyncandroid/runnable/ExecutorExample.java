package enthusiast.martin.asyncandroid.runnable;

import android.support.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by martin on 8/31/17.
 */

public class ExecutorExample {

  private ExecutorService executorService;

  public ExecutorExample(){
  }

  public void start(){
    this.executorService = Executors.newSingleThreadExecutor();
  }

  public void submit(@NonNull Runnable runnable){
    this.executorService.submit(runnable);
  }

  public void shutdown(){
    this.executorService.shutdown();
    try {
      this.executorService.awaitTermination(5, TimeUnit.SECONDS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }finally {
      this.executorService.shutdownNow();
    }
  }
}
