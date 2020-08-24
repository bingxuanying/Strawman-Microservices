const serverSetting = {
  port: process.env.PORT || 3000,
};

// const pool = new Pool({
//     user: 'dbuser',
//     host: 'database.server.com',
//     database: 'mydb',
//     password: 'secretpassword',
//     port: 3211,
//   })

const postgresSetting = {
  user: process.env.POSTGRES_USER || "postgres",
  host: process.env.POSTGRES_HOST || "localhost",
  database: process.env.POSTGRES_DB || "test",
  password: process.env.POSTGRES_PASSWORD || "123qwe",
  port: process.env.POSTGRES_PORT || null,
};

module.exports = Object.assign({}, { serverSetting, postgresSetting });
