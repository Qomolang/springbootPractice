package com.magnus.service.infrastruct.utils;

import java.util.function.Supplier;

public class ExceptionUtils {

    /**
     * 处理CE
     * @param supplier
     * @param errorMsg
     * @param <T>
     * @return
     */
        public static <T> T exceptionWrapper(Supplier<T> supplier, String errorMsg){
            try {
                return supplier.get();
            } catch (Exception e){
                //todo 真实使用时，替换为项目自建的runtimeException
                throw new RuntimeException(errorMsg);
            }
        }

}
