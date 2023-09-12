package earth.health.ui.utils

import android.content.Context
import android.widget.Toast

/**
 * Show toast on screen with a message or idMessage
 * @throws IllegalArgumentException if idMessage and message are both null
 */
fun showToast(context: Context, idMessage: Int?,  message: String?) {
    val toast = Toast(context)
    if (idMessage != null) {
        toast.setText(idMessage)
    } else {
        if(message == null) {
            throw IllegalArgumentException("Show toast function must have idMessage or message")
        }
        toast.setText(message)
    }
    toast.show()
}