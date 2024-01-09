# 根据target中的jar构建镜像
docker build -f ./Dockerfile -t secretsiden/chatgpt-data:v-1.0 .

# 推送镜像至远程docker仓库
docker push secretsiden/chatgpt-data:v-1.0

# 服务器上进行更新操作
#docker stop chatgpt-data && docker rm chatgpt-data && docker rmi secretsiden/chatgpt-data:v-1.0
#
## 更新服务
#docker run -p 8080:8080 --name chatgpt-data -d secretsiden/chatgpt-data:v-1.0