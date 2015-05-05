package com.song.dapei.aphone.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.song.dapei.aphone.R;
import com.song.dapei.aphone.client.UserManager;
import com.song.dapei.aphone.util.CommonUtils;

/**
 * 注册
 * 
 * @author songzigw
 *
 */
public class RegisterActivity extends BaseActivity {

	private Button btn_register;
	private EditText et_nickname, et_password, et_repeatpwd;
	private TextView tv_xieyi;
	private ImageView iv_hide, iv_show, iv_hide_2, iv_show_2;

	// private ProgressDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initTopBarForLeft("注册");

		et_nickname = (EditText) findViewById(R.id.et_nickname);
		et_password = (EditText) findViewById(R.id.et_password);
		et_repeatpwd = (EditText) findViewById(R.id.et_repeatpwd);
		tv_xieyi = (TextView) findViewById(R.id.tv_xieyi);
		iv_hide = (ImageView) findViewById(R.id.iv_hide);
		iv_show = (ImageView) findViewById(R.id.iv_show);
		iv_hide_2 = (ImageView) findViewById(R.id.iv_hide_2);
		iv_show_2 = (ImageView) findViewById(R.id.iv_show_2);

		String xieyi = "<font color=" + "\"" + "#AAAAAA" + "\">" + "点击上面的"
				+ "\"" + "注册" + "\"" + "按钮,即表示你同意" + "</font>" + "<br/><u>"
				+ "<font color=" + "\"" + "#576B95" + "\">" + "《松美软件许可及服务协议》"
				+ "</font>" + "</u>";
		tv_xieyi.setText(Html.fromHtml(xieyi));
		iv_hide.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_hide.setVisibility(View.GONE);
				iv_show.setVisibility(View.VISIBLE);
				et_password
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
				// 切换后将EditText光标置于末尾
				CharSequence charSequence = et_password.getText();
				if (charSequence instanceof Spannable) {
					Spannable spanText = (Spannable) charSequence;
					Selection.setSelection(spanText, charSequence.length());
				}
			}
		});
		iv_show.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_show.setVisibility(View.GONE);
				iv_hide.setVisibility(View.VISIBLE);
				et_password
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
				// 切换后将EditText光标置于末尾
				CharSequence charSequence = et_password.getText();
				if (charSequence instanceof Spannable) {
					Spannable spanText = (Spannable) charSequence;
					Selection.setSelection(spanText, charSequence.length());
				}
			}
		});
		iv_hide_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_hide_2.setVisibility(View.GONE);
				iv_show_2.setVisibility(View.VISIBLE);
				et_repeatpwd
						.setTransformationMethod(HideReturnsTransformationMethod
								.getInstance());
				// 切换后将EditText光标置于末尾
				CharSequence charSequence = et_repeatpwd.getText();
				if (charSequence instanceof Spannable) {
					Spannable spanText = (Spannable) charSequence;
					Selection.setSelection(spanText, charSequence.length());
				}
			}
		});
		iv_show_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				iv_show_2.setVisibility(View.GONE);
				iv_hide_2.setVisibility(View.VISIBLE);
				et_repeatpwd
						.setTransformationMethod(PasswordTransformationMethod
								.getInstance());
				// 切换后将EditText光标置于末尾
				CharSequence charSequence = et_repeatpwd.getText();
				if (charSequence instanceof Spannable) {
					Spannable spanText = (Spannable) charSequence;
					Selection.setSelection(spanText, charSequence.length());
				}
			}
		});

		btn_register = (Button) findViewById(R.id.btn_register);
		btn_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				register();
			}
		});
	}

	private void register() {
		String name = et_nickname.getText().toString();
		String password = et_password.getText().toString();
		String pwd_again = et_repeatpwd.getText().toString();

		if (TextUtils.isEmpty(name)) {
			ShowToast(R.string.toast_error_nickname_null);
			return;
		}

		if (TextUtils.isEmpty(password)) {
			ShowToast(R.string.toast_error_password_null);
			return;
		}
		if (!pwd_again.equals(password)) {
			ShowToast(R.string.toast_error_comfirm_password);
			return;
		}

		boolean isNetConnected = CommonUtils.isNetworkAvailable(this);
		if (!isNetConnected) {
			ShowToast(R.string.network_tips);
			return;
		}

		final ProgressDialog progress = new ProgressDialog(
				RegisterActivity.this);
		progress.setMessage("正在注册...");
		progress.setCanceledOnTouchOutside(false);
		progress.show();

		UserManager.getInstance(this);
	}

}
