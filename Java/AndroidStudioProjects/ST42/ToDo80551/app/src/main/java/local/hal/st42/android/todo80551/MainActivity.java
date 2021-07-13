package local.hal.st42.android.todo80551;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    /**
     * 新規登録モードを表す定数フィールド。
     * */
    static final int MODE_INSERT = 1;
    /**
     * 更新モードを表す定数フィールド。
     */
    static final int MODE_EDIT = 2;
    /**
     * メモリスト用ListView。
     */
    private ListView _lvToDoList;
    /**¬
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;
    /**
     * プレファレンスファイル名を表す定数フィールド。
     */
    private static final String PREFS_NAME = "ToDoFile";
    /**
     * メニューリストの種類を表すフィールド。
     */
    private int _menuCategory;
    /**
     * 全タスクリストを表す定数フィールド。
     */
    private static final int ALL = 1;
    /**
     * 未完了のタスクのみを表す定数フィールド。
     */
    private static final int UNFINISHED = 2;
    /**
     * 完了のタスクのみを表す定数フィールド。
     */
    private static final int FINISHED = 3;

    /**
     * 主処理
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        /********** スクローリングアクティビティの設定 **********/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.setTitle(getString(R.string.app_name));
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        /********** リストビューの設定 **********/
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        _menuCategory = settings.getInt("settings", Typeface.NORMAL);
        _lvToDoList = findViewById(R.id.lvArticleList);
        _lvToDoList.setOnItemClickListener(new ListItemClickListener());
        _helper = new DatabaseHelper(getApplicationContext());

        /********** ToDoリストの取得 **********/
        String[] from = {"name", "deadline", "done"};
        int[] to = {R.id.tvToDoName, R.id.tvToDoDeadline, R.id.cbToDoCheck};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.row, null, from, to, 0);
        adapter.setViewBinder(new CustomViewBinder());
        _lvToDoList.setAdapter(adapter);
    }

    /**
     * 開始処理
     */
    @Override
    protected void onResume() {
        super.onResume();
        setNewCursor();
    }

    /**
     * 終了処理
     */
    @Override
    protected void onDestroy() {
        _helper.close();
        super.onDestroy();
    }

    /**
     * カーソルアダプタ内のカーソルを更新するメソッド。
     */
    private void setNewCursor() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        SQLiteDatabase db = _helper.getWritableDatabase();
        Cursor cursor = null;

        switch(_menuCategory) {
            case UNFINISHED:
                cursor = DataAccess.findUnfinished(db);
                editor.putInt("settings", UNFINISHED);
                break;

            case FINISHED:
                cursor = DataAccess.findFinished(db);
                editor.putInt("settings", FINISHED);
                break;

            case ALL:
                cursor = DataAccess.findAll(db);
                editor.putInt("settings", ALL);
                break;
        }

        editor.apply();
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) _lvToDoList.getAdapter();
        adapter.changeCursor(cursor);
    }

    /**
     * リストビューのカスタムビューバインダークラス。
     */
    private class CustomViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            int viewId = view.getId();
            switch(viewId) {
                case R.id.tvToDoDeadline:
                    TextView tvToDoDeadline = (TextView) view;
                    String deadline = cursor.getString(columnIndex);

                    // 今日の日付を取得
                    Calendar cal = Calendar.getInstance();
                    int nowYear = cal.get(Calendar.YEAR);
                    int nowMonth = cal.get(Calendar.MONTH);
                    int nowDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
                    String nowDeadline =  nowYear + "年" + (nowMonth + 1) + "月" + nowDayOfMonth + "日";
                    SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy年MM月dd日");

                    //日付の比較
                    Date today = null;
                    Date deadline_date = null;
                    try {
                        today = dateFormat.parse(nowDeadline);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        deadline_date = dateFormat.parse(deadline);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    if(today.equals(deadline_date)){
                        deadline = "今日";
                        tvToDoDeadline.setTextColor(Color.rgb(0, 100, 255));
                    }else {
                        if(deadline_date.before(today)){
                            tvToDoDeadline.setTextColor(Color.rgb(220,20,60));
                        }else{
                            tvToDoDeadline.setTextColor(Color.rgb(30,30,30));
                        }
                    }
                    tvToDoDeadline.setText(deadline);
                    return true;
                case R.id.cbToDoCheck:
                    int idIdx = cursor.getColumnIndex("_id");
                    long id = cursor.getLong(idIdx);
                    CheckBox cbPhoneCheck = (CheckBox) view;
                    int phoneCheck = cursor.getInt(columnIndex);
                    boolean checked = false;
                    LinearLayout row = (LinearLayout) cbPhoneCheck.getParent();
                    int rColor = androidx.appcompat.R.drawable.abc_list_selector_holo_light;
                    if(phoneCheck == 1) {
                        checked = true;
                        rColor = androidx.appcompat.R.drawable.abc_list_selector_disabled_holo_dark;
                    }
//                    row.setBackgroundResource(rColor);
                    cbPhoneCheck.setChecked(checked);
                    cbPhoneCheck.setTag(id);
                    cbPhoneCheck.setOnClickListener(new OnCheckBoxClickListener());
                    return true;
            }
            return false;
        }
    }

    /**
     * リストがクリックされた時のリスナクラス。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id){
            Cursor item = (Cursor) parent.getItemAtPosition(position);
            int idxId = item.getColumnIndex("_id");
            long idNo =  item.getLong(idxId);

            Intent intent = new Intent(getApplicationContext(), ToDoEditActivity.class);
            intent.putExtra("mode", MODE_EDIT);
            intent.putExtra("idNo", idNo);
            startActivity(intent);
        }
    }

    /**
     * チェックボックスのチェック状態が変更されたときのリスナクラス。
     */
    private class OnCheckBoxClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            CheckBox cbPhoneCheck = (CheckBox) view;
            boolean isChecked = cbPhoneCheck.isChecked();
            long id = (Long) cbPhoneCheck.getTag();
            SQLiteDatabase db = _helper.getWritableDatabase();
            DataAccess.changePhoneChecked(db, id, isChecked);
            setNewCursor();
        }
    }

    /**
     * 追加ボタン用イベント処理メソッド。
     */
    public void onAddFabClick(View view){
        Intent intent = new Intent(getApplicationContext(), ToDoEditActivity.class);
        intent.putExtra("mode", MODE_INSERT);
        startActivity(intent);
    }


    //*************** アクションバー関連 ********************
    /**
     * アクションバーのR値インフレート
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MenuItem menuListOptionTitle = menu.findItem(R.id.menuListOptionTitle);
        switch(_menuCategory) {
            case UNFINISHED:
                menuListOptionTitle.setTitle(R.string.menu_main_unfinished);
                break;
            case FINISHED:
                menuListOptionTitle.setTitle(R.string.menu_main_finished);
                break;
            case ALL:
                menuListOptionTitle.setTitle(R.string.menu_main_all);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * アクションバーのイベント分岐用メソッド。
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        boolean returnVal = true;
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menuListOptionAll:
                editor.putInt("menuCategory", ALL);
                _menuCategory = ALL;
                break;
            case R.id.menuListOptionFinished:
                editor.putInt("menuCategory", FINISHED);
                _menuCategory = FINISHED;
                break;
            case R.id.menuListOptionUnfinished:
                editor.putInt("menuCategory", UNFINISHED);
                _menuCategory = UNFINISHED;
                break;

//            case R.id.addButton:
//                onAddButtonClick();
//                break;
            default:
                returnVal = super.onOptionsItemSelected(item);
        }

        editor.commit();
        if(returnVal) {
            setNewCursor();
            invalidateOptionsMenu();
        }
        return returnVal;
    }

//    /**
//     * 追加ボタン用イベント処理メソッド。
//     */
//    public void onAddButtonClick(){
//        Intent intent = new Intent(getApplicationContext(), ToDoEditActivity.class);
//        intent.putExtra("mode", MODE_INSERT);
//        startActivity(intent);
//    }
}