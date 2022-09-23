package slq.me.module1.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import slq.me.module1.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectUserAgeGreaterThan(Integer age);

    IPage<User> selectUserCity(Page<?> page);

    IPage<User> selectUserCity(Page<?> page, Integer age);

    int insertReturnId(User o);
}