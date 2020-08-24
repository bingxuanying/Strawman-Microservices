"use strict";

module.exports = (app, options) => {
  const { repo } = options;

  app.get("/", (req, res) => {
    res.send("OK");
  });

  app.post("/api/fetchTrapIDs", (req, res, next) => {
    let { company } = req.body;

    repo
      .fetchTrapIDs(company)
      .then((IdArr) => {
        console.log(IdArr);
        res.status(200).json(IdArr);
      })
      .catch(next);
  });
};
