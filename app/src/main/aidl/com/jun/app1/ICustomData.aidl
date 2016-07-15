// ICustomData.aidl
package com.jun.app1;

// Declare any non-default types here with import statements

// 注意：Person.aidl和Person.java的位置要一致
// 需要导入自定义数据的类型
import com.jun.app1.model.Person;

interface ICustomData {

    List<Person> add(in Person person);
}
