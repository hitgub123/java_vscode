const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  //更改默认端口
  devServer: {
    open: false, // 自动打开浏览器
    port: 8081,
  },
  //设置是否在开发环境下每次保存代码时都启用 eslint验证
  lintOnSave: false
})
