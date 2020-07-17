package shivani.com.example.hideandshow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goId;
    Button ansButton0;
    Button ansButton1;
    Button ansButton2;
    Button ansButton3;
    TextView sumTextView;
    TextView ansTextView;
    TextView finalTextView;
    TextView scoreTextView;
    TextView timerTextView;
    Button playButton;
    ArrayList<String > answers= new ArrayList<String>();
    int location,score=0,noq=0;
    public void playClick(View view){
        playButton.setVisibility(View.INVISIBLE);
        score=0;
        noq=0;
        scoreTextView.setText("0/0");
        timerTextView.setText("30s");
        ansTextView.setText(" ");
        finalTextView.setText(" ");
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished/1000) + "s");
            }

            @Override
            public void onFinish() {
                ansTextView.setText("Done");
                finalTextView.setText("your score is " + Integer.toString(score) + "/" + Integer.toString(noq));
                playButton.setVisibility(View.VISIBLE);
            }
        }.start();
        generateQuestion();
    }
    public void generateQuestion(){
        Random rand = new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b) );
        location=rand.nextInt(4);
        int ans=a+b;
        answers.clear();
        for(int i=0;i<4;++i)
        {
            if(i==location)
                answers.add(Integer.toString(ans));
            else {
                int l=rand.nextInt(41);
                while(l == ans)
                {
                    l=rand.nextInt(41);
                }
                answers.add(Integer.toString(l));
            }
        }
        ansButton0=(Button)findViewById(R.id.ansButton0);
        ansButton1=(Button)findViewById(R.id.ansButton1);
        ansButton2=(Button)findViewById(R.id.ansButton2);
        ansButton3=(Button)findViewById(R.id.ansButton3);
        ansButton0.setText(answers.get(0));
        ansButton1.setText(answers.get(1));
        ansButton2.setText(answers.get(2));
        ansButton3.setText(answers.get(3));
    }
    public void ansClick(View view){
        if(view.getTag().toString().equals(Integer.toString(location)))
        {ansTextView.setText("CORRECT");
         score++;
        }
        else ansTextView.setText("WRONG");
        noq++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(noq));
        generateQuestion();
    }
    public void goButton(View view){

        goId.setVisibility(View.INVISIBLE);
        playClick(findViewById(R.id.playButton));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goId=(Button) findViewById(R.id.goId);
        sumTextView=(TextView)findViewById(R.id.sumTextView);
        ansTextView=(TextView)findViewById(R.id.ansTextView);
        finalTextView=(TextView)findViewById(R.id.finalTextView);
        scoreTextView=(TextView)findViewById(R.id.scoreTextView);
        timerTextView=(TextView)findViewById(R.id.timerTextView);
        playButton=(Button)findViewById(R.id.playButton);

    }
}
