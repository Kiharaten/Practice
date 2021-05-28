package local.hal.st31.android.forresultsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ST31 Androidサンプル08 インテントからの返却
 *
 * 初期画面表示アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
    /**
     * リクエストコード1。
     * 星で評価する画面への遷移。
     */
    private static final int RATING_EVALUATE = 1;
    /**
     * リクエストコード1。
     * スライダーで評価する画面への遷移。
     */
    private static final int SEEK_EVALUATE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;
        setContentView(R.layout.activity_main);

        Button btRatingEvaluate = findViewById(R.id.btRatingEvaluate);
        btRatingEvaluate.setOnClickListener(new ButtonClickListener());
        Button btSeekEvaluate = findViewById(R.id.btSeekEvaluate);
        btSeekEvaluate.setOnClickListener(new ButtonClickListener());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText etName = findViewById(R.id.etName);
        String name = etName.getText().toString();

        TextView output = findViewById(R.id.tvOutput);
        if(resultCode == RESULT_OK) {
            int rate = data.getIntExtra("rate", -1);
            String msg = "正しく評価されていません。もう一度お願いします。";
            if(rate != -1) {
                if(requestCode == RATING_EVALUATE) {
                    msg = name + "さんの評価は★" + rate + "コです。";
                }
                else if(requestCode == SEEK_EVALUATE) {
                    msg = name + "さんの評価は" + rate + "点です。";
                }
            }
            output.setText(msg);
        }
    }

    /**
     * ボタンが押された時の処理が記述されたメンバクラス。
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText etName = findViewById(R.id.etName);
            String name = etName.getText().toString();
            if(name.equals("")) {
                Toast.makeText(getApplicationContext(), "名前を入力してください。",Toast.LENGTH_SHORT).show();
            }
            else {
                int id = view.getId();
                switch (id) {
                    case R.id.btRatingEvaluate:
                        Intent intentRatingEvaluate = new Intent(getApplicationContext(), RatingEvaluateActivity.class);
                        intentRatingEvaluate.putExtra("name", name);
                        startActivityForResult(intentRatingEvaluate, RATING_EVALUATE);
                        break;

                    case R.id.btSeekEvaluate:
                        Intent intentSeekEvaluate = new Intent(getApplicationContext(), SeekEvaluateActivity.class);
                        intentSeekEvaluate.putExtra("name", name);
                        startActivityForResult(intentSeekEvaluate, SEEK_EVALUATE);
                        break;
                }
            }
        }
    }
}
