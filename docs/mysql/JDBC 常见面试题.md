### JDBC 常见面试题

#### 1.JDBC操作数据库的步骤 ？

​	1.注册数据库驱动。
​	2.建立数据库连接。
​	3.创建一个Statement。
​	4.执行SQL语句。
​	5.处理结果集。
​	6.关闭数据库连接。

#### 2.JDBC中的Statement 和PreparedStatement的区别？

​	1.PreparedStatement是预编译的SQL语句，效率高于Statement。
​	2.PreparedStatement支持?操作符，相对于Statement更加灵活。
​	3.PreparedStatement可以防止SQL注入，安全性高于Statement。

#### 3.JDBC中大数据量的分页解决方法？

最好的办法是利用sql语句进行分页，这样每次查询出的结果集中就只包含某页的数据内容。
sql语句分页，不同的数据库下的分页方案各不一样，下面是主流的三种数据库的分页sql：
oracle:

```mysql
select * from
(select *,rownum as tempid from student ) t
where t.tempid between ” + pageSize*(pageNumber-1) + ” and ” + pageSize*pageNumber
```

mysql:

```mysql
select * from students limit ” + pageSize*(pageNumber-1) + “,” + pageSize;
```

sql server:

```mysql
select top ” + pageSize + ” * from students where id not in +
(select top ” + pageSize * (pageNumber-1) + id from students order by id) +
“order by id;
```

#### 4.说说数据库连接池工作原理和实现方案？

工作原理：JAVA EE服务器启动时会建立一定数量的池连接，并一直维持不少于此数目的池连接。客户端程序需要连接时，池驱动程序会返回一个未使用的池连接并将其表记为忙。如果当前没有空闲连接，池驱动程序就新建一定数量的连接，新建连接的数量有配置参数决定。当使用的池连接调用完成后，池驱动程序将此连接表记为空闲，其他调用就可以使用这个连接。
实现方案：返回的Connection是原始Connection的代理，代理Connection的close方法，当调用close方法时，不是真正关连接，而是把它代理的Connection对象放回到连接池中，等待下一次重复利用。
————————————————
版权声明：本文为CSDN博主「黄星辰」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/huangxingchen123/article/details/52698624