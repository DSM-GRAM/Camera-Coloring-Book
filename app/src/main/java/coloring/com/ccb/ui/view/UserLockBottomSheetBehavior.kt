package coloring.com.ccb.ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.view.MotionEvent
import android.view.View
import com.google.android.material.bottomsheet.BottomSheetBehavior


class UserLockBottomSheetBehavior<V : View>(context: Context?, attrs: AttributeSet?) : BottomSheetBehavior<V>(context, attrs) {

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: V, event: MotionEvent): Boolean {
        return false
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: V, event: MotionEvent): Boolean {
        return false
    }

    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout, child: V, directTargetChild: View, target: View, axes: Int, type: Int): Boolean {
        return false
    }

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: V, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return false
    }
}