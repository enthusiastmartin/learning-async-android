package enthusiast.martin.asyncandroid.thread;

/**
 * Created by martin on 8/29/17.
 */

public class MessageHandler {

  public interface Callback {
    void handleMessage(String msg);
  }

  private Callback msgCallback;

  public MessageHandler(Callback callback){
    this.msgCallback = callback;
  }

  public void sendMessage(String msg){
    if ( msgCallback !=null ){
      msgCallback.handleMessage(msg);
    }
  }
}
