# AwesomeText

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AwesomeText-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1554)[ ![Download](https://api.bintray.com/packages/jmpergar/maven/AwesomeText/images/download.svg) ](https://bintray.com/jmpergar/maven/AwesomeText/_latestVersion) [![Hex.pm](https://img.shields.io/hexpm/l/plug.svg)]() [![Platform](https://img.shields.io/badge/platform-android-green.svg)]()

Working with Spans is ugly and difficult to manage, it's like working with a goblin of Moria. Don't understand the Spans? With AwesomeText you will only have to work with the classic Adapter Pattern. All the ease and potential of a pattern you already know how to use.

Screenshots
-----------

![Demo Screenshot][1]

Download
--------

Download via Gradle:

```groovy
    dependencies {
        compile 'com.jmpergar:AwesomeText:1.0.0'
    }
```

Or via Maven:

```xml
    <dependency>
        <groupId>com.jmpergar</groupId>
        <artifactId>AwesomeText</artifactId>
        <version>1.0.0</version>
        <type>aar</type>
    </dependency>
```

Usage
-----

On the same way that you working with ListAdapter, you must implement an interface to delegate the creation of the view, in this case AwesomeTextHandler.ViewSpanRenderer interface. The method getView is the responsible of this.

```java
    public class HashtagsSpanRenderer implements AwesomeTextHandler.ViewSpanRenderer {

        @Override
        public View getView(String text, Context context) {
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView hashtagView = inflater.inflate(R.layout.hashtag);
            hashtagView.setText(text);
            return hashtagView;
        }
    }
```

If you want to manage click events only must to implement AwesomeTextHandler.ViewSpanClickListener.

```java
    public class MentionSpanRenderer implements AwesomeTextHandler.ViewSpanRenderer, AwesomeTextHandler.ViewSpanClickListener {

        @Override
        public View getView(String text, Context context) {
            LayoutInflater inflater = LayoutInflater.from(context);
            TextView mentionView = inflater.inflate(R.layout.mention);
            mentionView.setText(text);
            return mentionView;
        }

        @Override
        public void onClick(String text, Context context) {
            Toast.makeText(context, "Hello " + text, Toast.LENGTH_SHORT).show();
        }
    }
```

And finally you configure AwesomeTextHandler to view the changes. Now, when you call AwesomeTextHandler.setText the change will be rendered automatically.

```java
    public class MainActivity extends ActionBarActivity {
    
        private static final String HASHTAG_PATTERN = "(#[\\p{L}0-9-_]+)";
        private static final String MENTION_PATTERN = "(@[\\p{L}0-9-_]+)";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            TextView textView = (TextView) findViewById(R.id.textview);
            EditText editText = (EditText) findViewById(R.id.editText);

            AwesomeTextHandler awesomeTextViewHandler = new AwesomeTextHandler();
            awesomeTextViewHandler
                .addViewSpanRenderer(HASHTAG_PATTERN, new HashtagsSpanRenderer())
                .addViewSpanRenderer(MENTION_PATTERN, new MentionSpanRenderer())
                .setView(textView);

            AwesomeTextHandler awesomeEditTextHandler = new AwesomeTextHandler();
            awesomeEditTextHandler
                .addViewSpanRenderer(HASHTAG_PATTERN, new HashtagsSpanRenderer())
                .addViewSpanRenderer(MENTION_PATTERN, new MentionSpanRenderer())
                .setView(editText);
        }
    }
```


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

    Copyright 2015 José Manuel Pereira García

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
