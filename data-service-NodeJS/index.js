"use strict";
const { EventEmitter } = require("events");
const config = require("./config/");
const server = require("./server/server");
const repository = require("./repository/repository");
const mediator = new EventEmitter();

console.log("--- Data Service ---");

process.on("uncaughtException", (err) => {
  console.error("Unhandled Exception: ", err);
});

process.on("unhandledRejection", (err, promise) => {
  console.error("Unhandled Rejection", err);
});

config.postgres.connect(mediator);

mediator.emit("boot.ready");

mediator.on("db.ready", (db) => {
  let rep;
  repository
    .connect(db)
    .then((repo) => {
      console.log("Connected. Starting Server...");
      rep = repo;
      return server.start({
        port: config.serverSetting.port,
        repo,
      });
    })
    .then((app) => {
      console.log(
        `Server started successfully, running on port: ${config.serverSetting.port}`
      );
      app.on("close", () => {
        rep.disconnect();
      });
    });
});

mediator.on("db.error", (err) => {
  console.error(err);
});
