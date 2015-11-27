#include "NativeFill.h"
#include "com_hf_fillmemory_MyService.h"

/*
 * Class:     com_hf_fillmemory_MyService
 * Method:    fill
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_com_hf_fillmemory_MyService_fill(JNIEnv* pEnv, jobject obj, jint size) {
    NativeFill* pNativeFill = NativeFill::getInstance();
    pNativeFill->fill((int)size);
}

/*
 * Class:     com_hf_fillmemory_MyService
 * Method:    getFilledSize
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_hf_fillmemory_MyService_getFilledSize(JNIEnv* pEnv, jobject obj) {
    NativeFill* pNativeFill = NativeFill::getInstance();
    return (jint) pNativeFill->getFilledSize();
}
