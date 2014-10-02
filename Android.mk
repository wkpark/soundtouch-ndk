LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
res_dirs := res

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := sonic-test

LOCAL_JNI_SHARED_LIBRARIES := libsonic_jni

LOCAL_REQUIRED_MODULES := libsonic_jni

include $(BUILD_PACKAGE)
