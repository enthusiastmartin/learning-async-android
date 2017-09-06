package enthusiast.martin.asyncandroid;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import enthusiast.martin.asyncandroid.androidcore.HandlerThreadExample;

/**
 * Created by martin on 8/30/17.
 */

public class CoreExample extends AppCompatActivity{

  @BindView(R.id.message_text)
  TextView messageView;

  @BindView(R.id.edittext)
  EditText editText;

  private HandlerThreadExample androidCoreExample;

  private Handler mainHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_java_thread);


    mainHandler = new Handler(){
      @Override
      public void handleMessage(Message msg) {
        messageView.setText((String)msg.obj);
      }
    };

    androidCoreExample = new HandlerThreadExample(mainHandler);

    ButterKnife.bind(this);
  }


  @OnClick(R.id.start_btn)
  public void startThread(){
    androidCoreExample.runIt();
  }

  @OnClick(R.id.stop_btn)
  public void stopExecutor(){
    androidCoreExample.stopIt();
  }

  @OnClick(R.id.sendbtn)
  public void sendMessage(){
    androidCoreExample.submitMessage(editText.getText().toString());
  }
}
