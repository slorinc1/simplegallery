/*
 Copyright (c) 2012 Roman Truba

 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in all copies or substantial
 portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED
 TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH
 THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package it.feio.android.simplegallery.views;

import it.feio.android.simplegallery.models.listeners.OnViewTouchedListener;
import it.feio.android.simplegallery.models.ui.CoverFlowPageTransformer;
import it.feio.android.simplegallery.models.ui.DepthPageTransformer;
import it.feio.android.simplegallery.models.ui.ZoomOutPageTransformer;
import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 This class implements method to help <b>TouchImageView</b> fling, draggin and scaling.
 */
public class GalleryViewPager extends ViewPager {

	public static final int PAGE_TRANSFORMER_DEPTH = 0;
	public static final int PAGE_TRANSFORMER_ZOOM_OUT = 1;	
	public static final int PAGE_TRANSFORMER_COVER_FLOW = 2;	
	
    PointF last;
    public TouchImageView mCurrentView;
	private OnViewTouchedListener mOnViewTouchedListener;
    public GalleryViewPager(Context context) {
        super(context);
    }
    public GalleryViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
//    private float[] handleMotionEvent(MotionEvent event)
//    {
//        switch (event.getAction() & MotionEvent.ACTION_MASK) {
//            case MotionEvent.ACTION_DOWN:
//                last = new PointF(event.getX(0), event.getY(0));
//                break;
//            case MotionEvent.ACTION_MOVE:
//            case MotionEvent.ACTION_UP:
//                PointF curr = new PointF(event.getX(0), event.getY(0));
//                return new float[]{curr.x - last.x, curr.y - last.y};
//
//        }
//        return null;
//    }
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)
//        {
//            super.onTouchEvent(event);
//        }
//
//        float [] difference = handleMotionEvent(event);
//
//        if (mCurrentView.pagerCanScroll()) {
//            return super.onTouchEvent(event);
//        }
//        else {
//            if (difference != null && mCurrentView.onRightSide && difference[0] < 0) //move right
//            {
//                return super.onTouchEvent(event);
//            }
//            if (difference != null && mCurrentView.onLeftSide && difference[0] > 0) //move left
//            {
//                return super.onTouchEvent(event);
//            }
//            if (difference == null && ( mCurrentView.onLeftSide || mCurrentView.onRightSide))
//            {
//                return super.onTouchEvent(event);
//            }
//        }
//
//        return false;
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent event) {
//        if ((event.getAction() & MotionEvent.ACTION_MASK) == MotionEvent.ACTION_UP)
//        {
//            super.onInterceptTouchEvent(event);
//        }
//
//        float [] difference = handleMotionEvent(event);
//
//        if (mCurrentView.pagerCanScroll()) {
//            return super.onInterceptTouchEvent(event);
//        }
//        else {
//            if (difference != null && mCurrentView.onRightSide && difference[0] < 0) //move right
//            {
//                return super.onInterceptTouchEvent(event);
//            }
//            if (difference != null && mCurrentView.onLeftSide && difference[0] > 0) //move left
//            {
//                return super.onInterceptTouchEvent(event);
//            }
//            if (difference == null && ( mCurrentView.onLeftSide || mCurrentView.onRightSide))
//            {
//                return super.onInterceptTouchEvent(event);
//            }
//        }
//        return false;
//    }
    
    
    public void setPageTransformer(int presetPageTransformer) {
    	switch (presetPageTransformer) {
			case PAGE_TRANSFORMER_DEPTH:
				super.setPageTransformer(true, new DepthPageTransformer());
				break;
			case PAGE_TRANSFORMER_ZOOM_OUT:
				super.setPageTransformer(false, new ZoomOutPageTransformer());
				break;	
			case PAGE_TRANSFORMER_COVER_FLOW:
				super.setPageTransformer(false, new CoverFlowPageTransformer());
				break;	
			default:
				return;
		}
    }


	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if (mOnViewTouchedListener != null) {
			mOnViewTouchedListener.onViewTouchOccurred(ev);
		}
		return super.onInterceptTouchEvent(ev);
	}

	public void setOnViewTouchedListener(OnViewTouchedListener mOnViewTouchedListener) {
		this.mOnViewTouchedListener = mOnViewTouchedListener;
	}
}
