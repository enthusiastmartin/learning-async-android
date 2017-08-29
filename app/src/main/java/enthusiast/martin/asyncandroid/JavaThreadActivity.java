package enthusiast.martin.asyncandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import enthusiast.martin.asyncandroid.thread.JavaThread;
import enthusiast.martin.asyncandroid.thread.MessageHandler;
import enthusiast.martin.asyncandroid.thread.MessageQueue;

public class JavaThreadActivity extends AppCompatActivity {

  private static final String TAG = JavaThreadActivity.class.getName();

  private JavaThread javaThread;

  @BindView(R.id.edittext)
  EditText editText;

  @BindView(R.id.thread_state_view)
  TextView threadStateView;

  @BindView(R.id.message_text)
  TextView messageView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_java_thread);

    ButterKnife.bind(this);

    runJavaThreadExample();
  }

  @OnClick(R.id.sendbtn)
  public void sendMessage(){
    MessageQueue.queue.push(editText.getText().toString());
  }

  @OnClick(R.id.stop_btn)
  public void stopThread(){
    if ( javaThread != null ){
      javaThread.stopIt();
      javaThread = null;
      logState();
    }
  }

  @OnClick(R.id.start_btn)
  public void startThread(){
    if ( javaThread == null ){
      runJavaThreadExample();
    }
  }

  public void runJavaThreadExample(){
    javaThread = new JavaThread(new MessageHandler.Callback() {
      @Override
      public void handleMessage(final String msg) {

        Log.i(TAG, "Handle message thread: " + Thread.currentThread().getName());

        /**
         * To update the view
         */
        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            Log.i(TAG, "And this is on main thread? Current thread:  " + Thread.currentThread().getName());
            messageView.setText(msg);
          }
        });
      }
    });

    javaThread.start();

    logState();
  }

  private void logState(){
    if ( javaThread == null || ! javaThread.isAlive()){
      threadStateView.setText("Stopped");
    }else{
      threadStateView.setText("Running");
    }
  }
}
