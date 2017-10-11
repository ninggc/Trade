# Trade API规范

注 : API的URL直接从`/***`开始，前面的`http://***.***.***.***:**/`等省略

- 注册登录
  - 注册 /user/register
  - 登录 /user/login
  - 登出 /user/logout
- 发布 + 收藏商品、事件
  - 发布商品 /commodity/release
    - 数据(所需要的数据)
  - 发布事件 /delegation/release
  - 收藏 /\*/collect  ---------------------------------注·`*`代替`commodity`、`delegation`
