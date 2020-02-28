### JDBC是什么?

JDBC API是一个Java API，可以访问任何类型表列数据，特别是存储在关系数据库中的数据。JDBC代表Java数据库连接。

JDBC库中所包含的API通常与数据库使用于：

- 连接到数据库
- 创建SQL或MySQL语句
- 在数据库中执行SQL或MySQL查询
- 查看和修改数据库中的数据记录

### 创建JDBC应用程序

建立一个JDBC应用程序，本教程中以Java连接MySQL为一个示例，分六个步骤进行：

#### 1. 导入包

在程序中包含数据库编程所需的JDBC类。大多数情况下，使用 `import java.sql.*` 就足够了，如下所示：

```java
//STEP 1. Import required packages
import java.sql.*;
Java
```

#### 2. 注册JDBC驱动程序

需要初始化驱动程序，这样就可以打开与数据库的通信。以下是代码片段实现这一目标：

```java
//STEP 2: Register JDBC driver
Class.forName("com.mysql.jdbc.Driver");
Java
```

#### 3. 打开一个连接

使用`DriverManager.getConnection()`方法来创建一个`Connection`对象，它代表一个数据库的物理连接，如下所示：

```java
//STEP 3: Open a connection
//  Database credentials
static final String USER = "root";
static final String PASS = "pwd123456";
System.out.println("Connecting to database...");
conn = DriverManager.getConnection(DB_URL,USER,PASS);
Java
```

#### 4. 执行一个查询

需要使用一个类型为`Statement`或`PreparedStatement`的对象，并提交一个SQL语句到数据库执行查询。如下：

```java
//STEP 4: Execute a query
System.out.println("Creating statement...");
stmt = conn.createStatement();
String sql;
sql = "SELECT id, first, last, age FROM Employees";
ResultSet rs = stmt.executeQuery(sql);
Java
```

如果要执行一个SQL语句：`UPDATE`，`INSERT`或`DELETE`语句，那么需要下面的代码片段：

```java
//STEP 4: Execute a query
System.out.println("Creating statement...");
stmt = conn.createStatement();
String sql;
sql = "DELETE FROM Employees";
ResultSet rs = stmt.executeUpdate(sql);
Java
```

#### 5. 从结果集中提取数据

这一步中演示如何从数据库中获取查询结果的数据。可以使用适当的`ResultSet.getXXX()`方法来检索的数据结果如下：

```java
//STEP 5: Extract data from result set
while(rs.next()){
    //Retrieve by column name
    int id  = rs.getInt("id");
    int age = rs.getInt("age");
    String first = rs.getString("first");
    String last = rs.getString("last");

    //Display values
    System.out.print("ID: " + id);
    System.out.print(", Age: " + age);
    System.out.print(", First: " + first);
    System.out.println(", Last: " + last);
}
Java
```

#### 6. 清理环境资源

在使用JDBC与数据交互操作数据库中的数据后，应该明确地关闭所有的数据库资源以减少资源的浪费，对依赖于JVM的垃圾收集如下：

```java
//STEP 6: Clean-up environment
rs.close();
stmt.close();
conn.close()
```

//原文出自【易百教程】，商业转载请联系作者获得授权，非商业转载请保留原文链接：https://www.yiibai.com/jdbc/jdbc_quick_guide.html 

#### 示例代码

```java
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: jdbc
 * @author: Mr.
 * @create: 2020-02-28 14:01
 **/
public class JdbcDemo {
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/mybatis-test?serverTimezone=UTC";
  static final String USER = "root";
  static final String PASSWORD = "123456";

  public static void main(String[] args) {
    Connection conn = null;
    PreparedStatement stmt = null;
    List<User> userList = new ArrayList<>();

    try {
      // 1、注册mysql驱动
      Class.forName(JDBC_DRIVER);

      // 2、获取一个连接
      System.out.println("Connecting to database...");
      conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

      // 3、创建一个查询
      System.out.println("Creating statement...");
      stmt = conn.prepareStatement("select * from user where name = ?");
      System.out.println(stmt.toString());// 打印sql
      stmt.setString(1,"张三");
      System.out.println("1:张三");// 打印参数

//      stmt.execute();
//      ResultSet rs = stmt.getResultSet();
      ResultSet rs = stmt.executeQuery();

      // 4、遍历数据集
      while (rs.next()){
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setAge(rs.getInt("age"));
        user.setName(rs.getString("name"));

        System.out.println(user.toString());
        userList.add(user);
      }

      // 5、关闭连接
      rs.close();
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }finally{
      //finally block used to close resources
      try{
        if(stmt!=null)
          stmt.close();
      }catch(SQLException se2){
      }// nothing we can do
      try{
        if(conn!=null)
          conn.close();
      }catch(SQLException se){
        se.printStackTrace();
      }//end finally try
    }//end try
    System.out.println("There are so thing wrong!");
  }
}

```