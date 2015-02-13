package com.redbooth.awesomwtextview;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AwesomeTextView extends TextView {

    private final static int DEFAULT_RENDER_APPLY_MODE = Spannable.SPAN_EXCLUSIVE_EXCLUSIVE;
    private final static int UPPER_LEFT_X = 0;
    private final static int UPPER_LEFT_Y = 0;

    public interface ViewSpanRenderer {
        public abstract View getView(final String text, final Context context);
    }

    public interface ViewSpanClickListener {
        public void onClick(String text, Context context);
    }

    private Map<String, ViewSpanRenderer> renderers;

    public AwesomeTextView(Context context) {
        super(context);
        renderers = new HashMap<>();
    }

    public AwesomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        renderers = new HashMap<>();
    }

    public AwesomeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        renderers = new HashMap<>();
    }

    public AwesomeTextView addViewSpanRenderer(String pattern, ViewSpanRenderer viewSpanRenderer) {
        renderers.put(pattern, viewSpanRenderer);
        return this;
    }

    public Map<String, ViewSpanRenderer> getViewSpanRenderers() {
        return renderers;
    }

    public void applyRenderers() {
        if (renderers != null) {
            Spannable spannableString = new SpannableString(getText());
            Set<String> spanPatterns = renderers.keySet();
            for (String spanPattern : spanPatterns) {
                Pattern pattern = Pattern.compile(spanPattern);
                Matcher matcher = pattern.matcher(spannableString);
                while (matcher.find()) {
                    int end = matcher.end();
                    int start = matcher.start();
                    ViewSpanRenderer renderer = renderers.get(spanPattern);
                    String text = matcher.group(0);
                    View view = renderer.getView(text, getContext());
                    BitmapDrawable bitmpaDrawable = (BitmapDrawable) ViewUtils.convertViewToDrawable(view);
                    bitmpaDrawable.setBounds(UPPER_LEFT_X, UPPER_LEFT_Y, bitmpaDrawable.getIntrinsicWidth(), bitmpaDrawable.getIntrinsicHeight());
                    spannableString.setSpan(new ImageSpan(bitmpaDrawable), start, end, DEFAULT_RENDER_APPLY_MODE);
                    if (renderer instanceof ViewSpanClickListener) {
                        enableClickEvents();
                        ClickableSpan clickableSpan = getClickableSpan(text, (ViewSpanClickListener) renderer);
                        spannableString.setSpan(clickableSpan, start, end, DEFAULT_RENDER_APPLY_MODE);
                    }
                }
            }
            setText(spannableString);
        }
    }

    private void enableClickEvents() {
        setMovementMethod(LinkMovementMethod.getInstance());
    }

    private ClickableSpan getClickableSpan(final String text, final ViewSpanClickListener listener) {
        ClickableSpan clickableSpan = new ClickableSpanWithoutFormat() {
            @Override
            public void onClick(View view) {
                listener.onClick(text, getContext());
            }
        };
        return clickableSpan;
    }
}

