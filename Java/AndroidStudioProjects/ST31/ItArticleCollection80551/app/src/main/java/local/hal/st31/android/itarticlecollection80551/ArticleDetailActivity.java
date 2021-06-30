package local.hal.st31.android.itarticlecollection80551;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleAdapter;
import android.widget.TextView;

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

public class ArticleDetailActivity extends AppCompatActivity {

    /**
     * POST先のURL
     */
    private static final String ARTICLE_DETAIL_URL = "https://hal.architshin.com/st31/getOneArticle.php";

    /**
     * 保持している記事の主キー値
     */
    private String _idNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        _idNo = intent.getStringExtra("idNo");

        ArticleDetailActivity.ArticleDetailReceiver access = new ArticleDetailActivity.ArticleDetailReceiver();
        access.execute();
    }

    /**
     * 非同期で記事詳細を取得するクラス。
     */
    private class ArticleDetailReceiver extends AsyncTask<String, List<Map<String, String>>, String> {
        /**
         * ログに記載するタグ用の文字列。
         */
        private static final String DEBUG_TAG = "ArticleDetailReceiver";

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
            String urlFull = ARTICLE_DETAIL_URL + "?id=" + _idNo;

            try {
                URL url = new URL(urlFull);
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
            Map<String,String> article_detail = new HashMap<>();
            if(_success) {

                try {
                    /**
                     * 取得したJSONデータをリストに格納
                     */
                    JSONObject rootJSON = new JSONObject(result);
                    JSONArray article = rootJSON.getJSONArray("article");

                    JSONObject data = article.getJSONObject(0);
                    Log.e(DEBUG_TAG, data.toString());
                    article_detail.put("id", data.getString("id"));
                    article_detail.put("title", data.getString("title"));
                    article_detail.put("url", data.getString("url"));
                    article_detail.put("comment", data.getString("comment"));
                    article_detail.put("created_at", data.getString("created_at"));
                    article_detail.put("student_id", data.getString("student_id"));
                    article_detail.put("seat_no", data.getString("seat_no"));
                    String name = data.getString("last_name") + "　" + data.getString("first_name");
                    article_detail.put("name", name);
                    
                }
                catch(JSONException ex) {
                    Log.e(DEBUG_TAG, "JSON解析失敗", ex);
                }
            }

            /**
             * 画面に表示
             */
            TextView tvDetailId = findViewById(R.id.tvDetailId);
            tvDetailId.setText(article_detail.get("id"));
            TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
            tvDetailTitle.setText(article_detail.get("title"));
            TextView tvDetailUrl = findViewById(R.id.tvDetailUrl);
            tvDetailUrl.setText(article_detail.get("url"));
            TextView tvDetailComment = findViewById(R.id.tvDetailComment);
            tvDetailComment.setText(article_detail.get("comment"));
            TextView tvDetailCreatedat = findViewById(R.id.tvDetailDatetime);
            tvDetailCreatedat.setText(article_detail.get("created_at"));
            TextView tvDetailStudentid = findViewById(R.id.tvDetailStudentid);
            tvDetailStudentid.setText(article_detail.get("student_id"));
            TextView tvDetailSeatno = findViewById(R.id.tvDetailSeatno);
            tvDetailSeatno.setText(article_detail.get("seat_no"));
            TextView tvDetailName = findViewById(R.id.tvDetailName);
            tvDetailName.setText(article_detail.get("name"));


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
     * アクションバーのR値インフレート。
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detail_activity, menu);
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
            case android.R.id.home:
                finish();
                break;
            default:
                returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;
    }
}