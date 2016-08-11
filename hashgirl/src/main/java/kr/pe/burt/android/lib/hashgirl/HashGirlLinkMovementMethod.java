package kr.pe.burt.android.lib.hashgirl;

import android.text.Selection;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.widget.TextView;

/**
 * Created by burt on 2016. 8. 11..
 */
public class HashGirlLinkMovementMethod extends LinkMovementMethod {
    @Override
    public boolean onTouchEvent(TextView widget, Spannable buffer, MotionEvent event) {
        Selection.removeSelection(buffer);
        return super.onTouchEvent(widget, buffer, event);
    }
}
