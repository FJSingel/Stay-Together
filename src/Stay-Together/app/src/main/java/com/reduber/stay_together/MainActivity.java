package com.reduber.stay_together;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.widget.LoginButton;
import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.reduber.backend.base.Base;


public class MainActivity extends Activity {

    private TextView mLoggedInStatusTextView;

    private ProgressDialog mAuthProgressDialog;

    private AuthData authData;

    private static final String TAG = "Login";

    private LoginButton mFacebookLoginButton;

    private Button mPasswordLoginButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Facebook integration
       /* mFacebookLoginButton = (LoginButton)findViewById(R.id.login_with_facebook);
        mFacebookLoginButton.setSessionStatusCallback(new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                //onFacebookSessionStateChange(session, state, exception);
            }
        });*/
        /*//Password
        mPasswordLoginButton = (Button)findViewById(R.id.login_with_password);
        mPasswordLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithPassword();
            }
        });*/

        mLoggedInStatusTextView = (TextView)findViewById(R.id.login_status);

        Firebase.setAndroidContext(getApplicationContext());
/*
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();
*/
        Firebase.setAndroidContext(this);
        Base.root().addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
             //   mAuthProgressDialog.hide();
               // setAuthenticatedUser(authData);
            }
        });
    }

    public void toLobby(View view) {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }
}
   // @Override
 /*   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Facebook integration
        mFacebookLoginButton = (LoginButton)findViewById(R.id.login_with_facebook);
        mFacebookLoginButton.setSessionStatusCallback(new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                onFacebookSessionStateChange(session, state, exception);
            }
        });
        //Password
        mPasswordLoginButton = (Button)findViewById(R.id.login_with_password);
        mPasswordLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWithPassword();
            }
        });

        mLoggedInStatusTextView = (TextView)findViewById(R.id.login_status);

        Firebase.setAndroidContext(getApplicationContext());

        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading");
        mAuthProgressDialog.setMessage("Authenticating with Firebase...");
        mAuthProgressDialog.setCancelable(false);
        mAuthProgressDialog.show();

        Firebase.setAndroidContext(this);
        Base.root().addAuthStateListener(new Firebase.AuthStateListener() {
            @Override
            public void onAuthStateChanged(AuthData authData) {
                mAuthProgressDialog.hide();
                setAuthenticatedUser(authData);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Map<String, String> options = new HashMap<String, String>();
        /* it's the request by the Facebook login button, keep track of the session */
        /*Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (this.authData != null) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        if (this.authData != null) {
            /* logout of Firebase */
          /*  Base.root().unauth();
            /* Logout of any of the Frameworks. This step is optional, but ensures the user is not logged into
             * Facebook/Google+ after logging out of Firebase. */
     /*       if (this.authData.getProvider().equals("facebook")) {
                /* Logout from Facebook */
          /*      Session session = Session.getActiveSession();
                if (session != null) {
                    if (!session.isClosed()) {
                        session.closeAndClearTokenInformation();
                    }
                } else {
                    session = new Session(getApplicationContext());
                    Session.setActiveSession(session);
                    session.closeAndClearTokenInformation();
                }
            }
            setAuthenticatedUser(null);
        }
    }

    private void authWithFirebase(final String provider, Map<String, String> options) {
        if (options.containsKey("error")) {
            showErrorDialog(options.get("error"));
        } else {
            mAuthProgressDialog.show();
            Base.root().authWithOAuthToken(provider, options.get("oauth_token"), new AuthResultHandler(provider));
        }
    }

    private void setAuthenticatedUser(AuthData authData) {
        if (authData != null) {
            /* Hide all the login buttons */
            /*mFacebookLoginButton.setVisibility(View.GONE);
            mPasswordLoginButton.setVisibility(View.GONE);
            mLoggedInStatusTextView.setVisibility(View.VISIBLE);
            /* show a provider specific status text */
            /*String name = null;
            if (authData.getProvider().equals("facebook")) {
                name = (String)authData.getProviderData().get("displayName");
            } else if (authData.getProvider().equals("password")) {
                name = authData.getUid();
            } else {
                Log.e(TAG, "Invalid provider: " + authData.getProvider());
            }
            if (name != null) {
                mLoggedInStatusTextView.setText("Logged in as " + name + " (" + authData.getProvider() + ")");
            }
        } else {
            /* No authenticated user show all the login buttons */
           /* mFacebookLoginButton.setVisibility(View.VISIBLE);
            mPasswordLoginButton.setVisibility(View.VISIBLE);
            mLoggedInStatusTextView.setVisibility(View.GONE);
        }
        this.authData = authData;
        /* invalidate options menu to hide/show the logout button */
       // supportInvalidateOptionsMenu();
    /*}

    private void showErrorDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, null)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private class AuthResultHandler implements Firebase.AuthResultHandler {

        private final String provider;

        public AuthResultHandler(String provider) {
            this.provider = provider;
        }

        @Override
        public void onAuthenticated(AuthData authData) {
            mAuthProgressDialog.hide();
            Log.i(TAG, provider + " auth successful");
            setAuthenticatedUser(authData);
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            mAuthProgressDialog.hide();
            showErrorDialog(firebaseError.toString());
        }
    }

    private void onFacebookSessionStateChange(Session session, SessionState state, Exception exception) {
        if (state.isOpened()) {
            mAuthProgressDialog.show();
            Base.root().authWithOAuthToken("facebook", session.getAccessToken(), new AuthResultHandler("facebook"));
        } else if (state.isClosed()) {
            /* Logged out of Facebook and currently authenticated with Firebase using Facebook, so do a logout */
            /*if (this.authData != null && this.authData.getProvider().equals("facebook")) {
                Base.root().unauth();
                setAuthenticatedUser(null);
            }
        }
    }

    public void loginWithPassword() {
        mAuthProgressDialog.show();
        Base.root().authWithPassword("test@firebaseuser.com", "test1234", new AuthResultHandler("password"));
    }

    /*public void toLobby(View view) {
        Intent intent = new Intent(this, LobbyActivity.class);
        startActivity(intent);
    }*/
//}

