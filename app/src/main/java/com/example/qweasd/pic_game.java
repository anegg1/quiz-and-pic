package com.example.qweasd;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class pic_game extends AppCompatActivity {

    ImageView ivShowImage;
    ArrayList<String> celebNames = new ArrayList<>();
    ArrayList<String> newCelebNames = new ArrayList<>();
    HashMap<String, String> map = new HashMap<>();
    int index;
    Random random;
    String[] answers = new String[4];
    Button btn1, btn2, btn3, btn4, btnRestart;
    int points = 0;
    TextView tvPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic_game);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btnRestart = findViewById(R.id.btnRestart);
        tvPoints = findViewById(R.id.tvPoints);
        celebNames.add("Леонардо Ди Каприо");
        celebNames.add("Харрисон Форд"); //
        celebNames.add("Аль Пачино");
        celebNames.add("Роберт Де Ниро");
        celebNames.add("Томми Ли Джонс");
        celebNames.add("Брэд Питт");
        celebNames.add("Джонни Депп");//
        celebNames.add("Анджелина Джоли");
        index = 0;
        map.put(celebNames.get(0), "https://img3.goodfon.ru/original/1366x768/0/44/leonardo-dicaprio-actor.jpg");
        map.put(celebNames.get(1), "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fstatic.onecms.io%2Fwp-content%2Fuploads%2Fsites%2F20%2F2017%2F05%2Fharrison-ford.jpg");
        map.put(celebNames.get(2), "https://short-biography.com/wp-content/uploads/al-pacino/Alfredo-James-Pacino.jpg");
        map.put(celebNames.get(3), "https://avatars.mds.yandex.net/get-zen_doc/1872259/pub_5fe5a57dba62db3e3816b403_5fe5a67decae270e94bc78d5/scale_1200");
        map.put(celebNames.get(4), "https://theglobalstardom.com/wp-content/uploads/2020/12/tommy-image.jpg");
        map.put(celebNames.get(5), "https://russianteleweek.ru/wp-content/uploads/2020/12/brjed-pitt-1536x1166.jpg");
        map.put(celebNames.get(6), "https://mainstyles.ru/uploads/Dzhonni-Depp-mozhet-vernutsya-v-novuyu-chast-Piratov-Karibskogo-morya-01.jpg");
        map.put(celebNames.get(7), "https://www.kleo.ru/img/news/C3tWo.jpg");
        Collections.shuffle(celebNames);
        random = new Random();
        generateQuestions(index);
    }

    private void generateQuestions(int index) {
        Glide.with(this)
                .asBitmap()
                .load(map.get(celebNames.get(index)))
                .error(R.drawable.not_found)
                .into(ivShowImage);
        newCelebNames = (ArrayList<String>) celebNames.clone();
        newCelebNames.remove(index);
        Collections.shuffle(newCelebNames);
        int correctAnswerPosition = random.nextInt(4);
        for(int i=0; i < 4; i++){
            if(i == correctAnswerPosition){
                answers[i] = celebNames.get(index);
            } else{
                answers[i] = newCelebNames.get(i);
            }
        }
        btn1.setText(answers[0]);
        btn2.setText(answers[1]);
        btn3.setText(answers[2]);
        btn4.setText(answers[3]);
        btnRestart.setVisibility(View.GONE);
    }

    public void answerSelected(View view) {
        String answer = ((Button) view).getText().toString();
        if(answer.equals(celebNames.get(index))){
            points++;
            tvPoints.setText(points + "/7");
        }
        index++;
        if(index > celebNames.size() - 1){
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);
            btnRestart.setVisibility(View.VISIBLE);
        } else{
            generateQuestions(index);
        }
    }

    public void restart(View view) {
        if(index > 6){
            index = 0;
            points = 0;
            ivShowImage.setVisibility(View.VISIBLE);
            btn1.setVisibility(View.VISIBLE);
            btn2.setVisibility(View.VISIBLE);
            btn3.setVisibility(View.VISIBLE);
            btn4.setVisibility(View.VISIBLE);
            tvPoints.setText(points + "/7");
            Collections.shuffle(celebNames);
        }
        generateQuestions(index);
    }
}