### 什么是异常？

程序运行过程中产生的不正常的情况统称为异常！
Throwable 类是 Java 语言中所有错误或异常的超类。提供了错误堆栈实现等一系列方法。 有两个直接子类：Error & Exception

### 程序错误一般分为三种：

1.编译错误；2.运行时错误；3.逻辑错误。

（1）编译错误是因为程序没有遵循语法规则，编译程序能够自己发现并且提示我们错误的原因和位置，这个也是我们在刚接触编程语言最常遇到的问题。

（2）运行时错误是因为程序在执行时，运行环境发现了不能执行的操作。

（3）逻辑错误是因为程序没有按照预期的逻辑顺序执行。异常也就是指程序运行时发生错误，而异常处理就是对这些错误进行处理和控制。

#### 两个子类区别：

- Error： 程序不应该捕捉的错误，应该交由JVM来处理。一般可能指非常重大的错误。这个错误我们一般获取不到，也无法处理！

- Exception：程序中应该要捕获的错误。这个异常类及它的子类是我们需要学习获取要处理的。

（1）RuntimeException：运行时异常，也叫未检查异常，是Exception的子类，但不需捕捉的异常超类，但是实际发生异常时，还是会导致程序停止运行的的，只是编译时没有报错而已。比如除数为零，数组空指针等等，这些都是在运行之后才会报错。此类异常，可以处理也可以不处理，并且可以避免。

（2）在Exception的所有子类中 除了RuntimeException类和它的子类，其他类都叫做非运行时异常，或者叫已检查异常，通常被定义为Checked类，是必须要处理可能出现的异常，否则编译就报错了。Checked类主要包含：IO类和SQL类的异常情况，这些在使用时经常要先处理异常（使用throws或try catch捕获）。

#### java几种常见的异常：

#### 运行时异常：

1，java.lang.ArrayIndexOutOfBoundsException 数组索引越界异常。当对数组的索引值为负数或大于等于数组大小时抛出。
2，ArithmeticException 算术错误情形，如以零作除数，算术条件异常。
3 java.lang.SecurityException 安全性异常
4，IllegalArgumentException 方法接收到非法参数，非法参数异常！
5，java.lang.ArrayStoreException 数组中包含不兼容的值抛出的异常
6，java.lang.NegativeArraySizeException 数组长度为负异常
7 java.lang.ClassNotFoundException 找不到类异常。当应用试图根据字符串形式的类名构造类，而在遍历CLASSPAH之后找不到对应名称的class文件时，抛出该异常。
8 java.lang.NullPointerException 空指针异常。当应用试图在要求使用对象的地方使用了null时，抛出该异常。譬如：调用null对象的实例方法、访问null对象的属性、计算null对象的长度、使用throw语句抛出null等等。
9，java.lang.NumberFormatException（数字格式转换异常）
10，java.lang.ClassCastException(强制类型转换异常)

#### IOException

1， IOException 操作输入流和输出流时可能出现的异常
2， EOFException 文件已结束异常
3， FileNotFoundException 文件未找到异常

### 异常的产生：

自动产生：当程序遇到错误代码，会产生异常，程序终止
手动产生：throw new 异常类名();
throw必须定义在在方法体中，用来抛出一个Throwable类型的异常。程序会在throw语句后立即终止，它后面的语句执行不到，然后在包含它的所有try块中（可能在上层调用函数中）从里向外寻找含有与其匹配的catch子句的try块。

下面看一个手动抛出异常的例子：

```java
package prac;
public class t2 {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		m1();
	}
public static void m1() throws Exception {
	System.out.println("m1----------start");
	m2();
	// 手动抛出受查异常
	throw new Exception();
	//System.out.println("m1----------end");
}

public static void m2() {
	System.out.println("m2----------start");
	// 手动抛出运行时异常，需要携带信息“程序因为异常而终止”
	throw new RuntimeException("程序因为异常而终止");
	//System.out.println("m2----------end");
	}
}
程序运行结果为：
m1----------start
m2----------start
Exception in thread "main" java.lang.RuntimeException: 程序因为异常而终止
	at prac.t2.m2(t2.java:31)
	at prac.t2.m1(t2.java:21)
	at prac.t2.main(t2.java:16)
```


### 异常的处理：

异常的处理分为消极的处理（自己处理不了，就往调用它的地方上抛throws，异常没有解决，只是抛出）和积极处理（异常捕获，捕捉异常通过try-catch语句或者try-catch-finally语句实现）

#### 消极的处理：

语法：public void m1() throws 异常类名1,异常类名2{}
特点：相当于推卸责任，最终问题依然无法得到解决。
允许编译通过。
当上抛的异常为非运行（已检查）时异常时，调用此方法的调用者必须处理。
当上抛的异常为运行（未检查）时异常时，可以处理可以不处理。

#### 积极的处理：(异常捕获)

将异常直接捕获，并且做出处理
语法：

```javascript
try{
//异常代码
}catch(异常类名 引用名){  
	//当异常产生执行的代码
}
```
try 后的catch代码块 只能匹配成功一个
catch后声明的异常为父类时，它能够捕捉的异常为它本身+所有子类异常（多态的体现）
**注意：**catch代码块捕获异常时，子类异常必须定义在父类异常前面，否则会编译出错

finally代码块：一定会执行此代码块中的代码，常用来关闭资源，

```java
try{

}catch(){

}finally{
	//无论是否产生异常，一定会去执行的代码
}
注意：finally代码块中不要定义return语句
```

下面一个程序详细介绍异常的处理：

```java
package work;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

/**

 * @author 超伟

 * @date 2019年5月13日  

 * @version 1.0.0  
   */
   public class t4 {
   public static void main(String[] args) {
   	System.out.println("main1");
   	int n;
   	Scanner sc = new Scanner(System.in);
   	n = sc.nextInt();
   	ma(n);
   	System.out.println("main2");
   }
   public static void ma(int n){
   	try {
   		System.out.println("m1");
   		mb(n);
   		System.out.println("m2");
   	} catch (EOFException e) {
   		//e.printStackTrace();
   		System.out.println("EOFException    ");
   	} catch (IOException e) {
   		System.out.println("IOException e111");
   	}catch (SQLException e) {
   		System.out.println("SQLException");
   	}catch (Exception e) {
   		System.out.println("Exception");
   	}finally{
   		System.out.println("in finally");
   	}
   }

   public static void mb(int n) throws Exception {
   	System.out.println("mb1");
   	if (n==1) {
   		throw new EOFException();
   	}
   	if (n==2) {
   		throw new FileNotFoundException();
   	}
   	if (n==3) {
   		throw new SQLException();
   	}
   	if (n==4) {
   		throw new NullPointerException();
   	}
   	System.out.println("mb2");
   }	
   }
   程序运行结果为：
   main1
   1
   m1
   mb1
   EOFException    
   in finally
   main2
```



#### throw和throws的区别

这只是有点相似的关键字，并没有任何关联，要学会区分。

throw是用在方法体里面的，一般是放在判断情况的后面，手动抛出异常。

throws只能用在需要捕获异常的方法的方法名的括号后面使用。

### 自定义异常：

为什么要自定义异常？
当Java内置的异常都不能明确的说明异常情况的时候，需要创建自己的异常。

自定义一个异常类，需要继承于Exception或者其子类。
若想要这个异常类为运行时异常，需继承于RuntimeException或者其子类。
一般定义为运行时异常

自定义的异常类需要至少无参有参两个构造方法
例如：

```java
package work;
import java.io.EOFException;
import java.util.Scanner;
public class t5 {
public static void main(String[] args) {
	// TODO Auto-generated method stub
	Scanner sc = new Scanner(System.in);
	int n = sc.nextInt();
	try {
		m(n);
		
	} catch (MyException1 e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		System.out.println(e.getMessage());
	}catch (MyException2 e) {
		// TODO: handle exception
		e.printStackTrace();
		System.out.println(e.getMessage());
	}	
}

public static void m(int n) throws MyException1{
	if (n==1) {
		throw new MyException1("我自己定义的非运行时异常");
	}
	else {
		throw new MyException2("这是我定义的一个运行时异常！！！");
        }
    }
}
//自定义异常
//已检查异常，非运行时异常
class MyException1 extends EOFException{
	public MyException1() {
		super();
	}
    public MyException1(String s) {
	super(s);
	}
}
//未检查异常
//运行时异常
class MyException2 extends RuntimeException{
	public MyException2() {
	}
	public MyException2(String message) {
		super(message);
	}
}
```


#### 方法覆盖，在继承中的异常的提现：

方法覆盖需要满足的3个条件
1.返回值类型 方法名 参数列表完全一致
2.访问修饰符相同或更宽
3.子类中方法不能抛出比父类更多、更宽的异常

```java
package work;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**

 * @author 超伟

 * @date 2019年5月13日  

 * @version 1.0.0  
   */
   public class t8 {
   /**

    * @param args 
      */
      public static void main(String[] args) {
      // TODO Auto-generated method stub
      }
      }
      class Super{
      public void ma() throws IOException{

   }
   }
   interface IA{
   void mb();
   }
   class MySub extends Super implements IA{
   //父类抛出一个非运行时异常，子类可以抛出一个或者多个父类抛出的异常的子类异常
   /*public void ma() throws IOException,FileNotFoundException,EOFException{
   }*/
   //父类抛出一个非运行时异常，子类可以抛出一个或者多个运行时异常
   public void ma() throws SecurityException,ClassCastException{
   }
   public void mb() {
   }
 }
```

Java异常处理的原则和技巧
1，避免过大的try块。不要把不会出现异常的代码放到try块里面，尽量保持一个try块对应一个或多个异常。
2，细化异常的类型。不要不管什么类型的异常都写成Excetpion。
3，catch块尽量保持一个块捕获一类异常。不要忽略捕获的异常，捕获到后要么处理，要么转译，要么重新抛出新类型的异常。
4，不要把自己能处理的异常抛给别人。
5，不要用try…catch参与控制程序流程。异常控制的根本目的是处理程序的非正常情况。

所以像那些可以预料的错误，尽量在执行之前就做好相应的处理，

比如除数为零或空指针问题，要做事前判断再运行程序。

### final, finally, finalize 的区别

final 用于声明属性，方法和类，分别表示属性不可变，方法不可覆盖，类不可继承。内部类要访问局部变量，局部变量必须定义成 final 类型

finally 是异常处理语句结构的一部分，表示总是执行。

finalize 是 Object 类的一个方法，在垃圾收集器执行的时候会调用被回收对象的此方法，可以覆盖此方法提供垃圾收集时的其他资源回收，例如关闭文件等。JVM 不保证此方法总被调用

#### 运行时异常与一般异常有何异同？

异常表示程序运行过程中可能出现的非正常状态，运行时异常表示虚拟机的通常操作中可能遇到的异常，是一种常见运行错误。java 编译器要求方法必须声明抛出可能发生的非运行时异常，但是并不要求必须声明抛出未被捕获的运行时异常。

#### error 和 exception 有什么区别?

error 表示恢复不是不可能但很困难的情况下的一种严重问题。比如说内存溢出。不可能指望程序能处理这样的情况。
exception 表示一种设计或实现问题。也就是说，它表示如果程序运行正常，从不会发生的情况。

Java 中的异常处理机制的简单原理和应用。

异常是指 java 程序运行时（非编译）所发生的非正常情况或错误，与现实生活中的事件很相似，现实生活中的事件可以包含事件发生的时间、地点、人物、情节等信息，可以用一个对象来表示，Java 使用面向对象的方式来处理异常，它把程序中发生的每个异常也都分别封装到一个对象来表示的，该对象中包含有异常的信息。

Java 对异常进行了分类，不同类型的异常分别用不同的 Java 类表示，所有异常的根类为 java.lang.Throwable，
Throwable 下面又派生了两个子类：Error 和 Exception，Error 表示应用程序本身无法克服和恢复的一种严重问
题，程序只有死的份了，例如，说内存溢出和线程死锁等系统问题。Exception 表示程序还能够克服和恢复的问题，其中又分为系统异常和普通异常，系统异常是软件本身缺陷所导致的问题，也就是软件开发人员考虑不周所导致的问题，软件使用者无法克服和恢复这种问题，但在这种问题下还可以让软件系统继续运行或者让软件死掉，例如，数组脚本越界 （ ArrayIndexOutOfBoundsException ） ， 空 指 针 异 常 （ NullPointerException ） 、 类 转 换 异 常（ClassCastException）；普通异常是运行环境的变化或异常所导致的问题，是用户能够克服的问题，例如，网络断线，硬盘空间不够，发生这样的异常后，程序不应该死掉。

java 为系统异常和普通异常提供了不同的解决方案，编译器强制普通异常必须 try…catch 处理或用 throws 声明继
续抛给上层调用方法处理，所以普通异常也称为 checked 异常，而系统异常可以处理也可以不处理，所以，编译器不强制用 try…catch 处理或用 throws 声明，所以系统异常也称为 unchecked 异常。

就按照三个级别去思考：虚拟机必须宕机（就是死机）的错误，程序可以死掉也可以不死掉的错误，程序
不应该死掉的错误；

#### JAVA 语言如何进行异常处理，关键字：throws,throw,try,catch,finally 分别代表什么意义？

throws 是获取异常
throw 是抛出异常
try 是将会发生异常的语句括起来，从而进行异常的处理,也可以在 try 块中抛出新的异常
catch 是如果有异常就会执行他里面的语句
finally 不论是否有异常都会进行执行的语句
————————————————
版权声明：本文为CSDN博主「小草d使命」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/MacWx/article/details/90204111