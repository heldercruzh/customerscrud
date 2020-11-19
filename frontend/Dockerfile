FROM nginx:alpine as customers_angular
WORKDIR '/usr/share/nginx/html/'
VOLUME /var/cache/nginx
# NPM + NODE
RUN apk add nodejs
RUN apk add npm
COPY package.json .
RUN npm install --silent
COPY . .
ENTRYPOINT ["npm", "run", "start"]
#CMD cd /app && npm install && npm start --external 

#FROM node:14.2.0-alpine as customers_angular
#WORKDIR /app
#COPY package.json /app
#RUN npm install --silent
#COPY . .
#RUN npm run build

#FROM nginx:alpine
#VOLUME /var/cache/nginx
#COPY --from=customers_angular app/dist/frontend /usr/share/nginx/html
#COPY ./config/nginx.conf /etc/nginx/conf.d/default.conf

#/* proxy-config proxy.conf.js */
