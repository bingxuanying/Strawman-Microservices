const Pool = require("pg").Pool;
const { postgresSetting } = require("./config");

const connect = (mediator) => {
  mediator.once("boot.ready", () => {
    const pool = new Pool(postgresSetting);

    pool.connect((err, client, release) => {
      if (err) {
        mediator.emit("db.error", err);
      }

      client.query("SELECT NOW()", (err, result) => {
        release();
        if (err) {
          mediator.emit("db.error", err);
        }
        console.log(result.rows);
        mediator.emit("db.ready", pool);
      });
    });
  });
};

module.exports = Object.assign({}, connect);
