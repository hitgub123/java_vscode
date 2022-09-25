package slq.me.module1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import slq.me.module1.entity.User;
import slq.me.module1.mapper.UserMapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> all(QueryWrapper<User> wrapper) {
        return userMapper.selectList(wrapper);
    }

    @Override
    public int delete(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User o) {
        return userMapper.insert(o);
    }

    @Override
    public User one(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User one(QueryWrapper<User> wrapper) {
        return userMapper.selectOne(wrapper);
    }

    @Override
    public IPage<User> page(int cur, int size, QueryWrapper<User> wrapper) {
        Page<User> page = new Page<User>(cur, size); // 第几页，每页显示几条
        System.out.println("page" + cur + ":" + size);
        return userMapper.selectPage(page, wrapper);
    }

    @Override
    public int update(User o) {
        return userMapper.updateById(o);
    }

    @Override
    public int insertReturnId(User o) {
        if (userMapper.insertReturnId(o) > 0) {
            return o.getId();
        } else {
            return -1;
        }
    }
}
