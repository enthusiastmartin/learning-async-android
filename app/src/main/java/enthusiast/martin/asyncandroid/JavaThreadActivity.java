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

  public void runJavaThreadExample(){
    JavaThread javaThread = new JavaThread(new MessageHandler.Callback() {
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

    logState(javaThread);
  }

  private void logState(Thread t){
    if ( t.isAlive() ){
      threadStateView.setText("Running");
    }else{
      threadStateView.setText("Stopped");
    }
  }
}
