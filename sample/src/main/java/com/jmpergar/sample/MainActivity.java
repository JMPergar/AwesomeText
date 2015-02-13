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

package com.jmpergar.sample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.jmpergar.awesometextview.AwesomeTextView;

public class MainActivity extends ActionBarActivity {
    private static final String hashtagPattern = "(#[\\p{L}0-9-_]+)";
    private static final String mentionPattern = "(@[\\p{L}0-9-_]+)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AwesomeTextView awesomeTextView = (AwesomeTextView) findViewById(R.id.textview);
        awesomeTextView.addViewSpanRenderer(hashtagPattern, new HashtagsSpanRenderer());
        awesomeTextView.addViewSpanRenderer(mentionPattern, new MentionSpanRenderer());
        awesomeTextView.applyRenderers();
    }
}
