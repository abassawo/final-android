package nyc.c4q.android.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import nyc.c4q.android.R;

public class LoginActivity extends Activity {

  private EditText emailField;
  private EditText passwordField;
  private Button loginButton;
  private final AuthenticationManager manager;

  public LoginActivity() {
    // TODO - fix this
    manager = new AuthenticationManager() {
      @Override
      public boolean validateLogin(String email, String password) {
        return false;
      }
    };
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // TODO - load view hierarchy in R.layout.activity_login
    setContentView(R.layout.activity_login);
    // TODO - get references to views, and other setup
   loginButton = (Button) findViewById(R.id.login);
   emailField = (EditText) findViewById(R.id.email);
  passwordField = (EditText) findViewById(R.id.password);



    // TODO - call checkCredentials via OnClickListener

    loginButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        checkCredentials(emailField.getText().toString(), passwordField.getText().toString());
      }
    });
  }

  private void checkCredentials(String email, String password) {
    if(manager.validateLogin(email, password)) {
      // TODO - go to EmailListActivity
      startActivity(new Intent(this, EmailListActivity.class));
    }
    else {
      // TODO launch alert dialog on failed login
      // check strings.xml for dialog
      AlertDialog alertDialog = new AlertDialog.Builder(this).create();
      alertDialog.setTitle(R.string.alert_dialog_title);
      alertDialog.setMessage(R.string.alert_dialog_dismiss + " ");

    }
  }
}
