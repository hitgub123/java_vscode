<template>
  <el-upload
    ref="uploadRef"
    class="upload-demo"
    action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
    :on-remove="onRemove"
    :auto-upload="false"
  >
    <template #trigger>
      <el-button type="primary">select file</el-button>
    </template>

    <el-button class="ml-3" type="success" @click="submitUpload">
      upload to server
    </el-button>

    <template #tip>
      <div class="el-upload__tip">
        jpg/png files with a size less than 500kb
      </div>
    </template>
  </el-upload>
</template>

<script lang="ts">
import { ref } from "vue";
import  UploadInstance from "element-plus";
export default {
  methods:{
    onRemove(f: File,fs: FileList){
      console.log(f)
      console.log(fs)
    },
    submitUpload(this:{$refs:any}){
      console.log(this.$refs.uploadRef)
      this.$refs.uploadRef.submit()
    },
  },

};
</script>


