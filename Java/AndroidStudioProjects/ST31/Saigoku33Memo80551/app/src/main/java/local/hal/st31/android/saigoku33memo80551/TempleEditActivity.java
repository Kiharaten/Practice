package local.hal.st31.android.saigoku33memo80551;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ST31 Androidサンプル10 都道府県メモアプリ
 *
 * 第2画面表示用アクテビティクラス。
 * 都道府県メモ編集画面を表示する。
 *
 * @author Shizo SAITO
 */
public class TempleEditActivity extends AppCompatActivity {
    /**
     * 都道府県リスト画面で選択されたリストの行番号。
     */
    private int _selectedTempleNo = 0;
    /**
     * 都道府県リスト画面で選択された都道府県名。
     */
    private String _selectedTempleName = "";
    /**
     * データベースヘルパーオブジェクト
     */
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temple_edit);

        Intent intent = getIntent();
        _selectedTempleNo = intent.getIntExtra("selectedTempleNo", 0);
        _selectedTempleName = intent.getStringExtra("selectedTempleName");

        _helper = new DatabaseHelper(getApplicationContext());

        TextView tvTemple = findViewById(R.id.tvTemple);
        tvTemple.setText(_selectedTempleName);

        SQLiteDatabase db = _helper.getWritableDatabase();
        ArrayList<String> content = DataAccess.findContentByPK(db, _selectedTempleNo);

        EditText etHonzon = findViewById(R.id.etHonzon);
        etHonzon.setText(content.get(0));
        EditText etShushi = findViewById(R.id.etShushi);
        etShushi.setText(content.get(1));
        EditText etAddress = findViewById(R.id.etAddress);
        etAddress.setText(content.get(2));
        EditText etUrl = findViewById(R.id.etUrl);
        etUrl.setText(content.get(3));
        EditText etNote = findViewById(R.id.etNote);
        etNote.setText(content.get(4));
    }

    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }

    /**
     * 保存ボタンがタップされた時の処理メソッド。
     * @param view タップされた画面部品。
     */
    public void onSaveButtonClick(View view) {
        EditText etHonzon = findViewById(R.id.etHonzon);
        EditText etShushi = findViewById(R.id.etShushi);
        EditText etAddress = findViewById(R.id.etAddress);
        EditText etUrl = findViewById(R.id.etUrl);
        EditText etNote = findViewById(R.id.etNote);

        String honzon = etHonzon.getText().toString();
        String shushi= etShushi.getText().toString();
        String address = etAddress.getText().toString();
        String url = etUrl.getText().toString();
        String note = etNote.getText().toString();

        SQLiteDatabase db = _helper.getWritableDatabase();
        boolean exist = DataAccess.findRowByPK(db, _selectedTempleNo);
        if(exist) {
            DataAccess.update(db, _selectedTempleNo, _selectedTempleName, honzon, shushi, address , url , note);
        }
        else {
            DataAccess.insert(db, _selectedTempleNo, _selectedTempleName, honzon,shushi,address,url,note);
        }

        finish();
    }
}

