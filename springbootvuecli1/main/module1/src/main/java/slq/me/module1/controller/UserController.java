package slq.me.module1.controller;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpCookie;
import org.springframework.lang.Nullable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import lombok.extern.slf4j.Slf4j;
import slq.me.module1.entity.ErrorType2;
import slq.me.module1.entity.Result;
import slq.me.module1.entity.User;
import slq.me.module1.entity.ValidationGroups;
import slq.me.module1.service.UserService;
import slq.me.module1.util.FileAndPathUtil;
import slq.me.module1.util.ImageVerify;
import slq.me.module1.util.JWTUtils;

@RestController
@RequestMapping("u")
@CrossOrigin // 允许跨域，貌似可以不加
@Slf4j
public class UserController {

    @Autowired
    ImageVerify iv;

    @Autowired
    UserService userService;

    @Value("${page.size}")
    int pagesize;

    @Value("${file.uploadPath}")
    String uploadPath;

    @Value("${capture.name}")
    String captureName;

    @GetMapping("page")
    // 如果设置int page，即使加上@RequestParam(required = false) int page设置可以为空，
    // 如果url是page=&keyword=，会报错""无法转成int
    // 如果url是keyword=，url里不带page参数，会报错null无法转成基本类型，所以需要写成Integer
    // public Result page(@RequestParam(required = false) int page,String keyword) {
    // public Result page(int page,String keyword) {
    public Result page(Integer page, String keyword) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        int page1 = page == null ? 1 : page;
        if (StringUtils.isNotBlank(keyword)) {
            wrapper.like("name", keyword);
        }
        wrapper.orderByDesc("id");
        IPage<User> iPage = userService.page(page1, pagesize, wrapper);
        return new Result(0, null, iPage);
    }

    @GetMapping("capture")
    public void getCapture(HttpServletResponse response, HttpSession session) {
        ImageVerify.ImageCode imageCode = iv.create();
        try {
            ImageIO.write(imageCode.getImage(), "jpg", response.getOutputStream());
            response.setContentType("image/jpeg");
            session.setAttribute(captureName, imageCode.getCode());
            log.error("getcapture is " + imageCode.getCode());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("logout")
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpSession session)
            throws IOException {
        session.setAttribute("user", null);
        response.sendRedirect(request.getContextPath() + "/login.html");
    }

    @GetMapping("oneByid")
    public Result one(Integer id) {
        if (id == null) {
            return new Result(1, "id is null", null);
        }
        User user = userService.one(id);
        return new Result(0, null, user);
    }

    @GetMapping("delete")
    public Result delete(Integer id) {
        if (id == null) {
            return new Result(1, "id is null", null);
        }
        int countr = userService.delete(id);
        if (countr > 0) {
            return new Result(0, null, "删除成功,id=" + id);
        } else {
            return new Result(1, "删除失败,id=" + id, null);
        }
    }

    @PostMapping("insert")
    // @RequestPart里的value需要和FormData的文件数组的key对应
    public Result insert(@Nullable @RequestPart("userfile") MultipartFile[] uploadFiles,
            @Validated(ValidationGroups.InsertGroup.class) User o) {

        // log.error("sss"+result.getAllErrors().size());
        // if (result != null && result.hasErrors()) {
        // List<ObjectError> allErrors = result.getAllErrors();
        // for (ObjectError error : allErrors) {
        // log.error(error.getObjectName() + "----------" + error.getDefaultMessage()+
        // error.getClass());
        // }
        // } else {
        // log.info("用户信息校验成功");
        // }

        if (uploadFiles != null) {
            log.info("insert user" + o + "uploadFiles:" + uploadFiles.length);
            int index = 1;
            for (MultipartFile f : uploadFiles) {
                try {
                    String modulePath = FileAndPathUtil.getModulePath() + uploadPath;
                    String extension = FilenameUtils.getExtension(f.getOriginalFilename());
                    String filename = FileAndPathUtil.getRandomFilename() + "." + extension;
                    File f1 = new File(modulePath, filename);
                    f.transferTo(f1);
                    if (index == 1) {
                        o.setPic1(filename);
                    }
                    //如果只上传第二张图，路径会报错到pic1，不改了
                    if (index == 2) {
                        o.setPic2(filename);
                    }
                    index++;
                } catch (IllegalStateException | IOException e) {
                    e.printStackTrace();
                    return new Result(1, "文件上传失败", null);
                }
            }
        }

        int id = userService.insertReturnId(o);
        if (id != -1) {
            return new Result(0, null, "创建成功,id=" + id);
        } else {
            return new Result(1, "创建失败", null);
        }
    }

    @PostMapping("update")
    public Result update(@Validated(ValidationGroups.InsertGroup.class) @RequestBody User o, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError e : result.getAllErrors()) {
                System.out.println(e);
            }
            return new Result(2, "BindException", result.getAllErrors());
        }

        log.info("update user" + o);
        int count = userService.update(o);
        if (count > 0) {
            return new Result(0, null, "更新成功,id=" + o.getId());
        } else {
            return new Result(1, "更新失败,id=" + o.getId(), null);
        }
    }

    @PostMapping("login")
    public Result login(@Validated(ValidationGroups.LoginGroup.class) @RequestBody User o,
            HttpSession session, HttpServletResponse response) {
        String capture = (String) session.getAttribute(captureName);
        log.error("login session capture is "+capture);
        if (!StringUtils.equals(capture, o.getCapture())) {
            List<ErrorType2> errs = new ArrayList<ErrorType2>();
            errs.add(new ErrorType2("capture", "验证码错误"));
            return new Result(2, "登录失败", errs);
        }
        log.warn("login user" + o);
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("name", o.getName()).eq("password", o.getPass());
        User u = userService.one(wrapper);
        if (u != null) {
            // 保存登录信息到session，二选一即可
            // saveLoginStateWithSession(session, u);
            // return new Result(0, null, "登录成功,id=" + u.getId());

            // 保存登录信息到jwt，二选一即可
            String newToken = JWTUtils.createToken(u.getId()+"");
            // response.setHeader(JWTUtils.USER_LOGIN_TOKEN, newToken);
            JWTUtils.saveToken2Cookie(response, newToken);
            // return new Result(0, null, tokenMap);
            return new Result(0, null, "登录成功");
        } else {
            return new Result(1, "登录失败", null);
        }
    }

    // @PostMapping("loginJWT")
    // public void loginJWT(User o, HttpServletResponse response) {
    //     log.warn("login user" + o);
    //     o.setName("a").setId(1);
    //     saveLoginStateWithJWT(response, o);
    // }

    private void saveLoginStateWithSession(HttpSession session, User u) {
        session.setAttribute("user", u.getId());
    }
}
