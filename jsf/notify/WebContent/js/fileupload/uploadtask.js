jQuery(function () {
    'use strict';
    console.log('Upload js task target task');
    var url = '/portal/esse-unika-actas-portlet/UploadServlet?target=task';
    jQuery('#fileuploadtask').fileupload({
    	url: url,
        dataType: 'json',
        done: function (e, data) {
            jQuery.each(data.result, function (index, file) {
            	if(file.error != undefined){
                	jQuery("[id*='buttonerrorupdatedata']").click();
            	} else{
                	jQuery("[id*='buttonupdatedata']").click();
            	}
            });
        },
    }).prop('disabled', !jQuery.support.fileInput)
        .parent().addClass(jQuery.support.fileInput ? undefined : 'disabled');
});