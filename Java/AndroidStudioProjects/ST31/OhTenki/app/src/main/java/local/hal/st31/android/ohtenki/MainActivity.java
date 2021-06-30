package local.hal.st31.android.ohtenki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ST31 Androidサンプル15 Webアクセス
 *
 * お天気情報表示用アクティビティクラス。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
    /**
     * お天気情報のURL。
     */
    private static final String WEATHERINFO_URL = "https://api.openweathermap.org/data/2.5/weather?lang=ja";
    /**
     * お天気APIにアクセスすするためのAPI Key。
     * ※※※※※この値は各自のものに書き換える!!※※※※※
     */
    private static final String APP_ID = "a586b7b483b53df8e0f94967627e8492";
    /**
     * リストビューに表示させるリストデータ。
     */
    private List<Map<String, String>> _list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _list  = createList();

        ListView lvCityList = findViewById(R.id.lvCityList);
        String[] from = {"name"};
        int[] to = {android.R.id.text1};
        SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), _list, android.R.layout.simple_expandable_list_item_1, from, to);
        lvCityList.setAdapter(adapter);
        lvCityList.setOnItemClickListener(new ListItemClickListener());
    }

    /**
     * リストビューに表示させる天気ポイントリストデータを生成するメソッド。
     *
     * @return 生成された天気ポイントリストデータ。
     */
    private List<Map<String, String>> createList() {
        List<Map<String, String>> list = new ArrayList<>();

        Map<String, String> map = new HashMap<>();
        map.put("name", "大阪");
        map.put("q", "Osaka");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "神戸");
        map.put("q", "Kobe");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "京都");
        map.put("q", "Kyoto");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "大津");
        map.put("q", "Otsu");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "奈良");
        map.put("q", "Nara");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "和歌山");
        map.put("q", "Wakayama");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "姫路");
        map.put("q", "Himeji");
        list.add(map);

        return list;
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Map<String, String> item = _list.get(position);
            String q = item.get("q");

            WeatherInfoReceiver receiver = new WeatherInfoReceiver();
            receiver.execute(WEATHERINFO_URL, q, APP_ID);
        }
    }

    /**
     * 非同期でお天気データを取得するクラス。
     */
    private class WeatherInfoReceiver extends AsyncTask<String, Void, String> {
        // 引数が3つあり、Params, Progress, Result と名前がついている。
        // 型は決まっておらず、今回はString, Void, Stringになっている。
        // これらの引数はexecuteやdoInBackgroundで使われるもので、
        // ユーザーが任意の型で使えるようになっている。
        /**
         * ログに記載するタグ用の文字列。
         */
        private static final String DEBUG_TAG = "WeatherInfoReceiver";

        @Override
        public String doInBackground(String... params) {
            // params is array of string
            String urlBase = params[0];
            String q = params[1];
            String appId = params[2];
            String urlFull = urlBase + "&q=" + q + "&appid=" + appId;

            // ここからが、Httpアクセスのソースになる。
            HttpURLConnection con = null;
            InputStream is = null;
            String result = "";
            // この3つは必ずtryに入る前に宣言しておく。

            try {
                URL url = new URL(urlFull);
                con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.connect();
                // con.connect()で、コネクション確立からデータのやり取りまでしてくれる。
                is = con.getInputStream();
                // is はinputStreamの省略形で、Webアクセスの戻り値に合わせて取り扱う。

                result = is2String(is);
            }
            catch(MalformedURLException ex) {
            Log.e(DEBUG_TAG, "URL変換失敗", ex);
            }
            catch(IOException ex) {
            Log.e(DEBUG_TAG, "通信失敗", ex);
            }
            finally {
                if(con != null) {
                con.disconnect();
                // con.disconnect()は必ずfinallyで行う。
                }
                if(is != null) {
                    try {
                        is.close();
                    }
                    catch(IOException ex) {
                        Log.e(DEBUG_TAG, "InputStream解放失敗", ex);
                    }
                }
            }

            return result;
        }

        @Override
        public void onPostExecute(String result) {
            String cityName = "";
            String description = "";
            String latitude = "";
            String longitude = "";
            try {
                JSONObject rootJSON = new JSONObject(result);
                cityName = rootJSON.getString("name");
                JSONArray weatherJSONArray = rootJSON.getJSONArray("weather");
                JSONObject weatherJSON = weatherJSONArray.getJSONObject(0);
                description = weatherJSON.getString("description");
                JSONObject coordJson = rootJSON.getJSONObject("coord");
                latitude = coordJson.getString("lat");
                longitude = coordJson.getString("lon");
            }
            catch(JSONException ex) {
                Log.e(DEBUG_TAG, "JSON解析失敗", ex);
            }

            String title = cityName + "の天気";
            String msg = "現在は" + description + "\n緯度は" + latitude + "で経度は" + longitude;

            WeatherInfoDialog dialog = new WeatherInfoDialog();
            Bundle extras = new Bundle();
            extras.putString("title", title);
            extras.putString("msg", msg);
            dialog.setArguments(extras);
            FragmentManager manager = getSupportFragmentManager();
            dialog.show(manager, "WeatherInfoDialog");
        }

        /**
         * InputStreamオブジェクトを文字列に変換するメソッド。変換文字コードはUTF-8。
         * ここはほとんど定型文なので、コピペして使ってOK。
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
}