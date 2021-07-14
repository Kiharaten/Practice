package local.hal.st42.android.todo80551;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cursoradapter.widget.SimpleCursorAdapter;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import local.hal.st42.android.todo80551.DatabaseHelper;

/**
 * ST42 Androidサンプル08 リサイクラービュー
 *
 * 画面表示用アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {

    /**
     * モードを表す定数フィールド。
     * INSERT:新規登録, EDIT:更新
     */
    static final int MODE_INSERT = 1;
    static final int MODE_EDIT = 2;

    /**
     * タスクの表示形式を表す定数フィールド。
     * ALL:全て, UNFINISHED:未完了, FINISHED:完了
     */
    private static final int ALL = 1;
    private static final int UNFINISHED = 2;
    private static final int FINISHED = 3;

    /**
     * リスト情報を扱うフィールド。
     * id:主キー, name:Todo名, deadline:期限, done:完了フラグ
     */
    private ArrayList<String> id;
    private ArrayList<String> name;
    private ArrayList<Integer> deadline;
    private ArrayList<Integer> done;

    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    /**
     * プレファレンスファイル名を表す定数フィールド。
     */
    private static final String PREFS_NAME = "TodoFile";

    /**
     * リサイクラービューを表すフィールド。
     */
    private  RecyclerView _rvTodo;

    /**
     * メニューリストの種類を表すフィールド。
     */
    private int _menuCategory;

    /******************** 主処理 ********************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /********** メインアクティビティ起動 **********/
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /********** ツールバー(スクローリングアクティビティ)起動 **********/
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbarLayout);
        toolbarLayout.setTitle(getString(R.string.app_name));
        toolbarLayout.setExpandedTitleColor(Color.WHITE);
        toolbarLayout.setCollapsedTitleTextColor(Color.LTGRAY);

        /********** タスクの表示形式を保存 **********/
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        _menuCategory = settings.getInt("settings", Typeface.NORMAL);

        /********** リサイクラービューの起動 **********/
        _rvTodo = findViewById(R.id.rvTodo);
        LinearLayoutManager layout = new LinearLayoutManager(MainActivity.this);
        _rvTodo.setLayoutManager(layout);
        DividerItemDecoration decoration = new DividerItemDecoration(MainActivity.this, layout.getOrientation());
        _rvTodo.addItemDecoration(decoration);
//        createRecyclerView();

        _helper = new DatabaseHelper(MainActivity.this);
        id = new ArrayList<>();
        name = new ArrayList<>();
        deadline = new ArrayList<>();
        done = new ArrayList<>();

        /********** Todoの取得 **********/
        String[] from = {"name", "deadline", "done"};
        int[] to = {R.id.tvTodoName, R.id.tvDeadline, R.id.cbTodoCheck};
        ListAdapter adapter = new ListAdapter(MainActivity.this,id,name,deadline,done);
        _rvTodo.setAdapter(adapter);
    }

    /**
     * カーソルアダプタ内のカーソルを更新するメソッド。
     */
    private void setNewCursor() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        SQLiteDatabase db = _helper.getWritableDatabase();
        Cursor cursor = null;

        name = new ArrayList<>();
        deadline = new ArrayList<>();
        done = new ArrayList<>();

        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();

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

        while (cursor.moveToNext()){
            id.add(cursor.getInt(0));
            name.add(cursor.getString(1));
            deadline.add(cursor.getString(2));
            done.add(cursor.getInt(3));
        }

        editor.apply();
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) _rvList.getAdapter();
        adapter.changeCursor(cursor);
    }

    //readDataで用意したCursor操作。
    void DataInArrays(){
        SQLiteDatabase db = _helper.getWritableDatabase();
        Cursor cursor = DataAccess.findAll(db);
        while (cursor.moveToNext()){
            id.add(cursor.getInt(0));
            name.add(cursor.getString(1));
            deadline.add(cursor.getString(2));
            done.add(cursor.getInt(3));
        }
    }
    //*************** アクションバー関連 ********************
    /**
     * アクションバーのR値インフレート
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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
     * リストビューを表示させるメソッド。
     */
    private void createRecyclerView() {
        List<Map<String, Object>> menuList;
        switch(_menuCategory) {
            case FINISHED:
                menuList = createFinishedList();
                break;
            case ALL:
                menuList = createAllList();
                break;
            default:
                menuList = createUnfinishedList();
                break;
        }
        TodoListAdapter adapter = new TodoListAdapter(menuList);
        _rvTodo.setAdapter(adapter);
    }

//    /**
//     * リストビューに表示させるカレーリストデータを生成するメソッド。
//     *
//     * @return  生成されたカレーリストデータ。
//     */
//    private  List<Map<String, Object>> createCurryList() {
//        List<Map<String, Object>> list = new ArrayList<>();

    /**
     * リサイクラービューで利用するビューホルダクラス。
     */
    private class TodoListViewHolder extends RecyclerView.ViewHolder {
        /**
         * id用TextViewフィールド。
         */
        public  TextView _tvTodoId;
        /**
         * name用TextViewフィールド。
         */
        public  TextView _tvTodoName;
        /**
         * deadline用TextViewフィールド。
         */
        public  TextView _tvTodoDeadline;
        /**
         * done用TextViewフィールド。
         */
        public  TextView _tvTodoDone;

        /**
         * コンストラクタ。
         *
         * @param itemView リスト1行分の画面部品。
         */
        public  TodoListViewHolder(View itemView) {
            super(itemView);
            _tvTodoName = itemView.findViewById(R.id.tvTodoName);
            _tvTodoDeadline = itemView.findViewById(R.id.tvTodoDeadline);
            _tvTodoDone = itemView.findViewById(R.id.cbTodoCheck);
        }
    }

    /**
     * リサイクラービューで利用するアダプタクラス。
     */
    private class Adapter extends RecyclerView.Adapter<TodoListViewHolder> {
        /**
         * リストデータを表すフィールド。
         */
        private  List<Map<String, Object>> _listData;

        /**
         * コンストラクタ。
         *
         * @param listData リストデータ。
         */
        public ListAdapter(List<Map<String, Object>> listData) {
            _listData = listData;
        }

        @Override
        public  TodoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View row = inflater.inflate(R.layout.row_activity_main, parent, false);
            row.setOnClickListener(new ListItemClickListener());
            return new TodoListViewHolder(row);
        }

        @Override
        public void onBindViewHolder(TodoListViewHolder holder, int position) {
            Map<String, Object> item = _listData.get(position);
            int id = (Integer) item.get("id");
            String name = (String) item.get("name");
            String deadline = (String) item.get("deadline");
            int done = (Integer) item.get("done");

            String idStr = String.valueOf(id);
            String doneStr = String.valueOf(done);

            holder._tvTodoId.setText(idStr);
            holder._tvTodoName.setText(name);
            holder._tvTodoDeadline.setText(deadline);
            holder._tvTodoDone.setText(doneStr);
        }

        @Override
        public int getItemCount() {
            return  _listData.size();
        }
    }

    /**************************************** リスト関連 ****************************************/
    /**
     * リストビューのカスタムビューバインダークラス。
     * こいつをどうにか改造してリサイクラービューで使う。
     */
    private class CustomViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int columnIndex) {
            int viewId = view.getId();
            switch(viewId) {
                case R.id.tvTodoDeadline:
                    TextView tvTodoDeadline = (TextView) view;
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
                        // 期限が丁度今日の場合
                        deadline = "今日";
                        tvTodoDeadline.setTextColor(Color.rgb(0, 100, 255));
                    }else {
                        if(deadline_date.before(today)){
                            // 期限が今日以前の場合
                            tvTodoDeadline.setTextColor(Color.rgb(220,20,60));
                        }else{
                            // 期限が明日以降の場合
                            tvTodoDeadline.setTextColor(Color.rgb(30,30,30));
                        }
                    }
                    tvTodoDeadline.setText(deadline);
                    return true;
                case R.id.cbTodoCheck:
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
     * リストをタップした時の処理が記述されたメンバクラス。
     * こいつもリサイクラービュー用に置き換える
     */
    private class ListItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
//            TextView tvTodoId = view.findViewById(R.id.tvTodoId);
            TextView tvTodoName = view.findViewById(R.id.tvTodoName);
            TextView tvTodoDeadline = view.findViewById(R.id.tvTodoDeadline);
            TextView tvTodoDone = view.findViewById(R.id.cbTodoCheck);
            String id = tvTodoId.getText().toString();
            String name = tvTodoName.getText().toString();
            String deadline = tvTodoDeadline.getText()

            Bundle extras = new Bundle();
            extras.putString("name", name);
            extras.putString("price", price);
            OrderConfirmDialog dialog = new OrderConfirmDialog();
            dialog.setArguments(extras);
            FragmentManager manager = getSupportFragmentManager();
            dialog.show(manager, "OrderConfirmDialog");
        }
    }

    private class ListItemClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view){
            int idxId =  Integer.parseInt(String.valueOf(view.getTag(R.id.cbTodoCheck)));
            Log.d("id",String.valueOf(idxId));
            long idNo =  idxId;

            Intent intent = new Intent(getApplicationContext(), TodoActivity.class);
            intent.putExtra("mode", MODE_EDIT);
            intent.putExtra("idNo", idNo);
            startActivity(intent);
        }
    }


    private class OnCheckBoxClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            CheckBox cbCheck = (CheckBox) view;
            boolean isChecked = cbCheck.isChecked();
            long id = (Long) cbCheck.getTag();
            SQLiteDatabase db = _helper.getWritableDatabase();
            DataAccess.changePhoneChecked(db, id, isChecked);
            setNewCursor();
        }
    }
}
