package enthusiast.martin.asyncandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import enthusiast.martin.asyncandroid.runnable.ExecutorExample;
import enthusiast.martin.asyncandroid.runnable.RunnableExample;
import enthusiast.martin.asyncandroid.runnable.RunnableQueue;

/**
 * Created by martin on 8/30/17.
 */

public class RunnableEXecutorExampleActivity extends AppCompatActivity{

  @BindView(R.id.message_text)
  TextView messageView;

  @BindView(R.id.edittext)
  EditText editText;

  private ExecutorExample executorExample;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_java_thread);

    executorExample = new ExecutorExample();

    ButterKnife.bind(this);
  }


  @OnClick(R.id.start_btn)
  public void startThread(){
    executorExample.start();
    executorExample.submit(new RunnableExample(new RunnableExample.RunnableHandler() {
      @Override
      public void sendMessage(final String msg) {

        runOnUiThread(new Runnable() {
          @Override
          public void run() {
            messageView.setText(msg);
          }
        });
      }
    }));
  }

  @OnClick(R.id.stop_btn)
  public void stopExecutor(){
    executorExample.shutdown();
  }

  @OnClick(R.id.sendbtn)
  public void sendMessage(){
    RunnableQueue.queue.push(editText.getText().toString());
  }

}
