if (typeof addthis_widget15=="undefined"){
var addthis_widget15='addthis'; 
var at15O=''; 
var at15o=''; 
var at15Y=''; 
var autoclose = null;
var at15y=new Array( ); 
at15y['ask']=['Ask','ask.png']; 
at15y['delicious']=['Del.icio.us','delicious.png']; 
at15y['digg']=['Digg','digg.png']; at15y['email']=['Email','email.png']; 
at15y['favorites']=['Favorites','favorites.png']; at15y['facebook']=['Facebook','facebook.gif']; 
at15y['fark']=['Fark','fark.png']; at15y['furl']=['Furl','furl.gif']; at15y['google']=['Google','goog.png']; 
at15y['live']=['Live','live.gif']; at15y['myspace']=['MySpace','myspace.png']; 
at15y['myweb']=['Yahoo MyWeb','yahoo-myweb.png']; at15y['newsvine']=['Newsvine','newsvine.png'];
at15y['reddit']=['Reddit','reddit.gif']; at15y['sk*rt']=['Sk*rt','skrt.gif']; at15y['slashdot']=['Slashdot','slashdot.png'];
at15y['stumbleupon']=['StumbleUpon','su.png']; at15y['stylehive']=['Stylehive','stylehive.gif']; 
at15y['tailrank']=['Tailrank','tailrank2.png']; at15y['technorati']=['Technorati','technorati.png']; 
at15y['thisnext']=['ThisNext','thisnext.gif']; at15y['twitter']=['Twitter','twitter.gif']; 
at15y['more']=['More ...','more.gif']; 

function at15I(at15i){
    return document.getElementById(at15i);
}

function addthis_clearclosewin15( ){
if (typeof at15A!="undefined")  clearTimeout(at15A); 
}
function addthis_open(at15a,title,at15E,at15e,at15U,close){
       
        drawHtml(close)
	addthis_clearclosewin15( ); 
	addthis_url=at15e;
        addthis_title=at15U;         
	var at15u=020;
        var at15Z=at15a.getElementsByTagName('img');
	if (at15Z && at15Z[0]){
            at15a=at15Z[0]; at15u=0; 
        }
        addthis_caption_share=title;
        atshow(at15E); 
	var at15z=at15X(at15a); 
	var at15x=at15z[0]+addthis_offset_left; 
	var at15W=at15z[1]+at15u+1+addthis_offset_top; 
	var at15w=at15V( ); var at15v=at15T( );        
	var at15t=at15I('addthis_dropdown15');  
        at15t.style.display='block';       
        if (at15x-at15v[0]+at15t.clientWidth+024>at15w[0])at15x=at15x-at15t.clientWidth; 
        if (at15W-at15v[1]+at15t.clientHeight+at15a.clientHeight+024>at15w[1])at15W=at15W-at15t.clientHeight-024; 
        at15t.style.left=at15x+'px'; 
        at15t.style.top=(at15W+at15a.clientHeight)+'px'; 
        at15S(); 
        if(typeof close=="undefined"){
            at15A=setTimeout("addthis_closewin15()",4500);
        }
        return false; 
}
        
function at15S( ){
        if (at15s)return; 
        var at15R=addthis_options.replace(/\s/g,''); 
        var at15r=at15R.split(','); 
        for (var at15Q=0; at15Q<at15r.length; at15Q++){
        var at15q=at15r[at15Q]; 
        if (at15q in at15y){
        var at15P=at15I('addthis_'+at15q+'15'); if (at15P)at15P.src=at15o+at15y[at15q][1]; }}at15s= true; 
}

function addthis_close( ){
        at15A=setTimeout("addthis_closewin15()",0762);        
       
}

function addthis_closewin15( ){       
        var at15t=at15I('addthis_dropdown15');        
        if(at15t){
            at15t.style.display='none';                   
            if (getObj('tdComboOption')!=null) getObj('tdComboOption').style.display ='block';
            if (getObj('tdcomboUp')!=null) getObj('tdcomboUp').style.display ='block';
            if (getObj('tdcomboExpressId')!=null) getObj('tdcomboExpressId').style.display ='block';
            if (getObj('tdcomboViaId')!=null) getObj('tdcomboViaId').style.display ='block';
            if (getObj('tdcomboSecureId')!=null) getObj('tdcomboSecureId').style.display ='block';
            if (getObj('tdcomboClassifyId')!=null) getObj('tdcomboClassifyId').style.display ='block';
            if (getObj('tdcomboDocsTypeId')!=null) getObj('tdcomboDocsTypeId').style.display ='block';
            if (getObj('tdcomboStoreAgeId')!=null) getObj('tdcomboStoreAgeId').style.display ='block';
            
            
        }
        return false; 
}

function addthis_none(){       
                         
    if (getObj('tdComboOption')!=null) getObj('tdComboOption').style.display ='none';
    if (getObj('tdcomboUp')!=null) getObj('tdcomboUp').style.display ='none';
    if (getObj('tdcomboExpressId')!=null) getObj('tdcomboExpressId').style.display ='none';
    if (getObj('tdcomboViaId')!=null) getObj('tdcomboViaId').style.display ='none';
    if (getObj('tdcomboSecureId')!=null) getObj('tdcomboSecureId').style.display ='none';
    if (getObj('tdcomboClassifyId')!=null) getObj('tdcomboClassifyId').style.display ='none';
    if (getObj('tdcomboDocsTypeId')!=null) getObj('tdcomboDocsTypeId').style.display ='none';
    if (getObj('tdcomboStoreAgeId')!=null) getObj('tdcomboStoreAgeId').style.display ='none';                  
    return false; 
}
function addthis_sendto(at15p){
            if (at15p=='email'){
            atshow(at15p); 
            return false; 
            }
            addthis_closewin15( ); 
            if (at15p=='favorites'){
            if (document.all)window.external.AddFavorite(addthis_url,addthis_title); 
            else window.sidebar.addPanel(addthis_title,addthis_url,''); 
            return false; 
            }
            if (at15p=='stumbleupon')at15p='su'; 
            if (at15p=='sk*rt')at15p='skrt'; 
            window.open(at15N(at15p),'addthis','scrollbars=no,menubar=no,width=310px,height=no,resizable=yes,toolbar=no,location=no,status=no'); 
            return false; 
}

function at15N(at15p){
var at15n=encodeURIComponent(addthis_url); 
var at15M=encodeURIComponent(addthis_title); 
var at15m=encodeURIComponent(addthis_logo);
return 'http://www.addthis.com/bookmark.php?v=15&winname=addthis&pub='+addthis_pub+'&s='+at15p+'&url='+at15n+'&title='+at15M+'&logo='+at15m+'&logobg='+addthis_logo_background+'&logocolor='+addthis_logo_color; 
}
function at15X(at15L){
    var at15l=0,at15K=0; 
    do {at15l+=at15L.offsetTop || 0; at15K+=at15L.offsetLeft || 0; at15L=at15L.offsetParent; 
    }
    while (at15L); 
  return [at15K,at15l]; 
}

function at15V( ){
    var at15k=0; var at15J=0; 
    if (typeof(window.innerWidth)=='number'){at15k=window.innerWidth; at15J=window.innerHeight; 
    }else if (document.documentElement && (document.documentElement.clientWidth || document.documentElement.clientHeight)){
    at15k=document.documentElement.clientWidth; at15J=document.documentElement.clientHeight; 
    }else if (document.body && (document.body.clientWidth || document.body.clientHeight)){
    at15k=document.body.clientWidth; at15J=document.body.clientHeight; 
    }
    return [at15k,at15J]; 
}
function at15T( ){
        var at15j=0; var at15H=0; 
        if (typeof(window.pageYOffset)=='number'){
        at15H=window.pageYOffset; at15j=window.pageXOffset; 
        }else if (document.body && (document.body.scrollLeft || document.body.scrollTop)){at15H=document.body.scrollTop; at15j=document.body.scrollLeft; 
        }else if (document.documentElement && (document.documentElement.scrollLeft || document.documentElement.scrollTop))
        {
        at15H=document.documentElement.scrollTop; at15j=document.documentElement.scrollLeft; 
        }       
        return [at15j,at15H]; 
}

function at15h( ){
    var at15G=document.createElement('link'); 
    at15G.rel='stylesheet'; 
    at15G.type='text/css'; 
    at15G.href=at15O+'/styles/addthis_widget.css'; 
    at15G.media='all'; 
    document.lastChild.firstChild.appendChild(at15G);
}



function drawHtml(close){
        autoclose = close;
        at15h( ); 
        at15F( ); 
        var  at15f='<div  onmouseout="addthis_close()" id="addthis_dropdown15" class="addthis_dropdown15" onmouseover="addthis_clearclosewin15()" style="z-index: 1000000; position: absolute; display: none">'; 
        if (autoclose!=null){
             at15f='<div id="addthis_dropdown15" class="addthis_dropdown15" style="z-index: 1000000; position: absolute; display: none">'; 
        }
        
                at15f+='<table width="100%" cellpadding="2" cellspacing="0" style="background-color: #EEEEEE; height: 18px">'; 
                at15f+='<tr><td style="font-size: 12px; color: #666666; padding-left:3px"><span id="at_caption">Danh s&#225;ch c&#244;ng v&#259;n</span></td><td align="right" style="font-size: 9px; color: #666666; padding-right: 3px">'+addthis_brand+'</td></tr>'; 
                at15f+='</table>'; 
                var at15D= false; 
                at15f+='<div id="at_share">'; 
                at15f+='<table id="addthis_services" width="100%" cellpadding="0" style="font-family: Verdana, Arial; font-size: 11px">'; 
                at15f+='<tr><td colspan="2" style="height: 0px"></td></tr>'; 
                var at15r=addthis_options.split(','); 
                for (var at15Q=0; at15Q<at15r.length; at15Q++){
                        var at15q=at15r[at15Q];
                        if (at15q in at15y){
                                if (!at15D)at15f+='<tr>'; 
                                at15f+='\x3ctd width=\"50%\" style=\"height: 19px\">\x3ca href=\"/\" onclick=\"return addthis_sendto(\''+at15q+'\');\">\x3cimg id=\"addthis_'+at15q+'15" alt="" width="16" height="16" />&nbsp; '+at15y[at15q][0]+'</a></td>';
                                if (at15D)at15f+='</tr>'; 
                                at15D=!at15D; 
                        }
                }
                
                if (at15D)at15f+='<td></td></tr>'; 
                at15f+='<tr><td colspan="2" style="height: 2px"></td></tr>'; 
                at15f+='</table>'; 
                at15f+='\x3c/div>\n'; 
                
                at15f+='<div id="at_email" style="display: none; font-size: 11px; padding-left: 20px; padding-top: 6px">'; 
                at15f+='<table border="0">'; 
                at15f+='<tr><td style="font-size: 12px">To:</td><td><input id="at_to" type="text" style="width: 130px; height: 18px; font-size: 11px; font-family: Arial; color: #999999" value=" email address" onfocus="checkto(this)" /></td</tr>'; 
                at15f+='<tr><td style="font-size: 12px">From:</td><td><input id="at_from" type="text" style="width: 130px; height: 18px; font-size: 11px; font-family: Arial; color: #999999" value=" email address" onfocus="checkto(this)"/></td</tr>'; 
                at15f+='<tr><td style="font-size: 12px" valign="top">Note:</td><td><textarea id="at_msg" style="width: 130px; height: 36px; font-size: 11px; font-family: Arial;"/></textarea></td</tr>'; 
                at15f+='<tr><td colspan="2" align="right"><span id="at_success" style="font-size: 12px; color: #777777;"></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="Send" onclick="return addthis_send()" style="font-size: 9px"/></td</tr>'; 
                at15f+='</table>'; 
                at15f+='\x3c/div>\n'; 
                at15f+='<table width="100%" cellpadding="0" cellspacing="0" border="0" style="background-color: #EEEEEE">';
                at15f+='<tr><td colspan="2" align="right" style="padding: 0px; padding-right: 10px; height: 11px"><img id="at_img" src="'+at15Y+'select_load.png" style="width: 1px; height: 1px" /></a></td></tr>'; 
                at15f+='\x3c/table>\n'; 
        at15f+='\x3c/div>\n'; 
        at15d=document.createElement('div');      
        at15d.innerHTML=at15f;       
        document.body.insertBefore(at15d,document.body.firstChild); 
        at15d.style.zIndex=03641100;
        
}

function at15F( ){        
        at15s= false; 
        if (typeof addthis_pub=='undefined')addthis_pub=''; 
        if (typeof at15C=='undefined')at15C='#'; 
        if (typeof addthis_brand=='undefined')addthis_brand=''; 
        if (autoclose!=null) addthis_brand='<img  onclick="addthis_close()"  src="images/close.png" border="0" />'; 
        if (typeof addthis_logo=='undefined')addthis_logo=''; 
        if (typeof addthis_logo_background=='undefined')addthis_logo_background=''; 
        if (typeof addthis_logo_color=='undefined')addthis_logo_color=''; 
        if (typeof addthis_options=='undefined')addthis_options=''; 
        if (typeof addthis_offset_top!='number')addthis_offset_top=0;
        if (typeof addthis_offset_left!='number')addthis_offset_left=0;
        if (typeof addthis_caption_share=='undefined')addthis_caption_share='Danh s&#225;ch c&#244;ng v&#259;n'; 
        if (typeof addthis_caption_email=='undefined')addthis_caption_email='Email a Friend'; 
        addthis_options=addthis_options.replace(/\s/g,''); 
}

function atshow(at15c){
        var at15B=at15I('at_share'); 
        var at15b=at15I('at_email'); 
        var at15o0=at15I('at_caption'); 
        var at15O0=at15I('at_success'); 
        at15B.style.display='none'; 
        at15b.style.display='none'; 
        at15O0.innerHTML=''; 
        at15B.style.display='block'; 
        at15o0.innerHTML=addthis_caption_share; 
        
}


function addthis_send( ){
        var at15l0=at15I('at_from');
        var at15i0=at15I('at_to'); 
        var at15I0=at15I('at_img'); 
        var at15O0=at15I('at_success'); 
        var at15o1=at15I('at_msg'); 
        if (at15l0.value.indexOf('@')<0 || at15i0.value.indexOf('@')<0 || at15l0.value.indexOf('.')<0 || at15i0.value.indexOf('.')<0){alert('Please enter a valid email address!'); return; }at15O1=Math.random( ); at15l1='http://www.addthis.com/tellfriend.php?pub='+encodeURIComponent(addthis_pub)+'&url='+encodeURIComponent(addthis_url)+'&fromname=aaa&fromemail='+encodeURIComponent(at15l0.value)+'&tofriend='+encodeURIComponent(at15i0.value)+'&note='+encodeURIComponent(at15o1.value)+'&r='+at15O1; at15I0.src=at15l1; at15O0.innerHTML='Message Sent!'; at15A=setTimeout("addthis_closewin15()",02260); 
        return false; 
}

function checkto(at15i1){
    at15i1.style.color='#000000'; if (at15i1.value==' email address')at15i1.value=''; 
}

}




    function hideElementDisplay( elmID, overDiv )
    {
     
      if( ie )
      {    
        for( i = 0; i < document.all.tags( elmID ).length; i++ )
        {
          obj = document.all.tags( elmID )[i];         
          if( !obj || !obj.offsetParent )
          {
            continue;
          }
      
          // Find the element's offsetTop and offsetLeft relative to the BODY tag.
          objLeft   = obj.offsetLeft;
          objTop    = obj.offsetTop;
          objParent = obj.offsetParent;
          
          while( objParent.tagName.toUpperCase() != "BODY" )
          {
            objLeft  += objParent.offsetLeft;
            objTop   += objParent.offsetTop;
            objParent = objParent.offsetParent;
          }
      
          objHeight = obj.offsetHeight;
          objWidth = obj.offsetWidth;
      
          if(( overDiv.offsetLeft + overDiv.offsetWidth ) <= objLeft );
              else if(( overDiv.offsetTop + overDiv.offsetHeight ) <= objTop );
              else if( overDiv.offsetTop >= ( objTop + objHeight ));
              else if( overDiv.offsetLeft >= ( objLeft + objWidth ));
          else
          {
            obj.style.visibility = "hidden";
          }
        }
      }
    }


