var http = require("http");
var url  = require("url");

function iniciar(route) {
	http.createServer(function(request, response) {
		var pathname = url.parse(request.url).pathname;
		console.log("Petición recibida para "+pathname);

		route(pathname);		

		response.writeHead(200, {"Content-Type": "text/html"});
		response.write("Hola mundo");
		response.end();

	}).listen(8888);

	console.log("Servidor iniciado");

}

exports.iniciar = iniciar;
