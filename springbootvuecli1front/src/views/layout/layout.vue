<template>
  <div class="layout">
    <!-- 左侧导航区域 -->
    <!-- <menudemoC class="menudemoC" /> -->
    <menuC class="menuC" :collapseFlg="collapseFlg" />
    <!-- 右侧内容区域 -->
    <contentC
      class="contentC"
      :class="{ opened: !collapseFlg }"
      :collapseFlg="collapseFlg"
      @changCollapseFlg1="changCollapseFlg"
    />
  </div>
</template>

<script>
import menuC from "../../components/menu.vue";
import menudemoC from "../../components/menudemo.vue";
import contentC from "../../components/content.vue";
export default {
  components: {
    menuC,
    // menudemoC,
    contentC,
  },
  data() {
    return { collapseFlg: true };
  },
  methods: {
    changCollapseFlg() {
      this.collapseFlg = !this.collapseFlg;
    },
  },
};
</script>

<style scoped>
.layout {
  .menuC,
  .menudemoC {
    min-height: 500px;
    background: #666;
    position: fixed;
    top: 0;
    bottom: 0;
  }

  .contentC.opened {
    margin-left: 200px;
  }
  .contentC:not(.opened) {
    margin-left: 64px;
  }
}
</style>