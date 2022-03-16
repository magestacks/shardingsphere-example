package cn.acmenlt.shardingsphere.example.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * UserInfo.
 *
 * @author chen.ma
 * @date 2022/3/16 22:00
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfo {

    private int userId;

    private String userName;

    private String pwd;

}
