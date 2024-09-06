package com.hykj.fanyi.dal.mysql.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import com.hykj.fanyi.controller.admin.vo.AppOrderPageReqVO;
import com.hykj.fanyi.dal.dataobject.order.OrderDO;
import org.apache.ibatis.annotations.Mapper;


/**
 * 翻译订单 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface OrderMapper extends BaseMapperX<OrderDO> {

    default PageResult<OrderDO> selectPage(AppOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OrderDO>()
                .eqIfPresent(OrderDO::getCode, reqVO.getCode())
                .likeIfPresent(OrderDO::getName, reqVO.getName())
                .eqIfPresent(OrderDO::getStatus, reqVO.getStatus())
                .eqIfPresent(OrderDO::getUserId, reqVO.getUserId())
                .betweenIfPresent(OrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OrderDO::getId));
    }

}