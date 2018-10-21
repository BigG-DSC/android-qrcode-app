package eitdigital.big.brigtheyedapp.access;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import eitdigital.big.brigtheyedapp.R;

public class Access_Activity extends Activity {

    private static FragmentManager fragmentManager;

    /** TAG per i frammenti **/
    private static String Login = "AccessDoctor_Fragment";
    private static String ForgotPassword = "AccessPatient_Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        fragmentManager = getFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                .beginTransaction()
                .replace(R.id.frameContainer, new AccessDoctor_Fragment(), Login)
                .commit();
        }
    }

    /** Replace Login Fragment */
    protected void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new AccessDoctor_Fragment(), Login).commit();
    }

    /** Controllo comportamento bottone < [BACK] **/
    @Override
    public void onBackPressed() {
        /** Recupera frammento 'Ho dimenticato la password' **/
        Fragment ForgotPassword_Fragment = fragmentManager
                .findFragmentByTag(ForgotPassword);

        // If fragment not null then replace login fragment else do backpressed task
        if (ForgotPassword_Fragment != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}