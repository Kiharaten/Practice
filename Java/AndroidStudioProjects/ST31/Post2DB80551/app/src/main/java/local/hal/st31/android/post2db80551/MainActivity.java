package local.hal.st31.android.post2db80551;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
public class MainActivity extends AppCompatActivity {
    /**
     * POST先のURL
     */
    private static final String ACCESS_URL = "https://hal.architshin.com/st31/post2DB.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

     /**
     * 送信ボタンがクリックした時の処理メソッド。
     *
     * @param view Viewオブジェクト。
     */
    public void sendButtonClick(View view){
        EditText etLastName = findViewById(R.id.etLastName);
        EditText etFirstName = findViewById(R.id.etFirstName);
        EditText etStudentId = findViewById(R.id.etStudentId);
        EditText etSeatNo = findViewById(R.id.etSeatNo);
        EditText etMessage = findViewById(R.id.etMessage);
        TextView tvProcess = findViewById(R.id.tvProcess);
        TextView tvResult = findViewById(R.id.tvResult);

        tvProcess.setText("");
        tvResult.setText("");

        String lastname = etLastName.getText().toString();
        String firstname = etFirstName.getText().toString();
        String studentid = etStudentId.getText().toString();
        String seatno = etSeatNo.getText().toString();
        String message = etMessage.getText().toString();

        PostAccess access = new PostAccess(tvProcess, tvResult);
        access.execute(ACCESS_URL, lastname, firstname, studentid, seatno, message);
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
         * 各種メッセージを表示するための文字列部品。
         */
        private TextView _tvProcess;
        /**
         * 通信結果メッセージを表示するための文字列部品。
         */
        private TextView _tvResult;
        /**
         * サーバ通信が成功したかどうかのフラグ。
         * 成功した場合はtrue、失敗した場合はfalse。
         */
        private boolean _success = false;

        /**
         * コンストラクタ
         *
         * @param tvProcess 各種メッセージを表示するための画面部品。
         * @param tvResult  通信結果メッセージを表示するための文字列部品。
         */
        public PostAccess(TextView tvProcess, TextView tvResult) {
            _tvProcess = tvProcess;
            _tvResult = tvResult;
        }

        @Override
        public String doInBackground(String... params) {
            String urlStr = params[0];
            String lastname = params[1];
            String firstname = params[2];
            String studentid = params[3];
            String seatno = params[4];
            String message = params[5];

            String postData = "&lastname=" + lastname + "&firstname=" + firstname + "&studentid=" + studentid + "&seatno=" + seatno + "&message=" + message;
            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";

            try {
                publishProgress(getString(R.string.msg_send_before));
                URL url = new URL(urlStr);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setConnectTimeout(5000);
                con.setReadTimeout(5000);
                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(postData.getBytes());
                os.flush();
                os.close();
                int status = con.getResponseCode();
                if (status != 200) {
                    throw new IOException("ステータスコード:" + status);
                }
                publishProgress(getString(R.string.msg_send_after));
                is = con.getInputStream();

                result = is2String(is);
                _success = true;
            } catch (SocketTimeoutException ex) {
                publishProgress(getString(R.string.msg_err_timeout));
                Log.e(DEBUG_TAG, "タイムアウト", ex);
            } catch (MalformedURLException ex) {
                publishProgress(getString(R.string.msg_err_send));
                Log.e(DEBUG_TAG, "URL変換失敗", ex);
            } catch (IOException ex) {
                publishProgress(getString(R.string.msg_err_send));
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
                    publishProgress(getString(R.string.msg_err_parse));
                    Log.e(DEBUG_TAG, "InputStream開放失敗", ex);
                }
            }

            return result;
        }

        @Override
        public void onProgressUpdate(String... values) {
            String messages = _tvProcess.getText().toString();
            if (!messages.equals("")) {
                messages += "\n";
            }
            messages += values[0];
            _tvProcess.setText(messages);
        }

        @Override
        public void onPostExecute(String result) {
            if (_success) {
                String name = "";
                String studentid = "";
                String seatno = "";
                String status = "";
                String msg = "";
                String serialno = "";
                String timestamp = "";

                onProgressUpdate(getString(R.string.msg_parse_before));
                try {
                    JSONObject rootJSON = new JSONObject(result);
                    name = rootJSON.getString("name");
                    studentid = rootJSON.getString("studentid");
                    seatno = rootJSON.getString("seatno");

                    status = rootJSON.getString("status");
                    msg = rootJSON.getString("msg");
                    serialno = rootJSON.getString("serialno");
                    timestamp = rootJSON.getString("timestamp");

                } catch (JSONException ex) {
                    onProgressUpdate(getString(R.string.msg_err_parse));
                    Log.e(DEBUG_TAG, "JSON解析失敗", ex);
                }
                onProgressUpdate(getString(R.string.msg_parse_after));

                String messages = getString(R.string.dlg_msg_name) + name + "\n" + getString(R.string.dlg_msg_studentid) + studentid + "\n" + getString(R.string.dlg_msg_seatno) + seatno + "\n" + getString(R.string.dlg_msg_status) + status + "\n" + getString(R.string.dlg_msg_msg) + msg + "\n" + getString(R.string.dlg_msg_serialno) + serialno + "\n" + getString(R.string.dlg_msg_timestamp) + timestamp;
                _tvResult.setText(messages);

                Bundle extras = new Bundle();
                extras.putString("json", messages);
                FullDialogFragment dialog = new FullDialogFragment();
                FragmentManager manager = getSupportFragmentManager();
                //FullDialogFragmentへ値送る
                dialog.setArguments(extras);
                dialog.show(manager, "FullDialogFragment");
            }
        }

        /**
         * InputStreamオブジェクトを文字列に変換するメソッド。変換文字コードはUTF-8。
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
}