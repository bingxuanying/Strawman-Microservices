"use strict";

const repository = (db) => {
  const fetchTrapIDs = (company) => {
    return db.query(`SELECT * FROM users WHERE company=${company};`, [
      "brianc",
    ]);
  };

  return Object.create({
    fetchTrapIDs,
  });
};

const connect = (connection) => {
  return new Promise((resolve, reject) => {
    if (!connection) {
      reject(new Error("DB connection not supllied"));
    }
    resolve(repository(connection));
  });
};

module.exports = Object.assign({}, { connect });
