LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

#include stlport headers
include external/stlport/libstlport.mk

LOCAL_MODULE	:= libsoundtouch_jni

LOCAL_C_INCLUDES += external/soundtouch/include

LOCAL_SRC_FILES := SoundTouch.cpp

LOCAL_LDLIBS	+= -llog

LOCAL_CFLAGS	+= -Wall -fvisibility=hidden -I external/soundtouch/include \
		-U_FORTIFY_SOURCE \
		-D ST_NO_EXCEPTION_HANDLING -fdata-sections -ffunction-sections -marm

LOCAL_SHARED_LIBRARIES += liblog
LOCAL_STATIC_LIBRARIES := libsoundtouch
include $(BUILD_SHARED_LIBRARY)
