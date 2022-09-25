  
"eslintConfig": {
    -------省略-------，
    "rules": {
        // 解决Could not find a declaration file for module 
    "@typescript-eslint/no-var-requires": 0, 
        //// 解决Component name  should always be multi-word
    "vue/multi-word-component-names": "off"
    }
},    
// src\main.ts里以下两个注释用来解决Could not find a declaration file for module './plugins/element' 
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore
import installElementPlus from './plugins/element'

layout.vue有如下css，不写lang="less"无法解析嵌套的css，
需要npm install less -D和npm install less-loader -D后才能解析
<style lang="less" scoped>
.layout {
  .menuC,.menudemoC {
    min-height: 500px;
  }
}