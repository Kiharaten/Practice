package local.hal.st31.android.post2db80551;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

public class FullDialogFragment extends DialogFragment{

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity parent = getActivity();

        Bundle bundle = getArguments();
        String json = bundle.getString("json");

        AlertDialog.Builder builder = new AlertDialog.Builder(parent);
        builder.setMessage(json);
        AlertDialog dialog = builder.create();
        return dialog;
    }
}