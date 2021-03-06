/*
 * Copyright (C) 2016 Álinson Santos Xavier <isoron@gmail.com>
 *
 * This file is part of Loop Habit Tracker.
 *
 * Loop Habit Tracker is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 *
 * Loop Habit Tracker is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.isoron.androidbase.utils

import android.content.*
import android.graphics.*
import android.util.*
import android.view.*
import android.widget.*
import android.widget.TextView.*
import androidx.core.view.*

object InterfaceUtils {
    private var fontAwesome: Typeface? = null
    private var fixedResolution: Float? = null

    @JvmStatic
    fun setFixedResolution(f: Float) {
        fixedResolution = f
    }

    @JvmStatic
    fun getFontAwesome(context: Context): Typeface? {
        if (fontAwesome == null) {
            fontAwesome = Typeface.createFromAsset(context.assets, "fontawesome-webfont.ttf")
        }
        return fontAwesome
    }

    @JvmStatic
    fun dpToPixels(context: Context, dp: Float): Float {
        if (fixedResolution != null) return dp * fixedResolution!!
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                                         dp,
                                         context.resources.displayMetrics)
    }

    @JvmStatic
    fun spToPixels(context: Context, sp: Float): Float {
        if (fixedResolution != null) return sp * fixedResolution!!
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                                         sp,
                                         context.resources.displayMetrics)
    }

    @JvmStatic
    fun getDimension(context: Context, id: Int): Float {
        val dim = context.resources.getDimension(id)
        if (fixedResolution != null) {
            val actualDensity = context.resources.displayMetrics.density
            return dim / actualDensity * fixedResolution!!
        }
        return dim
    }

    fun setupEditorAction(parent: ViewGroup,
                          listener: OnEditorActionListener) {
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            if (child is ViewGroup) setupEditorAction(child, listener)
            if (child is TextView) child.setOnEditorActionListener(listener)
        }
    }

    fun isLayoutRtl(view: View?): Boolean {
        return ViewCompat.getLayoutDirection(view!!) ==
                ViewCompat.LAYOUT_DIRECTION_RTL
    }
}