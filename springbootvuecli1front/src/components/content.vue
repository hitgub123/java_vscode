<template>
  <div>
    <div class="header">
      <i
        v-if="collapseFlg"
        class="el-icon-menu"
        @click="changCollapseFlg2()"
      ></i>
      <i
        v-if="!collapseFlg"
        class="el-icon-setting"
        @click="changCollapseFlg2()"
      ></i
      >top
      <langC />
      <i class="el-icon-setting" @click="logout()"></i>
    </div>

    <div class="content">
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import langC from "./common/lang.vue";
export default {
  props: ["collapseFlg"],
  components: { langC },
  methods: {
    changCollapseFlg2() {
      this.$emit("changCollapseFlg1");
    },
    logout() {
      this.$router.push("/");
    },
  },
};
</script>

<style lang='less' scoped>
.header {
  background: skyblue;
  height: 60px;
}
i {
  font-size: 44px;
}
</style>