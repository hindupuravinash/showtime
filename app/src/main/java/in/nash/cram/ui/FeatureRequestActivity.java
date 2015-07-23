package in.nash.cram.ui;
import android.os.Bundle;
import app.support.v7.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import in.nash.cram.R;

public class FeatureRequestActivity extends AppCompatActivity{
@Override
protected void onCreate(Bundle savedinstancestate){
super.onCreate(savedinstancestate);
setContentView(R.layout.activity_feature_request);
Button button = (Button) findViewById(R.id.btn_submit);
button.setOnClickListener(this);
}
@Override
public void onClick(View view){
if(view.getId()==R.id.btn_submit){
//TODO://Submit handle
}
}
}
