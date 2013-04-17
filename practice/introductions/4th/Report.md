Practice Report for 1.4
------

実習のレポートを下記に記述してください

1.(実習)Android SDK 内の、下記の 2 つのディレクトリにあるコマンドを列挙してください。

    [tools]
    emulator-mips / monitor /emulator-x86 monkeyrunner / emulator64-arm / android / emulator64-mips / emulator64-x86 / sqlite3 / apkbuilder / etc1tool / hierarchyviewer / ddms / hprof-conv / dmtracedump / jobb / traceview / draw9patch / uiautomatorviewer / emulator / lint / zipalign / emulator-arm / mksdcard

    [platform-tools]
    aapt / dexdump / llvm-rs-cc / adb / dx / aidl / fastboot


2.(実習)上記のディレクトリにパスを通し、下記のコマンドを実行してください。

    90201702n:introductions jun.hashimoto$ adb devices
    List of devices attached 
    TG03242514	    device

    90201702n:introductions jun.hashimoto$ adb shell
    $ exit

3.(実習)adb shellコマンドを使って、Android 内のファイルシステムにアクセスし、下記の項目を確認してください。

**端末のSD カード領域に、ローカルにあるファイルを転送する**

    90201702n:~ jun.hashimoto$ adb push Downloads/test.txt sdcard/test.txt
    2 KB/s (15 bytes in 0.006s)

**端末のSD カード領域から、ローカルにファイルを転送する**

    90201702n:~ jun.hashimoto$ adb pull sdcard/test.txt Downloads/
    3 KB/s (15 bytes in 0.004s)

**課題用サンプルプロジェクトの apk ファイルをコマンド経由で端末にインストールする**

    90201702n:~ jun.hashimoto$ adb install Downloads/Hashijun.test.apk 
    1337 KB/s (196014 bytes in 0.143s)
        pkg: /data/local/tmp/Hashijun.test.apk
    Success


**インストールしたアプリを、コマンド経由でアンインストールする**

    90201702n:~ jun.hashimoto$ adb uninstall com.example.hashijun.test
    Success