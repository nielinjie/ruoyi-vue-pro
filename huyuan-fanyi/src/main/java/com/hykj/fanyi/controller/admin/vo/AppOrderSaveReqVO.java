package com.hykj.fanyi.controller.admin.vo;

import com.hykj.fanyi.dal.dataobject.order.OrderItemDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "用户 APP - 翻译订单新增/修改 Request VO")
@Data
public class AppOrderSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "72")
    private Long id;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "订单编号不能为空")
    private String code;

    @Schema(description = "名字", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "名字不能为空")
    private String name;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17330")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "翻译订单项列表")
    private List<OrderItemDO> orderItems;

}