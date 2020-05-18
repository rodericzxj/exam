package com.online.exam.common;

/**
 * @author rocky
 * @create 2020-05-05-18:52
 */
public class MallException extends  RuntimeException{
    public MallException(){

    }

    public MallException(String message){
        super(message);
    }

    /**
     * 丢出一个异常
     *
     * @param message
     */
    public static void fail(String message) {
        throw new MallException(message);
    }
}
