"use strict";

"use strict";

const repository = (db) => {
  const getAllProductId = () => {};

  return Object.create({
    getAllProductId,
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
