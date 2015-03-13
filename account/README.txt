打包：
mvn package -Dmaven.test.skip=true

发布：
mvn deploy -Dmaven.test.skip=true

部署流程：
1.修改数据库配置src/main/resources/config/dataSource.properties
2.修改注册中心配置src/main/resources/config/application.properties
3.顺序执行maven命令，生成部署包
  mvn clean compile
  mvn assembly:single
     会在target目录下生成payment-account-XXX-deployment文件夹和payment-account-XXX-deployment.zip文件
4.部署到服务器，执行bin目录下start.sh或start.bat