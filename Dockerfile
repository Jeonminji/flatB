FROM node:12.16.2 as builder

#작업 폴더 생성
RUN mkdir /front
WORKDIR /front

ENV PATH /front/node_modules/.bin:$PATH

COPY /Front/flatb_front/package.json /front/package.json

RUN apt-get update && apt-get install sudo

RUN curl -fsSL https://deb.nodesource.com/setup_12.x && sudo apt-get install -y nodejs && sudo npm cache clean --force && sudo npm install -g n && sudo n stable && sudo npm install -g npm && npm install

COPY /Front/flatb_front/. /front

RUN sudo npm run build

FROM nginx

RUN rm /etc/nginx/conf.d/default.conf

COPY /Front/flatb_front/default.conf /etc/nginx/conf.d

COPY --from=builder /front/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
~                                      
