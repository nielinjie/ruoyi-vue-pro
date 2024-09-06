package com.hykj.fanyi.controller.admin.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 翻译订单项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderItemRespVO {

    @Schema(description = "ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26879")
    @ExcelProperty("ID")
    private Long id;

    @Schema(description = "订单ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10899")
    @ExcelProperty("订单ID")
    private Long orderId;

    @Schema(description = "类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("类型")
    private String type;

    @Schema(description = "文件(json object of url)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("文件(json object of url)")
    private String files;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private Integer status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}