package cn.cherryv.cvapi.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.write.style.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ContentRowHeight(15)
@HeadRowHeight(20)
@ColumnWidth(20)
@HeadFontStyle(fontHeightInPoints = 11)
@ContentStyle(fillForegroundColor = 40)public class Stroes {

    @DateTimeFormat("yyyy-MM")
    @ExcelProperty(value = "发放时间")
    private Date createdAt;

    @ExcelProperty(value = "品牌名称")
    private String brandName;

    @ExcelProperty(value = "省份")
    private String province;

    @ExcelProperty(value = "城市")
    private String city;

    @ExcelProperty(value = "母婴店品牌")
    private String brand;

    @ExcelProperty(value = "店铺名称")
    private String storeName;

    @ExcelProperty(value = "销售数量")
    private Integer salesProductCount;

    @ExcelProperty(value = "单店红包占比")
    private String proportion;

    @ExcelProperty(value = "发放金额")
    private BigDecimal money;


}