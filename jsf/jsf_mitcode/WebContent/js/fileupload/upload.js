jQuery(function () {
    'use strict';
    console.log('upload js');
    var url = '/jsf_mitocode/UploadServlet?target=revision';
    jQuery('#fileupload').fileupload({
    	url: url,
        dataType: 'json',
        done: function (e, data) {
            jQuery.each(data.result, function (index, file) {
            	if(file.error != undefined){
                	//jQuery("[id*='buttonerrorupdatedata']").click();
            	} else{
                	console.log('subida exitosa')
            	}
            });
        },
    }).prop('disabled', !jQuery.support.fileInput)
        .parent().addClass(jQuery.support.fileInput ? undefined : 'disabled');
});