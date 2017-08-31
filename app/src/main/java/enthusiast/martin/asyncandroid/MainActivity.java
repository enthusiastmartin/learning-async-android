package enthusiast.martin.asyncandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);
  }

  @OnClick(R.id.java_thread_btn)
  public void runJavaThreadExample(){
    startActivity(new Intent(this, JavaThreadActivity.class));
  }

  @OnClick(R.id.runnable_btn)
  public void runRunnableExample(){
    startActivity(new Intent(this, RunnableExampleActivity.class));
  }

  @OnClick(R.id.executor_btn)
  public void runExecutorEXample(){
    startActivity(new Intent(this, RunnableEXecutorExampleActivity.class));
  }
  @OnClick(R.id.core_btn)
  public void runCoreExample(){
    startActivity(new Intent(this, CoreExample.class));
  }
}


