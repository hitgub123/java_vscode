<template>
  <div style="text-align: center">
    <el-pagination
      background
      layout="total,prev, pager, next,jumper"
      :total="total"
      :pageSize="pageSize666"
      :current-page="currentPage"
      @current-change="changePage"
    />
  </div>
</template>

<script>
export default {
  props: ["total", "pageSize666",'currentPage'],
  methods: {
    changePage(currentPage1) {
      this.$emit("searchF", { page: currentPage1 });
    },
  },
};
</script>

<style>
</style>