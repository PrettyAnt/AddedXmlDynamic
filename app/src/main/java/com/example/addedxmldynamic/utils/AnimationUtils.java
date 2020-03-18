package com.example.addedxmldynamic.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.addedxmldynamic.R;

/**
 * @author chenyu.
 *         Created on 下午1:59 2018/11/2.
 *         Author'github https://github.com/PrettyAnt
 */

public class AnimationUtils {

    /**
     * 渐隐渐现动画
     *
     * @param view     需要实现动画的对象
     * @param state    需要实现的状态
     * @param duration 动画实现的时长（ms）
     */
    public static void showAndHiddenAnimation(Activity activity,final View view, AnimationState state, long duration) {
        View firstLine = view.findViewById(R.id.ll_first_line);
        View secondLine = view.findViewById(R.id.ll_second_line);
        AnimatorSet animatorSet = new AnimatorSet();//组合动画
        if (state == AnimationState.STATE_SHOW) {//如果设置为显示状态
            view.setVisibility(View.VISIBLE);
//            addAnimation(view, state, duration, 0, 1);
           /* 补间动画---平移
           View firstLine = view.findViewById(R.id.ll_first_line);
            TranslateAnimation translateAnimation = new TranslateAnimation(0.0f, 300.0f, 0.0f, 0f);
            translateAnimation.setDuration(1000);
            translateAnimation.setFillAfter(true);
            firstLine.startAnimation(translateAnimation);
            translateAnimation.start();*/


            ObjectAnimator translationX = ObjectAnimator.ofFloat(firstLine, "translationX", 400, 0f);
            ObjectAnimator translationX2 = ObjectAnimator.ofFloat(secondLine, "translationX", -400, 0f);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 0, 1f);
            animatorSet.setDuration(2000);
            animatorSet.playTogether(translationX,translationX2,alpha);
//            animatorSet.playTogether(translationX,translationX2);
//            animatorSet.play(alpha).after(translationX);
            animatorSet.start();
        } else if (state == AnimationState.STATE_HIDDEN) {//如果设置为隐藏状态
            ObjectAnimator translationX = ObjectAnimator.ofFloat(firstLine, "translationX", 0, 400f);
            ObjectAnimator translationX2 = ObjectAnimator.ofFloat(secondLine, "translationX", 0, -400f);
            ObjectAnimator alpha = ObjectAnimator.ofFloat(view, "alpha", 1, 0f);
//            AnimatorSet.Builder play = animatorSet.play(alpha);
            animatorSet.playTogether(translationX,translationX2,alpha);
//            animatorSet.play(alpha).after(translationX);
//            animatorSet.play(alpha);
            animatorSet.setDuration(500);
            animatorSet.start();
            animatorSet.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    if (view instanceof LinearLayout) {
                        ((LinearLayout) view).removeAllViews();
                    }
                    if (view instanceof RelativeLayout) {
                        ((RelativeLayout) view).removeAllViews();
                    }
                }
            });

//            view.setVisibility(View.INVISIBLE);
//            addAnimation(view, state, duration, 1, 0);
        }
    }

    /**
     * 添加动画
     *
     * @param view
     * @param state
     * @param duration 持续时间
     * @param start    开始倍数
     * @param end      结束倍数
     */
    private static void addAnimation(final View view, final AnimationState state, long duration, int start, int end) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(start, end);
        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.clearAnimation();
                view.invalidate();
                if (state == AnimationState.STATE_HIDDEN) {
                    if (view instanceof LinearLayout) {
                        ((LinearLayout) view).removeAllViews();
                    } else if (view instanceof RelativeLayout) {
                        ((RelativeLayout) view).removeAllViews();
                    }
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.setAnimation(alphaAnimation);
        alphaAnimation.start();
    }

    public enum AnimationState {
        STATE_SHOW,
        STATE_HIDDEN
    }
}
