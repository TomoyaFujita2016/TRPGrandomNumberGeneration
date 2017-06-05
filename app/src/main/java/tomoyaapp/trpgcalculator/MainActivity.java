package tomoyaapp.trpgcalculator;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    private TextView titleImageView;
    private Typeface typeface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        typeface = Typeface.createFromAsset(getAssets(), "genkai_mincho.ttf");

        titleImageView = (TextView)findViewById(R.id.titleIv);

        titleImageView.setTypeface(typeface);
    }
}
