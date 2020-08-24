const { Pool } = require("pg");
const { postgresSetting } = require("./config");

const connect = (mediator) => {
  mediator.once("boot.ready", () => {
    const pool = new Pool({
      host: postgresSetting.host,
      user: postgresSetting.user,
      database: postgresSetting.database,
      password: postgresSetting.password,
      port: postgresSetting.port,
    });

    pool.query("SELECT NOW()", (err, res) => {
      if (err) {
        mediator.emit("db.error", err);
      }

      // console.log(res.rows);

      mediator.emit("db.ready", pool);
    });
  });
};

module.exports = Object.assign({}, { connect });
