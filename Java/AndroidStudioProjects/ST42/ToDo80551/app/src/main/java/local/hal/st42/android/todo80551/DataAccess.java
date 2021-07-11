package local.hal.st42.android.todo80551;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * ST42 課題01 ToDoアプリ作成
 *
 * データベース上のデータを処理するクラス。
 *
 * @author Kiharaten
 */
public class DataAccess {
    /**
     * 全データ検索メソッド。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @return 検索結果のCursorオブジェクト。
     */
    public static Cursor findAll(SQLiteDatabase db) {
        String sql = "SELECT * FROM tasks ORDER BY done ASC, deadline DESC";
        Cursor cursor = db.rawQuery(sql, null);

        return cursor;
    }

    public static Cursor findFinished(SQLiteDatabase db) {
        String sql = "SELECT * FROM tasks WHERE done = 1 ORDER BY deadline DESC";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    public static Cursor findUnfinished(SQLiteDatabase db) {
        String sql = "SELECT * FROM tasks WHERE done = 0 ORDER BY deadline ASC";
        Cursor cursor = db.rawQuery(sql, null);
        return cursor;
    }

    /**
     * 主キーによる検索。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 34主キーに対応するデータを格納したTaskオブジェクト。対応するデータが存在しない場合はnull。
     */
    public static Task findByPK(SQLiteDatabase db, long id) {
        String sql = "SELECT * FROM tasks WHERE _id = " + id;
        Cursor cursor = db.rawQuery(sql, null);
        Task result = null;

        if(cursor.moveToFirst()) {
            int idxName = cursor.getColumnIndex("name");
            int idxDeadline = cursor.getColumnIndex("deadline");
            int idxDone = cursor.getColumnIndex("done");
            int idxNote = cursor.getColumnIndex("note");
            String name = cursor.getString(idxName);
            String deadline = cursor.getString(idxDeadline);
            long done = cursor.getInt(idxDone);
            String note = cursor.getString(idxNote);

            result = new Task();
            result.setId(id);
            result.setName(name);
            result.setDeadline(deadline);
            result.setDone(done);
            result.setNote(note);
        }
        return result;
    }

    /**
     * 店舗情報を更新するメソッド。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @param name 店名。
     * @param deadline 期限。
     * @param done 完了フラグ。
     * @param note 詳細。
     * @return 更新件数。
     */
    public static int update(SQLiteDatabase db, long id, String name, String deadline, long done, String note) {
        String sql = "UPDATE tasks SET name = ?, deadline = ?, done = ?, note = ? WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, name);
        stmt.bindString(2, deadline);
        stmt.bindLong(3, done);
        stmt.bindString(4, note);
        stmt.bindLong(5, id);
        int result = stmt.executeUpdateDelete();

        return result;
    }

    /**
     * 店舗情報を新規登録するメソッド。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @param name 店名。
     * @param deadline 期限。
     * @param done 完了フラグ。
     * @param note 詳細。
     * @return 登録されたレコードの主キー値。
     */
    public static long insert(SQLiteDatabase db, String name, String deadline, long done, String note) {
        String sql = "INSERT INTO tasks (name, deadline, done, note) VALUES (?, ?, ?, ?)";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindString(1, name);
        stmt.bindString(2, deadline);
        stmt.bindLong(3, done);
        stmt.bindString(4, note);
        long id = stmt.executeInsert();

        return id;
    }

    /**
     * メモ情報を削除するメソッド。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 主キー値。
     * @return 削除件数。
     */
    public static int delete(SQLiteDatabase db, long id) {
        String sql = "DELETE FROM tasks WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        stmt.bindLong(1, id);
        int result = stmt.executeUpdateDelete();

        return result;
    }

    /**
     * checkedフラグのon/offを変更するメソッド。
     *
     * @param db SQLiteDatabaseオブジェクト。
     * @param id 変更対象レコードの主キー値。
     * @param isChecked trueの場合フラグをon(値を1)に、falseの場合フラグをoff(値を0)に変更する。
     */
    public static void changePhoneChecked(SQLiteDatabase db, long id, boolean isChecked) {
        String sql = "UPDATE tasks SET done = ? WHERE _id = ?";
        SQLiteStatement stmt = db.compileStatement(sql);
        if(isChecked) {
            stmt.bindLong(1, 1);
        }
        else {
            stmt.bindLong(1, 0);
        }
        stmt.bindLong(2, id);
        stmt.executeUpdateDelete();
    }
}
