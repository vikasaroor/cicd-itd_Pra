FROM node:current-alpine3.22
WORKDIR /app
COPY package.json .
RUN npm install
COPY src/ .
CMD ["node", "index.js"]
