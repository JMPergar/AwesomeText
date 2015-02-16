# AwesomeText
A tool that facilitates working with Spans on TextViews or any extension of them (EditTexts, Buttons...)

Screenshots
-----------

![Demo Screenshot][1]

Usage
-----

    public class HashtagsSpanRenderer implements AwesomeTextHandler.ViewSpanRenderer {

        private final static int textSizeInDips = 18;
        private final static int backgroundResource = R.drawable.common_hashtags_background;
        private final static int textColorResource = android.R.color.white;

        @Override
        public View getView(String text, Context context) {
            TextView view = new TextView(context);
            view.setText(text.substring(1));
            view.setTextSize(ScreenUtils.dipsToPixels(context, textSizeInDips));
            view.setBackgroundResource(backgroundResource);
            int textColor = context.getResources().getColor(textColorResource);
            view.setTextColor(textColor);
            return view;
        }
    }


    public class MentionSpanRenderer implements AwesomeTextHandler.ViewSpanRenderer, AwesomeTextHandler.ViewSpanClickListener {

        private final static int textSizeInDips = 18;
        private final static int backgroundResource = R.drawable.common_mentions_background;
        private final static int textColorResource = android.R.color.black;

        @Override
        public View getView(String text, Context context) {
            TextView view = new TextView(context);
            view.setText(text.substring(1));
            view.setTextSize(ScreenUtils.dipsToPixels(context, textSizeInDips));
            view.setBackgroundResource(backgroundResource);
            int textColor = context.getResources().getColor(textColorResource);
            view.setTextColor(textColor);
            return view;
        }

        @Override
        public void onClick(String text, Context context) {
            Toast.makeText(context, "Hello " + text, Toast.LENGTH_SHORT).show();
        }
    }


    public class MainActivity extends ActionBarActivity {
    
        private static final String hashtagPattern = "(#[\\p{L}0-9-_]+)";
        private static final String mentionPattern = "(@[\\p{L}0-9-_]+)";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TextView textView = (TextView) findViewById(R.id.textview);
            EditText editText = (EditText) findViewById(R.id.editText);

            AwesomeTextHandler awesomeTextViewHandler = new AwesomeTextHandler();
            awesomeTextViewHandler
                .addViewSpanRenderer(hashtagPattern, new HashtagsSpanRenderer())
                .addViewSpanRenderer(mentionPattern, new MentionSpanRenderer())
                .setView(textView);

            AwesomeTextHandler awesomeEditTextHandler = new AwesomeTextHandler();
            awesomeEditTextHandler
                .addViewSpanRenderer(hashtagPattern, new HashtagsSpanRenderer())
                .addViewSpanRenderer(mentionPattern, new MentionSpanRenderer())
                .setView(editText);
        }
    }

Developed By
------------

* José Manuel Pereira García - <jm.pereira.g@gmail.com>

<a href="https://twitter.com/jmpergar">
  <img alt="Follow me on Twitter" src="http://imageshack.us/a/img812/3923/smallth.png" />
</a>
<a href="http://www.linkedin.com/in/jmpergar">
  <img alt="Add me to Linkedin" src="http://imageshack.us/a/img41/7877/smallld.png" />
</a>

Who's using it
--------------

* [Redbooth][2]

*Does your app use AwesomeText? If you want to be featured on this list drop me a line.*

Contributors
------------

* [José Manuel Pereira García][3]

License
-------

    Copyright 2014 José Manuel Pereira García

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[1]: https://raw.githubusercontent.com/JMPergar/AwesomeText/master/Screenshot.png
[2]: https://redbooth.com
[3]: https://github.com/JMPergar
