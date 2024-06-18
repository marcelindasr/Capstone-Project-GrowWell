const Hapi = require("@hapi/hapi");
const Cookie = require("@hapi/cookie");
const routes = require("./routes");
require("dotenv").config();

const init = async () => {
  const server = Hapi.server({
    port: process.env.PORT,
    host: "0.0.0.0",
    routes: {
      cors: {
        origin: ["*"],
      },
    },
  });

  await server.register(Cookie);

  server.state("data", {
    ttl: null,
    isSecure: false, // Set to true in production
    isHttpOnly: true,
    encoding: "base64json",
  });

  server.route(routes);

  await server.start();
  console.log(`Listening on port ${server.info.uri}`);
};

process.on("unhandledRejection", (err) => {
  console.log(err);
  process.exit(1);
});

init();