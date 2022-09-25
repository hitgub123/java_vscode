<template>
  <el-dropdown @command="selectLang">
    <el-button type="primary">
      VUE国际化<el-icon class="el-icon--right"><arrow-down /></el-icon>
    </el-button>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item command="en">英文</el-dropdown-item>
        <el-dropdown-item command="jp">日文</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
  <el-dropdown @command="selectLang2">
    <el-button type="primary">
      ELEMENTPLUS国际化<el-icon class="el-icon--right"><arrow-down /></el-icon>
    </el-button>
    <template #dropdown>
      <el-dropdown-menu>
        <el-dropdown-item command="en">英文</el-dropdown-item>
        <el-dropdown-item command="jp">日文</el-dropdown-item>
        <el-dropdown-item command="cn">中文</el-dropdown-item>
      </el-dropdown-menu>
    </template>
  </el-dropdown>
  <el-config-provider :locale="localexx">1</el-config-provider>
</template>

<script>
import { ElConfigProvider } from "element-plus";
import cn from "element-plus/lib/locale/lang/zh-cn";
import en from "element-plus/lib/locale/lang/en";
import ja from "element-plus/lib/locale/lang/ja";
import { computed, ref } from "vue";
export default {
  components: {
    [ElConfigProvider.name]: ElConfigProvider,
  },
  data() {
    return {
    };
  },

  methods: {
    selectLang(command) {
      this.$i18n.locale = command;
    },
  },
  setup() {
    const language = ref("ja");
    const localexx = computed(() =>
      language.value === "cn" ? cn : language.value === "en" ? en : ja
    );

    const selectLang2 = (command) => {
      language.value = command;
    };

    return {
      localexx,
      selectLang2,
    };
  },
};
</script>

<style>
</style>