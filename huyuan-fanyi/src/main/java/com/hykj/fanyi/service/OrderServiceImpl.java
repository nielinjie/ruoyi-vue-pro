package com.hykj.fanyi.service;

import com.hykj.fanyi.controller.admin.vo.AppOrderPageReqVO;
import com.hykj.fanyi.controller.admin.vo.AppOrderSaveReqVO;
import com.hykj.fanyi.dal.dataobject.order.OrderDO;
import com.hykj.fanyi.dal.dataobject.order.OrderItemDO;
import com.hykj.fanyi.dal.mysql.order.OrderItemMapper;
import com.hykj.fanyi.dal.mysql.order.OrderMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static com.hykj.fanyi.ErrorCodeConstants.ORDER_NOT_EXISTS;

/**
 * 翻译订单 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderItemMapper orderItemMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(AppOrderSaveReqVO createReqVO) {
        // 插入
        OrderDO order = BeanUtils.toBean(createReqVO, OrderDO.class);
        orderMapper.insert(order);

        // 插入子表
        createOrderItemList(order.getId(), createReqVO.getOrderItems());
        // 返回
        return order.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(AppOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateOrderExists(updateReqVO.getId());
        // 更新
        OrderDO updateObj = BeanUtils.toBean(updateReqVO, OrderDO.class);
        orderMapper.updateById(updateObj);

        // 更新子表
        updateOrderItemList(updateReqVO.getId(), updateReqVO.getOrderItems());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOrder(Long id) {
        // 校验存在
        validateOrderExists(id);
        // 删除
        orderMapper.deleteById(id);

        // 删除子表
        deleteOrderItemByOrderId(id);
    }

    private void validateOrderExists(Long id) {
        if (orderMapper.selectById(id) == null) {
            throw exception(ORDER_NOT_EXISTS);
        }
    }

    @Override
    public OrderDO getOrder(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public PageResult<OrderDO> getOrderPage(AppOrderPageReqVO pageReqVO) {
        return orderMapper.selectPage(pageReqVO);
    }

    // ==================== 子表（翻译订单项） ====================

    @Override
    public List<OrderItemDO> getOrderItemListByOrderId(Long orderId) {
        return orderItemMapper.selectListByOrderId(orderId);
    }

    private void createOrderItemList(Long orderId, List<OrderItemDO> list) {
        list.forEach(o -> o.setOrderId(orderId));
        orderItemMapper.insertBatch(list);
    }

    private void updateOrderItemList(Long orderId, List<OrderItemDO> list) {
        deleteOrderItemByOrderId(orderId);
		list.forEach(o -> o.setId(null).setUpdater(null).setUpdateTime(null)); // 解决更新情况下：1）id 冲突；2）updateTime 不更新
        createOrderItemList(orderId, list);
    }

    private void deleteOrderItemByOrderId(Long orderId) {
        orderItemMapper.deleteByOrderId(orderId);
    }

}