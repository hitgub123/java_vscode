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
@Accessors(chain = true)
@TableName("users")
public class User implements Serializable {
        @TableId(value = "id", type = IdType.AUTO)
        private Integer id;

        @NotBlank(message = "trim()后长度必须大于0", groups = {
                        ValidationGroups.InsertGroup.class,
                        ValidationGroups.LoginGroup.class })
        @Size(min = 2, max = 3, message = "⻓度⾄少2位至多3位", groups = {
                        ValidationGroups.InsertGroup.class })
        private String name;

        @TableField("password")
        @NotEmpty(message = "长度必须大于0", groups = {
                        ValidationGroups.InsertGroup.class,
                        ValidationGroups.LoginGroup.class })
        @Email(message = "需要邮箱格式", groups = {
                        ValidationGroups.InsertGroup.class })
        private String pass;

        @Max(value = 3, message = "最大是3", groups = {
                        ValidationGroups.InsertGroup.class })
        @Min(value = 2, message = "最小是2", groups = {
                        ValidationGroups.InsertGroup.class })
        @NotNull(message = "不能是null", groups = {
                        ValidationGroups.InsertGroup.class })
        private Integer age;

        @TableField("lasttime")
        private Date lastTime;

        private String pic1;
        private String pic2;

        @TableField("gender")
        private Boolean sex;

        @TableField(exist = false)
        private List<Integer> hobbys;

        @TableField("hobbys")
        private String hobbysStr;

        @TableField(exist = false)
        private String capture;

        @TableField(exist = false)
        private Long captureTime;
}