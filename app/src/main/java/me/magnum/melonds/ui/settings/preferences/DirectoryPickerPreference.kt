package me.magnum.melonds.ui.settings.preferences

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import androidx.preference.Preference
import me.magnum.melonds.R

open class DirectoryPickerPreference(context: Context?, attrs: AttributeSet?) : Preference(context, attrs) {
    init {
        initAttributes(attrs)
    }

    var multiSelection = false
        private set

    open fun onDirectoryPicked(uri: Uri?) {
        // TODO: properly add support for multi directory selection
        val dirs = if (uri == null) return else setOf(uri.toString())

        if (isPersistent) {
            persistStringSet(dirs)
        }

        onPreferenceChangeListener?.onPreferenceChange(this, dirs)
    }

    protected open fun initAttributes(attrs: AttributeSet?) {
        if (attrs == null)
            return

        val attrArray = context.theme.obtainStyledAttributes(attrs, R.styleable.DirectoryPickerPreference, 0, 0)
        val count = attrArray.indexCount
        for (i in 0..count) {
            val attr = attrArray.getIndex(i)
            when (attr) {
                R.styleable.DirectoryPickerPreference_selection -> multiSelection = attrArray.getInt(R.styleable.DirectoryPickerPreference_selection, 0) == 1
            }
        }
        attrArray.recycle()
    }
}