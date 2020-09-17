package local.hal.st31.android.saigoku33memo80551;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * ST31 Androidsサンプル10 都道府県メモアプリ
 *
 * 第一画面表示用アクティビティクラス。
 * 都道府県リストを表示する。
 *
 * @author Shinzo SAITO
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lvTemple = findViewById(R.id.lvTemple);
        List<String> TempleList = createTempleectureList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, TempleList);
        lvTemple.setAdapter(adapter);
        lvTemple.setOnItemClickListener(new ListItemClickListener());
    }

    /**
     * 都道府県リストを生成するメソッド。
     * @return 都道府県リストオブジェクト。
     */
    private List<String> createTempleectureList(){
        List<String> TempleList = new ArrayList<>();
        TempleList.add("第一番 　青岸渡寺");
        TempleList.add("第二番 　金剛宝寺");
        TempleList.add("第三番 　粉河寺");
        TempleList.add("第四番 　施福寺");
        TempleList.add("第五番 　葛井寺");
        TempleList.add("第六番 　南法華寺");
        TempleList.add("第七番 　岡寺");
        TempleList.add("第八番 　長谷寺");
        TempleList.add("第九番 　南円堂");
        TempleList.add("第十番 　三室戸寺");
        TempleList.add("第十一番 　上醍醐 准胝堂");
        TempleList.add("第十二番 　正法寺");
        TempleList.add("第十三番 　石山寺");
        TempleList.add("第十四番 　三井寺");
        TempleList.add("第十五番 　今熊野観音寺");
        TempleList.add("第十六番 　清水寺");
        TempleList.add("第十七番 　六波羅蜜寺");
        TempleList.add("第十八番 　六角堂 頂法寺");
        TempleList.add("第十九番 　革堂 行願寺");
        TempleList.add("第二十番 　善峯寺");
        TempleList.add("第二十一番 　穴太寺");
        TempleList.add("第二十二番 　総持寺");
        TempleList.add("第二十三番 　勝尾寺");
        TempleList.add("第二十四番 　中山寺");
        TempleList.add("第二十五番 　播州清水寺");
        TempleList.add("第二十六番 　一乗寺");
        TempleList.add("第二十七番 　圓教寺");
        TempleList.add("第二十八番 　成相寺");
        TempleList.add("第二十九番 　松尾寺");
        TempleList.add("第三十番 　宝厳寺");
        TempleList.add("第三十一番 　長命寺");
        TempleList.add("第三十二番 　観音正寺");
        TempleList.add("第三十三番 　華厳寺");
        return  TempleList;
    }
    /**
     * リストが選択された時の処理が記述されたメンバクラス。
     * 第2画面へ処理を移管する。
     */
    private class ListItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String TempleName = (String) parent.getItemAtPosition(position);

            Intent intent = new Intent(getApplicationContext(), TempleEditActivity.class);
            intent.putExtra("selectedTempleNo", position);
            intent.putExtra("selectedTempleName", TempleName);
            startActivity(intent);

        }
    }
}
