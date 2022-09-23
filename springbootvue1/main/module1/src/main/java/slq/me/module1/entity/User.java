package slq.me.module1.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain=true)
@TableName("users")
public class User implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    @NotBlank(message = "trim()后，长度必须大于0")
    @Size(min = 2, max = 3, message = "⻓度⾄少2位至多3位")
    // @NotBlank
    // @Size(min = 2, max = 3)
    private String name;

    @NotEmpty(message = "长度必须大于0")
    @TableField("password")
    @Email(message = "需要邮箱格式")
    // @NotEmpty
    // @Email
    private String pass;

    
    @Max(value=3,message = "最大是3")
    @Min(value = 2, message = "最小是2")
    @NotNull(message = "不能是null")
    // @NotNull
    // @Max(value = 3)
    // @Min(value=2)
    private Integer age;

    @TableField("lasttime")
    private Date lastTime;

    private String pic1;
    private String pic2;

    @TableField("gender")
    private Boolean sex;
    
    private List<Integer> hobbys;
    
    @TableField(exist = false)
    private String hobbysStr;
}