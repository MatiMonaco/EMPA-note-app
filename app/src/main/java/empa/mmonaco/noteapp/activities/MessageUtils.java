package empa.mmonaco.noteapp.activities;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import empa.mmonaco.noteapp.R;

public class MessageUtils {

    public static void showToast(String message, Context context){
        Toast toast = Toast.makeText(context,message,Toast.LENGTH_SHORT);
        //toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
        System.out.println(toast.getView());
        toast.show();
    }

    public static void showDialog(String title, String message, Runnable onYesClicked, AppCompatActivity activity){
        activity.runOnUiThread(() -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle(title);
            builder.setMessage(message);
            builder.setNegativeButton(R.string.action_no,(dialog,id)->{});
            builder.setPositiveButton(activity.getString(R.string.action_yes), (dialog, id) -> {
                if (onYesClicked != null) {
                    onYesClicked.run();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}
