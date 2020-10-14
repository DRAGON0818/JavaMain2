#include "Chapter12Native_java_HelloJNI.h"
#include <jni.h>
#include <stdio.h>

JNIEXPORT void JNICALL Java_Chapter12Native_java_HelloJNI_sayHello
  (JNIEnv * env, jobject obj){
    printf("Hello Native World");
  }