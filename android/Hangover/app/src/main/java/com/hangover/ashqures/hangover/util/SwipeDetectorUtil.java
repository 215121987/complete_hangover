package com.hangover.ashqures.hangover.util;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.logging.Logger;

/**
 * Created by ashqures on 8/28/16.
 */
public class SwipeDetectorUtil{

    public static enum Action {
        LR, // Left to Right
        RL, // Right to Left
        TB, // Top to bottom
        BT, // Bottom to Top
        None // when no action was detected
    }

    private static final String logTag = "SwipeDetector";
    private static final int MIN_DISTANCE = 200;
    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private float downX, downY, upX, upY;
    private Action mSwipeDetected = Action.None;

    public SwipeDetectorUtil(MotionEvent event){
        onTouch(event);
    }

    public boolean swipeDetected() {
        return mSwipeDetected != Action.None;
    }

    public Action getAction() {
        return mSwipeDetected;
    }

    private boolean onTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                mSwipeDetected = Action.None;
                return false; // allow other events like Click to be processed
            }
            case MotionEvent.ACTION_MOVE: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;


                // horizontal swipe detection
                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    // left or right
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        // left or right
                        if (deltaX < 0) {
                            mSwipeDetected = Action.LR;
                            return true;
                        }
                        if (deltaX > 0) {
                            mSwipeDetected = Action.RL;
                            return true;
                        }
                    } else {
                        //not long enough swipe...
                        return false;
                    }
                } else
                    // vertical swipe detection
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        // top or down
                        if (deltaY < 0) {
                            //Logger.show(Log.INFO,logTag, "Swipe Top to Bottom");
                            mSwipeDetected = Action.TB;
                            return false;
                        }
                        if (deltaY > 0) {
                            //Logger.show(Log.INFO,logTag, "Swipe Bottom to Top");
                            mSwipeDetected = Action.BT;
                            return false;
                        }
                    }
                return true;
            }
        }
        return false;
    }
}