package local.hal.st31.android.favoriteshops80551;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Fragment;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * ST31 Androidサンプル12 メモ帳アプリ
 *
 * 第2画面表示用アクティビティクラス。
 * メモ情報編集画面を表示する。
 *
 * @author Shinzo SAITO
 */
public class ShopEditActivity extends AppCompatActivity {
    /**
     * 新規登録モードか更新モードかを表すフィールド。
     */
    private int _mode = MainActivity.MODE_INSERT;
    /**
     * 更新モードの際、現在表示しているメモ情報のデータベース上の主キー値。
     */
    private long _idNo = 0;
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    // 2020/09/17 FavoriteShops改造2にて追加:開始ポイント
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        // xmlの内容をJavaで扱うために、R値として展開している。
        // その動作をインフレートという。
        // menu/menu_options_activity_main.xmlは自動で読み込まれないので、
        // 直下のコードでインフレーターがインフレートしている。
        inflater.inflate(R.menu.menu_options_activity_main, menu);

        return true;
    }
    // 2020/09/17 FavoriteShops改造2にて追加:終了ポイント

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_edit);

        _helper = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();
        _mode = intent.getIntExtra("mode", MainActivity.MODE_INSERT);

        if(_mode == MainActivity.MODE_INSERT) {
            TextView tvNameEdit = findViewById(R.id.tvNameEdit);
            tvNameEdit.setText(R.string.tv_name_insert);

            Button btnSave = findViewById(R.id.btnSave);
            btnSave.setText(R.string.btn_insert);

            Button btnDelete = findViewById(R.id.btnDelete);
            // 不可視の呪文INVISIBLE...!
            btnDelete.setVisibility(View.INVISIBLE);
        }
        else {
            _idNo = intent.getLongExtra("idNo", 0);
            SQLiteDatabase db = _helper.getWritableDatabase();
            Shop shopData = DataAccess.findByPK(db, _idNo);

            EditText etInputName = findViewById(R.id.etInputName);
            etInputName.setText(shopData.getName());

            EditText etInputTel = findViewById(R.id.etInputTel);
            etInputTel.setText(shopData.getTel());

            EditText etInputUrl = findViewById(R.id.etInputUrl);
            etInputUrl.setText(shopData.getUrl());

            EditText etInputNote = findViewById(R.id.etInputNote);
            etInputNote.setText(shopData.getNote());
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnVal = true;
        int itemId = item.getItemId();
        if (itemId == R.id.btnSave) {
            EditText etInputName = findViewById(R.id.etInputName);
            String inputName = etInputName.getText().toString();
            if(inputName.equals("")) {
                Toast.makeText(ShopEditActivity.this, R.string.msg_input_name, Toast.LENGTH_SHORT).show();
            }
            else {
                EditText etInputTel = findViewById(R.id.etInputTel);
                String inputTel = etInputTel.getText().toString();

                EditText etInputUrl = findViewById(R.id.etInputUrl);
                String inputUrl = etInputUrl.getText().toString();

                EditText etInputNote = findViewById(R.id.etInputNote);
                String inputNote = etInputNote.getText().toString();

                SQLiteDatabase db = _helper.getWritableDatabase();

                if(_mode == MainActivity.MODE_INSERT) {
                    DataAccess.insert(db, inputName, inputTel, inputUrl, inputNote);
                }
                else {
                    DataAccess.update(db, _idNo, inputName, inputTel, inputUrl, inputNote);
                }
                finish();
            }
        }
        else if (itemId == R.id.btnBack) {
            finish();
        }
        else if (itemId == R.id.btnDelete) {
            // 2020/09/10 FavoriteShops改造1にて無効化:開始ポイント
//        SQLiteDatabase db = _helper.getWritableDatabase();
//        DataAccess.delete(db, _idNo);
            // 2020/09/10 FavoriteShops改造1にて無効化:終了ポイント

            // 2020/09/10 FavoriteShops改造1にて追加:開始ポイント
            DeleteDialogFragment dialog = new DeleteDialogFragment();
            FragmentManager manager = getSupportFragmentManager();
            dialog.show(manager, "DeleteDialogFragment");
            // 2020/09/10 FavoriteShops改造1にて追加:終了ポイント

            // 2020/09/10 FavoriteShops改造1にて無効化:開始ポイント
//        finish();
            // 2020/09/10 FavoriteShops改造1にて無効化:終了ポイント

        }
        return returnVal;
    }

    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }
}
