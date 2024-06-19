FROM node:20

WORKDIR /usr/src/app

COPY package*.json ./
COPY firebaseService.json ./

RUN npm install

COPY . .

ENV HOST="0.0.0.0"

ENV PORT=5000

CMD ["npm", "start"]
