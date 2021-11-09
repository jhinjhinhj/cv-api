package cn.cherryv.cvapi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(20)
@HeadFontStyle(fontHeightInPoints = 11)
@ContentStyle(fillForegroundColor = 40)public class Intercept {

    @ExcelProperty("拦截次数")
    Integer lanjieNum;

    @ExcelProperty("拦截销量")
    Integer lanJieCiaoLiang;

    @ExcelProperty(value = "拦截类型")
    private String InterceptType;

    @ExcelProperty(value = "流程编号")
    private String processId;

    @ExcelProperty(value = "申报时间")
    private Date ticket;

    @ExcelProperty(value = "省份")
    private String province;

    @ExcelProperty(value = "城市")
    private String city;

    @ExcelProperty(value = "母婴店品牌")
    private String brand;

    @ExcelProperty(value = "店铺名称")
    private String storeName;

    @ExcelProperty(value = "导购员姓名")
    private String salesManName;

    @ExcelProperty(value = "导购员手机号")
    private String salesManPhone;

    @ExcelProperty(value = "申报品牌")
    private String brandName;

    @ExcelProperty(value = "申报产品")
    private String productName;

    @ExcelProperty(value = "申报数量")
    private String declareNum;

    @ExcelProperty(value = "退回原因")
    private String sendBackResult;

    @ExcelProperty(value = "小票照片")
    private String xiaoPiaoimg;
}
