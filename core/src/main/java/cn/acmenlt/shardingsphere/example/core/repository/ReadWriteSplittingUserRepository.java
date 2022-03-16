package cn.acmenlt.shardingsphere.example.core.repository;

import cn.acmenlt.shardingsphere.example.core.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * ReadWriteSplitting user repository.
 *
 * @author chen.ma
 * @date 2021/9/26 21:03
 */
@Mapper
public interface ReadWriteSplittingUserRepository extends CommonRepository<UserInfo, Long> {

}
