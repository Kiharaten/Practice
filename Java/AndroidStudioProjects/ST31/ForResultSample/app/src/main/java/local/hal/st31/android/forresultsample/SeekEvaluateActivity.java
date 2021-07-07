package local.hal.st31.android.forresultsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * ST31 Androidサンプル08 インテントからの返却
 *
 * 第3画面表示アクティビティクラス。
 * スライダーでの評価画面。
 *
 * @author Shinzo SAITO
 */
public class SeekEvaluateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek_evaluate);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String msg = name + "さんの評価をつけてください。";
        TextView tvEvaluate = findViewById(R.id.tvEvaluate);
        tvEvaluate.setText(msg);

        Button btPrevious = findViewById(R.id.btPrevious);
        btPrevious.setOnClickListener(new ButtonClickListener());
    }

    /**
     * ボタンが押された時の処理が記述されたメンバクラス。
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            SeekBar skbValue = findViewById(R.id.skbValue);
            int rate = skbValue.getProgress();

            Intent intent = getIntent();
            intent.putExtra("rate", rate);
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
