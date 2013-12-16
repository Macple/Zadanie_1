package pl.plewko.android.zadanie_1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Maciej Plewko
 * @since December 6, 2013
 */
public class MainActivity extends Activity {

    final private Context context = this;
    private Button loginButton;
    private TextView nameTextField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        loginButton = (Button) findViewById(R.id.login_button);
        nameTextField = (EditText) findViewById(R.id.person_name);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog loginDialog = new Dialog(context);
                loginDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                loginDialog.setContentView(R.layout.login_dialog);

                String name = "";
                String message = "";

                try {
                    name = nameTextField.getText().toString().trim();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

                TextView dialogMessage = (TextView) loginDialog.findViewById(R.id.login_dialog_text);
                Button dialogButton = (Button) loginDialog.findViewById(R.id.dialog_button_ok);


                if (name.length() > 0) {
                    message = "Hello " + name + "!";
                } else {
                    message = "Name field is empty.\nPlease enter your name.";
                }

                dialogMessage.setText(message);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loginDialog.dismiss();
                    }
                });

                loginDialog.show();
            }
        });
    }

}