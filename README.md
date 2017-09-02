[toc]

## 项目演示

![这里写图片描述](http://img.blog.csdn.net/20170902172351113?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

## 源码下载

Github：https://github.com/AndroidHensen/NDKVoice

## 环境配置

1. 你的Android Studio在SDKManager中下载好ndk-build和cmake
2. 通过fmod的官网，在DownLoad页面中，找到Android平台的源码进行下载，这里需要注意使用VPN或者联系客服

## 项目开始

1、创建一个新的项目，勾选包含C++

![这里写图片描述](http://img.blog.csdn.net/20170902170637497?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

2、勾选C++11和C++的依赖库

![这里写图片描述](http://img.blog.csdn.net/20170902170709297?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

3、找到下载好的fmod目录下，在api/lowlevel/lib中，将所有文件拷贝到你的项目的libs目录下

![这里写图片描述](http://img.blog.csdn.net/20170902170819852?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

4、让项目添加对fmod.jar包的支持，fmod.jar右键Add As Libs

![这里写图片描述](http://img.blog.csdn.net/20170902171030466?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

5、找到下载好的fmod目录下，在api/lowlevel/inc中，将所有文件拷贝到你的项目的cpp(jni folder)目录下

![这里写图片描述](http://img.blog.csdn.net/20170902171306657?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

拷贝好后如下图

![这里写图片描述](http://img.blog.csdn.net/20170902171320417?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

6、准备好一个音频文件放到assets文件夹中

![这里写图片描述](http://img.blog.csdn.net/20170902172212909?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

7、配置app的gradle文件中的cmake和ndk

```
android {
    compileSdkVersion 25
    buildToolsVersion "25.0.3"
    defaultConfig {
        applicationId "com.handsome.ndkvoice"
        minSdkVersion 16
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags "-std=c++11 -frtti -fexceptions"
                abiFilters "armeabi","arm64-v8a","armeabi-v7a","x86"
            }
        }
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets.main {
        jniLibs.srcDirs = ['libs']
        jni.srcDirs = []
    }
    externalNativeBuild {
        cmake {
            path "CMakeLists.txt"
        }
    }
}
```

## 代码编写

1、我们创建一个Utils类，编写我们的fmod变声处理

```
public class Utils {

    //音效类型
    public static final int MODE_NORMAL = 0;
    public static final int MODE_LUOLI = 1;
    public static final int MODE_DASHU = 2;
    public static final int MODE_JINGSONG = 3;
    public static final int MODE_GAOGUAI = 4;
    public static final int MODE_KONGLING = 5;

    /**
     * 音效处理传2个值
     *
     * @param path 音频文件路径
     * @param type 音频文件类型
     */
    public native static void fix(String path, int type);

    static {
        System.loadLibrary("fmodL");
        System.loadLibrary("fmod");
        System.loadLibrary("sound");
    }
}
```

2、通过终端，javah我们的文件

![这里写图片描述](http://img.blog.csdn.net/20170902171651325?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

3、将创建好的.h文件放进我们的cpp目录下，并创建sound.cpp

![这里写图片描述](http://img.blog.csdn.net/20170902171742910?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

4、编写sound.cpp代码

```
#include "inc/fmod.hpp"
#include <stdlib.h>
#include <unistd.h>
#include  "com_handsome_ndkvoice_Utils.h"

#include <jni.h>

#include <android/log.h>
#define LOGI(FORMAT,...) __android_log_print(ANDROID_LOG_INFO,"zph",FORMAT,##__VA_ARGS__);
#define LOGE(FORMAT,...) __android_log_print(ANDROID_LOG_ERROR,"zph",FORMAT,##__VA_ARGS__);

#define MODE_NORMAL 0
#define MODE_LUOLI 1
#define MODE_DASHU 2
#define MODE_JINGSONG 3
#define MODE_GAOGUAI 4
#define MODE_KONGLING 5

using namespace FMOD;

JNIEXPORT void JNICALL Java_com_handsome_ndkvoice_Utils_fix(JNIEnv *env,
		jclass jcls, jstring path_jstr, jint type) {
    //声音引擎
	System *system;
    //声音
	Sound *sound;
	//数字处理（音效）
    DSP *dsp;
    //正在播放
	bool playing = true;
	//音乐轨道
    Channel *channel;
	//播放速度
    float frequency = 0;
    //音频地址
    const char* path_cstr = env->GetStringUTFChars(path_jstr, NULL);

    System_Create(&system);
	system->init(32, FMOD_INIT_NORMAL, NULL);

	try {
		//创建声音
		system->createSound(path_cstr, FMOD_DEFAULT, NULL, &sound);
		switch (type) {
            case MODE_NORMAL:
                //原生播放
                system->playSound(sound, 0, false, &channel);
                break;
            case MODE_LUOLI:
                //提升或者降低音调的一种音效
                system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
                //设置音调的参数
                dsp->setParameterFloat(FMOD_DSP_PITCHSHIFT_PITCH, 1.8);
                //添加进到channel，添加进轨道
                system->playSound(sound, 0, false, &channel);
                channel->addDSP(0, dsp);
                break;
            case MODE_DASHU:
                system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
                dsp->setParameterFloat(FMOD_DSP_PITCHSHIFT_PITCH, 0.8);
                system->playSound(sound, 0, false, &channel);
                channel->addDSP(0, dsp);
                break;
            case MODE_JINGSONG:
                system->createDSPByType(FMOD_DSP_TYPE_TREMOLO, &dsp);
                dsp->setParameterFloat(FMOD_DSP_TREMOLO_SKEW, 0.8);
                system->playSound(sound, 0, false, &channel);
                channel->addDSP(0, dsp);
                break;
            case MODE_GAOGUAI:
                //提高说话的速度
                system->playSound(sound, 0, false, &channel);
                channel->getFrequency(&frequency);
                frequency = frequency * 2;
                channel->setFrequency(frequency);
                break;
            case MODE_KONGLING:
                system->createDSPByType(FMOD_DSP_TYPE_ECHO, &dsp);
                dsp->setParameterFloat(FMOD_DSP_ECHO_DELAY, 300);
                dsp->setParameterFloat(FMOD_DSP_ECHO_FEEDBACK, 20);
                system->playSound(sound, 0, false, &channel);
                channel->addDSP(0, dsp);
                break;
            }
	} catch (...) {
		LOGE("%s", "发生异常");
		goto end;
	}
	system->update();

	//单位是微妙
	//每秒钟判断下是否是播放
	while (playing) {
		channel->isPlaying(&playing);
        usleep(1000);
	}
	goto end;

    //释放资源
    end: env->ReleaseStringUTFChars(path_jstr, path_cstr);
	sound->release();
	system->close();
	system->release();
}
```

5、配置cmake文件

```
cmake_minimum_required(VERSION 3.4.1)


find_library( # Sets the name of the path variable.
              log-lib
              log )

set(distribution_DIR ${CMAKE_SOURCE_DIR}/libs)

add_library( fmod
             SHARED
             IMPORTED )
set_target_properties( fmod
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmod.so )
add_library( fmodL
             SHARED
             IMPORTED )
set_target_properties( fmodL
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so )
add_library( sound
             SHARED
             src/main/cpp/sound.cpp )

include_directories(src/main/cpp/inc)

target_link_libraries( sound fmod fmodL
                       ${log-lib} )
```

6、编写我们的Activity，对每个类型处理点击事件并调用本地方法

```
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ExecutorService fixedThreadPool;
    private PlayerThread playerThread;
    private String path = "file:///android_asset/hensen.mp3";
    private int type;

    private LinearLayout normal, luoli, dashu, jingsong, gaoguai, kongling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        normal = (LinearLayout) findViewById(R.id.normal);
        luoli = (LinearLayout) findViewById(R.id.luoli);
        dashu = (LinearLayout) findViewById(R.id.dashu);
        jingsong = (LinearLayout) findViewById(R.id.jingsong);
        gaoguai = (LinearLayout) findViewById(R.id.gaoguai);
        kongling = (LinearLayout) findViewById(R.id.kongling);
        normal.setOnClickListener(this);
        luoli.setOnClickListener(this);
        dashu.setOnClickListener(this);
        jingsong.setOnClickListener(this);
        gaoguai.setOnClickListener(this);
        kongling.setOnClickListener(this);

        fixedThreadPool = Executors.newFixedThreadPool(1);
        FMOD.init(this);
    }

    class PlayerThread implements Runnable {
        @Override
        public void run() {
            Utils.fix(path, type);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.normal:
                type = Utils.MODE_NORMAL;
                break;
            case R.id.luoli:
                type = Utils.MODE_LUOLI;
                break;
            case R.id.dashu:
                type = Utils.MODE_DASHU;
                break;
            case R.id.jingsong:
                type = Utils.MODE_JINGSONG;
                break;
            case R.id.gaoguai:
                type = Utils.MODE_GAOGUAI;
                break;
            case R.id.kongling:
                type = Utils.MODE_KONGLING;
                break;
        }

        playerThread = new PlayerThread();
        fixedThreadPool.execute(playerThread);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FMOD.close();
    }
}
```

## 过程分析

* cmake文件的分析
* 变声类型的分析
* 实现变声代码过程的分析

### cmake文件分析

1、声明cmake版本

```
cmake_minimum_required(VERSION 3.4.1)
```

2、设置一个路径变量，指向我们的libs目录，也就是fmod.jar和so库的目录

```
set(distribution_DIR ${CMAKE_SOURCE_DIR}/libs)
```

3、添加我们需要的fmod、fmodL、sound的库

```
add_library( fmod
             SHARED
             IMPORTED )
set_target_properties( fmod
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmod.so )
add_library( fmodL
             SHARED
             IMPORTED )
set_target_properties( fmodL
                       PROPERTIES IMPORTED_LOCATION
                       ${distribution_DIR}/${ANDROID_ABI}/libfmodL.so )
add_library( sound
             SHARED
             src/main/cpp/sound.cpp )
```

这里是通过我们刚才设置的libs目录下，和对应的Gradle设置的架构进行匹配，找到libfmod.so文件

```
${distribution_DIR}/${ANDROID_ABI}/libfmod.so
```

Gradle设置的架构在这个地方

![这里写图片描述](http://img.blog.csdn.net/20170902173448296?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

### 变声类型的分析

* 原声：直接播放音频文件
* 萝莉：对音频提高八度
* 大叔：对音频减低八度
* 惊悚：增加音频的颤音
* 搞笑：增加音频的播放速度
* 空灵：增加音频的回音

### 实现变声代码过程的分析

这里的实现主要是阅读fmod下载的例子，播放声音的代码和增加音效的代码来编写的

![这里写图片描述](http://img.blog.csdn.net/20170902173847118?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvcXFfMzAzNzk2ODk=/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast)

大概的流程

1. 创建fmod的系统：System_Create(&system);
2. 系统进行初始化：system->init(32, FMOD_INIT_NORMAL, NULL);
3. 创建音频：system->createSound(path_cstr, FMOD_DEFAULT, NULL, &sound);
4. 创建不同类型的音效：system->createDSPByType(FMOD_DSP_TYPE_PITCHSHIFT, &dsp);
5. 播放声音：system->playSound(sound, 0, false, &channel);
6. 将音效加入声音中：channel->addDSP(0, dsp);
7. 关闭资源：end部分
