Practice Report for 2/8
------

実習のレポートを下記に記述してください

### Service

1. サンプルプロジェクト (ServiceSample) に、サービスのライフサイクルをログに出力する実装が格納されています。このプロジェクトをビルドし、ログがどのように表示されるかをレポートしてください。

[Start]
05-15 14:01:24.945: V/StartedService(24924): onCreate
05-15 14:01:24.945: V/StartedService(24924): onStartCommand


[Stop]
05-15 14:01:45.062: V/StartedService(24924): onDestroy


[Bind]
05-15 14:01:55.476: V/BoundService(24924): onCreate
05-15 14:01:55.476: V/MainActivity(24924): onServiceConnected

[Unbind]
05-15 14:02:14.883: V/BoundService(24924): onUnbind
05-15 14:02:14.883: V/BoundService(24924): onDestroy

[Call IntentService]
05-15 14:02:27.750: V/MyIntentService(24924): onCreate
05-15 14:02:27.758: V/MyIntentService(24924): onStartCommand
05-15 14:02:27.758: V/MyIntentService(24924): onHandleIntent
05-15 14:02:27.773: V/MyIntentService(24924): onDestroy


### Loader

1. サンプルプロジェクト (LoaderSample) に、AsyncTaskLoader のライフサイクルをログに出力する実装が格納されています。このプロジェクトをビルドし、ログがどのように出力されているかをレポートしてください。

05-15 14:12:54.828: V/MainActivity(25915): onCreateLoader
05-15 14:12:54.828: V/MyAsyncTaskLoader(25915): onStartLoading
05-15 14:12:54.859: V/MyAsyncTaskLoader(25915): loadInBackground
05-15 14:12:54.914: W/WindowManager(195): focusMayChange = true [win.mAttrs.type=2011]
05-15 14:12:54.961: I/ActivityManager(195): Displayed activity jp.mixi.sample.loader/.MainActivity: 66910 ms (total 66910 ms)
05-15 14:12:54.992: I/ggheart(1055): onStop
05-15 14:12:55.875: V/MyAsyncTaskLoader(25915): deliverResult
05-15 14:12:55.883: V/MainActivity(25915): onLoadFinished


### AsyncTask

1. `AsyncTask#doInBackground()` で、TextView の文字を変更するような、UI の処理を実行するとどうなるかを、理由を添えてレポートしてください。

05-15 15:00:39.406: E/AndroidRuntime(29911): FATAL EXCEPTION: AsyncTask #1
05-15 15:00:39.406: E/AndroidRuntime(29911): java.lang.RuntimeException: An error occured while executing doInBackground()
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at android.os.AsyncTask$3.done(AsyncTask.java:200)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.util.concurrent.FutureTask$Sync.innerSetException(FutureTask.java:273)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.util.concurrent.FutureTask.setException(FutureTask.java:124)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.util.concurrent.FutureTask$Sync.innerRun(FutureTask.java:307)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.util.concurrent.FutureTask.run(FutureTask.java:137)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1068)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:561)
05-15 15:00:39.406: E/AndroidRuntime(29911): 				 at java.lang.Thread.run(Thread.java:1096)
05-15 15:00:39.406: E/AndroidRuntime(29911): Caused by: android.view.ViewRoot$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.

ビューを変更できるのはUIスレッドのみなので、onProgressUpdateやonPostExecute内でTextViewの中身を変更する分にはアプリは落ちない。