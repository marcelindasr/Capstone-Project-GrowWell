FROM node:20

WORKDIR /usr/src/app

COPY package*.json ./

RUN npm install

COPY . .

ENV PORT=5000

CMD ["npm", "start"]
