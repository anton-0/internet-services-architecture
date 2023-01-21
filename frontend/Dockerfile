FROM node:18 AS builder

FROM nginx

RUN rm -rf /usr/share/nginx/html/*

COPY app /usr/share/nginx/html/

EXPOSE 80
COPY nginx.conf /etc/nginx/conf.d/default.conf
ENTRYPOINT ["nginx", "-g", "daemon off;"]