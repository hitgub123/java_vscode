<template>
  <div>
    <el-menu
      :uniqueOpened="true"
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      router
      :collapse="collapseFlg"
    >
      <el-menu-item index="/demo">
        <i class="el-icon-menu"></i>
        <template #title>首页</template>
      </el-menu-item>
      <el-menu-item index="/demo/goods">
        <i class="el-icon-document"></i>
        <template #title>商品</template>
      </el-menu-item>
      <el-menu-item index="/demo/params">
        <i class="el-icon-setting"></i>
        <template #title>规格</template>
      </el-menu-item>
      <el-menu-item index="/demo/ads">
        <i class="el-icon-setting"></i>
        <template #title>广告</template>
      </el-menu-item>
      <el-menu-item index="/demo/orders">
        <i class="el-icon-setting"></i>
        <template #title>订单</template>
      </el-menu-item>
      <el-submenu index="/demo/diy">
        <template #title>
          <i class="el-icon-location"></i>
          <span>DIY Center</span>
        </template>
        <el-menu-item-group>
          <el-menu-item index="/demo/diy/diymusic"
            ><i class="el-icon-setting"></i><span>DIY MUSIC</span></el-menu-item
          >
          <el-menu-item index="/demo/diy/diymanga"
            ><i class="el-icon-setting"></i><span>DIY MANGA</span></el-menu-item
          >
          <el-menu-item index="/demo/diy/diygame"
            ><i class="el-icon-setting"></i><span>DIY GAME</span></el-menu-item
          >
        </el-menu-item-group>
      </el-submenu>
    </el-menu>
  </div>
</template>

<script lang="ts">
export default {
  props: ["collapseFlg"],
  methods: {
    handleOpen(key: any, keyPath: any) {
      console.log("open");
    },
    handleClose(key: any, keyPath: any) {
      console.log("close");
    },
  },
};
</script>

<style scoped>
.el-menu:not(.el-menu--collapse) {
  width: 200px;
}
</style>