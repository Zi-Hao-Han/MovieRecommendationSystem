var express = require('express');
 var app = express();
 const hostname = '192.168.73.1';
 const port = 8888;
 app.use(express.static('./'));
 app.listen(port, hostname, () => {
  console.log(` running ......`);
 });