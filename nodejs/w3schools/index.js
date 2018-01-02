var http = require('http');
var dt = require('./time.js');
var url = require('url');

http.createServer(function (req, res)  {
	res.writeHead(200, {'Content-Type': 'text/html'});

	//es posible parsear (mapear) la url
	var q = url.parse(req.url, true).query;
	res.write("The date and time are currently : "+ dt.myDateTime() + " anho : "+q.anho);


	res.end();
}).listen(8080);
