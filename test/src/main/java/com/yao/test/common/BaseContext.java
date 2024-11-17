package com.yao.test.common;

public class BaseContext {
    private static ThreadLocal<String> threadLocal = new ThreadLocal();

    public static void setThreadLocal(String userName){
        threadLocal.set(userName);
    }

    public static String getThreadLocal(){
        return threadLocal.get();
    }
    public static void clearThreadLocal(){
        threadLocal.remove();
    }
}
