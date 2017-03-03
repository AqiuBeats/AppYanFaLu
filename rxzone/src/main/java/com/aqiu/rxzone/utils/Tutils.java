package com.aqiu.rxzone.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

/**
 *单例显示Toast的方法 
 */
public class Tutils {

	
	private static Toast toast;

	public static void showToast(Context context, String msg){
		if(toast == null){
			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		}
		toast.setText(msg);
		toast.show();
	}

	/**
	 * 位移动画
	 *
	 * @param view
	 * @param xFrom
	 * @param xTo
	 * @param yFrom
	 * @param yTo
	 * @param duration
	 */
	public static void translateAnimation(View view, float xFrom, float xTo,
										  float yFrom, float yTo, long duration) {

		TranslateAnimation translateAnimation = new TranslateAnimation(
				Animation.RELATIVE_TO_SELF, xFrom, Animation.RELATIVE_TO_SELF, xTo,
				Animation.RELATIVE_TO_SELF, yFrom, Animation.RELATIVE_TO_SELF, yTo);
		translateAnimation.setFillAfter(false);
		translateAnimation.setDuration(duration);
		view.startAnimation(translateAnimation);
		translateAnimation.startNow();
	}
	
}
