package cn.acmenlt.shardingsphere.example.core.repository;

import cn.acmenlt.shardingsphere.example.core.entity.ShadowUser;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shadow user repository.
 *
 * @author chen.ma
 * @date 2021/9/26 21:03
 */
@Mapper
public interface ShadowUserRepository extends CommonRepository<ShadowUser, Long> {

    /**
     * Select all, Including shadow and non shadow.
     *
     * @return
     * @throws SQLException
     */
    @Override
    default List<ShadowUser> selectAll() throws SQLException {
        List<ShadowUser> result = new ArrayList();
        result.addAll(selectAllByShadow(true));
        result.addAll(selectAllByShadow(false));
        return result;
    }

    /**
     * Select according to shadow identification.
     *
     * @param shadow
     * @return
     * @throws SQLException
     */
    List<ShadowUser> selectAllByShadow(boolean shadow) throws SQLException;

}
