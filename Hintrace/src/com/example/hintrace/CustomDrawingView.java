package com.example.hintrace;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

@SuppressLint("DrawAllocation")
public class CustomDrawingView extends View {

	private ArrayList<Point> points;
	private ArrayList<ArrayList<Point>> stroke;
	private Paint paint;
	private Path m_Path;
	private float mX, mY;
	private int backgroundResource;
	private Canvas m_Canvas;
	private static final float TOUCH_TOLERANCE = 4;
	

	ArrayList<Pair<Path, Paint>> undonePaths = new ArrayList<Pair<Path, Paint>>();

	public CustomDrawingView(Context context, int imageResource) {
		super(context);
		this.backgroundResource = imageResource;
		points = new ArrayList<Point>();
		stroke = new ArrayList<ArrayList<Point>>();
		onCanvasInitialization();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		this.setBackgroundResource(backgroundResource);
		for (Object obj : stroke) {
			drawStrokeValue((ArrayList<Point>) obj, canvas);
			paint.setDither(true);
		}
		m_Path = new Path();
		Paint newPaint = new Paint(paint);
		drawStrokeValue(points, canvas);
		paint.setDither(true);
	}

	private void drawStrokeValue(ArrayList<Point> stroke, Canvas canvas) 
	{
		if (stroke.size() > 0)
		{
			Point p0 = stroke.get(0);
			for (int i = 0; i < stroke.size(); i++) {
				Point p1 = stroke.get(i);
				canvas.drawLine(p0.x, p0.y, p1.x, p1.y, paint);
				p0 = p1;
				paint.setDither(true);
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{

		if (event.getActionMasked() == MotionEvent.ACTION_MOVE) 
		{
			points.add(new Point((int) event.getX(), (int) event.getY()));
			invalidate();
		}

		if (event.getActionMasked() == MotionEvent.ACTION_UP) 
		{
			this.stroke.add(points);
			points = new ArrayList<Point>();
			invalidate();
		}
		return true;
	}

	public void onCanvasInitialization() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setDither(true);
		paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(9f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setXfermode(new PorterDuffXfermode(Mode.CLEAR));
		m_Canvas = new Canvas();
		m_Path = new Path();
		Paint newPaint = new Paint(paint);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	public boolean onTouch(View arg0, MotionEvent event) {
		float x = event.getX();
		float y = event.getY();

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			touch_move(x, y);
			invalidate();
			break;
		case MotionEvent.ACTION_UP:
			touch_up();
			invalidate();
			break;
		}
		return true;
	}

	

	private void touch_move(float x, float y) {
		float dx = Math.abs(x - mX);
		float dy = Math.abs(y - mY);
		if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
			m_Path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
			mX = x;
			mY = y;
		}
	}

	private void touch_up() {
		m_Path.lineTo(mX, mY);
		// commit the path to our offscreen
		m_Canvas.drawPath(m_Path, paint);
		// kill this so we don't double draw
		m_Path = new Path();
		Paint newPaint = new Paint(paint); // Clones the mPaint object
		//paths.add(new Pair<Path, Paint>(m_Path, newPaint));
	}
	}


