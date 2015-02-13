package com.redbooth.awesomwtextview;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class ClickableSpanWithoutFormat extends ClickableSpan {

    @Override
    public void updateDrawState(TextPaint ds) {
        // Overriding to avoid the default behavior, as we only require the click functionality.
        // Only is needed the click functionality
    }
}
