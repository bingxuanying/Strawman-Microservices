const express = require("express");
const morgan = require("morgan");
const api = require("../api/data");

const start = (options) => {
  return new Promise((resolve, reject) => {
    if (!options.repo) {
      reject(
        new Error("The server must be started with a connected respository")
      );
      if (!options.port) {
        reject(new Error("The server must be started with an available port"));
      }

      const app = express();
      app.use(morgan("combined"));

      api(app, options);

      const server = app.listen(options.port, () => resolve(server));
    }
  });
};

module.exports = Object.assign({}, { start });
