package com.example.lesson_05_yermakov

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
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
    private val maxValue = 100
    val columnAnimator = ValueAnimator.ofFloat(
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
        columnArrayLength = 9
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
        setData()
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CustomView, defStyleAttr, 0)
        lineColor = typedArray.getColor(R.styleable.CustomView_lineColor, lineColor)
        textColor = typedArray.getColor(R.styleable.CustomView_textColor, textColor)
        typedArray.recycle()
        startMyAnimation()
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
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val columnWidth = 40f
        val space = (width - columnWidth * columnArrayLength) / (columnArrayLength + 1).toFloat()
        textPaint.textSize = height * 0.1f
        val columnY = height * columnCoef
        var left = 0f
        var right: Float
        for (i in 0 until columnArrayLength) {
            left += space
            right = left + columnWidth
            val columnLength =
                (currentData[columnArrayLength - i - 1] / 100f) * (height * (columnCoef - 0.2f))
            canvas.drawRoundRect(
                left,
                columnY,
                right,
                columnY - columnLength,
                50f,
                50f,
                columnPaint
            )
            canvas.drawText(
                dataList[columnArrayLength - i - 1].date,
                left + columnWidth / 2f,
                (columnY) + textPaint.fontMetricsInt.run { descent - ascent },
                textPaint
            )
            canvas.drawText(
                dataList[columnArrayLength - i - 1].value.toString(),
                left + columnWidth / 2f,
                columnY - columnLength - 10f,
                textPaint
            )
            left = right
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }

    private data class Content(val date: String, val value: Int)
}