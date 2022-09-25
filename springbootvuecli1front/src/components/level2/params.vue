<template>
  <div>规格参数</div>
  <div>
    <h1>{{ mycount }}</h1>
    <button @click="changeMyCount">changeMyCount</button>
  </div>
</template>

<script>
import { ref } from "vue";
export default {
  setup() {
    const mycount = ref(1);
    const changeMyCount = () => {
      mycount.value = mycount.value + 2;
    };

    return {
      mycount,
      changeMyCount,
    };
  },
};
</script>

<style>
</style>