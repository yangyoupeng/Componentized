package com.maoliicai.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.maoliicai.common.R;


/**
 * @author mumuji
 * @version 1.0.0
 * @date 2017/10/31
 * @description
 */

@SuppressLint("AppCompatCustomView")
public class RoundRectImageView extends ImageView {

    private static final Bitmap.Config BITMAP_CONFIG = Bitmap.Config.ARGB_8888;
    private static final int COLORDRAWABLE_DIMENSION = 2;
    private final Matrix mMatrix = new Matrix();
    private Context mContext;
    private float mRadiusTopLeft;
    private float mRadiusTopRight;
    private float mRadiusBottomLeft;
    private float mRadiusBottomRight;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;

    public RoundRectImageView(Context context) {
        this(context, null);
    }

    public RoundRectImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundRectImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RoundRectImageView);
        mRadiusTopLeft = ta.getDimension(R.styleable.RoundRectImageView_roundRadiusTopLeft, 0);
        mRadiusTopRight = ta.getDimension(R.styleable.RoundRectImageView_roundRadiusTopRight, 0);
        mRadiusBottomLeft = ta.getDimension(R.styleable.RoundRectImageView_roundRadiusBottomLeft, 0);
        mRadiusBottomRight = ta.getDimension(R.styleable.RoundRectImageView_roundRadiusBottomRight, 0);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.save();
//            Shader shader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
//            mPaint.setShader(shader);
        if (getDrawable() == null) {
            return;
        }
        int width = getWidth();
        int height = getHeight();
        RectF rectF = new RectF(0, 0, width, height);
        float[] radii = {mRadiusTopLeft,mRadiusTopLeft,
                mRadiusTopRight,mRadiusTopRight,
                mRadiusBottomRight,mRadiusBottomRight,
                mRadiusBottomLeft,mRadiusBottomLeft};//8个数据表示4个角每个角的弧度
        Path path = new Path();
        path.addRoundRect(rectF,radii, Path.Direction.CW);
//        canvas.drawRoundRect(rectF, mRadius, mRadius, mPaint);//矩形(100,100,500,300),圆角的横向半径mRadius，纵向半径mRadius
        canvas.drawPath(path,mPaint);
        canvas.restore();
//        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        Bitmap bitmap = BitmapFactory.decodeResource(mContext.getResources(),R.mipmap.bg_guide4);
////        Bitmap target = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
////        Canvas canvas = new Canvas(target);
//        int width = DensityUtils.dp2px(mContext,275);
//        int height = DensityUtils.dp2px(mContext,350);
//        RectF rect = new RectF(0, 0, width, height);
//        canvas.drawRoundRect(rect, DensityUtils.dp2px(mContext,20), DensityUtils.dp2px(mContext,20), paint);
//        // 核心代码取两个图片的交集部分
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//        canvas.drawBitmap(bitmap, 0, 0, paint);
//
//        paint.setXfermode(null);
//        canvas.restoreToCount(saved);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        setUpShader();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
        mBitmap = bm;
        setUpShader();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        mBitmap = getBitmapFromDrawable(drawable);
        setUpShader();
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setUpShader();
    }

    @Override
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        mBitmap = getBitmapFromDrawable(getDrawable());
        setUpShader();
    }

    private Bitmap getBitmapFromDrawable(Drawable drawable) {
        if (drawable == null) {
            return null;
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        try {
            Bitmap bitmap;

            if (drawable instanceof ColorDrawable) {
                bitmap = Bitmap.createBitmap(COLORDRAWABLE_DIMENSION, COLORDRAWABLE_DIMENSION, BITMAP_CONFIG);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), BITMAP_CONFIG);
            }

            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        } catch (OutOfMemoryError e) {
            return null;
        }
    }

    private void setUpShader() {
        if (mBitmap == null) {
            return;
        }

        // 将mBitmap作为着色器，就是在指定区域内绘制bmp
        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float scale = 1.0f;

        // shader的变换矩阵，我们这里主要用于放大或者缩小
        if (getScaleType() == ScaleType.FIT_XY) {
            //长宽拉伸
            mMatrix.setScale(getWidth() * 1.0f / mBitmap.getWidth(), getHeight()
                    * 1.0f / mBitmap.getHeight());
        } else {//默认为centercrop样式
            // 如果图片的宽或者高与view的宽高不匹配，计算出需要缩放的比例；缩放后的图片的宽高，一定要大于我们view的宽高；所以我们这里取大值；
            scale = Math.max(getWidth() * 1.0f / mBitmap.getWidth(), getHeight()
                    * 1.0f / mBitmap.getHeight());

            mMatrix.setScale(scale, scale);

            float moveHeight = 0.0f;
            float moveWidth = 0.0f;
            if (getWidth() * 1.0f / mBitmap.getWidth() > getHeight() * 1.0f / mBitmap.getHeight()) {//宽比较小，以宽为基础放大
                //移动Y轴
                moveHeight = (getWidth() * 1.0f / mBitmap.getWidth() * mBitmap.getHeight() - getHeight()) / 2;
            } else {//以高为基础放大
                //移动X轴
                moveWidth = (getHeight() * 1.0f / mBitmap.getHeight() * mBitmap.getWidth() - getWidth()) / 2;
            }
            //默认显示的是左上部分的图片，所以需要计算移动到中间的距离
            mMatrix.postTranslate(-moveWidth, -moveHeight);
        }

        // 设置变换矩阵
        mBitmapShader.setLocalMatrix(mMatrix);
        // 设置shader
        mPaint.setShader(mBitmapShader);
        //刷新界面
        invalidate();
    }

}
