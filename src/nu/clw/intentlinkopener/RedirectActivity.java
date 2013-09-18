/*
 * RedirectActivity
 */
package nu.clw.intentlinkopener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * RedirectActivity
 * @author clworld $Author$
 * @version $Id$
 */
public class RedirectActivity extends Activity {

	/** 送られてきたIntent */
	protected Intent originalIntent;

	/**
	 * onCreate
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// タイトルを通常の物に戻す
		setTitle(R.string.title_activity_main);

		// Intent情報保存
		originalIntent = (Intent) getIntent().clone();
		originalIntent.setComponent(null);

		// 画面表示
		setContentView(R.layout.activity_redirect);

		// リンク作成
		TextView linkView = (TextView) findViewById(R.id.linkView);
		String shareText = originalIntent.getStringExtra(Intent.EXTRA_TEXT);
		if (null != shareText && !"".equals(shareText)) {
			shareText = shareText.replace("\u3000", " ");
			linkView.setText(shareText);
		} else {
			linkView.setText(R.string.text_notext);
		}
	}

	/**
	 * onPause(画面から見えなくなった)
	 */
	@Override
	public void onPause() {
		super.onPause();

		// 別の画面に行った時点で終了する。
		this.finish();
	}

	/**
	 * Intent再送
	 * @param v View
	 */
	public void onResend(View v) {
		try {
			startActivity(originalIntent);
		} catch (Exception e) {
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
}
