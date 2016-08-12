package kr.pe.burt.android.lib.hashgirl;

import android.graphics.Color;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * HashGirl.with(text).grap(pattern).click(clicklistener).into(textview)
 * Created by burt on 2016. 8. 11..
 */
public class HashGirl {

    private TextView textView = null;
    private OnURLClickListener onURLClickListener = null;
    private String text = null;
    private String pattern = null;
    private String postfixToRemove = "";
    private String prefixToRemove = "";
    private boolean underline = false;
    private boolean strike = false;
    private int color = Color.RED;
    private int bgcolor = Color.TRANSPARENT;
    private int alpha = 255;

    public static HashGirl with(String text) {
        HashGirl hashGirl = new HashGirl();
        hashGirl.text = text;
        return hashGirl;
    }

    public HashGirl grab(String pattern) {
        return grab(pattern, "");
    }

    public HashGirl grab(String pattern, String postfixToRemove) {
        return grab(pattern, postfixToRemove, prefixToRemove);
    }

    public HashGirl grab(String pattern, String postfixToRemove, String prefixToRemove) {
        this.pattern = pattern;
        this.postfixToRemove = postfixToRemove;
        this.prefixToRemove = prefixToRemove;
        return this;
    }

    public HashGirl underline() {
        return underline(true);
    }

    public HashGirl underline(boolean underline) {
        this.underline = underline;
        return this;
    }

    public HashGirl strike() {
        return strike(true);
    }

    public HashGirl strike(boolean strike) {
        this.strike = strike;
        return this;
    }

    public HashGirl color(int color) {
        this.color = color;
        return this;
    }

    public HashGirl bgcolor(int color) {
        this.bgcolor = color;
        return this;
    }

    public HashGirl alpha(int alpha) {
        this.alpha = alpha;
        return this;
    }

    public HashGirl click(OnURLClickListener onURLClickListener) {
        this.onURLClickListener = onURLClickListener;
        return this;
    }

    public void into(TextView textView) {
        build(textView);
    }


    private void build(TextView textView) {
        if(text == null || pattern == null)
            return;

        String processedText = processText(pattern, text);
        textView.setText(wrapIntoHashGirlURLSpan(processedText, onURLClickListener));
        textView.setMovementMethod(new HashGirlLinkMovementMethod());

    }

    private String processText(String pattern, String text) {
        Pattern pt = Pattern.compile(pattern);
        Matcher m = pt.matcher(text);
        String processedData = text;
        while (m.find()) {
            String tag = m.group();
            String newTag = tag;

            if(postfixToRemove.length() > 0) {
                newTag = tag.replace(postfixToRemove, "");
            }

            if(prefixToRemove.length() > 0) {
                newTag = newTag.replace(prefixToRemove, "");
            }

            processedData = processedData.replace(tag, "<a href=\"" + newTag + "\">" + newTag + "</a>");
        }
        return processedData;
    }

    private SpannableString wrapIntoHashGirlURLSpan(String htmlText, final OnURLClickListener onURLClickListener) {
        SpannableString s = SpannableString.valueOf(Html.fromHtml(htmlText));
        URLSpan[] urlSpanList = s.getSpans(0, s.length(), URLSpan.class);
        for(URLSpan urlSpan : urlSpanList) {
            int start = s.getSpanStart(urlSpan);
            int end = s.getSpanEnd(urlSpan);
            s.removeSpan(urlSpan);
            HashGirlURLSpan hashGirlURLSpan = new HashGirlURLSpan(urlSpan.getURL(), new OnURLClickListener() {
                @Override
                public void onClickURL(String url) {
                    if(onURLClickListener != null) {
                        onURLClickListener.onClickURL(url);
                    }
                }
            });
            hashGirlURLSpan.setUnderline(underline);
            hashGirlURLSpan.setStrike(strike);
            hashGirlURLSpan.setColor(color);
            hashGirlURLSpan.setBgcolor(bgcolor);
            hashGirlURLSpan.setAlpha(alpha);
            s.setSpan(hashGirlURLSpan, start, end, 0);
        }
        return s;
    }

}
