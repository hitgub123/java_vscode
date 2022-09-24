

    简介：
        使用技术：vscode,sprinboot,mybatisplus,springmvc,postgresql,vue,jwt,axios
        springboot多模块，前后端分离，多文件上传，后端数据校验

    vscode springboot maven 多模块项目搭建
        1，创建主模块
            1-1，安装spring initializr java support插件( quick start Spring Boot java project)
            1-2，f1搜索spring，点击spring initializr:create a maven project
            1-3，选择springboot version(2.73)，选择语言(java)，输入group id(slq.me)，输入项目名(main，建议改个名字)，
            1-4，选择打包方式(jar)，选择java version(8)，选择dependencies(spring web,lombok)，
            1-5，选择项目路径(/springbootvue1)生成项目(/springbootvue1/main)。至此主模块生成完毕
        2，创建子模块
            2-1，VScode的explorer里选择主模块(main)后,重复1-2到1-4，其中1-3的项目名(module1)，
            2-2，选择主模块路径(/springbootvue1/main)生成子模块项目(/springbootvue1/main/module1)。至此子模块生成完毕
        3，修改主模块
            3-1，修改pom文件
                3-1-1，	<name>同级别区域添加<packaging>pom</packaging>
                3-1-2，	<name>同级别区域添加<modules><module>module1</module></modules>，这里module1是子模块的名字
            3-2，可以删除src文件夹
        4，修改子模块
            4-1，修改pom文件
                4-1-1，	<parent>里<groupId>,<artifactId>和<version>设置成主模块一样
                4-1-2，	<parent>里<relativePath/>改成<relativePath>..</relativePath>，表示取上一级文件夹找父项目的pom.xml
                4-1-3，	删除主模块pom重复的部分，如<properties><java.version>8</java.version></properties>,
                        <plugins>和所有<dependency>
            4-2，重启vscode，运行module1的Module1Application可启动项目
            4-3，可以下载spring boot dashboard插件，方便管理

    启动项目时报错Cannot load driver class: org.postgresql.Driver，
        这个在主模块的pom里添加org.postgresql的依赖，右键reload project

    maven报错Missing artifact hikari-cp:hikari-cp:jar:2.13.0和
    The container 'Maven Dependencies' references non existing library 'C:\Users****hikari-cp-2.13.0.jar'
        发现该路径下没有hikari-cp-2.13.0.jar，运行mvn install报错
        Could not resolve dependencies for project  https://repo.maven.apache.org/maven2*****
            修改C:\Users\81802\.m2\settings.xml，如果没有从maven安装目录\conf里复制一个，
            <mirros>里最前面加上  	 
                <mirror>
                    <id>nexus-aliyun</id>
                    <mirrorOf>*</mirrorOf>
                    <name>Nexus aliyun</name>
                    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
                </mirror> 
            后，重新mvn intall即可。（mvn intall需要在有pom文件的路径下cmd执行）
            下载后很多文件后依旧报错Missing artifact hikari-cp:hikari-cp:jar:2.13.0等，
            把版本改成2.6.0。估计是新版本没有被收录

    lombok常用注解：
        @Data：@getter + @setter + @tostirng + @hashcode + @equals
        @AllArgsConstructor： 全参数构造器
        @NoArgsConstructor： 无参构造器
        @Accessors(chain=true) ： 链式，可这样user.setAge(666).setName("fff")

    mybatisplus：
        entity：
            类名上加@TableName("users")，表示将此类和哪张表关联。非必须
            类名上加@Accessors(chain=true)，可以使用链式写法，user.setAge(666).setName("fff");
            属性名上加@TableField("age")，表示将此属性与表的哪个字段关联
            属性名上加@TableField(exist = false)，表示该属性不为数据库表字段，如果表里没有此字段必须添加
            在表的自增主键对应的属性上加@TableId(value = "id", type = IdType.AUTO)，
                否则mybatisplus在插入数据时候会自动生成一个UUID
        mapper:
            如下加上@Mapper注解和继承BaseMapper<User>接口，默认实现了有很多增删改查和分页的功能
                @Mapper
                public interface UserMapper extends BaseMapper<User> {}
            想写其他的sql，需要在resources下与Mapper.java同路径的文件夹里创建Mapper.xml，
                比如userMapper.java在src\main\java\slq\me\module1\mapper，
                UserMapper.xml就创建在src\main\resources\slq\me\module1\mapper。
            如果在application.properties里配置mybatis-plus.type-aliases-package=slq.me.module1.entity，
                UserMapper.xml里slq.me.module1.entity.User就可以使用别名User。

            
    mybatis特殊符号转义：
        想拼接 select * from users where age >= #{age}，
        ok：<![CDATA[   select * from users where age >= #{age}    ]]>
        ok：select * from users where age <![CDATA[>=]]> #{age}
        ng：select * from users where age <![CDATA[>]]>= #{age}

    mybatisplus使用selectPage后未分页，需要加上如下配置类：
        @EnableTransactionManagement
        @Configuration
        public class MybatisPlusConfig {
            @Bean
            public MybatisPlusInterceptor mybatisPlusInterceptor() {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
            return interceptor;
            }
        }

    setting.json里加上"editor.codeActionsOnSave": { "source.organizeImports": true }，
    按ctrl+s后删除未使用到的import、多import归类整理、排序等

    springmvc:
        获取ajax的post请求的参数需要加@RequestBody，否则取不到
            @PostMapping("update")
            public Result update(@RequestBody User o) 
        获取的ajax multipart/form-data类型的post请求的参数不加，加了反而报错
        
        // 如果设置int page，即使加上@RequestParam(required = false) int page设置可以为空，
        // 如果url是page=&keyword=，会报错""无法转成int
        // 如果url是keyword=，url里不带page参数，会报错null无法转成基本类型，所以需要写成Integer
        // public Result page(@RequestParam(required = false) int page,String keyword) 
        // public Result page(int page,String keyword) 
        @GetMapping("page")
        public Result page(Integer page, String keyword) 

        处理ajax多/单文件上传
            前端html：
                <form>
                    文件1<input type='file' name='file11' ref='file1'/><br />
                    文件2<input type='file' name='file22' ref='file2'/><br />
                </form>
            前端js：
                let params = new FormData();
                let fileSelectDom1 =this.$refs.file1
                let fileSelectDom2 =this.$refs.file2     
                const config = {
                    headers: { "Content-Type": "multipart/form-data"}
                }
                params.append('userfile', fileSelectDom1.files[0])        
                params.append('userfile', fileSelectDom2.files[0])
                params.append('name', this.user.name)
                axios.post("/u/insert", params,config)
                .then(function (res) {
                    if (res.data.code == 0) {
                        location.href = "/u/users"
                    }
                });
            后端：
                @PostMapping("insert")     
                //@RequestPart里的value需要和FormData的文件数组的key对应
                public Result insert(@Nullable @RequestPart("userfile") MultipartFile[] uploadFiles, User o) {
                    for (MultipartFile f : uploadFiles) {
                        try {
                            String modulePath = FileAndPathUtil.getModulePath() + uploadPath;
                            String extension = FilenameUtils.getExtension(f.getOriginalFilename());
                            String filename = FileAndPathUtil.getRandomFilename()+ "." + extension;
                            File f1 = new File(modulePath, filename);
                            f.transferTo(f1);   
                        } catch (IllegalStateException | IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

        数据校验（仅对绑定到对象的数据）：
            1，对被绑定的对象加上各种校验规则，参考slq\me\module1\entity\User.java，修改后可能要mvn clean install后才生效
            2，在controoler里，要校验的方法的该对象前加上@Validated，参考slq\me\module1\controller\UserController.java
            3，对校验异常进行处理，有两种方法：
                a，全局异常处理，参考slq\me\module1\controller\GlobalExceptionHandler.java
                    a-1，数据校验失败报错BindException，看BindException的处理方法即可
                    a-2，BindException的处理方法在接收到BindException后返回json对象，controller内部的代码不会被执行
                    a-3，UserController.java里insert方法使用的是全局异常处理，注意方法形参里不能含有BindingResult，
                    否则不会被GlobalExceptionHandler处理
                    a-4，前端拿到BindException后返回json对象后，分字段显示对应的所有错误，参考user.html
                b，单个异常处理，slq\me\module1\controller\UserController.java的upate方法
                    b-1，update方法形参里必须含有BindingResult，
                    result.hasErrors()判断是否也error，result.getAllErrors()获取所有error
                    b-2，也返回json对象，后续前端的处理后全局异常处理一模一样
                不管用哪个异常处理，接收异常后必须return，否则之后的controller代码会继续执行
        
        数据分组校验（往db插入或更新user时需要在form输入很多字段，login时form只填写user和pass，校验规则不同，所以需要进行分组校验）：
            1，创建groups的空接口，如public class ValidationGroups {public interface Required {}}
            2，在有校验差异需求的规则里加上groups，如 @Email(message = "需要邮箱格式", groups = {ValidationGroups.Required.class })
            3，在controller里@Validated注解里加groups,
                如public Result update(@Validated(ValidationGroups.Required.class) @RequestBody User o) ,此时@Email对此User生效。
            4，一个组的校验规则，对其他组不生效
            5，如果某个校验规则没有groups属性，那么它对controller里@Validated含有groups属性的表单都不生效
            6，如果某个controller里@Validated不含groups，那么@Validated含有groups属性的校验规则对它都不生效

        拦截器：
            1，配置springboot扫描拦截器，参考slq\me\module1\config\WebConfig.java
                注意：要写addPathPatterns("/**")，不要写addPathPatterns("/*")，否则/u/delete这样的url不会被拦截
            2，配置拦截器，参考slq\me\module1\interceptor\LoginInterceptor.java
        拦截器实现使用session登录：
            1，在LoginInterceptor.java的preHandle里使用preHandleInnerWithSession方法，
            检查被拦截的请求的session里是否有user，如果没有就跳转login
            2，login登录成功后，往session里设置user
            3，访问任意页面session里都会携带user
        拦截器实现使用jwt登录：
            token保存在客户端，不在服务器端，不管服务器怎么重启都不会失效
            1，pom引用com.auth0.java-jwt
            2，使用jwt生成/校验token，检查是否快过期，参考slq\me\module1\util\JWTUtils.java
            3，在LoginInterceptor.java的preHandle里使用preHandleInnerWithJWT方法，
            检查被拦截的请求的cookie里是否有token，是否过期，能否通过检验，如果不能就跳转login
            4，login登录成功后，往cookie里设置token，注意设置cookie路径为根路径（"/"）
            5，访问任意页面cookie里都会自动携带token
            网上参考别人的方法是把token放在响应头里返回前端，之后保存在localstorage里，
            访问其他页面时读取localstorage再手动加到请求头，后台的拦截器从请求头读取token进行校验。
            自己有些页面时js的location.href跳转，不知道怎么设置请求头，所以把token放在cookie里。

    vuecli+elementui 
