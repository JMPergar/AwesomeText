/*
 * Copyright (C) 2015 José Manuel Pereira García.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jmpergar.awesometext;

import android.text.TextPaint;
import android.text.style.ClickableSpan;

public abstract class ClickableSpanWithoutFormat extends ClickableSpan {

    @Override
    public void updateDrawState(TextPaint ds) {
        // Overriding to avoid the default behavior, as we only require the click functionality.
    }
}
