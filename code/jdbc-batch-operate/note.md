1. 原生jdbc开启事务：conn.setAutoCommit(false)，即关闭自动提交
2. 提交conn.commit()，回滚conn.rollback()
3. point1 = conn.setSavepoint("point1")；conn.rollback(point1)回退到指定保存点，再提交conn.commit()，只会提交指定保存点之前的更改。和rollback()全部回退不同
4. 批量执行preStatementInsert.addBatch(), 添加完后执行preStatementInsert.executeBatch()