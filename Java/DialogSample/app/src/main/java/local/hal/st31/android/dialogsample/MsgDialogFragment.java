package local.hal.st31.android.dialogsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.security.PublicKey;

/**
 * ST31 Androidサンプル13cダイアログ
 *
 * 入力メッセージを表示するダイアログクラス。
 *
 * @author Shinzo SAITO
 */
public class MsgDialogFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity parent = getActivity();
        Bundle extras = getArguments();
        String msg = "";
        if(extras != null) {
            msg = extras.getString("msg");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
        builder.setTitle(R.string.dlg_msg_msg);
        builder.setMessage(msg);
        builder.setPositiveButton(R.string.dlg_msg_btn, new MsgDialogButtonClickListener());
        AlertDialog dialog = builder.create();
        return dialog;
    }

    /**
     * ダイアログのボタンが押された時の処理が記述されたメンバクラス。
     */
    private class MsgDialogButtonClickListener implements DialogInterface.OnClickListener {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            Activity parent = getActivity();
            Toast.makeText(parent, R.string.dlg_msg_toast, Toast.LENGTH_SHORT).show();
        }
    }
}
