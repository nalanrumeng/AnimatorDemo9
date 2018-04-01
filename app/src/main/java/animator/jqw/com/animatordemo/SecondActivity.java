package animator.jqw.com.animatordemo;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

public class SecondActivity extends Activity {
    private Context context;
    private MyButton btnTween;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        context=SecondActivity.this;
        btnTween= (MyButton) findViewById(R.id.btn_tween);
        // 步骤1：创建平移动画的对象：平移动画对应的Animation子类为TranslateAnimation
        // 参数分别是：
        // 1. fromXDelta ：视图在水平方向x 移动的起始值
        // 2. toXDelta ：视图在水平方向x 移动的结束值
        // 3. fromYDelta ：视图在竖直方向y 移动的起始值
        // 4. toYDelta：视图在竖直方向y 移动的结束值
//        final Animation translateAnimation = new TranslateAnimation(0,500,0,500);
//        // 固定属性的设置都是在其属性前加“set”，如setDuration（）
//
//        translateAnimation.setDuration(5000);
//        // 步骤3:播放动画
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                btnTween.startAnimation(translateAnimation);
//            }
//        },2000);

        // 步骤1:创建 需要设置动画的 视图View.
        final Animation translateAnimation1 = AnimationUtils.loadAnimation(this, R.anim.translate);
        // 步骤2:创建 动画对象 并传入设置的动画效果xml文件
        btnTween.startAnimation(translateAnimation1);
        findViewById(R.id.control_Animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnTween.startAnimation(translateAnimation1);
            }
        });




//        btnTween.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, btnTween.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}
