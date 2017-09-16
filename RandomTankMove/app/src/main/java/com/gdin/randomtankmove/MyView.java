package com.gdin.randomtankmove;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;
import android.util.AttributeSet;
import android.util.Log;
/**
 * Created by aceracer on 2017/9/15.
 */
public class MyView extends View {

    Bitmap bitmap,bit_map;
    boolean isRun = false;

    //锚点
    int x = 100, y = 150;   //图片锚点
    int speed = 20;
    //定义位置
    final int RIGHT = 1;
    final int LEFT = 2;
    final int UP = 3;
    final int DOWN = 4;
    //定义方向
    int dir = DOWN;

    int bitmapWidth, bitmapHight;
    int limitW,limitH;

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context,AttributeSet set){
        super(context,set);
    }

    public void setReady(){
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.modal_tank);
        bit_map=bitmap;
        Log.i("bbb_mmm",bit_map.toString());
        //得到图片宽高
        bitmapWidth = bitmap.getWidth();
        bitmapHight = bitmap.getHeight();
        //不需要触摸事件,但是精灵移动属于耗时操作
        //new Thread(this).start();
        limitH=600-bitmapHight;
        limitW=400 - bitmapWidth;
        Log.i("limit",limitH+"");
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Log.i("yyyyyyyy",bit_map.toString());
        canvas.drawBitmap(bit_map, x, y, null);
        //移动位置
        if (dir == RIGHT) {
            x += speed;
        } else if (dir == DOWN) {
            y += speed;
        } else if (dir == LEFT) {
            x -= speed;
        } else if (dir == UP) {
            y -= speed;
        }
        //方向
        if (x > this.getWidth() - bitmapWidth) {
            dir = DOWN;
            //
            x = this.getWidth() - bitmapWidth;
        } else if (y > this.getHeight() - bitmapHight) {
            dir = LEFT;
            y = this.getHeight() - bitmapHight;
        } else if (x < 0) {
            dir = UP;
            x = 0;
        } else if (y < 0) {
            dir = RIGHT;
            y = 0;
        }

    }
    public Bitmap adjustPhotoRotation(Bitmap bitmap,int curDir){
        int deg;
        Matrix m = new Matrix();
        switch (curDir){
            case RIGHT:
                deg = 90;
                break;
            case UP:
                deg = 0;
                break;
            case DOWN:
                deg = 180;
                break;
            default:deg = -90;
        }
        m.setRotate(deg);
        return Bitmap.createBitmap(bitmap, 0,0, bitmap.getWidth(), bitmap.getHeight(), m, true);
    }
    /*
    @Override
    public void run() {
        while (isRun) {
            bit_map=adjustPhotoRotation(bitmap,dir);
            for (int i=0;i<5;i++) {
                this.postInvalidate();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dir=GetNum.getRands();
        }
    }*/
}
