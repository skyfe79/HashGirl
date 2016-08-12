package kr.pe.burt.android.lib.hashgirl;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.text.TextPaint;
import android.text.style.URLSpan;
import android.view.View;

/**
 * Created by burt on 2016. 8. 11..
 */
public class HashGirlURLSpan extends URLSpan {

    private OnClickHashListener onClickHashListener = null;
    private boolean underline = false;
    private boolean strike = false;
    private int color = Color.RED;
    private int bgcolor = Color.TRANSPARENT;
    private int alpha = 255;

    public HashGirlURLSpan(String url, OnClickHashListener onClickHashListener) {
        super(url);
        this.onClickHashListener = onClickHashListener;
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setUnderlineText(underline);
        ds.setStrikeThruText(strike);
        ds.linkColor = color;
        ds.setColor(color);
        ds.bgColor = bgcolor;
        ds.setAlpha(alpha);
    }

    @Override
    public void onClick(View widget) {
        onClickHashListener.onClickHash(getURL());
    }

    public void setUnderline(boolean underline) {
        this.underline = underline;
    }

    public void setStrike(boolean strike) {
        this.strike = strike;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setBgcolor(int bgcolor) {
        this.bgcolor = bgcolor;
    }

    public void setAlpha(int alpha) {
        this.alpha = alpha;
    }
}
