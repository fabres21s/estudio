//Está desactualizado

var nodemailer = require('nodemailer');

var transporter = nodemailer.createTransport({
  service: 'gmail',
  auth: {
    user: 'myeamil@gmail.com',
    pass: 'mypassword'
  }
});

var mailOptions = {
  from: 'myemail@gmail.com',
  to: 'youremail@uniandes.edu.co',
  subject: 'Sending Email using Node.js',
  text: 'That was easy!'
};

transporter.sendMail(mailOptions, function(error, info){
  if (error) {
    console.log(error);
  } else {
    console.log('Email sent: ' + info.response);
  }
});