/**
 * 
 */
package com.gf.test.videoplayer;

import org.xutils.x;

import android.app.Activity;
import android.os.Bundle;

public abstract class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getLayoutRes());
		x.view().inject(this);
		init();
	}
	
	public abstract int getLayoutRes();
	public abstract void init();
}
