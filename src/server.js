const Hapi = require("@hapi/hapi");
const Cookie = require("@hapi/cookie");
const routes = require("./routes");
require("dotenv").config();

const init = async () => {
  const server = Hapi.server({
    port: process.env.PORT || 8080,
    host: "0.0.0.0",
    routes: {
      cors: {
        origin: ["*"],
      },
    },
  });

  // Registrasi plugin Cookie
  await server.register(Cookie);

  // Registrasi plugin Inert untuk pengelolaan file statis
  await server.register(require("@hapi/inert"));

  // Registrasi plugin Vision untuk pengaturan tampilan
  await server.register(require("@hapi/vision"));

  // Middleware untuk mengurai payload JSON pada POST/PUT requests
  server.ext("onPostAuth", (request, h) => {
    if (request.method === "post" || request.method === "put") {
      if (typeof request.payload === "string") {
        try {
          request.payload = JSON.parse(request.payload);
        } catch (error) {
          console.error("Invalid JSON payload", error);
        }
      }
    }
    return h.continue;
  });

  // Konfigurasi cookie state
  server.state("data", {
    ttl: null,
    isSecure: false, // Set to true in production
    isHttpOnly: true,
    encoding: "base64json",
  });

  // Menambahkan rute
  server.route(routes);

  // Memulai server
  await server.start();
  console.log(`Listening on port ${server.info.uri}`);
};

process.on("unhandledRejection", (err) => {
  console.log(err);
  process.exit(1);
});

init();