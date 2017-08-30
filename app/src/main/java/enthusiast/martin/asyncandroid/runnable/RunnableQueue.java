package enthusiast.martin.asyncandroid.runnable;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by martin on 8/30/17.
 */

public class RunnableQueue {

  public static final BlockingDeque<String> queue = new LinkedBlockingDeque<>();
}
