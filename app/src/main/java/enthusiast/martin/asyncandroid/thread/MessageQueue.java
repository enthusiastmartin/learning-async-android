package enthusiast.martin.asyncandroid.thread;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by martin on 8/28/17.
 */

public class MessageQueue {

  public static final BlockingDeque<String> queue = new LinkedBlockingDeque<>();
}
