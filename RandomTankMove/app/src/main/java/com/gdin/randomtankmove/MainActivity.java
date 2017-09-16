package com.gdin.randomtankmove;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    MyView myView;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView =(MyView) findViewById(R.id.my_view);
        myView.setReady();
        //myView = new MyView(this);
        Log.i("999999","999999");
        //new Thread(myView).start();
        //setContentView(myView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                    while (myView.isRun) {
                        myView.bit_map = myView.adjustPhotoRotation(myView.bitmap, myView.dir);
                        for (int i = 0; i < 5; i++) {
                            myView.postInvalidate();
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        myView.dir = GetNum.getRands();
                    }}
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        }).start();


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("apple","true");
                myView.isRun = true;
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myView.isRun = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Tag", "onDestroy");
        //关闭线程,通过boolean值       (注意myView是空引用，没有地址的出现空指针异常)。
        //myView.isRun = false;
    }

}
