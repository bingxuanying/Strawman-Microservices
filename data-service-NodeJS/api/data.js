"use strict";

module.exports = (app, options) => {
  const { repo } = options;

  app.get("/", (req, res) => {
    res.send("OK");
  });

  app.get("/api/allProductId", (req, res, next) => {
    repo
      .getAllProductId()
      .then((IdArr) => {
        res.status(200).json(IdArr);
      })
      .catch(next);
  });
};
