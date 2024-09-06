package com.hykj.fanyi.service;

import java.util.*;

import com.hykj.fanyi.controller.admin.vo.AppOrderPageReqVO;
import com.hykj.fanyi.controller.admin.vo.AppOrderSaveReqVO;
import com.hykj.fanyi.dal.dataobject.order.OrderDO;
import com.hykj.fanyi.dal.dataobject.order.OrderItemDO;
import jakarta.validation.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 翻译订单 Service 接口
 *
 * @author 芋道源码
 */
public interface OrderService {

    /**
     * 创建翻译订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid AppOrderSaveReqVO createReqVO);

    /**
     * 更新翻译订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid AppOrderSaveReqVO updateReqVO);

    /**
     * 删除翻译订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得翻译订单
     *
     * @param id 编号
     * @return 翻译订单
     */
    OrderDO getOrder(Long id);

    /**
     * 获得翻译订单分页
     *
     * @param pageReqVO 分页查询
     * @return 翻译订单分页
     */
    PageResult<OrderDO> getOrderPage(AppOrderPageReqVO pageReqVO);

    // ==================== 子表（翻译订单项） ====================

    /**
     * 获得翻译订单项列表
     *
     * @param orderId 订单ID
     * @return 翻译订单项列表
     */
    List<OrderItemDO> getOrderItemListByOrderId(Long orderId);

}