package cn.acmenlt.shardingsphere.example.core.repository;

import cn.acmenlt.shardingsphere.example.core.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Error test repository.
 *
 * @author chen.ma
 * @date 2022/3/24 08:05
 */
@Mapper
public interface ErrorTestRepository {
    
    @Select("select over from t_user")
    UserInfo testError();
    
}
