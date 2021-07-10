package org.mascota.ui.view.content.detail.adapter

import android.graphics.Canvas
import android.icu.lang.UCharacter.IndicPositionalCategory.LEFT
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import java.lang.Float.max
import java.lang.Float.min

class SwipeHelperCallback : ItemTouchHelper.Callback() {

    private var curPosition: Int? = null
    private var prePosition: Int? = null
    private var curDx = 0f
    private var clamp = 0f

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        // Drag와 Swipe 방향을 결정 Drag는 사용하지 않아 0, Swipe의 경우는 LEFT 사용가능하도록 설정
        return makeMovementFlags(0, LEFT)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ) = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {}

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        curDx = 0f
        prePosition = viewHolder.bindingAdapterPosition
        getDefaultUIUtil().clearView(getView(viewHolder))
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        viewHolder?.let {
            curPosition = viewHolder.bindingAdapterPosition
            getDefaultUIUtil().onSelected(getView(it))
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            val view = getView(viewHolder)
            val isClamped = getTag(viewHolder)
            val x = clampViewPositionHorizontal(view, dX, isClamped, isCurrentlyActive)

            curDx = x

            getDefaultUIUtil().onDraw(
                c,
                recyclerView,
                view,
                x,
                dY,
                actionState,
                isCurrentlyActive
            )
        }
    }

    private fun getView(viewHolder: RecyclerView.ViewHolder): View {
        return (viewHolder as ContentDetailDiaryAdapter.ContentDetailDiaryViewHolder).binding.clItemDiaryTop
    }

    override fun getSwipeEscapeVelocity(defaultValue: Float): Float {
        return defaultValue * 10
    }

    override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
        val isClamped = getTag(viewHolder)
        // 현재 View가 고정되어있지 않고 사용자가 -clamp 이상 swipe 시 isClamped true로 변경 아닐시 false로 변경
        setTag(viewHolder, !isClamped && curDx <= -clamp)
        return 2f
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun clampViewPositionHorizontal(
        view: View,
        dX: Float,
        isClamped: Boolean,
        isCurrentlyActive: Boolean
    ): Float {
        // View의 가로 길이의 절반까지만 swipe 되도록
        val minSwipe: Float = -view.width.toFloat() / 2
        // RIGHT 방향으로 swipe 막기
        val maxSwipe: Float = 0f

        val x = if (isClamped) {
            // View가 고정되었을 때 swipe되는 영역 제한
            if (isCurrentlyActive) dX - clamp else -clamp
        } else {
            dX
        }

        return min(max(minSwipe, x), maxSwipe)
    }

    private fun setTag(viewHolder: RecyclerView.ViewHolder, isClamped: Boolean) {
        // isClamped를 view의 tag로 관리
        viewHolder.itemView.tag = isClamped
    }

    private fun getTag(viewHolder: RecyclerView.ViewHolder): Boolean {
        // isClamped를 view의 tag로 관리
        return viewHolder.itemView.tag as? Boolean ?: false
    }

    fun setClamp(clamp: Float) {
        this.clamp = clamp
    }

    // 다른 View가 swipe 되거나 터치되면 고정 해제
    fun removePreviousClamp(recyclerView: RecyclerView) {
        if (curPosition == prePosition)
            return
        prePosition?.let {
            val viewHolder = recyclerView.findViewHolderForAdapterPosition(it) ?: return
            getView(viewHolder).translationX = 0f
            setTag(viewHolder, false)
            prePosition = null
        }
    }
}