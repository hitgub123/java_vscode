<template>
  <div>
    ({{ $t("message.hello") }} {{ $t("message.test") }})<h1>商品管理</h1>{{
      dialogFormVisible
    }}{{ vCount }}
  </div>
  <div class="header">
    <el-input v-model="input" @change="search1" placeholder="Please input" />
    <el-button type="primary" @click="search1">查询</el-button>
    <el-button type="success"
      ><router-link to="goods/addOrUpdate" style="color: #fff"
        >页面添加</router-link
      ></el-button
    >
    <el-button type="success" @click="setDialogFormVisible(true)"
      >弹窗添加</el-button
    >
    <el-button type="success" @click="setDialogFormVisible2(true)"
      >弹窗添加2</el-button
    >
  </div>
  <div class="wrapper">
    <el-table
      :data="tableData"
      stripe
      border
      :default-sort="{ prop: 'name', order: 'descending' }"
      style="width: 100%"
      height="250"
    >
      <el-table-column type="selection" width="55" />
      <el-table-column fixed prop="id" label="商品id" width="100" />
      <el-table-column
        prop="name"
        sortable
        label="名字"
        show-overflow-tooltip
      />
      <el-table-column prop="price" label="价格" />
      <el-table-column prop="count" label="数量" />
      <el-table-column prop="category" label="类别" />
      <el-table-column prop="pic" label="图片" />
      <el-table-column prop="sellpoint" label="卖点" />
      <el-table-column prop="description" label="描述" />
      <el-table-column
        #default="scopexx"
        fixed="right"
        label="操作"
        width="200"
      >
        <el-button type="warning" @click="update(scopexx.$index, scopexx.row)"
          >编辑</el-button
        >
        <el-button type="danger" @click="del(scopexx.$index, scopexx.row)"
          >删除</el-button
        ></el-table-column
      >
    </el-table>
  </div>
  <paginationC
    :total="total"
    :pageSize666="pageSize"
    :currentPage="currentPage"
    @searchF="search"
  />
  <goodsAddOrUpdateDialogC
    :vCount="vCount"
    :show="dialogFormVisible"
    @setDialogFormVisible="setDialogFormVisible"
  ></goodsAddOrUpdateDialogC>
  <goodsAddOrUpdateDialogC2 ref="dialog2"></goodsAddOrUpdateDialogC2>
</template>

<script>
import pagination from "/src/components/common/pagination.vue";
import goodsAddOrUpdateDialog from "/src/components/level2/goods/goodsAddOrUpdateDialog.vue";
import goodsAddOrUpdateDialog2 from "/src/components/level2/goods/goodsAddOrUpdateDialog2.vue";
export default {
  components: {
    paginationC: pagination,
    goodsAddOrUpdateDialogC: goodsAddOrUpdateDialog,
    goodsAddOrUpdateDialogC2: goodsAddOrUpdateDialog2,
  },
  data() {
    return {
      tableData: [],
      input: "",
      dialogFormVisible: false,
      vCount: 0,
      currentPage: 1,
    };
  },
  methods: {
    search1() {
      this.currentPage = 1;
      this.search({ page: 1 });
    },
    search(searchParam) {
      searchParam.input = this.input;
      if (searchParam.page) {
        this.currentPage = searchParam.page;
      }
      this.$api.goodsList(searchParam).then((res) => {
        if (res.status == 200) {
          this.tableData = res.data.data;
          this.total = res.data.total;
          this.pageSize = res.data.pageSize;
        }
      });
    },
    setDialogFormVisible(flg) {
      this.vCount++;
      this.dialogFormVisible = flg;
    },
    setDialogFormVisible2(flg) {
      this.$refs.dialog2.show2 = flg;
    },
    update(index, row) {
      console.log(index);
      console.log(row);
      this.setDialogFormVisible2(true);
      this.$api.selectOneGood({ id: row.id }).then((res) => {
        const data = res.data.data[0];
        data.checkList = [];
        data.checkedIds = [1, 8, 6];
        this.$refs.dialog2.goodsForm = data;
      });
    },
    del(index, row) {
      console.log("index", index);
      this.$confirm({
        title: "确认删除吗？",
        okText: "是",
        cancelText: "否",
        icon: "exclamation-circle",
      })
        .then(() => {
          this.$api.delGood({ id: row.id }).then((res) => {
            this.$message({ message: "删除成功", type: "info" });
            this.search1();
          });
        })
        .catch(() => {
          this.$message({ message: "取消删除", type: "error" });
        });
    },
  },
  created() {
    this.search({ page: 1 });
  },
};
</script>

<style scoped>
.header {
  display: flex;
  padding: 15px;
  button {
    margin-left: 10px;
  }
}
.wrapper {
  padding: 15px;
}
</style>