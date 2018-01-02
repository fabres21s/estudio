var http = require('http');
var fs = require('fs');
var dt = require('./time.js');

http.createServer(function(req,res) {
	fs.readFile('file.html', function(err,data){
		res.writeHead(200, {'Content-Type': 'text/html'});
		res.write(data);
		res.end();
	});

	//create file

	//appendFile appends specified content to a file
	//if the file does not exist, the file will be created
	fs.appendFile('newFile.txt', 'Hello content!\n', function(err){
		if (err) throw err;
		console.log('Saved! - appendFile');
	});

	//open takes a "flag" as the second argument
	//if the flag is 'w' for 'writing', the specified file is opened for writing
	//if the file does not exist, an empty file is created
	fs.open('newFile2.txt', 'w', function(err, file){
		if (err) throw err;
		console.log('Saved! - open');
	});

	//writeFile replaces the specified file and content if exists
	//if the file does not exisy, a new file, containing the specified content, will be created
	fs.writeFile('newFile3.txt', 'I am writing at '+ dt.myDateTime(), function(err){
		if (err) throw err;
		console.log('Saved - Write File');
	});

	//unlink deletes the file specified
	/*fs.unlink('newFile.txt', function(err){
		if (err) throw err;
		console.log('File deleted');
	});*/

	//rename renames the specified file
	fs.rename('newFile.txt', 'renamed.txt', function(err) {
		if (err) throw err;
		console.log('File renamed');
	});
}).listen(8080);