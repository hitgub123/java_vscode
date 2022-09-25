package slq.me.module1;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import slq.me.module1.entity.User;
import slq.me.module1.mapper.UserMapper;
import slq.me.module1.service.UserService;
import slq.me.module1.util.JWTUtils;

@SpringBootTest
class Module1ApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserService userService;

	// @Autowired
	// ImageVerify iv;

	@Test
	public void t1() {
		User user = userMapper.selectById(71);
		System.out.println(user);

	}

	@Test
	public void t2() {
		// List<User> users = userMapper.selectList(null);
		// List<User> users = userMapper.selectUserAgeGreaterThan(5555554);
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.like("name", "2").between("age", 0, 100).orderByDesc("age").orderByAsc("id").isNotNull("lasttime");
		// queryWrapper.select("name", "age","id","lasttime","pic1","password");
		List<User> users = userMapper.selectList(queryWrapper);
		// users.forEach(u->System.out.println(u));
		users.forEach(System.out::println);
	}

	@Test
	public void t3() {
		QueryWrapper<User> wrapper = new QueryWrapper<>();
		wrapper.like("name", "2").orderByDesc("id");
		// userService.all(wrapper).forEach(System.out::println);
		// IPage<User> users = userService.page(3, 2, wrapper);
		// System.out.printf("\nuser:%s.%s.%s.%s.%d\n", users.getPages(),
		// users.offset(), users.getCurrent(),users.getSize(), users.getTotal());
		// System.out.println( userService.one(66));
		// System.out.println(userService.one(wrapper.eq("id", 66)));
		// System.out.println(userService.delete(125));
		// System.out.println(userMapper.deleteBatchIds(Arrays.asList(123,124,125)));

		// System.out.println(userService.update(new
		// User().setName("").setAge(null).setId(3)));
		// System.out.println(userMapper.update(null,new
		// LambdaUpdateWrapper<User>().set(User::getAge,null ).eq(User::getId, 3)));

		// System.out.println(userService.insert(new
		// User().setName("QQ").setAge(77).setId(3)));
		// System.out.println(userService.insert(new User().setName("QQ").setAge(77)));
		User o1 = new User().setName("QQ22").setId(1);
		System.out.printf("\n插入%s条数据，id是%s\n", userMapper.insertReturnId(o1), o1.getId());

	}

	@Test
	public void testJWT() {
		String s = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI4NyIsImV4cCI6MTY2NDAxMzY4OX0.mTjpbE5y6FBjrdf_pw5Uk5l-exvjIEMXGbASGPmvslUgP53ywBgrC-yQu1g2UCXO1Hy9iTWK_-FOdBRtKLX0UW";
		// String x=JWTUtils.validateToken(s);
		// System.out.println(x);
	}

}