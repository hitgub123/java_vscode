<template style="text-align: center;">
  <div>
    <h1>login</h1>
    <el-form
      ref="loginFormC"
      :model="loginForm"
      :rules="rules"
      label-width="100px"
    >
      <el-form-item label="用户名" prop="name">
        <el-input v-model="loginForm.name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input v-model="loginForm.pass"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="capture">
        <el-input v-model="loginForm.capture"></el-input>
        <img :src="loginForm.captureSrc" @click="getCapture" />
      </el-form-item>
      <input name="captureTime" type="hidden" v-model="loginForm.captureTime" />
    </el-form>
    <el-button type="success" @click="login">登 录 </el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loginForm: {
        name: "",
        pass: "",
        capture: "",
        captureSrc: "",
        captureTime: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入", trigger: "blur" },
          { min: 1, max: 3, message: "长度1到3", trigger: "blur" },
        ],
        pass: [{ required: true, message: "请输入", trigger: "blur" }],
      },
    };
  },
  methods: {
    login() {
      const this_ = this;
      this.$axios
        .post("http://localhost:8080/u/login", {
          name: this_.loginForm.name,
          pass: this_.loginForm.pass,
          capture: this_.loginForm.capture,
          captureTime: this_.loginForm.captureTime,
        })
        .then(function (res) {
          // console.log(res.data["data"]["USER_LOGIN_TOKEN"]);
          if (res.data.code == 0) {
            window.localStorage.setItem(
              "USER_LOGIN_TOKEN",
              res.data["data"]["USER_LOGIN_TOKEN"]
            );
            // console.log(window.localStorage)
            //location.href = "/users.html";
          } else if (res.data.code == 2) {
            // writeErrors(res.data.data, this_.errors);
          }
        });
    },
    getCapture() {
      let captureTime = Date.now();
      this.loginForm.captureTime = captureTime;
      this.loginForm.captureSrc =
        "http://localhost:8080/u/capture?time=" + captureTime;
    },
  },
  created() {
    this.getCapture();
  },
};
</script>

<style>
</style>