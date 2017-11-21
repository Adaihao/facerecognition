package com.facerecognition.utils;

import com.facerecognition.arcsoftAPI.AFD_FSDKLibrary;
import com.facerecognition.arcsoftAPI.AFR_FSDKLibrary;
import com.facerecognition.arcsoftAPI.CLibrary;
import com.facerecognition.arcsoftAPI._AFD_FSDK_OrientPriority;
import com.sun.jna.NativeLong;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.PointerByReference;

/**
 * @author luoji
 * @params
 * @since 2017/11/8 
 */
public class PointerEngineUtil {
    public static final String APPID = "F4xvQyXSY1byE2wBuo4awk6YTRKisr1ac7wpLUXAS1py";
    public static final String FD_SDKKEY = "7dtk1xHjL5PcH15hYo4mr8TZizSXZuEYn8rQChkYWeqV";
    public static final String FR_SDKKEY = "7dtk1xHjL5PcH15hYo4mr8TgtPhkL4EPNWnC4EDmdNj8";

    public static final int FD_WORKBUF_SIZE = 20 * 1024 * 1024;
    public static final int FR_WORKBUF_SIZE = 40 * 1024 * 1024;
    public static final int MAX_FACE_NUM = 50;

    static Pointer hFDEngine = null;
    static Pointer hFREngine = null;

    public static Pointer gethFDEngineInstance() {
        if (hFDEngine == null) {
            synchronized (PointerEngineUtil.class) {
                if (hFDEngine == null) {
                    hFDEngine = newhFDEngine();
                }
            }
        }
        return hFDEngine;
    }

    public static Pointer gethFREngineInstance() {
        if (hFREngine == null) {
            synchronized (PointerEngineUtil.class) {
                if (hFREngine == null) {
                    hFREngine = newhFREngine();
                }
            }
        }
        return hFREngine;
    }

    /**
     * 人脸识别
     *
     * @author adaihao
     * @since 2017/11/8 
     */
    private static Pointer newhFDEngine() {
        Pointer pFDWorkMem = CLibrary.INSTANCE.malloc(FD_WORKBUF_SIZE);
        PointerByReference phFDEngine = new PointerByReference();
        NativeLong ret = AFD_FSDKLibrary.INSTANCE.AFD_FSDK_InitialFaceEngine(APPID, FD_SDKKEY, pFDWorkMem, FD_WORKBUF_SIZE, phFDEngine, _AFD_FSDK_OrientPriority.AFD_FSDK_OPF_0_HIGHER_EXT, 32, MAX_FACE_NUM);
        if (ret.longValue() != 0) {
            CLibrary.INSTANCE.free(pFDWorkMem);
            System.exit(0);
        }
        Pointer hFDEngine = phFDEngine.getValue();
        return hFDEngine;
    }

    /**
     * 人脸对比
     *
     * @author adaihao
     * @since 2017/11/8 
     */
    private static Pointer newhFREngine() {
        Pointer pFRWorkMem = CLibrary.INSTANCE.malloc(FR_WORKBUF_SIZE);
        PointerByReference phFREngine = new PointerByReference();
        NativeLong ret = AFR_FSDKLibrary.INSTANCE.AFR_FSDK_InitialEngine(APPID, FR_SDKKEY, pFRWorkMem, FR_WORKBUF_SIZE, phFREngine);
        if (ret.longValue() != 0) {
            AFD_FSDKLibrary.INSTANCE.AFD_FSDK_UninitialFaceEngine(hFDEngine);
            CLibrary.INSTANCE.free(pFRWorkMem);
            System.exit(0);
        }
        Pointer hFREngine = phFREngine.getValue();
        return hFREngine;
    }
}
