package com.example.lesson_05_yermakov

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val simpleDateFormat = SimpleDateFormat("dd.MM", Locale.getDefault())
    private val random = java.util.Random()
    private var columnArrayLength = 0
    private var dataList = mutableListOf<Content>()
    private var currentData = mutableListOf<Float>()
    private val aspect = 150 / 360f
    private val columnCoef = 0.85f
    private val textOffset = context.resources.getDimension(R.dimen.text_offset)
    private val textSpaceCoef = 0.1f
    private val roundRadio = context.resources.getDimension(R.dimen.round_ratio)
    private val columnWidth = context.resources.getDimension(R.dimen.column_width)
    private val halfColumnWidth = columnWidth / 2f
    private var space = 0.0f
    private var columnY = 0f
    private val columnAnimator: ValueAnimator = ValueAnimator.ofFloat(
        0f,
        100f
    ).apply {
        duration = 1000
        addUpdateListener {
            for (i in 0 until columnArrayLength) {
                currentData.add(
                    i,
                    dataList[i].value * ((animatedValue as Float) / maxValue)
                )
                invalidate()
            }
        }
    }

    private var maxValue = 100
    private var lineColor = ContextCompat.getColor(context, R.color.purple)
    private var textColor = ContextCompat.getColor(context, R.color.white)
    private val columnPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = lineColor
        style = Paint.Style.FILL
    }
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = textColor
        textSize = height.toFloat()
        textAlign = Paint.Align.CENTER
    }

    fun setData() {
        dataList.clear()
        columnArrayLength = random.nextInt(9) + 1
        val currentDate = Calendar.getInstance()
        for (i in 0 until columnArrayLength) {
            val pastDate = Calendar.getInstance()
            pastDate.time = currentDate.time
            pastDate.add(Calendar.DAY_OF_MONTH, -i)
            dataList.add(
                Content(
                    simpleDateFormat.format(pastDate.time),
                    random.nextInt(maxValue) + 1
                )
            )
            currentData.add(i, 10f)
        }
    }

    fun startMyAnimation() {
        columnAnimator.start()
    }

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0)
        lineColor = typedArray.getColor(R.styleable.CustomView_lineColor, lineColor)
        textColor = typedArray.getColor(R.styleable.CustomView_textColor, textColor)
        maxValue = typedArray.getInt(R.styleable.CustomView_maxValue, maxValue)
        setData()
        typedArray.recycle()
    }

    private val myListener =  object : GestureDetector.SimpleOnGestureListener() {
        override fun onDown(e: MotionEvent): Boolean {
            return true
        }
    }

    private val detector: GestureDetector = GestureDetector(context, myListener)


    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when {
            detector.onTouchEvent(event) -> true
            event.action == MotionEvent.ACTION_UP -> {
                startMyAnimation()
                true
            }
            else -> false
        }
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        var desiredWidth = 0
        var desiredHeight = 0
        when (widthMode) {
            MeasureSpec.EXACTLY -> {
                desiredWidth = widthSize
                desiredHeight = (desiredWidth * aspect).toInt()
            }

            MeasureSpec.AT_MOST -> {
                desiredWidth = resources.getDimensionPixelSize(R.dimen.custom_view_width)
                desiredHeight = (desiredWidth * aspect).toInt()
            }

            MeasureSpec.UNSPECIFIED -> {
                desiredWidth = 0
                desiredHeight = 0
            }
        }
        setMeasuredDimension(desiredWidth, desiredHeight)
        space = (desiredWidth - columnWidth * columnArrayLength) / (columnArrayLength + 1).toFloat()
        textPaint.textSize = desiredHeight * textSpaceCoef
        columnY = desiredHeight * columnCoef
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var left = 0f
        var right: Float
        for (i in 0 until columnArrayLength) {
            left += space
            right = left + columnWidth
            val columnLength =
                (currentData[columnArrayLength - i - 1] / 100f) * (height * (columnCoef - textSpaceCoef * 2))
            canvas.drawRoundRect(
                left,
                columnY,
                right,
                columnY - columnLength,
                roundRadio,
                roundRadio,
                columnPaint
            )
            canvas.drawText(
                dataList[columnArrayLength - i - 1].date,
                left + halfColumnWidth,
                (columnY) + textPaint.fontMetricsInt.run { descent - ascent },
                textPaint
            )
            canvas.drawText(
                dataList[columnArrayLength - i - 1].value.toString(),
                left + halfColumnWidth,
                columnY - columnLength - textOffset,
                textPaint
            )
            left = right
        }
    }

    private class SavedState : BaseSavedState {
        lateinit var listOfSavedData: MutableList<Content>
        lateinit var listOfSavedCurrentData: MutableList<Float>
        var numOfSavedColumns: Int = 0

        constructor(superState: Parcelable) : super(superState)

        constructor(parcel: Parcel) : super(parcel)

        companion object CREATOR : Parcelable.Creator<SavedState> {
            override fun createFromParcel(parcel: Parcel): SavedState {
                return SavedState(parcel)
            }

            override fun newArray(size: Int): Array<SavedState?> {
                return arrayOfNulls(size)
            }
        }
    }

    override fun onSaveInstanceState(): Parcelable? {
        return super.onSaveInstanceState()?.let {
            SavedState(it).apply {
                listOfSavedData = dataList
                listOfSavedCurrentData = currentData
                numOfSavedColumns = columnArrayLength
            }
        }
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is SavedState) {
            dataList = state.listOfSavedData
            currentData = state.listOfSavedCurrentData
            columnArrayLength = state.numOfSavedColumns
            super.onRestoreInstanceState(state.superState)
        } else {
            super.onRestoreInstanceState(state)
        }
    }

    private data class Content(val date: String, val value: Int)
}