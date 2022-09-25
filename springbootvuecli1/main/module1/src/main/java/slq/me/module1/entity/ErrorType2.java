package slq.me.module1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
// 前端取BindException的field(定位报错的字段)和defaultMessage(报错信息)展示报错信息，
// 照这个结构自定义一个类使用相同的接口展示报错信息
public class ErrorType2 {
    String field;
    String defaultMessage;

}