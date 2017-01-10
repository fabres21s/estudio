jQuery(function () {
    'use strict';
    console.log('Upload js REVISION target revision');
    var url = 'UploadServlet?x=1';
    jQuery('#fileupload').fileupload({
    	url: url,
        dataType: 'json',
        done: function (e, data) {
            jQuery.each(data.result, function (index, file) {
            	console.log("se ha subido el archivo con la opción 1");
            });
        },
    }).prop('disabled', !jQuery.support.fileInput)
        .parent().addClass(jQuery.support.fileInput ? undefined : 'disabled');
});