package local.hal.st31.android.calcbmi80551;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * ST31　Androidサンプル04 割り算計算機
 *
 * メインアクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btCalc = findViewById(R.id.btCalc);
        btCalc.setOnClickListener(new ButtonClickListener());
        Button btClear = findViewById(R.id.btClear);
        btClear.setOnClickListener(new ButtonClickListener());
    }
    /**
     * ボタンが押されたときの処理が記述されたメンバクラス。
     */
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            EditText etHeight = findViewById(R.id.inputHeight);
            EditText etWeight = findViewById(R.id.inputWeight);
            TextView showResult = findViewById(R.id.showResult);
            TextView showMessage = findViewById(R.id.showMessage);
            Context context = getApplicationContext();

            int id = view.getId();
            switch (id) {
                case R.id.btCalc:
                    String strHeight = etHeight.getText().toString();
                    String strWeight = etWeight.getText().toString();

                    if(strHeight.equals("")  || strWeight.equals("")) {
                        String msg = "項目が未入力です";
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        double height = Double.valueOf(strHeight);
                        double weight = Double.valueOf(strWeight);
                        if(height == 0 || weight == 0) {
                            String msg = "無効な入力値です";
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            // cm → m の処理
                            height = height / 100;
                            // height → Height^2
                            double Height_squared = height * height;

                            BigDecimal bigAns = new BigDecimal(weight / Height_squared);
//                            bigAns = bigAns.setScale(3,RoundingMode.HALF_UP);

                            BigDecimal roundedAns = bigAns.setScale(1,BigDecimal.ROUND_HALF_UP);
                            String strAns = roundedAns.toString();
                            StringBuilder showBmi = new StringBuilder();
                            showBmi.append("BMI：");
                            showBmi.append(strAns);
                            showResult.setText(showBmi);

                            BigDecimal max25 = new BigDecimal(25);
                            BigDecimal min18 = new BigDecimal(18.5);
                            StringBuilder message = new StringBuilder();
                            BigDecimal intHeight = new BigDecimal(22 * height * height);
                            BigDecimal five = new BigDecimal(5);

                            if (roundedAns.compareTo(max25) >= 0){
                                message.append("肥満です。体重");
                                message.append(intHeight.setScale(0, RoundingMode.HALF_UP));
                                message.append("kgを目指しましょう。");
                                showMessage.setText(message);
                            }

                            else if (roundedAns.compareTo(min18) < 0) {
                                message.append("やせています。体重");
                                message.append(intHeight.setScale(0,RoundingMode.HALF_UP));
                                message.append("kgを目指しましょう。");
                                showMessage.setText(message);
                            }

                            else {
                                showMessage.setText("ちょうどいいです。現状を維持しましょう");
                            }


//                            bigAns = bigAns.setScale(3,RoundingMode.HALF_UP);
//                            BigDecimal roundedAns = bigAns.setScale(1,BigDecimal.ROUND_HALF_UP);
//                            String strAns = roundedAns.toString();
//                            StringBuilder showBmi = new StringBuilder();
//                            showBmi.append("BMI：");
//                            showBmi.append(strAns);
//                            showResult.setText(showBmi);
                        }
                    }
                    break;
                case R.id.btClear:
                    etWeight.setText("");
                    etHeight.setText("");
                    showResult.setText("");
                    showMessage.setText("");
                    break;
            }
        }
    }
}
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//    }
//}


//package local.hal.st31.android.calcbmi80551;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        Button btCalc = findViewById(R.id.inputCalc);
//        btCalc.setOnClickListener(new ButtonClickListener());
////        Button btClear = findViewById(R.id.inputClear);
////        btClear.setOnClickListener(new ButtonClickListener());
//    }
//
//    /**
//     * ボタンが押されたときの処理が記述されたメンバクラス。
//     */
//    private class ButtonClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View view) {
//            // 計算に必要な値の取得
//            EditText etWeight = findViewById(R.id.inputWeight);
//            EditText etHeight = findViewById(R.id.inputHeight);
//            TextView showResult = findViewById(R.id.showResult);
//            Context context = getApplicationContext();
//
//            int id = view.getId();
//            switch (id) {
//                case R.id.inputCalc:
//                    // 文字型に変更
//                    String strWeight = etWeight.getText().toString();
//                    String strHeight = etHeight.getText().toString();
//
//                    // 例外１未入力
//                    if(strWeight.equals("")  || strHeight.equals("")) {
//                        String msg = "何か数字を入力せなあきへんがな!";
//                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        double weight = Double.valueOf(strWeight);
//                        // 例外２ゼロ除算
//                        if(Weight == 0) {
//                            String msg = "分母に0はあきまへんがな!";
//                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//                        }
//
//                        // 計算処理
//                        else {
//                            double height = Double.valueOf(strHeight);
//                            BigDecimal bigAns = new BigDecimal(Height / Weight);
//                            bigAns = bigAns.setScale(3, RoundingMode.HALF_UP);
//                            String strAns = bigAns.toString();
//                            showResult.setText(strAns);
//                        }
//                    }
//                    break;
////                case R.id.inputClear:
////                    etHeight.setText("");
////                    etWeight.setText("");
////                    showResult.setText("");
////                    break;
//            }
//        }
//    }
//
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////    }
//}
