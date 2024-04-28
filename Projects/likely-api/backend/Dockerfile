FROM oven/bun:1-alpine

WORKDIR /app

COPY package*.json ./

RUN bun install
RUN bun install --frozen-lockfile --production

COPY . .

CMD bun run src/index.js

EXPOSE 3000