const { serverSetting, postgresSetting } = require("./config");
const postgres = require("./postgres");

module.exports = Object.assign(
  {},
  { serverSetting, postgresSetting, postgres }
);
