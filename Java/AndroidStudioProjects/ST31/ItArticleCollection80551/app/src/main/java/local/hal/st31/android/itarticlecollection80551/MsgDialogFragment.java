package local.hal.st31.android.itarticlecollection80551;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

/**
 * 入力メッセージを表示するダイアログクラス。
 */
public class MsgDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity parent = getActivity();
        Bundle extras = getArguments();
        String msg = "";
        if(extras != null) {
            msg = extras.getString("json");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.tv_ok_button, new MsgDialogButtonClickListener());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * ダイアログのボタンが押された時の処理が記述されたメンバクラス。
     */
    private class MsgDialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    }
}
