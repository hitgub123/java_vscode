<template>
  <el-dialog v-model="show2" title="Add商品">
    <div class="inner">
      <el-form
        :model="goodsForm"
        :rules="rules"
        ref="goodsFormsss"
        label-width="100px"
      >
        <el-form-item label="用户名" prop="name">
          <el-input v-model="goodsForm.name"></el-input>
        </el-form-item>

        <el-form-item
          ><el-col class="marginleft0">
            <el-form-item prop="pass" label="密码">
              <el-input
                v-model="goodsForm.pass"
                placeholder="Please Input"
              /> </el-form-item
          ></el-col>
          <el-col>
            <el-form-item prop="age" label="年龄"
              ><el-input
                v-model="goodsForm.age"
                placeholder="Please Input"
              /> </el-form-item
          ></el-col>
          <!-- <el-col>
            <el-form-item label="类别" prop="region">
              <el-select
                v-model="goodsForm.category"
                placeholder="请选择活动区域"
              >
                <el-option label="区域一" value="shanghai"></el-option>
                <el-option label="区域二" value="beijing"></el-option>
              </el-select> </el-form-item
          ></el-col> -->
          <el-col>
            <el-form-item label="性别">
              <el-radio-group v-model="goodsForm.sex">
                <el-radio :label="1">man</el-radio>
                <el-radio :label="0">woman</el-radio>
              </el-radio-group>
            </el-form-item></el-col
          >
        </el-form-item>

        <!-- <el-form-item label="爱好" prop="hobbys">
          <el-checkbox-group v-model="goodsForm.checkList">
            <el-checkbox label="吃饭" />
            <el-checkbox label="玩游戏" />
            <el-checkbox label="游泳" />
            <el-checkbox label="学习" disabled />
            <el-checkbox label="工作" disabled />
          </el-checkbox-group>
        </el-form-item> -->

        <!-- <el-form-item
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
        ></el-form-item>-->

        <!-- <el-form-item label="商品描述" prop="desc">
          <el-input
            v-model="goodsForm.descr"
            :rows="2"
            type="textarea"
            placeholder="Please input"
        /></el-form-item> -->

        <el-form-item>
          <!-- <el-button
            type="primary"
            size="medium"
            @click="categorySelectDialogFlg = true"
            >类别选择</el-button
          > -->
          <!-- <el-button
            type="danger"
            size="medium"
            @click="uploadPicDialogFlg = true"
            >上传图片</el-button
          > -->
          <el-button type="primary" size="medium" @click="add">保 存</el-button>
          <el-button type="danger" size="medium" @click="reset"
            >reset</el-button
          >
        </el-form-item>
<!-- 
        <el-dialog
          v-model="categorySelectDialogFlg"
          width="40%"
          title="添加类别"
          append-to-body
          ><categoryTreeC :checkedIds="goodsForm.checkedIds"
        /></el-dialog> -->

        <!-- <el-dialog
          v-model="uploadPicDialogFlg"
          width="30%"
          title="上传图片"
          append-to-body
        > 
          <uploadPicC />
        </el-dialog>-->
      </el-form>
    </div>
  </el-dialog>
</template>
 
<script>
import categoryTreeC from "./categoryTree.vue";
import uploadPicC from "./uploadPic.vue";
export default {
  components: { categoryTreeC, uploadPicC },
  data() {
    return {
      show2: false,
      categorySelectDialogFlg: false,
      uploadPicDialogFlg: false,
      goodsForm: {
        // name: Math.floor(Math.random() * 100),
        // pass: Math.floor(Math.random() * 100),
        // age: Math.floor(Math.random() * 100),
        // descr: Math.floor(Math.random() * 100),
        // checkList: [],
        // baoyou: true,
        // switch1: true,
        // rate1: 4,
        // textarea1: "",
        // time: null,
        // category: "",
        // checkedIds: [],
      },
      rules: {
        name: [
          { required: true, message: "请输入名", trigger: "blur" },
          { min: 2, max: 9, message: "长度2到9", trigger: "blur" },
        ],
        pass: [{ required: true, message: "请输入名", trigger: "blur" }],
        age: [{ required: true, message: "请输入数量", trigger: "blur" }],
        // region: [{ required: true, message: "请输入价格", trigger: "blur" }],
        // point: [{ required: true, message: "请输入卖点", trigger: "blur" }],
        // desc: [{ required: true, message: "请输入描述", trigger: "blur" }],
      },
    };
  },
  methods: {
    reset() {
      this.goodsForm = {};
    },
    add() {
      this.show2 = false;
      this.$api.addGood(this.goodsForm).then((res) => {
        this.goodsForm = { checkList: [] };
      });
    },
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
