package com.my_blogs.context;

public class BaseContext {
    /**
     * 一个线程局部存储当前线程的ID
     */
    public static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置当前线程的ID
     *
     * @param id 当前线程的ID
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 获取当前线程的ID
     *
     * @return 当前线程的ID
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }

    /**
     * 移除当前线程的ID
     */
    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
