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
        <el-input v-model="name"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="pass">
        <el-input v-model="pass"></el-input>
      </el-form-item>
      <el-form-item label="验证码" prop="capture">
        <el-input v-model="capture"></el-input>
        <img :src="captureSrc" @click="getCapture" />
      </el-form-item>
    </el-form>
    <el-button type="success" @click="login">登 录 </el-button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      pass: "",
      capture: "",
      captureSrc: "",
      rules: {
        name: [
          { required: true, message: "请输入", trigger: "blur" },
          { min: 2, max: 9, message: "长度2到9", trigger: "blur" },
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
          name: this_.name,
          pass: this_.pass,
          capture: this_.capture,
        })
        .then(function (res) {
          console.log(
            "res.data.data['USER_LOGIN_TOKEN']",
            res.data["data"]["USER_LOGIN_TOKEN"]
          );
          if (res.data.code == 0) {
            //window.localStorage.setItem("USER_LOGIN_TOKEN",res.data["data"]['USER_LOGIN_TOKEN'])
            //document.cookie ="USER_LOGIN_TOKEN="+res.data["data"]['USER_LOGIN_TOKEN']
            //console.log(document.cookie )
            //location.href = "/users.html";
          } else if (res.data.code == 2) {
            // writeErrors(res.data.data, this_.errors);
          }
        });
    },
    getCapture() {
      this.captureSrc = "http://localhost:8080/u/capture?time=" + Date.now();
    },
  },
  created() {
    this.getCapture();
  },
};
</script>

<style>
</style>