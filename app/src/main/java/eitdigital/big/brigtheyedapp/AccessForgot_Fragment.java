package eitdigital.big.brigtheyedapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessForgot_Fragment extends Fragment implements OnClickListener {

	private static View view;

	private static EditText emailId;
	private static TextView submit, back;

	private static LinearLayout forgotPasswordLayout;
	private static Animation shakeAnimation;

    private static String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";

	public AccessForgot_Fragment() {}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.forgotpassword_layout, container, false);
		initViews();
		setListeners();
		forgotPasswordLayout = (LinearLayout) view.findViewById(R.id.forgotPassword_layout);
		shakeAnimation = AnimationUtils.loadAnimation(getActivity(), R.anim.shake);

		return view;
	}

	/** Views initialization */
	private void initViews() {
		emailId = (EditText) view.findViewById(R.id.registered_emailid);
		submit = (TextView) view.findViewById(R.id.forgot_button);
		back = (TextView) view.findViewById(R.id.backToLoginBtn);


	}

	/** Set button Listeners */
	private void setListeners() {
		back.setOnClickListener(this);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.backToLoginBtn:
				// Replace Login Fragment on Back Pressed
				new Access_Activity().replaceLoginFragment();
				break;
			case R.id.forgot_button:
				// Call Reset Request task
				sendResetRequest();
				break;
		}

	}

	private void sendResetRequest() {
		String getEmailId = emailId.getText().toString();

		// Pattern for email id validation
		Pattern p = Pattern.compile(regEx);

		// Match the pattern
		Matcher m = p.matcher(getEmailId);

		// First check if email id is not null else show error toast
		if (getEmailId.equals("") || getEmailId.length() == 0){
			new CustomToast()
					.Show_Toast(getActivity(), view, "Please, insert your credentials before submitting");
			forgotPasswordLayout.startAnimation(shakeAnimation);
		}

		// Check if email id is valid or not
		else if (!m.find()) {
			new CustomToast()
					.Show_Toast(getActivity(), view, "Invalid credentials!");
			forgotPasswordLayout.startAnimation(shakeAnimation);
		}
		// Else submit email id and fetch password or do your stuff
		else
			Toast.makeText(getActivity(), "Get Forgot Password.",
					Toast.LENGTH_SHORT).show();
	}
}