package com.example.kota.loginapplication.activities;

        import android.os.Bundle;
        import android.support.design.widget.Snackbar;
        import android.support.design.widget.TextInputEditText;
        import android.support.design.widget.TextInputLayout;
        import android.support.v4.widget.NestedScrollView;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.AppCompatButton;
        import android.support.v7.widget.AppCompatTextView;
        import android.view.View;

        import com.example.kota.loginapplication.R;
        import com.example.kota.loginapplication.database.DatabaseHelper;
        import com.example.kota.loginapplication.model.User;
        import com.example.kota.loginapplication.validation.InputValidation;

/**
 * Created by kota on 12/19/2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;
    private TextInputLayout textInputLayoutFName;
    private TextInputLayout textInputLayoutLName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputEditText textInputEditTextFName;
    private TextInputEditText textInputEditTextLName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private AppCompatButton appCompatButtonSignUp;
    private AppCompatTextView appCompatTextViewLoginLink;
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }
    /*
     *Views Intialized
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutFName = (TextInputLayout) findViewById(R.id.textInputLayoutFname);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPwd);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfpwd);

        textInputEditTextFName = (TextInputEditText) findViewById(R.id.textInputEditTextFname);
        textInputEditTextLName = (TextInputEditText) findViewById(R.id.textInputEditTextLname);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPwd);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextconfPwd);

        appCompatButtonSignUp = (AppCompatButton) findViewById(R.id.appCompatButtonSignUp);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.textViewLinkLogin);

    }
    /*
     *Listeners Intialized
     */
    private void initListeners() {
        appCompatButtonSignUp.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects() {
        inputValidation = new InputValidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User();
    }
    /*
     * onClick Listener of view
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonSignUp:
                postDataToSQLite();
                break;

            case R.id.textViewLinkLogin:
                finish();
                break;
        }
    }
    /*
     * Validates input text and store data to SQLite
     */
    private void postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextFName, textInputLayoutFName, getString(R.string.error_message_fname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextLName, textInputLayoutFName, getString(R.string.error_message_lname))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }

        if (!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())) {

            user.setFirstName(textInputEditTextFName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());

            databaseHelper.addUser(user);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }


    }

    private void emptyInputEditText() {
        textInputEditTextFName.setText(null);
        textInputEditTextLName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}
