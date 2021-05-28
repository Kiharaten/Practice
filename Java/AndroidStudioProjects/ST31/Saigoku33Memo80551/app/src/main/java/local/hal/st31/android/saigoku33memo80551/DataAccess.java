package local.hal.st31.android.saigoku33memo80551;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * ST31　Androidサンプル10 都道府県メモアプリ
 *
 * データベース上のデータを処理するクラス。
 *
 * @author Shinzo SAITO
 */
public class DataAccess {
    /**
     * 主キーによるメモ内容検索メソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するcontentカラムの値。
     */
    public static ArrayList<String> findContentByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT honzon,shushi,address,url,note FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        ArrayList<String> result = new ArrayList<String>();

        result.add(0,"");
        result.add(1,"");
        result.add(2,"");
        result.add(3,"");
        result.add(4,"");

        while(cursor.moveToNext()) {
            int idxHonzon = cursor.getColumnIndex("honzon");
            int idxShushi = cursor.getColumnIndex("shushi");
            int idxAddress = cursor.getColumnIndex("address");
            int idxUrl = cursor.getColumnIndex("url");
            int idxNote = cursor.getColumnIndex("note");
            result.set(0,cursor.getString(idxHonzon));
            result.set(1,cursor.getString(idxShushi));
            result.set(2,cursor.getString(idxAddress));
            result.set(3,cursor.getString(idxUrl));
            result.set(4,cursor.getString(idxNote));
        }
        return result;
    }

    /**
     * 主キーによるレコード存在チェックメソッド。
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 主キーに対応するcontentカラムの値。
     */
    public static boolean findRowByPK(SQLiteDatabase db, int id) {
        String sql = "SELECT COUNT(*) AS count FROM temples WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        boolean result = false;
        if(cursor.moveToFirst()) {
            int idxCount = cursor.getColumnIndex("count");
            int count = cursor.getInt(idxCount);
            if(count >= 1) {
                result = true;
            }
        }
        return result;
    }

    /**
     * メモ情報を更新するメソッド。
     */
    public static int update(SQLiteDatabase db, int id, String name, String honzon, String shushi, String address , String url , String note ) {
        String sql = "UPDATE temples SET name = ?, honzon = ?, shushi = ?, address = ?, url = ?, note = ? WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, name);
        stmt.bindString(2, honzon);
        stmt.bindString(3, shushi);
        stmt.bindString(4, address);
        stmt.bindString(5, url);
        stmt.bindString(6, note);
        stmt.bindLong(7, id);
        int result = stmt.executeUpdateDelete();
        return result;
    }

    /**
     * メモ情報を新規登録するメソッド。
     */
    public static long insert(SQLiteDatabase db, int id, String name, String honzon, String shushi, String address, String url, String note) {
        String sql = "INSERT INTO temples (_id, name, honzon,shushi,address,url,note) VALUES (?, ?, ?, ?, ?, ?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, id);
        stmt.bindString(2, name);
        stmt.bindString(3, honzon);
        stmt.bindString(4, shushi);
        stmt.bindString(5, address);
        stmt.bindString(6, url);
        stmt.bindString(7, note);
        long insertedId = stmt.executeInsert();
        return insertedId;
    }
}
