FROM nginx

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY app .

EXPOSE 80
COPY nginx.conf /etc/nginx/conf.d/default.conf
ENTRYPOINT ["nginx", "-g", "daemon off;"]