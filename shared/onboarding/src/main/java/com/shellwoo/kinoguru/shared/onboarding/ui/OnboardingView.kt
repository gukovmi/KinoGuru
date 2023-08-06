package com.shellwoo.kinoguru.shared.onboarding.ui

import android.content.Context
import android.graphics.*
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import com.shellwoo.kinoguru.core.ui.ext.getLeftOnWindow
import com.shellwoo.kinoguru.core.ui.ext.getThemeColor
import com.shellwoo.kinoguru.core.ui.ext.getTopOnWindow
import com.shellwoo.kinoguru.shared.onboarding.R
import com.shellwoo.kinoguru.design.resource.R as designResourceR

internal class OnboardingView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(
    context,
    attributes,
    defStyleAttr,
) {

    private companion object {

        const val SUPER_STATE_KEY = "SUPER_STATE_KEY"

        const val DESCRIPTION = "DESCRIPTION"

        const val STATUS_BAR_HEIGHT = "STATUS_BAR_HEIGHT"

        const val HOLE_LEFT_KEY = "HOLE_LEFT_KEY"
        const val HOLE_RIGHT_KEY = "HOLE_RIGHT_KEY"
        const val HOLE_TOP_KEY = "HOLE_TOP_KEY"
        const val HOLE_BOTTOM_KEY = "HOLE_BOTTOM_KEY"

        const val LINE_LEFT_KEY = "LINE_LEFT_KEY"
        const val LINE_RIGHT_KEY = "LINE_RIGHT_KEY"
        const val LINE_TOP_KEY = "LINE_TOP_KEY"
        const val LINE_BOTTOM_KEY = "LINE_BOTTOM_KEY"

        const val LINE_WIDTH = 4f
        const val LINE_DASH_INTERVAL = 20f
        const val LINE_HEIGHT = 200f
    }

    private var targetView: View? = null

    private val holePaint = Paint().apply {
        flags = Paint.ANTI_ALIAS_FLAG
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private val linePaint = Paint().apply {
        style = Paint.Style.STROKE
        color = getThemeColor(context, designResourceR.attr.colorOnPrimary)
        strokeWidth = LINE_WIDTH
        pathEffect = DashPathEffect(floatArrayOf(LINE_DASH_INTERVAL, LINE_DASH_INTERVAL), 0f)
    }

    private var statusBarHeight = 0f

    private val bgColor = getThemeColor(context, designResourceR.attr.colorOverlayDark)

    private var holeLeft = 0f
    private var holeRight = 0f
    private var holeTop = 0f
    private var holeBottom = 0f

    private var lineLeft = 0f
    private var lineRight = 0f
    private var lineTop = 0f
    private var lineBottom = 0f

    private lateinit var contentLayout: LinearLayout
    private lateinit var descriptionTextView: TextView
    private lateinit var okButton: Button

    init {
        setWillNotDraw(false)
        setLayerType(LAYER_TYPE_HARDWARE, null)

        createContent()
    }

    private fun createContent() {
        LayoutInflater.from(context).inflate(R.layout.onboarding_view_content, this, true)

        contentLayout = findViewById(R.id.content)
        descriptionTextView = findViewById(R.id.description)
        okButton = findViewById(R.id.ok)
    }

    fun setup(targetView: View, description: String, okAction: () -> Unit) {
        this.targetView = targetView
        descriptionTextView.text = description
        okButton.setOnClickListener { okAction() }

        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val targetView = targetView ?: return

        measureStatusBar()
        measureHole(targetView)
        measureLine()
    }

    private fun measureStatusBar() {
        val statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (statusBarHeightId != 0) {
            statusBarHeight = resources.getDimension(statusBarHeightId)
        }
    }

    private fun measureHole(view: View) {
        holeLeft = view.getLeftOnWindow().toFloat() - view.marginStart - view.paddingStart
        val horizontalExtraSpace = view.marginStart + view.paddingStart + view.marginEnd + view.paddingEnd
        holeRight = holeLeft + view.measuredWidth + horizontalExtraSpace
        holeTop = view.getTopOnWindow().toFloat() - statusBarHeight - view.marginTop
        val verticalExtraSpace = view.marginBottom + view.marginTop
        holeBottom = holeTop + view.measuredHeight + verticalExtraSpace
    }

    private fun measureLine() {
        lineLeft = (holeLeft + holeRight) / 2
        lineRight = (holeLeft + holeRight) / 2
        lineTop = holeBottom
        lineBottom = holeBottom + LINE_HEIGHT
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        contentLayout.layout(left, lineBottom.toInt(), right, bottom)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(bgColor)
        canvas.drawRect(holeLeft, holeTop, holeRight, holeBottom, holePaint)
        canvas.drawLine(lineLeft, lineTop, lineRight, lineBottom, linePaint)

        super.onDraw(canvas)
    }

    override fun onSaveInstanceState(): Parcelable =
        Bundle().apply {
            putParcelable(SUPER_STATE_KEY, super.onSaveInstanceState())
            putString(DESCRIPTION, descriptionTextView.text.toString())
            putFloat(STATUS_BAR_HEIGHT, statusBarHeight)
            putFloat(HOLE_LEFT_KEY, holeLeft)
            putFloat(HOLE_RIGHT_KEY, holeRight)
            putFloat(HOLE_TOP_KEY, holeTop)
            putFloat(HOLE_BOTTOM_KEY, holeBottom)
            putFloat(LINE_LEFT_KEY, lineLeft)
            putFloat(LINE_RIGHT_KEY, lineRight)
            putFloat(LINE_TOP_KEY, lineTop)
            putFloat(LINE_BOTTOM_KEY, lineBottom)
        }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            descriptionTextView.text = state.getString(DESCRIPTION)
            statusBarHeight = state.getFloat(STATUS_BAR_HEIGHT)
            holeLeft = state.getFloat(HOLE_LEFT_KEY)
            holeRight = state.getFloat(HOLE_RIGHT_KEY)
            holeTop = state.getFloat(HOLE_TOP_KEY)
            holeBottom = state.getFloat(HOLE_BOTTOM_KEY)
            lineLeft = state.getFloat(LINE_LEFT_KEY)
            lineRight = state.getFloat(LINE_RIGHT_KEY)
            lineTop = state.getFloat(LINE_TOP_KEY)
            lineBottom = state.getFloat(LINE_BOTTOM_KEY)
            super.onRestoreInstanceState(state.getParcelable(SUPER_STATE_KEY))
        } else {
            super.onRestoreInstanceState(state)
        }
    }
}