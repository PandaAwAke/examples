package org.example.boot.mapper;

import org.apache.ibatis.annotations.Param;
import org.example.boot.bean.TUser;

public interface UserMapper {

    /**
     * 每个方法都在 Mapper 文件中有一个 sql 标签对应
     * 可以直接 Alt+Enter 用 MyBatisX 插件生成
     * 所有参数都应该用 @Param 签名，然后在 xml 中用 #{} 取值
     */
    public TUser getUserById(@Param("id") Long id);

}
