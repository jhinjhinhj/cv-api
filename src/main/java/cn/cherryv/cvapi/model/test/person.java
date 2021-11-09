package cn.cherryv.cvapi.model.test;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class person {

    @ExcelProperty(value = "xingming")
    private String name;

    @ExcelProperty(value = "nianling")
    private String age;

    @ExcelProperty(value = "dizhi")
    private String address;

}
