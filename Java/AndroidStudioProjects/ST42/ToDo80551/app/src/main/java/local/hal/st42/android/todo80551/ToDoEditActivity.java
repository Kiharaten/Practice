package local.hal.st42.android.todo80551;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static java.lang.Integer.parseInt;

public class ToDoEditActivity extends AppCompatActivity {

    /**
     * 新規登録モードか更新モードかを表すフィールド。
     */
    private int _mode = MainActivity.MODE_INSERT;
    /**
     * 更新モードの際、現在表示している店舗情報のデータベース上の主キー値。
     */
    private long _idNo = 0;
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_edit);

        // 戻るボタン表示
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        _helper = new DatabaseHelper(getApplicationContext());

        Intent intent = getIntent();
        _mode = intent.getIntExtra("mode", MainActivity.MODE_INSERT);

        // 日付取得
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        String strDate = dateFormat.format(date);

        TextView tvDeadline = findViewById(R.id.tvDeadline);
        tvDeadline.setText(strDate);

        if (_mode == MainActivity.MODE_INSERT) {
            TextView tvNameEdit = findViewById(R.id.tvToDoEditPageName);
            tvNameEdit.setText(R.string.tv_to_do_add_pagename);
        } else {
            _idNo = intent.getLongExtra("idNo", 0);
            SQLiteDatabase db = _helper.getWritableDatabase();
            Task taskData = DataAccess.findByPK(db, _idNo);

            EditText etName = findViewById(R.id.etName);
            etName.setText(taskData.getName());

            tvDeadline.setText(taskData.getDeadline());

            Switch swDone = findViewById(R.id.swDone);
            if (taskData.getDone() == 1){
                swDone.setChecked(true);
            }

            EditText etNote = findViewById(R.id.etNote);
            etNote.setText(taskData.getNote());
        }
    }

    /**
     * 日付選択ダイアログ表示ボタンが押されたときのイベント処理用メソッド。
     *
     * @param view 画面部品。
     */
    public void showDatePickerDialog(View view) {
        TextView tvDeadline = findViewById(R.id.tvDeadline);
        String deadline = tvDeadline.getText().toString();

        int year = Integer.parseInt(deadline.substring(0,4));
        int month = Integer.parseInt(deadline.substring(5, 7)) - 1;
        int date = Integer.parseInt(deadline.substring(8, 10));

        DatePickerDialog dialog = new DatePickerDialog(ToDoEditActivity.this, new DatePickerDialogDateSetListener(), year, month, date);
        dialog.show();
    }

    /**
     * 日付選択ダイアログの完了ボタンが押されたときの処理が記述されたメンバクラス。
     */
    private class DatePickerDialogDateSetListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String deadline = String.format("%04d年%02d月%02d日", year, (month + 1), dayOfMonth);
            TextView tvDeadline = findViewById(R.id.tvDeadline);
            tvDeadline.setText((CharSequence) deadline);
        }
    }

    //*************** アクションバー関連 ********************
    /**
     * アクションバーのR値インフレート
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_to_do_edit_activity, menu);
        return true;
    }

    /**
     * アクションバーのイベント分岐用メソッド。
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        boolean returnVal = true;
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.deleteButton: // 削除ボタン
                onDeleteButtonClick();
                break;
            case R.id.saveButton: // 保存ボタン
                onSaveButtonClick();
                break;
            case android.R.id.home: // 戻るボタン
                finish();
                return true;
            default:
                returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;
    }

    /**
     * 削除ボタン用イベント処理メソッド。
     */
    public void onDeleteButtonClick(){
        Bundle extras = new Bundle();
        extras.putLong("id", _idNo);
        FullDialogFragment dialog = new FullDialogFragment();
        FragmentManager manager = getSupportFragmentManager();
        //FullDialogFragment1へ値送る
        dialog.setArguments(extras);
        dialog.show(manager, "FullDialogFragment");
    }

    /**
     * 保存ボタン用イベント処理メソッド。
     */
    public void onSaveButtonClick(){
        EditText etName = findViewById(R.id.etName);
        String inputName = etName.getText().toString();

        if (inputName.equals("")) {
            Toast.makeText(ToDoEditActivity.this, R.string.toast_empty , Toast.LENGTH_SHORT).show();
        }
        else{
            TextView etDeadline = findViewById(R.id.tvDeadline);
            String inputDeadline = etDeadline.getText().toString();

            Switch swDone = findViewById(R.id.swDone);
            long inputDone = 0;
            if(swDone.isChecked()) {
                inputDone = 1;
            }
            EditText etNote = findViewById(R.id.etNote);
            String inputNote = etNote.getText().toString();
            SQLiteDatabase db = _helper.getWritableDatabase();
            if (_mode == MainActivity.MODE_INSERT) {
                DataAccess.insert(db, inputName, inputDeadline, inputDone, inputNote);
            }
            else {
                DataAccess.update(db, _idNo, inputName, inputDeadline, inputDone, inputNote);
            }
            finish();
        }
    }

    /**
     * 追加の際は削除ボタンを表示しないための処理
     */
    public boolean onPrepareOptionsMenu(Menu menu){
        if(_mode == MainActivity.MODE_INSERT){
            menu.findItem(R.id.deleteButton).setVisible(false);
        }
        return true;
    }
}