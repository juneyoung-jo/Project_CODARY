module.exports = {
  configureWebpack : { 
    externals : { 
      vue : 'Vue' , 
      vuetify : 'Vuetify' 
    } 
  },
  devServer: {
    disableHostCheck:true, 
  },
  transpileDependencies: [
    "vuetify"
  ]
}