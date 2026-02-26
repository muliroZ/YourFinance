FROM oven/bun:1.3.9-alpine
WORKDIR /app

COPY package.json bun.lock ./
RUN bun install

EXPOSE 5173

ENTRYPOINT ["bun", "run", "dev", "--host"]