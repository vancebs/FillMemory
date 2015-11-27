//
// Created by Fan on 2015/11/27.
//

#ifndef FILLMEMORY_NATIVEFILL_H
#define FILLMEMORY_NATIVEFILL_H


class NativeFill {
private:
    char** mppBuff;
    int mBuffSize;

    static NativeFill* mpInstance;
public:
    NativeFill();
    void fill(int size);
    int getFilledSize();

    static NativeFill* getInstance();
};


#endif //FILLMEMORY_NATIVEFILL_H
