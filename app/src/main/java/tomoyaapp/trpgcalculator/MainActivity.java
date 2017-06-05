package tomoyaapp.trpgcalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{
    private TextView titleTextView;
    private TextView mainTextView;
    private EditText editText;
    private ImageButton imageButton;
    private Typeface typeface, typeface2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        typeface = Typeface.createFromAsset(getAssets(), "genkai_mincho.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "aroy.ttf");

        titleTextView = (TextView)findViewById(R.id.titleIv);
        mainTextView = (TextView)findViewById(R.id.mainTv);
        editText = (EditText) findViewById(R.id.editText);
        imageButton = (ImageButton)findViewById(R.id.btn);
        imageButton.setOnClickListener(this);


        titleTextView.setTypeface(typeface);
        mainTextView.setTypeface(typeface2);
    }

    @Override
    public void onClick(View view){

    }
}
