package animator.jqw.com.animatordemo;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
    private MyButton btn;
    private MyButton btnTween;
    private MyView myView;
    ViewWrapper wrapper;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        btn = (MyButton) findViewById(R.id.btn_pro);

        // 创建包装类,并传入动画作用的对象.
        wrapper = new ViewWrapper(btn);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, btn.getText().toString(), Toast.LENGTH_SHORT).show();
                // 设置动画的对象是包装类的对象
//                ObjectAnimator objectAnimator=ObjectAnimator.ofInt(wrapper, "width",100, 500).setDuration(3000);
//                objectAnimator.start();

            }
        });
        float curTranX = btn.getTranslationX();
        ObjectAnimator translation = ObjectAnimator.ofFloat(btn, "translationX", curTranX, 300,curTranX);
        // 平移动画
        ObjectAnimator rotate = ObjectAnimator.ofFloat(btn, "rotation", 0f, 360f);
        // 旋转动画
        ObjectAnimator alpha = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f, 1f);
        // 透明度动画

        // 步骤2：创建组合动画的对象
        AnimatorSet animSet = new AnimatorSet();

        // 步骤3：根据需求组合动画
        animSet.play(translation);
        animSet.setDuration(5000);

        // 步骤4：启动动画
        animSet.start();



        myView = (MyView) findViewById(R.id.MyView2);
        //改变MButton的透明度
//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(btn, "alpha", 1f, 0f, 1f);
//        objectAnimator.setDuration(5000);
//        objectAnimator.setRepeatCount(2);
//        objectAnimator.start();

//        ObjectAnimator animator = ObjectAnimator.ofFloat(btn, "rotation", 0f, 360f);
//
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是旋转rotation
//        // 动画效果是:0 - 360
//        animator.setDuration(5000);
//        animator.setRepeatCount(2);
//        animator.start();

        float curTranslationX = btn.getTranslationX();
        // 获得当前按钮的位置
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(myView, "translationX", curTranslationX, 300);


        // 表示的是:
        // 动画作用对象是mButton
        // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationX"
        // 动画效果是:从当前位置平移到 x=300 再平移到初始位置
        animator1.setDuration(5000);
        animator1.setRepeatCount(2);
        animator1.start();

//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(btn, "scaleX", 1f, 3f, 1f);
//        // 表示的是:
//        // 动画作用对象是mButton
//        // 动画作用的对象的属性是X轴缩放
//        // 动画效果是:放大到3倍,再缩小到初始大小
//        animator2.setDuration(5000);
//        animator2.setRepeatCount(2);
//        animator2.start();

        ObjectAnimator anim = ObjectAnimator.ofObject(myView, "color", new ColorEvaluator(),
                "#0000FF", "#FF0000");
        // 设置自定义View对象、背景颜色属性值 & 颜色估值器
        // 本质逻辑：
        // 步骤1：根据颜色估值器不断 改变 值
        // 步骤2：调用set（）设置背景颜色的属性值（实际上是通过画笔进行颜色设置）
        // 步骤3：调用invalidate()刷新视图，即调用onDraw（）重新绘制，从而实现动画效果

        anim.setDuration(8000);
        anim.start();
        btnTween= (MyButton) findViewById(R.id.btn_tween);

        Animation translateAnimation = new TranslateAnimation(0,500,0,500);
        // 步骤2：创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
        // 参数分别是：
        // 1. fromXDelta ：视图在水平方向x 移动的起始值
        // 2. toXDelta ：视图在水平方向x 移动的结束值
        // 3. fromYDelta ：视图在竖直方向y 移动的起始值
        // 4. toYDelta：视图在竖直方向y 移动的结束值

        translateAnimation.setDuration(5000);
        // 固定属性的设置都是在其属性前加“set”，如setDuration（）
        btnTween.startAnimation(translateAnimation);
        // 步骤3:播放动画
        btnTween.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, btnTween.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    // 提供ViewWrapper类,用于包装View对象
    // 本例:包装Button对象
    private static class ViewWrapper {
        private View mTarget;

        // 构造方法:传入需要包装的对象
        public ViewWrapper(View target) {
            mTarget = target;
        }

        // 为宽度设置get（） & set（）
        public int getWidth() {
            return mTarget.getLayoutParams().width;
        }

        public void setWidth(int width) {
            mTarget.getLayoutParams().width = width;
            mTarget.requestLayout();
        }

    }


}
