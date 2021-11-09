package cn.cherryv.cvapi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(20)
@HeadFontStyle(fontHeightInPoints = 11)
@ContentStyle(fillForegroundColor = 40)@Data
public class Mingxi {

    @ExcelProperty(value = "活动名称")
    private String name;

    @ExcelProperty(value = "流程编号")
    private String processId;

    @ExcelProperty(value = "CUID")
    private String cuid;

    @ExcelProperty(value = "销量申报时间")
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

    @ExcelProperty(value = "导购员身份证号")
    private String identityCard;

    @ExcelProperty(value = "产品品牌")
    private String brandName;

    @ExcelProperty(value = "型号")
    private String productName;

    @ExcelProperty(value = "销售数量")
    private Integer salesProductCount;

    @ExcelProperty(value = "红利金额")
    private Integer bonus;

    @TableField(exist = false)
    @ExcelProperty(value = "信息服务费")
    private Integer infoMoney;

    @TableField(exist = false)
    @ExcelProperty(value = "备注")
    private String detail;

    @DateTimeFormat("yyyy-MM-dd")
    @ExcelProperty(value = "发放时间")
    private Date createdAt;

}
