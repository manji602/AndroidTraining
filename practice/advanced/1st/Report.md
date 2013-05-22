Practice Report for 3.1
------

実習のレポートを下記に記述してください

デバッグ

1. (実習) 実習プロジェクト`DebugPractice`をビルド・インストールし、起動してから画面が立ち上がるまでのメソッドのプロファイリングを実行し、どのメソッドに時間がかかっているかレポートしてください。

android/text/Layout.access$100 が最も時間がかかっていた。
Incl Cpu Time(%) 68.2%
Incl Cpu Time 255.846
Excl Cpu Time(%) 68.2%
Excl Cpu Time 255.550
Calls Total 40+0
Cpu Time/Call 6.396

自動ビルド

2. (実習) 適用したプロジェクトを ant でビルドし、ログを見て、ビルドに必要な手順をレポートしてください。

LinearLayoutPractice1で実行してみました。

90201702n:Practice1 jun.hashimoto$ android update project -p ./ -n LinearLayoutPractice1
Updated local.properties
Updated file ./build.xml
Updated file ./proguard-project.txt
It seems that there are sub-projects. If you want to update them
please use the --subprojects parameter.


90201702n:Practice1 jun.hashimoto$ ant debug
Buildfile: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/build.xml

-set-mode-check:

-set-debug-files:

-check-env:
 [checkenv] Android SDK Tools Revision 21.1.0
 [checkenv] Installed at /Applications/Android_SDK/sdk

-setup:
     [echo] Project Name: LinearLayoutPractice1
  [gettype] Project Type: Application

-set-debug-mode:

-debug-obfuscation-check:

-pre-build:

-build-setup:
     [echo] Resolving Build Target for LinearLayoutPractice1...
[gettarget] Project Target:   Android 4.2.2
[gettarget] API level:        17
     [echo] ----------
     [echo] Creating output directories if needed...
     [echo] ----------
     [echo] Resolving Dependencies for LinearLayoutPractice1...
[dependency] Library dependencies:
[dependency] No Libraries
     [echo] ----------
     [echo] Building Libraries with 'debug'...
   [subant] No sub-builds to iterate on

-code-gen:
[mergemanifest] No changes in the AndroidManifest files.
     [echo] Handling aidl files...
     [aidl] No AIDL files to compile.
     [echo] ----------
     [echo] Handling RenderScript files...
[renderscript] No RenderScript files to compile.
     [echo] ----------
     [echo] Handling Resources...
     [aapt] Found new input file
     [aapt] Generating resource IDs...
     [echo] ----------
     [echo] Handling BuildConfig class...
[buildconfig] No need to generate new BuildConfig.

-pre-compile:

-compile:
    [javac] Compiling 1 source file to /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/classes

-post-compile:

-obfuscate:

-dex:
      [dex] input: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/classes
      [dex] input: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/libs/android-support-v4.jar
      [dex] Using Pre-Dexed android-support-v4-5c26d80075eb96aba92e98c221e3d46e.jar <- /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/libs/android-support-v4.jar
      [dex] Found modified input file
      [dex] Converting compiled files and external libraries into /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/classes.dex...
       [dx] Merged dex A (11 defs/3.4KiB) with dex B (316 defs/355.2KiB). Result is 327 defs/438.4KiB. Took 0.2s

-crunch:
   [crunch] Crunching PNG Files in source dir: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/res
   [crunch] To destination dir: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/res
   [crunch] Crunched 0 PNG files to update cache

-package-resources:
     [aapt] No changed resources or assets. LinearLayoutPractice1.ap_ remains untouched

-package:
[apkbuilder] Found modified input file
[apkbuilder] Creating LinearLayoutPractice1-debug-unaligned.apk and signing it with a debug key...

-post-package:

-do-debug:
 [zipalign] Running zip align on final apk...
     [echo] Debug Package: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/LinearLayoutPractice1-debug.apk
[propertyfile] Updating property file: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/build.prop
[propertyfile] Updating property file: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/build.prop
[propertyfile] Updating property file: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/build.prop
[propertyfile] Updating property file: /Users/jun.hashimoto/src/Android/AndroidTraining/practice/fundamentals/1st/LinearLayoutPractice/Practice1/bin/build.prop

-post-build:

debug:

BUILD SUCCESSFUL
Total time: 3 seconds

正しく生成されました。


90201702n:Practice1 jun.hashimoto$ cd bin
90201702n:bin jun.hashimoto$ ls
AndroidManifest.xml					classes
AndroidManifest.xml.d					classes.dex
LinearLayoutPractice1-debug-unaligned.apk		classes.dex.d
LinearLayoutPractice1-debug-unaligned.apk.d		dexedLibs
LinearLayoutPractice1-debug.apk				jarlist.cache
LinearLayoutPractice1.ap_				proguard.txt
LinearLayoutPractice1.ap_.d	res	build.prop

LinearLayoutPractice1-debug.apkが生成されました。