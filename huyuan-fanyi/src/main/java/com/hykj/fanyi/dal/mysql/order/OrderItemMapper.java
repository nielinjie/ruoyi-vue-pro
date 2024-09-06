package com.hykj.fanyi.dal.mysql.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.hykj.fanyi.controller.admin.vo.OrderItemPageReqVO;
import com.hykj.fanyi.dal.dataobject.order.OrderItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 翻译订单项 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderItemMapper extends BaseMapperX<OrderItemDO> {

    default PageResult<OrderItemDO> selectPage(OrderItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderItemDO>()
                .eqIfPresent(OrderItemDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(OrderItemDO::getType, reqVO.getType())
                .eqIfPresent(OrderItemDO::getFiles, reqVO.getFiles())
                .eqIfPresent(OrderItemDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(OrderItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderItemDO::getId));
    }


    List<OrderItemDO> selectListByOrderId(Long orderId);

    void deleteByOrderId(Long orderId);
}