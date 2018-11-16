package com.baizhi.util;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by wdwhwn on 2018/11/16.
 */
/*写一个类
* 用来作为请求的对象
* 使service可以通过这个参数利用反射
* 接收实现类的方法
* 1 类对象
* 2 方法名
* 3 参数列表
* 4 参数类型
* */
public class RequestData implements Serializable{
    private Class  className;
    private String methodName;
    private Object[] paramList;
    private Class[] typeList;

    @Override
    public String toString() {
        return "RequestData{" +
                "className=" + className +
                ", methodName='" + methodName + '\'' +
                ", paramList=" + Arrays.toString(paramList) +
                ", typeList=" + Arrays.toString(typeList) +
                '}';
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParamList() {
        return paramList;
    }

    public void setParamList(Object[] paramList) {
        this.paramList = paramList;
    }

    public Class[] getTypeList() {
        return typeList;
    }

    public void setTypeList(Class[] typeList) {
        this.typeList = typeList;
    }

    public RequestData(Class className, String methodName, Object[] paramList, Class[] typeList) {
        this.className = className;
        this.methodName = methodName;
        this.paramList = paramList;
        this.typeList = typeList;
    }

    public RequestData() {
    }
}
