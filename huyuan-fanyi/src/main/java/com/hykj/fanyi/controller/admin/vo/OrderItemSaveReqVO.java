package com.hykj.fanyi.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 翻译订单项新增/修改 Request VO")
@Data
public class OrderItemSaveReqVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26879")
    private Long id;

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10899")
    @NotNull(message = "订单ID不能为空")
    private Long orderId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "类型不能为空")
    private String type;

    @Schema(description = "文件(json object of url)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "文件(json object of url)不能为空")
    private String files;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "状态不能为空")
    private Integer status;

}