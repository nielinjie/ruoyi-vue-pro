package com.hykj.fanyi.dal.dataobject.order;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 翻译订单 DO
 *
 * @author 芋道源码
 */
@TableName("fanyi_order")
@KeySequence("fanyi_order_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 订单编号
     */
    private String code;
    /**
     * 名字
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 用户ID
     */
    private Long userId;

}