// IBaseData.aidl
package com.jun.app1;

// Declare any non-default types here with import statements

interface IBaseData {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    // 不支持short类型
    void basicTypes(byte aByte, int anInt, long aLong,
            boolean aBoolean,
            float aFloat, double aDouble,
            char aChar,
            String aString, CharSequence aCharSequence,
            inout List<String> aList);

    // 打印出List中的数据
    void showList(in List<String> aList);
}
