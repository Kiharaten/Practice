package local.hal.st31.android.itarticlecollection80551;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.app.Activity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;

/**
 * 画面表示用アクティビティクラス
 */
public class ArticleAddActivity extends AppCompatActivity {
    /**
     * POST先のURL
     */
    private static final String ACCESS_URL = "https://hal.architshin.com/st31/insertItArticle.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_add);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    /**
     * 送信ボタンが押された時の処理メソッド。
     */
    public void sendButtonClick(){
        EditText etTitle = findViewById(R.id.etTitle);
        EditText etUrl = findViewById(R.id.etUrl);
        EditText etComment = findViewById(R.id.etComment);

        String title = etTitle.getText().toString();
        String url = etUrl.getText().toString();
        String comment = etComment.getText().toString();

        if(title.equals("")) {
            Context context = getApplicationContext();
            Toast.makeText(context, R.string.tv_add_check_title, Toast.LENGTH_SHORT).show();
        }
        else if(url.equals("")) {
            Context context = getApplicationContext();
            Toast.makeText(context, R.string.tv_add_check_url, Toast.LENGTH_SHORT).show();
        }
        else{
            String lastname = "木原";
            String firstname = "天";
            String studentid = "80551";
            String seatno = "09";

            PostAccess access = new PostAccess();
            access.execute(ACCESS_URL, title, url, comment, lastname, firstname, studentid, seatno);
        }
    }

    /**
     * 非同期でサーバと通信するクラス。
     */
    private class PostAccess extends AsyncTask<String, String, String> {
        /**
         * ログに記載するタグ用の文字列。
         */
        private static final String DEBUG_TAG = "PostAccess";
        /**
         * サーバ通信が成功したかどうかのフラグ。
         * 成功した場合はtrue、失敗した場合はfalse。
         */
        private boolean _success = false;

        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            String title = params[1];
            String url_str = params[2];
            String comment = params[3];
            String lastname = params[4];
            String firstname = params[5];
            String studentid = params[6];
            String seatno = params[7];
            String error_name = "";

            String postData = "title=" + title + "&url=" + url_str + "&comment=" + comment + "&lastname=" + lastname + "&firstname=" + firstname + "&studentid=" + studentid + "&seatno=" + seatno;
            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";
            try {
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setConnectTimeout(1000);
                con.setReadTimeout(1000);
                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();
                int status = con.getResponseCode();
                if (status != 200) {
                    throw new IOException("ステータスコード:" + status);
                }
                is = con.getInputStream();

                result = is2String(is);
                _success = true;

            } catch (SocketTimeoutException ex) {
                Log.e(DEBUG_TAG, "タイムアウト", ex);
            } catch (MalformedURLException ex) {
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            } catch (IOException ex) {
                Log.e(DEBUG_TAG, "通信失敗", ex);
            } finally {
                if (con != null) {
                    con.disconnect();
                }
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException ex) {
                    Log.e(DEBUG_TAG, "InputStream開放失敗", ex);
                }
            }
            return result;
        }

        @Override
        public void onProgressUpdate(String... values) {}

        @Override
        public void onPostExecute(String result) {
            if (_success) {
                String title = "";
                String url_str = "";
                String comment = "";
                String name = "";
                String studentid = "";
                String seatno = "";
                String status = "";
                String msg = "";
                String timestamp = "";

                try {
                    JSONObject rootJSON = new JSONObject(result);
                    title = rootJSON.getString("title");
                    url_str = rootJSON.getString("url");
                    comment = rootJSON.getString("comment");
                    name = rootJSON.getString("name");
                    studentid = rootJSON.getString("studentid");
                    seatno = rootJSON.getString("seatno");
                    status = rootJSON.getString( "status");
                    msg = rootJSON.getString("msg");
                    timestamp = rootJSON.getString("timestamp");

                } catch (JSONException ex) {
                    Log.e(DEBUG_TAG, "JSON解析失敗", ex);
                }

                if(status.equals("0")) {
                    String messages = msg;
                    Bundle extras = new Bundle();
                    extras.putString("json", messages);
                    MsgDialogFragment dialog = new MsgDialogFragment();
                    dialog.setArguments(extras);
                    FragmentManager manager = getSupportFragmentManager();
                    dialog.show(manager, "MsgDIalogFragment");
                }
                else {
                    finish();
                }
            }
        }


        /**
         * InputStreamオブジェクトを文字列に変換するメソッド。変換文字コードはUTF-8。
         *
         * @param is 変換対象のInputStreamオブジェクト。
         * @return 変換された文字列。
         * @throws IOException 変換に失敗したときに発生。
         */
        private String is2String(InputStream is) throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            StringBuffer sb = new StringBuffer();
            char[] b = new char[1024];
            int line;
            while (0 <= (line = reader.read(b))) {
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
        inflater.inflate(R.menu.menu_add_activity, menu);
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
            case R.id.sendButton:
                sendButtonClick();
                break;
            default:
                returnVal = super.onOptionsItemSelected(item);
        }
        return returnVal;
    }
}