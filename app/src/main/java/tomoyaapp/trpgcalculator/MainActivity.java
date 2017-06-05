package tomoyaapp.trpgcalculator;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.icu.text.IDNA;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView titleTextView;
    private TextView mainTextView;
    private TextView[] popupTextView;
    private EditText editText;
    private ImageButton imageButton;
    private Typeface typeface, typeface2, typeface3;
    private AlphaAnimation animationFeedIn, animationFeedOut;
    private int targetNumber;
    private int inputNumber;
    private boolean byInt = true;
    private boolean byEx = false;
    private Random rand;
    private String[] message;
    private int[] TEST = {1, 25, 50, 75, 98, 99};
    private int idx;

    private static final int Fumble = 95;
    private static final int Critical = 6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        popupTextView = new TextView[3];
        animationFeedIn = new AlphaAnimation(0, 1);
        animationFeedOut = new AlphaAnimation(1, 0);
        rand = new Random();
        message = new String[2];

        typeface = Typeface.createFromAsset(getAssets(), "genkai_mincho.ttf");
        typeface2 = Typeface.createFromAsset(getAssets(), "aroy.ttf");
        typeface3 = Typeface.createFromAsset(getAssets(), "mountain.ttf");

        titleTextView = (TextView) findViewById(R.id.titleIv);
        mainTextView = (TextView) findViewById(R.id.mainTv);
        editText = (EditText) findViewById(R.id.editText);
        imageButton = (ImageButton) findViewById(R.id.btn);
        popupTextView[0] = (TextView) findViewById(R.id.popUp);
        popupTextView[1] = (TextView) findViewById(R.id.popUp2);
        popupTextView[2] = (TextView) findViewById(R.id.popUp3);

        imageButton.setOnClickListener(this);
        titleTextView.setTypeface(typeface);
        mainTextView.setTypeface(typeface2);



        for (int i = 0; i < popupTextView.length; i++) {
            popupTextView[i].setTypeface(typeface3);
            popupTextView[i].setVisibility(View.INVISIBLE);
        }
    }

    private void judgeEX() {
        Log.d("DF", "JUDGE!!!");

        if (inputNumber < Critical) {

            popupTextView[2].setText("Critical!!!");
            feedIO(popupTextView[2], 1000);
        }
        if (Fumble < inputNumber) {

            popupTextView[2].setText("Fumble!!!");
            feedIO(popupTextView[2], 1000);
        }
    }

    @Override
    public void onClick(View view) {
        try {
            targetNumber = Integer.parseInt(editText.getText().toString());
        } catch (Exception e) {
            Log.d("ERROR", "NOT NUMBER!!!");
            resetEditText("Contains characters!");
            byInt = false;
        }

        if (byInt)
            if (!(0 <= targetNumber && targetNumber < 100)) {
                resetEditText("Only 0 ~ 99 !");
            } else {
                inputNumber = rand.nextInt(100)+1;

                inputNumber = TEST[idx];
                idx++;
                if (idx > TEST.length -1)
                    idx = 0;

                mainTextView.setText(inputNumber + "");

                message[0] = "Success!!!";
                message[1] = "Failure...";

                if (inputNumber < Critical) {
                    message[0] += "\nCritical!!!";
                    message[1] += "\nCritical!!!";
                    //popupTextView[2].setText("Critical!!!");
                    //feedIO(popupTextView[2], 1000);
                }
                if (Fumble < inputNumber) {
                    message[0] += "\nFumble!!!";
                    message[1] += "\nFumble!!!";
                    //popupTextView[2].setText("Fumble!!!");
                    //feedIO(popupTextView[2], 1000);
                }
                if (inputNumber <= targetNumber) {
                    Log.d("SUCCESS", inputNumber + "");
                    popupTextView[0].setText(message[0]);
                    feedIO(popupTextView[0], 1000);
                }
                if (targetNumber < inputNumber) {
                    Log.d("FAILURE", inputNumber + "");
                    popupTextView[1].setText(message[1]);
                    feedIO(popupTextView[1], 1000);
                }




            }

        byInt = true;
    }


    private void resetEditText(String errorMessage) {
        editText.setText(1+"");
        targetNumber = 1;
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void feedIO(View view, int duration) {
        animationFeedIn.cancel();
        animationFeedOut.cancel();
        animationFeedIn.setDuration(duration);
        animationFeedOut.setDuration(duration);

        view.startAnimation(animationFeedIn);
        view.startAnimation(animationFeedOut);
    }

}

