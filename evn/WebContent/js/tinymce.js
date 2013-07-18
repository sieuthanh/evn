tinyMCE.init({
		// General options
            mode : "textareas",
            theme : "advanced",
            plugins : "safari,pagebreak,style,layer,table,save,advhr,advimage,advlink,emotions,iespell,inlinepopups,insertdatetime,preview,media,searchreplace,print,contextmenu,paste,directionality,fullscreen,noneditable,visualchars,nonbreaking,xhtmlxtras,template,wordcount",

            // Theme options
            theme_advanced_buttons1 : ",bold,italic,underline,|,justifyleft,justifycenter,justifyright,justifyfull,fontselect,fontsizeselect,forecolor,|,bullist,numlist,|,outdent,indent,|,code,",
            theme_advanced_buttons2 : "",                       
            theme_advanced_toolbar_location : "top",
            theme_advanced_toolbar_align : "left",            
            theme_advanced_resizing : true,

            // Example content CSS (should be your site CSS)
            content_css : "css/content.css",

            // Drop lists for link/image/media/template dialogs
            template_external_list_url : "lists/template_list.js",
            
            // Replace values for the template plugin
            template_replace_values : {
                    username : "Some User",
                    staffid : "991234"
            }
});

function subStringResult(text){   
    var result =text   ;
//    if (text!=null && text.length>0){     
//       result = text.substring(0,3)=="<p>"?text.substring(3,text.length-4):result;       
//    }
   return   result;
}