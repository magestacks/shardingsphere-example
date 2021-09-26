package cn.acmenlt.shardingsphere.example.core.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Shadow user.
 *
 * @author chen.ma
 * @date 2021/9/26 21:03
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ShadowUser implements Serializable {

    private static final long serialVersionUID = -1631569839675868106L;

    private int userId;

    private String userName;

    private String userNamePlain;

    private String pwd;

    private String assistedQueryPwd;

    private Boolean shadow;

}

