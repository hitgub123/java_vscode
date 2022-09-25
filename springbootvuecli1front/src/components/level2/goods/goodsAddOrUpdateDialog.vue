<template>
  <el-dialog v-model="dialogVisible" title="Shipping address">
    <el-breadcrumb class="inner">
      <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item :to="{ path: '/goods' }"
        >商品管理</el-breadcrumb-item
      >
      <el-breadcrumb-item>商品添加或修改</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="inner">
      <el-form
        :model="goodsForm"
        :rules="rules"
        ref="goodsFormsss"
        label-width="100px"
      >
        <el-form-item label="商品名" prop="name">
          <el-input v-model="goodsForm.name"></el-input>
        </el-form-item>

        <el-form-item
          ><el-col class="marginleft0">
            <el-form-item prop="price" label="价格">
              <el-input
                v-model="goodsForm.price"
                placeholder="Please Input"
              /> </el-form-item
          ></el-col>
          <el-col>
            <el-form-item prop="count" label="数量"
              ><el-input
                v-model="goodsForm.count"
                placeholder="Please Input"
              /> </el-form-item
          ></el-col>
          <el-col>
            <el-form-item label="类别" prop="region">
              <el-select
                v-model="goodsForm.category"
                placeholder="请选择活动区域"
              >
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select> </el-form-item
          ></el-col>
          <el-col>
            <el-form-item label="是否包邮">
              <el-radio-group v-model="goodsForm.baoyou">
                <el-radio :label="1">包邮</el-radio>
                <el-radio :label="0">不包邮</el-radio>
              </el-radio-group>
            </el-form-item></el-col
          >
        </el-form-item>

        <el-form-item label="商品卖点" prop="point">
          <el-checkbox-group v-model="goodsForm.checkList">
            <el-checkbox label="Option A" />
            <el-checkbox label="Option B" />
            <el-checkbox label="Option C" />
            <el-checkbox label="disabled" disabled />
            <el-checkbox label="selected and disabled" disabled />
          </el-checkbox-group>
        </el-form-item>

        <el-form-item
          ><el-col class="marginleft0">
            <el-form-item label="进口商品">
              <el-switch
                v-model="goodsForm.switch1"
                active-text="进口"
                inactive-text="非进口" /></el-form-item
          ></el-col>
          <el-col>
            <el-form-item label="修改时间">
              <el-date-picker
                v-model="goodsForm.time"
                type="datetime"
                placeholder="Select date and time"
                :default-time="defaultTime" /></el-form-item></el-col
          ><el-col>
            <el-form-item label="商品评分">
              <el-rate
                v-model="goodsForm.rate1"
                show-score="true"
                allow-half="true"
              /> </el-form-item></el-col
        ></el-form-item>

        <el-form-item label="商品描述" prop="desc">
          <el-input
            v-model="goodsForm.descr"
            :rows="2"
            type="textarea"
            placeholder="Please input"
        /></el-form-item>

        <el-form-item>
          <el-button type="primary" size="medium">类别选择</el-button>
          <el-button type="danger" size="medium">上传图片</el-button>
          <el-button type="primary" size="medium" @click="submitForm"
            >保 存</el-button
          >
          <el-button type="danger" size="medium" @click="reset"
            >reset</el-button
          >
        </el-form-item>
      </el-form>
    </div>
  </el-dialog>
</template>
 
<script>
import { ref, reactive, watchEffect, toRefs } from "vue";
export default {
  props: ["show",'vCount'],
  data() {
    return {
      goodsForm: {
        name: Math.floor(Math.random() * 100),
        count: Math.floor(Math.random() * 100),
        price: Math.floor(Math.random() * 100),
        descr: Math.floor(Math.random() * 100),
        checkList: [],
        baoyou: true,
        switch1: true,
        rate1: 4,
        textarea1: "",
        time: null,
        category: "",
      },
      rules: {
        name: [
          { required: true, message: "请输入名", trigger: "blur" },
          { min: 2, max: 9, message: "长度2到9", trigger: "blur" },
        ],
        price: [{ required: true, message: "请输入名", trigger: "blur" }],
        count: [{ required: true, message: "请输入数量", trigger: "blur" }],
        region: [{ required: true, message: "请输入价格", trigger: "blur" }],
        point: [{ required: true, message: "请输入卖点", trigger: "blur" }],
        desc: [{ required: true, message: "请输入描述", trigger: "blur" }],
      },
    };
  },
  methods: {
    reset() {
      this.goodsForm = {};
    },
    submitForm() {
      this.$emit("setDialogFormVisible",false);
    },
  },
  setup(props) {
    const state = reactive({ dialogVisible: "" ,vCount:0});
    watchEffect(() => {
      state.dialogVisible = props.show;
      state.vCount = props.vCount;
    });
    return { ...toRefs(state) };
  },
};
</script>

<style  lang='less' scoped>
.inner {
  padding: 15px;
  margin: 15px;
  font-size: 20px;
  background: #fff;
}
.w-50 {
  width: 12.5rem;
}
.marginleft0 {
  margin-left: -100px;
}
</style>
