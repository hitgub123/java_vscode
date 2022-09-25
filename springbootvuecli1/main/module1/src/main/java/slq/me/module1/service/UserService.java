package slq.me.module1.service;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import slq.me.module1.entity.User;

public interface UserService {
    public User one(int id);
    public User one(QueryWrapper<User> wrapper );
    public int update(User o);
    public int insert(User o);
    public int delete(int id);
    public List<User> all(QueryWrapper<User> wrapper);
    public IPage<User> page(int cur, int size,QueryWrapper<User> wrapper);
    public int insertReturnId(User o);
}
