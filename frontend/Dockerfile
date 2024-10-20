FROM nginx:alpine

ENV BE_GATEWAY_URL=http://noted-gateway-service:8080

COPY dist /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf
