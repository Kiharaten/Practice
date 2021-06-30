package local.hal.st31.android.itarticlecollection80551;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 画面表示用アクティビティクラス。
 */
public class MainActivity extends AppCompatActivity {
    /**
     * IT記事リスト用ListView。
     */
    private ListView _lvArticleList;

    /**
     * 共有IT記事のURL。
     */
    private static final String SHARED_IT_ARTICLE_URL = "https://hal.architshin.com/st31/getItArticlesList.php";
    /**
     * リストビューに表示させるリストデータ。
     */
    private List<Map<String, String>> _list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _lvArticleList = findViewById(R.id.lvArticleList);
        _lvArticleList.setOnItemClickListener(new ListItemClickListener());
    }

    /**
     * 記事リスト表示
     */
    @Override
    protected void onResume() {
        super.onResume();
        SharedItArticleReceiver access = new SharedItArticleReceiver();
        access.execute();
        _lvArticleList.setOnItemClickListener(new ListItemClickListener());
    }

    /**
     * リストがタップされた時のリスナクラス。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = _list.get(position);
            String idNo = item.get("id");

            Intent intent = new Intent(getApplicationContext(), ArticleDetailActivity.class);
            intent.putExtra("idNo", idNo);
            startActivity(intent);
        }
    }

    /**
     * 非同期で共有IT記事を取得するクラス。
     */
    private class SharedItArticleReceiver extends AsyncTask<String, List<Map<String, String>>, String> {
        /**
         * ログに記載するタグ用の文字列。
         */
        private static final String DEBUG_TAG = "SharedItArticleReceiver";

        /**
         * 通信が成功したか失敗したかのフラグ
         * 成功した場合true, 失敗した場合falseが入る
         */
        private boolean _success = false;

        @Override
        public String doInBackground(String... params) {
            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";

            try {
                URL url = new URL(SHARED_IT_ARTICLE_URL);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);
                con.connect();
                is = con.getInputStream();
                int status = con.getResponseCode();
                if(status != 200){
                    throw new IOException("ステータスコード：" + status);
                }
                is = con.getInputStream();
                result = is2String(is);
                _success = true;
            } catch (SocketTimeoutException ex){
                Log.e(DEBUG_TAG, "タイムアウト", ex);
            } catch (MalformedURLException ex) {
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            } catch (IOException ex) {
                Log.e(DEBUG_TAG, "通信失敗", ex);
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException ex) {
                        Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
                    }
                }
            }

            return result;
        }

        @Override
        public void onPostExecute(String result){
            List<Map<String,String>> article_list = new ArrayList<>();
            if(_success) {

                try {
                    /**
                     * 取得したJSONデータをリストに格納
                     */
                    JSONObject rootJSON = new JSONObject(result);
                    JSONArray list = rootJSON.getJSONArray("list");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject data = list.getJSONObject(i);
                        Map<String, String> map = new HashMap<>();
                        Log.e(DEBUG_TAG, data.toString());
                        map.put("id", data.getString("id"));
                        map.put("title", data.getString("title"));
                        String name = data.getString("last_name") + "　" + data.getString("first_name");
                        map.put("name", name);
                        article_list.add(map);
                    }
                }
                catch(JSONException ex) {
                    Log.e(DEBUG_TAG, "JSON解析失敗", ex);
                }
            }

            /**
             * 画面に表示
             */
            _list = article_list;
            String[] from = {"title", "name"};
            int[] to = {android.R.id.text1, android.R.id.text2};
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), _list, android.R.layout.simple_list_item_2, from, to);
            _lvArticleList.setAdapter(adapter);


        }

        /**
         * InputStreamオブジェクトを文字列に変換するメソッド。変換文字コードはUTF-8。
         * @param is 変換対象のInputStreamオブジェクト。
         * @return 変換された文字列。
         * @throws IOException 変換に失敗した時に発生。
         */
        private String is2String(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            int line;
            while(0 <= (line = reader.read(b))) {
                sb.append(b, 0, line);
            }
            return sb.toString();
        }
    }

    /**
     * 新規投稿ボタンが押されたときのイベント処理用メソッド。
     */
    public void onNewButtonClick() {
        Intent intent = new Intent(getApplicationContext(), ArticleAddActivity.class);
        startActivity(intent);
    }

    /**
     * アクションバーのR値インフレート。
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    /**
     * アクションバーのイベント処理用メソッド。
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean returnVal = true;
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.addButton:
                onNewButtonClick();
                break;
            default:
                returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;
    }
}