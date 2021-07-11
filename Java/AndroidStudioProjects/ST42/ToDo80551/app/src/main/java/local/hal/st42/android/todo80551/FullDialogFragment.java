package local.hal.st42.android.todo80551;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class FullDialogFragment extends DialogFragment {

    private DatabaseHelper _helper;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity parent = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
        builder.setTitle(R.string.dialog_title);
        builder.setMessage(R.string.dialog_message);
        builder.setPositiveButton(R.string.dialog_positive, new DialogButtonClickListener());
        builder.setNegativeButton(R.string.dialog_negative, new DialogButtonClickListener());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * ダイアログのボタンが押されたときの処理が記述されたメンバクラス。
     */
    private class DialogButtonClickListener implements DialogInterface.OnClickListener {

        @Override
        public void onClick(DialogInterface dialog, int which) {


            Activity parent = getActivity();
            Bundle bundle = getArguments();
            long _idNo = bundle.getLong("id");

            switch(which) {
                case DialogInterface.BUTTON_POSITIVE:
                    _helper = new DatabaseHelper(parent.getApplicationContext());
                    SQLiteDatabase db = _helper.getWritableDatabase();
                    DataAccess.delete(db, _idNo);
                    parent.finish();
                    break;
                case DialogInterface.BUTTON_NEGATIVE:
                    break;
            }
        }
    }
}
