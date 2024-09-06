package com.hykj.fanyi.controller.admin;

import com.hykj.fanyi.controller.admin.vo.AppOrderPageReqVO;
import com.hykj.fanyi.controller.admin.vo.AppOrderRespVO;
import com.hykj.fanyi.controller.admin.vo.AppOrderSaveReqVO;
import com.hykj.fanyi.dal.dataobject.order.OrderDO;
import com.hykj.fanyi.dal.dataobject.order.OrderItemDO;

import com.hykj.fanyi.service.OrderService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;


@Tag(name = "用户 APP - 翻译订单")
@RestController
@RequestMapping("/fanyi/order")
@Validated
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "创建翻译订单")
    public CommonResult<Long> createOrder(@Valid @RequestBody AppOrderSaveReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新翻译订单")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody AppOrderSaveReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除翻译订单")
    @Parameter(name = "id", description = "编号", required = true)
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得翻译订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    public CommonResult<AppOrderRespVO> getOrder(@RequestParam("id") Long id) {
        OrderDO order = orderService.getOrder(id);
        return success(BeanUtils.toBean(order, AppOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得翻译订单分页")
    public CommonResult<PageResult<AppOrderRespVO>> getOrderPage(@Valid AppOrderPageReqVO pageReqVO) {
        PageResult<OrderDO> pageResult = orderService.getOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AppOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出翻译订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderExcel(@Valid AppOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderDO> list = orderService.getOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "翻译订单.xls", "数据", AppOrderRespVO.class,
                        BeanUtils.toBean(list, AppOrderRespVO.class));
    }

    // ==================== 子表（翻译订单项） ====================

    @GetMapping("/order-item/list-by-order-id")
    @Operation(summary = "获得翻译订单项列表")
    @Parameter(name = "orderId", description = "订单ID")
    public CommonResult<List<OrderItemDO>> getOrderItemListByOrderId(@RequestParam("orderId") Long orderId) {
        return success(orderService.getOrderItemListByOrderId(orderId));
    }

}