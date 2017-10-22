# Trade API规范

注 : API的URL直接从`/***`开始，前面的`http://***.***.***.***:**/`等省略

---
注册登录
  - 注册 /user/register
  - 登录 /user/login
  - 登出 /user/logout
---
发布商品、事件
  - 发布商品 /commodity/release
    - 所需数据（列出所需要的数据）
      - user_id(用户id)
      - 上面是例子，补全
	- 返回结果
	  - 成功 ...
	  - 失败 ...
	  - 其他 ...
  - 发布事件 /delegation/release
---
查看 + 评论商品、事件
- 查看... /\*/select
  - 数据（\*类别`category`、数量`count`[可选]）
  - 类型
  - 结果（列表，每组10（或count
  ）个，再请求返回接下来的10（或count）个）
- 评论... /\*/review
  - 数据（\*`id`、评论信息`review`）
  - 结果（评论成功标志）
