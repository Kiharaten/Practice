package local.hal.st31.android.favoriteshops80551;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import android.content.Intent;

/**
 * シンプルなダイアログクラス。
 */
public class DeleteDialogFragment extends DialogFragment {
    /**
     * データベースヘルパーオブジェクト。
     */
    private DatabaseHelper _helper;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity parent = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
        builder.setMessage(R.string.dlg_simple_msg);
        builder.setPositiveButton(R.string.dlg_btn_ok, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dlg_btn_ng, null);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * ダイアログのボタンが選択された時の処理が記述されたメンバクラス。
     */
    private class DialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Activity parent = getActivity();

            Toast.makeText(parent,R.string.dlg_simple_toast, Toast.LENGTH_SHORT).show();

            Intent intent = parent.getIntent();

            Long _idNo = intent.getLongExtra("idNo", -1);

            _helper = new DatabaseHelper(parent.getApplicationContext());

            SQLiteDatabase db = _helper.getWritableDatabase();
            DataAccess.delete(db, _idNo);

            parent.finish();
        }
    }
}
