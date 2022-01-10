package com.example.visapaymentapp.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.example.visapaymentapp.MainActivity
import com.example.visapaymentapp.extensions.openPage
import com.example.visapaymentapp.ui.view.PaymentAction
import java.lang.Exception
import java.lang.Math.abs

open class OnSwipeTouchListener(context: Context?) : View.OnTouchListener {

    private val gestureDetector: GestureDetector


    override fun onTouch(view: View?, motionEvent: MotionEvent?): Boolean {

        var isTouch: Boolean = if (motionEvent != null) {

            gestureDetector.onTouchEvent(motionEvent)
        } else {
            true
        }

        return isTouch
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onFling(
            e1: MotionEvent,
            e2: MotionEvent,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            var result = false
            try {
                val diffY = e1.y.let { e2.y.minus(it) }
                val diffX = e1.x.let { e2.x.minus(it) }

                if (kotlin.math.abs(diffX) > kotlin.math.abs(diffY)) {
                    if (kotlin.math.abs(diffX) > SWIPE_THRESHOLD && kotlin.math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight()
                        } else {
                            onSwipeLeft()
                        }
                        result = true
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return result
        }

    }

    init {
        gestureDetector = GestureDetector(context, GestureListener())
    }

    companion object {
        private const val SWIPE_THRESHOLD = 300
        private const val SWIPE_VELOCITY_THRESHOLD = 300
    }

    open fun onSwipeRight() {
        println("GOHAR::: onSwipeRight")
    }

    open fun onSwipeLeft() {
        println("GOHAR::: onSwipeLeft")
    }

    open fun onSwipeTop() {
        println("GOHAR::: onSwipeTop")
    }

    open fun onSwipeBottom() {
        println("GOHAR::: onSwipeBottom")
    }
}