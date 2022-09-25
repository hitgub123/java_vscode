<template>
  <el-tree
    :data="data"
    show-checkbox
    node-key="id"
    ref="cattree"
    :default-expanded-keys="[2, 3]"
    :default-checked-keys="checkedIds"
    :props="defaultProps"
    @check-change="getChecked"
  />
  <el-button type="success" @click="getChecked">获取选择</el-button
  >{{ selectedName }}
</template>

<script lang="ts" >
export default {
  props:['checkedIds'],
  data() {
    return {
      selectedName:'',
    }
  },
  setup() {
    const defaultProps = {
      children: "childrenxx",
      label: "labelxx",
      selectedName: "",
    };
    const data = [
      {
        id: 1,
        labelxx: "Level one 1",
        childrenxx: [
          {
            id: 4,
            labelxx: "Level two 1-1",
            childrenxx: [
              {
                id: 9,
                labelxx: "Level three 1-1-1",
              },
              {
                id: 10,
                labelxx: "Level three 1-1-2",
              },
            ],
          },
        ],
      },
      {
        id: 2,
        labelxx: "Level one 2",
        childrenxx: [
          {
            id: 5,
            labelxx: "Level two 2-1",
          },
          {
            id: 6,
            labelxx: "Level two 2-2",
          },
        ],
      },
      {
        id: 3,
        labelxx: "Level one 3",
        childrenxx: [
          {
            id: 7,
            labelxx: "Level two 3-1",
          },
          {
            id: 8,
            labelxx: "Level two 3-2",
          },
        ],
      },
    ];
    return {
      data,
      defaultProps,
      test: 666,
    };
  },
    methods: {
     getChecked(this: {test: number,$refs:any,selectedName:string}) {
      console.log(this.test);
      console.log(this.$refs.cattree.getCheckedNodes(true));
      if (this.$refs.cattree.getCheckedNodes(true).length) {
        this.selectedName = this.$refs.cattree.getCheckedNodes(true)[0]['labelxx'];
      }
    },
  },
};
</script>


