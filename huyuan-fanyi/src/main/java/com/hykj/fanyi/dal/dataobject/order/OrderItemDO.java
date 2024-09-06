package com.hykj.fanyi.dal.dataobject.order;

import lombok.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 翻译订单项 DO
 *
 * @author 芋道源码
 */
@TableName("fanyi_order_item")
@KeySequence("fanyi_order_item_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 类型
     */
    private String type;
    /**
     * 文件(json object of url)
     */
    private String files;
    /**
     * 状态
     */
    private Integer status;

}